import random
import sys
from threading import Thread
import threading
import time

from Cython.Compiler.Options import pre_import
from PyQt5 import uic, QtGui
from PyQt5.Qt import QPixmap
from PyQt5.QtWidgets import *
from PyQt5.uic.Compiler.qtproxies import QtGui
from PyQt5.QtGui import *
from PyQt5 import QtCore


#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("tetris.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class Block():
    def __init__(self):
        self.kind = random.randint(1,7)
        self.status = 1
        self.i = 1
        self.j = 5
        
    def myinit(self):
        self.kind = random.randint(1,7)
        self.status = 1
        self.i = 1
        self.j = 5
        
    def __str__(self):
        return str(self.kind) +":"+ str(self.status)+":"+str(self.i)+":"+str(self.j)
        
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)
        self.resize(800,800)
        self.block2D = []
        self.stack2D = []
        self.scrin2D = []
        self.lbl2D = []
        self.block = Block()
        self.flagIng = True
        
        self.initBlock2Dstack2Dscrin2D()
            
        self.stack2D[19][0] = 11
        self.stack2D[19][1] = 11
        self.stack2D[19][2] = 11
        self.stack2D[19][3] = 11
        
        for i in range(len(self.scrin2D)):
            arr=[]
            for j in range(len(self.scrin2D[i])):
                lbl = QLabel('',self)
                lbl.setGeometry(30*j, 30*i, 28, 28)
                self.lbl2D[i][j].setPixmap(QPixmap("png.png"))
                arr.append(self.icon0)
            self.lbl2D.append(arr)
                
        self.myrender()
        self.print2D(self.scrin2D)
        
        t = threading.Thread(target=self.down_thread, args=(1, 50000))
        t.start()
        
        print(self.block)
        
    def down_thread(self,start,stop):
        for i in range(start,stop):
            time.sleep(1)
            self.realPress('16777237')
            
        
                
        
    def changeBlockStatus(self):
        if self.block.kind == 1 :
            self.block.status = self.block.status
        if self.block.kind == 2 or self.block.kind == 3 or self.block.kind ==4 :
            if self.block.status == 1:
                self.block.status =2
            elif self.block.status == 2:
                self.block.status =1
        if self.block.kind == 5 or self.block.kind == 6 or self.block.kind == 7 :
            if self.block.status == 1:
                self.block.status =2
            elif self.block.status == 2:
                self.block.status =3
            elif self.block.status == 3:
                self.block.status =4
            elif self.block.status == 4:
                self.block.status =1
                    
    
    # stack2D와 block2D를 합쳐서 scrin2D에 표시
    def moveStackBlock2Scrin(self):
        for i in range(len(self.block2D)):
            for j in range(len(self.block2D[i])):
                self.scrin2D[i][j] = self.block2D[i][j] + self.stack2D[i][j]
    
    # 블록의 종류에 따라 표시
    def setBlock2DWtithBlock(self):
        for i in range(len(self.block2D)):
            for j in range(len(self.block2D[i])):
                self.block2D[i][j] = 0
                
        if self.block.kind == 1 :
            if self.block.status == 1:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j+1] = self.block.kind
                self.block2D[self.block.i+1][self.block.j] = self.block.kind
                self.block2D[self.block.i+1][self.block.j+1] = self.block.kind
                
        if self.block.kind == 2 :
            if self.block.status == 1:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 2][self.block.j] = self.block.kind
            if self.block.status == 2:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i][self.block.j - 2] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind

        if self.block.kind == 3 :
            if self.block.status == 1:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j  + 1] = self.block.kind
            if self.block.status == 2:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j - 1] = self.block.kind

        if self.block.kind == 4: 
            if self.block.status == 1:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j - 1] = self.block.kind
            if self.block.status == 2:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                
        if self.block.kind == 5: 
            if self.block.status == 1:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
            if self.block.status == 2:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
            if self.block.status == 3:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
            if self.block.status == 4:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                
        if self.block.kind == 6: 
            if self.block.status == 1:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j + 1] = self.block.kind
            if self.block.status == 2:
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j - 1] = self.block.kind
            if self.block.status == 3:
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j - 1] = self.block.kind
            if self.block.status == 4:
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j + 1] = self.block.kind
            
        if self.block.kind == 7: 
            if self.block.status == 1:
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j - 1] = self.block.kind
            if self.block.status == 2:
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j  - 1] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j - 1] = self.block.kind
            if self.block.status == 3:
                self.block2D[self.block.i + 1][self.block.j] = self.block.kind
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j] = self.block.kind
                self.block2D[self.block.i - 1][self.block.j  + 1] = self.block.kind
            if self.block.status == 4:
                self.block2D[self.block.i][self.block.j - 1] = self.block.kind
                self.block2D[self.block.i][self.block.j + 1] = self.block.kind
                self.block2D[self.block.i][self.block.j] = self.block.kind
                self.block2D[self.block.i + 1][self.block.j + 1] = self.block.kind
                
    # 누를 시 이벤트     
    def keyPressEvent(self, e):
        keycode = e.key()
        self.realPress(keycode)
        
    def realPress(self, keycode):
        if not self.flagIng : 
            return
        
        pre_status = self.block.status
        pre_i = self.block.i
        pre_j = self.block.j
        flag_col_bound = False
        flag_down = False
            
        print(keycode)
        if keycode == 16777237:
            self.block.i+=1
            flag_down = True
        if keycode == 16777234:
            self.block.j -= 1
        if keycode == 16777236:
            self.block.j += 1
        if keycode == 16777235:
            self.changeBlockStatus()
        if keycode == 16777216:
            sys.exit()
            
        try:
            self.block.j = self.block.j -10 
            self.setBlock2DWtithBlock()
            self.block.j = self.block.j +10
            self.setBlock2DWtithBlock()
            
