create database HieuSach

GO

use HieuSach

CREATE TABLE NhanVien (
	MaNv int IDENTITY NOT NULL, 
	TaiKhoanID int NOT NULL, 
	TenNv nvarchar(50) NULL, 
	SoDienThoai varchar(15) NULL, 
	DiaChi nvarchar(255) NULL, 
	CaLamViec int default 0,
	ChucNang int default 0,
	PRIMARY KEY (MaNv)
);

CREATE TABLE SanPham (
	MaSP int IDENTITY NOT NULL, 
	MaNCC int NOT NULL, 
	MaLoai int NOT NULL, 
	TenSp nvarchar(255) NULL, 
	GiaSp float(10) NOT NULL, 
	GiaNhap float(10) NOT NULL, 
	SoLuong int default 0,
	TacGia nvarchar(50) NULL, 
	SoTrang int NULL,
	NamXuatBan int NULL,
	PRIMARY KEY (MaSP)
);

CREATE TABLE KhachHang (
	MaKH int IDENTITY NOT NULL, 
	HoTen nvarchar(50) NULL, 
	SoDienThoai varchar(15) NULL, 
	DiaChi nvarchar(255) NULL, 
	TaiKhoanID int NOT NULL, 
	PRIMARY KEY (MaKH)
);
	
CREATE TABLE HoaDon (
	MaHD int IDENTITY NOT NULL, 
	MaNV int NOT NULL, 
	MaKH int NOT NULL, 
	TongTien float(10) NOT NULL, 
	NgayMua datetime default GETDATE(),
	PRIMARY KEY (MaHD)
);


CREATE TABLE ChiTietHoaDon (
	ID int IDENTITY NOT NULL, 
	MaSP int NOT NULL, 
	MaHD int NOT NULL, 
	SoLuong int NOT NULL, 
	DonGia float(10) NOT NULL, 
	PRIMARY KEY (ID)
);

CREATE TABLE TaiKhoan (
	ID int IDENTITY NOT NULL, 
	TaiKhoan varchar(50) NULL, 
	MatKhau varchar(255) NULL, 
	PRIMARY KEY (ID)
);

CREATE TABLE LoaiSanPham (
	MaLoai int IDENTITY NOT NULL, 
	TenLoai nvarchar(255) NULL, 
	PRIMARY KEY (MaLoai)
);

CREATE TABLE NhaCungCap (
	MaNCC int IDENTITY NOT NULL, 
	TenNCC nvarchar(255) NULL, 
	DiaChi nvarchar(255) NULL, 
	SoDienThoai varchar(255) NULL, 
	PRIMARY KEY (MaNCC)
);

CREATE TABLE DonDatHang (
	MaDDH int IDENTITY NOT NULL, 
	maKH int NOT NULL, 
	TongTien float(10) NOT NULL, 
	NgayDat date NULL, 
	tinhTrang int default 0,
	PRIMARY KEY (MaDDH)
);

CREATE TABLE ChiTietDonDatHang (
	ID int IDENTITY NOT NULL, 
	MaDDH int NOT NULL, 
	MaSP int NOT NULL, 
	SoLuong int NOT NULL, 
	DonGia float(10) NOT NULL, 
	PRIMARY KEY (ID)
);

ALTER TABLE SanPham ADD CONSTRAINT FKSanPham622519 FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham (MaLoai);
ALTER TABLE NhanVien ADD CONSTRAINT FKNhanVien63380 FOREIGN KEY (TaiKhoanID) REFERENCES TaiKhoan (ID);
ALTER TABLE SanPham ADD CONSTRAINT FKSanPham756167 FOREIGN KEY (MaNCC) REFERENCES NhaCungCap (MaNCC);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon785667 FOREIGN KEY (MaKH) REFERENCES KhachHang (MaKH);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon185080 FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNv);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa443020 FOREIGN KEY (MaHD) REFERENCES HoaDon (MaHD);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa746492 FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP);
ALTER TABLE DonDatHang ADD CONSTRAINT FKDonDatHang885023 FOREIGN KEY (maKH) REFERENCES KhachHang (MaKH);
ALTER TABLE ChiTietDonDatHang ADD CONSTRAINT FKChiTietDon328320 FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP);
ALTER TABLE ChiTietDonDatHang ADD CONSTRAINT FKChiTietDon937162 FOREIGN KEY (MaDDH) REFERENCES DonDatHang (MaDDH);
ALTER TABLE KhachHang ADD CONSTRAINT FKKhachHang937708 FOREIGN KEY (TaiKhoanID) REFERENCES TaiKhoan (ID);

GO

-- admin
insert into TaiKhoan (TaiKhoan, MatKhau) values('admin', 'admin');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'admin', '0987654321', N'', 1);
insert into NhanVien (TenNv, SoDienThoai, DiaChi, caLamViec, chucNang, TaiKhoanID) values(N'admin', '0987654321', N'', 1, 3, 1);

