import cx_Oracle

conn=cx_Oracle.connect("lmh/java@localhost:1521/xe")
cursor = conn.cursor()
sql="select buyer_id,buyer_name,buyer_lgu from buyer"
cursor.execute(sql)

for row in cursor:
    for i in range(len(row)):
        print(row[i],end=" ")
    print()
cursor.close()
conn.close()

