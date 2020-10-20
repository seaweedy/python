from random import * 

i = randint(1,3)

if i == 1:
    com = "가위"
elif i == 2:
    com = "바위"
else:
    com ="보"
    
x = input("가위/바위/보를 입력하세요")

if com == x :
    print("컴퓨터 : {}".format(com))
    print("사용자 : {}".format(x))
    print("비겼습니다.")
elif (com == "가위 " and x == "보") or (com == "바위" and x == "가위") or (com == "보" and x =="바위"):
    print("컴퓨터 : {}".format(com))
    print("사용자 : {}".format(x))
    print("졌습니다.")
elif (com == "가위 " and x == "바위") or (com == "바위" and x == "보") or (com == "보" and x =="가위"):
    print("컴퓨터 : {}".format(com))
    print("사용자 : {}".format(x))
    print("이겼습니다.")
else :
    print("제대로 입력하여주세요")
    