package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import entity.KhachHang;

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

public class GioHang_GUI extends JFrame {

	private KhachHang khachHang = null;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int numberOfItem = 5;
	public JMenuItem mntmQuanLy;
	public JMenuItem mntmGioHang;
	public JMenuItem mntmDangXuat;
	public JLabel lblHelp;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	 */
	public GioHang_GUI() {
		GUI();
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(50, 0, 0, 0));
		panelContent.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_5 = new JLabel("Thông tin khách hàng");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_5.add(lblNewLabel_5);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_6 = new JLabel("Mã khách hàng");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setPreferredSize(new Dimension(150, 20));
		panel_6.add(lblNewLabel_6);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setPreferredSize(new Dimension(30, 20));
		panel_6.add(textField_2);
		textField_2.setColumns(20);
		
		JPanel panel_6_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Tên khách hàng");
		lblNewLabel_6_1.setPreferredSize(new Dimension(150, 20));
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6_1.add(lblNewLabel_6_1);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(20);
		panel_6_1.add(textField_3);
		
		JPanel panel_6_1_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6_1_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6_1_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_6_1_1.setPreferredSize(new Dimension(150, 20));
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6_1_1.add(lblNewLabel_6_1_1);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(20);
		panel_6_1_1.add(textField_4);
		
		JPanel panel_6_1_1_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6_1_1_1.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6_1_1_1);
		
		JLabel lblNewLabel_6_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_6_1_1_1.setPreferredSize(new Dimension(150, 20));
		lblNewLabel_6_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6_1_1_1.add(lblNewLabel_6_1_1_1);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(20);
		panel_6_1_1_1.add(textField_5);
		
		JPanel pnThongTin = new JPanel();
		panel_4.add(pnThongTin);
		pnThongTin.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnDatHang = new JButton("Đặt hàng", null);
		btnDatHang.setPreferredSize(new Dimension(150, 30));
		btnDatHang.setIconTextGap(8);
		btnDatHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatHang.setBackground(Color.WHITE);
		pnThongTin.add(btnDatHang);
		
		JButton btnTroVe = new JButton("Tiếp tục mua hàng", null);
		btnTroVe.setPreferredSize(new Dimension(200, 30));
		btnTroVe.setIconTextGap(8);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTroVe.setBackground(Color.WHITE);
		pnThongTin.add(btnTroVe);
		
		JPanel panel = new JPanel();
		panelContent.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Giỏ hàng");
		lblNewLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setForeground(new Color(0, 206, 209));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, -500 + numberOfItem*200));
		panel_1.add(panel_2);
		
		JPanel pnItems = new JPanel();
		panel_2.add(pnItems);
		pnItems.setLayout(new GridLayout(0, 1, 0, 5));
		
		pnItems.add(this.itemGUI());
		pnItems.add(this.itemGUI());
		pnItems.add(this.itemGUI());
		pnItems.add(this.itemGUI());
		pnItems.add(this.itemGUI());
		

		ImageIcon icon_dathang = new ImageIcon("data/images/shopping-cart32.png");
		

		ImageIcon icon_tieptucmuahang = new ImageIcon("data/images/cart.png");
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		panelContent.add(horizontalStrut_1);
		contentPane.add(pane);
	}
	
	public JPanel headerGUI() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(255, 255, 255));
		panelHeader.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panelHeader.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Hiệu Sách NPB");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2.setAlignmentX(1.0f);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(20);
		
		JButton btnNewButton = new JButton("T\u00ECm ki\u1EBFm");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setPreferredSize(new Dimension(100, 19));
		panel_1.add(btnNewButton);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_3.setBackground(Color.WHITE);
		panelHeader.add(panel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_3.add(panel_2);
		
		ImageIcon icon_help = new ImageIcon("data/images/question.png");
		lblHelp = new JLabel("trợ giúp        ");
		lblHelp.setIcon(icon_help);
		panel_2.add(lblHelp);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Xin chào: Trần Văn Nhân");
		menuBar.add(mnNewMenu);
		
		mntmGioHang = new JMenuItem("Giỏ hàng");
		mnNewMenu.add(mntmGioHang);
		
		mntmDangXuat = new JMenuItem("Đăng xuất");
		mnNewMenu.add(mntmDangXuat);
		
		return panelHeader;
	}
	
	public JPanel itemGUI() {
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
		ImageIcon imageProduct = new ImageIcon("data/product/conan_tap_1.jpg");
		imageProduct = TrangChu_GUI.resizeIcon(imageProduct, new Dimension(150, 90));
		lbIcon.setIcon(imageProduct);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		pnItem.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Conan - tập 1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_1);
		
		JPanel pnDonGia = new JPanel();
		pnDonGia.setBackground(Color.WHITE);
		panel_3.add(pnDonGia);
		pnDonGia.setLayout(new BoxLayout(pnDonGia, BoxLayout.X_AXIS));
		
		JLabel lblDonGia = new JLabel("Đơn giá:    ");
		lblDonGia.setBackground(Color.WHITE);
		pnDonGia.add(lblDonGia);
		
		JLabel donGia = new JLabel("14.000đ");
//		lblTongTien.setForeground(new Color(0, 206, 209));
		donGia.setBackground(Color.WHITE);
		pnDonGia.add(donGia);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_3.add(panel_1_1);
		panel_1_1.setLayout(new BoxLayout(panel_1_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_3 = new JLabel("Số lượng:  ");
		panel_1_1.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setColumns(10);
		panel_1_1.add(textField);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4 = new JLabel("Tổng tiền:  ");
		lblNewLabel_4.setBackground(Color.WHITE);
		panel_4.add(lblNewLabel_4);
		
		JLabel lblTongTien = new JLabel("14.000đ");
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
		JButton btnNewButton = new JButton("Xóa", icon_delete);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_6.add(btnNewButton);
		
		return pnItem;
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
}
