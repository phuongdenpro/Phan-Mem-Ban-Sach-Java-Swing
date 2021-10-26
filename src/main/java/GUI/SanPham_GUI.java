package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale.Category;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class SanPham_GUI extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	String[] cols = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn vị tính", "Gía nhập", "Gía bán", "Mô tả",
			"Mã loại" };
	private DefaultTableModel model;
	private JTable table;
	private JButton btnTaoMoi, btnDangxuat, btnThoat;
	private JTextField txtTim;
	private JTextField txtmaSP;
	private JTextField txttenSP;
	private JTextField txtSoluong;
	private JTextField txtDonvi;
	private JTextField txtGianhap;
	private JTextField txtGiaban;
	private JTextField txtMota;
	private JLabel lblTimKiem;
	private JTextField txtTimKiem;
	private JRadioButton radMaLoai;
	private JRadioButton radMaSanPham;
	private JRadioButton radTenSanPham;
	private static JComboBox cboListMaloai;

	
	private JPanel contentPane;
	
	public SanPham_GUI() {
		
		setTitle("Quản Lý Sản Phẩm");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		ImageIcon icon1 = new ImageIcon("data/images/sanpham.png");
		ImageIcon icon2 = new ImageIcon("data/images/blueAdd_16.png");
		ImageIcon icon3 = new ImageIcon("data/images/trash.png");
		ImageIcon icon4 = new ImageIcon("data/images/repairing-service.png");
		ImageIcon icon5 = new ImageIcon("data/images/refresh.png");
		ImageIcon icon6 = new ImageIcon("data/images/timkiem.png");
		ImageIcon icon7 = new ImageIcon("data/images/search_16.png");

		JLabel jLabel1 = new JLabel();
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.SystemColor.activeCaption);
        jLabel1.setIcon(icon1); // NOI18N
        jLabel1.setText("THÔNG TIN SẢN PHẨM");

		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, 1300, 700);
		

		JLabel lbTitle = new JLabel("Quản Lý Sản Phẩm");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbTitle.setForeground(Color.BLACK);
		lbTitle.setBounds(500, 10, 500, 35);

		JLabel lblmaSP = new JLabel("Mã sản phẩm: ");
		JLabel lbltenSP = new JLabel("Tên sản phẩm: ");
		JLabel lblSoluong = new JLabel("Số lượng:");
		JLabel lblDonvi = new JLabel("Đơn vị tính: ");
		JLabel lblGianhap = new JLabel("Giá nhập: ");
		JLabel lblGiaban = new JLabel("Giá bán: ");
		JLabel lblMota = new JLabel("Mô tả: ");
		JLabel lblMaloai = new JLabel("Mã loại: ");
		
		btnThem = new JButton();
		btnThem.setText("Thêm");
		btnThem.setIcon(icon2);
        btnXoa = new JButton();
        btnXoa.setIcon(icon3); // NOI18N
        btnXoa.setText("Xóa");
        btnSua = new JButton();
        btnSua.setIcon(icon4); // NOI18N
        btnSua.setText("Sửa");
        btnTaoMoi = new JButton();
        btnTaoMoi.setIcon(icon5); // NOI18N
        btnTaoMoi.setText("Tạo Mới");
        lblTimKiem = new JLabel();
        lblTimKiem.setIcon(icon6); // NOI18N
        lblTimKiem.setText("Search: ");
        txtTimKiem = new JTextField();
        btnTim = new JButton();
        btnTim.setText("Tìm");
		txtmaSP = new JTextField();
		txttenSP = new JTextField();
		txtSoluong = new JTextField();
		txtDonvi = new JTextField();
		txtGianhap = new JTextField();
		txtGiaban = new JTextField();
		txtMota = new JTextField();
		cboListMaloai = new JComboBox<String>();
		

		JPanel pnThongtin = new JPanel();
		pnThongtin.setLayout(null);
		pnThongtin.setBounds(220, 50, 800, 280);
		pnThongtin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
