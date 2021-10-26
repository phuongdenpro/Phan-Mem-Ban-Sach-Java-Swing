package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import entity.KhachHang;
import entity.SanPham;

public class DonDatHangDao extends ConnectDB{

    public DonDatHangDao() throws SQLException {
		super();
	}
    
    public boolean themSanPhamVaoDonDatHang(SanPham sp, int soLuong, int maKH) {
        PreparedStatement stmt = null;
        System.out.println(maKH);
        try {
        	

            String sql = "SELECT maDDH FROM dbo.DonDatHang where maKH = ? and tinhTrang = 0";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maKH);
            ResultSet rsDDH = stmt.executeQuery();
            
//            System.out.println(rsDDH.next());
//          kiểm tra xem đã có đơn đặt hàng chưa đặt của khách hàng đó không
            if(!rsDDH.next()) {
//				Chưa có đơn -> Tạo mới
            	System.out.println(this.taoDDH(maKH));
            	
//            	thêm sản phẩm vào đơn hàng
            	
            	this.themSanPhamVaoDonDatHang(sp, soLuong, maKH);
            }else {
//            	kiểm tra xem đã có sản phẩm đó trong đơn đặt hàng chưa
            	sql = "UPDATE dbo.ChiTietDonDatHang SET SoLuong = ? WHERE maDDH = ? and MaSP = ?";
            	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
            	prpStmt.setInt(1, soLuong);
            	prpStmt.setInt(2, rsDDH.getInt("maDDH"));
            	prpStmt.setInt(3, sp.getMaSp());
                int n = prpStmt.executeUpdate();
                
                if(n == 0) { // chưa có sản phẩm đó trong đơn đặt hàng
                	ChiTietDonDatHangDAO chiTietDDHDao = new ChiTietDonDatHangDAO();
                	return chiTietDDHDao.themChiTietDonDatHang(sp, rsDDH.getInt("maDDH"), soLuong);
                }
                return n > 0;
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
    	
    	return false;
    }
    
    public boolean taoDDH(int maKH) {
    	PreparedStatement stmt = null;
        try {

            String sql = "INSERT INTO dbo.DonDatHang (maKH, tongTien, tinhTrang) values(?, 0, 0)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maKH);
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
    
    public static void main(String[] args) throws SQLException {
//    	KhachHang kh = new KhachHangDAO().getKhachHang(1);
//    	SanPham sp = new SanPhamDAO().getSanPham(17);
    	DonDatHangDao DDHDao = new DonDatHangDao();
//    	
//    	System.out.println(DDHDao.themSanPhamVaoDonDatHang(sp, 1, 1));
	}
}
