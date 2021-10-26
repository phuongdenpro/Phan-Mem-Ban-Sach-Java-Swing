package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVien {
	private int maNv;
	private String tenNv;
	private String soDienThoai;
	private String diaChi;
	public TaiKhoan taiKhoan;
	private String email;
	
	public NhanVien(int maNv, String tenNv, String soDienThoai, String diaChi, TaiKhoan taiKhoan,String email) {
		super();
		this.maNv = maNv;
		this.tenNv = tenNv;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.taiKhoan = taiKhoan;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public NhanVien(ResultSet rs) throws SQLException {
		this.maNv = rs.getInt("maNV");
		this.tenNv = rs.getString("tenNv");
		this.soDienThoai = rs.getString("soDienThoai");
		this.diaChi = rs.getString("diaChi");
	}

	public int getMaNv() {
		return maNv;
	}

	public void setMaNv(int maNv) {
		this.maNv = maNv;
	}

	public String getTenNv() {
		return tenNv;
	}

	public void setTenNv(String tenNv) {
		this.tenNv = tenNv;
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

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	@Override
	public String toString() {
		return "NhanVien [maNv=" + maNv + ", tenNv=" + tenNv + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi
				+ ", taiKhoan=" + taiKhoan + ",Email=" +email+ "]";
	}
	
	
}