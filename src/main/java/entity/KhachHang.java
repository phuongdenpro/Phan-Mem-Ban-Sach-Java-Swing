package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DonDatHangDAO;
import dao.KhachHangDAO;

public class KhachHang {
	private int maKh;
	private String hoTen;
	private String soDienThoai;
	private String diaChi;
	private int maTk;
	private String tenTk;
	
	
	
	public String getTenTk() {
		return tenTk;
	}

	public void setTenTk(String tenTk) {
		this.tenTk = tenTk;
	}

	public int getMaTk() {
		return maTk;
	}

	public void setMaTk(int maTk) {
		this.maTk = maTk;
	}

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
	public KhachHang(int maKh,String hoTen, String soDienThoai, String diaChi) {
		this.maKh = maKh;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
	}
	
	public KhachHang(ResultSet rs) throws SQLException {
		try {
			this.maKh = rs.getInt("MaKH");
		}catch (Exception e) {
			
		}
		try {
			this.hoTen = rs.getString("HoTen");
			this.soDienThoai = rs.getString("SoDienThoai");
			this.diaChi = rs.getString("DiaChi");
			this.maTk = rs.getInt("TaiKhoanID");
			this.tenTk = rs.getString("TaiKhoan");
			
		}catch (Exception e) {
			KhachHang tmp = new KhachHangDAO().getKhachHang(this.maKh);
			this.hoTen = tmp.getHoTen();
			this.soDienThoai = tmp.getSoDienThoai();
			this.diaChi = tmp.getDiaChi();
			try {
				this.maTk = tmp.getTaiKhoan().getId();
				this.tenTk = tmp.getTaiKhoan().getTaiKhoan();
			}catch(Exception ex) {
				//ex.printStackTrace();
			}
			
			}
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