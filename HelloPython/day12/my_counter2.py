import sys

from PyQt5 import uic
from PyQt5.QtWidgets import *
import threading
import time


#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("my_counter2.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)
        
        #버튼에 기능을 연결하는 코드
        self.pushButton.clicked.connect(self.on_click)
        
    def on_increase(self):
        for i in range(10):
            a = int(self.label.text())
            a += 1 # int로 바꿔 +1
            self.label.setText(str(a)) # 다시 String
            time.sleep(1)
        #버튼1이 눌리면 작동할 함수
    
    def on_click(self):
        t1 = threading.Thread(target=self.on_increase)
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
    