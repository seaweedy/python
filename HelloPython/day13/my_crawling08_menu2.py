import time

import sys
from PyQt5 import uic
from PyQt5.QtWidgets import *
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.wait import WebDriverWait
from day03.HelloPy import form_class

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)
        self.pushButton.clicked.connect(self.button1Function)
        
        options = Options()
        options.headless = False
        self.browser = webdriver.Chrome(executable_path="chromedriver.exe", options=options)
#         browser.get("https://map.naver.com/v5/search/%EB%8C%80%ED%9D%A5%EB%8F%99%20%EB%A7%9B%EC%A7%91/place/16069633?c=14184594.9051795,4345509.2531727,17,0,0,0,dh")
        self.browser.get("http://localhost/HelloWeb/Hello.jsp")
        
    #버튼1이 눌리면 작동할 함수
    def button1Function(self):
        tag_names = self.browser.find_element_by_css_selector(".rank_top1000_list").find_elements_by_tag_name("li")
        for tag in tag_names:
            print(tag.text.split("\n"))

if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()
