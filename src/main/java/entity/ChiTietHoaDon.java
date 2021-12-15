package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SanPhamDAO;

public class ChiTietHoaDon {
	private int id;
	private int soLuong;
	private double donGia;
	public HoaDon hoaDon;
	public SanPham sanPham;
	
	public ChiTietHoaDon(int id, int soLuong, double donGia, HoaDon hoaDon, SanPham sanPham) {
		super();
		this.id = id;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
	}
	
	public ChiTietHoaDon(SanPham sanPham, int soLuong, double donGia) {
		super();
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
	
	public ChiTietHoaDon(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.soLuong = rs.getInt("soLuong");
		this.donGia = rs.getDouble("donGia");
		try {
			this.sanPham = new SanPham(rs);
		}catch (Exception e) {
			this.sanPham = new SanPhamDAO().getSanPham(rs.getInt("maSP"));
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [id=" + id + ", soLuong=" + soLuong + ", donGia=" + donGia + ", hoaDon=" + hoaDon
				+ ", sanPham=" + sanPham + "]";
	}
	
	public double tinhThanhTien() {
		return this.donGia * this.soLuong;
	}
}