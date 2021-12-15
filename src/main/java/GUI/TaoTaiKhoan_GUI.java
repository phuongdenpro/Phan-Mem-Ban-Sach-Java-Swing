package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dao.TaiKhoanDAO;
import entity.KhachHang;
import entity.NhanVien;
import util.Placeholder;
import javax.swing.JComboBox;

public class TaoTaiKhoan_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtTenTk;
	private JTextField txtMatKhau;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private JTextField txtRePassword;
	private JTextField txtDiaChi;
	private JComboBox cboCaLamViec;
	private JPanel pnChucNang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaoTaiKhoan_GUI frame = new TaoTaiKhoan_GUI();
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
	public TaoTaiKhoan_GUI() {
		setTitle("Tạo tài khoản");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 400, 450);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDe = new JPanel();
		contentPane.add(pnTieuDe, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("Tạo tài khoản nhân viên");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTieuDe.add(lblTieuDe);
		
		JPanel pnThongTin = new JPanel();
		Border compoud = BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pnThongTin.setBorder(compoud);
		contentPane.add(pnThongTin);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
		
		JPanel pnHoTen = new JPanel();
		pnThongTin.add(pnHoTen);
		pnHoTen.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblHoTen = new JLabel("Họ tên NV *");
		lblHoTen.setPreferredSize(new Dimension(100, 14));
		pnHoTen.add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setPreferredSize(new Dimension(7, 25));
		pnHoTen.add(txtHoTen);
		txtHoTen.setColumns(20);
		
		JPanel pnSdt = new JPanel();
		pnThongTin.add(pnSdt);
		pnSdt.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblSdt = new JLabel("Số điện thoại *");
		lblSdt.setPreferredSize(new Dimension(100, 14));
		pnSdt.add(lblSdt);
		
		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(7, 25));
		txtSdt.setColumns(20);
		pnSdt.add(txtSdt);
		
		JPanel pnDiaChi = new JPanel();
		pnThongTin.add(pnDiaChi);
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(100, 14));
		pnDiaChi.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(7, 25));
		txtDiaChi.setColumns(20);
		pnDiaChi.add(txtDiaChi);
		
		JPanel pnCaLamViec = new JPanel();
		pnThongTin.add(pnCaLamViec);
		pnCaLamViec.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblCaLamViec = new JLabel("Ca làm việc *");
		lblCaLamViec.setPreferredSize(new Dimension(100, 14));
		pnCaLamViec.add(lblCaLamViec);
		
		cboCaLamViec = new JComboBox();
		cboCaLamViec.setPreferredSize(new Dimension(200, 22));
		pnCaLamViec.add(cboCaLamViec);
		cboCaLamViec.addItem((String) "Sáng");
		cboCaLamViec.addItem((String) "Chiều");
		
		pnChucNang = new JPanel();
		pnThongTin.add(pnChucNang);
		pnChucNang.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblChucNang = new JLabel("Chức năng *");
		lblChucNang.setPreferredSize(new Dimension(100, 14));
		pnChucNang.add(lblChucNang);
		
		JComboBox cboChucNang = new JComboBox();
		cboChucNang.setPreferredSize(new Dimension(200, 22));
		pnChucNang.add(cboChucNang);
		cboChucNang.addItem((String) "Nhân viên bán hàng");
		cboChucNang.addItem((String) "Nhân viên quản lý sản phẩm");
		
		JPanel pnTenTaiKhoan = new JPanel();
		pnThongTin.add(pnTenTaiKhoan);
		pnTenTaiKhoan.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblTenTk = new JLabel("Tên tài khoản *");
		lblTenTk.setPreferredSize(new Dimension(100, 14));
		pnTenTaiKhoan.add(lblTenTk);
		
		txtTenTk = new JTextField();
		txtTenTk.setPreferredSize(new Dimension(7, 25));
		txtTenTk.setColumns(20);
		pnTenTaiKhoan.add(txtTenTk);
		
		JPanel pnMatKhau = new JPanel();
		pnThongTin.add(pnMatKhau);
		pnMatKhau.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblMatKhau = new JLabel("Mật khẩu *");
		lblMatKhau.setPreferredSize(new Dimension(100, 14));
		pnMatKhau.add(lblMatKhau);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setPreferredSize(new Dimension(7, 25));
		txtMatKhau.setColumns(20);
		pnMatKhau.add(txtMatKhau);
		
		JPanel pnNhapLaiMatKhau = new JPanel();
		pnThongTin.add(pnNhapLaiMatKhau);
		pnNhapLaiMatKhau.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNhapLaiMatKhau = new JLabel("Nhập lại *");
		lblNhapLaiMatKhau.setPreferredSize(new Dimension(100, 14));
		pnNhapLaiMatKhau.add(lblNhapLaiMatKhau);
		
		txtRePassword = new JPasswordField();
		txtRePassword.setPreferredSize(new Dimension(7, 25));
		txtRePassword.setColumns(20);
		pnNhapLaiMatKhau.add(txtRePassword);
		
		JPanel pnBtn = new JPanel();
		pnThongTin.add(pnBtn);
		
		JButton btnTaoTk = new JButton("Tạo tài khoản", new ImageIcon("data/images/blueAdd_16.png"));
		btnTaoTk.setPreferredSize(new Dimension(150, 30));
		btnTaoTk.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTaoTk.setBackground(new Color(0, 250, 154));
		btnTaoTk.setToolTipText("Tạo tài khoản mới");
		pnBtn.add(btnTaoTk);
		
		JButton btnHuy = new JButton("Hủy", new ImageIcon("data/images/cancel_16.png"));
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setPreferredSize(new Dimension(70, 30));
		btnHuy.setBackground(new Color(255, 99, 71));
		pnBtn.add(btnHuy);
		
		btnTaoTk.addActionListener((e) -> {
			if(txtHoTen.getText().trim().equals("")){
	            renderError(txtHoTen, "Họ tên không được để trống");
	            return;
	        }
			
			if(!txtHoTen.getText().matches("^[^0-9]{2,25}$")){
	            renderError(txtHoTen, "Họ tên không được chứa chữ số, ít nhất là 2 ký tự");
	            return;
	        }
			
			
			if(!txtSdt.getText().matches("^0[0-9]{9}$")){
	            renderError(txtSdt, "Số điện thoại không hợp lệ");
	            return;
	        }
			
			if(txtTenTk.getText().trim().length() < 8){
	            renderError(txtTenTk, "Tài khoản phải ít nhất 8 ký tự");
	            return;
	        }
			
			if(!txtTenTk.getText().matches("^[a-zA-Z0-9]{8,}$")){
	            renderError(txtTenTk, "Tài khoản chỉ được chứa ký tự hoa thường a-z và chữ số");
	            return;
	        }
			
			if(txtMatKhau.getText().trim().length() < 8){
	            renderError(txtMatKhau, "Mật khẩu phải ít nhất 8 ký tự");
	            return;
	        }
			
			if(!txtMatKhau.getText().matches("^[a-zA-Z0-9]{8,}$")){
	            renderError(txtMatKhau, "Mật khẩu chỉ được chứa ký tự hoa thường a-z và chữ số");
	            return;
	        }

			if(!txtMatKhau.getText().trim().equals(txtRePassword.getText().trim())){
	            renderError(txtRePassword, "Mật khẩu không khớp");
	            return;
	        }
			
			NhanVien nv = new NhanVien(txtHoTen.getText(), txtSdt.getText(), txtDiaChi.getText(), cboCaLamViec.getSelectedIndex()+1, cboChucNang.getSelectedIndex()+1);
//			
			try {
				if(new TaiKhoanDAO().themTaiKhoan(nv, txtTenTk.getText(), txtMatKhau.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Tạo tài khoản thành công");
					txtHoTen.setText("");
					txtSdt.setText("");
					txtDiaChi.setText("");
					txtTenTk.setText("");
					txtMatKhau.setText("");
					txtRePassword.setText("");
					this.setVisible(false);
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			
			JOptionPane.showMessageDialog(contentPane, "Tài khoản đã tồn tại");
			
			return;
		});
		
		btnHuy.addActionListener((e) -> {
			this.setVisible(false);
		});
	}
	
	public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(contentPane, message);
    }

	public JPanel getContentPane() {
		 return this.contentPane;
	 }
}
