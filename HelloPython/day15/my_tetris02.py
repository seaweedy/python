import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("tetris.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)
        self.resize(800,800)
        self.block2D = []
        self.stack2D = []
        self.scrin2D = []
        self.lbl2D = []
        
        self.initBlock2Dstack2Dscrin2D()
        self.scrin2D[0][0] = 11
        self.scrin2D[0][1] = 8
        self.scrin2D[1][1] = 7
        self.scrin2D[2][1] = 6
        self.print2D(self.scrin2D)

        
        for i in range(len(self.scrin2D)):
            arr=[]
            for j in range(len(self.scrin2D[i])):
                lbl = QLabel('',self)
                lbl.setGeometry(30*j, 30*i, 28, 28)
                lbl.setStyleSheet("background-color: lightgray")
                arr.append(lbl)
            self.lbl2D.append(arr)
                
        self.myrender()
                    
    def initBlock2Dstack2Dscrin2D(self):
        for i in range(20):
            self.block2D.append([0,0,0,0,0, 0,0,0,0,0])
            self.stack2D.append([0,0,0,0,0, 0,0,0,0,0])
            self.scrin2D.append([0,0,0,0,0, 0,0,0,0,0])
    
    def print2D(self,arr):
        print("----------------------------------")
        for line in arr:
            print(line)
            
    def myrender(self):
        for i in range(len(self.scrin2D)):
            for j in range(len(self.scrin2D[i])):
                if self.scrin2D[i][j] == 0:
                    self.lbl2D[i][j].setStyleSheet("background-color: white")
                if self.scrin2D[i][j] == 1:
                    self.lbl2D[i][j].setStyleSheet("background-color: #00ff00")
                if self.scrin2D[i][j] == 2:
                    self.lbl2D[i][j].setStyleSheet("background-color: #00fd00")
                if self.scrin2D[i][j] == 3:
                    self.lbl2D[i][j].setStyleSheet("background-color: #00fe00")
                if self.scrin2D[i][j] == 4:
                    self.lbl2D[i][j].setStyleSheet("background-color: #00fc00")
                if self.scrin2D[i][j] == 5:
                    self.lbl2D[i][j].setStyleSheet("background-color: #00fb00")
                if self.scrin2D[i][j] == 6:
                    self.lbl2D[i][j].setStyleSheet("background-color: #000700")
                if self.scrin2D[i][j] == 7:
                    self.lbl2D[i][j].setStyleSheet("background-color: #000d00")
                    
                if self.scrin2D[i][j] == 11:
                    self.lbl2D[i][j].setStyleSheet("background-color: #090900")
                if self.scrin2D[i][j] == 12:
                    self.lbl2D[i][j].setStyleSheet("background-color: #ff0000")
                if self.scrin2D[i][j] == 13:
                    self.lbl2D[i][j].setStyleSheet("background-color: #fd0000")
                if self.scrin2D[i][j] == 14:
                    self.lbl2D[i][j].setStyleSheet("background-color: #fe0000")
                if self.scrin2D[i][j] == 15:
                    self.lbl2D[i][j].setStyleSheet("background-color: #fc0000")
                if self.scrin2D[i][j] == 16:
                    self.lbl2D[i][j].setStyleSheet("background-color: #fb0000")
                if self.scrin2D[i][j] == 17:
                    self.lbl2D[i][j].setStyleSheet("background-color: #fa0000")
            

if __name__ == "__main__" :
    app = QApplication(sys.argv) 

    myWindow = WindowClass() 

    myWindow.show()

    app.exec_()