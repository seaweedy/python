import sqlite3

# conn = sqlite3.connect("MyDB.db")
conn = sqlite3.connect("MyDB.db", isolation_level=None)
# Autocommit
cursor = conn.cursor()
sql = "insert into Mytable values(:1, :2, :3)"
data=('3','3','3')
cursor.execute(sql,data)
cursor.close()
# conn.commit()
conn.close()
