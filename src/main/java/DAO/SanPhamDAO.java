package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.KhachHang;
import entity.SanPham;

public class SanPhamDAO extends ConnectDB{
	
	public SanPhamDAO() throws SQLException {
		super();
		
	}

    public ArrayList<SanPham> getListSanPham() {
    	ArrayList<SanPham> dataList = new ArrayList<SanPham>();
        Statement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.SanPham inner join loaiSanPham on SanPham.MaLoai = loaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC";
            stmt = this.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs);
            	printResultSet(rs);
                SanPham sanPham = new SanPham(rs);
                dataList.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    
    public ArrayList<SanPham> getListSanPhamByMaLoai(int maLoai) {
    	ArrayList<SanPham> dataList = new ArrayList<SanPham>();
        PreparedStatement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.SanPham where maLoai = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maLoai);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	printResultSet(rs);
                SanPham sanPham = new SanPham(rs);
                dataList.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    
    public SanPham getSanPham(int maSP) {
        PreparedStatement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.SanPham where maSP = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maSP);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            
            SanPham sp = new SanPham(rs);
            return sp;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException {
    	SanPhamDAO sanPhamDao = new SanPhamDAO();
//    	System.out.println(sanPhamDao.getListSanPham());
    	sanPhamDao.getListSanPhamByMaLoai(1);
	}
}
