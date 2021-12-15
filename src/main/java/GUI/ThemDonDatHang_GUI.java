package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietDonDatHangDAO;
import dao.DonDatHangDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.ChiTietDonDatHang;
import entity.ChiTietHoaDon;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import util.Currency;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

public class ThemDonDatHang_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField textField_4;
	private JButton btnThemSP;
	private JButton btnBoSP;
	

	private DefaultComboBoxModel<String> modelKH;
	private JComboBox cboKH;
	
	private DefaultTableModel modelSach;
	private DefaultTableModel modelSPK;
	private DefaultTableModel modelSPTGH;
	private JTable tblSach;
	private JTable tblSPK;
	
	private ArrayList<KhachHang> dskh;
	private ArrayList<SanPham> dsSach;
	private ArrayList<SanPham> dsSPKhac;
	private ArrayList<ChiTietDonDatHang> dsctddh = new ArrayList<ChiTietDonDatHang>();
	private JTextField txtSoLuong;
	private JButton btnThemHD; 
	private JTextField txtTongTien;
	private JTable tblSPTGH;
	private int indexKH = -1;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JTextField txtMaDDH;
	
	private boolean onlyEdit;
	private DonDatHang _ddh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemDonDatHang_GUI frame = new ThemDonDatHang_GUI();
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
	public ThemDonDatHang_GUI() throws SQLException {
		GUI();
	}
			
	public void GUI() throws SQLException {
		setTitle("Tạo hóa đơn");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnThongTinKH = new JPanel();
		contentPane.add(pnThongTinKH, BorderLayout.WEST);
		
		JPanel pnThongTinKH2 = new JPanel();
		pnThongTinKH.add(pnThongTinKH2);
		pnThongTinKH2.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTTKH = new JPanel();
		pnTTKH.setBorder(new EmptyBorder(40, 0, 0, 0));
		pnThongTinKH2.add(pnTTKH, BorderLayout.NORTH);
		
		JLabel lblTTKH = new JLabel("Thông tin đơn đặt hàng");
		lblTTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTTKH.add(lblTTKH);
		
		JPanel pnThongTin = new JPanel();
		pnThongTinKH2.add(pnThongTin, BorderLayout.CENTER);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
		
		JPanel pnMaKH_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnMaKH_1.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaKH_1);
		
		JLabel lblMaDDH = new JLabel("Mã hóa đơn");
		lblMaDDH.setPreferredSize(new Dimension(100, 20));
		pnMaKH_1.add(lblMaDDH);
		
		txtMaDDH = new JTextField();
		txtMaDDH.setPreferredSize(new Dimension(7, 30));
		txtMaDDH.setEditable(false);
		txtMaDDH.setColumns(20);
		pnMaKH_1.add(txtMaDDH);
		
		JPanel pnMaKH = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnMaKH.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaKH);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setPreferredSize(new Dimension(100, 20));
		pnMaKH.add(lblMaKH);
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setPreferredSize(new Dimension(7, 30));
		pnMaKH.add(txtMaKH);
		txtMaKH.setColumns(20);
		
		JPanel pnTenKH = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnTenKH.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenKH);
		
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setPreferredSize(new Dimension(100, 20));
		pnTenKH.add(lblTenKH);
		
		modelKH = new DefaultComboBoxModel<String>();
		cboKH = new JComboBox(modelKH);
		cboKH.setPreferredSize(new Dimension(170, 30));
		pnTenKH.add(cboKH);
		
		JPanel pnSoDienThoai = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnSoDienThoai.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoDienThoai);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setPreferredSize(new Dimension(100, 20));
		pnSoDienThoai.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setPreferredSize(new Dimension(7, 30));
		txtSDT.setColumns(20);
		pnSoDienThoai.add(txtSDT);
		
		JPanel pnDiaChi = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnDiaChi.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnDiaChi);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(100, 20));
		pnDiaChi.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setPreferredSize(new Dimension(7, 30));
		txtDiaChi.setColumns(20);
		pnDiaChi.add(txtDiaChi);
		
		JPanel pnTongTien = new JPanel();
		pnThongTin.add(pnTongTien);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setPreferredSize(new Dimension(100, 20));
		pnTongTien.add(lblTongTien);
		
		txtTongTien = new JTextField("0");
		txtTongTien.setPreferredSize(new Dimension(7, 30));
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(20);
		pnTongTien.add(txtTongTien);
		
		JPanel pnBtn = new JPanel();
		pnThongTin.add(pnBtn);
		
		btnThemHD = new JButton("Thêm đơn đặt", new ImageIcon("data/images/blueAdd_16.png"));
		btnThemHD.setBackground(Color.WHITE);
		pnBtn.add(btnThemHD);
		
		btnSua = new JButton("Sửa đơn đặt hàng", null);
		btnSua.setBackground(Color.WHITE);
		pnBtn.add(btnSua);
		
		btnLamMoi = new JButton("Làm mới", null);
		btnLamMoi.setBackground(Color.WHITE);
		pnBtn.add(btnLamMoi);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblNewLabel);
		
		
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		String[] cols = {"Tên sản phẩm", "Đơn giá", "Số lượng", "Nhà xuất bản"};
		modelSach = new DefaultTableModel(cols, 0);
		JTable tblSach = new JTable(modelSach);
		JScrollPane scrollPane = new JScrollPane(tblSach);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_7.add(scrollPane);
		
		String[] cols2 = {"Tên sản phẩm", "Đơn giá", "Số lượng", "Nhà cung cấp"};
		modelSPK = new DefaultTableModel(cols2, 0);
		
		JPanel panel_12 = new JPanel();
		panel_7.add(panel_12);
		
		JLabel lblSPKhac = new JLabel("Sản phẩm khác");
		lblSPKhac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_12.add(lblSPKhac);
		JTable tblSPK = new JTable(modelSPK);
		JScrollPane scrollPane_2 = new JScrollPane(tblSPK);
		panel_7.add(scrollPane_2);
		