//		pnThongtin.setBorder(new TitledBorder(
//				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
//				"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		//pnThongtin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jLabel1.setBounds(20,10,500,30);
		lblmaSP.setBounds(40, 50, 100, 22);
		txtmaSP.setBounds(130, 50, 220, 22);
		lbltenSP.setBounds(430, 50, 100, 22);
		txttenSP.setBounds(530, 50, 220, 22);
		lblSoluong.setBounds(40, 77, 100, 22);
		txtSoluong.setBounds(130, 77, 220, 22);
		lblDonvi.setBounds(430, 77, 100, 22);
		txtDonvi.setBounds(530, 77, 220, 22);
		lblGianhap.setBounds(40, 107, 110, 22);
		txtGianhap.setBounds(130, 107, 220, 22);
		lblGiaban.setBounds(430, 107, 100, 22);
		txtGiaban.setBounds(530, 107, 220, 22);
		lblMota.setBounds(40, 137, 100, 22);
		txtMota.setBounds(130, 137, 220, 22);
		lblMaloai.setBounds(430, 137, 100, 22);
		cboListMaloai.setBounds(530, 137, 220, 22);
		cboListMaloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		
		radMaSanPham = new JRadioButton("Mã Sản Phẩm");
        radMaSanPham.setFont(new Font("Arial", Font.BOLD, 14));
        radTenSanPham = new JRadioButton("Tên Sản Phẩm");
        radTenSanPham.setFont(new Font("Arial", Font.BOLD, 14));
		radMaLoai = new JRadioButton("Mã Loại", true);
		radMaLoai.setFont(new Font("Arial", Font.BOLD, 14));
        

        ButtonGroup btnGr = new ButtonGroup();
        btnGr.add(radMaSanPham);
        btnGr.add(radTenSanPham);
        btnGr.add(radMaLoai);
        
        
      
       
		
		btnThem.setBounds(250,170,120,25);
		btnXoa.setBounds(380,170,120,25);
		btnSua.setBounds(250,205,120,25);
		btnTaoMoi.setBounds(380,205,120,25);
		lblTimKiem.setBounds(30, 230, 150, 40);
		txtTimKiem.setBounds(120, 240, 200, 22);
		radMaSanPham.setBounds(330,240,120,22);
		radTenSanPham.setBounds(450,240,130,22);
		radMaLoai.setBounds(580,240,90,22);
		
		btnTim.setBounds(670, 240, 80, 25);
		btnTim.setIcon(icon7);
		
		pnThongtin.add(jLabel1);
		pnThongtin.add(lblmaSP);
		pnThongtin.add(txtmaSP);
		pnThongtin.add(lbltenSP);
		pnThongtin.add(txttenSP);
		pnThongtin.add(lblSoluong);
		pnThongtin.add(txtSoluong);
		pnThongtin.add(lblDonvi);
		pnThongtin.add(txtDonvi);
		pnThongtin.add(lblGianhap);
		pnThongtin.add(txtGianhap);
		pnThongtin.add(lblGiaban);
		pnThongtin.add(txtGiaban);
		pnThongtin.add(lblMota);
		pnThongtin.add(txtMota);
		pnThongtin.add(lblMaloai);
		pnThongtin.add(cboListMaloai);
		
		pnThongtin.add(btnThem);
		pnThongtin.add(btnXoa);
		pnThongtin.add(btnSua);
		pnThongtin.add(btnTaoMoi);
		pnThongtin.add(lblTimKiem);
		pnThongtin.add(txtTimKiem);
		pnThongtin.add(radMaSanPham);
		pnThongtin.add(radTenSanPham);
		pnThongtin.add(radMaLoai);
		pnThongtin.add(btnTim);
//		pnThongtin.add(txtgiaXe);

		// int widthLb = 85, widthPn = 700, widthBtn = 70, h = 25, hTxt = 25, xTxt =
		// 105;

		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnTable.setLayout(null);
		model = new DefaultTableModel(cols, 0) {
			// khóa không cho người dùng nhập trên table
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table = new JTable(model);
		JScrollPane sql = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTable.add(sql);
		pnTable.setBounds(20, 330, 1255, 310);
		sql.setBounds(10, 20, 1230, 275);

		
		pnMain.add(lbTitle);
		pnMain.add(pnThongtin);
		pnMain.add(pnTable);
		//pnMain.add(pnChucnang);

		getContentPane().add(pnMain);

		
		table.addMouseListener(this);

	}
			
	public static void main(String[] args) {
		new SanPham_GUI().setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
