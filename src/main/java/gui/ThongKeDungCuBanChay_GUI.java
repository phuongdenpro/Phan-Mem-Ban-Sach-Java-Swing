package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.SanPhamDAO;
import entity.SanPham;
import util.Currency;
import util.Ngay;
import util.Pair;
import util.Placeholder;
import util.Printer;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class ThongKeDungCuBanChay_GUI extends Printer {

	private JPanel contentPane;
	private JTextField txtTuNgay;
	private JTextField txtToiNgay;
	private Map<SanPham, Integer> dssp;
	private DefaultTableModel modelDSSP;
	private JTable tblDSSP;
	DialogDatePicker f = new DialogDatePicker();
	private kDatePicker dpTuNgay;
	private kDatePicker dpToiNgay;
	private JComboBox comboBox;
	private JComboBox cboLoaiTK;
	private JLabel lblTongSo;
	private int soLuongSP;
	private JTextField txtThangNam;
	private JComboBox cboThang;
	private JComboBox cboNam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeDungCuBanChay_GUI frame = new ThongKeDungCuBanChay_GUI();
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
	public ThongKeDungCuBanChay_GUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Thống kê dụng cụ bán chạy");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblThongKeTheo = new JLabel("Thống kê theo: ");
		panel_2.add(lblThongKeTheo);
		
		
		DefaultComboBoxModel<String> modelLoai = new DefaultComboBoxModel<String>();
		cboLoaiTK = new JComboBox(modelLoai);
		panel_2.add(cboLoaiTK);
		modelLoai.addElement("Tùy chỉnh");
		modelLoai.addElement("Ngày hôm nay");
		modelLoai.addElement("Ngày hôm qua");
		modelLoai.addElement("7 ngày qua");
		modelLoai.addElement("Theo tháng");
		modelLoai.addElement("Theo năm");
		
//		thang nam, tuy chinh
		JPanel pnTextField = new JPanel();
		panel_2.add(pnTextField);
		pnTextField.setVisible(false);
		
		JPanel pnThang = new JPanel();
		pnTextField.add(pnThang);
		
		JLabel lblThang = new JLabel("Tháng: ");
		pnThang.add(lblThang);
		
		cboThang = new JComboBox();
		pnThang.add(cboThang);
		for(int i=1; i<=12; i++)
			cboThang.addItem("Tháng "+i);
		cboThang.setSelectedIndex(Ngay.homNay().getMonth());
		
		JPanel pnNam = new JPanel();
		pnTextField.add(pnNam);
		
		JLabel lblNam = new JLabel("Năm: ");
		pnNam.add(lblNam);
		
		cboNam = new JComboBox();
		pnNam.add(cboNam);
		for(int i=2015; i<=Ngay.homNay().getYear() + 1900; i++)
			cboNam.addItem("Năm "+i);
		cboNam.setSelectedIndex(cboNam.getItemCount() - 1);
		
		JPanel pbTuyChinh = new JPanel();
		panel_2.add(pbTuyChinh);
		
		JLabel lblTuNgay = new JLabel("Từ ngày:  ");
		pbTuyChinh.add(lblTuNgay);
		
		
		dpTuNgay = new kDatePicker();
		pbTuyChinh.add(dpTuNgay);
		
		JLabel lblToiNgay = new JLabel("Tới ngày");
		pbTuyChinh.add(lblToiNgay);
		
		dpToiNgay = new kDatePicker();
		pbTuyChinh.add(dpToiNgay);
		
//		thang nam, tuy chinh
		
		JButton btnThongKe = new JButton("Thống kê", new ImageIcon("data/images/statistics.png"));

		btnThongKe.setBackground(Color.WHITE);
		panel_2.add(btnThongKe);
		
		JButton btnLamMoi = new JButton("Làm mới dữ liệu", new ImageIcon("data/images/refresh.png"));
		btnLamMoi.setBackground(Color.WHITE);
		panel_2.add(btnLamMoi);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		panel_1.setLayout(new BorderLayout(0, 0));
		
		String[] cols = {"Mã sản phẩm", "Tên sản phẩm", "Nhà cung cấp", "Đơn giá", "Số lượng đã bán"};
		modelDSSP = new DefaultTableModel(cols, 0);
		tblDSSP = new JTable(modelDSSP);
		JScrollPane scrollPane = new JScrollPane(tblDSSP);
		panel_1.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);
		
		JPanel pnThongKeChung = new JPanel();
		panel_4.add(pnThongKeChung);
		
		JLabel lblTong = new JLabel("Tổng số dụng cụ đã bán: ");
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongKeChung.add(lblTong);
		
		lblTongSo = new JLabel("20");
		lblTongSo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongKeChung.add(lblTongSo);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JButton btnInBaoCao = new JButton("In báo cáo");
		btnInBaoCao.setBackground(Color.WHITE);
		panel_6.add(btnInBaoCao);
		
		renderData();
		
		btnInBaoCao.addActionListener(e -> {
			panel_6.remove(btnInBaoCao);
			panel_2.remove(btnThongKe);
			panel_2.remove(btnLamMoi);
			printFrame();
			JOptionPane.showMessageDialog(contentPane, "Đã hoàn tất tác vụ");
			panel_6.add(btnInBaoCao);
			panel_6.revalidate();
			panel_6.repaint();
			panel_2.add(btnThongKe);
			panel_2.add(btnLamMoi);
			panel_2.revalidate();
			panel_2.repaint();
		});
	
		btnThongKe.addActionListener((e) -> {
			long ml=System.currentTimeMillis(); 
//	        ml = ml/86400000*86400000;
	        Date now = new Date(ml);
	        
			Date tuNgay = new Date(ml), toiNgay = new Date(ml); // hom nay
			
			if(cboLoaiTK.getSelectedIndex() == 0) { // tuy chinh
	            try {
	                tuNgay = dpTuNgay.getFullDate();
	                toiNgay = dpToiNgay.getFullDate(); 
	            } catch (ParseException e1) {
	                e1.printStackTrace();
	            }
	            
	            if(tuNgay.after(now)){
	                JOptionPane.showMessageDialog(contentPane, "Từ ngày không hợp lệ");
	                return;
	            }
	            
	            if(toiNgay.after(now)){
	                JOptionPane.showMessageDialog(contentPane, "Tới ngày không hợp lệ");
	                return;
	            }
	            
	            if(tuNgay.after(toiNgay)){
	                JOptionPane.showMessageDialog(contentPane, "Ngày không hợp lệ");
	                return;
	            } 
			}else if(cboLoaiTK.getSelectedIndex() == 2){ // hom qua
				tuNgay = Ngay.homQua();
				toiNgay = Ngay.homQua();
			}else if(cboLoaiTK.getSelectedIndex() == 3) { // 7 ngay qua
				toiNgay = Ngay.homNay();
				tuNgay = Ngay._7NgayQua();
			}else if(cboLoaiTK.getSelectedIndex() == 4) { // theo thang
				int thang = cboThang.getSelectedIndex() + 1;
				int nam = cboNam.getSelectedIndex() + 2015;
				
				Pair<Date, Date> range = Ngay.getRangeMonth(thang, nam);
				tuNgay = range.getElement0();
				toiNgay = range.getElement1();
			}else if(cboLoaiTK.getSelectedIndex() == 5) { // theo nam
				int nam = cboNam.getSelectedIndex() + 2015;
				
				Pair<Date, Date> range = Ngay.getRangeYear(nam);
				tuNgay = range.getElement0();
				toiNgay = range.getElement1();
			}
			
		    try {
				renderData(tuNgay, toiNgay);
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
		
		cboLoaiTK.addActionListener((e) -> {
			if(cboLoaiTK.getSelectedIndex() == 0) {
				pbTuyChinh.setVisible(true);
				pnTextField.setVisible(false);
			}else if(cboLoaiTK.getSelectedIndex() == 4){ // thang
				pnTextField.setVisible(true);
				pbTuyChinh.setVisible(false);
				pnThang.setVisible(true);
				pnNam.setVisible(true);
			}else if(cboLoaiTK.getSelectedIndex() == 5){ // thang
				pnTextField.setVisible(true);
				pbTuyChinh.setVisible(false);
				pnThang.setVisible(false);
				pnNam.setVisible(true);
			}else {
				pnTextField.setVisible(false);
				pbTuyChinh.setVisible(false);
			}
		});
	}
	
	public void renderData() throws SQLException {
		dssp = new SanPhamDAO().thongKeSPBanChay(false, 30);
		tblDSSP.clearSelection();
		modelDSSP.getDataVector().removeAllElements();
		soLuongSP = 0;
		dssp.forEach((sp, soLuongDaBan) -> {
			modelDSSP.addRow(new Object[] {
				sp.getMaSp(),
				sp.getTenSp(),
				sp.getNhaCungCap().getTenNCC(),
				Currency.format(sp.getGiaSp()).toString(),
				soLuongDaBan
			});
			soLuongSP += soLuongDaBan;
		});
		lblTongSo.setText(String.valueOf(soLuongSP));
		tblDSSP.revalidate();
		tblDSSP.repaint();
	}
	
	public void renderData(Date tuNgay, Date toiNgay) throws SQLException {
		long soNgay = Ngay.tinhKhoangNgay(tuNgay, toiNgay);
		int minSoLuong = 0;
		if(soNgay <= 3) {
			minSoLuong = 5; 
		}else if(soNgay <= 7)
			minSoLuong = 15;
		else if(soNgay <= 15)
			minSoLuong = 30;
		else if(soNgay <= 30)
			minSoLuong = 60;
		else if(soNgay <= 100)
			minSoLuong = 100;
		else
			minSoLuong = 200;

		dssp = new SanPhamDAO().thongKeSPBanChay(tuNgay, toiNgay, false, minSoLuong);
		tblDSSP.clearSelection();
		modelDSSP.getDataVector().removeAllElements();
		soLuongSP = 0;
		dssp.forEach((sp, soLuongDaBan) -> {
			modelDSSP.addRow(new Object[] {
				sp.getMaSp(),
				sp.getTenSp(),
				sp.getNhaCungCap().getTenNCC(),
				Currency.format(sp.getGiaSp()).toString(),
				soLuongDaBan
			});
			soLuongSP += soLuongDaBan;
		});
		lblTongSo.setText(String.valueOf(soLuongSP));
		tblDSSP.revalidate();
		tblDSSP.repaint();
	}
	
	

	public JPanel getContentPane() {
		return this.contentPane;
	}
}
