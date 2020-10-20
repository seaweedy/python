import sqlite3

conn = sqlite3.connect("MyDB.db")
cursor = conn.cursor()
sql = "delete from mytable where col02 = :1"
data ="3" 
cursor.execute(sql,data)
cursor.close()
conn.commit()
conn.close()
