package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import util.Placeholder;

public class TaoTaiKhoan_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtTenTk;
	private JTextField txtMatKhau;
	private JTextField txtSdt;

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
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 400, 300);
		
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
		
		JLabel lblHoTen = new JLabel("Họ tên NV");
		lblHoTen.setPreferredSize(new Dimension(100, 14));
		pnHoTen.add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setPreferredSize(new Dimension(7, 25));
		new Placeholder().placeholder(txtHoTen, "Họ và tên");
		pnHoTen.add(txtHoTen);
		txtHoTen.setColumns(20);
		
		JPanel pnSdt = new JPanel();
		pnThongTin.add(pnSdt);
		pnSdt.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setPreferredSize(new Dimension(100, 14));
		pnSdt.add(lblSdt);
		
		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(7, 25));
		txtSdt.setColumns(20);
		new Placeholder().placeholder(txtSdt,"09xx xxx xxx");
		pnSdt.add(txtSdt);
		
		JPanel pnTenTaiKhoan = new JPanel();
		pnThongTin.add(pnTenTaiKhoan);
		pnTenTaiKhoan.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblTenTk = new JLabel("Tên tài khoản");
		lblTenTk.setPreferredSize(new Dimension(100, 14));
		pnTenTaiKhoan.add(lblTenTk);
		
		txtTenTk = new JTextField();
		txtTenTk.setPreferredSize(new Dimension(7, 25));
		txtTenTk.setColumns(20);
		new Placeholder().placeholder(txtTenTk, "tên tài khoản");
		pnTenTaiKhoan.add(txtTenTk);
		
		JPanel pnMatKhau = new JPanel();
		pnThongTin.add(pnMatKhau);
		pnMatKhau.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setPreferredSize(new Dimension(100, 14));
		pnMatKhau.add(lblMatKhau);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setPreferredSize(new Dimension(7, 25));
		txtMatKhau.setColumns(20);
		new Placeholder().placeholder(txtMatKhau, "**********");
		pnMatKhau.add(txtMatKhau);
		
		JPanel pnChucNang = new JPanel();
		pnThongTin.add(pnChucNang);
		
		JButton btnTaoTk = new JButton("Tạo tài khoản");
		btnTaoTk.setPreferredSize(new Dimension(150, 30));
		btnTaoTk.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTaoTk.setBackground(new Color(0, 250, 154));
		btnTaoTk.setToolTipText("Tạo tài khoản mới");
		pnChucNang.add(btnTaoTk);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setPreferredSize(new Dimension(70, 30));
		btnHuy.setBackground(new Color(255, 99, 71));
		pnChucNang.add(btnHuy);
	}

	public JPanel getContentPane() {
		 return this.contentPane;
	 }
}
