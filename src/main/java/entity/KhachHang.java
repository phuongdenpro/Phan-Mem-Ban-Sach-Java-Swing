package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHang {
	private int maKh;
	private String hoTen;
	private String soDienThoai;
	private String diaChi;
	public ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();
	public TaiKhoan taiKhoan;
	public ArrayList<DonDatHang> donDatHangs = new ArrayList<DonDatHang>();
	
	public KhachHang(int maKh, String hoTen, String soDienThoai, String diaChi, ArrayList<HoaDon> hoaDons,
			TaiKhoan taiKhoan, ArrayList<DonDatHang> donDatHangs) {
		super();
		this.maKh = maKh;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.hoaDons = hoaDons;
		this.taiKhoan = taiKhoan;
		this.donDatHangs = donDatHangs;
	}
	
	public KhachHang(String hoTen, String soDienThoai, String diaChi) {
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
	}
	
	public KhachHang(ResultSet rs) throws SQLException {
		try {
			this.maKh = rs.getInt("maKH");
		}catch (Exception e) {
			
		}
		this.hoTen = rs.getString("hoTen");
		this.soDienThoai = rs.getString("soDienThoai");
		this.diaChi = rs.getString("diaChi");
	}

	public int getMaKh() {
		return maKh;
	}

	public void setMaKh(int maKh) {
		this.maKh = maKh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public ArrayList<HoaDon> getHoaDons() {
		return hoaDons;
	}

	public void setHoaDons(ArrayList<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public ArrayList<DonDatHang> getDonDatHangs() {
		return donDatHangs;
	}

	public void setDonDatHangs(ArrayList<DonDatHang> donDatHangs) {
		this.donDatHangs = donDatHangs;
	}

	@Override
	public String toString() {
		return "KhachHang [maKh=" + maKh + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi
				+ ", hoaDons=" + hoaDons + ", taiKhoan=" + taiKhoan + ", donDatHangs=" + donDatHangs + "]";
	}
	
	
}