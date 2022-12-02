package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDAO;
import dao.LoaiSanPhamDAO;
import entity.KhachHang;
import entity.LoaiSanPham;

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

public class TimKiemDanhMucSanPham_GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnTimKiem, btnRefresh;
	private JTable tblKetQua;
	private ArrayList<KhachHang> dskh;
	private DefaultTableModel modelDanhmuc;
	private JTextField txtMaLoai;
	private JCheckBox chkMaLoai;
	private JTextField txtTenLoai;
	private JCheckBox chkTenLoai;
	private ArrayList<LoaiSanPham> dsloai;
	private List<LoaiSanPham> dsloaitim;
	private LoaiSanPhamDAO loaiDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemDanhMucSanPham_GUI frame = new TimKiemDanhMucSanPham_GUI();
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
	public TimKiemDanhMucSanPham_GUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		loaiDAO = new LoaiSanPhamDAO();
		JPanel pnTieuDe = new JPanel();
		contentPane.add(pnTieuDe, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("Tìm kiếm danh mục sản phẩm");
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

	

		JPanel pnMaLoai = new JPanel();
		FlowLayout fl_pnMaLoai = (FlowLayout) pnMaLoai.getLayout();
		fl_pnMaLoai.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaLoai);

		JLabel lblMaLoai = new JLabel("Mã loại:");
		lblMaLoai.setPreferredSize(new Dimension(70, 14));
		pnMaLoai.add(lblMaLoai);

		txtMaLoai = new JTextField();
		txtMaLoai.setPreferredSize(new Dimension(200, 20));
		txtMaLoai.setColumns(20);
		pnMaLoai.add(txtMaLoai);
		chkMaLoai = new JCheckBox("");
		pnMaLoai.add(chkMaLoai);

		JPanel pnTenLoai = new JPanel();
		FlowLayout fl_pnTenLoai = (FlowLayout) pnTenLoai.getLayout();
		fl_pnTenLoai.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenLoai);

		JLabel lblTenLoai = new JLabel("Tên loại:");
		lblTenLoai.setPreferredSize(new Dimension(70, 14));
		pnTenLoai.add(lblTenLoai);

		txtTenLoai = new JTextField();
		txtTenLoai.setPreferredSize(new Dimension(200, 20));
		pnTenLoai.add(txtTenLoai);
		txtTenLoai.setColumns(20);

		chkTenLoai = new JCheckBox("");
		pnTenLoai.add(chkTenLoai);		

		JPanel pnTim = new JPanel();
		pnTim.setLayout(new FlowLayout());
		pnThongTin.add(pnTim);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(Color.WHITE);
		btnRefresh = new JButton("Làm mới");
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

		String[] cols = { "Mã Loại", "Tên Loại" };
		modelDanhmuc = new DefaultTableModel(cols, 0);
		pnRightBottom.setLayout(new BorderLayout(0, 0));
		tblKetQua = new JTable(modelDanhmuc);
		JScrollPane srcTblKetQua = new JScrollPane(tblKetQua);
		pnRightBottom.add(srcTblKetQua);
		renderData();
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtMaLoai.getText().equals("") && txtTenLoai.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Lỗi, chưa nhập dữ liệu tìm kiếm");

				} else {
					
					String where = "";
					if (chkMaLoai.isSelected()) {
						where += "MaLoai like N'" + txtMaLoai.getText() + "' and ";
					} else {
						where += "MaLoai like N'%" + txtMaLoai.getText() + "%' and ";
					}

					if (chkTenLoai.isSelected()) {
						where += "TenLoai like N'" + txtTenLoai.getText() + "'";
					} else {
						where += "TenLoai like N'%" + txtTenLoai.getText() + "%'";
					}
					System.out.println(where);
					dsloaitim = loaiDAO.timKiem2(where);
					if (dsloaitim.size() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Không có danh mục phù hợp");
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
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaLoai.setText("");
				txtTenLoai.setText("");
				try {
					tblKetQua.clearSelection();

					modelDanhmuc.getDataVector().removeAllElements();
					renderData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		
	}

	

	public void renderData() throws SQLException {
		// modelDSSach.getDataVector().removeAllElements();
		tblKetQua.clearSelection();

		modelDanhmuc.getDataVector().removeAllElements();
		dsloai = new LoaiSanPhamDAO().getDanhSachLoaiSanPham();

//		String stt = table.getValueAt(1, 0).toString();

		dsloai.forEach(loai -> {
			modelDanhmuc.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
		});

	}
	public void renderDataTimKiem() throws SQLException {
		tblKetQua.clearSelection();

		modelDanhmuc.getDataVector().removeAllElements();

		dsloaitim.forEach(loai -> {
			modelDanhmuc.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
		});

		tblKetQua.revalidate();
		tblKetQua.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
