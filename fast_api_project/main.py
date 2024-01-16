import random
import database
from fastapi import FastAPI, Form, Request
from fastapi.responses import HTMLResponse, RedirectResponse
from fastapi.templating import Jinja2Templates


app = FastAPI()

templates = Jinja2Templates(directory="templates")


@app.on_event("startup")
async def startup():
    # initializing database
    global db
    db = database.AlchemyDB()
    await db.init()



@app.get("/", response_class=HTMLResponse)
async def index(request: Request):
    return templates.TemplateResponse("login.html", {"request": request, "show_number": False})

@app.post("/submit-form", response_class=HTMLResponse)
async def handle_form(request: Request, username: str = Form(...), password: str = Form(...)):
    number_to_show = 0
    try:  
      number_to_show = await db.get_user_number(username)
      show_number = True
    except Exception:
      show_number = False

    return templates.TemplateResponse("login.html", {
        "request": request,
        "show_number": show_number,
        "number": number_to_show
      }
    )


@app.get("/register", response_class=HTMLResponse)
async def register(request: Request):
    return templates.TemplateResponse("register.html", {"request": request})

@app.post("/handle-register")
async def handle_register(request: Request, username: str = Form(...), password: str = Form(...)):
    all_users = await db.get_all_users()
    for user in all_users:
      print(user)
      if user[1] == username:
        return RedirectResponse(url="/register", status_code=303)

    user_id = await db.add_user(username, password, random.randint(1, 100))
    return RedirectResponse(url="/", status_code=303)


