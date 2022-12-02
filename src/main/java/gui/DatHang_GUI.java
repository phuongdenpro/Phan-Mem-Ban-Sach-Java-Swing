package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.DonDatHangDAO;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import entity.DonDatHang;
import entity.HoaDon;
import entity.NhanVien;

import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class DatHang_GUI extends JFrame implements ActionListener, MouseListener {
	private NhanVien nhanVien;
	
	private DefaultTableModel modelDonDat;
	String[] colsDonDat = { "Mã đơn đặt hàng", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Tổng tiền", "Tình trạng", "Thời gian đặt" };
	public JPanel pnMain;
	private JTable tableDonDat;
	private JPanel panel_1;
	private JButton btnThanhToan;
	private JButton btnXoa;
	private JTextField txtTimMaDon;
	private List<DonDatHang> dsddh;
	private DefaultTableModel modelDSSP;
	private JTable tblDSSP;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JComboBox comboBox;
	
	private boolean isTimKiem = false;

	private JButton btnSuaDDH;
	
	public DatHang_GUI() throws SQLException{
		this.nhanVien = new NhanVienDAO().getNhanVienByMaNV(1);
		GUI();
	}
	
	public DatHang_GUI(NhanVien nhanVien){
		this.nhanVien = nhanVien;
		GUI();
	}
	
	public void GUI(){
		

		setTitle("Quản lý đơn đặt hàng");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		setContentPane(pnMain);

		JLabel lbTitle = new JLabel("Quản Lý Đơn Đặt Hàng");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(434, 11, 348, 30);
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


		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách đơn đặt hàng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(10, 65, 1274, 575);
		pnMain.add(panel_1);
		panel_1.setLayout(null);

		tableDonDat = new JTable(modelDonDat);
		JScrollPane scDonDat = new JScrollPane(tableDonDat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scDonDat.setBounds(10, 67, 1254, 300);
		panel_1.add(scDonDat);
		
		String[] colsDSSP = { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền" };
		modelDSSP = new DefaultTableModel(colsDSSP, 0);
		tblDSSP = new JTable(modelDSSP);
		JScrollPane scrollPane = new JScrollPane(tblDSSP);
		scrollPane.setBounds(10, 375, 1254, 200);
		panel_1.add(scrollPane);
		
//		DefaultCom
		DefaultComboBoxModel<String> modelTimKiem = new DefaultComboBoxModel<String>();
		comboBox = new JComboBox(modelTimKiem);
		comboBox.setBounds(10, 29, 133, 25);
		panel_1.add(comboBox);
		modelTimKiem.addElement("Mã đơn đặt hàng");
		modelTimKiem.addElement("Mã khách hàng");
		modelTimKiem.addElement("Tên khách hàng");
		modelTimKiem.addElement("Số điện thoại");

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(153, 29, 179, 25);
		panel_1.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		ImageIcon icon_timKiem = new ImageIcon("data/images/search_16.png");
		btnTimKiem = new JButton("Tìm kiếm", icon_timKiem);
		btnTimKiem.setBackground(Color.WHITE);
		btnTimKiem.setBounds(340, 29, 115, 25);
		panel_1.add(btnTimKiem);
		
		ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
		JButton btnLamMoi = new JButton("Làm mới dữ liệu", icon_refresh);
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setBounds(465, 29, 167, 25);
		panel_1.add(btnLamMoi);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBackground(Color.WHITE);
		btnThanhToan.setIcon(new ImageIcon("data/images/blueAdd_16.png"));
		btnThanhToan.setBounds(642, 29, 145, 25);
		panel_1.add(btnThanhToan);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setBounds(797, 29, 115, 25);
		btnXoa.setIcon(new ImageIcon("data/images/cancel_16.png"));
		panel_1.add(btnXoa);
		
		btnSuaDDH = new JButton("Sửa đơn đặt hàng");
		btnSuaDDH.setBackground(Color.WHITE);
		btnSuaDDH.setBounds(927, 29, 179, 25);
		btnSuaDDH.setIcon(new ImageIcon("data/images/edit2_16.png"));
		panel_1.add(btnSuaDDH);
		
		pnMain.addMouseListener(this);

		try {
			renderData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableDonDat.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(tableDonDat.getSelectedRow());
				renderSSSP(tableDonDat.getSelectedRow());
			}
		});
		
		btnThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = tableDonDat.getSelectedRow();
				
				if(id == -1) return;
				
				if(dsddh.get(id).getTinhTrang() == 2)return ;
				
				int choose = JOptionPane.showConfirmDialog(pnMain, "Chắc chắn muốn thanh toán ?");
				if(choose != 0)
					return;
				
				try {
					if(new DonDatHangDAO().thanhToan(dsddh.get(id).getMaDDH(), nhanVien)) {
						int choose2 = JOptionPane.showConfirmDialog(pnMain, "Thanh toán thành công, bạn có muốn xuất hóa đơn không ?");
						if(choose2 == 0) {
							int maHD = new HoaDonDAO().getLastestMaHD();
							HoaDon hd = new HoaDonDAO().getHD(maHD);
							XuatHoaDon_GUI xuaHoaDonGUI = new XuatHoaDon_GUI();
							xuaHoaDonGUI.setHoaDon(hd);
							xuaHoaDonGUI.setVisible(true);
							xuaHoaDonGUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						}
						
						if(isTimKiem) {
							String key = "maDDH";
							if(comboBox.getSelectedIndex() == 1) {
								key = "KhachHang.maKH";
							}else if(comboBox.getSelectedIndex() == 2) {
								key = "HoTen";
							}else if(comboBox.getSelectedIndex() == 3) {
								key = "soDienThoai";
							}
							
							dsddh = new DonDatHangDAO().timKiem(key, txtTimKiem.getText());
							renderDataTimKiem();
						}else 
							renderData();
					}else {
						JOptionPane.showMessageDialog(pnMain, "Có lỗi xảy ra");
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = tableDonDat.getSelectedRow();
				
				if(id == -1) return;
				
				int choose = JOptionPane.showConfirmDialog(pnMain, "Chắc chắn xóa ?");
				if(choose != 0)
					return;
				
				try {
					if(new DonDatHangDAO().xoaDonDatHang(dsddh.get(id).getMaDDH())) {
						if(isTimKiem) {
							String key = "maDDH";
							if(comboBox.getSelectedIndex() == 1) {
								key = "KhachHang.maKH";
							}else if(comboBox.getSelectedIndex() == 2) {
								key = "HoTen";
							}else if(comboBox.getSelectedIndex() == 3) {
								key = "soDienThoai";
							}
							
							dsddh = new DonDatHangDAO().timKiem(key, txtTimKiem.getText());
							renderDataTimKiem();
						}else 
							renderData();
						JOptionPane.showMessageDialog(pnMain, "Xóa thành công");
					}else {
						JOptionPane.showMessageDialog(pnMain, "Có lỗi xảy ra");
					}
					
					
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnLamMoi.addActionListener((e) -> {
			try {
				renderData();
				isTimKiem = false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnTimKiem.addActionListener((e) -> {
			
			try {
				String key = "maDDH";
				if(comboBox.getSelectedIndex() == 1) {
					key = "KhachHang.maKH";
				}else if(comboBox.getSelectedIndex() == 2) {
					key = "HoTen";
				}else if(comboBox.getSelectedIndex() == 3) {
					key = "soDienThoai";
				}
				
				dsddh = new DonDatHangDAO().timKiem(key, txtTimKiem.getText());
				renderDataTimKiem();
				isTimKiem = true;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnSuaDDH.addActionListener(e -> {
			int idx = tableDonDat.getSelectedRow();
			if(idx == -1) {
				JOptionPane.showMessageDialog(pnMain, "Vui lòng chọn hóa đơn để sửa");
				return;
			}
			
			DonDatHang ddh = dsddh.get(idx);
			if(ddh.getTinhTrang() == 2) {
				JOptionPane.showMessageDialog(pnMain, "Đơn đặt hàng đã được thanh toán không thể sửa");
				return;
			}
			
			try {
				ThemDonDatHang_GUI themDDHGUI = new ThemDonDatHang_GUI();
				themDDHGUI.setDDH(ddh);
				themDDHGUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				themDDHGUI.setVisible(true);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			
			
		});
	}
	
	public void renderData() throws SQLException {
		tableDonDat.clearSelection();
		
		modelDonDat.getDataVector().removeAllElements();
		dsddh = new DonDatHangDAO().getDSDonDatHang();
		
		dsddh.forEach(ddh -> {
			String tinhTrang = "Chưa thanh toán";
			if(ddh.getTinhTrang() == 2)
				tinhTrang = "Đã thanh toán";
			
			modelDonDat.addRow(new Object[] {
					ddh.getMaDDH(), 
					ddh.getKhachHang().getMaKh(), 
					ddh.getKhachHang().getHoTen(), 
					ddh.getKhachHang().getSoDienThoai(),
					ddh.getKhachHang().getDiaChi(),
					ddh.getTongTien(), 
					tinhTrang, 
					ddh.getNgayDat()});
		});
		
		tableDonDat.revalidate();
		tableDonDat.repaint();
	}
	
	public void renderDataTimKiem() throws SQLException {
		tableDonDat.clearSelection();
		
		modelDonDat.getDataVector().removeAllElements();
		
		dsddh.forEach(ddh -> {
			String tinhTrang = "Chưa thanh toán";
			if(ddh.getTinhTrang() == 2)
				tinhTrang = "Đã thanh toán";
			
			modelDonDat.addRow(new Object[] {
					ddh.getMaDDH(), 
					ddh.getKhachHang().getMaKh(), 
					ddh.getKhachHang().getHoTen(), 
					ddh.getKhachHang().getSoDienThoai(),
					ddh.getKhachHang().getDiaChi(),
					ddh.getTongTien(), 
					tinhTrang, 
					ddh.getNgayDat()});
		});
		
		tableDonDat.revalidate();
		tableDonDat.repaint();
	}

	public void renderSSSP(int i) {
		modelDSSP.getDataVector().removeAllElements();
		if(i == -1) return;
		tblDSSP.clearSelection();
		
		
		dsddh.get(i).getChiTietDonDatHangs().forEach(ctddh -> {
			modelDSSP.addRow(new Object[] {
					ctddh.getSanPham().getMaSp(), 
					ctddh.getSanPham().getTenSp(), 
					ctddh.getSanPham().getGiaSp(),
					ctddh.getSoLuong(),
					ctddh.tinhThanhTien()
				});
		});
		
		tblDSSP.revalidate();
		tblDSSP.repaint();
	}
	

	public static void main(String[] args) throws SQLException {
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
		
	}

	public JPanel getContentPane() {
		return this.pnMain;
	}
}