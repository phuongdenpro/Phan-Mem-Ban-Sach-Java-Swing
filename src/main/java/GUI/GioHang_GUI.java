package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dao.ChiTietDonDatHangDAO;
import dao.DonDatHangDAO;
import dao.KhachHangDAO;
import entity.ChiTietDonDatHang;
import entity.DonDatHang;
import entity.KhachHang;
import util.Currency;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class GioHang_GUI extends JFrame {

	private KhachHang khachHang = null;
	private DonDatHang donDatHang = null;
	
	private JPanel contentPane;
	private JTextField textField_1;
	private int numberOfItem = 5;
	public JMenuItem mntmQuanLy;
	public JMenuItem mntmGioHang;
	public JMenuItem mntmDangXuat;
	public JLabel lblHelp;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;

	protected JButton btnDatHang;

	protected JButton btnTroVe;

	private JPanel pnItems;
	private JTextField txtTongTien;
	private JMenu mnMenu;
	public JMenuItem mntmThoat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//KhachHang kh = new KhachHangDAO().getKhachHang(3);
					GioHang_GUI frame = new GioHang_GUI();
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
	public GioHang_GUI() throws SQLException {
		this.khachHang = new KhachHangDAO().getKhachHang(1);
		GUI();
		renderData();
	}
	
	public GioHang_GUI(KhachHang khachHang) {
		this.khachHang = khachHang;
		GUI();
	}
	
	
	public void GUI() {
//		Giỏ hàng
		setTitle("Giỏ hàng");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		header
		contentPane.add(this.headerGUI(), BorderLayout.NORTH);
		
//		content
		JPanel panelContent = new JPanel();
		
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.X_AXIS));
		
		JScrollPane pane = new JScrollPane(panelContent);
		
		Component horizontalStrut = Box.createHorizontalStrut(30);
		panelContent.add(horizontalStrut);
		
		JPanel pnThongTinKhachHang = new JPanel();
		pnThongTinKhachHang.setBorder(new EmptyBorder(50, 0, 0, 0));
		panelContent.add(pnThongTinKhachHang);
		
		JPanel boxThongTin = new JPanel();
		pnThongTinKhachHang.add(boxThongTin);
		boxThongTin.setLayout(new BoxLayout(boxThongTin, BoxLayout.Y_AXIS));
		
		JPanel pnThongTinKH = new JPanel();
		boxThongTin.add(pnThongTinKH);
		
		JLabel lblThongTinKH = new JLabel("Thông tin khách hàng");
		lblThongTinKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnThongTinKH.add(lblThongTinKH);
		
		JPanel pnKH = new JPanel();
		FlowLayout fl_pnKH = (FlowLayout) pnKH.getLayout();
		fl_pnKH.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnKH);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaKH.setPreferredSize(new Dimension(150, 20));
		pnKH.add(lblMaKH);
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setPreferredSize(new Dimension(30, 20));
		pnKH.add(txtMaKH);
		txtMaKH.setColumns(20);
		
		JPanel pnTenKH = new JPanel();
		FlowLayout fl_pnTenKH = (FlowLayout) pnTenKH.getLayout();
		fl_pnTenKH.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnTenKH);
		
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setPreferredSize(new Dimension(150, 20));
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenKH.add(lblTenKH);
		
		txtTenKH = new JTextField(this.khachHang.getHoTen());
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(20);
		pnTenKH.add(txtTenKH);
		
		JPanel pnSDT = new JPanel();
		FlowLayout fl_pnSDT = (FlowLayout) pnSDT.getLayout();
		fl_pnSDT.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnSDT);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setPreferredSize(new Dimension(150, 20));
		lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSDT.add(lblSoDienThoai);
		
		txtSoDienThoai = new JTextField(this.khachHang.getSoDienThoai());
		txtSoDienThoai.setEditable(false);
		txtSoDienThoai.setColumns(20);
		pnSDT.add(txtSoDienThoai);
		
		JPanel pnDiaChi = new JPanel();
		FlowLayout fl_pnDiaChi = (FlowLayout) pnDiaChi.getLayout();
		fl_pnDiaChi.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnDiaChi);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(150, 20));
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnDiaChi.add(lblDiaChi);
		
		txtDiaChi = new JTextField(this.khachHang.getDiaChi());
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(20);
		pnDiaChi.add(txtDiaChi);
		
		JPanel pnTongTien = new JPanel();
		boxThongTin.add(pnTongTien);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setPreferredSize(new Dimension(150, 20));
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTongTien.add(lblTongTien);
		
		txtTongTien = new JTextField((String) null);
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(20);
		pnTongTien.add(txtTongTien);
		
		JPanel pnThongTin = new JPanel();
		boxThongTin.add(pnThongTin);
		pnThongTin.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnDatHang = new JButton("Đặt hàng", new ImageIcon("data/images/check2_16.png"));
		btnDatHang.setPreferredSize(new Dimension(150, 30));
		btnDatHang.setIconTextGap(8);
		btnDatHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatHang.setBackground(Color.WHITE);
		pnThongTin.add(btnDatHang);
		
		btnTroVe = new JButton("Tiếp tục mua hàng", new ImageIcon("data/images/carts (1).png"));
		btnTroVe.setPreferredSize(new Dimension(200, 30));
		btnTroVe.setIconTextGap(8);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTroVe.setBackground(Color.WHITE);
		pnThongTin.add(btnTroVe);
		
		JPanel panel = new JPanel();
		panelContent.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblGioHang = new JLabel("Giỏ hàng");
		lblGioHang.setBorder(new EmptyBorder(10, 0, 0, 0));
		lblGioHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblGioHang.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblGioHang.setForeground(new Color(0, 206, 209));
		panel.add(lblGioHang, BorderLayout.NORTH);
		
		
		
		JPanel boxGioHang = new JPanel();
