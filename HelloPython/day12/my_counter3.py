import sys

from PyQt5 import uic, QtGui
from PyQt5.QtWidgets import *
import threading
import time
from sympy.solvers.ode import sysode_linear_2eq_order1


#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("RGB.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        self.index = 0
        super().__init__()
        self.setupUi(self)
        
        #버튼에 기능을 연결하는 코드
        self.pb.clicked.connect(self.on_click)
        
    def on_shift(self,s,e):
        for i in range(s,e):
            if self.index%3 == 1:
                self.lbl1.setPixmap(QtGui.QPixmap("FF0000.png"))
                self.lbl2.setPixmap(QtGui.QPixmap("00FF00.png"))
                self.lbl3.setPixmap(QtGui.QPixmap("0000FF.png"))
#                 self.lbl1.setStyleSheet("background-color: #FF0000")
#                 self.lbl2.setStyleSheet("background-color: #00FF00")
#                 self.lbl3.setStyleSheet("background-color: #0000FF")
            elif self.index % 3 == 2:
                self.lbl1.setPixmap(QtGui.QPixmap("0000FF.png"))
                self.lbl2.setPixmap(QtGui.QPixmap("FF0000.png"))
                self.lbl3.setPixmap(QtGui.QPixmap("00FF00.png"))
#                 self.lbl1.setStyleSheet("background-color: #0000FF")
#                 self.lbl2.setStyleSheet("background-color: #FF0000")
#                 self.lbl3.setStyleSheet("background-color: #00FF00")
            else:
                self.lbl1.setPixmap(QtGui.QPixmap("00FF00.png"))
                self.lbl2.setPixmap(QtGui.QPixmap("0000FF.png"))
                self.lbl3.setPixmap(QtGui.QPixmap("FF0000.png"))
#                 self.lbl1.setStyleSheet("background-color: #00FF00")
#                 self.lbl2.setStyleSheet("background-color: #0000FF")
#                 self.lbl3.setStyleSheet("background-color: #FF0000")
            time.sleep(1)
            self.index += 1
        
    def on_click(self):
        print('on_click')
        t1 = threading.Thread(target=self.on_shift, args =(1,10))
        t1.start()
    
if __name__ == "__main__" :
    #QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv) 
    #WindowClass의 인스턴스 생성
    myWindow = WindowClass() 
    #프로그램 화면을 보여주는 코드
    myWindow.show()
    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()
    