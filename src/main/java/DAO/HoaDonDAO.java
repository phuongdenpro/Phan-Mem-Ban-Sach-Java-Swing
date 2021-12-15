package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import connectdb.ConnectDB;
import entity.ChiTietHoaDon;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.SanPham;
import util.Ngay;

public class HoaDonDAO extends ConnectDB{
	private String error = "";
	
	public HoaDonDAO() throws SQLException {
		super();
	}

	public boolean themHoaDon(HoaDon hd) {
    	PreparedStatement stmt = null;
        try {
        	ArrayList<ChiTietHoaDon> dscthd = hd.getChiTietHoaDons();
        	
//        	kiểm tra số lượng sp
        	for(int i=0; i<dscthd.size(); i++) {
        		if(dscthd.get(i).getSanPham().getSoLuong() < dscthd.get(i).getSoLuong()) {
        			this.error = "Lỗi: "+ dscthd.get(i).getSanPham().getTenSp() + " chỉ còn " + dscthd.get(i).getSanPham().getSoLuong() + " sản phẩm";
        			return false;
        		}
        	}
        	AtomicBoolean flag = new AtomicBoolean();
        	flag.set(true);
//        	cập nhật số lượng sản phẩm
        	dscthd.forEach(cthd -> {
        		SanPham sp = cthd.getSanPham();
        		sp.setSoLuong(sp.getSoLuong()-cthd.getSoLuong());
        		try {
					if(new SanPhamDAO().capNhat(sp) == false) {
						flag.set(false);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	});
        	
        	if(flag.get() == false) {
        		this.error = "Có lỗi xảy ra";
        		return false;
        	}
        	
//        	thêm hóa đơn
            String sql = "INSERT INTO dbo.HoaDon (maNV, maKH, tongTien) values(?, ?, ?)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, hd.getNhanVien().getMaNv());
            
            if(hd.getKhachHang() != null)
            	stmt.setInt(2, hd.getKhachHang().getMaKh());
            else
            	stmt.setNull(2, java.sql.Types.INTEGER);
            
            stmt.setDouble(3, hd.getTongTien());
            
            int n = stmt.executeUpdate();
            if(n == 0) {
            	this.error = "Có lỗi xảy ra";
        		return false;
        	}
            
//            thêm chi tiết hóa đơn
        	dscthd.forEach(cthd -> {
        		try {
        			boolean flag2 = new ChiTietHoaDonDAO().themChiTietHoaDon(cthd, getLastestMaHD());
        			if(flag2 == false) {
        				this.error =  "Có lỗi xảy ra";
        			}
					flag.set(flag2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	});
            
            return flag.get();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	
        }
		return false;
	}
	
	public HoaDon getHD(int maHD) {
        int id = 0;
        Statement stmt = null;
        try {
            String sql = "SELECT * FROM dbo.HoaDon where maHD = "+maHD;
            stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.next())
            	return null;
            
            HoaDon hd = new HoaDon(rs);
        	hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
            return hd;
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
	
	public int getLastestMaHD() {
        int id = 0;
        Statement stmt = null;
        try {
            String sql = "SELECT * FROM dbo.HoaDon";
            stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            id = rs.getInt("MaHD");
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
	}
	
	public ArrayList<HoaDon> getDSHD(){
		ArrayList<HoaDon> dataList = new ArrayList<HoaDon>();
        Statement stmt = null;
        try {

            String sql = "SELECT * FROM dbo.HoaDon";
            stmt = this.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	HoaDon hd = new HoaDon(rs);
            	hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
            	dataList.add(hd);
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
	
	public List<HoaDon> timKiem(String key, String val) {
    	Statement stmt = null;
    	List<HoaDon> dshd = new ArrayList<HoaDon>();
        try {
        	System.out.println(key + " " + val);

            String sql = "SELECT * FROM dbo.HoaDon inner join dbo.KhachHang on dbo.HoaDon.maKH = dbo.KhachHang.maKH where "+ key +" like N'%"+ val + "%'";
            stmt = this.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            
            
            while(rs.next()) {
//            	printResultSet(rs);
            	HoaDon hd = new HoaDon(rs);
            	hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
            	dshd.add(hd);
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
    	
    	return dshd;
    }
	
	public List<HoaDon> timKiem(String where) {
    	Statement stmt = null;
    	List<HoaDon> dshd = new ArrayList<HoaDon>();
        try {
            String sql = "SELECT * FROM dbo.HoaDon inner join dbo.KhachHang on dbo.HoaDon.maKH = dbo.KhachHang.maKH where "+ where;
            System.out.println(sql);
            stmt = this.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            
            
            while(rs.next()) {
//            	printResultSet(rs);
            	HoaDon hd = new HoaDon(rs);
            	hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
            	dshd.add(hd);
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
    	
    	return dshd;
    }
	
	public boolean xoaHD(int maHD) {
		PreparedStatement stmt = null;

        try {
        	if(new ChiTietHoaDonDAO().xoaHetChiTietHD(maHD) == false) {
        		return false;
        	}
        	
        	String sql = "DELETE from dbo.HoaDon WHERE maHD = ?";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	
        	prpStmt.setDouble(1, maHD);
            int n = prpStmt.executeUpdate();
               
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return false;
	}
	
	public String getError() {
		return this.error;
	}
	
	public double thongKeLoiNhuan(Date d) {
		PreparedStatement stmt = null;

        try {
        	String sql = "select sum(ChiTietHoaDon.soLuong * (DonGia-GiaNhap)) as loiNhuan\r\n"
        			+ "from [HieuSach].[dbo].[HoaDon]\r\n"
        			+ "inner join [HieuSach].[dbo].[ChiTietHoaDon]\r\n"
        			+ "on HoaDon.maHD = ChiTietHoaDon.maHD\r\n"
        			+ "inner join [HieuSach].[dbo].[SanPham]\r\n"
        			+ "on ChiTietHoaDon.maSP = SanPham.maSP\r\n"
        			+ "where ngayMua >= ? and ngayMua <= ?";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	
        	prpStmt.setTimestamp(1, Ngay.tuNgay(d));
        	prpStmt.setTimestamp(2, Ngay.toiNgay(d));
            ResultSet rs = prpStmt.executeQuery();
               
            if(!rs.next())
            	return 0;
            
            return rs.getInt("loiNhuan");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return 0;
	}
	
	public double thongKeDoanhThu(Date d) {
		PreparedStatement stmt = null;

        try {
        	String sql = "select sum(tongTien) as doanhThu from [HieuSach].[dbo].[HoaDon]\r\n"
        			+ "where ngayMua >= ? and ngayMua <= ?";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	
        	prpStmt.setTimestamp(1, Ngay.tuNgay(d));
        	prpStmt.setTimestamp(2, Ngay.toiNgay(d));
            ResultSet rs = prpStmt.executeQuery();
               
            if(!rs.next())
            	return 0;
            
            return rs.getInt("doanhThu");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return 0;
	}
	
	public List<Map<String, String>> chiTiet(){
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		PreparedStatement stmt = null;
        try {
        	String sql = "select *\r\n"
        			+ "from [HieuSach].[dbo].[HoaDon]\r\n"
        			+ "inner join [HieuSach].[dbo].[KhachHang]\r\n"
        			+ "on HoaDon.maKH = KhachHang.maKH\r\n"
        			+ "inner join [HieuSach].[dbo].[ChiTietHoaDon]\r\n"
        			+ "on HoaDon.maHD = ChiTietHoaDon.maHD\r\n"
        			+ "inner join [HieuSach].[dbo].[SanPham]\r\n"
        			+ "on SanPham.MaSP = ChiTietHoaDon.maSP\r\n"
        			+ "inner join [HieuSach].[dbo].[LoaiSanPham]\r\n"
        			+ "on SanPham.MaLoai = LoaiSanPham.maLoai\r\n";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	
            ResultSet rs = prpStmt.executeQuery();
               
            while(rs.next()) {
            	Map<String, String> mp = new HashMap<String, String>();
            	mp.put("maHD", String.valueOf(rs.getInt("maHD")));
            	mp.put("maKH", String.valueOf(rs.getInt("maKH")));
            	Timestamp ts = rs.getTimestamp("ngayMua");
            	mp.put("ngayMua", Ngay.convertTimeToString(ts));
            	mp.put("TongTien", String.valueOf(rs.getInt("tongTien")));
            	mp.put("maSP", String.valueOf(rs.getInt("maSP")));
            	mp.put("soLuong", String.valueOf(rs.getInt("soLuong")));
            	mp.put("donGia", String.valueOf(rs.getInt("donGia")));
            	mp.put("giaNhap", String.valueOf(rs.getInt("giaNhap")));
            	mp.put("tenLoai", String.valueOf(rs.getString("tenLoai")));
            	ls.add(mp);
            }
            
            return ls;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return ls;
	}
	
	public List<Map<String, String>> chiTiet(Date d1, Date d2){
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		PreparedStatement stmt = null;
        try {
        	String sql = "select *\r\n"
        			+ "from [HieuSach].[dbo].[HoaDon]\r\n"
        			+ "inner join [HieuSach].[dbo].[KhachHang]\r\n"
        			+ "on HoaDon.maKH = KhachHang.maKH\r\n"
        			+ "inner join [HieuSach].[dbo].[ChiTietHoaDon]\r\n"
        			+ "on HoaDon.maHD = ChiTietHoaDon.maHD\r\n"
        			+ "inner join [HieuSach].[dbo].[SanPham]\r\n"
        			+ "on SanPham.MaSP = ChiTietHoaDon.maSP\r\n"
        			+ "inner join [HieuSach].[dbo].[LoaiSanPham]\r\n"
        			+ "on SanPham.MaLoai = LoaiSanPham.maLoai\r\n"
        			+ "where ngayMua >= ? and ngayMua <= ?";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	
        	prpStmt.setTimestamp(1, Ngay.tuNgay(d1));
        	prpStmt.setTimestamp(2, Ngay.toiNgay(d2));
            ResultSet rs = prpStmt.executeQuery();
            
            while(rs.next()) {
            	Map<String, String> mp = new HashMap<String, String>();
            	mp.put("maHD", String.valueOf(rs.getInt("maHD")));
            	mp.put("maKH", String.valueOf(rs.getInt("maKH")));
            	Timestamp ts = rs.getTimestamp("ngayMua");
            	mp.put("ngayMua", Ngay.convertTimeToString(ts));
            	mp.put("TongTien", String.valueOf(rs.getInt("tongTien")));
            	mp.put("maSP", String.valueOf(rs.getInt("maSP")));
            	mp.put("soLuong", String.valueOf(rs.getInt("soLuong")));
            	mp.put("donGia", String.valueOf(rs.getInt("donGia")));
            	mp.put("giaNhap", String.valueOf(rs.getInt("giaNhap")));
            	mp.put("tenLoai", String.valueOf(rs.getString("tenLoai")));
            	ls.add(mp);
            }
            
            return ls;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return ls;
	}

	
}
