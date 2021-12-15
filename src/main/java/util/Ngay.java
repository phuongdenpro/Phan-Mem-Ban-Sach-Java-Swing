package util;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Ngay {
	public static Date now = new Date((long)System.currentTimeMillis());
	
	public static Date homNay() {
		return now;
	}
	
	
	public static Date homQua() {	
		if(now.getDate() == 1) {
			if(now.getMonth() == 0)
				return new Date(now.getYear()-1, 12, 31);
			else 
				return new Date(now.getYear(), now.getMonth()-1, Ngay.getSoNgayTrongThang(now.getMonth(), now.getYear()+1900));
		}else {
			return new Date(now.getYear(), now.getMonth(), now.getDate()-1);
		}
			
	}
	
	public static Date _7NgayQua() {	
		if(now.getDate() <= 7) {
			if(now.getMonth() == 0)
				return new Date(now.getYear()-1, 12, 32-(7-now.getDate()));
			else 
				return new Date(now.getYear(), now.getMonth()-1, Ngay.getSoNgayTrongThang(now.getMonth(), now.getYear()+1900)-(7-now.getDate()));
		}else {
			return new Date(now.getYear(), now.getMonth(), now.getDate()-7);
		}
			
	}
	
	public static Pair<Date, Date> getRangeMonth(int thang, int nam){
		Date tuNgay = new Date(nam - 1900, thang-1, 1);
		Date toiNgay = new Date(nam - 1900, thang-1, getSoNgayTrongThang(thang, nam));
		
		return Pair.createPair(tuNgay, toiNgay);
	}
	
	public static Pair<Date, Date> getRangeYear(int nam){
		Date tuNgay = new Date(nam, 0, 1);
		Date toiNgay = new Date(nam, 11, 31);
		
		return Pair.createPair(tuNgay, toiNgay);
	}
	
	public static Date _1ThangQua() {	
		if(now.getMonth() == 0)
			return new Date(now.getYear()-1, 12, now.getDate());
		else 
			return new Date(now.getYear(), now.getMonth()-1, now.getDate());
	}
	
	public static Date _1NamQua() {	
		return new Date(now.getYear()-1, now.getMonth(), now.getDate());
		
	}
	
	public static int getSoNgayTrongThang(int m, int y) {
		 if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)
			 return 31;
		 else if(m == 4 || m == 6 || m == 9 || m == 11)
			 return 30;
		 else if(y % 400 == 0 || (y % 100 == 0 && y % 4 != 0))
			 return 29;
		 else return 29;
	}
	
	public static long tinhKhoangNgay(Date d1, Date d2) {
		return (long) (d2.getTime() - d1.getTime()) / 86400000 + 1;
	}
	
	
	public static String convertTimeToString(Timestamp ts) {
		if (ts != null) {
			LocalDateTime datetime = ts.toLocalDateTime();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        return datetime.format(format);
	        
	    }else
	    	return ""; 
	}
	
	public static Timestamp stringToTimestamp(String s) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.from(format.parse(s));
		
		Timestamp ts = Timestamp.valueOf(localDateTime);
		return ts;
	}
	
	public static Timestamp stringDateToTimestamp(String s) {
		s += " 00:00:00";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.from(format.parse(s));
		
		Timestamp ts = Timestamp.valueOf(localDateTime);
		return ts;
	}
	
	public static boolean getCa(Timestamp ts) {
		int hours = ts.getHours();
//		ts.getTime
		System.out.println(hours);
		if(hours > 4 && hours < 16) // ca sang
			return true;
		// ca toi
		return false;
	}
	
	public static Timestamp tuNgay(Date d1) {
		LocalDate localDate = d1.toLocalDate();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Timestamp ts = stringDateToTimestamp(localDate.toString());
		return ts;
	}
	
	public static Timestamp toiNgay(Date d1) {
		LocalDate localDate = d1.toLocalDate();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Timestamp ts = stringDateToTimestamp(localDate.toString());
		ts.setTime(ts.getTime()+ 86400000 -1);
		return ts;
	}
	
	public static void main(String[] args) {
		System.out.println(Ngay.stringToTimestamp("10-11-2021 21:30:50"));
		System.out.println(Ngay.tuNgay(Ngay.homNay()));
//		System.out.println(Ngay.isSang(Ngay.stringToTimestamp("10-11-2021 12:01:00")));
//		System.out.println(Ngay.isSang(Ngay.stringToTimestamp("10-11-2021 23:59:59")));
	}
}
