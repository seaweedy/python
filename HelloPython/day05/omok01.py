import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic, QtCore
from dask.array.image import imread
from PyQt5.Qt import QPixmap, QIcon
from dask.dataframe.tests.test_rolling import idx

#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("omok01.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)
        self.resize(750,750)
        self.ie = QIcon('0.jpg')
        self.iw = QIcon('1.jpg')
        self.ib = QIcon('2.jpg')
        self.int2d = [
                      [0,0,0,0,0, 0,0,0,0,0],
                      [0,1,0,0,0, 0,0,0,0,0],
                      [0,0,2,0,0, 0,0,0,0,0],
                      [0,0,0,2,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0],
                      
                      [0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0],
                      [0,0,0,0,0, 0,0,0,0,0]
                      ]
        
        self.arr2d = [] # 큰배열
        
        for i in range(10):
            arr = [] # 한줄짜리 배열
            for j in range(10):
                pb = QPushButton('',self)
                pb.setGeometry(j*75, i*75, 75, 75)
                pb.setIconSize(QtCore.QSize(75,75))
                pb.setIcon(self.ie)
                pb.setWhatsThis("{},{}".format(i, j))
                pb.clicked.connect(self.myclick)
                arr.append(pb) # 한줄에 pb값을 넣는다.
            self.arr2d.append(arr) # 여러줄의 pb를 넣는다.
        self.mydraw() # 최초 실행시 arr2d에 맞는 이미지 출력
        
    def myclick(self): #클릭할 때 값 출력
        print(self.sender().whatsThis())
        self.myshow2d()
        self.mydraw()
        
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
