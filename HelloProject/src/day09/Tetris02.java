package day09;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tetris02 extends JFrame {

	private JPanel contentPane;
	public int[][] block2D = new int[20][10]; // 블록들을 2D로 표현
	public int[][] stack2D = new int[20][10]; // 쌓여있는 블록들을 2D로 표현
	public int[][] scrin2D = new int[20][10]; // 스크린의 블록을 2D로 표현
	public JLabel[][] lbl2D = new JLabel[20][10]; // 2차원 jlabel
	public Block block = new Block(6);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tetris02 frame = new Tetris02();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tetris02() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				myPress(e);
			}

		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 800); // setSize
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setBlock2DWtithBlock();

		stack2D[19][0] = 12;
		stack2D[19][1] = 12;
		stack2D[19][2] = 12;
		stack2D[19][3] = 12;

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				JLabel lbl = new JLabel("");
				lbl.setBackground(Color.WHITE);
				lbl.setBounds(25 * j, 25 * i, 24, 24);
				lbl.setOpaque(true);
				contentPane.add(lbl);
				lbl2D[i][j] = lbl;
			}
		}
		System.out.println(block);
		print2D(block2D);
		print2D(stack2D);
	}

	public void myRender() {
		for (int i = 0; i < scrin2D.length; i++) {
			for (int j = 0; j < scrin2D[i].length; j++) {
				if (scrin2D[i][j] == 0) {
					lbl2D[i][j].setBackground(Color.WHITE);
					// 빈곳 표시
				} else if (scrin2D[i][j] == 1) {
					lbl2D[i][j].setBackground(new Color(0, 0, 255 - (10 * 1)));
					// 내려오는 블럭
				} else if (scrin2D[i][j] == 2) {
					lbl2D[i][j].setBackground(new Color(0, 0, 255 - (10 * 2)));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 3) {
					lbl2D[i][j].setBackground(new Color(0, 0, 255 - (10 * 3)));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 4) {
					lbl2D[i][j].setBackground(new Color(0, 0, 255 - (10 * 4)));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 5) {
					lbl2D[i][j].setBackground(new Color(0, 0, 255 - (10 * 5)));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 6) {
					lbl2D[i][j].setBackground(new Color(0, 0, 255 - (10 * 6)));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 7) {
					lbl2D[i][j].setBackground(new Color(0, 0, 255 - (10 * 7)));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 11) {
					lbl2D[i][j].setBackground(new Color(0, 255 - (10 * 1), 0));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 12) {
					lbl2D[i][j].setBackground(new Color(0, 255 - (10 * 2), 0));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 13) {
					lbl2D[i][j].setBackground(new Color(0, 255 - (10 * 3), 0));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 14) {
					lbl2D[i][j].setBackground(new Color(0, 255 - (10 * 4), 0));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 15) {
					lbl2D[i][j].setBackground(new Color(0, 255 - (10 * 5), 0));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 16) {
					lbl2D[i][j].setBackground(new Color(0, 255 - (10 * 6), 0));
					// 죽은 블럭
				} else if (scrin2D[i][j] == 17) {
					lbl2D[i][j].setBackground(new Color(0, 255 - (10 * 7), 0));
					// 죽은 블럭
				}
			}
		}

	}

	private void myPress(KeyEvent e) { // 키를 누를 경우
		System.out.print(e.getKeyChar());
		if (e.getKeyCode() == 37) {
			block.j--;
			System.out.println("← 누름");
		}
		if (e.getKeyCode() == 39) {
			block.j++;
			System.out.println("→ 누름");
		}
		if (e.getKeyCode() == 38) { // 회전으로 status를 변경
			changeBlockStatus(block.kind);
			System.out.println("↑ 누름");
		}
		if (e.getKeyCode() == 40) {
			block.i++;
			System.out.println("↓ 누름");
		}
		setBlock2DWtithBlock(); // block의 2D를 set한다.

		moveStackBlock2Scrin(); // stack2D의 값과 block2D의 값을 합한 수를 scrin2D에 대입

		myRender(); // 값에 따라 lbl 변화

		print2D(scrin2D); // 변수의 값을 console로 출력

//		block2DCrashStack2D(); // stack2D와 block2D가 부딪힐 때 쌓이는 메서드

	}

	public void block2DCrashStack2D() {
		for (int i = 0; i < scrin2D.length; i++) {
			for (int j = 0; j < scrin2D[i].length; j++) {
				if (stack2D[i][j] > 0 && block2D[i][j] > 0) { // 충돌
					scrin2D[i + 2][j] = stack2D[i][j] + block2D[i][j];
				}
			}
		}
	}

	public void changeBlockStatus(int blockKind) {
		if (block.kind == 1) {
			block.status = block.status;
		}
		if (block.kind == 2 || block.kind == 3 || block.kind == 4) {
			if (block.status == 1) {
				block.status = 2;
			} else {
				block.status = 1;
			}
		}
		if (block.kind == 5 || block.kind == 6 || block.kind == 7) {
			if (block.status == 1) {
				block.status = 2;
			} else if (block.status == 2) {
				block.status = 3;
			} else if (block.status == 3) {
				block.status = 4;
			} else {
				block.status = 1;
			}
		}
	}

	public void moveStackBlock2Scrin() { // stack2D의 값과 block2D의 값을 합한 수를 scrin2D에 대입
		for (int i = 0; i < scrin2D.length; i++) {
			for (int j = 0; j < scrin2D[i].length; j++) {
				scrin2D[i][j] = stack2D[i][j] + block2D[i][j];
			}
		}
	}

	private void setBlock2DWtithBlock() { // block 클래스의 값에 따라 block2D에 수를 대입해주는 메서드
		for (int i = 0; i < block2D.length; i++) { // 기존의 값을 0으로 바꿔 위치 이동
			for (int j = 0; j < block2D[i].length; j++) {
				block2D[i][j] = 0;
			}
		}
		if (block.kind == 1) {
			if (block.status == 1) {
				block2D[block.i + 1][block.j + 1] = block.kind;
				block2D[block.i][block.j] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
			}
		}

		if (block.kind == 2) {
			if (block.status == 1) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i + 2][block.j] = block.kind;
			} else if (block.status == 2) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i][block.j - 2] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
			}
		}

		if (block.kind == 3) {
			if (block.status == 1) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i + 1][block.j + 1] = block.kind;
			} else if (block.status == 2) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i + 1][block.j - 1] = block.kind;
			}
		}

		if (block.kind == 4) {
			if (block.status == 1) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i + 1][block.j - 1] = block.kind;
			} else if (block.status == 2) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j - 1] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
			}
		}

		if (block.kind == 5) {
			if (block.status == 1) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
			} else if (block.status == 2) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
			} else if (block.status == 3) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
			} else if (block.status == 4) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
			}
		}

		if (block.kind == 6) {
			if (block.status == 1) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i + 1][block.j + 1] = block.kind;
			} else if (block.status == 2) {
				block2D[block.i][block.j] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
				block2D[block.i + 1][block.j - 1] = block.kind;
			} else if (block.status == 3) {
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i - 1][block.j - 1] = block.kind;
			} else if (block.status == 4) {
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j + 1] = block.kind;
			}
		}
		if (block.kind == 7) {
			if (block.status == 1) {
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i][block.j] = block.kind;
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i + 1][block.j - 1] = block.kind;
			} else if (block.status == 2) {
				block2D[block.i][block.j + 1] = block.kind;
				block2D[block.i][block.j] = block.kind;
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i - 1][block.j - 1] = block.kind;
			} else if (block.status == 3) {
				block2D[block.i + 1][block.j] = block.kind;
				block2D[block.i][block.j] = block.kind;
				block2D[block.i - 1][block.j] = block.kind;
				block2D[block.i - 1][block.j + 1] = block.kind;
			} else if (block.status == 4) {
				block2D[block.i][block.j - 1] = block.kind;
				block2D[block.i][block.j + 1] = block.kind;
				block2D[block.i][block.j] = block.kind;
				block2D[block.i + 1][block.j + 1] = block.kind;
			}
		}
	}

	private void printStack2D() { // stack2D를 숫자료 표현 해주는 메서드
		System.out.println("-----------------------------------------------------------------stack2D");
		for (int i = 0; i < stack2D.length; i++) {
			for (int j = 0; j < stack2D[i].length; j++) {
				System.out.print(stack2D[i][j]);
			}
			System.out.println();
		}

	}

	public void print2D(int[][] lbl2D) { // block2D를 숫자로 표현 해주는 메서드
		System.out.println("-----------------------------------------------------------------block2D");
		for (int i = 0; i < lbl2D.length; i++) {
			for (int j = 0; j < lbl2D[i].length; j++) {
				System.out.print(lbl2D[i][j]);
			}
			System.out.println();
		}
	}
}
