package util;

import java.text.DecimalFormat;

public class Currency {
	private String currency;
	
	public static String format(long a) {
		return new DecimalFormat("###,###,###").format(a)+" VNĐ";
	}
	
	public static String format(double a) {
		return new DecimalFormat("###,###,###").format(a)+" VNĐ";
	}
	
	public static String format(float a) {
		return new DecimalFormat("###,###,###").format(a)+" VNĐ";
	}
	
	public static String format(String a) {
		return new DecimalFormat("###,###,###").format(a)+" VNĐ";
	}
	
}