#             if self.block.i >= 20 or self.block.i <= 0 or self.block.j >= 10 or self.block.j <= 0 :
#                 print(4/0)
#             self.setBlock2DWtithBlock()
        except:
            flag_col_bound = True
            print("에러!")
        
        flag_collision = self.isCollision()
        
        if flag_collision or flag_col_bound : 
            print(flag_collision)
            self.block.status = pre_status
            self.block.i = pre_i
            self.block.j = pre_j
            self.setBlock2DWtithBlock()
            self.moveStackBlock2Scrin()
            
            if flag_down :
                self.moveBlock2Stack()
                notFullStack = self.getNotFullStack()
                cnt10 = 20 - len(notFullStack)
                print(self.lblCount.text())
                cnt = int(self.lblCount.text())
                cnt_setting = cnt - cnt10
                self.lblCount.setText(str(cnt_setting))
                if cnt_setting <=0:
                    QMessageBox.about(self, '결과는?', 'you win')
                    self.flagIng = False
                    return
                
                if  self.stack2D[5][0] > 0 or self.stack2D[5][1] > 0 or self.stack2D[5][2] > 0 or self.stack2D[5][3] > 0 or self.stack2D[5][4] > 0 or self.stack2D[5][5] > 0 or self.stack2D[5][6] > 0 or self.stack2D[5][7] > 0 or self.stack2D[5][8] > 0 or self.stack2D[5][9] > 0:
                    QMessageBox.about(self, '결과는?', 'you loose')
                    self.flagIng = False
                for i in range(cnt10):
                    notFullStack.insert(0, "0,0,0,0,0,0,0,0,0,0")
                    
                i=0
                for str_line in notFullStack: #줄지워주는 메서드
                    data = str_line.split(",")
                    self.stack2D[i][0] = int(data[0])
                    self.stack2D[i][1] = int(data[1])
                    self.stack2D[i][2] = int(data[2])
                    self.stack2D[i][3] = int(data[3])
                    self.stack2D[i][4] = int(data[4])
                    self.stack2D[i][5] = int(data[5])
                    self.stack2D[i][6] = int(data[6])
                    self.stack2D[i][7] = int(data[7])
                    self.stack2D[i][8] = int(data[8])
                    self.stack2D[i][9] = int(data[9])
                    i+=1
                         
                self.block.myinit();
                self.setBlock2DWtithBlock()
                self.moveStackBlock2Scrin()
            
        self.moveStackBlock2Scrin()
        self.myrender()    
        print(self.block)
    
    def getNotFullStack(self):
        stack_temp = []
        for temp in(self.stack2D):
            if temp[0] > 0 and temp[1] > 0 and temp[2] > 0 and temp[3] > 0 and temp[4] > 0 and temp[5] > 0 and temp[6] > 0 and temp[7] > 0 and temp[8] > 0 and temp[9] > 0 :
                pass
            else:
                str_line = str(temp[0]) + "," + str(temp[1]) + "," + str(temp[2]) + "," + str(temp[3]) + "," + str(temp[4]) + "," + str(temp[5]) + "," + str(temp[6]) + "," + str(temp[7]) + "," + str(temp[8]) + "," + str(temp[9])
                stack_temp.append(str_line)
        return stack_temp        
    
    def isCollision(self):
        for i in range(len(self.scrin2D)):
            for j in range(len(self.scrin2D[i])):
                if self.stack2D[i][j] > 0 and self.block2D[i][j] > 0 :
                    return True
        return False
                    
    def moveBlock2Stack(self):
        for i in range(len(self.block2D)):
            for j in range(len(self.block2D[i])):
                if self.block2D[i][j] > 0 :
                    self.stack2D[i][j] = self.block2D[i][j] +10
                            
    # 2D에 0의 값을 초기화
    def initBlock2Dstack2Dscrin2D(self):
        for i in range(20):
            self.block2D.append([0,0,0,0,0, 0,0,0,0,0])
            self.stack2D.append([0,0,0,0,0, 0,0,0,0,0])
            self.scrin2D.append([0,0,0,0,0, 0,0,0,0,0])
    
    # console에 찍기위함
    def print2D(self,arr):
        print("----------------------------------")
        for line in arr:
            print(line)
            
    # lbl에 숫자에맞는 색깔 입히기
    def myrender(self):
        for i in range(len(self.scrin2D)):
            for j in range(len(self.scrin2D[i])):
                self.lbl2D[i][j].setPixmap(QPixmap("png.png"))
                if self.scrin2D[i][j] == 0:
                    self.lbl2D[i][j].setPixmap(QPixmap("png.png"))
                if self.scrin2D[i][j] == 1:
                    self.lbl2D[i][j].setStyleSheet("background-color: blue")
                if self.scrin2D[i][j] == 2:
                    self.lbl2D[i][j].setStyleSheet("background-color: brown")
                if self.scrin2D[i][j] == 3:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
                if self.scrin2D[i][j] == 4:
                    self.lbl2D[i][j].setStyleSheet("background-color: yellow")
                if self.scrin2D[i][j] == 5:
                    self.lbl2D[i][j].setStyleSheet("background-color: red")
                if self.scrin2D[i][j] == 6:
                    self.lbl2D[i][j].setStyleSheet("background-color: silver")
                if self.scrin2D[i][j] == 7:
                    self.lbl2D[i][j].setStyleSheet("background-color: red")
                    
                if self.scrin2D[i][j] == 11:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
                if self.scrin2D[i][j] == 12:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
                if self.scrin2D[i][j] == 13:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
                if self.scrin2D[i][j] == 14:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
                if self.scrin2D[i][j] == 15:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
                if self.scrin2D[i][j] == 16:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
                if self.scrin2D[i][j] == 17:
                    self.lbl2D[i][j].setStyleSheet("background-color: gray")
            

if __name__ == "__main__" :
    app = QApplication(sys.argv) 

    myWindow = WindowClass() 

    myWindow.show()

    app.exec_()
