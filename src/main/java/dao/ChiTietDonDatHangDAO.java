package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectdb.ConnectDB;
import entity.ChiTietDonDatHang;
import entity.SanPham;

public class ChiTietDonDatHangDAO extends ConnectDB{
	public ChiTietDonDatHangDAO() throws SQLException {
		super();
	}
	
	public boolean themChiTietDonDatHang(SanPham sp, int maDDH, int soLuong) {
		PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO dbo.ChiTietDonDatHang (maDDH, maSP, soLuong, donGia) values(?, ?, ?, ?)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maDDH);
            stmt.setInt(2, sp.getMaSp());
            stmt.setInt(3, soLuong);
            stmt.setDouble(4, sp.getGiaSp());
            int n = stmt.executeUpdate();
//            
            if(n == 0)
                return false;
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
	}
	
	
	
	public boolean xoaChiTietDonDatHang(int maSP, int maDDH) {
		PreparedStatement stmt = null;
        try {
            String sql = "DELETE from dbo.ChiTietDonDatHang where maDDH = ? and maSP = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maDDH);
            stmt.setInt(2, maSP);
            
            int n = stmt.executeUpdate();
//            
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
	}
	
	public boolean xoaHetChiTietDonDatHang(int maDDH) {
		PreparedStatement stmt = null;
        try {
            String sql = "DELETE from dbo.ChiTietDonDatHang where maDDH = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maDDH);
            
            int n = stmt.executeUpdate();
//            
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
	}
	
	public ArrayList<ChiTietDonDatHang> getDSChiTietDDH(int maDDH){
		ArrayList<ChiTietDonDatHang> dsDDH = new ArrayList<ChiTietDonDatHang>();
		PreparedStatement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.ChiTietDonDatHang where maDDH = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maDDH);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
//            	printResultSet(rs);
                ChiTietDonDatHang chiTietDDH = new ChiTietDonDatHang(rs);
                dsDDH.add(chiTietDDH);
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
        return dsDDH;
	}
	
    public boolean themSanPhamVaoDonDatHang(int maDDH, SanPham sp, int soLuong) {
        PreparedStatement stmt = null;

        try {
        	

//          kiểm tra xem đã có sản phẩm đó trong đơn đặt hàng chưa
        	String sql = "UPDATE dbo.ChiTietDonDatHang SET SoLuong = ? WHERE maDDH = ? and MaSP = ?";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	prpStmt.setInt(1, soLuong);
        	prpStmt.setInt(2, maDDH);
        	prpStmt.setInt(3, sp.getMaSp());
            int n = prpStmt.executeUpdate();
            
            if(n == 0) { // chưa có sản phẩm đó trong đơn đặt hàng
            	ChiTietDonDatHangDAO chiTietDDHDao = new ChiTietDonDatHangDAO();
            	return chiTietDDHDao.themChiTietDonDatHang(sp, maDDH, soLuong);
            }
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
    	
    	return false;
    }

}
