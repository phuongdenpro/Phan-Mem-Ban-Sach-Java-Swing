package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectdb.ConnectDB;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;

public class LoaiSanPhamDAO extends ConnectDB{
	
	public LoaiSanPhamDAO() throws SQLException {
		super();
		
	}
	public ArrayList<LoaiSanPham> getDanhSachLoaiSach() throws SQLException {
		ArrayList<LoaiSanPham> dataList = new ArrayList<LoaiSanPham>();
        Statement stmt = this.conn.createStatement();
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        
        try {

            String sql = "SELECT * FROM dbo.LoaiSanPham where LoaiSanPham.TenLoai like N'%Sách%' OR LoaiSanPham.TenLoai like N'%Truyện%'";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs);
//            	printResultSet(rs);
            	LoaiSanPham loaiSp = new LoaiSanPham(rs);
            	loaiSp.setSanPhams(sanPhamDao.getListSanPhamByMaLoai(rs.getInt("maLoai")));
                dataList.add(loaiSp);
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
	public ArrayList<LoaiSanPham> getDanhSachLoaiSanPhamKhac() throws SQLException {
		ArrayList<LoaiSanPham> dataList = new ArrayList<LoaiSanPham>();
        Statement stmt = this.conn.createStatement();
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        
        try {

            String sql = "SELECT * FROM dbo.LoaiSanPham where TenLoai NOT like N'%Sách%' AND TenLoai NOT like N'%Truyện%'";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs);
//            	printResultSet(rs);
            	LoaiSanPham loaiSp = new LoaiSanPham(rs);
            	loaiSp.setSanPhams(sanPhamDao.getListSanPhamByMaLoai(rs.getInt("maLoai")));
                dataList.add(loaiSp);
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
	
	public ArrayList<LoaiSanPham> getDanhSachLoaiSanPham() throws SQLException {
		ArrayList<LoaiSanPham> dataList = new ArrayList<LoaiSanPham>();
        Statement stmt = this.conn.createStatement();
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        
        try {

            String sql = "SELECT * FROM dbo.LoaiSanPham";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	LoaiSanPham loaiSp = new LoaiSanPham(rs);
            	loaiSp.setSanPhams(sanPhamDao.getListSanPhamByMaLoai(rs.getInt("maLoai")));
                dataList.add(loaiSp);
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
	public LoaiSanPham getLoaiByTenLoai(String tenLoai) {
		PreparedStatement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.LoaiSanPham where TenLoai = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, tenLoai);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return null;

			LoaiSanPham loai = new LoaiSanPham(rs);
			return loai;
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
	public boolean createLoaiSp(String tenLoai) {
		PreparedStatement statement = null;

		try {
			String sql = "insert into LoaiSanPham (TenLoai) values(?);";
			statement = conn.prepareStatement(sql);
			statement.setString(1, tenLoai);
			int n = statement.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	public List<LoaiSanPham> timKiem(String key, String val) {
    	Statement stmt = null;
    	List<LoaiSanPham> dssp = new ArrayList<LoaiSanPham>();
        try {
        	System.out.println(key + " " + val);

            String sql = "SELECT * FROM dbo.LoaiSanPham where "+ key +" like N'%"+ val + "%'";
            stmt = this.conn.createStatement();
            
            ResultSet rsLoai = stmt.executeQuery(sql);
            
            System.out.println(rsLoai.getStatement().toString());
            
            while(rsLoai.next()) {
//            	printResultSet(rsLoai);
            	LoaiSanPham sp = new LoaiSanPham(rsLoai);
            //	sp.setChiTietDonDatHangs(new ChiTietDonDatHangDAO().getDSChiTietDDH(rsSP.getInt("maDDH")));
            	dssp.add(sp);
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
    	
    	return dssp;
    }
	public List<LoaiSanPham> timKiem2(String where) {
		Statement stmt = null;
		List<LoaiSanPham> dsloai = new ArrayList<LoaiSanPham>();
		try {
			String sql = "SELECT * FROM dbo.LoaiSanPham where "+where;
			System.out.println(sql);
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
//	            	printResultSet(rs);
				LoaiSanPham hd = new LoaiSanPham(rs);
				// hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
				dsloai.add(hd);
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

		return dsloai;
	}
	public boolean capNhat(LoaiSanPham loai) {
		PreparedStatement stmt = null;
		try {

			String sql = "UPDATE dbo.LoaiSanPham set TenLoai = ? where MaLoai = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, loai.getTenLoai());
			stmt.setDouble(2, loai.getMaLoai());
					
			
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
	 public boolean delete(LoaiSanPham loai) {
	        PreparedStatement statement = null;
	 
	        int n = 0;
	        try {
	            String sql = "delete from dbo.LoaiSanPham " + "where MaLoai = ?";
	            statement = conn.prepareStatement(sql);
	            statement.setInt(1, loai.getMaLoai());
	            n = statement.executeUpdate();
	        } catch (SQLException e) {
//	            e.printStackTrace();
	        } finally {

	        }
	        return n > 0;
	    }
	
	public static void main(String[] args) throws SQLException {
		LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
		System.out.println(loaiSanPhamDAO.getDanhSachLoaiSanPham());
	}

}
