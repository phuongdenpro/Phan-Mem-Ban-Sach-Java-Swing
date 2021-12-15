package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DonDatHangDAO;
import dao.KhachHangDAO;
import dao.SanPhamDAO;
import entity.KhachHang;
import entity.SanPham;
import util.Currency;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class TimKiemTrangMuaHang_GUI extends JFrame {
	private KhachHang khachHang;
	
	private JPanel contentPane;
	private JTextField txtSoLuong;
	private DefaultTableModel modelTimKiem;
	private JTable tblTimKiem;
	public ArrayList<SanPham> dssp;
	private JButton btnThem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemTrangMuaHang_GUI frame = new TimKiemTrangMuaHang_GUI();
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
	public TimKiemTrangMuaHang_GUI() throws SQLException {
		this.khachHang = new KhachHangDAO().getKhachHang(1);
		GUI();
	}
	
	public TimKiemTrangMuaHang_GUI(KhachHang khachHang) {
		this.khachHang = khachHang;
		GUI();
	}
	
	public void GUI(){
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Kết quả tìm kiếm: Sách tiếng việt");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		String[] cols = {"Tên sản phẩm", "Giá", "Số lượng"};
		modelTimKiem = new DefaultTableModel(cols, 0);
		panel_1.setLayout(new BorderLayout(0, 0));
		tblTimKiem = new JTable(modelTimKiem);
		JScrollPane scrollPane = new JScrollPane(tblTimKiem);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblSoLuong = new JLabel("Số lượng: ");
		panel_2.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		panel_2.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		btnThem = new JButton("Thêm vào giỏ");
		btnThem.setBackground(Color.WHITE);
		panel_2.add(btnThem);
		
		btnThem.addActionListener((e) -> {
			int idx = tblTimKiem.getSelectedRow();
			if(idx != -1) {
				int soLuong = 0;
				try {
					soLuong = Integer.parseInt(txtSoLuong.getText());
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
					return;
				}
				
				if(soLuong < 0) {
					JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
					return;
				}
				
				if(soLuong > dssp.get(idx).getSoLuong()) {
					JOptionPane.showMessageDialog(contentPane, dssp.get(idx).getTenSp()+" chỉ còn "+dssp.get(idx).getSoLuong()+" sản phẩm");
					
					return;
				}
				
				
				boolean kq;
				try {
					kq = new DonDatHangDAO().themSanPhamVaoDonDatHang(dssp.get(idx), soLuong, this.khachHang.getMaKh());
					if(kq) {
						JOptionPane.showMessageDialog(contentPane, "Thêm vào giỏ thành công");
					}else {
						JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
				}
				
			}
		});
	}
	
	public void renderData(String keyword) throws SQLException {
		dssp = new SanPhamDAO().timKiem(keyword);
		tblTimKiem.clearSelection();
		modelTimKiem.getDataVector().removeAllElements();
		
		dssp.forEach(sp -> {
			modelTimKiem.addRow(new Object[] {sp.getTenSp(), Currency.format(sp.getGiaSp()).toString(), sp.getSoLuong()});
		});
		tblTimKiem.revalidate();
		tblTimKiem.repaint();
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	
	

}
