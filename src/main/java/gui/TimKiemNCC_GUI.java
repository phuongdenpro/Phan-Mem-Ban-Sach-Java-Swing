package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import entity.KhachHang;
import entity.NhaCungCap;

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

public class TimKiemNCC_GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtSdt;
	private JCheckBox chkSdt, chkDiaChi;
	private JButton btnTimKiem, btnRefresh;
	private DefaultTableModel modelKhachHang;
	private JTable tblKetQua;
	private ArrayList<KhachHang> dskh;
	private JCheckBox chkMaNCC;
	private JTextField txtTenNCC;
	private JCheckBox chkTenNCC;
	private JTextField txtDiaChi;
	private DefaultTableModel modelNCC;
	private NhaCungCapDAO nhaCCDAO;
	private ArrayList<NhaCungCap> dsncc;
	private List<NhaCungCap> dsncctim;
	private JTextField txtMaNCC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemNCC_GUI frame = new TimKiemNCC_GUI();
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
	public TimKiemNCC_GUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		nhaCCDAO = new NhaCungCapDAO();
		JPanel pnTieuDe = new JPanel();
		contentPane.add(pnTieuDe, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("Tìm kiếm nhà cung cấp");
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

		JPanel pnMaNCC = new JPanel();
		FlowLayout fl_pnMaNCC = (FlowLayout) pnMaNCC.getLayout();
		fl_pnMaNCC.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaNCC);

		JLabel lblMaNCC = new JLabel("Mã NCC: ");
		lblMaNCC.setPreferredSize(new Dimension(80, 14));
		pnMaNCC.add(lblMaNCC);

		txtMaNCC = new JTextField();
		txtMaNCC.setPreferredSize(new Dimension(200, 20));
		txtMaNCC.setColumns(20);
		pnMaNCC.add(txtMaNCC);
		chkMaNCC = new JCheckBox("");
		pnMaNCC.add(chkMaNCC);

		JPanel pnTenNCC = new JPanel();
		FlowLayout fl_pnTenNCC = (FlowLayout) pnTenNCC.getLayout();
		fl_pnTenNCC.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenNCC);

		JLabel lblTenNCC = new JLabel("Tên NCC:");
		lblTenNCC.setPreferredSize(new Dimension(80, 14));
		pnTenNCC.add(lblTenNCC);

		txtTenNCC = new JTextField();
		txtTenNCC.setPreferredSize(new Dimension(200, 20));
		txtTenNCC.setColumns(20);
		pnTenNCC.add(txtTenNCC);
		chkTenNCC = new JCheckBox("");
		pnTenNCC.add(chkTenNCC);

		JPanel pnDaiChi = new JPanel();
		FlowLayout fl_pnDaiChi = (FlowLayout) pnDaiChi.getLayout();
		fl_pnDaiChi.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnDaiChi);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setPreferredSize(new Dimension(80, 14));
		pnDaiChi.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(200, 20));
		pnDaiChi.add(txtDiaChi);
		txtDiaChi.setColumns(20);

		chkDiaChi = new JCheckBox("");
		pnDaiChi.add(chkDiaChi);

		JPanel pnSdt = new JPanel();
		FlowLayout fl_pnNXB = (FlowLayout) pnSdt.getLayout();
		fl_pnNXB.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSdt);

		JLabel lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setPreferredSize(new Dimension(80, 14));
		pnSdt.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(200, 20));
		pnSdt.add(txtSdt);
		txtSdt.setColumns(20);

		chkSdt = new JCheckBox("");
		pnSdt.add(chkSdt);

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

		String[] cols = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại" };
		modelNCC = new DefaultTableModel(cols, 0);
		pnRightBottom.setLayout(new BorderLayout(0, 0));
		tblKetQua = new JTable(modelNCC);
		JScrollPane srcTblKetQua = new JScrollPane(tblKetQua);
		pnRightBottom.add(srcTblKetQua);
		renderData();
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaNCC.setText("");
				txtTenNCC.setText("");
				txtDiaChi.setText("");
				txtSdt.setText("");
				try {
					tblKetQua.clearSelection();

					modelNCC.getDataVector().removeAllElements();
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
				if (txtMaNCC.getText().equals("") && txtTenNCC.getText().equals("") && txtDiaChi.getText().equals("")
						&& txtSdt.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Lỗi, chưa nhập dữ liệu tìm kiếm");

				} else {
					
					String where = "";
					if (chkMaNCC.isSelected()) {
						where += "MaNCC like N'" + txtMaNCC.getText() + "' and ";
					} else {
						where += "MaNCC like N'%" + txtMaNCC.getText() + "%' and ";
					}

					if (chkTenNCC.isSelected()) {
						where += "TenNCC like N'" + txtTenNCC.getText() + "' and ";
					} else {
						where += "TenNCC like N'%" + txtTenNCC.getText() + "%' and ";
					}

					if (chkDiaChi.isSelected()) {
						where += "DiaChi like N'" + txtDiaChi.getText() + "' and ";
					} else {
						where += "DiaChi like N'%" + txtDiaChi.getText() + "%' and ";
					}
					if (chkSdt.isSelected()) {
						where += "SoDienThoai like N'" + txtSdt.getText() + "'";
					} else {
						where += "SoDienThoai like N'%" + txtSdt.getText() + "%'";
					}

					System.out.println(where);
					dsncctim = nhaCCDAO.timKiemNCC2(where);
					if (dsncctim.size() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Không có nhà cung cấp phù hợp");
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

	}

	public void renderData() throws SQLException {
		tblKetQua.clearSelection();

		modelNCC.getDataVector().removeAllElements();
		dsncc = nhaCCDAO.getListNhaCungCap();

		dsncc.forEach(ncc -> {
			modelNCC.addRow(new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai() });
		});
	}

	public void renderDataTimKiem() throws SQLException {
		tblKetQua.clearSelection();

		modelNCC.getDataVector().removeAllElements();

		dsncctim.forEach(ncc -> {
			modelNCC.addRow(new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai() });
		});

		tblKetQua.revalidate();
		tblKetQua.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
