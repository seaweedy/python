import cx_Oracle

conn=cx_Oracle.connect("lmh/java@localhost:1521/xe")
cursor = conn.cursor()
sql = "insert into pytable values(:1, :2, :3) "
data=('2','2','2')
cursor.execute(sql,data)
cursor.close()
conn.commit() # java는 auto commit 
conn.close()
