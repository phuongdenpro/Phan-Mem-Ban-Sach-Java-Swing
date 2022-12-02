package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

import dao.LoaiSanPhamDAO;
import dao.NhaCungCapDAO;
import dao.SanPhamDAO;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;
import util.Currency;
import util.Placeholder;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.ScrollPaneConstants;

public class SanPhamKhac_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable table;

	private JTextField txtSoLuong;
	private JTextField txtGiaNhap;
	private JTextField txtGiaBan;

	private JComboBox<String> cboListMaloai;
	private JTextField txtMaSanPham;
	private JTextField txtNCC;
	private ArrayList<SanPham> dsssp;
	private DefaultTableModel modelDSSanPham;
	private ArrayList<LoaiSanPham> dsLoai;
	private List<SanPham> dssptim;
	private JTextField txtTenSanPham;
	private SanPhamDAO sanphamDAO;

	private LoaiSanPhamDAO loaiDAO;
	private NhaCungCapDAO nCCDAO;
	private ArrayList<NhaCungCap> dsNCC;
	private boolean isTimKiem = false;
	private JComboBox<String> cboListNCC;
	private DefaultComboBoxModel<String> modelMaLoai;
	private DefaultComboBoxModel<String> modelNCC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SanPhamKhac_GUI frame = new SanPhamKhac_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public SanPhamKhac_GUI() throws SQLException {
		setTitle("Quản Lý Dụng Cụ Học Tập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		sanphamDAO = new SanPhamDAO();
		loaiDAO = new LoaiSanPhamDAO();
		nCCDAO = new NhaCungCapDAO();
		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QUẢN LÝ DỤNG CỤ HỌC TẬP");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		top.add(title);
		// title.setHorizontalAlignment(ABORT);
		out.add(top);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		out.add(bottom);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		bottom.add(contentPane, BorderLayout.CENTER);
		JPanel pnLeft = new JPanel();
		// pnLeft.setBorder();
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pnLeft.setBorder(compound);
		contentPane.add(pnLeft);

		JPanel pnThongTin = new JPanel();
		pnLeft.add(pnThongTin);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(35);
		pnThongTin.add(verticalStrut_2);

		JPanel pnTieuDe = new JPanel();
		pnThongTin.add(pnTieuDe);

		JLabel lblTieuDe = new JLabel("Thông tin sản phẩm");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTin.add(verticalStrut_1);

		JPanel pnMaSanPham = new JPanel();
		FlowLayout fl_pnMaSP = (FlowLayout) pnMaSanPham.getLayout();
		fl_pnMaSP.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaSanPham);

		JLabel lblMaSanPham = new JLabel("Mã Sản Phẩm:  ");
		lblMaSanPham.setPreferredSize(new Dimension(100, 14));
		pnMaSanPham.add(lblMaSanPham);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setEnabled(false);
		txtMaSanPham.setPreferredSize(new Dimension(7, 30));
		pnMaSanPham.add(txtMaSanPham);
		txtMaSanPham.setColumns(20);

		JPanel pnTenSanPham = new JPanel();
		FlowLayout fl_pnTenSP = (FlowLayout) pnTenSanPham.getLayout();
		fl_pnTenSP.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenSanPham);

		JLabel lblTenSanPham = new JLabel("Tên Sản Phẩm:");
		lblTenSanPham.setPreferredSize(new Dimension(100, 14));
		pnTenSanPham.add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setPreferredSize(new Dimension(7, 30));
		txtTenSanPham.setColumns(20);
		// PromptSupport.setPrompt("tên khách hàng", txtTenKh);
//		new Placeholder().placeholder(txtTenKh, "tên khách hàng");
		pnTenSanPham.add(txtTenSanPham);

		JPanel pnnhaCC = new JPanel();
		FlowLayout fl_pnNCC = (FlowLayout) pnnhaCC.getLayout();
		fl_pnNCC.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnnhaCC);

		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setPreferredSize(new Dimension(100, 14));
		pnnhaCC.add(lblNCC);
		
		modelNCC = new DefaultComboBoxModel<String>();
		cboListNCC = new JComboBox<String>(modelNCC);
		cboListNCC.setPreferredSize(new Dimension(202, 30));
		cboListNCC.addItem("");

		// PromptSupport.setPrompt("Example@gmail.com", txtEmail);

		pnnhaCC.add(cboListNCC);

		JPanel pnSoLuong = new JPanel();
		FlowLayout fl_pnSoLuong = (FlowLayout) pnSoLuong.getLayout();
		fl_pnSoLuong.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoLuong);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setPreferredSize(new Dimension(100, 14));
		pnSoLuong.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setPreferredSize(new Dimension(7, 30));
		txtSoLuong.setColumns(20);
		// PromptSupport.setPrompt("Example@gmail.com", txtEmail);

		pnSoLuong.add(txtSoLuong);

		JPanel pnGiaNhap = new JPanel();
		FlowLayout fl_pnGiaNhap = (FlowLayout) pnGiaNhap.getLayout();
		fl_pnGiaNhap.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnGiaNhap);

		JLabel lblGiaNhap = new JLabel("Giá nhập:");
		lblGiaNhap.setPreferredSize(new Dimension(100, 14));
		pnGiaNhap.add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setPreferredSize(new Dimension(7, 30));
		txtGiaNhap.setColumns(20);
		// PromptSupport.setPrompt("09xx xxx xxx ", txtSdt);
