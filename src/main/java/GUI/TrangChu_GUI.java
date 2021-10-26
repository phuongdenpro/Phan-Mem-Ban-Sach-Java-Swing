package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import DAO.DonDatHangDao;
import DAO.LoaiSanPhamDAO;
import DAO.SanPhamDAO;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;

public class TrangChu_GUI extends JFrame {

	private KhachHang khachHang = null;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Container panelContent;

	
	public JMenuItem mntmQuanLy;
	public JMenuItem mntmGioHang;
	public JMenuItem mntmDangXuat;
	public JLabel lblHelp;
	
	private LoaiSanPhamDAO loaiSPDao;
	private ArrayList<LoaiSanPham> dslsp = new ArrayList<LoaiSanPham>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					TrangChu_GUI frame = new TrangChu_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TrangChu_GUI() {
		
	} 
	
	public TrangChu_GUI(KhachHang khachHang) throws SQLException {
		this.khachHang = khachHang;
		GUI();
	} 
	
	public void GUI() throws SQLException {
		setTitle("Trang chủ");
		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(this.headerGUI(), BorderLayout.NORTH);
		
		
		panelContent = new JPanel();
		panelContent.setBackground(Color.WHITE);
		
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
		
		JScrollPane pane = new JScrollPane(panelContent);
		pane.getVerticalScrollBar().setUnitIncrement(20);
		pane.setBorder(null);
		contentPane.add(pane);
		
		
		
		loaiSPDao = new LoaiSanPhamDAO();
		dslsp = loaiSPDao.getDanhSachLoaiSanPham();
		
		for(int i=0; i<dslsp.size(); i++) {
			this.categoryGUI(dslsp.get(i));
		}
		
		
		
	}
	
//	public void renderData() {
//		
//	}

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
	
	public void categoryGUI(LoaiSanPham loaiSanPham) {
		JPanel pnCategory = new JPanel();
		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		pnCategory.setSize(screenSize);
		
		pnCategory.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelContent.add(pnCategory);
		pnCategory.setLayout(new BorderLayout(0, 0));
		
		
		
		
		JPanel panel_5 = new JPanel();
		pnCategory.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblTruyen = new JLabel(loaiSanPham.getTenLoai(), SwingConstants.CENTER);
		lblTruyen.setForeground(new Color(0, 206, 209));
		lblTruyen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTruyen.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 206, 209)));
		panel_5.add(lblTruyen);
		
		JPanel panel_10 = new JPanel(new FlowLayout());
		pnCategory.add(panel_10, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 5, 10, 5); 
//		gridLayout.setHgap(10);
		panel_9.setLayout(gridLayout);
		panel_10.add(panel_9);
		
//		panel_9.setLayout(new GridLayout(0, 4));
		ArrayList<SanPham> dsSanPham = loaiSanPham.getSanPhams();
		for(int i=0; i<dsSanPham.size(); i++) {
			panel_9.add(this.item(dsSanPham.get(i)));
		}
		
	}
	

	public JPanel item(SanPham sanPham) {
		JPanel pnItem = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
//		pnItem.setBackground(color);
//		pnItem.setSize(new Dimension(200, 300));
		pnItem.setPreferredSize(new Dimension(200, 200));
		pnItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbIcon = new JLabel();
		lbIcon.setAlignmentX(CENTER_ALIGNMENT);
		lbIcon.setPreferredSize(new Dimension(200, 100));
		ImageIcon imageProduct = new ImageIcon("data/product/default.png");
		imageProduct = TrangChu_GUI.resizeIcon(imageProduct, new Dimension(199, 100));
		lbIcon.setIcon(imageProduct);
		pnItem.add(lbIcon);
		
		JPanel pnInfo = new JPanel();
//		pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));
		pnInfo.setPreferredSize(new Dimension(190, 80));
		pnInfo.setLayout(new GridLayout(3, 0));
		pnItem.add(pnInfo);
		
		JLabel lbTenSanPham = new JLabel(sanPham.getTenSp());
		
//		lbTenSanPham.setPreferredSize(new Dimension(200, 20));
		lbTenSanPham.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		pnInfo.add(lbTenSanPham);
		
		JLabel lbGia = new JLabel(String.valueOf(sanPham.getGiaSp()));
		pnInfo.add(lbGia);
		
		JButton btnThemVaoGio = new JButton("Thêm vào giỏ");
		btnThemVaoGio.setBackground(Color.WHITE);
		pnInfo.add(btnThemVaoGio);
		
		btnThemVaoGio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soLuong = showThemVaoGio();
				if(soLuong <= 0) {
					JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập số lớn hơn 0");
					return;
				}
				
				try {
					boolean kq = new DonDatHangDao().themSanPhamVaoDonDatHang(sanPham, soLuong, khachHang.getMaKh());
					if(kq) {
						JOptionPane.showMessageDialog(contentPane, "Thêm vào giỏ thành công");
					}else {
						JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		return pnItem;
	}
	
	public int showThemVaoGio() {
		try {
			int soLuong = Integer.parseInt(JOptionPane.showInputDialog(contentPane, "Số lượng", 1));
			
			return soLuong;
		}catch (Exception e) {
		}
		
		return -1;
	}
	
	public static JPanel panelBackgroundImage(final String filepath) {
		return new JPanel() {  
			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(  
						DangNhap_GUI.class.getResource(filepath));  
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
		};
	}
	
	public static ImageIcon resizeIcon(ImageIcon icon, Dimension dimension) {
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance((int)dimension.getWidth(), (int)dimension.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);  // transform it back
		return icon;
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
}
