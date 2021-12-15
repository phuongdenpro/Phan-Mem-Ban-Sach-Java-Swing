package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class TrangChaoMung_GUI extends JFrame {

	private JPanel contentPane;
	public JButton btnDangXuat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChaoMung_GUI frame = new TrangChaoMung_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public TrangChaoMung_GUI() throws SQLException {
		setTitle("Trang quản lý");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		contentPane = 
//				new JPanel();
				TrangChu_GUI.panelBackgroundImage("/images/bg2.jpg");
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnThoat = new JButton("Thoát chương trình", new ImageIcon("data/images/shut-down.png"));
		
		btnThoat.setBounds((int) getSize().getWidth() - 250, 594, 200, 40);
//		btnNewButton.setIcon(new ImageIcon("/data/images/shut-down.png"));
//		btnNewButton.setForeground(new Color(0, 191, 255));
		btnThoat.setBackground(Color.WHITE);
		contentPane.add(btnThoat);
		
		btnDangXuat = new JButton("Đăng xuất", new ImageIcon("data/images/logout.png"));
		btnDangXuat.setBackground(Color.WHITE);
		btnDangXuat.setBounds(1050, 543, 200, 40);
		contentPane.add(btnDangXuat);
		
		btnThoat.addActionListener((e) -> {
			System.exit(0);
		});
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
}
