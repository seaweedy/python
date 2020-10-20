import sys
import time

from PyQt5 import uic
from PyQt5.QtWidgets import *
import cx_Oracle
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
#         self.browser.get("http://localhost/HelloWeb/Hello.jsp")
        self.browser.get("https://place.map.kakao.com/12468740")
        
        
    #버튼1이 눌리면 작동할 함수
    def button1Function(self):
        self.conn=cx_Oracle.connect("lmh/java@localhost:1521/xe")
        self.cursor = self.conn.cursor()
        try:
            title = self.browser.find_elements_by_class_name("tit_location")[1].text;
            objs = self.browser.find_element_by_class_name("list_menu").find_elements_by_tag_name('li')
            for obj in objs:
                menu_name = obj.find_element_by_class_name("loss_word").text
                menu_price = obj.find_element_by_class_name("price_menu").text
                
                print(title, menu_name, menu_price)
                sql = "insert into mymenu (title, menu_name, menu_price) values (:1,:2,:3)"
                data=(title, menu_name,menu_price)
                self.cursor.execute(sql,data)
        except:
            print("예외")
        self.cursor.close()
        self.conn.commit() # java는 auto commit 
        self.conn.close()
            
    def __del__(self):
        pass

if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()
