package entity;

import java.util.ArrayList;

public class NhanVienBanHang extends NhanVien {
	private int caLamViec;
	public ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();
	
	public NhanVienBanHang(int maNv, String tenNv, String soDienThoai, String diaChi, TaiKhoan taiKhoan, int caLamViec,
			String email,ArrayList<HoaDon> hoaDons) {
		super(maNv, tenNv, soDienThoai, diaChi, taiKhoan,email);
		this.caLamViec = caLamViec;
		this.hoaDons = hoaDons;
	}

	public int getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(int caLamViec) {
		this.caLamViec = caLamViec;
	}

	public ArrayList<HoaDon> getHoaDons() {
		return hoaDons;
	}

	public void setHoaDons(ArrayList<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

	@Override
	public String toString() {
		return "NhanVienBanHang [caLamViec=" + caLamViec + ", hoaDons=" + hoaDons + ", taiKhoan=" + taiKhoan + "]";
	}

	
}