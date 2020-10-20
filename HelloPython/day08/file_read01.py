f = open("D:/A_TeachingMaterial/8.Python/workspace/HelloPython/day08/myfile","r")
lines = f.readlines()
for line in lines:
    print(line)
f.close
