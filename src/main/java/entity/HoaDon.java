package entity;

import java.sql.Date;
import java.util.ArrayList;

public class HoaDon {
	private int maHD;
	private double tongTien;
	private Date ngayMua;
	public NhanVienBanHang nhanVienBanHang;
	public KhachHang khachHang;
	public ArrayList<ChiTietHoaDon> chiTietHoaDons = new ArrayList<ChiTietHoaDon>();
	
	public HoaDon(int maHD, double tongTien, Date ngayMua, NhanVienBanHang nhanVienBanHang, KhachHang khachHang,
			ArrayList<ChiTietHoaDon> chiTietHoaDons) {
		super();
		this.maHD = maHD;
		this.tongTien = tongTien;
		this.ngayMua = ngayMua;
		this.nhanVienBanHang = nhanVienBanHang;
		this.khachHang = khachHang;
		this.chiTietHoaDons = chiTietHoaDons;
	}

	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public Date getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}

	public NhanVienBanHang getNhanVienBanHang() {
		return nhanVienBanHang;
	}

	public void setNhanVienBanHang(NhanVienBanHang nhanVienBanHang) {
		this.nhanVienBanHang = nhanVienBanHang;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public ArrayList<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}

	public void setChiTietHoaDons(ArrayList<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", tongTien=" + tongTien + ", ngayMua=" + ngayMua + ", nhanVienBanHang="
				+ nhanVienBanHang + ", khachHang=" + khachHang + ", chiTietHoaDons=" + chiTietHoaDons + "]";
	}
	
	
	
}