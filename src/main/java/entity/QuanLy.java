package entity;

public class QuanLy extends NhanVien {
	
	public QuanLy(int maNv, String tenNv, String soDienThoai, String diaChi, TaiKhoan taiKhoan,String email) {
		super(maNv, tenNv, soDienThoai, diaChi, taiKhoan,email);
	}

	@Override
	public String toString() {
		return "QuanLy []";
	}
	
	
}