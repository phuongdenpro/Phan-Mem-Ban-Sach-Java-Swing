package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

public class NhanVienDAO extends ConnectDB{

	public NhanVienDAO() throws SQLException {
		super();
	}
	
//	lấy thông tin nhân viên bằng mã tài khoản
	public NhanVien getNhanVienByMaTK(int taiKhoanID) {
        PreparedStatement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.NhanVien where taiKhoanID = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, taiKhoanID);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            
            NhanVien nv = new NhanVien(rs);
            return nv;
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
	
}