//		new Placeholder().placeholder(txtSdt, "09xx xxx xxx");
		pnGiaNhap.add(txtGiaNhap);

		JPanel pnGiaBan = new JPanel();
		FlowLayout fl_pnGiaBan = (FlowLayout) pnGiaBan.getLayout();
		fl_pnGiaBan.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnGiaBan);

		JLabel lblGiaBan = new JLabel("Giá bán:");
		lblGiaBan.setPreferredSize(new Dimension(100, 14));
		pnGiaBan.add(lblGiaBan);

		txtGiaBan = new JTextField();
		txtGiaBan.setPreferredSize(new Dimension(7, 30));
		txtGiaBan.setColumns(20);
		// PromptSupport.setPrompt("Số nhà, tên đường, tỉnh thành", txtDiaChi);

		pnGiaBan.add(txtGiaBan);

		JPanel pnLoai = new JPanel();
		FlowLayout fl_pnLoai = (FlowLayout) pnLoai.getLayout();
		fl_pnLoai.setAlignment(FlowLayout.LEFT);
		JLabel lblMaLoai = new JLabel("Loại sản phẩm:");
		lblMaLoai.setPreferredSize(new Dimension(100, 14));
		pnLoai.add(lblMaLoai);
		pnThongTin.add(pnLoai);
		
		modelMaLoai = new DefaultComboBoxModel<String>();
		cboListMaloai = new JComboBox<String>(modelMaLoai);

		cboListMaloai.setPreferredSize(new Dimension(202, 30));
