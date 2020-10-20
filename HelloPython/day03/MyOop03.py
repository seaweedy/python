class Human:
    def __init__(self): # 생성자
        self.name = "홍길동"
        print("constructor")
    def __del__(self):
        print("distructor")
            
if __name__ == '__main__':
    a = Human()
    b = Human()
    
        