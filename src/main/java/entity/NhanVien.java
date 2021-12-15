package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class NhanVien {
	private int maNv;
	private String tenNv;
	private String soDienThoai;
	private String diaChi;
	private int maTk;
	private String tenTk;
	
	public int getMaTk() {
		return maTk;
	}

	public void setMaTk(int maTk) {
		this.maTk = maTk;
	}

	public TaiKhoan taiKhoan;
	public int caLamViec;
	public int chucNang;
	
	public NhanVien(int maNv, String tenNv, String soDienThoai, String diaChi, TaiKhoan taiKhoan, int caLamViec, int chucNang) {
		super();
		this.maNv = maNv;
		this.tenNv = tenNv;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.taiKhoan = taiKhoan;
		this.caLamViec = caLamViec;
		this.chucNang = chucNang;
	}
	
	public NhanVien(String tenNv, String soDienThoai, String diaChi, int caLamViec, int chucNang) {
		super();
		this.tenNv = tenNv;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.caLamViec = caLamViec;
		this.chucNang = chucNang;
	}
	
	public NhanVien(int maNv, String tenNv, String soDienThoai, String diaChi, TaiKhoan taiKhoan) {
		super();
		this.maNv = maNv;
		this.tenNv = tenNv;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.taiKhoan = taiKhoan;
	}
	
	public NhanVien(int maNv, String tenNv, String soDienThoai, String diaChi,int caLam,int chucNang) {
		super();
		this.maNv = maNv;
		this.tenNv = tenNv;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.caLamViec = caLam;
		this.chucNang = chucNang;
	}

	
	public NhanVien(ResultSet rs) throws SQLException {
		this.maNv = rs.getInt("maNV");
		this.tenNv = rs.getString("tenNv");
		this.soDienThoai = rs.getString("soDienThoai");
		this.diaChi = rs.getString("diaChi");
		this.caLamViec = rs.getInt("CaLamViec");
		this.chucNang = rs.getInt("ChucNang");
		this.maTk = rs.getInt("TaiKhoanID");
		this.caLamViec = rs.getInt("caLamViec");
		this.chucNang = rs.getInt("chucNang");
//		this.tenTk = rs.getString("TaiKhoan");
	}

	public String getTenTk() {
		return tenTk;
	}

	public void setTenTk(String tenTk) {
		this.tenTk = tenTk;
	}

	public NhanVien(int ma, String ten, String sdt, String diaChi2, int caLam) {
		// TODO Auto-generated constructor stub
		super();
		this.maNv = ma;
		this.tenNv = ten;
		this.soDienThoai = sdt;
		this.diaChi = diaChi2;
		this.caLamViec = caLam;
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
	
	

	public int getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(int caLamViec) {
		this.caLamViec = caLamViec;
	}

	public int getChucNang() {
		return chucNang;
	}

	public void setChucNang(int chucNang) {
		this.chucNang = chucNang;
	}

	@Override
	public String toString() {
		return "NhanVien [maNv=" + maNv + ", tenNv=" + tenNv + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi
				+ ", taiKhoan=" + taiKhoan + ", caLamViec=" + caLamViec + ", chucNang=" + chucNang + "]";
	}

	
	
	
}