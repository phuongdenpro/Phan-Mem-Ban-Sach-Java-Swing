package dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import connectdb.ConnectDB;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.SanPham;
import util.Ngay;

public class SanPhamDAO extends ConnectDB {

	public SanPhamDAO() throws SQLException {
		super();

	}

	public ArrayList<SanPham> getListSanPham() {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();
		Statement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.SanPham inner join LoaiSanPham on SanPham.MaLoai = loaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC";
			stmt = this.conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
//                System.out.println(rs);
//				printResultSet(rs);
				SanPham sanPham = new SanPham(rs);
				dataList.add(sanPham);
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

	public ArrayList<SanPham> getListSach() {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();
		Statement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.SanPham inner join LoaiSanPham on SanPham.MaLoai = loaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC\r\n"
					+ "where TenLoai like N'%Sách%' OR TenLoai like N'%Truyện%'";
			stmt = this.conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
//                System.out.println(rs);
				printResultSet(rs);
				SanPham sanPham = new SanPham(rs);
				dataList.add(sanPham);
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

	public ArrayList<SanPham> getListSanPhamKhac() {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();
		Statement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.SanPham inner join LoaiSanPham on SanPham.MaLoai = LoaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC\r\n"
					+ "where LoaiSanPham.TenLoai NOT like N'%Sách%' AND LoaiSanPham.TenLoai NOT like N'%Truyện%'";
			stmt = this.conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
//                System.out.println(rs);
//				printResultSet(rs);
				SanPham sanPham = new SanPham(rs);
				dataList.add(sanPham);
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

	public ArrayList<SanPham> getListSanPhamByMaLoai(int maLoai) {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();
		PreparedStatement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.SanPham where maLoai = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, maLoai);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				printResultSet(rs);
				SanPham sanPham = new SanPham(rs);
				dataList.add(sanPham);
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

	public SanPham getSanPham(int maSP) {
		PreparedStatement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.SanPham where maSP = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, maSP);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return null;

			SanPham sp = new SanPham(rs);
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

	public SanPham getSanPhamCuoiCung() {
		Statement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.SanPham WHERE MaSP=(SELECT max(MaSP) FROM dbo.SanPham);";
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next())
				return null;
			SanPham sp = new SanPham(rs);
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

	public boolean create(SanPham sp) {
		PreparedStatement statement = null;

		try {
			String sql = "insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, sp.getNhaCungCap().getMaNCC());
			statement.setInt(2, sp.getLoaiSanPham().getMaLoai());
			statement.setString(3, sp.getTenSp());
			statement.setDouble(4, sp.getGiaSp());
			statement.setDouble(5, sp.getGiaNhap());
			statement.setInt(6, sp.getSoLuong());
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
	public boolean createSach(SanPham sp) {
		PreparedStatement statement = null;

		try {
			String sql = "insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(?, ?, ?, ?, ?, ?,?,?,?)";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, sp.getNhaCungCap().getMaNCC());
			statement.setInt(2, sp.getLoaiSanPham().getMaLoai());
			statement.setString(3, sp.getTenSp());
			statement.setDouble(4, sp.getGiaSp());
			statement.setDouble(5, sp.getGiaNhap());
			statement.setInt(6, sp.getSoLuong());
			statement.setString(7, sp.getTacGia());
			statement.setInt(8, sp.getSoTrang());
			statement.setInt(9, sp.getNamXuatBan());
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

	public boolean capNhat(SanPham sp) {
		PreparedStatement stmt = null;
		try {
			String loai = " ";
			if (sp.getLoaiSanPham() != null)
				loai = " , MaLoai = " + sp.getLoaiSanPham().getMaLoai() + " ";

			String ncc = " ";
			if (sp.getNhaCungCap() != null)
				ncc = " , MaNCC = " + sp.getNhaCungCap().getMaNCC() + " ";

			String sql = "UPDATE dbo.SanPham set TenSp = ?, GiaSp = ?, GiaNhap = ?, SoLuong = ?" + loai + ncc
					+ " where MaSP = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, sp.getTenSp());
			stmt.setDouble(2, sp.getGiaSp());
			stmt.setDouble(3, sp.getGiaNhap());
			stmt.setInt(4, sp.getSoLuong());
			stmt.setInt(5, sp.getMaSp());
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
	public boolean capNhatSach(SanPham sp) {
		PreparedStatement stmt = null;
		try {
			String loai = " ";
			if (sp.getLoaiSanPham() != null)
				loai = " , MaLoai = " + sp.getLoaiSanPham().getMaLoai() + " ";

			String ncc = " ";
			if (sp.getNhaCungCap() != null)
				ncc = " , MaNCC = " + sp.getNhaCungCap().getMaNCC() + " ";

			String sql = "UPDATE dbo.SanPham set TenSp = ?, GiaSp = ?, GiaNhap = ?, SoLuong = ?, TacGia = ?, soTrang = ?, namXuatBan = ?" + loai + ncc
					+ " where MaSP = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, sp.getTenSp());
			stmt.setDouble(2, sp.getGiaSp());
			stmt.setDouble(3, sp.getGiaNhap());
			stmt.setInt(4, sp.getSoLuong());
			stmt.setString(5, sp.getTacGia());
			stmt.setInt(6, sp.getSoTrang());
			stmt.setInt(7, sp.getNamXuatBan());
			stmt.setInt(8, sp.getMaSp());
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

	public boolean delete(SanPham sp) {
		PreparedStatement statement = null;

		int n = 0;
		try {
			String sql = "delete from dbo.SanPham " + "where MaSP = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, sp.getMaSp());
			n = statement.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		return n > 0;
	}

	public List<SanPham> timKiemSach(String key, String val) {
		Statement stmt = null;
		List<SanPham> dssp = new ArrayList<SanPham>();
		try {
			System.out.println(key + " " + val);

			String sql = "SELECT * FROM dbo.SanPham inner join LoaiSanPham on SanPham.MaLoai = LoaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC where (TenLoai like N'%Sách%'"
					+ " OR TenLoai like N'%Truyện%') AND  " + key + " like N'%" + val + "%'";
			stmt = this.conn.createStatement();

			ResultSet rsSP = stmt.executeQuery(sql);

//	            System.out.println(rsSP.getStatement().toString());

			while (rsSP.next()) {
//	            	printResultSet(rsSP);
				SanPham sp = new SanPham(rsSP);
				// sp.setChiTietDonDatHangs(new
				// ChiTietDonDatHangDAO().getDSChiTietDDH(rsSP.getInt("maDDH")));
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

	public List<SanPham> timKiemSach2(String where) {
		Statement stmt = null;
		List<SanPham> dssp = new ArrayList<SanPham>();
		try {
			String sql = "SELECT * FROM dbo.SanPham inner join LoaiSanPham on SanPham.MaLoai = LoaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC where "
					+ where + " and (TenLoai like N'%Sách%'\r\n" + "	 OR TenLoai like N'%Truyện%')";
			System.out.println(sql);
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
//	            	printResultSet(rs);
				SanPham hd = new SanPham(rs);
				// hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
				dssp.add(hd);
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

	public List<SanPham> timKiemSanPhamKhac(String key, String val) {
		Statement stmt = null;
		List<SanPham> dssp = new ArrayList<SanPham>();
		try {
			System.out.println(key + " " + val);

			String sql = "SELECT * FROM dbo.SanPham inner join loaiSanPham on SanPham.MaLoai = loaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC where (TenLoai NOT like N'%Sách%'"
					+ " AND TenLoai NOT like N'%Truyện%') AND  " + key + " like N'%" + val + "%'";
			stmt = this.conn.createStatement();

			ResultSet rsSP = stmt.executeQuery(sql);

//	            System.out.println(rsSP.getStatement().toString());

			while (rsSP.next()) {
//	            	printResultSet(rsSP);
				SanPham sp = new SanPham(rsSP);
				// sp.setChiTietDonDatHangs(new
				// ChiTietDonDatHangDAO().getDSChiTietDDH(rsSP.getInt("maDDH")));
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

	public List<SanPham> timKiemSanPhamKhac2(String where) {
		Statement stmt = null;
		List<SanPham> dssp = new ArrayList<SanPham>();
		try {
			String sql = "SELECT * FROM dbo.SanPham inner join LoaiSanPham on SanPham.MaLoai = LoaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC where "
					+ where + " and (TenLoai not like N'%Sách%'\r\n" + "	 and TenLoai not like N'%Truyện%')";
			System.out.println(sql);
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
//	            	printResultSet(rs);
				SanPham hd = new SanPham(rs);
				// hd.setChiTietHoaDons(new ChiTietHoaDonDAO().getDSChiTietHD(hd.getMaHD()));
				dssp.add(hd);
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

	public List<SanPham> getSanPhamDaHet(boolean isSach) {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();

		PreparedStatement stmt = null;
		try {
			String loai = "";
			if (isSach)
				loai = " (tenLoai like N'%Sách%' or tenLoai like N'%Truyện%') ";
			else {
				loai = " (tenLoai not like N'%Sách%' and tenLoai not like N'%Truyện%') ";
			}

			String sql = "  SELECT * FROM [HieuSach].[dbo].[SanPham]\r\n"
					+ "  inner join dbo.NhaCungCap on sanPham.MaNCC = nhaCungCap.MaNCC \r\n"
					+ "  inner join dbo.LoaiSanPham on sanPham.maLoai = loaiSanPham.maLoai \r\n"
					+ "  where soLuong = 0 and " + loai;
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
//            	printResultSet(rs);

				SanPham sanPham = new SanPham(rs);
				dataList.add(sanPham);
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

	public int getSoLuongSP(int maSP) {
		PreparedStatement stmt = null;
		try {

			String sql = "SELECT soLuong FROM dbo.SanPham where maSP = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, maSP);
			ResultSet rs = stmt.executeQuery(sql);
			return rs.getInt("soLuong");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Map<SanPham, Integer> thongKeSPBanChay(boolean isSach, int minSoLuong) {
		Map<SanPham, Integer> kq = new LinkedHashMap<SanPham, Integer>();
		PreparedStatement stmt = null;
		try {
			String loai = "";
			if (isSach)
				loai = " (tenLoai like N'%Sách%' or tenLoai like N'%Truyện%') ";
			else {
				loai = " (tenLoai not like N'%Sách%' and tenLoai not like N'%Truyện%') ";
			}

			String soLuong = " ";
			if (minSoLuong != 0) {
				soLuong = " and sum([ChiTietHoaDon].soLuong) >= " + minSoLuong + "\r\n";
			}

			String sql = "select SanPham.maSP, tenSP, TacGia, soTrang, namXuatBan, maNCC, dongia, SanPham.MaLoai, TenLoai, sum([ChiTietHoaDon].soLuong) as soLuongDaBan\r\n"
					+ "from [HieuSach].[dbo].[ChiTietHoaDon]\r\n" + "inner join [HieuSach].[dbo].[SanPham]\r\n"
					+ "on ChiTietHoaDon.maSP = SanPham.maSP\r\n" + "inner join [HieuSach].[dbo].[LoaiSanPham]\r\n"
					+ "on LoaiSanPham.maLoai = SanPham.maLoai\r\n"
					+ "group by SanPham.maSP, SanPham.maSP, tenSP, TacGia, soTrang, namXuatBan, maNCC, dongia, SanPham.MaLoai, TenLoai\r\n"
					+ "having " + loai + soLuong + "order by soLuongDaBan desc";
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
//            	printResultSet(rs);
				NhaCungCap ncc = new NhaCungCapDAO().getNhaCungCap(rs.getInt("maNCC"));
				SanPham sp = new SanPham(rs.getInt("maSP"), rs.getString("tenSP"), rs.getDouble("donGia"), ncc);
				sp.setTacGia(rs.getString("TacGia"));
				sp.setSoTrang(rs.getInt("soTrang"));
				sp.setNamXuatBan(rs.getInt("namXuatBan"));
				kq.put(sp, rs.getInt("soLuongDaBan"));
			}

			return kq;

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

	public Map<SanPham, Integer> thongKeSPBanChay(Date tuNgay, Date toiNgay, boolean isSach, int minSoLuong) {
		Map<SanPham, Integer> kq = new LinkedHashMap<SanPham, Integer>();
		PreparedStatement stmt = null;
		try {
			String loai = "";
			if (isSach)
				loai = " (tenLoai like N'%Sách%' or tenLoai like N'%Truyện%') ";
			else {
				loai = " (tenLoai not like N'%Sách%' and tenLoai not like N'%Truyện%') ";
			}

			String soLuong = " ";
			if (minSoLuong != 0) {
				soLuong = " and sum([ChiTietHoaDon].soLuong) >= " + minSoLuong + " ";
			}

			String sql = "select maSP, tenSP, TacGia, soTrang, namXuatBan, maNCC, dongia, maLoai, tenLoai, sum(soLuongDaBan) as soLuongDaBan \r\n"
					+ "from (select SanPham.maSP, tenSP, TacGia, soTrang, namXuatBan, maNCC, [LoaiSanPham].MaLoai, tenLoai, dongia, ngayMua, sum([ChiTietHoaDon].soLuong) as soLuongDaBan \r\n"
					+ "	from [HieuSach].[dbo].[ChiTietHoaDon]\r\n" + "	inner join [HieuSach].[dbo].[SanPham]\r\n"
					+ "	on ChiTietHoaDon.maSP = SanPham.maSP\r\n" + "	inner join [HieuSach].[dbo].[LoaiSanPham]\r\n"
					+ "	on [LoaiSanPham].maLoai = SanPham.maLoai\r\n" + "	inner join [HieuSach].[dbo].[HoaDon]\r\n"
					+ "	on ChiTietHoaDon.maHD = HoaDon.maHD\r\n"
					+ "	group by SanPham.maSP, SanPham.maSP, tenSP, TacGia, soTrang, namXuatBan, maNCC, dongia, ngayMua, [LoaiSanPham].maLoai, tenLoai\r\n"
					+ "	having ngayMua >= ? and ngayMua <= ? and " + loai + soLuong + ") as cthd\r\n"
					+ "group by cthd.maSP, cthd.maSP, tenSP, TacGia, soTrang, namXuatBan, maNCC, dongia, maLoai, tenLoai\r\n"
					+ "order by soLuongDaBan desc";
			System.out.println(sql);
			System.out.println(Ngay.tuNgay(tuNgay));
			System.out.println(Ngay.toiNgay(toiNgay));
			stmt = this.conn.prepareStatement(sql);
			stmt.setTimestamp(1, Ngay.tuNgay(tuNgay));
            stmt.setTimestamp(2, Ngay.toiNgay(toiNgay));
            
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				printResultSet(rs);
				NhaCungCap ncc = new NhaCungCapDAO().getNhaCungCap(rs.getInt("maNCC"));
				SanPham sp = new SanPham(rs.getInt("maSP"), rs.getString("tenSP"), rs.getDouble("donGia"), ncc);
				sp.setTacGia(rs.getString("TacGia"));
				sp.setSoTrang(rs.getInt("soTrang"));
				sp.setNamXuatBan(rs.getInt("namXuatBan"));
				kq.put(sp, rs.getInt("soLuongDaBan"));
			}

			return kq;

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

	public int soLuongDaBanHomNay() {
		PreparedStatement stmt = null;
		try {

			String sql = "select sum(soLuong) as soLuong\r\n" + "from [HieuSach].[dbo].[HoaDon]\r\n"
					+ "inner join [HieuSach].[dbo].[ChiTietHoaDon]\r\n" + "on hoaDon.maHD = chiTietHoaDon.maHD\r\n"
					+ "where ngayMua = '11/03/2021'";
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (!rs.next())
				return 0;

			return rs.getInt("soLuong");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public ArrayList<SanPham> timKiem(String keyword) {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();
		PreparedStatement stmt = null;
		try {

			String sql = "SELECT * FROM dbo.SanPham inner join loaiSanPham on SanPham.MaLoai = loaiSanPham.MaLoai inner join NhaCungCap on SanPham.MaNCC = NhaCungCap.MaNCC\r\n"
					+ "where TenSP like ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//                System.out.println(rs);
//				printResultSet(rs);
				SanPham sanPham = new SanPham(rs);
				dataList.add(sanPham);
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

	public Map<String, Integer> thongKeSoLuongBanRa() {
		Map<String, Integer> kq = new HashMap<String, Integer>();
		PreparedStatement stmt = null;
		try {

			String sql = "select SanPham.maSP, SanPham.TenSP, sum(ChiTietHoaDon.SoLuong) as sl\r\n"
					+ "from [HieuSach].[dbo].[ChiTietHoaDon]\r\n" + "inner join [HieuSach].[dbo].[SanPham]\r\n"
					+ "on ChiTietHoaDon.maSP = SanPham.maSP\r\n" + "group by SanPham.maSP, SanPham.TenSP";
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				printResultSet(rs);
				kq.put(rs.getString("tenSP"), rs.getInt("sl"));
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
		return kq;
	}

	public static void main(String[] args) throws SQLException {
		SanPhamDAO sanPhamDao = new SanPhamDAO();
//    	System.out.println(sanPhamDao.getListSanPham());
		System.out.println(sanPhamDao.getListSach());
		// sanPhamDao.createNCC("phuong");
	}
}
