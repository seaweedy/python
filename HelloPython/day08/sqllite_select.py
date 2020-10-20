import sqlite3

conn = sqlite3.connect("MyDB.db")
cursor = conn.cursor()
cursor.execute("select * from Mytable")
for row in cursor:
    print(row)
#     for i in range(len(row)):
#         print(row[i], end="")
#     print()
cursor.close()
conn.close()
