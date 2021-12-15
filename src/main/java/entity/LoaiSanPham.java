package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoaiSanPham {
	private int maLoai;
	private String tenLoai;
	public ArrayList<SanPham> sanPhams = new ArrayList<SanPham>();
	
	public LoaiSanPham(int maLoai, String tenLoai, ArrayList<SanPham> sanPhams) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.sanPhams = sanPhams;
	}
	public LoaiSanPham(int maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		
	}
	public LoaiSanPham() {
		
	}
	public LoaiSanPham(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public LoaiSanPham(ResultSet rs) throws SQLException {
		this.maLoai = rs.getInt("MaLoai");
		this.tenLoai = rs.getString("TenLoai");
	}
	

	public int getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(int maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public ArrayList<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(ArrayList<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}

	@Override
	public String toString() {
		return "LoaiSanPham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", sanPhams=" + sanPhams + "]";
	}
	
	
}