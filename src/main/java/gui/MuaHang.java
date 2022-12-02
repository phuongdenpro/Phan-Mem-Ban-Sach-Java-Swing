package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.DonDatHangDAO;
import dao.KhachHangDAO;
import entity.KhachHang;


public class MuaHang extends JFrame{
	private KhachHang khachHang;
	private boolean isPrimary = false;
	public TrangChu_GUI trangChuGUI = new TrangChu_GUI();;
	public GioHang_GUI gioHangGUI = new GioHang_GUI();
//	private QuanLy_GUI quanLyGUI = new QuanLy_GUI();
	private TroGiup_GUI troGiupGUI = new TroGiup_GUI();
	
	private JPanel contentPane = new JPanel();
	
	public static void main(String[] args) throws SQLException {
		MuaHang muaHangGUI = new MuaHang();
		muaHangGUI.setVisible(true);
	}
	
	public MuaHang() throws SQLException {
		this.khachHang = new KhachHangDAO().getKhachHang(1);
		trangChuGUI.setKhachHang(khachHang);
		gioHangGUI.setKhachHang(khachHang);
		renderGUI();
	}
	
	public MuaHang(KhachHang khachHang) throws SQLException {
		this.khachHang = khachHang;
		trangChuGUI = new TrangChu_GUI(khachHang);
		gioHangGUI = new GioHang_GUI(khachHang);
		renderGUI();
	}
	
	public MuaHang(KhachHang khachHang, boolean isPrimary) throws SQLException {
		this.khachHang = khachHang;
		trangChuGUI = new TrangChu_GUI(khachHang);
		gioHangGUI = new GioHang_GUI(khachHang);
		renderGUI();
		this.isPrimary = isPrimary;
		if(isPrimary) {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}
	
	public void renderGUI() throws SQLException {
		
		this.setTitle("Hiệu sách");
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
			
		//		
		this.renderMain(trangChuGUI.getContentPane(), "trangchu");
		trangChuGUI.renderData();
//		contentPane
		handleMenu();
		handleGioHang();
	}
	
	public void renderMain(JPanel contentPane, String tab) {
//		this.remove(this.contentPane);
        this.revalidate();
        this.repaint();
//        this.contentPane = contentPane;
//        this.add(this.contentPane);
        
        
        System.out.println("-> "+tab);
//		if(tab.equals("dangnhap")) {
//			handleLogin();
//			loginGUI.requestFocus();
//		}else if(tab.equals("dangky")) {
//			handleRegister();
//		}else 
		if(tab.equals("trangchu")) {
			
		}else if(tab.equals("giohang")) {
			gioHangGUI.renderData();
		}
//		else if(tab.equals("quanly")) {
//			handleQuanLy();
//		}
		else if(tab.equals("trogiup")) {
			handleTroGiup();
		}
		
		this.setContentPane(contentPane);
        this.revalidate();
        this.repaint();
	}
	
	public void handleMenu() {
		trangChuGUI.mntmGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderMain(gioHangGUI.getContentPane(), "giohang");
			}
		});
		trangChuGUI.mntmThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isPrimary == false)
					setVisible(false);
				else
					System.exit(0);
			}
		});
		
		
		trangChuGUI.lblHelp.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				renderMain(troGiupGUI.getContenPane(), "trogiup");
//				System.out.println(quanLyGUI.get());
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
	}

	
	public void handleTroGiup() {
		troGiupGUI.btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderMain(trangChuGUI.getContentPane(), "trangchu");
			}
		});
	}
	
	public void handleGioHang() {
		gioHangGUI.btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderMain(trangChuGUI.getContentPane(), "trangchu");
			}
		});
		gioHangGUI.mntmThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isPrimary == false)
					setVisible(false);
				else
					System.exit(0);
			}
		});
		gioHangGUI.btnDatHang.addActionListener(new ActionListener() {
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(contentPane, "Xác nhận đặt hàng ?");
				if(choose != 0) {
					return;
				}
				
				try {
					DonDatHangDAO donDatHangDao = new DonDatHangDAO();
					if(donDatHangDao.xacNhanDatHang(khachHang.getMaKh())) {
						JOptionPane.showMessageDialog(contentPane, "Đặt hàng thành công");
						renderMain(trangChuGUI.getContentPane(), "trangchu");
					}else {
						JOptionPane.showMessageDialog(contentPane, donDatHangDao.getError());
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
//		gioHangGUI.mntmGioHang.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				renderMain(gioHangGUI.getContentPane(), "giohang");
//			}
//		});
		
		gioHangGUI.lblHelp.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				renderMain(troGiupGUI.getContenPane(), "trogiup");
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	
	
	
	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
		trangChuGUI.setKhachHang(khachHang);
		gioHangGUI.setKhachHang(khachHang);
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	
}