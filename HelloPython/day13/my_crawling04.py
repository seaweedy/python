import time

from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.wait import WebDriverWait

options = Options()
options.headless = False
browser = webdriver.Chrome(executable_path="chromedriver.exe", options=options)
browser.get("http://localhost/HelloWeb/Hello.jsp")

time.sleep(3)
objs = browser.find_element_by_css_selector(".rank_top1000_list").find_elements_by_tag_name("li")
for obj in objs:
    print(obj.text.split("\n"))
