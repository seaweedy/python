import threading
 
def printChar(start, stop):
    for i in range(start,stop):
        print(chr(i),end='')
        if i%5000 == 0 :
            print()

def printNumber(start, stop):
    for i in range(start,stop):
        print(i, end ='')
        if i%1000 == 0 :
            print()
    
if __name__ == '__main__':
    t = threading.Thread(target=printChar, args=(1, 50000))
    s = threading.Thread(target=printNumber, args=(1, 100000))
    t.start()
    s.start()
    
