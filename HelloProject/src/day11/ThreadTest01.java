package day11;

import java.awt.event.KeyEvent;

public class ThreadTest01 {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("hello");
			}
		}.start();
	}
}
