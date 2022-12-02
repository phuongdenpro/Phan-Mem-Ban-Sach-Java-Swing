package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDAO;
import entity.NhanVien;

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
import javax.swing.SwingConstants;

public class TimKiemNhanVien_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenNv,txtSdt,txtdiaChi;
	private JCheckBox chkTenNv, chkSdt,chkDiaChi;
	private JButton btnTimKiem,btnRefresh;
	private DefaultTableModel modelNhanVien;
	private JTable tblKetQua;
	private ArrayList<NhanVien> dsnv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemNhanVien_GUI frame = new TimKiemNhanVien_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimKiemNhanVien_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnTieuDe = new JPanel();
		contentPane.add(pnTieuDe, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("Tìm kiếm nhân viên");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pnTieuDe.add(lblTieuDe);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED, null, null, null, null), 
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
		
		JPanel pnTenNv = new JPanel();
		FlowLayout fl_pnTenNv = (FlowLayout) pnTenNv.getLayout();
		fl_pnTenNv.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenNv);
		
		JLabel lblTenNv = new JLabel("Tên NV");
		lblTenNv.setPreferredSize(new Dimension(80, 14));
		pnTenNv.add(lblTenNv);
		
		txtTenNv = new JTextField();
		txtTenNv.setPreferredSize(new Dimension(200, 20));
		pnTenNv.add(txtTenNv);
		txtTenNv.setColumns(20);
		chkTenNv = new JCheckBox("");
		pnTenNv.add(chkTenNv);
		
		JPanel pnSdt = new JPanel();
		FlowLayout fl_pnSdt = (FlowLayout) pnSdt.getLayout();
		fl_pnSdt.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSdt);
		
		JLabel lblSdt = new JLabel("Số Đt");
		lblSdt.setPreferredSize(new Dimension(80, 14));
		pnSdt.add(lblSdt);
		
		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(200, 20));
		txtSdt.setColumns(20);
		pnSdt.add(txtSdt);
		
		chkSdt = new JCheckBox("");
		pnSdt.add(chkSdt);
		
		JPanel pnDiaChi = new JPanel();
		FlowLayout fl_pnDiaChi = (FlowLayout) pnDiaChi.getLayout();
		pnThongTin.add(pnDiaChi);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(80, 14));
		pnDiaChi.add(lblDiaChi);
		
		txtdiaChi = new JTextField();
		txtdiaChi.setPreferredSize(new Dimension(200, 20));
		pnDiaChi.add(txtdiaChi);
		txtdiaChi.setColumns(20);
		
		chkDiaChi = new JCheckBox("");
		pnDiaChi.add(chkDiaChi);
		
		JPanel pnTim = new JPanel();
		FlowLayout fl_pnTim = new FlowLayout();
		pnTim.setLayout(fl_pnTim);
		pnThongTin.add(pnTim);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(Color.WHITE);
		pnTim.add(btnTimKiem);
		
		btnRefresh = new JButton("Làm mới");
		btnRefresh.setBackground(Color.WHITE);
		pnTim.add(btnRefresh);
		
		JPanel pnRight = new JPanel();
		pnRight.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		contentPane.add(pnRight, BorderLayout.CENTER);
		pnRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnRightTop = new JPanel();
		pnRight.add(pnRightTop, BorderLayout.NORTH);
		
		JLabel lblKqTim = new JLabel("kết quả tìm kiếm");
		lblKqTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnRightTop.add(lblKqTim);
		
		JPanel pnRightBottom = new JPanel();
		pnRightBottom.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		pnRight.add(pnRightBottom);
		
		String[] cols = {"Mã Nhân Viên", "Tên Nhân Viên", "Số điện thoại", "Địa chỉ","Ca làm"};
		modelNhanVien = new DefaultTableModel(cols, 0);
		pnRightBottom.setLayout(new BorderLayout(0, 0));
		tblKetQua = new JTable(modelNhanVien);
		JScrollPane srcTblKetQua = new JScrollPane(tblKetQua);
		pnRightBottom.add(srcTblKetQua);
		
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				if(!chkTenNv.isSelected() && !chkSdt.isSelected() && !chkDiaChi.isSelected()) {
//					JOptionPane.showMessageDialog(contentPane, "chưa chọn tìm kiếm, không thể tìm");
//					modelNhanVien.setRowCount(0);
//					return;
//				}
//				
				String ten = txtTenNv.getText();
				String sdt = txtSdt.getText();
				String diaChi = txtdiaChi.getText();
				
				String where = "";
				
				boolean chonTen = chkTenNv.isSelected();
				boolean chonSdt = chkSdt.isSelected();
				boolean chonDiaChi = chkDiaChi.isSelected();
				
				if(chonTen) 
					where += " NhanVien.TenNv like N'" + ten + "'";
				else 
					where  += " NhanVien.TenNv like N'%" + ten + "%'";
				//
				if(chonSdt)
					where += " and NhanVien.SoDienThoai like '" + sdt + "'";
				else 
					where += " and NhanVien.SoDienThoai like '%" + sdt + "%'";
				//
				if(chonDiaChi)  
					where += " and NhanVien.DiaChi like N'" + diaChi + "'";
				else
					where += " and NhanVien.DiaChi like N'%" + diaChi + "%'";
				System.out.println(where);
								
				try {
					dsnv = new NhanVienDAO().TimKiem(where);
					if(dsnv.size() == 0) {
						modelNhanVien.setRowCount(0);
						JOptionPane.showMessageDialog(contentPane, "Không tìm thấy nhân viên nào");
						return;
					}
					renderDataTimKiem(dsnv);					
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				
			}

			private void renderDataTimKiem(ArrayList<NhanVien> dsnv) {
				// TODO Auto-generated method stub
				modelNhanVien.setRowCount(0);
				for(NhanVien nv: dsnv) {
					modelNhanVien.addRow(new Object[] {nv.getMaNv(),nv.getTenNv(),nv.getSoDienThoai(),nv.getDiaChi(),nv.getCaLamViec()});
				}
				tblKetQua.revalidate();
				tblKetQua.repaint();
				
			}});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTenNv.setText("");
				txtdiaChi.setText("");
				txtSdt.setText("");
				chkDiaChi.setSelected(false);
				chkSdt.setSelected(false);
				chkTenNv.setSelected(false);
				modelNhanVien.setRowCount(0);
			}});
		
		
	}

}
