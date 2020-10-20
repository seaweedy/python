import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic, QtCore
from dask.array.image import imread
from PyQt5.Qt import QPixmap, QIcon
from dask.dataframe.tests.test_rolling import idx
from PyQt5.uic.Compiler.qtproxies import QtWidgets
from PyQt5.QtWidgets import QMessageBox

#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("omok02.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)
        self.resize(800,800)
        self.ie = QIcon('0.png')
        self.iw = QIcon('1.png')
        self.ib = QIcon('2.png')
        self.flagTurn = True
        self.flagIng = True 
        self.int2d = [
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                                             
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0]
                      ]
        
        self.arr2d = [] # 큰배열
        
        for i in range(20): # 실제 배열에 이미지를 넣는다.
            arr = [] # 한줄짜리 배열
            for j in range(20):
                pb = QPushButton('',self)
                pb.setGeometry(j*40, i*40, 40, 40)
                pb.setIconSize(QtCore.QSize(40,40))
                pb.setIcon(self.ie)
                pb.setWhatsThis("{},{}".format(i, j)) # WhatThis에 x 값 y 값 입력
                pb.clicked.connect(self.myclick)
                arr.append(pb) # 한줄에 pb값을 넣는다.
            self.arr2d.append(arr) # 여러줄의 pb를 넣는다.
        self.mydraw() # 최초 실행시 arr2d에 맞는 이미지 출력
        
    def myclick(self): #클릭할 때 값 출력
        # 클릭한 곳의 좌표를 받아 int2d의 값을 변경하여 돌을 찍히게 함
        if(not self.flagIng):
            return
        a = self.sender().whatsThis() # 문자열로 x,y 좌표값을 받는다.
        b = a.split(",")# 문자에만 적용됨
        ii = int(b[0])
        jj = int(b[1])
        cnt_stone = 0
        
        if self.int2d[ii][jj] == 0: # 한 번 찍힌 곳은 0이 아니므로 찍을 수 없다.
            if self.flagTurn :
                self.int2d[ii][jj] = 1#찍힌 곳은 1로 바꿔 백돌 표시
                cnt_stone = 1
            else :
                self.int2d[ii][jj] = 2#찍힌 곳은 2로 바꿔 흑돌 표시
                cnt_stone = 2
            self.mydraw()
            
            up_cnt = self.getUp(ii,jj,cnt_stone)
            dw_cnt = self.getDown(ii,jj,cnt_stone)
            le_cnt = self.getLeft(ii,jj,cnt_stone)
            ri_cnt = self.getRight(ii,jj,cnt_stone)
            print("up_cnt : {}".format(up_cnt))
            print("dw_cnt : {}".format(dw_cnt))
            print("ri_cnt : {}".format(ri_cnt))
            print("le_cnt : {}".format(le_cnt))
            
            riup_cnt = self.getRightUp(ii,jj,cnt_stone)
            ridw_cnt = self.getRightDown(ii,jj,cnt_stone)
            leup_cnt = self.getLeftUp(ii,jj,cnt_stone)
            ledw_cnt = self.getLeftDown(ii,jj,cnt_stone)
            print("riup_cnt : {}".format(riup_cnt))
            print("ridw_cnt : {}".format(ridw_cnt))
            print("leup_cnt : {}".format(leup_cnt))
            print("ledw_cnt : {}".format(ledw_cnt))
            
            cnt5p = [0,0,0,0]
            cnt5p[0] = up_cnt+dw_cnt+1
            cnt5p[1] = le_cnt+ri_cnt+1
            cnt5p[2] = riup_cnt+ledw_cnt+1
            cnt5p[3] = leup_cnt+ridw_cnt+1
            
            for i in range(len(cnt5p)):
                if(cnt5p[i] == 5 and cnt_stone == 1):
                    QMessageBox.about(self, '승자는?', '백돌입니다.')
                    self.flagIng = False
                    
                elif(cnt5p[i] == 5 and cnt_stone == 2):
                    QMessageBox.about(self, '승자는?', '흑돌입니다.')
                    self.flagIng = False
    
            self.flagTurn = not self.flagTurn 
    # 위로 찍히는 같은 돌의 수를 판단
    def getUp(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            ii -= 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
    # 아래로 찍히는 같은 돌의 수를 판단
    def getDown(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            ii += 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
    
    # 우측으로 찍히는 같은 돌의 수를 판단
    def getRight(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            jj += 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
    
    # 좌측으로 찍히는 같은 돌의 수를 판단
    def getLeft(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            jj -= 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
        
    # 좌측으로 찍히는 같은 돌의 수를 판단
    def getRightUp(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            ii -= 1
            jj += 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
        
    # 좌측으로 찍히는 같은 돌의 수를 판단
    def getRightDown(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            ii += 1
            jj += 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
        
    # 좌측으로 찍히는 같은 돌의 수를 판단
    def getLeftUp(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            ii -= 1
            jj -= 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
        
    # 좌측으로 찍히는 같은 돌의 수를 판단
    def getLeftDown(self,ii,jj,cnt_stone):
        cnt = 0
        while True:
            ii += 1
            jj -= 1
            if(ii<0 or ii > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(jj<0 or jj > len(self.int2d)-1): # 파이선 배열 특성을 잡아주는 코드
                return cnt
            if(self.int2d[ii][jj] == cnt_stone) :
                cnt += 1
            else:
                break
        return cnt   
        
    def myshow2d(self): #2d 숫자 배열을 출력
        for arr in self.int2d:
            print(arr)
            
    def mydraw(self): #arr2d의 숫자에 맞는 이미지를 set해준다.
        ii = 0
        for line in self.arr2d:
            jj = 0
            for item in line:
                if self.int2d[ii][jj] == 0 :
                    item.setIcon(self.ie)
                if self.int2d[ii][jj] == 1 :
                    item.setIcon(self.iw)
                if self.int2d[ii][jj] == 2 :
                    item.setIcon(self.ib)
#                 print("{},{}".format(ii, jj))
                jj+=1
            ii +=1
#                 self.arr2d[line][item].setIcon(self.ib)
                        
                 




if __name__ == "__main__" :
    #QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv) 

    #WindowClass의 인스턴스 생성
    myWindow = WindowClass() 

    #프로그램 화면을 보여주는 코드
    myWindow.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()
