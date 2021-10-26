package entity;

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

	@Override
	public String toString() {
		return "ChiTietDonDatHang [id=" + id + ", soLuong=" + soLuong + ", donGia=" + donGia + ", sanPham=" + sanPham
				+ ", donDatHang=" + donDatHang + "]";
	}
	
	
}