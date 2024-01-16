from sqlalchemy import select, MetaData, Table, Column, Integer, String
from sqlalchemy.ext.asyncio import create_async_engine





class AlchemyDB:
    async def init(self) -> None:
        self.engine = create_async_engine("sqlite+aiosqlite:///:memory:", echo=False)

        self.meta = MetaData()

        self.users = Table('users', self.meta,
                      Column('id', Integer, primary_key=True),
                      Column('username', String),
                      Column('password', String),
                      Column('number', Integer))

        async with self.engine.begin() as conn:
            await conn.run_sync(self.meta.create_all)

    async def add_user(self, username, password, number):
        async with self.engine.begin() as conn:
          result = await conn.execute(
              self.users.insert().values(
                  username=username,
                  password=password,
                  number=number
                )
          )
          return result.inserted_primary_key[0]
          
    async def get_user_number(self, username):
        async with self.engine.begin() as conn:
            result = await conn.execute(
                select(self.users).where(self.users.c.username == username)
            )
            return result.fetchone()[3]

    async def get_all_users(self):
        async with self.engine.begin() as conn:
            result = await conn.execute(
                select(self.users)
            )
            return result.fetchall()            