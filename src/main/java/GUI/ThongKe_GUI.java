package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.util.Random;

public class ThongKe_GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe_GUI frame = new ThongKe_GUI();
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
	public ThongKe_GUI() {
		setTitle("Thống kê");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JPanel pnContent = new JPanel();
		JScrollPane pane = new JScrollPane(pnContent);
		pane.getVerticalScrollBar().setUnitIncrement(20);
		pane.setPreferredSize(new Dimension(1400, 700));
		pane.setBorder(null);
		contentPane.add(pane);
		pnContent.setLayout(new BoxLayout(pnContent, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		pnContent.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(1300, 150));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 20, 0));
		
		ImageIcon icon_sold = new ImageIcon("data/images/sold.png");
		panel_1.add(this.dashboardGeneralGUI("Đã bán hôm nay", "20 sản phẩm", icon_sold, new Color(32, 178, 170)));
		
		ImageIcon icon_customer = new ImageIcon("data/images/customer.png");
		panel_1.add(this.dashboardGeneralGUI("Khách hàng", "5 khách hàng mới", icon_customer, new Color(0, 255, 127)));
		
		ImageIcon icon_profit = new ImageIcon("data/images/financial-profit.png");
		panel_1.add(this.dashboardGeneralGUI("Lợi nhuận hôm nay", "9.123.000đ", icon_profit, new Color(0, 191, 255)));
		
		ImageIcon icon_traffic = new ImageIcon("data/images/web-traffic.png");
		panel_1.add(this.dashboardGeneralGUI("Lượt truy cập", "123 lượt", icon_traffic, new Color(255, 255, 0)));
		
		JPanel panel_2 = new JPanel();
		pnContent.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setPreferredSize(new Dimension(1300, 1000));
//		panel_2.add(panel_3);
//		JScrollPane scrollPane = new JScrollPane(panel_3);
//		scrollPane.setBorder(null);
		panel_2.add(panel_3);
		
		
		
		// chart
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[] {1000, 400};
        gbl_panel_3.rowHeights = new int[] {0};
        gbl_panel_3.columnWeights = new double[]{1.0, 1.0};
        gbl_panel_3.rowWeights = new double[]{1.0};
        panel_3.setLayout(gbl_panel_3);
        
        JPanel panel_5 = new JPanel();
        panel_5.setPreferredSize(new Dimension(10, 1000));
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.insets = new Insets(0, 0, 5, 5);
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.gridx = 0;
        gbc_panel_5.gridy = 0;
        panel_3.add(panel_5, gbc_panel_5);
        
        // lợi nhuận
        JFreeChart jfreechart = createProfitChart();
        panel_5.setLayout(new GridLayout(0, 1, 0, 20));
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        panel_5.add(chartpanel);
        
        // Số lượng sản phẩm còn lại
        JFreeChart productChart = createProductChart();
        ChartPanel chartpanel_1 = new ChartPanel(productChart, true, true, true, false, true);
        panel_5.add(chartpanel_1);
        
        
        JPanel panel_4 = new JPanel();
        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
        gbc_panel_4.insets = new Insets(0, 0, 0, 5);
        gbc_panel_4.fill = GridBagConstraints.BOTH;
        gbc_panel_4.gridx = 1;
        gbc_panel_4.gridy = 0;
        panel_3.add(panel_4, gbc_panel_4);
        
        // tỷ lệ sản phẩm bán ra
        JFreeChart pieChart = createRatioProductSoldChart(createRatioProductSoldDataset());
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
        ChartPanel chartPanel = new ChartPanel(pieChart);
        panel_4.add(chartPanel);
        
        ChartPanel chartPanel_1 = new ChartPanel((JFreeChart) null);
        panel_4.add(chartPanel_1);
	}
	
	private JPanel dashboardGeneralGUI(String title, String content, ImageIcon icon, Color color) {
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(color);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {60, 0};
		gbl_panel_2.rowHeights = new int[] {150, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0};
		panel_2.setLayout(gbl_panel_2);
		
		
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		panel_2.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		panel_7.setBackground(new Color(0, 0, 0, 0));
		
		
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNewLabel);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 0;
		panel_2.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		panel_6.setBackground(new Color(0, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel(title);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setAlignmentY(0.0f);
		panel_6.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(content);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_6.add(lblNewLabel_2);
		
		return panel_2;
	}

	private static JFreeChart createRatioProductSoldChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Tỷ lệ sản phẩm bán ra", dataset, true, true, true);
//        chart.setAntiAlias(true);
        chart.setBorderVisible(false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(new Color(0, 0, 0, 0));
//        chart.setBackgroundImage(null);
        return chart;
    }
	
    private static PieDataset createRatioProductSoldDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Truyện conan", new Double(25.0));
        dataset.setValue("Giáo trình tư tưởng Hồ Chí Minh", new Double(66.0));
        dataset.setValue("Bút bi", new Double(9.0));
        return dataset;
    }
	
	private static JFreeChart createProfitChart() {
		XYDataset xydataset = createProfitDataset();
		String s = "Lợi nhuận";
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(s, "Ngày", "Lợi nhuận theo ngày (ngàn đồng)", xydataset, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setLowerMargin(0.40000000000000002D);
		DecimalFormat decimalformat = new DecimalFormat("00.00");
		numberaxis.setNumberFormatOverride(decimalformat);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
		jfreechart.getPlot().setBackgroundPaint(new Color(0, 0, 0, 0));
		return jfreechart;
	 }

	 private static XYDataset createProfitDataset() {
		TimeSeries timeseries = new TimeSeries("Lợi nhuận", org.jfree.data.time.Day.class);
		Random rand = new Random();
		for(int i=1; i<=30; i++) {
			timeseries.add(new Day(i, 1, 2021), rand.nextDouble()*1000);
		}
		
		return new TimeSeriesCollection(timeseries);
	 }
	
	 private static JFreeChart createProductChart() {
		 JFreeChart barChart = ChartFactory.createBarChart(
		     "Số lượng sản phẩm đã bán và còn lại",
		     "Sản phẩm",
		     "Số lượng",
		     createProductDataset(),
		     PlotOrientation.VERTICAL,
		     true, true, false);
		 barChart.getPlot().setBackgroundPaint(new Color(0, 0, 0, 0));
		 return barChart;
	 }
	 
	 private static CategoryDataset createProductDataset( ) {
	      final String conLai = "Còn lại";
	      final String daBan = "Đã bán";
	      final DefaultCategoryDataset dataset = 
	      new DefaultCategoryDataset( ); 

	      dataset.addValue( 120 , conLai , "Truyện conan" );        
	      dataset.addValue( 50 , conLai , "Giáo trình tư tưởng" );        
	      dataset.addValue( 80 , conLai , "Bút bi" ); 
	      dataset.addValue( 20 , conLai , "Vở viết" );
	      dataset.addValue( 20 , conLai , "Mực" );        

	      dataset.addValue( 12 , daBan , "Truyện conan" );        
	      dataset.addValue( 131 , daBan , "Giáo trình tư tưởng" );       
	      dataset.addValue( 30 , daBan , "Bút bi" );        
	      dataset.addValue( 1 , daBan , "Vở viết" );             
	      dataset.addValue( 13 , daBan , "Mực" );  
	      
	      return dataset; 
	 }
	 
	 
	 public JPanel getContentPane() {
		 return this.contentPane;
	 }
}
