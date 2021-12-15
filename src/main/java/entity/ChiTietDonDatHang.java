package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SanPhamDAO;

public class ChiTietDonDatHang {
	private int id;
	private int soLuong;
	private double donGia;
	public SanPham sanPham;
	public DonDatHang donDatHang;
	
	public ChiTietDonDatHang(int id, int soLuong, double donGia, SanPham sanPham, DonDatHang donDatHang) {
		super();
		this.id = id;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.sanPham = sanPham;
		this.donDatHang = donDatHang;
	}
	
	public ChiTietDonDatHang(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.soLuong = rs.getInt("soLuong");
		this.donGia = rs.getDouble("donGia");
		try {
			this.sanPham = new SanPham(rs);
		}catch (Exception e) {
			this.sanPham = new SanPhamDAO().getSanPham(rs.getInt("maSP"));
		}
	}

	public ChiTietDonDatHang(SanPham sp, int soLuong, double donGia) {
		this.sanPham = sp;
		this.soLuong = soLuong;
		this.donGia = donGia;
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

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public DonDatHang getDonDatHang() {
		return donDatHang;
	}

	public void setDonDatHang(DonDatHang donDatHang) {
		this.donDatHang = donDatHang;
	}
	
	public double tinhThanhTien() {
		return this.donGia * this.soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietDonDatHang [id=" + id + ", soLuong=" + soLuong + ", donGia=" + donGia + ", sanPham=" + sanPham
				+ ", donDatHang=" + donDatHang + "]";
	}
	
	
}