package day04;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrmOmok02 extends JFrame {

	private JPanel contentPane; // 오목판
	private JLabel[][] arr2d = new JLabel[10][10]; // 2차원 jlabel
	private int[][] int2d = new int[10][10]; // 계산상의 이점으로 여기에 위치
	private ImageIcon iie = new ImageIcon(FrmOmok02.class.getResource("/day04/0.jpg"));
	private ImageIcon iiw = new ImageIcon(FrmOmok02.class.getResource("/day04/1.jpg"));
	private ImageIcon iib = new ImageIcon(FrmOmok02.class.getResource("/day04/2.jpg"));
	private JLabel lbl;
	private boolean flag = false; // 흑돌 백돌 번갈아
	private boolean flagIng = true; // 게임의 끝난여부
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmOmok02 frame = new FrmOmok02();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showInt2d() { // 텍스트의 위치를 찍어줌
		for(int i = 0; i < int2d.length ; i ++) { // 2차원 배열  행
			for (int j = 0; j < int2d[i].length; j++) { // 2차원 배열 열
				System.out.print(int2d[i][j]);
			}
			System.out.println("");
		}
	}
	
	public void myRender() { // 찍어진 텍스트에 따라 아이콘을 찍어줌
		for(int i = 0 ; i < int2d.length; i++) {
			for(int j =0; j < int2d[i].length; j++) {
				if(int2d[i][j] == 0) {
					arr2d[i][j].setIcon(iie);
				}else if(int2d[i][j] == 2) {
					arr2d[i][j].setIcon(iib);
				}else if(int2d[i][j] == 1) {
					arr2d[i][j].setIcon(iiw);
				}
			}
		}
	}
	
	public FrmOmok02() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000); // 창 크기
		contentPane = new JPanel(); // 창 객체 생성
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (int i = 0; i < arr2d.length; i++) { // 2차원 배열 행

			for (int j = 0; j < arr2d[i].length; j++) { // 2차원 배열 열

				JLabel lbl = new JLabel("New label"); // 바둑판 일부분 객체
				lbl.setIcon(iie); // 아이콘 대입
				lbl.setText(i + "," + j); // 좌표 텍스트로 입력받음
				lbl.setBounds((j * 75), (i * 75), 75, 75); // 아이콘 크기 설정
				lbl.addMouseListener(new MouseAdapter() { // 레이블 클릭

					public void mouseClicked(MouseEvent e) { // 클릭한 레이블(매개변수 e)
						myClick(e);

					}

				});
				contentPane.add(lbl);
				arr2d[i][j] = lbl;
				// 좌표의 (x,y) +- 1 차이나는 좌표에 값이 찍힌다면 카운트 추가
			}
		}

		showInt2d();
		myRender();
	}
	private void myClick(MouseEvent e) {
		if(!flagIng) {
			return;
		}
		JLabel temp = (JLabel) e.getComponent(); // 매개변수 e의 값을 Jlabel객체로 받음
		
		String a = temp.getText(); // x,y 를 가져옴
		String[] b = a.split(","); // ,로 분리하여 추출
		
		int ii = Integer.parseInt(b[0]); // int로 변환해서 ii로 넣는다.
		int jj = Integer.parseInt(b[1]); // int로 변환해서 jj로 넣는다.
		
		if (int2d[ii][jj] == 0) { // 클릭하는 값이 0일 때만 동작
			
			int cnt_stone = 1;
			if (flag) { // 흰돌
				int2d[ii][jj] = 1; // flag에 false true 면
				cnt_stone = 1;
			} else {
				int2d[ii][jj] = 2;
				cnt_stone = 2;
			}
			myRender();
			int up_cnt = getUP(ii,jj,cnt_stone);
			int dw_cnt = getDown(ii, jj, cnt_stone);
			int le_cnt = getLeft(ii, jj, cnt_stone);
			int ri_cnt = getRight(ii, jj, cnt_stone);
			int uple_cnt = getLeftUp(ii, jj, cnt_stone);
			int upri_cnt = getRightUp(ii, jj, cnt_stone);
			int dwle_cnt = getLeftDown(ii, jj, cnt_stone);
			int dwri_cnt = getRightDown(ii, jj, cnt_stone);
			
			int[] cnt5p = new int[4];
			
			cnt5p[0] = up_cnt+ dw_cnt+1;
			cnt5p[1] = le_cnt+ ri_cnt+1;
			cnt5p[2] = uple_cnt+ dwri_cnt+1;
			cnt5p[3] = dwle_cnt+ upri_cnt+1;
			
			
			System.out.println("위로 돌의 개수 : " +up_cnt);
			System.out.println("아래로 돌의 개수 : " +dw_cnt);
			System.out.println("왼쪽으로 돌의 개수 : " +le_cnt);
			System.out.println("오른쪽으로 돌의 개수 : " +ri_cnt);
			
			System.out.println("왼쪽 위로 돌의 개수 : " +uple_cnt);
			System.out.println("왼쪽 아래로 돌의 개수 : " +dwle_cnt);
			System.out.println("오른쪽 위로 돌의 개수 : " + upri_cnt);
			System.out.println("오른쪽 아래로 돌의 개수 : " + dwri_cnt);
			
			flag = !flag;
			
			for(int i = 0; i <cnt5p.length; i++ ) {
				if(cnt5p[i] == 5 && cnt_stone == 1) {
					JOptionPane.showMessageDialog(null, "백돌이 이겼습니다.");
					flagIng =  false;
				}else if(cnt5p[i] == 5 && cnt_stone == 2) { // 경기 종료 후 돌 놓기 금지
					JOptionPane.showMessageDialog(null, "흑돌이 이겼습니다.");
					flagIng =  false;
				}
			}
			
		}
	}
	
	// 돌을 놓으며 주변의 개수 체크

	private int getUP(int ii, int jj, int cnt_stone) { // 위로갈 때 체크
		int cnt = 0; // 내 위로 몇개 있는가?
		try {
			while(true) { 
				ii--;
				if(int2d[ii][jj] == cnt_stone) { // 위쪽의 돌이 cnt_stone이랑 같으면
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
	
	private int getDown(int ii, int jj, int cnt_stone) { // 아래로 갈 때 체크
		int cnt = 0;
		try {
			while(true) {
				ii++;
				if(int2d[ii][jj] == cnt_stone) {
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
	
	private int getRight(int ii, int jj, int cnt_stone) { // 우측으로 갈 때 체크
		int cnt = 0;
		try {
			while(true) {
				jj++;
				if(int2d[ii][jj] == cnt_stone) {
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
	
	private int getLeft(int ii, int jj, int cnt_stone) { // 좌측으로 갈 때 체크
		int cnt = 0;
		try {
			while(true) {
				jj--;
				if(int2d[ii][jj] == cnt_stone) {
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
	
	private int getLeftUp(int ii, int jj, int cnt_stone) { // 좌측 위 대각선으로 갈 때 체크
		int cnt = 0;
		try {
			while(true) {
				ii--;
				jj--;
				if(int2d[ii][jj] == cnt_stone) {
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
	
	private int getLeftDown(int ii, int jj, int cnt_stone) { // 좌측 아래 대각선으로 갈 때 체크
		int cnt = 0;
		try {
			while(true) {
				ii++;
				jj--;
				if(int2d[ii][jj] == cnt_stone) {
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
	
	private int getRightUp(int ii, int jj, int cnt_stone) { // 우측 위 대각선으로 갈 때 체크
		int cnt = 0;
		try {
			while(true) {
				ii--;
				jj++;
				if(int2d[ii][jj] == cnt_stone) {
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
	
	private int getRightDown(int ii, int jj, int cnt_stone) { // 우측 아래 대각선으로 갈 때 체크
		int cnt = 0;
		try {
			while(true) {
				ii++;
				jj++;
				if(int2d[ii][jj] == cnt_stone) {
					++cnt;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("line Out");
		}
		return cnt;
	}
}
