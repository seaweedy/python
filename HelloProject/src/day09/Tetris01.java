package day09;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tetris01 extends JFrame {

	private JPanel contentPane;
	JLabel lbl;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tetris01 frame = new Tetris01();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tetris01() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 500); // setSize
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl = new JLabel("");
		lbl.setBackground(Color.YELLOW);
		lbl.setBounds(0, 0, 25, 25);
		lbl.setOpaque(true);
		contentPane.add(lbl);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				int x = (int)lbl.getBounds().getX();
				int y = (int)lbl.getBounds().getY();
				int w = (int)lbl.getBounds().getWidth();
				int h = (int)lbl.getBounds().getHeight();

				System.out.print(e.getKeyChar());
				if (e.getKeyCode() == 37) {
					lbl.setBounds(x - 10, y, w, h);
					System.out.println("← 누름");
				}
				if (e.getKeyCode() == 39) {
					lbl.setBounds(x + 10, y, w, h);
					System.out.println("→ 누름");
				}
				if (e.getKeyCode() == 38) {
					lbl.setBounds(x, y - 10, w, h);
					System.out.println("↑ 누름");
				}
				if (e.getKeyCode() == 40) {
					lbl.setBounds(x, y + 10, w, h);
					System.out.println("↓ 누름");
				}

			}
		});
	}
}
