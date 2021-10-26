package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class TaiKhoanDAO extends ConnectDB{

	public TaiKhoanDAO() throws SQLException {
		super();
	}

//	lấy mật khẩu
	public String getMatKhau(String taiKhoan) {
		String matKhau = "";
		PreparedStatement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.TaiKhoan where taiKhoan = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, taiKhoan);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
                return null;
            printResultSet(rs);
            matKhau = rs.getString("matKhau");
//                
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matKhau;
	}
	
//	thêm tài khoản mới cho khách hàng
	public boolean themTaiKhoan(KhachHang kh, String taiKhoan, String matKhau) {
		PreparedStatement stmt = null;
        try {
        	String sql = "SELECT id from dbo.TaiKhoan WHERE TaiKhoan = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, taiKhoan);
            ResultSet rs = stmt.executeQuery();
        	if(rs.next()) {
        		return false;
        	}

            sql = "INSERT INTO dbo.TaiKhoan (taiKhoan, matKhau) values(?, ?)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, taiKhoan);
            stmt.setString(2, matKhau);
            int n = stmt.executeUpdate();
//            
            if(n == 0)
                return false;
            
            System.out.println(getLatestID());
            
            KhachHangDAO khachHangDao = new KhachHangDAO();
            if(!khachHangDao.themKhachHang(kh, getLatestID())) 
            	return false;
//                
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
	
//	lấy id cuối cùng
    public int getLatestID() {
        int id = 0;
        Statement stmt = null;
        try {
            String sql = "SELECT * FROM dbo.TaiKhoan";
            stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            id = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
    
    public TaiKhoan getTaiKhoan(String taiKhoan) {
    	
    	PreparedStatement stmt = null;
    	
        try {

            String sql = "SELECT * FROM dbo.TaiKhoan where taiKhoan = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, taiKhoan);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
                return null;
            
            TaiKhoan tk = new TaiKhoan(rs);
            return tk;
            
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
    
//    từ tài khoản -> trả về nhân viên hoặc Khách hàng
    public Object getNguoiDung(String taiKhoan) {
    	PreparedStatement stmt = null;
        try {

            String sql = "SELECT id FROM dbo.TaiKhoan where taiKhoan = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, taiKhoan);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
                return null;
            
            int id = rs.getInt("id");
            
            // kiểm tra xem có phải tài khoản khách hàng không ?
            KhachHang kh = new KhachHangDAO().getKhachHangByMaTK(id);
            if(kh != null)
            	return kh;
            
            NhanVien nv = new NhanVienDAO().getNhanVienByMaTK(id);
            if(nv != null)
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
	
	public static void main(String[] args) throws SQLException {
		TaiKhoanDAO taiKhoanDao = new TaiKhoanDAO();
		KhachHang kh = new KhachHang("Trần Văn Nhân", "0987654321", "Thủ đức");
		System.out.println(taiKhoanDao.themTaiKhoan(kh, "nhan12345769", "nhan123456"));
	}
}
