package GUI;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;

import DAO.TaiKhoanDAO;
import entity.KhachHang;
import util.Placeholder;

public class DangKy_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	public JButton btnDangKy;
	public JButton btnDangNhap;
	private JPasswordField txtRePassword;
	private JTextField txtTenKH;
	private JCheckBox chcHienThiMatKhau;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKy_GUI frame = new DangKy_GUI();
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
	public DangKy_GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		
		contentPane = 
//				new JPanel(); 
				TrangChu_GUI.panelBackgroundImage("/images/bg3.jpg");
		
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(423, 114, 479, 467);
		panel.setBackground(new Color(0, 0, 0, 150));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblDangKy = new JLabel("Đăng ký");
		lblDangKy.setBounds(159, 11, 159, 49);
		panel.add(lblDangKy);
		lblDangKy.setForeground(new Color(0, 206, 209));
		lblDangKy.setBackground(new Color(255, 255, 255));
		lblDangKy.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		txtTenKH = new JTextField("Họ và tên");
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtTenKH.setBounds(41, 73, 398, 41);
		panel.add(txtTenKH);
		new Placeholder().placeholder(txtTenKH, "Họ và tên");
		
		txtSoDienThoai = new JTextField("Số điện thoại");
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtSoDienThoai.setBounds(41, 125, 398, 41);
		panel.add(txtSoDienThoai);
		new Placeholder().placeholder(txtSoDienThoai, "Số điện thoại");
		
		txtDiaChi = new JTextField("Địa chỉ");
		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtDiaChi.setBounds(41, 177, 398, 41);
		panel.add(txtDiaChi);
		new Placeholder().placeholder(txtDiaChi, "Địa chỉ");
		
		txtUserName = new JTextField("Tài khoản");
		txtUserName.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtUserName.setBounds(41, 229, 398, 41);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		new Placeholder().placeholder(txtUserName, "Tài khoản");
		
		txtPassword = new JPasswordField("Mật khẩu");
		txtPassword.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtPassword.setEchoChar((char)0);
		txtPassword.setBounds(41, 281, 398, 41);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		new Placeholder().placeholder(txtPassword, "Mật khẩu");
		
		txtRePassword = new JPasswordField("Nhập lại mật khẩu");
		txtRePassword.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtRePassword.setEchoChar((char)0);
		txtRePassword.setBounds(41, 333, 398, 41);
		panel.add(txtRePassword);
		txtRePassword.setColumns(10);
		new Placeholder().placeholder(txtRePassword, "Nhập lại mật khẩu");
		
		btnDangKy = new JButton("Đăng ký");
		btnDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDangKy.setBackground(new Color(0, 206, 209));
		btnDangKy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDangKy.setBounds(105, 411, 130, 41);
		panel.add(btnDangKy);
		
		btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.setBackground(Color.WHITE);
		btnDangNhap.setBounds(261, 411, 130, 41);
		panel.add(btnDangNhap);
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		chcHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");
		chcHienThiMatKhau.setBounds(300, 381, 139, 23);
		panel.add(chcHienThiMatKhau);

		chcHienThiMatKhau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hienThiPassword();
				hienThiRePassword();
			}
			
		});
	}
	
	public void hienThiPassword() {
		if(chcHienThiMatKhau.isSelected()) {
        	txtPassword.setEchoChar((char)0);
		}else {
			txtPassword.setEchoChar('*');
		}
	}
	
	public void hienThiRePassword() {
		if(chcHienThiMatKhau.isSelected()) {
			txtRePassword.setEchoChar((char)0);
		}else {
			txtRePassword.setEchoChar('*');
		}
	}
	
	public boolean dangKy() throws SQLException {
		
		if(txtTenKH.getText().trim().equals("") || txtTenKH.getText().trim().equals("Họ và tên")){
            renderError(txtTenKH, "Tên khách hàng không được để trống");
            return false;
        }
		
		if(!txtTenKH.getText().matches("^[^0-9]{2,25}$")){
            renderError(txtTenKH, "Tên khách hàng không được chứa chữ số, ít nhất là 2 ký tự");
            return false;
        }
		
		if(!txtSoDienThoai.getText().matches("^0[0-9]{9}$")){
            renderError(txtSoDienThoai, "Số điện thoại không hợp lệ");
            return false;
        }
		
		if(txtUserName.getText().trim().length() < 8 || txtUserName.getText().trim().equals("Tài khoản")){
            renderError(txtUserName, "Tài khoản phải ít nhất 8 ký tự");
            return false;
        }
		
		if(!txtUserName.getText().matches("^[a-zA-Z0-9]{8,}$")){
            renderError(txtUserName, "Tài khoản chỉ được chứa ký tự hoa thường a-z và chữ số");
            return false;
        }
		
		if(txtPassword.getText().trim().length() < 8 || txtPassword.getText().trim().equals("Mật khẩu")){
            renderError(txtPassword, "Mật khẩu phải ít nhất 8 ký tự");
            return false;
        }
		
		if(!txtPassword.getText().matches("^[a-zA-Z0-9]{8,}$")){
            renderError(txtPassword, "Mật khẩu chỉ được chứa ký tự hoa thường a-z và chữ số");
            return false;
        }

		if(!txtPassword.getText().trim().equals(txtRePassword.getText().trim())){
            renderError(txtRePassword, "Mật khẩu không khớp");
            return false;
        }
		
		TaiKhoanDAO taiKhoanDao = new TaiKhoanDAO();
		KhachHang kh = new KhachHang(txtTenKH.getText(), txtSoDienThoai.getText(), txtDiaChi.getText());
		
		if(taiKhoanDao.themTaiKhoan(kh, txtUserName.getText(), txtPassword.getText())) {
			return true;
		}
		
		JOptionPane.showMessageDialog(contentPane, "Tài khoản đã tồn tại");
		
		return false;
	}
	
	public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(contentPane, message);
    }
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	public void requestFocus() {
		this.txtUserName.requestFocus();
	}
}
