class Animal:
    def __init__(self):
        self.age = 0
    def getOlder(self):
        self.age +=1
        
class Human(Animal):
    def __init__(self):
        super().__init__()
        self.name = "이재용"
    def changeName(self,name):
        self.name = name
            
if __name__ == '__main__':
    
    hum = Human()
    print(hum.age)
    print(hum.name)
    hum.changeName("홍길동")
    hum.getOlder()
    print(hum.age)
    print(hum.name)
        
