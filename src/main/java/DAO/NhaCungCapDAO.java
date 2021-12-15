package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectdb.ConnectDB;
import entity.NhaCungCap;
import entity.SanPham;

public class NhaCungCapDAO extends ConnectDB {

	public NhaCungCapDAO() throws SQLException {
		super();
	}

	public NhaCungCap getNhaCungCap(int maNCC) {
		PreparedStatement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.NhaCungCap where maNCC = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, maNCC);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}

			NhaCungCap ncc = new NhaCungCap(rs);
			return ncc;

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

	public ArrayList<NhaCungCap> getListNhaCungCap() {
		ArrayList<NhaCungCap> dataList = new ArrayList<NhaCungCap>();
		Statement stmt = null;
		try {

			String sql = "select * from NhaCungCap";
			stmt = this.conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
//				printResultSet(rs);
				NhaCungCap ncc = new NhaCungCap(rs);
				dataList.add(ncc);
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
	public ArrayList<NhaCungCap> getListNhaCungCapSach() {
		ArrayList<NhaCungCap> dataList = new ArrayList<NhaCungCap>();
		Statement stmt = null;
		try {

			String sql = "select DISTINCT NhaCungCap.MaNCC, NhaCungCap.TenNCC, NhaCungCap.DiaChi, NhaCungCap.SoDienThoai from dbo.NhaCungCap inner join  SanPham on SanPham.MaNCC = NhaCungCap.MaNCC inner join LoaiSanPham on SanPham.MaLoai = LoaiSanPham.MaLoai\r\n" + 
					"where TenLoai like N'%Sách%' OR TenLoai like N'%Truyện%'";
			stmt = this.conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
//				printResultSet(rs);
				NhaCungCap ncc = new NhaCungCap(rs);
				dataList.add(ncc);
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
	public ArrayList<NhaCungCap> getListNhaCungCapSanPhamKhac() {
		ArrayList<NhaCungCap> dataList = new ArrayList<NhaCungCap>();
		Statement stmt = null;
		try {

			String sql = "select DISTINCT NhaCungCap.MaNCC, NhaCungCap.TenNCC, NhaCungCap.DiaChi, NhaCungCap.SoDienThoai from dbo.NhaCungCap inner join  SanPham on SanPham.MaNCC = NhaCungCap.MaNCC inner join LoaiSanPham on SanPham.MaLoai = LoaiSanPham.MaLoai\r\n" + 
					"where TenLoai not like N'%Sách%' AND TenLoai not like N'%Truyện%'";
			stmt = this.conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
//				printResultSet(rs);
				NhaCungCap ncc = new NhaCungCap(rs);
				dataList.add(ncc);
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
	
	

	public boolean createNCC(String tenNCC) {
		PreparedStatement statement = null;

		try {
			String sql = "insert into NhaCungCap (tenNCC) values(?);";
			statement = conn.prepareStatement(sql);
			statement.setString(1, tenNCC);
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

	public boolean create(NhaCungCap ncc) {
		PreparedStatement statement = null;

		try {
			String sql = "insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(?, ?, ?);";
			statement = conn.prepareStatement(sql);
			statement.setString(1, ncc.getTenNCC());
			statement.setString(2, ncc.getDiaChi());
			statement.setString(3, ncc.getSoDienThoai());
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

	public boolean capNhat(NhaCungCap ncc) {
		PreparedStatement stmt = null;
		try {

			String sql = "UPDATE dbo.NhaCungCap set tenNCC = ?, DiaChi = ?, SoDienThoai = ? where MaNCC = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getDiaChi());
			stmt.setString(3, ncc.getSoDienThoai());
			stmt.setInt(4, ncc.getMaNCC());
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

	public boolean delete(NhaCungCap ncc) {
		PreparedStatement statement = null;

		int n = 0;
		try {
			String sql = "delete from dbo.NhaCungCap " + "where MaNCC = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, ncc.getMaNCC());
			n = statement.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public List<NhaCungCap> timKiem(String key, String val) {
    	Statement stmt = null;
    	List<NhaCungCap> dsncc = new ArrayList<NhaCungCap>();
        try {
        	System.out.println(key + " " + val);

            String sql = "SELECT * FROM dbo.NhaCungCap where "+ key +" like N'%"+ val + "%'";
            stmt = this.conn.createStatement();
            
            ResultSet rsNCC = stmt.executeQuery(sql);
            
//            System.out.println(rsSP.getStatement().toString());
            
            while(rsNCC.next()) {
//            	printResultSet(rsSP);
            	NhaCungCap ncc = new NhaCungCap(rsNCC);
            //	sp.setChiTietDonDatHangs(new ChiTietDonDatHangDAO().getDSChiTietDDH(rsSP.getInt("maDDH")));
            	dsncc.add(ncc);
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
    	
    	return dsncc;
    }
	
	public List<NhaCungCap> timKiemNCC2(String where) {
		Statement stmt = null;
		List<NhaCungCap> dsncc = new ArrayList<NhaCungCap>();
		try {
			String sql = "SELECT * FROM dbo.NhaCungCap where "+where;
			System.out.println(sql);
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
//	            	printResultSet(rs);
				NhaCungCap hd = new NhaCungCap(rs);
				// hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
				dsncc.add(hd);
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

		return dsncc;
	}
	public NhaCungCap getNCCByTenNCC(String tenNCC) {
		PreparedStatement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.NhaCungCap where tenNCC = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, tenNCC);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return null;

			NhaCungCap sp = new NhaCungCap(rs);
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
}
