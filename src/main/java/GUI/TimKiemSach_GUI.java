package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.LoaiSanPhamDAO;
import dao.NhaCungCapDAO;
import dao.SanPhamDAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;
import util.Currency;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimKiemSach_GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnTimKiem, btnRefresh;
	private JTable tblKetQua;

	private JCheckBox chkLoaiSach;
	private JTextField txtMaSach;
	private JCheckBox chkMaSach;
	private JTextField txtTieu;
	private JCheckBox chkTieu;
	private JCheckBox chkNXB;
	private DefaultTableModel modelSach;
	private SanPhamDAO sachDAO;
	private LoaiSanPhamDAO loaiDAO;
	private NhaCungCapDAO nhaCCDAO;
	private ArrayList<SanPham> dssach;
	private ArrayList<LoaiSanPham> dsLoai;
	private ArrayList<NhaCungCap> dsNCC;
	private List<SanPham> dssachtim;
	private JComboBox comboBoxNXB;
	private JComboBox comboBoxLoai;
	private JCheckBox chkTacGia;
	private JTextField txtNamXB;
	private JCheckBox chkNamXB;
	private JComboBox comboBoxTacGia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemSach_GUI frame = new TimKiemSach_GUI();
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
	public TimKiemSach_GUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		sachDAO = new SanPhamDAO();
		loaiDAO = new LoaiSanPhamDAO();
		nhaCCDAO = new NhaCungCapDAO();
		JPanel pnTieuDe = new JPanel();
		contentPane.add(pnTieuDe, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("Tìm kiếm sách");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pnTieuDe.add(lblTieuDe);

		JPanel pnLeft = new JPanel();
		pnLeft.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		contentPane.add(pnLeft, BorderLayout.WEST);
		pnLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel pnThongTin = new JPanel();
		pnLeft.add(pnThongTin);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));

		JPanel pnLblThongTin = new JPanel();
		pnLblThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnThongTin.add(pnLblThongTin);

		JLabel lblThongTin = new JLabel("Thông tin tìm kiếm");
		lblThongTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnLblThongTin.add(lblThongTin);

		JPanel pnChuThich = new JPanel();
		FlowLayout fl_pnChuThich = (FlowLayout) pnChuThich.getLayout();
		fl_pnChuThich.setAlignment(FlowLayout.RIGHT);
		pnThongTin.add(pnChuThich);

		JLabel lblChuThich = new JLabel("Tìm kiếm chính xác");
		pnChuThich.add(lblChuThich);

		JPanel pnLoaiSach = new JPanel();
		FlowLayout fl_pnLoaiSach = (FlowLayout) pnLoaiSach.getLayout();
		fl_pnLoaiSach.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnLoaiSach);

		JLabel lblLoaiSach = new JLabel("Loại sách: ");
		lblLoaiSach.setPreferredSize(new Dimension(80, 14));
		pnLoaiSach.add(lblLoaiSach);

		comboBoxLoai = new JComboBox();
		comboBoxLoai.setPreferredSize(new Dimension(222, 20));
		comboBoxLoai.addItem("");
		pnLoaiSach.add(comboBoxLoai);
		chkLoaiSach = new JCheckBox("");
		pnLoaiSach.add(chkLoaiSach);

		JPanel pnMaSach = new JPanel();
		FlowLayout fl_pnMaSach = (FlowLayout) pnMaSach.getLayout();
		fl_pnMaSach.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaSach);

		JLabel lblMaSach = new JLabel("Mã sách:");
		lblMaSach.setPreferredSize(new Dimension(80, 14));
		pnMaSach.add(lblMaSach);

		txtMaSach = new JTextField();
		txtMaSach.setColumns(20);
		pnMaSach.add(txtMaSach);
		chkMaSach = new JCheckBox("");
		pnMaSach.add(chkMaSach);

		JPanel pnTieu = new JPanel();
		FlowLayout fl_pnTieuDe = (FlowLayout) pnTieu.getLayout();
		fl_pnTieuDe.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTieu);

		JLabel lblTieu = new JLabel("Tiêu đề:");
		lblTieu.setPreferredSize(new Dimension(80, 14));
		pnTieu.add(lblTieu);

		txtTieu = new JTextField();
		pnTieu.add(txtTieu);
		txtTieu.setColumns(20);

		chkTieu = new JCheckBox("");
		pnTieu.add(chkTieu);

		JPanel pnTacGia = new JPanel();
		FlowLayout fl_pnTacGia = (FlowLayout) pnTacGia.getLayout();
		fl_pnTacGia.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTacGia);

		JLabel lblTacGia = new JLabel("Tác giả:");
		lblTacGia.setPreferredSize(new Dimension(80, 14));
		pnTacGia.add(lblTacGia);

		comboBoxTacGia = new JComboBox();
		comboBoxTacGia.setPreferredSize(new Dimension(222, 20));
		comboBoxTacGia.addItem("");
		pnTacGia.add(comboBoxTacGia);
		

		chkTacGia = new JCheckBox("");
		pnTacGia.add(chkTacGia);

		JPanel pnNamXB = new JPanel();
		FlowLayout fl_pnNamXB = (FlowLayout) pnNamXB.getLayout();
		fl_pnNamXB.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnNamXB);

		JLabel lblNamXB = new JLabel("Năm xuất bản:");
		lblNamXB.setPreferredSize(new Dimension(80, 14));
		pnNamXB.add(lblNamXB);

		txtNamXB = new JTextField();
		pnNamXB.add(txtNamXB);
		txtNamXB.setColumns(20);

		chkNamXB = new JCheckBox("");
		pnNamXB.add(chkNamXB);

		JPanel pnNXB = new JPanel();
		FlowLayout fl_pnNXB = (FlowLayout) pnNXB.getLayout();
		fl_pnNXB.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnNXB);

		JLabel lblNXB = new JLabel("Nhà xuất bản:");
		lblNXB.setPreferredSize(new Dimension(80, 14));
		pnNXB.add(lblNXB);

		comboBoxNXB = new JComboBox();
		comboBoxNXB.setPreferredSize(new Dimension(222, 20));
		comboBoxNXB.addItem("");
		pnNXB.add(comboBoxNXB);
		chkNXB = new JCheckBox("");
		pnNXB.add(chkNXB);

		JPanel pnTim = new JPanel();
		pnTim.setLayout(new FlowLayout());
		pnThongTin.add(pnTim);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(Color.WHITE);
		btnRefresh = new JButton("Làm mới dữ liệu");
		btnRefresh.setBackground(Color.WHITE);

		pnTim.add(btnTimKiem);
		pnTim.add(btnRefresh);

		JPanel pnRight = new JPanel();
		pnRight.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		contentPane.add(pnRight, BorderLayout.CENTER);
		pnRight.setLayout(new BorderLayout(0, 0));

		JPanel pnRightTop = new JPanel();
		pnRight.add(pnRightTop, BorderLayout.NORTH);

		JLabel lblKqTim = new JLabel("Kết quả tìm kiếm");
		lblKqTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnRightTop.add(lblKqTim);

		JPanel pnRightBottom = new JPanel();
		pnRightBottom.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		pnRight.add(pnRightBottom);

		String[] cols = { "Mã sách", "Tên sách", "Tác giả", "Số trang", "Nhà xuất bản", "Năm xuất bản", "Số lượng",
				"Giá nhập", "Giá bán", "Loại Sách" };
		modelSach = new DefaultTableModel(cols, 0);
		pnRightBottom.setLayout(new BorderLayout(0, 0));
		tblKetQua = new JTable(modelSach);
		JScrollPane srcTblKetQua = new JScrollPane(tblKetQua);
		pnRightBottom.add(srcTblKetQua);

		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaSach.setText("");
				txtTieu.setText("");
				comboBoxTacGia.setSelectedItem("");
				txtNamXB.setText("");
				comboBoxLoai.setSelectedItem("");
				comboBoxNXB.setSelectedItem("");
				try {
					tblKetQua.clearSelection();

					modelSach.getDataVector().removeAllElements();
					renderData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxLoai.getSelectedItem().toString().equals("") && txtMaSach.getText().equals("")
						&& txtTieu.getText().equals("") && comboBoxNXB.getSelectedItem().toString().equals("")
						&& comboBoxTacGia.getSelectedItem().toString().equals("") && txtNamXB.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Lỗi, chưa nhập dữ liệu tìm kiếm");

				} else {
					String where = "";
					if (chkLoaiSach.isSelected()) {
						where += "TenLoai like N'" + comboBoxLoai.getSelectedItem().toString() + "' and ";
					} else {
						where += "TenLoai like N'%" + comboBoxLoai.getSelectedItem().toString() + "%' and ";
					}

					if (chkMaSach.isSelected()) {
						where += "MaSP like N'" + txtMaSach.getText() + "' and ";
					} else {
						where += "MaSP like N'%" + txtMaSach.getText() + "%' and ";
					}

					if (chkTieu.isSelected()) {
						where += "TenSP like N'" + txtTieu.getText() + "' and ";
					} else {
						where += "TenSP like N'%" + txtTieu.getText() + "%' and ";
					}

					if (chkTacGia.isSelected()) {
						where += "TacGia like N'" + comboBoxTacGia.getSelectedItem().toString() + "' and ";
					} else {
						where += "TacGia like N'%" + comboBoxTacGia.getSelectedItem().toString() + "%' and ";
					}
					if (chkNamXB.isSelected()) {
						where += "namXuatBan like N'" + txtNamXB.getText() + "' and ";
					} else {
						where += "namXuatBan like N'%" + txtNamXB.getText() + "%' and ";
					}
					if (chkNXB.isSelected()) {
						where += "TenNCC like N'" + comboBoxNXB.getSelectedItem().toString() + "'";
					} else {
						where += "TenNCC like N'%" + comboBoxNXB.getSelectedItem().toString() + "%'";
					}

					System.out.println(where);

					dssachtim = sachDAO.timKiemSach2(where);
					if (dssachtim.size() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Không có sản phẩm phù hợp");
						return;
					} else {
						try {
							renderDataTimKiem();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
		try {
			renderData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loadCboNCC();
		loadCboMaLoai();
		loadCboTacGia();

	}

	private void addEvents() {
		// TODO Auto-generated method stub

	}

	private void loadCboMaLoai() throws SQLException {
		dsLoai = loaiDAO.getDanhSachLoaiSach();
		for (LoaiSanPham loai : dsLoai) {
			String ma = loai.getTenLoai();
			comboBoxLoai.addItem(String.valueOf(ma));
		}
	}

	private void loadCboNCC() throws SQLException {
		dsNCC = nhaCCDAO.getListNhaCungCapSach();
		for (NhaCungCap ncc : dsNCC) {
			String ma = ncc.getTenNCC();
			comboBoxNXB.addItem(String.valueOf(ma));
		}

	}
	
	public boolean isExisted(Object obj, JComboBox jbox) {
	    for (int i = 0, c = jbox.getItemCount(); i < c; ++i)
	    if (obj.equals(jbox.getItemAt(i))) 
	    		return true;
	    return false;
	}
	
	private void loadCboTacGia() throws SQLException {
		dssach = sachDAO.getListSach();
		for (SanPham sach : dssach) {
			if(!isExisted(sach.getTacGia(), comboBoxTacGia)){
				comboBoxTacGia.addItem(String.valueOf(sach.getTacGia()));
		}}

	}
	
	

	public void renderData() throws SQLException {
		tblKetQua.clearSelection();

		modelSach.getDataVector().removeAllElements();

		dssach = sachDAO.getListSach();

		dssach.forEach(sach -> {
			modelSach.addRow(new Object[] { sach.getMaSp(), sach.getTenSp(), sach.getTacGia(), sach.getSoTrang(),
					sach.getNhaCungCap().getTenNCC(), sach.getNamXuatBan(), sach.getSoLuong(),
					Currency.format((int) sach.getGiaNhap()).toString(), Currency.format((int) sach.getGiaSp()).toString(),
					sach.getLoaiSanPham().getTenLoai() });
		});
	}

	public void renderDataTimKiem() throws SQLException {
		tblKetQua.clearSelection();

		modelSach.getDataVector().removeAllElements();

		dssachtim.forEach(sach -> {
			modelSach.addRow(new Object[] { sach.getMaSp(), sach.getTenSp(), sach.getTacGia(), sach.getSoTrang(),
					sach.getNhaCungCap().getTenNCC(), sach.getNamXuatBan(), sach.getSoLuong(),
					Currency.format((int) sach.getGiaNhap()).toString(), Currency.format((int) sach.getGiaSp()).toString(),
					sach.getLoaiSanPham().getTenLoai() });
		});

		tblKetQua.revalidate();
		tblKetQua.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
