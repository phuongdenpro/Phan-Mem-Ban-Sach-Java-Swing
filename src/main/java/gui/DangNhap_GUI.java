package gui;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import dao.TaiKhoanDAO;
import util.Placeholder;

import javax.swing.SwingConstants;

public class DangNhap_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	public JButton btnDangNhap;
	public JButton btnDangKy;
	private JLabel lblMsg;
	private JCheckBox chcHienThiMatKhau;
	public JCheckBox chkIsNhanVien;
	public JButton btnThoat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI frame = new DangNhap_GUI();
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
	public DangNhap_GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		
//		contentPane =  new JPanel(); 
		contentPane = new JPanel() {  
			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(  
						DangNhap_GUI.class.getResource("/images/bg2.jpg"));  
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
		};
		
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnThoat = new JButton("Thoát chương trình", new ImageIcon("data/images/shut-down.png"));
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setBounds(1099, 600, 200, 40);
		contentPane.add(btnThoat);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(424, 176, 479, 265);
		panel.setBackground(new Color(0, 0, 0, 150));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDangNhap = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblDangNhap.setBounds(144, 11, 198, 49);
		panel.add(lblDangNhap);
		lblDangNhap.setForeground(new Color(0, 206, 209));
		lblDangNhap.setBackground(new Color(255, 255, 255));
		lblDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		txtUserName = new JTextField("Tài khoản");
		txtUserName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtUserName.setBounds(41, 77, 398, 41);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		new Placeholder().placeholder(txtUserName, "Tài khoản");
		
		txtPassword = new JPasswordField("Mật khẩu");
		txtPassword.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtPassword.setEchoChar((char)0);
		txtPassword.setBounds(41, 129, 398, 41);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		new Placeholder().placeholder(txtPassword, "Mật khẩu");
		
		btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDangNhap.setBackground(new Color(0, 206, 209));
		btnDangNhap.setBounds(105, 207, 130, 41);
		panel.add(btnDangNhap);
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnDangKy = new JButton("Đăng ký");
		btnDangKy.setBackground(Color.WHITE);
		btnDangKy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDangKy.setBounds(259, 207, 130, 41);
		panel.add(btnDangKy);
		
		chcHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");
		chcHienThiMatKhau.setBounds(315, 177, 124, 23);
		panel.add(chcHienThiMatKhau);
		
		chkIsNhanVien = new JCheckBox("Đăng nhập với tư cách nhân viên");
		chkIsNhanVien.setBounds(41, 177, 229, 23);
		panel.add(chkIsNhanVien);
		
		
		
		chcHienThiMatKhau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtPassword.getText().equals("Mật khẩu")) {
					txtPassword.setEchoChar((char)0);	
					chcHienThiMatKhau.setSelected(false);
					return ;
				}
				
				if(chcHienThiMatKhau.isSelected()) {
		        	txtPassword.setEchoChar((char)0);
				}else {
					txtPassword.setEchoChar('*');
				}
			}
			
		});
	}
	
	public boolean checkPassword() throws SQLException {
		TaiKhoanDAO taiKhoanDao = new TaiKhoanDAO();
		String matKhau = taiKhoanDao.getMatKhau(txtUserName.getText());
		if(txtPassword.getText().equals(matKhau)) {
			
			return true;
		}
		
		renderError("Tài khoản hoặc mật khẩu không chính xác");	
		return false;
	}
	
	public void renderError(String message){
        JOptionPane.showMessageDialog(contentPane, message);
    }
	
	public void clear() {
		txtUserName.setText("Tài khoản");
		txtPassword.setText("Mật khẩu");
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	public void requestFocus() {
		this.txtUserName.requestFocus();
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}
}
