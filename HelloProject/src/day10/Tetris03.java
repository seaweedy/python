package day10;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tetris03 extends JFrame {

	private JPanel contentPane;

	public int[][] block2D = new int[20][10]; // 블록들을 2D로 표현
	public int[][] stack2D = new int[20][10]; // 쌓여있는 블록들을 2D로 표현
	public int[][] scrin2D = new int[20][10]; // 스크린의 블록을 2D로 표현
	public JLabel[][] lbl2D = new JLabel[20][10]; // 2차원 jlabel
	public day10.Block block = new day10.Block();
	public JLabel lblRow = new JLabel("10");
	public boolean flagIng = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tetris03 frame = new Tetris03();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tetris03() {
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
		
		JLabel lblDisp = new JLabel("지워야할 행");
		lblDisp.setBounds(359, 99, 101, 15);
		contentPane.add(lblDisp);
		
		
		lblRow.setBounds(369, 124, 57, 15);
		contentPane.add(lblRow);

		setBlock2DWtithBlock();

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
		print2D(block2D);
		print2D(stack2D);
		
		new Thread(){
			public void run(){
				while(true) {
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					realPress(40);
				}
			};
		}.start();
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
	
	public void myPress(KeyEvent e) { 
		realPress(e.getKeyCode());
		
	}
	
	private void realPress(int keycode) { // 키를 누를 경우
		if(!flagIng) {
			return;
		}
		boolean flag_col_bound = false; 
		boolean flag_down = false;
		// 충돌 시 복구 하기 위한 기존의 데이터
		int pre_status = block.status;
		int pre_i = block.i;
		int pre_j = block.j;
		
		if (keycode == 37) {
			block.j--;
		}
		if (keycode == 39) {
			block.j++;
		}
		if (keycode == 38) { // 회전으로 status를 변경
			changeBlockStatus(block.kind);
		}
		if (keycode == 40) {
			block.i++;
			flag_down = true;
		}
		if (keycode == 32) {
			block.i++;
		}
		
		System.out.println(block);
		try {
			setBlock2DWtithBlock(); // block의 2D를 set한다.
		} catch (Exception e2) {
			flag_col_bound = true;
		}
		moveStackBlock2Scrin(); // stack2D의 값과 block2D의 값을 합한 수를 scrin2D에 대입
		
		boolean flag_collision = isCollision(); // stack2D와 block2D가 부딪힐 때 쌓이는 메서드
		
		if(flag_collision||flag_col_bound) { // 충돌 시 
			block.status = pre_status;
			block.i = pre_i;
			block.j = pre_j;
			setBlock2DWtithBlock(); // block의 2D를 set한다.
			moveStackBlock2Scrin(); // stack2D의 값과 block2D의 값을 합한 수를 scrin2D에 대입
			if(flag_down) {
				moveBlock2Stack(); // 쌓여서 부딪힌 블럭을 스택으로 옮기는 메서드
				
				ArrayList<String> notFullStack = getNotFullStack();
				int cnt10 = stack2D.length - notFullStack.size(); // 최대 개수는 4개 
				System.out.println("cnt10 :" + cnt10);

				String zeroLine = "0,0,0,0,0,0,0,0,0,0";
				
				for (int i = 0; i < cnt10; i++) { // 지워진 두 줄 만큼 notFullStack에 두 줄을 추가하는 
					notFullStack.add(0, zeroLine);
					lblRow.setText((Integer.parseInt(lblRow.getText())-1)+"");
					System.out.println(lblRow.getText());
					if(Integer.parseInt(lblRow.getText())<=0) {
						JOptionPane.showMessageDialog(null, "경기 종료");
						flagIng = false;
						return;
					}
				}
				if(
					stack2D[5][0] > 0 ||
					stack2D[5][1] > 0 ||
					stack2D[5][2] > 0 ||
					stack2D[5][3] > 0 ||
					stack2D[5][4] > 0 ||
					
					stack2D[5][5] > 0 ||
					stack2D[5][6] > 0 ||
					stack2D[5][7] > 0 ||
					stack2D[5][8] > 0 ||
					stack2D[5][9] > 0) {
					
					JOptionPane.showMessageDialog(null, "실패");
					flagIng = false;
					return;
				}
				
				for (int i = 0; i < stack2D.length; i++) { // 줄이 지워진 notFullStack의 값들을  stack2D로 옮김 
					for (int j = 0; j < stack2D[i].length; j++) {
						String line = notFullStack.get(i);
						String[] data = line.split(",");
						stack2D[i][j] = Integer.parseInt(data[j]);
					}
				}
				
				block.init();
				setBlock2DWtithBlock(); // block의 2D를 set한다.
				moveStackBlock2Scrin(); // 말을 위로 다시 셋팅
			}
		}
		System.out.println("flag_collision : " + flag_collision);
		System.out.println("flag_col_bound : " + flag_col_bound);
		myRender(); // 값에 따라 lbl 변화
		print2D(scrin2D); // 변수의 값을 console로 출력
//		deleteStack();
	}
	
//	public int findStackI() {
//		for (int i = 0; i < stack2D.length; i++) {
//			for (int j = 0; j < stack2D[i].length; j++) {
//				if(block2D[i][j])
//			}
//		}
//	}
	
	public ArrayList<String> getNotFullStack() { // 10개가 찼는지 확인
		ArrayList<String> stack_temp = new ArrayList<>();
		for (int i = 0; i < stack2D.length; i++) {
			int[] temp = stack2D[i];
			if ( // 10개를 채운 줄에 대하여
				temp[0] > 0 &&
				temp[1] > 0 &&
				temp[2] > 0 &&
				temp[3] > 0 &&
				temp[4] > 0 &&
				temp[5] > 0 &&
				temp[6] > 0 &&
				temp[7] > 0 &&
				temp[8] > 0 &&
				temp[9] > 0 
					) {
			}else { // 10개를 못 채운 줄에 대하여
				String str_line = temp[0] + "," +temp[1] + "," +temp[2] + "," +temp[3] + "," +temp[4] + "," +temp[5] + "," +temp[6] + "," +temp[7] + "," +temp[8] + "," +temp[9];
				stack_temp.add(str_line);
			}
		}
		return stack_temp;
	}
	
	public void moveBlock2Stack() {
		for (int i = 0; i < block2D.length; i++) {
			for (int j = 0; j < block2D[i].length; j++) {
				if(block2D[i][j] > 0) {
					stack2D[i][j] = block2D[i][j]+10;
				}
			}
		}
		
	}

	public boolean isCollision() {
		for (int i = 0; i < scrin2D.length; i++) {
			for (int j = 0; j < scrin2D[i].length; j++) {
				if (stack2D[i][j] > 0 && block2D[i][j] > 0) { // 충돌
					return true;
				}
			}
		}
		return false;
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
