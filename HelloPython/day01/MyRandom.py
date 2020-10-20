from random import * 

i = randint(1,2)
if i == 1:
    com="홀"
else:
    com="짝"

mine = input()
if com == mine:
    print(com)
    print("이겼습니다.")
else:
    print(com)
    print("졌습니다.")


