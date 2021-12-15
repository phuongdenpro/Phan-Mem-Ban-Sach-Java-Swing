package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.tree.DefaultXTreeCellEditor;

import dao.DonDatHangDAO;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import util.Currency;

import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class HoaDon_GUI extends JFrame{
	private DefaultTableModel modelHD;
	String[] colsHD = { "Mã hoá đơn", "Mã khách hàng","Tên khách hàng","Số điện thoại","Địa chỉ","Tổng tiền", "Ngày lập"};
	public JPanel pnMain;
	private JTable tableHD;
	private JPanel panel_1;
	private JTextField txtTimMaHDDV;

	
	private JTextField txtTongTien;
	private JButton btnTimKiem;
	private JTextField txtDiaChi;
	private JButton btnXoa;
	private JComboBox cboTimKiem;
	private DefaultTableModel modelDSSP;
	private JTable tblDSSP;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblMaHD;
	private JTextField txtMaHD;
	private ArrayList<HoaDon> dshd;
	private JTextField txtMaKH;
	private JTextField txtSdt;
	private JTextField txtTenKH;
	private JTextField txtTimKiem;
	private boolean isTimKiem = false;
	private JButton btnXuatHoaDon;

	public HoaDon_GUI() throws SQLException {
		

		setTitle("Quản lý hóa đơn");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
	
		
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		setContentPane(pnMain);

		JLabel lbTitle = new JLabel("Quản Lý Hóa Đơn");
		lbTitle.setBounds(585, 11, 348, 30);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnMain.add(lbTitle);

		

		pnMain.setLayout(null);

		JPanel pn = new JPanel();
		pn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chi tiết", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn.setBounds(10, 65, 347, 330);
		pnMain.add(pn);
		pn.setLayout(null);
		
		lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaHD.setBounds(10, 24, 93, 25);
		pn.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setEditable(false);
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(122, 24, 205, 25);
		pn.add(txtMaHD);

		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMaKH.setBounds(10, 60, 93, 25);
		pn.add(lbMaKH);
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(122, 60, 205, 25);
		pn.add(txtMaKH);

		JLabel lbTen = new JLabel("Tên khách hàng:");
		lbTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTen.setBounds(10, 96, 93, 25);
		pn.add(lbTen);
		
		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(122, 96, 205, 25);
		pn.add(txtTenKH);
		
		JLabel lbSoDienThoai = new JLabel("Số điện thoại:");
		lbSoDienThoai.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSoDienThoai.setBounds(10, 129, 93, 25);
		pn.add(lbSoDienThoai);
		
		txtSdt = new JTextField();
		txtSdt.setEditable(false);
		txtSdt.setColumns(10);
		txtSdt.setBounds(122, 129, 205, 25);
		pn.add(txtSdt);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiaChi.setBounds(10, 162, 93, 25);
		pn.add(lblDiaChi);
		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(122, 162, 205, 25);
		pn.add(txtDiaChi);
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTongTien.setBounds(10, 196, 93, 25);
		pn.add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(122, 196, 205, 25);
		pn.add(txtTongTien);
		txtTongTien.setColumns(10);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(10, 247, 154, 35);
		pn.add(btnXoa);
		btnXoa.setIcon(new ImageIcon("data/images/cancel_16.png"));
		btnXoa.setBackground(Color.WHITE);
		
		btnXuatHoaDon = new JButton("Xuất hóa đơn");
		btnXuatHoaDon.setBackground(Color.WHITE);
		btnXuatHoaDon.setBounds(174, 247, 154, 35);
		pn.add(btnXuatHoaDon);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(370, 65, 900, 575);
		pnMain.add(panel_1);
		panel_1.setLayout(null);
		modelHD = new DefaultTableModel(colsHD, 0) {
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

		tableHD = new JTable(modelHD);
		JScrollPane scHD = new JScrollPane(tableHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scHD.setBounds(10, 67, 875, 260);
		panel_1.add(scHD);
		String[] colsDSSP = { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền" };
		modelDSSP = new DefaultTableModel(colsDSSP, 0){
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
		tblDSSP = new JTable(modelDSSP);
		JScrollPane scrollPane = new JScrollPane(tblDSSP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 330, 875, 235);
		panel_1.add(scrollPane);

//		JLabel lbTimMaHDDV = new JLabel("Mã hoá đơn:");
//		lbTimMaHDDV.setFont(new Font("Tahoma", Font.BOLD, 11));
//		lbTimMaHDDV.setBounds(20, 25, 110, 30);
//		panel_1.add(lbTimMaHDDV);
		
		DefaultComboBoxModel<String> modelTimKiem = new DefaultComboBoxModel<String>();
		cboTimKiem = new JComboBox(modelTimKiem);
		cboTimKiem.setBounds(20, 29, 120, 25);
		panel_1.add(cboTimKiem);
		modelTimKiem.addElement("Mã hóa đơn");
		modelTimKiem.addElement("Mã khách hàng");
		modelTimKiem.addElement("Tên khách hàng");
		modelTimKiem.addElement("Số điện thoại");

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(150, 29, 120, 25);
		panel_1.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		
		btnTimKiem = new JButton("Tìm");
		btnTimKiem.setIcon(new ImageIcon("data/images/search_16.png"));
		btnTimKiem.setBounds(285, 29, 115, 25);
		btnTimKiem.setBackground(Color.WHITE);
		panel_1.add(btnTimKiem);
		
		ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
		JButton btnLamMoi = new JButton("Làm mới dữ liệu", icon_refresh);
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setBounds(410, 29, 165, 25);
		panel_1.add(btnLamMoi);

		renderData();
		
		tableHD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int idx = tableHD.getSelectedRow();
				if(idx != -1) {
					renderHD(dshd.get(idx));
				}
				
			}
		});
		
		btnTimKiem.addActionListener((e) -> {
			
			try {
				String key = "maHD";
				if(cboTimKiem.getSelectedIndex() == 1) {
					key = "KhachHang.maKH";
				}else if(cboTimKiem.getSelectedIndex() == 2) {
					key = "KhachHang.HoTen";
				}else if(cboTimKiem.getSelectedIndex() == 3) {
					key = "KhachHang.soDienThoai";
				}
				
				dshd = (ArrayList<HoaDon>) new HoaDonDAO().timKiem(key, txtTimKiem.getText());
				renderDataTimKiem();
				isTimKiem = true;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnXoa.addActionListener((e) -> {
			int idx = tableHD.getSelectedRow();
			if(idx == -1) {
				JOptionPane.showMessageDialog(pnMain, "Vui lòng chọn hóa đơn để xóa");
				return;
			}
			int choose = JOptionPane.showConfirmDialog(pnMain, "Chắc chắn xóa ?");
			if(choose == 0) {
				try {
					if(new HoaDonDAO().xoaHD(dshd.get(idx).getMaHD())) {
						JOptionPane.showMessageDialog(pnMain, "Xóa thành công");
						clear();
						
						if(isTimKiem) {
							String key = "maHD";
							if(cboTimKiem.getSelectedIndex() == 1) {
								key = "KhachHang.maKH";
							}else if(cboTimKiem.getSelectedIndex() == 2) {
								key = "KhachHang.HoTen";
							}else if(cboTimKiem.getSelectedIndex() == 3) {
								key = "KhachHang.soDienThoai";
							}
							
							dshd = (ArrayList<HoaDon>) new HoaDonDAO().timKiem(key, txtTimKiem.getText());
							renderDataTimKiem();
						}else 
							renderData();
					}else {
						JOptionPane.showMessageDialog(pnMain, "Xóa thất bại");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		btnLamMoi.addActionListener((e) -> {
			try {
				isTimKiem  = false;
				
				clear();
				
				renderData();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		btnXuatHoaDon.addActionListener((e) -> {
			int idx = tableHD.getSelectedRow();
			if(idx == -1) {
				JOptionPane.showMessageDialog(pnMain, "Vui lòng chọn hóa đơn để xuất");
				return;
			}
			
			XuatHoaDon_GUI xuaHoaDonGUI = new XuatHoaDon_GUI();
			xuaHoaDonGUI.setHoaDon(dshd.get(idx));
			xuaHoaDonGUI.setVisible(true);
			xuaHoaDonGUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		});

	}
	
	public void renderData() throws SQLException {
		dshd = new HoaDonDAO().getDSHD();
		
		tableHD.clearSelection();
		modelHD.getDataVector().removeAllElements();
		dshd.forEach(hd -> {
			modelHD.addRow(new Object[] {
				hd.getMaHD(), 
				hd.getKhachHang().getMaKh(), 
				hd.getKhachHang().getHoTen(), 
				hd.getKhachHang().getSoDienThoai(),
				hd.getKhachHang().getDiaChi(),
				Currency.format(hd.tinhTongTien()),
				hd.getNgayMua2()
			});
		});
		tableHD.revalidate();
		tableHD.repaint();
	}
	
	public void renderHD(HoaDon hd) {
		txtMaHD.setText(String.valueOf(hd.getMaHD()));
		txtMaKH.setText(String.valueOf(hd.getKhachHang().getMaKh()));
		txtTenKH.setText(hd.getKhachHang().getHoTen());
		txtSdt.setText(hd.getKhachHang().getSoDienThoai());
		txtDiaChi.setText(hd.getKhachHang().getDiaChi());
		txtTongTien.setText(Currency.format(hd.tinhTongTien()));
		
		tblDSSP.clearSelection();
		modelDSSP.getDataVector().removeAllElements();
		hd.getChiTietHoaDons().forEach(cthd -> {
			modelDSSP.addRow(new Object[] {
				cthd.getSanPham().getMaSp(),
				cthd.getSanPham().getTenSp(),
				cthd.getDonGia(),
				cthd.getSoLuong(),
				Currency.format(cthd.tinhThanhTien())
			});
		});
		tblDSSP.revalidate();
		tblDSSP.repaint();
	}
	
	public void renderDataTimKiem() throws SQLException {
		tableHD.clearSelection();
		
		modelHD.getDataVector().removeAllElements();
		
		dshd.forEach(hd -> {
			modelHD.addRow(new Object[] {
					hd.getMaHD(), 
					hd.getKhachHang().getMaKh(), 
					hd.getKhachHang().getHoTen(), 
					hd.getKhachHang().getSoDienThoai(),
					hd.getKhachHang().getDiaChi(),
					Currency.format(hd.tinhTongTien()),
					hd.getNgayMua2()
				});
		});
		
		tableHD.revalidate();
		tableHD.repaint();
	}

	public JPanel getContentPane() {
		return this.pnMain;
	}
	
	public void clear() {
		tableHD.clearSelection();
		tblDSSP.clearSelection();
		modelDSSP.getDataVector().removeAllElements();
		tblDSSP.revalidate();
		tblDSSP.repaint();
		
		txtMaHD.setText("");
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSdt.setText("");
		txtDiaChi.setText("");
		txtTongTien.setText("");
		
		
	}

	public static void main(String[] args) throws SQLException {
		new HoaDon_GUI().setVisible(true);
	}

}