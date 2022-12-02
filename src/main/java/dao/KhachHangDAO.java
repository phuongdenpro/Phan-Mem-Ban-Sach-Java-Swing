package dao;

import java.util.*;

import javax.swing.JOptionPane;

import connectdb.ConnectDB;

import java.sql.*;
import java.sql.Date;

import entity.KhachHang;
import entity.NhanVien;
import entity.NhaCungCap;
import entity.SanPham;
import util.Ngay;


public class KhachHangDAO extends ConnectDB{

    public KhachHangDAO() throws SQLException {
		super();
		
	}

    public ArrayList<KhachHang> getListKhachHang() {
        ArrayList<KhachHang> dataList = new ArrayList<KhachHang>();
        Statement stmt = null;
        try {

            String sql = "select * from KhachHang kh join TaiKhoan tk on kh.TaiKhoanID = tk.ID ";
            stmt = this.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//            	printResultSet(rs);
            	KhachHang kh = new KhachHang(rs);
            	dataList.add(kh);
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

    public boolean themKhachHang(KhachHang kh, int taiKhoanId) {
    	PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO dbo.KhachHang (hoTen, soDienThoai, diaChi, taiKhoanId) values(?, ?, ?, ?)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, kh.getHoTen());
            stmt.setString(2, kh.getSoDienThoai());
            stmt.setString(3, kh.getDiaChi());
            stmt.setInt(4, taiKhoanId);
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
    
    public KhachHang getKhachHang(int maKH) {
        PreparedStatement stmt = null;
        try {

            String sql = "select * from KhachHang kh join TaiKhoan tk on kh.TaiKhoanID = tk.ID where MaKH = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maKH);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            
            KhachHang kh = new KhachHang(rs);
            return kh;
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
    
    //tìm khách hàng theo tên
    public ArrayList<KhachHang> getKhachHangByTenKh(String ten) {
    	ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
    	PreparedStatement stmt = null;
    	try {
    		String sql = "select * from dbo.KhachHang where HoTen like '%?%'";
    		stmt.setString(1,ten);
    		ResultSet result = stmt.executeQuery();
    		while (result.next()) {
    			KhachHang kh = new KhachHang(result);
    			dskh.add(kh);
    		}
    		
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	return dskh;
    }
    
    public KhachHang getKhachHangByMaTK(int taiKhoanID) {
        PreparedStatement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.KhachHang where taiKhoanID = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, taiKhoanID);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            
            KhachHang kh = new KhachHang(rs);
            return kh;
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

	public boolean suaKH(KhachHang kh,int ma) {
	    PreparedStatement stmt = null;
		try {
		
		    String sql = "UPDATE KhachHang set HoTen = ?, SoDienThoai = ?, DiaChi = ? where MaKH = ?";
		    stmt = this.conn.prepareStatement(sql);
		    
		    stmt.setString(1, kh.getHoTen());
		    stmt.setString(2, kh.getSoDienThoai());
		    stmt.setString(3, kh.getDiaChi());
		    stmt.setInt(4, ma);
		    
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
	
	public boolean xoaKhachHang(KhachHang kh) {
		PreparedStatement stmt = null;
		try {
		String sql = " delete from dbo.KhachHang where maKH = ?";
		System.out.println(sql);
	
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, kh.getMaKh());
			
			int n = stmt.executeUpdate();
			
			if(n== 0) 
				return false;
			if(kh.getTaiKhoan()!= null) {
				new TaiKhoanDAO().xoaTaiKhoan(kh.getTaiKhoan().getId());
			}
			new TaiKhoanDAO().xoaTaiKhoan(kh.getMaTk());
			return true;
		}catch(SQLException ex) {
			//ex.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
    
    public Map<KhachHang, Map<String, Integer>> thongKeKHTN(int count, long minTongTien) {
    	Map<KhachHang, Map<String, Integer>> kq = new LinkedHashMap<KhachHang, Map<String, Integer>>();
    	PreparedStatement stmt = null;
        try {
        	String top = "";
        	if(count != 0)
        		top = " top "+count+" ";
        	
        	String tongTien = "";
        	if(minTongTien != 0)
        		tongTien = "having sum(tongTien) >= "+minTongTien+"\r\n";
        	
            String sql = "select "+top+" KhachHang.maKH, HoTen, SoDienThoai, DiaChi, count(KhachHang.maKH) as soLanMuaHang, sum(tongTien) as tongTien\r\n"
            		+ "  from [HieuSach].[dbo].[HoaDon]\r\n"
            		+ "  inner join [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "  on HoaDon.maKH = KhachHang.maKH\r\n"
            		+ "  group by KhachHang.maKH, HoTen, SoDienThoai, DiaChi\r\n"
            		+ tongTien
            		+ "  order by tongTien desc";
            stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
//            	printResultSet(rs);
            	KhachHang kh = new KhachHang(rs);
            	Map<String, Integer> mp = new HashMap<String, Integer>();
            	mp.put("soLanMuaHang", rs.getInt("soLanMuaHang"));
            	mp.put("tongTien", rs.getInt("tongTien"));
            	kq.put(kh, mp);
            }
            
            return kq;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return kq;
    }
    
    public Map<KhachHang, Map<String, Integer>> thongKeKHTN(Date d1, Date d2, int count, long minTongTien) {
    	Map<KhachHang, Map<String, Integer>> kq = new LinkedHashMap<KhachHang, Map<String, Integer>>();
    	PreparedStatement stmt = null;
        try {
        	String top = "";
        	if(count != 0)
        		top = " top "+count+" ";
        	
        	String tongTien = "";
        	if(minTongTien != 0)
        		tongTien = "having sum(tongTien) >= "+minTongTien+"\r\n";
        	
            String sql = "select "+ top +" KhachHang.maKH, HoTen, SoDienThoai, DiaChi, count(KhachHang.maKH) as soLanMuaHang, sum(tongTien) as tongTien\r\n"
            		+ "from [HieuSach].[dbo].[HoaDon]\r\n"
            		+ "inner join [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "on HoaDon.maKH = KhachHang.maKH\r\n"
            		+ "where ngayMua >= ? and ngayMua <= ?\r\n"
            		+ "group by KhachHang.maKH, HoTen, SoDienThoai, DiaChi\r\n"
            		+ tongTien
            		+ "order by tongTien desc";
            System.out.println(sql);
            stmt = this.conn.prepareStatement(sql);
            stmt.setTimestamp(1, Ngay.tuNgay(d1));
            stmt.setTimestamp(2, Ngay.toiNgay(d2));
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
//            	printResultSet(rs);
            	KhachHang kh = new KhachHang(rs);
            	Map<String, Integer> mp = new HashMap<String, Integer>();
            	mp.put("soLanMuaHang", rs.getInt("soLanMuaHang"));
            	mp.put("tongTien", rs.getInt("tongTien"));
            	kq.put(kh, mp);
            }
            
            return kq;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return kq;
    }
    
    public List<KhachHang> getDSKHKhongMua(){
    	List<KhachHang> kq = new ArrayList<KhachHang>();
    	PreparedStatement stmt = null;
        try {
        	
            String sql = "select * from [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "where maKH not in \r\n"
            		+ "(select KhachHang.maKH\r\n"
            		+ "from [HieuSach].[dbo].[HoaDon]\r\n"
            		+ "inner join [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "on HoaDon.maKH = KhachHang.maKH)";
            stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
//            	printResultSet(rs);
            	KhachHang kh = new KhachHang(rs);
            	kq.add(kh);
            }
            
            return kq;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return kq;
    }
    
    public List<KhachHang> getDSKHKhongMua(Date d1, Date d2){
    	List<KhachHang> kq = new ArrayList<KhachHang>();
    	PreparedStatement stmt = null;
        try {
        	
            String sql = "select * from [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "where maKH not in \r\n"
            		+ "(select KhachHang.maKH\r\n"
            		+ "from [HieuSach].[dbo].[HoaDon]\r\n"
            		+ "inner join [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "on HoaDon.maKH = KhachHang.maKH\r\n"
            		+ "where ngayMua >= ? and ngayMua <= ?)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setTimestamp(1, Ngay.tuNgay(d1));
            stmt.setTimestamp(2, Ngay.toiNgay(d2));
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
//            	printResultSet(rs);
            	KhachHang kh = new KhachHang(rs);
            	kq.add(kh);
            }
            
            return kq;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return kq;
    }
    
    public Map<KhachHang, Map<String, Integer>> thongKeKHMuaIt(int count, long maxTongTien) {
    	Map<KhachHang, Map<String, Integer>> kq = new LinkedHashMap<KhachHang, Map<String, Integer>>();
    	PreparedStatement stmt = null;
        try {
        	List<KhachHang> dskh = getDSKHKhongMua();
        	int sz = dskh.size();
        	if(count != 0)
        		sz = Math.min(sz, count);
        	
        	for(int i=0; i<sz; i++) {
        		Map<String, Integer> mp = new HashMap<String, Integer>();
            	mp.put("soLanMuaHang", 0);
            	mp.put("tongTien", 0);
            	kq.put(dskh.get(i), mp);
        	}
        	
        	String top = "";
        	if(count != 0) {
        		count -= sz;
        		top = " top "+count+" ";
        	}
        	
        	String tongTien = "";
        	if(maxTongTien != 0)
        		tongTien = "having sum(tongTien) <= "+maxTongTien+"\r\n";
        	
            String sql = "select "+top+" KhachHang.maKH, HoTen, SoDienThoai, DiaChi, count(KhachHang.maKH) as soLanMuaHang, sum(tongTien) as tongTien\r\n"
            		+ "  from [HieuSach].[dbo].[HoaDon]\r\n"
            		+ "  inner join [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "  on HoaDon.maKH = KhachHang.maKH\r\n"
            		+ "  group by KhachHang.maKH, HoTen, SoDienThoai, DiaChi\r\n"
            		+ tongTien
            		+ "  order by tongTien";
            stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
//            	printResultSet(rs);
            	KhachHang kh = new KhachHang(rs);
            	Map<String, Integer> mp = new HashMap<String, Integer>();
            	mp.put("soLanMuaHang", rs.getInt("soLanMuaHang"));
            	mp.put("tongTien", rs.getInt("tongTien"));
            	kq.put(kh, mp);
            }
            
            return kq;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return kq;
    }
    
    public Map<KhachHang, Map<String, Integer>> thongKeKHMuaIt(Date d1, Date d2, int count, long maxTongTien) {
    	Map<KhachHang, Map<String, Integer>> kq = new LinkedHashMap<KhachHang, Map<String, Integer>>();
    	PreparedStatement stmt = null;
        try {
        	List<KhachHang> dskh = getDSKHKhongMua(d1, d2);
        	int sz = dskh.size();
        	if(count != 0)
        		sz = Math.min(sz, count);
        	
        	for(int i=0; i<sz; i++) {
        		Map<String, Integer> mp = new HashMap<String, Integer>();
            	mp.put("soLanMuaHang", 0);
            	mp.put("tongTien", 0);
            	kq.put(dskh.get(i), mp);
        	}
        	
        	String top = "";
        	if(count != 0) {
        		count -= sz;
        		top = " top "+count+" ";
        	}
        	
        	String tongTien = "";
        	if(maxTongTien != 0)
        		tongTien = "having sum(tongTien) <= "+maxTongTien+"\r\n";
        	
            String sql = "select "+ top +" KhachHang.maKH, HoTen, SoDienThoai, DiaChi, count(KhachHang.maKH) as soLanMuaHang, sum(tongTien) as tongTien\r\n"
            		+ "from [HieuSach].[dbo].[HoaDon]\r\n"
            		+ "inner join [HieuSach].[dbo].[KhachHang]\r\n"
            		+ "on HoaDon.maKH = KhachHang.maKH\r\n"
            		+ "where ngayMua >= ? and ngayMua <= ?\r\n"
            		+ "group by KhachHang.maKH, HoTen, SoDienThoai, DiaChi\r\n"
            		+ tongTien
            		+ "order by tongTien";
            stmt = this.conn.prepareStatement(sql);
            stmt.setTimestamp(1, Ngay.tuNgay(d1));
            stmt.setTimestamp(2, Ngay.toiNgay(d2));
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
//            	printResultSet(rs);
            	KhachHang kh = new KhachHang(rs);
            	Map<String, Integer> mp = new HashMap<String, Integer>();
            	mp.put("soLanMuaHang", rs.getInt("soLanMuaHang"));
            	mp.put("tongTien", rs.getInt("tongTien"));
            	kq.put(kh, mp);
            }
            
            return kq;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return kq;
    }
    
    public int soLuongKhachHang() {
    	PreparedStatement stmt = null;
        try {

            String sql = "SELECT count(maKH) as soLuong FROM dbo.KhachHang";
            stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if(!rs.next()) {
            	return 0;
            }
            
            return rs.getInt("soLuong");
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }

	public ArrayList<KhachHang> TimKiem(String where)  {
		// TODO Auto-generated method stub
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		
		try {
			String sqlTimKiem = "select * from KhachHang where" + where ;
			System.out.println(sqlTimKiem);
			PreparedStatement stml = this.conn.prepareStatement(sqlTimKiem,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);	
			ResultSet result = stml.executeQuery();
			
			int maKh; 
			String ten, sdt, diaChi;
			while (result.next()) {
				maKh = Integer.parseInt(result.getString("MaKH"));
				ten = result.getString("HoTen");
				sdt = result.getString("SoDienThoai");
				diaChi = result.getString("DiaChi");
				KhachHang kh = new KhachHang(maKh, ten, sdt, diaChi);
				dskh.add(kh);
			}
			return dskh;
				
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return dskh;
	}
}
