import numpy as np
data = np.load('x_train.npy')

# npy 데이터 눈에 보이게 출력
for i in data :
    print("--------------------------------------------------------------------------")
    for j in i :
        line = ''
        for k in j :
            if k > 1:
                k = 1
            elif k == 0 :
                k = 0
            line += str(k)
        print(line)