//		cboListMaloai.setModel(new javax.swing.DefaultComboBoxModel<>());
		cboListMaloai.addItem("");
		// cboListMaloai.setSize(7, 30);
		// PromptSupport.setPrompt("Số nhà, tên đường, tỉnh thành", txtDiaChi);

		pnLoai.add(cboListMaloai);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnThongTin.add(verticalStrut);

		JPanel pnChucNang = new JPanel();
		pnThongTin.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(2, 0, 5, 5));

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.WHITE);
		btnThem.setPreferredSize(new Dimension(70, 35));
		btnThem.setIcon(new ImageIcon("data\\images\\blueAdd_16.png"));
		btnThem.setIconTextGap(10);
		out.getRootPane().setDefaultButton(btnThem);
		pnChucNang.add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(Color.WHITE);
		btnSua.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
		btnSua.setIconTextGap(30);
		pnChucNang.add(btnSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setIcon(new ImageIcon("data\\images\\trash.png"));
		btnXoa.setIconTextGap(10);
		pnChucNang.add(btnXoa);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setIcon(new ImageIcon("data\\images\\refresh.png"));
		btnLamMoi.setIconTextGap(10);
		pnChucNang.add(btnLamMoi);

		JPanel pnRight = new JPanel();
		contentPane.add(pnRight);
		pnRight.setLayout(new BorderLayout(0, 0));

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		pnRight.add(pnTimKiem, BorderLayout.NORTH);

		DefaultComboBoxModel cboLoaiTimKiem = new DefaultComboBoxModel<String>();
		JComboBox cmbLoaiTimKiem = new JComboBox(cboLoaiTimKiem);
		cmbLoaiTimKiem.setToolTipText("tìm kiếm theo");
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		cmbLoaiTimKiem.setPreferredSize(new Dimension(130, 22));
		pnTimKiem.add(cmbLoaiTimKiem);

		cboLoaiTimKiem.addElement((String) "Mã Sản Phẩm");
		cboLoaiTimKiem.addElement((String) "Tên Sản Phẩm");
		cboLoaiTimKiem.addElement((String) "Loại Sản Phẩm");
		cboLoaiTimKiem.addElement((String) "Nhà Cung Cấp");

		txtNhapLieu = new JTextField();
		txtNhapLieu.setPreferredSize(new Dimension(7, 25));
		pnTimKiem.add(txtNhapLieu);
		// PromptSupport.setPrompt("nhập liệu tìm kiếm", txtNhapLieu);
//		new Placeholder().placeholder(txtNhapLieu, "nhập liệu tìm kiếm");
		txtNhapLieu.setColumns(30);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setPreferredSize(new Dimension(130, 25));
		btnTimKiem.setBackground(Color.WHITE);
		btnTimKiem.setIcon(new ImageIcon("data\\images\\search_16.png"));
		pnTimKiem.add(btnTimKiem);

		JButton btnLamMoiDuLieu = new JButton("Làm mới dữ liệu");
		btnLamMoiDuLieu.setPreferredSize(new Dimension(150, 25));
		btnLamMoiDuLieu.setBackground(Color.WHITE);
		btnLamMoiDuLieu.setIcon(new ImageIcon("data\\images\\refresh.png"));
		pnTimKiem.add(btnLamMoiDuLieu);

		JPanel pnTableKh = new JPanel();
		pnRight.add(pnTableKh, BorderLayout.CENTER);
		pnTableKh.setLayout(new BorderLayout(0, 0));

		String[] cols_dssanpham = { "Mã sản phẩm", "Tên sản phẩm", "Nhà cung cấp", "Số lượng", "Giá nhập", "Giá bán",
				"Loại sản phẩm" };
		modelDSSanPham = new DefaultTableModel(cols_dssanpham, 0);
		table = new JTable(modelDSSanPham);
		JScrollPane scrTableSanPham = new JScrollPane(table);
		scrTableSanPham.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableSanPham.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableSanPham);

		// modelDSKH.addRow(new Object[]{"1", "Tran Van Nhan", "0987654321",
		// "tranvannhan@gmail.com", "Thủ Đức, Hồ Chí Minh"});
		try {
			renderData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.addMouseListener(this);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTenSanPham.getText().equals("") || cboListNCC.getSelectedItem().toString().equals("")
						|| txtSoLuong.getText().equals("") || txtGiaNhap.getText().equals("")
						|| txtGiaBan.getText().equals("") || cboListMaloai.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(out, "Thiếu dữ liệu đầu vào");
				} else if (ktdulieu()) {
					int masp = sanphamDAO.getSanPhamCuoiCung().getMaSp() + 1;
					String tensp = txtTenSanPham.getText().trim();
					String nc = cboListNCC.getSelectedItem().toString();
					NhaCungCap ncc = nCCDAO.getNCCByTenNCC(nc);

					String soluong = txtSoLuong.getText().trim();
					String giaNhap = txtGiaNhap.getText().trim();
					String giasp = txtGiaBan.getText().trim();
					String loaispk = cboListMaloai.getSelectedItem().toString();
					LoaiSanPham loaisp = loaiDAO.getLoaiByTenLoai(loaispk);

					SanPham sp = new SanPham(masp, tensp, Integer.parseInt(soluong), Double.parseDouble(giaNhap),
							Double.parseDouble(giasp), loaisp, ncc);
					if (timma(sp.getMaSp())) {
						JOptionPane.showMessageDialog(out, "Mã đã tồn tại");
					} else {
						boolean result = sanphamDAO.create(sp);
						if (result) {
							modelDSSanPham.addRow(new Object[] { sp.getMaSp(), sp.getTenSp(),
									sp.getNhaCungCap().getTenNCC(), sp.getSoLuong(),
									Currency.format((int) sp.getGiaNhap()),
									Currency.format((int) sp.getGiaSp()), sp.getLoaiSanPham().getTenLoai() });
							JOptionPane.showMessageDialog(out, "Thêm thành công");

						} else {
							JOptionPane.showMessageDialog(out, "Thêm thất bại");
						}
					}
				}
			}

		});

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTenSanPham.getText().equals("") || cboListNCC.getSelectedItem().toString().equals("")
						|| txtSoLuong.getText().equals("") || txtGiaNhap.getText().equals("")
						|| txtGiaBan.getText().equals("") || cboListMaloai.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(out, "Thiếu dữ liệu đầu vào");
				} else if (ktdulieu()) {
					SanPham sp = getSelectedDataTable();
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "Bạn chưa chọn dòng sản phẩm cần sửa", "Cảnh báo",
								JOptionPane.WARNING_MESSAGE);
					} else {
						boolean result = sanphamDAO.capNhat(sp);
						if (result == true) {

							modelDSSanPham.setValueAt(sp.getMaSp(), row, 0);
							modelDSSanPham.setValueAt(sp.getTenSp(), row, 1);
							modelDSSanPham.setValueAt(sp.getNhaCungCap().getTenNCC(), row, 2);
							modelDSSanPham.setValueAt(sp.getSoLuong(), row, 3);
							modelDSSanPham.setValueAt(Currency.format((int) sp.getGiaNhap()), row, 4);
							modelDSSanPham.setValueAt(Currency.format((int) sp.getGiaSp()), row, 5);
							modelDSSanPham.setValueAt(sp.getLoaiSanPham().getTenLoai(), row, 6);
							JOptionPane.showMessageDialog(out, "Cập nhập sản phẩm thành công");
							modelDSSanPham.fireTableDataChanged();
							sanphamDAO.getListSanPhamKhac();
						} else {
							JOptionPane.showMessageDialog(out, "Lỗi: Cập nhật sản phẩm thất bại");
						}
					}

				}
			}

		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					SanPham sp = getSelectedDataTable();
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "Bạn chưa chọn sẩn phẩm cần xoá !!!");
					} else {
						int select;
						select = JOptionPane.showConfirmDialog(out, "Bạn có muốn xoá sản phẩm đã chọn ?", "Cảnh báo",
								JOptionPane.YES_NO_OPTION);
						if (select == JOptionPane.YES_OPTION) {
							boolean result = sanphamDAO.delete(sp);
							if (result) {
								modelDSSanPham.removeRow(row);
								JOptionPane.showMessageDialog(out, "Xóa thành công");
								txtMaSanPham.setText("");
								txtTenSanPham.setText("");
								cboListNCC.setSelectedItem("");
								txtSoLuong.setText("");
								txtGiaNhap.setText("");
								txtGiaBan.setText("");
								cboListMaloai.setSelectedItem("");
							} else {
								JOptionPane.showMessageDialog(out, "Xóa thất bại");
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(out, "Lỗi! Xóa sản phẩm thất bại");

				}
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaSanPham.setText("");
				txtTenSanPham.setText("");
				cboListNCC.setSelectedItem("");
				txtSoLuong.setText("");
				txtGiaNhap.setText("");
				txtGiaBan.setText("");
				cboListMaloai.setSelectedItem("");
				txtNhapLieu.setText("");
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtNhapLieu.getText().equals("")) {
					JOptionPane.showMessageDialog(out, "Cần nhập dữ liệu sản phẩm cần tìm", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						String key = "maSP";
						if (cboLoaiTimKiem.getSelectedItem().toString().equals("Mã Sản Phẩm")) {
							key = "SanPham.MaSP";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Tên Sản Phẩm")) {
							key = "SanPham.TenSP";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Loại Sản Phẩm")) {
							key = "LoaiSanPham.TenLoai";

						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Nhà Cung Cấp")) {
							key = "NhaCungCap.TenNCC";
						}
						dssptim = sanphamDAO.timKiemSanPhamKhac(key, txtNhapLieu.getText());

						if (dssptim.size() == 0) {
							JOptionPane.showMessageDialog(out, "Không tìm thấy dữ liệu theo yêu cầu cần tìm");
							table.clearSelection();
							modelDSSanPham.getDataVector().removeAllElements();
							table.revalidate();
							table.repaint();
							isTimKiem = false;
						} else {
							renderDataTimKiem();
							isTimKiem = true;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnLamMoiDuLieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					table.clearSelection();

					modelDSSanPham.getDataVector().removeAllElements();
					renderData();
					isTimKiem = false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	private void loadCboMaLoai() throws SQLException {
		modelMaLoai.removeAllElements();
		dsLoai = new LoaiSanPhamDAO().getDanhSachLoaiSanPhamKhac();
		for (LoaiSanPham loai : dsLoai) {
			String ma = loai.getTenLoai();
			modelMaLoai.addElement(String.valueOf(ma));
		}
	}

	private void loadCboNCC() throws SQLException {
		modelNCC.removeAllElements();
		dsNCC = nCCDAO.getListNhaCungCap();
		for (NhaCungCap ncc : dsNCC) {
			String ma = ncc.getTenNCC();
			modelNCC.addElement(String.valueOf(ma));
		}
	}

//
	private SanPham getSelectedDataTable() {
		String masp = txtMaSanPham.getText().trim();
		String tensp = txtTenSanPham.getText().trim();
		String nc = cboListNCC.getSelectedItem().toString();
		NhaCungCap ncc = nCCDAO.getNCCByTenNCC(nc);

		String soluong = txtSoLuong.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String giasp = txtGiaBan.getText().trim();
		String loaispk = cboListMaloai.getSelectedItem().toString();
		LoaiSanPham loaisp = loaiDAO.getLoaiByTenLoai(loaispk);

		SanPham sp = new SanPham(Integer.parseInt(masp), tensp, Integer.parseInt(soluong), Double.parseDouble(giaNhap),
				Double.parseDouble(giasp), loaisp, ncc);
		return sp;
	}

	private boolean ktdulieu() {
		String tenSP = txtTenSanPham.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String giaBan = txtGiaBan.getText().trim();
		if (tenSP.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
			txtTenSanPham.selectAll();
			txtTenSanPham.requestFocus();
			return false;
		}
		if (tenSP.length()<2) {
			JOptionPane.showMessageDialog(this, "Tên phải ít nhất là 2 ký tự");
			txtTenSanPham.selectAll();
			txtTenSanPham.requestFocus();
			return false;
		}
		if (!soLuong.matches("^[0-9]{1,}$")) {
			JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
			return false;
		}
		if (!giaNhap.matches("^[0-9]{1,}$")) {
			JOptionPane.showMessageDialog(this, "Giá nhập không hợp lệ");
			txtGiaNhap.selectAll();
			txtGiaNhap.requestFocus();
			return false;
		}
		if (!giaBan.matches("^[0-9]{1,}$")) {
			JOptionPane.showMessageDialog(this, "Giá bán không hợp lệ");
			txtGiaBan.selectAll();
			txtGiaBan.requestFocus();
			return false;
		}
		double nhap = Double.parseDouble(giaNhap);
		double ban = Double.parseDouble(giaBan);
		
		if(nhap > ban) {
			JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn giá nhập");
			txtGiaBan.selectAll();
			txtGiaBan.requestFocus();
			return false;
		}
		
		return true;

	}

	public boolean timma(int ma) {
		int temp;
		for (int i = 0; i < table.getRowCount(); i++) {
			temp = (int) table.getValueAt(i, 0);
			if (temp == ma) {
				table.setRowSelectionInterval(i, i);
				// scroll đến dòng được chọn
				Rectangle cellRect = table.getCellRect(i, 0, true);
				table.scrollRectToVisible(cellRect);
				return true;
			}
		}
		return false;
	}

	public void renderData() throws SQLException {
		// modelDSSach.getDataVector().removeAllElements();
		table.clearSelection();

		modelDSSanPham.getDataVector().removeAllElements();
		dsssp = new SanPhamDAO().getListSanPhamKhac();

		dsssp.forEach(sp -> {
			modelDSSanPham.addRow(new Object[] { sp.getMaSp(), sp.getTenSp(), sp.getNhaCungCap().getTenNCC(),
					sp.getSoLuong(), Currency.format((int) sp.getGiaNhap()),
					Currency.format((int) sp.getGiaSp()), sp.getLoaiSanPham().getTenLoai() });
		});
		
		loadCboMaLoai();
		loadCboNCC();
	}

	public void renderDataTimKiem() throws SQLException {
		table.clearSelection();

		modelDSSanPham.getDataVector().removeAllElements();

		dssptim.forEach(sp -> {
			modelDSSanPham.addRow(new Object[] { sp.getMaSp(), sp.getTenSp(), sp.getNhaCungCap().getTenNCC(),
					sp.getSoLuong(), Currency.format((int) sp.getGiaNhap()),
					Currency.format((int) sp.getGiaSp()), sp.getLoaiSanPham().getTenLoai() });
		});

		table.revalidate();
		table.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaSanPham.setText(modelDSSanPham.getValueAt(row, 0).toString());
		txtTenSanPham.setText(modelDSSanPham.getValueAt(row, 1).toString());
		cboListNCC.setSelectedItem(modelDSSanPham.getValueAt(row, 2).toString());
		txtSoLuong.setText(modelDSSanPham.getValueAt(row, 3).toString());
		txtGiaNhap.setText(modelDSSanPham.getValueAt(row, 4).toString().replaceAll("[^\\d]", ""));
		txtGiaBan.setText(modelDSSanPham.getValueAt(row, 5).toString().replaceAll("[^\\d]", ""));
		cboListMaloai.setSelectedItem(modelDSSanPham.getValueAt(row, 6).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
