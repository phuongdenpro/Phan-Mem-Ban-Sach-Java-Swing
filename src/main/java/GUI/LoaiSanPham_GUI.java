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
import dao.SanPhamDAO;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;
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

public class LoaiSanPham_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable table;

	private JTextField txtTenLoai;
	private JTextField txtMaLoai;
	private ArrayList<LoaiSanPham> dsloai;
	private DefaultTableModel modelDSLoai;
	private LoaiSanPhamDAO loaiDAO;
	private List<LoaiSanPham> dsloaitim;
	private boolean isTimKiem = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoaiSanPham_GUI frame = new LoaiSanPham_GUI();
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
	public LoaiSanPham_GUI() throws SQLException {
		setTitle("Quản Lý Danh Mục");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		loaiDAO = new LoaiSanPhamDAO();
		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QUẢN LÝ DANH MỤC");
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

		JLabel lblTieuDe = new JLabel("Thông tin danh mục");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTin.add(verticalStrut_1);

		JPanel pnMaLoai = new JPanel();
		FlowLayout fl_pnMaLoai = (FlowLayout) pnMaLoai.getLayout();
		fl_pnMaLoai.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaLoai);

		JLabel lblMaLoai = new JLabel("Mã Loại:             ");
		lblMaLoai.setPreferredSize(new Dimension(100, 14));
		pnMaLoai.add(lblMaLoai);

		txtMaLoai = new JTextField();
		txtMaLoai.setEnabled(false);
		txtMaLoai.setPreferredSize(new Dimension(7, 30));
		pnMaLoai.add(txtMaLoai);
		txtMaLoai.setColumns(20);

		JPanel pnTenLoai = new JPanel();
		FlowLayout fl_pnTenLoai = (FlowLayout) pnTenLoai.getLayout();
		fl_pnTenLoai.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenLoai);

		JLabel lblTenLoai = new JLabel("Tên Loại:");
		lblTenLoai.setPreferredSize(new Dimension(100, 14));
		pnTenLoai.add(lblTenLoai);

		txtTenLoai = new JTextField();
		txtTenLoai.setPreferredSize(new Dimension(7, 30));
		txtTenLoai.setColumns(20);
		pnTenLoai.add(txtTenLoai);

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
		cboLoaiTimKiem.addElement((String) "Mã Loại");
		cboLoaiTimKiem.addElement((String) "Tên Loại");

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

		JPanel pnTableLoai = new JPanel();
		pnRight.add(pnTableLoai, BorderLayout.CENTER);
		pnTableLoai.setLayout(new BorderLayout(0, 0));

		String[] cols_dsLoai = { "Mã Loại", "Tên Loại" };
		modelDSLoai = new DefaultTableModel(cols_dsLoai, 0);
		table = new JTable(modelDSLoai);
		JScrollPane scrTableLoai = new JScrollPane(table);
		scrTableLoai.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableLoai.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableLoai.add(scrTableLoai);

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
				if (txtTenLoai.getText().equals("")) {
					JOptionPane.showMessageDialog(out, "Thiếu dữ liệu đầu vào");
				} else if (ktdulieu()) {

					String tenLoai = txtTenLoai.getText().trim();
					try {
						dsloai = loaiDAO.getDanhSachLoaiSanPham();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					boolean re = true;
					for (LoaiSanPham l : dsloai) {
						if (tenLoai.equalsIgnoreCase(l.getTenLoai())) {
							JOptionPane.showMessageDialog(out, "Danh mục đã tồn tại");
							re = false;
							break;

						}
					}
					if (re) {
						boolean result = loaiDAO.createLoaiSp(tenLoai);
						if (result) {
							LoaiSanPham loaisp = loaiDAO.getLoaiByTenLoai(tenLoai);
							modelDSLoai.addRow(new Object[] { loaisp.getMaLoai(), loaisp.getTenLoai() });
							JOptionPane.showMessageDialog(out, "Thêm danh mục thành công");

						} else {
							JOptionPane.showMessageDialog(out, "Thêm danh mục thất bại");
						}
					}
				}
			}
		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtMaLoai.getText().equals("") || txtTenLoai.getText().equals("")) {
					JOptionPane.showMessageDialog(out, "Thiếu dữ liệu đầu vào");
				} else if (ktdulieu()) {
					LoaiSanPham loai = getSelectedDataTable();
					int row = table.getSelectedRow();
					String tenLoai = txtTenLoai.getText().trim();
					boolean re = true;
					for (LoaiSanPham l : dsloai) {
						if (tenLoai.equalsIgnoreCase(l.getTenLoai())) {
							JOptionPane.showMessageDialog(out, "Danh mục đã tồn tại");
							re = false;
							break;

						}
					}
					boolean result = loaiDAO.capNhat(loai);
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "Bạn chưa chọn dòng cần sửa", "Cảnh báo",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (result == true && re == true) {
							modelDSLoai.setValueAt(loai.getMaLoai(), row, 0);
							modelDSLoai.setValueAt(loai.getTenLoai(), row, 1);
							JOptionPane.showMessageDialog(out, "Cập nhập danh mục thành công");
							modelDSLoai.fireTableDataChanged();
							try {
								loaiDAO.getDanhSachLoaiSanPham();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(out, "Lỗi: Cập nhật danh mục thất bại");
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
					LoaiSanPham loai = getSelectedDataTable();
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "Bạn chưa chọn danh mục sẩn phẩm cần xoá !!!");
					} else {
						int select;
						select = JOptionPane.showConfirmDialog(out, "Bạn có muốn xoá danh mục sản phẩm đã chọn ?",
								"Cảnh báo", JOptionPane.YES_NO_OPTION);
						if (select == JOptionPane.YES_OPTION) {
							boolean result = loaiDAO.delete(loai);
							if (result) {
								modelDSLoai.removeRow(row);
								JOptionPane.showMessageDialog(out, "Xóa thành công");
								txtMaLoai.setText("");
								txtTenLoai.setText("");
							} else {
								JOptionPane.showMessageDialog(out, "Xóa thất bại");
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(out, "Lỗi! Xóa thất bại");
				}
			}

		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaLoai.setText("");
				txtTenLoai.setText("");
				txtNhapLieu.setText("");
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtNhapLieu.getText().equals("")) {
					JOptionPane.showMessageDialog(out, "Cần nhập dữ liệu danh mục cần tìm", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						String key = "";
						if (cboLoaiTimKiem.getSelectedItem().toString().equals("Mã Loại")) {
							key = "MaLoai";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Tên Loại")) {
							key = "TenLoai";

						}
						dsloaitim = loaiDAO.timKiem(key, txtNhapLieu.getText());
						if (dsloaitim.size() == 0) {
							JOptionPane.showMessageDialog(out, "Không tìm thấy dữ liệu theo yêu cầu cần tìm");
							table.clearSelection();
							modelDSLoai.getDataVector().removeAllElements();
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
					modelDSLoai.getDataVector().removeAllElements();
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



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaLoai.setText(modelDSLoai.getValueAt(row, 0).toString());
		txtTenLoai.setText(modelDSLoai.getValueAt(row, 1).toString());

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

	private LoaiSanPham getSelectedDataTable() {
		String maLoai = txtMaLoai.getText().trim();
		String tenLoai = txtTenLoai.getText().trim();
		LoaiSanPham loai = new LoaiSanPham(Integer.parseInt(maLoai), tenLoai);
		return loai;
	}

	private boolean ktdulieu() {
		String tenLoai = txtTenLoai.getText().trim();
		if(tenLoai.equals("")){
			JOptionPane.showMessageDialog(this, "Tên không được bỏ trống");
			txtTenLoai.selectAll();
			txtTenLoai.requestFocus();
            return false;
        }
		
		if(tenLoai.length() <2){
			JOptionPane.showMessageDialog(this, "Tên phải ít nhất là 2 ký tự");
			txtTenLoai.selectAll();
			txtTenLoai.requestFocus();
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

		modelDSLoai.getDataVector().removeAllElements();
		dsloai = new LoaiSanPhamDAO().getDanhSachLoaiSanPham();

//		String stt = table.getValueAt(1, 0).toString();

		dsloai.forEach(loai -> {
			modelDSLoai.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
		});

	}
	public void renderDataTimKiem() throws SQLException {
		table.clearSelection();

		modelDSLoai.getDataVector().removeAllElements();

		dsloaitim.forEach(loai -> {
			modelDSLoai.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
		});

		table.revalidate();
		table.repaint();
	}
}
