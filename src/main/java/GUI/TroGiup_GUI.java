package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;

public class TroGiup_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel pnContent;
	private JPanel panel_2;
	public JButton btnTroVe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TroGiup_GUI frame = new TroGiup_GUI();
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
	public TroGiup_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 700);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {300, 1050};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(280, 200));
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Trợ giúp");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_4.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_5.setBackground(Color.WHITE);
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnTroVe = new JButton("Trở về");
		btnTroVe.setPreferredSize(new Dimension(200, 23));
		btnTroVe.setBackground(Color.WHITE);
		panel_3.add(btnTroVe, BorderLayout.SOUTH);
		
		List<JLabel> lblDanhSachCauHoi = new ArrayList<JLabel>();
		
		lblDanhSachCauHoi.add(new JLabel("1. Cách mua hàng như thế nào ?"));
		lblDanhSachCauHoi.add(new JLabel("2. Cách xem giỏ hàng như thế nào ?"));
		lblDanhSachCauHoi.add(new JLabel("3. Cách đặt hàng như thế nào ?"));
		lblDanhSachCauHoi.add(new JLabel("4. Cách đi vào trang quản lý ?"));
		
		lblDanhSachCauHoi.forEach(lbl -> {
			panel_5.add(lbl);
		});
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		pnContent = this.troGiup4();
		panel_2.add(pnContent);
		
		List<JPanel> pnTraLoi = new ArrayList<JPanel>();
		pnTraLoi.add(troGiup1());
		pnTraLoi.add(troGiup2());
		pnTraLoi.add(troGiup3());
		pnTraLoi.add(troGiup4());
		
		lblDanhSachCauHoi.forEach(lbl -> {
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			});
			
			lbl.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					
					panel_2.remove(pnContent);
					panel_2.revalidate();
					panel_2.repaint();
					int id = Integer.parseInt(((JLabel)e.getSource()).getText().substring(0, 1)) - 1;
					System.out.println(id);
					pnContent = pnTraLoi.get(id);
					panel_2.add(pnContent);
					panel_2.revalidate();
					panel_2.repaint();
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
		});
	}
	
	private JPanel troGiup1() {
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("Cách mua hàng như thế nào ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. Bấm vào nút \"Thêm vào giỏ hàng\" tại sản phẩm mong muốn.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. Nhập số lượng sản phẩm cần mua (lớn hơn 0).");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_7 = new JLabel("3. Sản phẩm đã được thêm vào giỏ hàng, có thể tiếp tục mua sắm hoặc vào giỏ hàng để đặt hàng.");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_7);
		
		return panel_6;
	}
	
	private JPanel troGiup2() {
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("Cách xem giỏ hàng như thế nào ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. Bấm vào nút \"Xin chào: <Tên bạn>\" trên thanh menu.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. Bấm nút giỏ hàng để đi tới giỏ hàng.");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1);
		
		return panel_6;
	}

	private JPanel troGiup3() {
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("Cách đặt hàng như thế nào ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. Bấm vào nút \"Xin chào: <Tên bạn>\" trên thanh menu.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. Bấm nút \"giỏ hàng\" để đi tới giỏ hàng.");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("3. Điền thông tin cá nhân.");
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1_1);
		
		JLabel lblNewLabel_6_1_1_1 = new JLabel("4. Bấm \"đặt hàng\".");
		lblNewLabel_6_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1_1_1);
		
		JLabel lblNewLabel_6_1_1_1_1 = new JLabel("5. Bấm \"Yes\" để xác nhận đặt hàng.");
		lblNewLabel_6_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1_1_1_1);
		
		return panel_6;
	}

	private JPanel troGiup4() {
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("Cách đi tới trang quản lý ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. Bấm vào nút \"Xin chào: <Tên bạn>\" trên thanh menu.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. Bấm nút \"đi tới trang quản lý\".");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1);
		
		return panel_6;
	}
	
	public JPanel getContenPane() {
		return this.contentPane;
	}
}
