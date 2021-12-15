package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import connectdb.ConnectDB;
import entity.ChiTietDonDatHang;
import entity.ChiTietHoaDon;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class DonDatHangDAO extends ConnectDB{
	private String error = "";
	
    public DonDatHangDAO() throws SQLException {
		super();
	}
    
    public boolean themSanPhamVaoDonDatHang(SanPham sp, int soLuong, int maKH) {
        PreparedStatement stmt = null;

        try {
        	

            String sql = "SELECT maDDH FROM dbo.DonDatHang where maKH = ? and tinhTrang = 0";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maKH);
            ResultSet rsDDH = stmt.executeQuery();
            
//            System.out.println(rsDDH.next());
//          kiểm tra xem đã có đơn đặt hàng chưa đặt của khách hàng đó không
            if(!rsDDH.next()) {
//				Chưa có đơn -> Tạo mới
            	if(this.taoDDH(maKH) == false) {
            		return false;
            	}
            	
//            	thêm sản phẩm vào đơn hàng
            	return this.themSanPhamVaoDonDatHang(sp, soLuong, maKH);
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
    
//    nv

    
    public int taoDDH(DonDatHang ddh) {
    	PreparedStatement stmt = null;
        try {

            String sql = "INSERT INTO dbo.DonDatHang (maKH, tongTien, tinhTrang) values(?, ?, 0)";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, ddh.getKhachHang().getMaKh());
            stmt.setDouble(2, ddh.getTongTien());
            int n = stmt.executeUpdate();
            
            if(n == 0)return -1;
            ddh.setMaDDH(getID());
            ddh.getChiTietDonDatHangs().forEach(ctddh -> {
            	try {
					new ChiTietDonDatHangDAO().themSanPhamVaoDonDatHang(ddh.getMaDDH(), ctddh.getSanPham(), ctddh.getSoLuong());
				} catch (SQLException e) {
					e.printStackTrace();
				}
            });
            
           
            
            return ddh.getMaDDH();
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
    
    public DonDatHang getDonDatHang(int maKH) {
    	PreparedStatement stmt = null;

        try {
        	

            String sql = "SELECT * FROM dbo.DonDatHang where maKH = ? and tinhTrang = 0";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maKH);
            ResultSet rsDDH = stmt.executeQuery();
            
//          kiểm tra xem đã có đơn đặt hàng chưa đặt của khách hàng đó không
            if(!rsDDH.next()) {
            	return null;
            }
//            printResultSet(rsDDH);
            DonDatHang ddh = new DonDatHang(rsDDH);
            
            ddh.setChiTietDonDatHangs(new ChiTietDonDatHangDAO().getDSChiTietDDH(rsDDH.getInt("maDDH")));
            
            return ddh;
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
    
    public DonDatHang getDonDatHangByMaDDH(int maDDH) {
    	PreparedStatement stmt = null;

        try {
        	

            String sql = "SELECT * FROM dbo.DonDatHang where maDDH = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maDDH);
            ResultSet rsDDH = stmt.executeQuery();
            
//          kiểm tra xem đã có đơn đặt hàng chưa đặt của khách hàng đó không
            if(!rsDDH.next()) {
            	return null;
            }
//            printResultSet(rsDDH);
            DonDatHang ddh = new DonDatHang(rsDDH);
            
            ddh.setChiTietDonDatHangs(new ChiTietDonDatHangDAO().getDSChiTietDDH(rsDDH.getInt("maDDH")));
            
            return ddh;
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
    
    public boolean xacNhanDatHang(int maKH) {
    	PreparedStatement stmt = null;

        try {
        	
        	DonDatHang ddh = this.getDonDatHang(maKH);
        	if(ddh == null)
        		return false;
        	
        	ddh.tinhTongTien();
        	List<ChiTietDonDatHang> dsctdh = ddh.getChiTietDonDatHangs();
//        	System.out.println(dsctdh);
        	AtomicBoolean flag = new AtomicBoolean();
        	flag.set(true);
        	
        	dsctdh.forEach(ctddh -> {
        		if(ctddh.getSoLuong() > ctddh.getSanPham().getSoLuong()) {
        			flag.set(false);
        			if(error.equals("")) {
        				error = ctddh.getSanPham().getTenSp()+" chỉ còn "+ctddh.getSanPham().getSoLuong()+" sản phẩm";
        			}else {
        				error += ", "+ctddh.getSanPham().getTenSp()+" chỉ còn "+ctddh.getSanPham().getSoLuong()+" sản phẩm";
        			}
        		}
        	});
        	
        	if(flag.get() == false) {
        		return false;
        	}
        	
        	dsctdh.forEach(ctddh -> {
        		SanPham sp = ctddh.getSanPham();
        		sp.setSoLuong(sp.getSoLuong()-ctddh.getSoLuong());
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
        		return false;
        	}
        	
        	Date now = new Date(new java.util.Date().getTime());
        	String sql = "UPDATE dbo.DonDatHang SET tinhTrang = 1, tongTien = ?, ngayDat = ? WHERE maKH = ? and tinhTrang = 0";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	prpStmt.setDouble(1, ddh.getTongTien());
        	prpStmt.setDate(2, now);
        	prpStmt.setInt(3, maKH);
            int n = prpStmt.executeUpdate();
               
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return false;
    }
    
    public boolean cloneDDHtoHD(int maDDH, NhanVien nv) {
    	PreparedStatement stmt = null;

        try {
        	DonDatHang ddh = this.getDonDatHangByMaDDH(maDDH);
        	
        	ArrayList<ChiTietHoaDon> dscthd = new ArrayList<ChiTietHoaDon>();
        	ddh.getChiTietDonDatHangs().forEach(ctddh -> {
            	ChiTietHoaDon cthd = new ChiTietHoaDon(ctddh.getSanPham(), ctddh.getSoLuong(), ctddh.getDonGia());
    			dscthd.add(cthd);
        	});
        	HoaDon hd = new HoaDon(nv, ddh.getKhachHang(), dscthd);
        	
			return new HoaDonDAO().themHoaDon(hd);
			
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return false;
    }
    
    public boolean thanhToan(int maDDH, NhanVien nv) {
    	PreparedStatement stmt = null;

        try {
        	
        	this.cloneDDHtoHD(maDDH, nv);
        	
        	Date now = new Date(new java.util.Date().getTime());
        	String sql = "UPDATE dbo.DonDatHang SET tinhTrang = 2 WHERE maDDH = ?";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	prpStmt.setDouble(1, maDDH);
            int n = prpStmt.executeUpdate();
               
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return false;
    }
    
    public boolean xoaDonDatHang(int maDDH) {
    	PreparedStatement stmt = null;

        try {
        	ArrayList<ChiTietDonDatHang> dsctddh = new ChiTietDonDatHangDAO().getDSChiTietDDH(maDDH);
        	
//        	cập nhật lại số lượng
        	dsctddh.forEach(ctddh -> {
        		SanPham sp = ctddh.getSanPham();
        		sp.setSoLuong(sp.getSoLuong() + ctddh.getSoLuong());
        		try {
					new SanPhamDAO().capNhat(sp);
				
        		} catch (SQLException e) {
					e.printStackTrace();
				}
        	});
        	
//        	xoa chi tiet ddh
        	if(new ChiTietDonDatHangDAO().xoaHetChiTietDonDatHang(maDDH) == false) {
        		return false;
        	}
        	
        	String sql = "DELETE from dbo.DonDatHang WHERE maDDH = ?";
        	PreparedStatement prpStmt = this.conn.prepareStatement(sql);
        	
        	prpStmt.setDouble(1, maDDH);
            int n = prpStmt.executeUpdate();
               
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    	
    	return false;
    }
    
    public List<DonDatHang> getDSDonDatHang() {
    	Statement stmt = null;
    	List<DonDatHang> dsddh = new ArrayList<DonDatHang>();
        try {
        	

            String sql = "SELECT * FROM dbo.DonDatHang where tinhTrang != 0";
            stmt = this.conn.createStatement();
            ResultSet rsDDH = stmt.executeQuery(sql);
            
            while(rsDDH.next()) {
//            	printResultSet(rsDDH);
            	DonDatHang ddh = new DonDatHang(rsDDH);
            	ddh.setChiTietDonDatHangs(new ChiTietDonDatHangDAO().getDSChiTietDDH(rsDDH.getInt("maDDH")));
            	dsddh.add(ddh);
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
    	
    	return dsddh;
    }
    
    public List<DonDatHang> timKiem(String key, String val) {
    	Statement stmt = null;
    	List<DonDatHang> dsddh = new ArrayList<DonDatHang>();
        try {
        	System.out.println(key + " " + val);

            String sql = "SELECT * FROM dbo.DonDatHang inner join dbo.KhachHang on dbo.DonDatHang.maKH = dbo.KhachHang.maKH where dbo.DonDatHang.tinhTrang != 0 and "+ key +" like N'%"+ val + "%'";
            stmt = this.conn.createStatement();
            
            ResultSet rsDDH = stmt.executeQuery(sql);
            
//            System.out.println(rsDDH.getStatement().toString());
            
            while(rsDDH.next()) {
//            	printResultSet(rsDDH);
            	DonDatHang ddh = new DonDatHang(rsDDH);
            	ddh.setChiTietDonDatHangs(new ChiTietDonDatHangDAO().getDSChiTietDDH(rsDDH.getInt("maDDH")));
            	dsddh.add(ddh);
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
    	
    	return dsddh;
    }
    
    public DonDatHang getDonDatHangChuaThanhToan(int maKH) {
    	PreparedStatement stmt = null;

        try {
        	

            String sql = "SELECT * FROM dbo.DonDatHang where maKH = ? and tinhTrang == 1";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maKH);
            ResultSet rsDDH = stmt.executeQuery();
            
//          kiểm tra xem đã có đơn đặt hàng chưa đặt của khách hàng đó không
            if(!rsDDH.next()) {
            	return null;
            }
//            printResultSet(rsDDH);
            DonDatHang ddh = new DonDatHang(rsDDH);
            
            ddh.setChiTietDonDatHangs(new ChiTietDonDatHangDAO().getDSChiTietDDH(rsDDH.getInt("maDDH")));
            
            return ddh;
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
    
    public int getID() {
    	PreparedStatement stmt = null;

        try {
        	

            String sql = "SELECT top 1 maDDH FROM dbo.DonDatHang order by maDDH desc";
            stmt = this.conn.prepareStatement(sql);
            ResultSet rsDDH = stmt.executeQuery();
            
//          kiểm tra xem đã có đơn đặt hàng chưa đặt của khách hàng đó không
            if(!rsDDH.next()) {
            	return 1;
            }
//          
            return rsDDH.getInt("maDDH");
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
    
    
    public String getError() {
		return error;
	}

	public static void main(String[] args) throws SQLException {
//    	KhachHang kh = new KhachHangDAO().getKhachHang(1);
//    	SanPham sp = new SanPhamDAO().getSanPham(17);
    	DonDatHangDAO DDHDao = new DonDatHangDAO();
//    	
//    	System.out.println(DDHDao.themSanPhamVaoDonDatHang(sp, 1, 1));
    	System.out.println(DDHDao.timKiem("soDienThoai", "0987654222"));
	}
}
