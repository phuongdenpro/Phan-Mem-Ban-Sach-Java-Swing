package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectdb.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.NhanVien_GUI;

public class NhanVienDAO extends ConnectDB{

	public NhanVienDAO() throws SQLException {
		super();
	}
	
//	lấy thông tin nhân viên bằng mã tài khoản
	public NhanVien getNhanVienByMaTK(int taiKhoanID) {
        PreparedStatement stmt = null;
        try {

            String sql = "select * from NhanVien nv join TaiKhoan tk on nv.TaiKhoanID = tk.ID where taiKhoanID = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, taiKhoanID);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            
            NhanVien nv = new NhanVien(rs);
            nv.setTaiKhoan(new TaiKhoan(taiKhoanID));
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
	
	public List<NhanVien> getDSNV(){
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
	    PreparedStatement stmt = null;
		try {
		
		    String sql = "select * from NhanVien nv join TaiKhoan tk on nv.TaiKhoanID = tk.ID";
		    stmt = this.conn.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();

		    while(rs.next()) {
//		    	printResultSet(rs);
		    	NhanVien nv = new NhanVien(rs);
		    	dsnv.add(nv);
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
		return dsnv;		
	}
	
	public NhanVien getNhanVienByMaNV(int maNV){
	    PreparedStatement stmt = null;
		try {
		
		    String sql = "select * from NhanVien nv join TaiKhoan tk on nv.TaiKhoanID = tk.ID where maNV = ?";
		    stmt = this.conn.prepareStatement(sql);
		    stmt.setInt(1, maNV);
		    ResultSet rs = stmt.executeQuery();

		    if(!rs.next()) {
		    	return null;
		    }
		    
//	    	printResultSet(rs);
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
	
	public boolean suaNV(NhanVien nv) {
	    PreparedStatement stmt = null;
		try {
		
		    String sql = "UPDATE NhanVien set tenNV = ?, soDienThoai = ?, diaChi = ?,CaLamViec = ?,ChucNang = ? where maNV = ?";
		    stmt = this.conn.prepareStatement(sql);
		    
		    stmt.setString(1, nv.getTenNv());
		    stmt.setString(2, nv.getSoDienThoai());
		    stmt.setString(3, nv.getDiaChi());
		    stmt.setInt(4, nv.getCaLamViec());
		    stmt.setInt(5, nv.getChucNang());
		    stmt.setInt(6, nv.getMaNv());
		    
		    int n = stmt.executeUpdate();

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
	

	
	public boolean xoaNV(NhanVien nv) {
	    PreparedStatement stmt = null;
		try {
		    String sql = "DELETE FROM NhanVien where maNV = ?";
		    stmt = this.conn.prepareStatement(sql);	    
		    stmt.setInt(1, nv.getMaNv());
		    System.out.println(sql);
		    
		    int n = stmt.executeUpdate();	    
		    if(n == 0) {
		    	return false;
		    }
		    
		    // xoas tai khoan
		    if(nv.getTaiKhoan() != null)
		    	new TaiKhoanDAO().xoaTaiKhoan(nv.getTaiKhoan().getId());
		    else
		    	new TaiKhoanDAO().xoaTaiKhoan(nv.getMaTk());
		    
		    return true;
		} catch (SQLException e) {
		    //e.printStackTrace();
		} finally {
		    try {
		        stmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		return false;	
	}
	
	public boolean themNhanVien(NhanVien nv, int taiKhoanId) {
    	PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO dbo.NhanVien (TenNV, soDienThoai, diaChi, caLamViec, chucNang, taiKhoanId) values(?, ?, ?, ?, ?, ?)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nv.getTenNv());
            stmt.setString(2, nv.getSoDienThoai());
            stmt.setString(3, nv.getDiaChi());
            stmt.setInt(4, nv.getCaLamViec());
            stmt.setInt(5, nv.getChucNang());
            stmt.setInt(6, taiKhoanId);
            int n = stmt.executeUpdate();
//            
            if(n == 0)
                return false;
            
            return true;
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
        return false;
    }
	
	public static void main(String[] args) throws SQLException {
		System.out.println(new NhanVienDAO().getDSNV());
	}
	
	public ArrayList<NhanVien> TimKiem(String where) {
		// TODO Auto-generated method stub
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		String sqlTimKiem = "select * from dbo.NhanVien where "+ where;
		System.out.println(sqlTimKiem);
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlTimKiem, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				
				NhanVien nv = new NhanVien(result);
				dsnv.add(nv);			
			}
			return dsnv;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return dsnv;
	}
	
}
