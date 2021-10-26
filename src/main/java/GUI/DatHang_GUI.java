package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;




import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class DatHang_GUI extends JFrame implements ActionListener, MouseListener {
	private DefaultTableModel modelDonDat;
	String[] colsDonDat = { "Mã đơn đặt hàng", "Mã khách hàng","Mã sản phẩm", "Số lượng", "Đơn giá", "Thời gian đặt" };
	public JPanel pnMain;
	private JTable tableDonDat;

	private JTextField txtSoLuong;
	private JButton btnThem;
	private JComboBox<String> cboMaKH;
	private JComboBox<String> cboMaSP;
	private JPanel panel_1;

	
	private JTextField txtGia;
	private JTextField txtTen;
	private JButton btnXem;
	private JButton btnBoChon;
	private JTextField txtTenSP;
	private JButton btnXoa;
	private JButton btnSua;
	private JTextField txtTimMaDon;
	private JButton btnTimMaDon;
	

	public DatHang_GUI() {
		

		setTitle("Quản lý đơn đặt hàng");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		getContentPane().add(pnMain);

		JLabel lbTitle = new JLabel("Quản Lý Đơn Đặt Hàng");
		lbTitle.setBounds(585, 11, 348, 30);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnMain.add(lbTitle);

		modelDonDat = new DefaultTableModel(colsDonDat, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};

		pnMain.setLayout(null);

		JPanel pn = new JPanel();
		pn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chi tiết", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn.setBounds(10, 65, 347, 360);
		pnMain.add(pn);
		pn.setLayout(null);

		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMaKH.setBounds(10, 25, 93, 25);
		pn.add(lbMaKH);

		cboMaKH = new JComboBox<String>();
		cboMaKH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboMaKH.setBounds(122, 25, 205, 25);
		cboMaKH.addItem("");
		pn.add(cboMaKH);

		JLabel lbTen = new JLabel("Tên khách hàng");
		lbTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTen.setBounds(10, 61, 93, 25);
		pn.add(lbTen);

		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(122, 61, 205, 25);
		pn.add(txtTen);
		
		JLabel lbMaSP = new JLabel("Mã sản phẩm:");
		lbMaSP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMaSP.setBounds(10, 94, 93, 25);
		pn.add(lbMaSP);

		cboMaSP = new JComboBox<String>();
		cboMaSP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboMaSP.setBounds(122, 94, 205, 25);
		cboMaSP.addItem("");
		pn.add(cboMaSP);
		JLabel lbts = new JLabel("Tên sản phẩm:");
		lbts.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbts.setBounds(10, 127, 93, 25);
		pn.add(lbts);
		txtTenSP = new JTextField();
		txtTenSP.setEditable(false);
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(122, 127, 205, 25);
		pn.add(txtTenSP);
		
		JLabel lbGia = new JLabel("Đơn giá:");
		lbGia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbGia.setBounds(10, 161, 93, 25);
		pn.add(lbGia);

		txtGia = new JTextField();
		txtGia.setEditable(false);
		txtGia.setBounds(122, 161, 205, 25);
		pn.add(txtGia);
		txtGia.setColumns(10);

		JLabel lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSoLuong.setBounds(10, 193, 93, 25);
		pn.add(lbSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSoLuong.setBounds(122, 193, 205, 25);
		pn.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		btnSua = new JButton("Sửa đơn");
		btnSua.setIcon(new ImageIcon("data/images/edit2_16.png"));
		btnSua.setBounds(10, 243, 317, 35);
		pn.add(btnSua);

		btnThem = new JButton("Tạo đơn");
		btnThem.setBounds(10, 287, 317, 35);
		pn.add(btnThem);
		btnThem.setIcon(new ImageIcon("data/images/check.png"));


		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách đơn đặt hàng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(370, 65, 900, 575);
		pnMain.add(panel_1);
		panel_1.setLayout(null);

		tableDonDat = new JTable(modelDonDat);
		JScrollPane scDonDat = new JScrollPane(tableDonDat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scDonDat.setBounds(10, 67, 870, 500);
		panel_1.add(scDonDat);

		JLabel lbTimMaDon = new JLabel("Mã đơn:");
		lbTimMaDon.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTimMaDon.setBounds(20, 25, 110, 30);
		panel_1.add(lbTimMaDon);

		txtTimMaDon = new JTextField();
		txtTimMaDon.setBounds(100, 29, 120, 25);
		panel_1.add(txtTimMaDon);
		txtTimMaDon.setColumns(10);

		btnTimMaDon = new JButton("Tìm");
		btnTimMaDon.setIcon(new ImageIcon("data/images/search_16.png"));
		btnTimMaDon.setBounds(235, 28, 115, 25);
		panel_1.add(btnTimMaDon);

		btnXem = new JButton("Xem tất cả");
		btnXem.setIcon(new ImageIcon("data/images/blueAdd_16.png"));
		btnXem.setBounds(360, 28, 115, 25);
		panel_1.add(btnXem);
		
		btnBoChon = new JButton("Bỏ chọn");
		btnBoChon.setBounds(485, 28, 115, 25);
		btnBoChon.setIcon(new ImageIcon("data/images/check2_16.png"));
		panel_1.add(btnBoChon);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(610, 28, 115, 25);
		btnXoa.setIcon(new ImageIcon("data/images/cancel_16.png"));
		panel_1.add(btnXoa);
		
		btnSua.addActionListener(this);

		btnThem.addActionListener(this);
		btnTimMaDon.addActionListener(this);
		btnXem.addActionListener(this);
		btnBoChon.addActionListener(this);
		cboMaSP.addActionListener(this);
		cboMaKH.addActionListener(this);
		tableDonDat.addMouseListener(this);
		pnMain.addMouseListener(this);

		
	}

	

	public static void main(String[] args) {
		new DatHang_GUI().setVisible(true);
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

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}}