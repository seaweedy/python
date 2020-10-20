package day11;

public class ThreadTest02 {
	public static void main(String[] args) {
		printNumber();
		printChar();
	}
	
	public static void printNumber() {
		for (int i = 0; i < 100000; i++) {
			System.out.print(i);
			if(i%1000==0) {
				System.out.println();
			}
		}
	}
	public static void printChar() {
		for (int i = 0; i < 100000; i++) {
			System.out.print((char)i);
			if(i%1000==0) {
				System.out.println();
			}
		}
	}
}
