## parser.py
import requests
from bs4 import BeautifulSoup
from selenium import webdriver as wd
driver = wd.Chrome(executable_path="chromedriver.exe")
url = "https://www.naver.com"

driver.get(url)