-- khach hang
insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang1', 'khachhang1');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Trần Văn A', '0922222222', N'Hà Nội', 2);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang2', 'khachhang2');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Trần Văn B', '0933333333', N'Hà Nội', 3);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang3', 'khachhang3');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Nguyễn Khuyến', '0944444444', N'Hà Nam', 3);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang4', 'khachhang4');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Nguyễn Tiến Vũ', '0923232323', N'Nam Định', 4);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang5', 'khachhang5');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Võ Trần', '0925555553', N'Nam Hải', 5);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang6', 'khachhang6');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Nguyễn Thái Vũ', '0924444444', N'Nghệ An', 6);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang6', 'khachhang7');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Trần Văn C', '0999999999', N'Hà Nội', 7);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang7', 'khachhang8');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Nguyễn Văn Khuyến', '09456565656', N'Hà Nam Ninh', 8);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang8', 'khachhang9');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Nguyễn Văn Nam', '0923238787', N'Hà Tĩnh', 9);

insert into TaiKhoan (TaiKhoan, MatKhau) values('khachhang10', 'khachhang11');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Võ Trần Phước', '0923238799', N'Hồ Chí Minh', 10);

-- nhan vien
insert into TaiKhoan (TaiKhoan, MatKhau) values('nhanvien1', 'nhanvien1');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Trần Hải', '0987654333', N'Hà Nội', 11);
insert into NhanVien (TenNv, SoDienThoai, DiaChi, caLamViec, chucNang, TaiKhoanID) values(N'Trần Hải', '0987654333', N'Hà Nội', 1, 1, 11);

insert into TaiKhoan (TaiKhoan, MatKhau) values('nhanvien2', 'nhanvien2');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Trần Phước', '0923238222', N'Hà Nội', 12);
insert into NhanVien (TenNv, SoDienThoai, DiaChi, caLamViec, chucNang, TaiKhoanID) values(N'Trần Phước', '0923238222', N'Hà Nội', 2, 2, 12);


-- Nha Cung Cap
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Kim đồng', N'Hà Nội', '0987654321');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thiên kim', N'Hà Nội', '0987654322');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Hà Nội', N'Hà Nội', '0987654222');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thiên Long', N'Hồ Chí Minh', '0987654324');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Long Hải', N'Ninh Bình', '0987654234');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thiên Phước', N'Hồ Chí Minh', '0987654356');

-- Loai San Pham
insert into LoaiSanPham (TenLoai) values(N'Sách tâm lý');
insert into LoaiSanPham (TenLoai) values(N'Sách giáo khoa');
insert into LoaiSanPham (TenLoai) values(N'Truyện trinh thám');
insert into LoaiSanPham (TenLoai) values(N'Truyện cổ tích');
insert into LoaiSanPham (TenLoai) values(N'Bút');
insert into LoaiSanPham (TenLoai) values(N'Vở viết');
insert into LoaiSanPham (TenLoai) values(N'Thước kẻ');
insert into LoaiSanPham (TenLoai) values(N'Máy tính cầm tay');

-- San Pham
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 2, N'Sách tiếng việt 1', 15000, 13000, 50, N'Sở giáo dục', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(2, 2, N'Sách tiếng việt 2', 16000, 13000, 50, N'Sở giáo dục', 150, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 2, N'Sách toán 1', 14500, 13000, 50, N'Sở giáo dục', 140, 2021);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(4, 2, N'Sách toán 2', 17000, 13000, 50, N'Sở giáo dục', 170, 2021);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 2, N'Sách đạo đức 1', 17000, 13000, 50, N'Sở giáo dục', 170, 2019);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện conan tập 1', 17500, 16000, 50, 'Aoyama Gosho', 90, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện conan tập 2', 17500, 16000, 50, 'Aoyama Gosho', 90, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện conan tập 3', 17500, 16000, 50, 'Aoyama Gosho', 90, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện doraemon tập 1', 18500, 16000, 50, 'Fujiko Fujio', 90, 2014);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện doraemon tập 2', 18500, 16000, 50, 'Fujiko Fujio', 90, 2014);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện doraemon tập 3', 18500, 16000, 50, 'Fujiko Fujio', 90, 2014);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(5, 5, N'Bút bi', 5000, 4500, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 5, N'Bút chì', 6000, 4500, 50);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(5, 6, N'Vở 5 ô ly', 5000, 4500, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 6, N'Vở 4 ô ly', 6000, 4500, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(5, 6, N'Vở ghi chú cầm tay', 5500, 4500, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 6, N'Vở 200 trang', 6000, 4500, 50);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(5, 7, N'Thước kẻ 20cm', 5000, 4500, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 7, N'Thước đo độ', 6000, 4500, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(5, 7, N'Thước đo tam giác', 5500, 4500, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 7, N'Thước gỗ 30cm', 15000, 14500, 50);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(5, 8, N'Casio fx570es', 500000, 400000, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 8, N'Casio fx570vn', 650000, 550000, 50);

