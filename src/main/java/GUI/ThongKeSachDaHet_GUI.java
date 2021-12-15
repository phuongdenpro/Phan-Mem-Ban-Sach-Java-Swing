package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.SanPhamDAO;
import entity.SanPham;
import util.Currency;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

public class ThongKeSachDaHet_GUI extends JFrame {

	private JPanel contentPane;
	private List<SanPham> dssp;
	private DefaultTableModel modelDSSP;
	private JTable tblDSSP;
	private JLabel lblSoLuongHet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeSachDaHet_GUI frame = new ThongKeSachDaHet_GUI();
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
	public ThongKeSachDaHet_GUI() throws SQLException {
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
		
		JPanel pnTitle = new JPanel();
		panel.add(pnTitle);
		
		JLabel lblThongKeSanPhamDaHet = new JLabel("Thống kê sách đã hết");
		lblThongKeSanPhamDaHet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTitle.add(lblThongKeSanPhamDaHet);
		
		JPanel pnTable = new JPanel();
		contentPane.add(pnTable, BorderLayout.CENTER);
		
		pnTable.setLayout(new BorderLayout(0, 0));
		String[] cols = {"Mã sản phẩm", "Tên sản phẩm", "Tác giả", "Số trang", "Tác giả", "Loại sản phẩm", "Nhà xuất bản", "Đơn giá"};
		modelDSSP = new DefaultTableModel(cols, 0);
		tblDSSP = new JTable(modelDSSP);
		JScrollPane scrollPane = new JScrollPane(tblDSSP);
		pnTable.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		pnTable.add(panel_1, BorderLayout.SOUTH);
		
		JPanel pnThongKeChung = new JPanel();
		panel_1.add(pnThongKeChung);
		
		JLabel lblSk = new JLabel("Số lượng sách đã hết: ");
		lblSk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongKeChung.add(lblSk);
		
		lblSoLuongHet = new JLabel("0");
		lblSoLuongHet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongKeChung.add(lblSoLuongHet);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		JButton btnInBaoCao = new JButton("In báo cáo");
		btnInBaoCao.setBackground(Color.WHITE);
		panel_6.add(btnInBaoCao);
		
		renderData();
		
		btnInBaoCao.addActionListener(e -> {
			JOptionPane.showMessageDialog(contentPane, "In báo cáo thành công");
			
		});
	}
	
	public void renderData() throws SQLException {
		dssp = new SanPhamDAO().getSanPhamDaHet(true);
		modelDSSP.getDataVector().removeAllElements();
		
		dssp.forEach(sp -> {
			modelDSSP.addRow(new Object[] {
					sp.getMaSp(),
					sp.getTenSp(),
					sp.getTacGia(),
					sp.getSoTrang(),
					sp.getNamXuatBan(),
					sp.getLoaiSanPham().getTenLoai(),
					sp.getNhaCungCap().getTenNCC(),
					Currency.format(sp.getGiaSp()).toString()
			});
		});
		lblSoLuongHet.setText(String.valueOf(dssp.size()));
		tblDSSP.revalidate();
		tblDSSP.repaint();
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
}
