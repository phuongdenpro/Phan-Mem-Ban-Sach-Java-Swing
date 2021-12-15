package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDonDAO;
import entity.HoaDon;
import util.Currency;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class TimKiemHoaDon_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private ArrayList<HoaDon> dshd;
	private DefaultTableModel modelKq;
	private JTable tblKetQua;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemHoaDon_GUI frame = new TimKiemHoaDon_GUI();
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
	public TimKiemHoaDon_GUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		JLabel lblThongTinTimKiem = new JLabel("Thông tin tìm kiếm");
		lblThongTinTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_8.add(lblThongTinTimKiem);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_2.add(panel_7);
		
		JLabel lblTimKiemChinhXac = new JLabel("Tìm kiếm chính xác");
		panel_7.add(lblTimKiemChinhXac);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5);
		
		JLabel lblTenKH = new JLabel("Tên KH");
		lblTenKH.setPreferredSize(new Dimension(100, 14));
		panel_5.add(lblTenKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setPreferredSize(new Dimension(200, 20));
		panel_5.add(txtTenKH);
		txtTenKH.setColumns(20);
		
		JCheckBox chkTenKH = new JCheckBox("");
		panel_5.add(chkTenKH);
		
		JPanel panel_5_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5_1.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5_1);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setPreferredSize(new Dimension(100, 14));
		panel_5_1.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setPreferredSize(new Dimension(200, 20));
		txtSDT.setColumns(20);
		panel_5_1.add(txtSDT);
		
		JCheckBox chkSDT = new JCheckBox("");
		panel_5_1.add(chkSDT);
		
		JPanel panel_5_1_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_5_1_1.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5_1_1);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(100, 14));
		panel_5_1_1.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(200, 20));
		txtDiaChi.setColumns(20);
		panel_5_1_1.add(txtDiaChi);
		
		JCheckBox chkDiaChi = new JCheckBox("");
		panel_5_1_1.add(chkDiaChi);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9);
		
		
		panel_9.setLayout(new GridLayout(0, 2, 10, 0));
		ImageIcon icon_timkiem = new ImageIcon("data/images/search_16.png");
		JButton btnTimKiem = new JButton("Tìm kiếm", icon_timkiem);
		btnTimKiem.setBackground(Color.WHITE);
		panel_9.add(btnTimKiem);
		
		ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
		JButton btnLamMoi = new JButton("Làm mới dữ liệu", icon_refresh);
		btnLamMoi.setBackground(Color.WHITE);
		panel_9.add(btnLamMoi);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		
		JLabel lblTimKiem = new JLabel("Tìm kiếm hóa đơn");
		lblTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_3.add(lblTimKiem);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		String[] cols = {"Mã hóa đơn", "Mã khách hàng", "Họ tên", "Số điện thoại", "Địa chỉ", "Tổng tiền", "Ngày lập"};
		modelKq = new DefaultTableModel(cols, 0);
		panel_4.setLayout(new BorderLayout(0, 0));
		tblKetQua = new JTable(modelKq);
		JScrollPane scrollPane = new JScrollPane(tblKetQua);
		panel_4.add(scrollPane);
		
		renderData();
		
		btnTimKiem.addActionListener((e) -> {
			String where = "";
			if(chkTenKH.isSelected()) {
				where += "KhachHang.HoTen like N'" + txtTenKH.getText() + "' and ";
			}else {
				where += "KhachHang.HoTen like N'%" + txtTenKH.getText() + "%' and ";
			}
			
			if(chkSDT.isSelected()) {
				where += "KhachHang.SoDienThoai like N'" + txtSDT.getText() + "' and ";
			}else {
				where += "KhachHang.SoDienThoai like N'%" + txtSDT.getText() + "%' and ";
			}
			
			if(chkDiaChi.isSelected()) {
				where += "KhachHang.DiaChi like N'" + txtDiaChi.getText() + "'";
			}else {
				where += "KhachHang.DiaChi like N'%" + txtDiaChi.getText() + "%'";
			}
			
			System.out.println(where);
			
			try {
				dshd = (ArrayList<HoaDon>) new HoaDonDAO().timKiem(where);
				if(dshd.size() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Không có hóa đơn phù hợp");
					return;
				}
				renderDataTimKiem();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		

		btnLamMoi.addActionListener((e) -> {
			try {
				renderData();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
	}
	
	public void renderData() throws SQLException {
		dshd = new HoaDonDAO().getDSHD();
		
		tblKetQua.clearSelection();
		modelKq.getDataVector().removeAllElements();
		dshd.forEach(hd -> {
			modelKq.addRow(new Object[] {
				hd.getMaHD(), 
				hd.getKhachHang().getMaKh(), 
				hd.getKhachHang().getHoTen(), 
				hd.getKhachHang().getSoDienThoai(),
				hd.getKhachHang().getDiaChi(),
				Currency.format(hd.tinhTongTien()).toString(),
				hd.getNgayMua()
			});
		});
		tblKetQua.revalidate();
		tblKetQua.repaint();
	}
	
	public void renderDataTimKiem() throws SQLException {
		tblKetQua.clearSelection();
		
		modelKq.getDataVector().removeAllElements();
		
		dshd.forEach(hd -> {
			modelKq.addRow(new Object[] {
					hd.getMaHD(), 
					hd.getKhachHang().getMaKh(), 
					hd.getKhachHang().getHoTen(), 
					hd.getKhachHang().getSoDienThoai(),
					hd.getKhachHang().getDiaChi(),
					hd.tinhTongTien(),
					hd.getNgayMua()
				});
		});
		
		tblKetQua.revalidate();
		tblKetQua.repaint();
	}
}
