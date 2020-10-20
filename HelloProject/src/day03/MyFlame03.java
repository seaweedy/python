package day03;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFlame03 extends JFrame {

	private JPanel contentPane;
	private JTextField input1;
	private JTextField input2;
	private JTextField output;
	private JButton clear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFlame03 frame = new MyFlame03();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFlame03() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("+");
		lblNewLabel.setBounds(93, 62, 11, 15);
		contentPane.add(lblNewLabel);
		
		input1 = new JTextField();
		input1.setBounds(10, 59, 71, 21);
		contentPane.add(input1);
		input1.setColumns(10);
		
		input2 = new JTextField();
		input2.setBounds(116, 59, 83, 21);
		contentPane.add(input2);
		input2.setColumns(10);
		
		JButton btnNewButton = new JButton("=");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = Integer.parseInt(input1.getText());
				int b = Integer.parseInt(input2.getText());
				
				output.setText((a+b)+"");
			}
		});
		btnNewButton.setBounds(211, 58, 43, 23);
		contentPane.add(btnNewButton);
		
		output = new JTextField();
		output.setBounds(266, 59, 116, 21);
		contentPane.add(output);
		output.setColumns(10);
		
		clear = new JButton("지우기");
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				input1.setText("");
				input2.setText("");
				output.setText("");
			}
		});
		clear.setBounds(266, 100, 97, 23);
		contentPane.add(clear);
	}
}
