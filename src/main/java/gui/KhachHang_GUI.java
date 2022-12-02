package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

import dao.KhachHangDAO;
import entity.KhachHang;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class KhachHang_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable tblKhachHang;
	private JTextField txtMaKh,txtTenKh,txtSdt,txtDiaChi;
	private DefaultTableModel modelDSKH;
	private JButton btnLamMoi,btnXoaKh,btnSuaKh,btnTimKiem;
	private DefaultComboBoxModel cboLoaiTimKiem;
	private JComboBox cmbLoaiTimKiem;
	ArrayList<KhachHang> dskh ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHang_GUI frame = new KhachHang_GUI();
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
	public KhachHang_GUI() throws SQLException {
		setTitle("Khách hàng");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		out = new JPanel();
		out.setLayout(new BoxLayout(out,BoxLayout.Y_AXIS));
		setContentPane(out);
		
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		top.add(title);
		//title.setHorizontalAlignment(ABORT);
		out.add(top);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		out.add(bottom);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		bottom.add(contentPane,BorderLayout.CENTER);
		JPanel pnLeft = new JPanel();
		//pnLeft.setBorder();
		Border compound = BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pnLeft.setBorder(compound);
		contentPane.add(pnLeft);
		
		JPanel pnThongTinKh = new JPanel();
		pnLeft.add(pnThongTinKh);
		pnThongTinKh.setLayout(new BoxLayout(pnThongTinKh, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(35);
		pnThongTinKh.add(verticalStrut_2);
		
		JPanel pnTieuDe = new JPanel();
		pnThongTinKh.add(pnTieuDe);
		
		JLabel lblTieuDe = new JLabel("Thông tin khách hàng");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut_1);
		
		JPanel pnMaKh = new JPanel();
		FlowLayout fl_pnMaKh = (FlowLayout) pnMaKh.getLayout();
		fl_pnMaKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnMaKh);
		
		JLabel lblMaKh = new JLabel("Mã KH             ");
		lblMaKh.setPreferredSize(new Dimension(100, 14));
		pnMaKh.add(lblMaKh);
		
		txtMaKh = new JTextField();
		txtMaKh.setEnabled(false);
		txtMaKh.setPreferredSize(new Dimension(7, 30));
		pnMaKh.add(txtMaKh);
		txtMaKh.setColumns(20);
		
		JPanel pnTenKh = new JPanel();
		FlowLayout fl_pnTenKh = (FlowLayout) pnTenKh.getLayout();
		fl_pnTenKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnTenKh);
		
		JLabel lblTenKh = new JLabel("Tên KH");
		lblTenKh.setPreferredSize(new Dimension(100, 14));
		pnTenKh.add(lblTenKh);
		
		txtTenKh = new JTextField();
		txtTenKh.setPreferredSize(new Dimension(7, 30));
		txtTenKh.setColumns(20);
		pnTenKh.add(txtTenKh);
		
		JPanel pnSoDienThoai = new JPanel();
		FlowLayout fl_pnSoDienThoai = (FlowLayout) pnSoDienThoai.getLayout();
		fl_pnSoDienThoai.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnSoDienThoai);
		
		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setPreferredSize(new Dimension(100, 14));
		pnSoDienThoai.add(lblSdt);
		
		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(7, 30));
		txtSdt.setColumns(20);
		pnSoDienThoai.add(txtSdt);
		
		JPanel pnDiaChi = new JPanel();
		FlowLayout fl_pnDiaChi = (FlowLayout) pnDiaChi.getLayout();
		fl_pnDiaChi.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnDiaChi);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(100, 14));
		pnDiaChi.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(7, 30));
		txtDiaChi.setColumns(20);
		pnDiaChi.add(txtDiaChi);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut);
		
		JPanel pnChucNang = new JPanel();
		pnThongTinKh.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(0, 1, 0, 5));
		
		btnSuaKh = new JButton("Sửa");
		btnSuaKh.setBackground(Color.WHITE);
		btnSuaKh.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
		btnSuaKh.setIconTextGap(30);
		pnChucNang.add(btnSuaKh);
		
		btnXoaKh = new JButton("Xóa");
		btnXoaKh.setBackground(Color.WHITE);
		btnXoaKh.setIcon(new ImageIcon("data\\images\\trash.png"));
		btnXoaKh.setIconTextGap(10);
		pnChucNang.add(btnXoaKh);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setIcon(new ImageIcon("data\\images\\refresh.png"));
		btnLamMoi.setIconTextGap(10);
		pnChucNang.add(btnLamMoi);
		
		JPanel pnRight = new JPanel();
		contentPane.add(pnRight);
		pnRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		pnRight.add(pnTimKiem, BorderLayout.NORTH);

		cboLoaiTimKiem = new DefaultComboBoxModel<String>();
		cmbLoaiTimKiem = new JComboBox(cboLoaiTimKiem);
		cmbLoaiTimKiem.setToolTipText("tìm kiếm theo");
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		cmbLoaiTimKiem.setPreferredSize(new Dimension(130, 22));
		pnTimKiem.add(cmbLoaiTimKiem);
		cboLoaiTimKiem.addElement((String) "Tên KH");
		cboLoaiTimKiem.addElement((String) "Số điện thoại");
		cboLoaiTimKiem.addElement((String) "Địa chỉ");
		
		
		txtNhapLieu = new JTextField();
		txtNhapLieu.setPreferredSize(new Dimension(7, 25));
		pnTimKiem.add(txtNhapLieu);
		new Placeholder().placeholder(txtNhapLieu, "nhập liệu tìm kiếm");
		txtNhapLieu.setColumns(30);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setPreferredSize(new Dimension(130, 25));
		btnTimKiem.setBackground(Color.WHITE);
		btnTimKiem.setIcon(new ImageIcon("data\\images\\search_16.png"));
		pnTimKiem.add(btnTimKiem);
		
		JPanel pnTableKh = new JPanel();
		pnRight.add(pnTableKh, BorderLayout.CENTER);
		pnTableKh.setLayout(new BorderLayout(0, 0));
		
		String[] cols_dskh = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ","Tài khoản"};
		modelDSKH = new DefaultTableModel(cols_dskh, 0);
		tblKhachHang = new JTable(modelDSKH);
		JScrollPane scrTableKhachhang = new JScrollPane(tblKhachHang);
		scrTableKhachhang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableKhachhang.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableKhachhang);
		
		//modelDSKH.addRow(new Object[]{"1", "Tran Van Nhan", "0987654321", "tranvannhan@gmail.com", "Thủ Đức, Hồ Chí Minh"});
		
		renderData();
		addEvents();
		
	}
	
	private void addEvents() {
		// TODO Auto-generated method stub
		setDisable();
		
		tblKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int index = tblKhachHang.getSelectedRow();
				if(index == -1 && txtTenKh.getText().equals("")) {
					setDisable();
				}
					
				if(index != -1) {
					setEnable();
					
					KhachHang kh = dskh.get(index);
					txtMaKh.setText(String.valueOf(kh.getMaKh()));
					txtTenKh.setText(kh.getHoTen());
					txtSdt.setText(kh.getSoDienThoai());	
					txtDiaChi.setText(kh.getDiaChi()); 
					
				}
			}
		});
		
		btnLamMoi.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaKh.setText("");
				txtTenKh.setText("");
				txtSdt.setText("");
				txtDiaChi.setText("");
				try {
					renderData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
			}});
		
		

		btnSuaKh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tblKhachHang.clearSelection();
					
				int makh = Integer.parseInt( txtMaKh.getText());
				String tenKh = txtTenKh.getText();
				String sdt = txtSdt.getText();
				String diaChi = txtDiaChi.getText();
				
				if(tenKh.equals("")|| kiemTraSo(tenKh)) {
					JOptionPane.showMessageDialog(contentPane, "Tên không hợp lệ");
					return;
				}
				//boolean ktTen = tenKh.ma
				boolean ktSdt  = sdt.matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$");			
				if (ktSdt == false) {
					JOptionPane.showMessageDialog(contentPane, "Sai định dạng số điện thoại");
					return;	
				}
				
				KhachHang kh = new KhachHang(tenKh, sdt, diaChi);
				boolean kq; 
				try {
					kq = new KhachHangDAO().suaKH(kh,makh);
					if(kq) {
						JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
						renderData();
					}	
					else 
						JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
				}catch (Exception ex) {
					ex.printStackTrace();
				}
					lamMoi();
					setDisable();
			}});
		btnXoaKh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = tblKhachHang.getSelectedRow();
				if(index!= -1) {
					int choose = JOptionPane.showConfirmDialog(contentPane, 
							"chắc chắn xóa", "xác nhận", JOptionPane.YES_NO_OPTION);
					if (choose == 0) {
						tblKhachHang.clearSelection();
						boolean kq;
						try {
							kq = new KhachHangDAO().xoaKhachHang(dskh.get(index));
							System.out.println(kq);
							if(kq) {
								JOptionPane.showMessageDialog(contentPane, "xóa thành công");
								renderData();
								lamMoi();
								setDisable();
								return;
							}else {
								JOptionPane.showMessageDialog(contentPane, "không thể xóa khách hàng này");
								return;
							}
							
						}catch(Exception ex) {
							ex.printStackTrace();
						}
					}			
				}
				
			}});
		
		
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtNhapLieu.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane,"cần nhập dữ liệu tìm kiếm");
					return;
				}
				String key = "";
					
				if(cboLoaiTimKiem.getSelectedItem().equals("Tên KH")) 
					key = "HoTen";							
				else if(cboLoaiTimKiem.getSelectedItem().equals("Số điện thoại")) 
					key = "SoDienThoai";		
				else if(cboLoaiTimKiem.getSelectedItem().equals("Địa chỉ")) 
					key = "DiaChi";
				String sql = " "+ key + " like " + "N'%" +txtNhapLieu.getText()+ "%'" ;
				System.out.println(sql);
				dskh = new ArrayList<KhachHang>();
				try {
					dskh = new KhachHangDAO().TimKiem(sql);
					renderDataTimKiem(dskh);
					if(dskh.size() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Không tìm thấy khách hàng nào");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}});
	}

	public void renderData() throws SQLException {
		modelDSKH.setRowCount(0);
		dskh = new ArrayList<KhachHang>();
		dskh = new KhachHangDAO().getListKhachHang();
		for (KhachHang kh: dskh) {
			System.out.println(kh.toString());
			modelDSKH.addRow(new Object[] {kh.getMaKh(),kh.getHoTen(),kh.getSoDienThoai(),kh.getDiaChi(),kh.getTenTk()});
		}
		tblKhachHang.revalidate();
		tblKhachHang.repaint();
	}
	public void renderDataTimKiem(ArrayList<KhachHang> dskh) throws SQLException {
		tblKhachHang.clearSelection();

		modelDSKH.getDataVector().removeAllElements();

		dskh.forEach(kh -> {
			modelDSKH.addRow(new Object[] { kh.getMaKh(),kh.getHoTen(),kh.getSoDienThoai(),kh.getDiaChi(),kh.getTenTk()});
		});

		tblKhachHang.revalidate();
		tblKhachHang.repaint();
	}
	public void lamMoi() {
		txtMaKh.setText("");
		txtTenKh.setText("");
		txtSdt.setText("");
		txtDiaChi.setText("");
	}
	public void setDisable() {
		btnSuaKh.setEnabled(false);
		btnXoaKh.setEnabled(false);
	}
	public void setEnable() {
		btnSuaKh.setEnabled(true);
		btnXoaKh.setEnabled(true);
	}
	public boolean kiemTraSo(String ten) {
		char arrTen[] = ten.toCharArray();
		String cTen;
		for(int i=0;i<ten.length();i++) {
			cTen = String.valueOf(arrTen[i]);
			if(cTen.matches("[0-9]"))
				return true;
		}
		return false;
	}
	public JPanel getContentPane() {
		 return this.contentPane;
	 }
}
