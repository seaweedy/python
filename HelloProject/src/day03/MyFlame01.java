package day03;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFlame01 extends JFrame {

	private JPanel Contentpane;
	JLabel lbl;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFlame01 frame = new MyFlame01();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MyFlame01() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Contentpane = new JPanel();
		Contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Contentpane);
		Contentpane.setLayout(null); // absolute 레이아웃으로 변경
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 0, 251);
		Contentpane.add(label);
		
		lbl = new JLabel("Hello");
		lbl.setBounds(17, 36, 116, 33);
		Contentpane.add(lbl);
		
		JButton btnNewButton = new JButton("click");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbl.setText("Good Morning!");
			}
		});
		btnNewButton.setBounds(145, 41, 97, 23);
		Contentpane.add(btnNewButton);
	}
}