//		modelSPCTM.addRow(new Object[] {"1", "Sach giao khoa", 123.456});
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(250);
		panel_5.add(verticalStrut);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		btnThemSP = new JButton(">>");
		btnThemSP.setBackground(Color.WHITE);
		btnThemSP.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnThemSP);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setText("1");
		txtSoLuong.setPreferredSize(new Dimension(49, 30));
		txtSoLuong.setColumns(4);
		panel_10.add(txtSoLuong);
		
		JPanel panel_11 = new JPanel();
		panel_8.add(panel_11);
		
		btnBoSP = new JButton("<<");
		btnBoSP.setBackground(Color.WHITE);
		btnBoSP.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_11.add(btnBoSP);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách sản phẩm trong giỏ hàng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_1);
		
		String[] cols3 = {"Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
		modelSPTGH = new DefaultTableModel(cols3, 0);
		tblSPTGH = new JTable(modelSPTGH);
		JScrollPane scrollPane_1 = new JScrollPane(tblSPTGH);
		scrollPane_1.setPreferredSize(new Dimension(450, 500));
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		renderKhachHang();
		renderData();
		
		cboKH.addActionListener((e) -> {
			
			int idx = cboKH.getSelectedIndex();
			if(idx != -1) {
				idx--;
				
				
				if(idx == -1) {
					txtMaKH.setText("");
					txtSDT.setText("");
					txtDiaChi.setText("");
					indexKH = -1;
					return;
				}
				
				DonDatHang ddh = null;
				if(indexKH != idx) {
					
					try {
						ddh = new DonDatHangDAO().getDonDatHangChuaThanhToan(dskh.get(idx).getMaKh());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if(ddh != null) {
					int choose = JOptionPane.showConfirmDialog(contentPane, "Phát hiện khách hàng "+ ddh.getKhachHang().getHoTen() +" có 1 đơn đặt hàng chưa thanh toán. Thay đổi khách hàng sẽ không lưu sản phẩm vừa thêm. Tiếp tục ?");
					if(choose != 0) {
						cboKH.setSelectedIndex(indexKH+1);
						return;
					}
					
					txtMaDDH.setText(String.valueOf(ddh.getMaDDH()));
					modeSua();
					
					try {
						dsctddh = new ChiTietDonDatHangDAO().getDSChiTietDDH(ddh.getMaDDH());
						renderData();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				txtMaKH.setText(String.valueOf(dskh.get(idx).getMaKh()));
				txtSDT.setText(dskh.get(idx).getSoDienThoai());
				txtDiaChi.setText(dskh.get(idx).getDiaChi());
				indexKH = idx;
			}
		});
		
		btnThemSP.addActionListener((e) -> {
			if(cboKH.getSelectedIndex() <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng trước khi thêm sản phẩm");
				return;
			}
			
			if(tblSach.getSelectedRow() == -1 && tblSPK.getSelectedRow() == -1) {
				return;
			}
			int idx;
			SanPham sp;
			if(tblSach.getSelectedRow() != -1) {
				idx = tblSach.getSelectedRow();
				sp = dsSach.get(idx); // sách
			}else {
				idx = tblSPK.getSelectedRow();
				sp = dsSPKhac.get(idx);
			}
			int soLuong = 0;
			try {
				soLuong = Integer.parseInt(txtSoLuong.getText());
			}catch (Exception e2) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
				txtSoLuong.requestFocus();
				return;
			}
			
			if(soLuong > sp.getSoLuong()) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng không đủ");
				txtSoLuong.requestFocus();
				return;
			}
			
			
//			kiểm tra xem đã có sp đó trong giỏ chưa
			int vt = -1;
			for(int i=0; i<dsctddh.size(); i++) {
				if(dsctddh.get(i).getSanPham().getMaSp() == sp.getMaSp())
					vt = i;
			}
			if(vt != -1) { // thêm số lượng
				dsctddh.get(vt).setSoLuong(dsctddh.get(vt).getSoLuong() + soLuong);
			}else { // thêm sp
				ChiTietDonDatHang cthddh = new ChiTietDonDatHang(sp, soLuong, sp.getGiaSp());
				dsctddh.add(cthddh);
			}
			tinhTongTien();
			
			try {
				renderData();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		
		
		btnBoSP.addActionListener((e) -> {
			if(cboKH.getSelectedIndex() <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng trước khi thêm sản phẩm");
				return;
			}
			
			int idx = tblSPTGH.getSelectedRow();
			if(idx != -1) {
				int soLuong = 0;
				try {
					soLuong = Integer.parseInt(txtSoLuong.getText());
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
					txtSoLuong.requestFocus();
					return;
				}
				
				if(soLuong > dsctddh.get(idx).getSoLuong()) {
					JOptionPane.showMessageDialog(contentPane, "Số lượng không hợp lệ");
					txtSoLuong.requestFocus();
					return;
				}else if(soLuong == dsctddh.get(idx).getSoLuong()) {	
					dsctddh.remove(idx);
				}else {
					dsctddh.get(idx).setSoLuong(dsctddh.get(idx).getSoLuong() - soLuong);
				}
				
				tinhTongTien();
				try {
					renderData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnThemHD.addActionListener((e) -> {
			int idx = cboKH.getSelectedIndex(); 
			if(idx <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng");
				return;
			}
			
			if(dsctddh.size() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm hàng vào giỏ");
				return;
			}
			
			int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn đơn đặt hàng");
			if(choose == 0) {
				
				DonDatHang ddh = new DonDatHang(dskh.get(idx-1), dsctddh);
				try {
					DonDatHangDAO donDatHangDao = new DonDatHangDAO();
					int maDDH = donDatHangDao.taoDDH(ddh);
					if(maDDH != -1) {
						JOptionPane.showMessageDialog(contentPane, "Đã thêm đơn đặt hàng thành công");
						txtMaDDH.setText(String.valueOf(maDDH));
						modeSua();
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
			}
		});
		
		btnSua.addActionListener((e) -> {
			int idx = cboKH.getSelectedIndex(); 
			if(idx <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng");
				return;
			}
			
			if(dsctddh.size() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm hàng vào giỏ");
				return;
			}
			
			DonDatHang ddh = new DonDatHang(dskh.get(idx-1), dsctddh);
			ddh.setMaDDH(Integer.parseInt(txtMaDDH.getText()));
			dsctddh.forEach(ctddh -> {
				try {
					new ChiTietDonDatHangDAO().themSanPhamVaoDonDatHang(ddh.getMaDDH(), ctddh.getSanPham(), ctddh.getSoLuong());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
			return;
			
//			JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
		
		});
		
		btnLamMoi.addActionListener(e -> {
			
			cboKH.setSelectedIndex(0);
			modeThem();
			dsctddh.clear();
			tinhTongTien();
			try {
				renderData();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		tblSach.getSelectionModel().addListSelectionListener((x) -> {
			tblSPK.clearSelection();
		});
		
		tblSPK.getSelectionModel().addListSelectionListener((x) -> {
			tblSach.clearSelection();
		});
		
		modeThem();
	}
	
	public void renderKhachHang() throws SQLException {
//		khach hang
		dskh = new KhachHangDAO().getListKhachHang();
		modelKH.removeAllElements();
		modelKH.addElement("");
		cboKH.setSelectedIndex(0);
		
		dskh.forEach(kh -> {
//			System.out.println(kh.getHoTen());
			modelKH.addElement("#"+ kh.getMaKh()+ " " + kh.getHoTen());
		});
		cboKH.revalidate();
		cboKH.repaint();
	}
	
	public void renderData() throws SQLException {
		dsSach = new SanPhamDAO().getListSach();
		modelSach.getDataVector().removeAllElements();
		for(int i=0; i<dsSach.size(); i++) {
			boolean flag = true;
			for(int j=0; j<dsctddh.size(); j++) {
				if(dsctddh.get(j).getSanPham().getMaSp() == dsSach.get(i).getMaSp()) {
					dsSach.get(i).setSoLuong(dsSach.get(i).getSoLuong() - dsctddh.get(j).getSoLuong());
				}
			}
			if(dsSach.get(i).getSoLuong() != 0) {
				modelSach.addRow(new Object[] {
						dsSach.get(i).getTenSp(), 
						Currency.format(dsSach.get(i).getGiaSp()).toString(), 
						dsSach.get(i).getSoLuong(),
						dsSach.get(i).getNhaCungCap().getTenNCC()
					});
			}else {
				dsSach.remove(i);
				i--;
			}
			
		}
		
		dsSPKhac = new SanPhamDAO().getListSanPhamKhac();
		modelSPK.getDataVector().removeAllElements();
		for(int i=0; i<dsSPKhac.size(); i++) {
			for(int j=0; j<dsctddh.size(); j++) {
				if(dsctddh.get(j).getSanPham().getMaSp() == dsSPKhac.get(i).getMaSp()) {
					dsSPKhac.get(i).setSoLuong(dsSPKhac.get(i).getSoLuong() - dsctddh.get(j).getSoLuong());
				}
			}
			if(dsSPKhac.get(i).getSoLuong() != 0) {
				modelSPK.addRow(new Object[] {
						dsSPKhac.get(i).getTenSp(), 
						Currency.format(dsSPKhac.get(i).getGiaSp()), 
						dsSPKhac.get(i).getSoLuong(),
						dsSPKhac.get(i).getNhaCungCap().getTenNCC()
				});
			}else {
				dsSPKhac.remove(i);
				i--;
			}
			
		}
		
//		san pham trong don hang
		tblSPTGH.clearSelection();
		modelSPTGH.getDataVector().removeAllElements();
		dsctddh.forEach(cthd -> {
			modelSPTGH.addRow(new Object[] {cthd.getSanPham().getTenSp(), Currency.format(cthd.getDonGia()), cthd.getSoLuong(), Currency.format(cthd.tinhThanhTien())});
		});
		tblSPTGH.revalidate();
		tblSPTGH.repaint();
	}
	
	public void tinhTongTien() {
		double tongTien = 0;
		for(int i=0; i<dsctddh.size(); i++) {
			tongTien += dsctddh.get(i).tinhThanhTien();
		}
		txtTongTien.setText(Currency.format(tongTien));
	}
	
	public void modeThem() {
		txtMaDDH.setText("");
		btnThemHD.setVisible(true);
		btnSua.setVisible(false);
		cboKH.setEnabled(true);
		if(onlyEdit) {
			cboKH.setEnabled(false);
			btnLamMoi.setVisible(false);
		}
	}
	
	public void modeSua() {
		btnThemHD.setVisible(false);
		btnSua.setVisible(true);
		cboKH.setEnabled(false);
		if(onlyEdit)
			btnLamMoi.setVisible(false);
	}
	
	public void setOnlyEdit(boolean onlyEdit) {
		this.onlyEdit = onlyEdit;
	}
	
	public void setDDH(DonDatHang ddh) {
		this._ddh = ddh;
		this.onlyEdit = true;
		txtMaDDH.setText(String.valueOf(ddh.getMaDDH()));
		modeSua();
		
		for(int i=0; i<dskh.size(); i++) {
			if(dskh.get(i).getMaKh() == ddh.getKhachHang().getMaKh()) {
				indexKH = i;
				cboKH.setSelectedIndex(i + 1);
				break;
			}
		}
		try {
			dsctddh = new ChiTietDonDatHangDAO().getDSChiTietDDH(ddh.getMaDDH());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			renderData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}

}
