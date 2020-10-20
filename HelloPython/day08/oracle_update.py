import cx_Oracle

conn = cx_Oracle.connect("lmh/java@localhost:1521/xe")
cursor = conn.cursor()
col01 = str(4)
sql ="update pytable set col01 = "+ col01 
cursor.execute(sql)
cursor.close()
conn.commit()
conn.close()
