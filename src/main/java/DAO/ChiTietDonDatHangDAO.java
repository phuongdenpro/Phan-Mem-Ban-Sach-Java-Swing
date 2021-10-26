package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectDB.ConnectDB;
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
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
	}

}