//		panel.add(boxGioHang, BorderLayout.CENTER);
		boxGioHang.setLayout(new BoxLayout(boxGioHang, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(boxGioHang);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setPreferredSize(new Dimension(600, 500));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel pnGioHang = new JPanel();
		boxGioHang.add(pnGioHang);
		
		pnItems = new JPanel();
		pnGioHang.add(pnItems);
		pnItems.setLayout(new GridLayout(0, 1, 0, 5));
		
		
				

		ImageIcon icon_dathang = new ImageIcon("data/images/shopping-cart32.png");
		

		ImageIcon icon_tieptucmuahang = new ImageIcon("data/images/cart.png");
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		panelContent.add(horizontalStrut_1);
		contentPane.add(pane);
	}
	
	public void renderData() {
		txtMaKH.setText(String.valueOf(this.khachHang.getMaKh()));
		txtTenKH.setText(this.khachHang.getHoTen());
		txtDiaChi.setText(this.khachHang.getDiaChi());
		txtSoDienThoai.setText(this.khachHang.getSoDienThoai());
		txtTongTien.setText("0");
		mnMenu.setText("Xin chào: "+this.khachHang.getHoTen());
		pnItems.removeAll();
		donDatHang = null;
		try {
			donDatHang = new DonDatHangDAO().getDonDatHang(this.khachHang.getMaKh());
			if(donDatHang != null) {
				ArrayList<ChiTietDonDatHang> chiTietDDH = donDatHang.getChiTietDonDatHangs();
				if(chiTietDDH.size() == 0) {
					btnDatHang.setEnabled(false);
					pnItems.add(new JLabel("Không có sản phẩm nào trong giỏ hàng"));
					
					return;
				}
				btnDatHang.setEnabled(true);
				AtomicReference<Double> tongTien = new AtomicReference<Double>(0.0);
				chiTietDDH.forEach(chiTiet -> {
					pnItems.add(this.itemGUI(chiTiet));
					tongTien.set(tongTien.get() + chiTiet.getSoLuong()*chiTiet.getDonGia());
				});
				txtTongTien.setText(Currency.format(tongTien.get()));
			}else {
				btnDatHang.setEnabled(false);
				pnItems.add(new JLabel("Không có sản phẩm nào trong giỏ hàng"));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JPanel headerGUI() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(255, 255, 255));
		panelHeader.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panelHeader.add(panel);
		
		JLabel lblLogo = new JLabel("Hiệu Sách NPB");
		lblLogo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblLogo.setAlignmentX(1.0f);
		panel.add(lblLogo);
		
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBorder(null);
		pnTimKiem.setBackground(new Color(255, 255, 255));
		panelHeader.add(pnTimKiem);
		pnTimKiem.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		JPanel pbMenu = new JPanel();
		pbMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
		pbMenu.setBackground(Color.WHITE);
		panelHeader.add(pbMenu);
		
		JPanel pnTroGiup = new JPanel();
		pnTroGiup.setBackground(Color.WHITE);
		pbMenu.add(pnTroGiup);
		
		ImageIcon icon_help = new ImageIcon("data/images/question.png");
		lblHelp = new JLabel("trợ giúp        ");
		lblHelp.setIcon(icon_help);
		pnTroGiup.add(lblHelp);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pbMenu.add(menuBar);
		
		mnMenu = new JMenu("Xin chào: Trần Văn Nhân");
		menuBar.add(mnMenu);
		
		mntmGioHang = new JMenuItem("Giỏ hàng");
		mnMenu.add(mntmGioHang);
		
		mntmDangXuat = new JMenuItem("Đăng xuất");
		mnMenu.add(mntmDangXuat);
		
		mntmThoat = new JMenuItem("Thoát chương trình");
		mnMenu.add(mntmThoat);
		
		return panelHeader;
	}
	
	public JPanel itemGUI(ChiTietDonDatHang chiTietDDH) {
		JPanel pnItem = new JPanel();
		pnItem.setPreferredSize(new Dimension(700, 100));
		pnItem.setBackground(Color.WHITE);
		pnItem.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		pnItem.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbIcon = new JLabel();
		lbIcon.setAlignmentX(0.5f);
		panel_2_1.add(lbIcon);
		ImageIcon imageProduct = new ImageIcon("data/product/default.png");
		imageProduct = TrangChu_GUI.resizeIcon(imageProduct, new Dimension(150, 90));
		lbIcon.setIcon(imageProduct);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		pnItem.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTenSp = new JLabel(chiTietDDH.getSanPham().getTenSp());
		lblTenSp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenSp.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSp.setToolTipText(chiTietDDH.getSanPham().getTenSp());
		panel_3.add(lblTenSp);
		
		
		JPanel pnDonGia = new JPanel();
		pnDonGia.setBackground(Color.WHITE);
		panel_3.add(pnDonGia);
		pnDonGia.setLayout(new BoxLayout(pnDonGia, BoxLayout.X_AXIS));
		
		JLabel lblDonGia = new JLabel("Đơn giá:    ");
		lblDonGia.setBackground(Color.WHITE);
		pnDonGia.add(lblDonGia);
		
		JLabel donGia = new JLabel(Currency.format(chiTietDDH.getDonGia()));
//		lblTongTien.setForeground(new Color(0, 206, 209));
		donGia.setBackground(Color.WHITE);
		pnDonGia.add(donGia);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_3.add(panel_1_1);
		panel_1_1.setLayout(new BoxLayout(panel_1_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_3 = new JLabel("Số lượng:  ");
		panel_1_1.add(lblNewLabel_3);
		
		JTextField txtSoLuong = new JTextField();
		txtSoLuong.setText(String.valueOf(chiTietDDH.getSoLuong()));
		txtSoLuong.setColumns(10);
		txtSoLuong.setEditable(false);
		panel_1_1.add(txtSoLuong);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4 = new JLabel("Tổng tiền:  ");
		lblNewLabel_4.setBackground(Color.WHITE);
		panel_4.add(lblNewLabel_4);
		
		JLabel lblTongTien = new JLabel(Currency.format(chiTietDDH.getDonGia()*chiTietDDH.getSoLuong()));
		lblTongTien.setForeground(new Color(0, 206, 209));
		lblTongTien.setBackground(Color.WHITE);
		panel_4.add(lblTongTien);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		pnItem.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		pnItem.add(panel_6);
		


		ImageIcon icon_delete = new ImageIcon("data/images/trash.png");
		JButton btnXoa = new JButton("Xóa", icon_delete);
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnXoa.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_6.add(btnXoa);
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn xóa ?");
				System.out.println(choose);
				
				if(choose == 0) {
					try {
						boolean kq = new ChiTietDonDatHangDAO().xoaChiTietDonDatHang(chiTietDDH.getSanPham().getMaSp(), donDatHang.getMaDDH());
						if(kq == false) {
							JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
							return;
						}
						
						pnItems.remove(pnItem);
						pnItems.revalidate();
						pnItems.repaint();
	//					pnItem.setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		
		return pnItem;
	}
	
	
	public JPanel getContentPane() {
		return this.contentPane;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	
	
}
