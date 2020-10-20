import sqlite3

# conn = sqlite3.connect("MyDB.db")
conn = sqlite3.connect("MyDB.db", isolation_level=None)
cursor = conn.cursor()
col01 = str(9)
# sql = "update mytable set col01=" + col01 + "where col03 = 3"
sql = "update mytable set col02=?, col03=?, where col01=?"
cursor.execute(sql, ('5','5','5'))
cursor.close()
# conn.commit()
conn.close()
