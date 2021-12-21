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

insert into TaiKhoan (TaiKhoan, MatKhau) values('nhanvien3', 'nhanvien3');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Phan Đình Phương', '0354043344', N'Hải Phòng', 13);
insert into NhanVien (TenNv, SoDienThoai, DiaChi, caLamViec, chucNang, TaiKhoanID) values(N'Phan Đình Phương', '0354043344', N'Hải Phòng', 2, 2, 13);

insert into TaiKhoan (TaiKhoan, MatKhau) values('nhanvien4', 'nhanvien4');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Trần Văn Nhân', '0977965717', N'Hải Dương', 14);
insert into NhanVien (TenNv, SoDienThoai, DiaChi, caLamViec, chucNang, TaiKhoanID) values(N'Trần Văn Nhân', '0354043344', N'Hải Dương', 1, 1, 14);

insert into TaiKhoan (TaiKhoan, MatKhau) values('nhanvien5', 'nhanvien5');
insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'Lê Đình Viết', '0355414564', N'Tuyên Quang', 15);
insert into NhanVien (TenNv, SoDienThoai, DiaChi, caLamViec, chucNang, TaiKhoanID) values(N'Lê Đình Viết', '0354043344', N'Tuyên Quang', 2, 2, 15);


-- Nha Cung Cap
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Kim đồng', N'Hà Nội', '0987654321');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thiên kim', N'Hà Nội', '0987654322');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Hà Nội', N'Hà Nội', '0987654222');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thiên Long', N'Hồ Chí Minh', '0987654324');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Long Hải', N'Ninh Bình', '0987654234');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thiên Phước', N'Hồ Chí Minh', '0987654356');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thế giới', N'Hồ Chí Minh', '0976214532');

-- Loai San Pham
insert into LoaiSanPham (TenLoai) values(N'Sách tâm lý');
insert into LoaiSanPham (TenLoai) values(N'Sách giáo khoa');
insert into LoaiSanPham (TenLoai) values(N'Truyện trinh thám');
insert into LoaiSanPham (TenLoai) values(N'Truyện cổ tích');
insert into LoaiSanPham (TenLoai) values(N'Bút');
insert into LoaiSanPham (TenLoai) values(N'Vở viết');
insert into LoaiSanPham (TenLoai) values(N'Thước kẻ');
insert into LoaiSanPham (TenLoai) values(N'Máy tính cầm tay');
insert into LoaiSanPham (TenLoai) values(N'Sách thiếu nhi');
insert into LoaiSanPham (TenLoai) values(N'Sách chính trị');
insert into LoaiSanPham (TenLoai) values(N'Truyện ngôn tình');
insert into LoaiSanPham (TenLoai) values(N'Sách khoa học');
insert into LoaiSanPham (TenLoai) values(N'Truyện kinh dị');


-- San Pham
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 2, N'Sách tiếng việt 1', 15000, 13000, 50, N'Sở giáo dục', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(2, 2, N'Sách tiếng việt 2', 16000, 13000, 50, N'Sở giáo dục', 150, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 2, N'Sách toán 1', 14500, 13000, 50, N'Sở giáo dục', 140, 2021);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(4, 2, N'Sách toán 2', 17000, 13000, 50, N'Sở giáo dục', 170, 2021);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 2, N'Sách đạo đức 1', 17000, 13000, 50, N'Sở giáo dục', 170, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 2, N'Sách vật lý lớp 7', 17000, 13000, 50, N'Sở giáo dục', 170, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 2, N'Sách toán 7 tập 1', 17000, 13000, 50, N'Sở giáo dục', 170, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 2, N'Sách toán 7 tập 2', 17000, 13000, 50, N'Sở giáo dục', 170, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 2, N'Sách Ngữ văn 7 tập 1', 17000, 13000, 50, N'Sở giáo dục', 170, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 2, N'Sách Ngữ văn 7 tập 2', 17000, 13000, 50, N'Sở giáo dục', 170, 2019);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(7, 1, N'Những kẻ xuất chúng', 150000, 130000, 50, N'Malcolm Gladwell', 304, 2008);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 1, N'Tư duy nhanh và chậm', 170000, 140000, 50, N'Daniel Kahneman', 170, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 1, N'The Happiness Hypothesis', 170000, 130000, 50, N'Jonathan Haidt', 320, 2006);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 1, N'Nền văn minh và sự bất mãn của nó', 170000, 130000, 50, N'Sigmund Freud', 127, 2019);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện conan tập 1', 17500, 16000, 50, N'Aoyama Gosho', 90, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện conan tập 2', 17500, 16000, 50, N'Aoyama Gosho', 90, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện conan tập 3', 17500, 16000, 50, N'Aoyama Gosho', 90, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện doraemon tập 1', 18500, 16000, 50, N'Fujiko Fujio', 90, 2014);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện doraemon tập 2', 18500, 16000, 50, N'Fujiko Fujio', 90, 2014);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 3, N'Truyện doraemon tập 3', 18500, 16000, 50, N'Fujiko Fujio', 90, 2014);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 3, N'Điệp viên kỳ quái', 185000, 160000, 50, N'Nguyệt Tri', 130, 2014);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 3, N'Hồ sơ bí ẩn', 185000, 160000, 50, N'Khố Kỳ Kỳ', 137, 2014);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 3, N'Đề thi đẫm máu', 185000, 160000, 50, N'Lôi Mể', 304, 2015);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 3, N'Án mạng trên sông Nile', 185000, 160000, 50, N'Agatha Christie', 324, 2015);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 4, N'Tấm Cám', 17500, 16000, 50, N'Nhiều tác giả', 21, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 4, N'Cây tre trăm đốt', 17500, 16000, 50, N'Nhiều tác giả', 16, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 4, N'Sọ dừa', 17500, 16000, 50, N'Nhiều tác giả', 17, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 4, N'Thạch Sanh', 17500, 16000, 50, N'Nguyễn Mạnh Thái', 20, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 4, N'Thánh Gióng', 17500, 16000, 50, N'Minh Long', 30, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 4, N'Cô bé lọ lem', 17500, 16000, 50, N'Nhiều tác giả', 10, 2016);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 4, N'Cô bé bán diêm', 17500, 16000, 50, N'Triệu Phương Phương', 18, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 4, N'Nàng Bạch Tuyết và bảy chú lùn', 17500, 16000, 50, N'Nhiều tác giả', 24, 2020);

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
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 8, N'Casio FX 570VN Plus', 680000, 570000, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 8, N'Vinacal 570ES Plus', 680000, 570000, 50);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong) values(6, 8, N'Vinacal 570EX Plus', 650000, 550000, 50);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 9, N'Không gia đình', 250000, 230000, 50, N'Hector Malot', 300, 2017);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 9, N'Những tấm lòng cao cả', 250000, 230000, 50, N'Edomondo De Amicis', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 9, N'Dế Mèn phiêu lưu kí', 150000, 130000, 50, N'Tô Hoài', 230, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 9, N'Đất rừng phương Nam', 170000, 130000, 50, N'Đoàn Giỏi', 204, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 9, N'Cái tết của mèo con', 70000, 50000, 50, N'Nguyễn Đình Thi', 70, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 9, N'Bầu trời trong quả trứng', 15000, 13000, 50, N'Xuân Quỳnh', 30, 2018);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 9, N'Tuổi thơ dữ dội', 15000, 13000, 50, N'Phùng Quán', 20, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 9, N'Chuyện hoa chuyện quả', 15000, 13000, 50, N'Phạm Hổ', 20, 2020);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(7, 10, N'Cộng hòa', 140000, 130000, 50, N'Plato', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(7, 10, N'Thế giới cho đến ngày hôm qua', 250000, 230000, 50, N'Jared Diamond', 250, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 10, N'Sụp đổ', 150000, 130000, 50, N'Jared Diamond', 200, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 10, N'Một tư duy khác về kinh tế và xã hội Việt Nam', 170000, 130000, 50, N'Alan Phan', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 10, N'Tiền không mua được gì', 150000, 130000, 50, N'Michael J. Sandel', 205, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(7, 10, N'Gã khổng lồ mất ngủ', 17000, 130000, 50, N'Susan L. Shirk', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(7, 10, N'Hồi ký chính trị', 15000, 13000, 50, N'Dr Mahathir', 302, 2020);


insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 11, N'Anh có thích nước Mỹ không?', 150000, 130000, 50, N'Tân Di Ổ', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 11, N'Từng có một người yêu tôi như sinh mệnh', 15000, 13000, 50, N'Thư Nghi', 210, 2018);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 11, N'Đáng tiếc không phải anh', 210000, 180000, 50, N'Diệp Tử', 300, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 11, N'Thất tịch không mưa', 160000, 130000, 50, N'Lâu Vũ Tình', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 11, N'Sẽ có thiên thần thay anh yêu em', 190000, 170000, 50, N'Minh Hiểu Khê', 205, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 11, N'Ốc sên chạy', 150000, 130000, 50, N'Điệp Chí Linh', 201, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 11, N'Bến xe', 150000, 130000, 50, N'Thương Thái Vi', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 11, N'Mãi mãi là bao xa', 150000, 130000, 50, N'Diệp Lạc Vô Tâm', 203, 2020);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 12, N'Lược sử thời gian', 240000, 230000, 50, N'Stephen Hawking', 256, 2017);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 12, N'Nguồn gốc các loài', 300000, 270000, 50, N'Charles Darwin', 496, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 12, N'Những nhà khám phá', 250000, 230000, 50, N'Daniel J. Boorstin', 745, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 12, N'Bản thiết kế vĩ đại', 150000, 130000, 50, N'Sở giáo dục', 208, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 12, N'Súng, vi trùng và thép', 300000, 270000, 50, N'Jared Diamond', 676, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 12, N'Mùa xuân vắng lặng', 150000, 130000, 50, N'Rachel Carson', 200, 2020);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 13, N'Kỳ án ánh trăng', 150000, 130000, 50, N'Qủy Cổ Nữ', 306, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 13, N'Hồ Tuyệt Mệnh', 140000, 120000, 50, N'Qủy Cổ Nữ', 250, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 13, N'Thủy xa quán', 70000, 40000, 50, N'Yukito Ayatsuji', 110, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(3, 13, N'Giả thuyết thứ 7', 57000, 40000, 50, N'Paul Halter', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 13, N'Ring – Vòng Tròn Ác Nghiệt', 140000, 130000, 50, N'Suzuki Kōji', 200, 2020);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 13, N'Tơ đồng rỏ máu', 84000, 70000, 50, N'Qủy Cổ Nữ', 168, 2019);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp, GiaNhap, SoLuong, TacGia, soTrang, namXuatBan) values(1, 13, N'Đau thương đến chết', 150000, 130000, 50, N'Qủy Cổ Nữ', 170, 2020);


--Hóa đơn
insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 3,235000,'2021-12-21 20:22:21');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(1, 1, 1, 15000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(2, 1, 1, 16000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(5, 1, 1, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(7, 1, 1, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(12, 1, 1, 170000);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 4,330000,'2021-11-20 21:46:43.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(33, 2, 1, 5000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(35, 2, 1, 5000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(11, 2, 1, 150000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(13, 2, 1, 170000);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 5,261000,'2021-10-17 15:30:21.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(5, 3, 2, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(6, 3, 1, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(25, 3, 3, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(27, 3, 4, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(30, 3, 5, 17500);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 6,878500,'2021-11-12 14:35:25.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(45, 4, 1, 680000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(36, 4, 1, 6000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(25, 4, 2, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(28, 4, 3, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(32, 4, 6, 17500);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 6,4787500,'2021-12-13 12:40:25.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(17, 5, 15, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(19, 5, 17, 18500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(24, 5, 20, 185000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(26, 5, 23, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(36, 5, 18, 6000);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 7,10117500,'2021-11-13 17:35:25.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(46, 6, 3, 680000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(14, 6, 19, 170000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(24, 6, 21, 185000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(28, 6, 30, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(32, 6, 25, 17500);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 8,12822500,'2021-11-19 14:35:25.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(22, 7, 21, 185000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(23, 7, 21, 185000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(24, 7, 22, 185000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(25, 7, 31, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(26, 7, 6, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(32, 7, 14, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(34, 7, 15, 6000);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 9,2786500,'2021-12-11 11:35:25.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(1, 8, 30, 15000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(3, 8, 31, 14500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(5, 8, 26, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(7, 8, 37, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(8, 8, 16, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(9, 8, 16, 17000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(10, 8, 16, 17000);


insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 14,5110000,'2021-11-18 14:3:25.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(45, 9, 1, 680000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(46, 9, 1, 680000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(47, 9, 2, 650000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(48, 9, 2, 250000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(50, 9, 5, 150000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(51, 9, 5, 170000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(52, 9, 5, 70000);

insert into HoaDon(MaNV, MaKH,TongTien,NgayMua) values(1, 16,2265000,'2021-11-19 10:35:25.150');
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(45, 10, 1, 680000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(36, 10, 25, 6000);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(25, 10, 23, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(28, 10, 33, 17500);
insert into ChiTietHoaDon(MaSP, MaHD, SoLuong,DonGia) values(32, 10, 26, 17500);


-- Đơn đặt hàng
insert into DonDatHang(maKH, TongTien, NgayDat,tinhTrang) values(7,2265000,'2021-11-14 10:35:25.150',1)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(1,45,1,680000)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(1,36,25,6000)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(1,25,23,17500)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(1,28,33,17500)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(1,32,26,17500)

insert into DonDatHang(maKH, TongTien, NgayDat,tinhTrang) values(8,2786500,'2021-11-17 9:35:25.150',1)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(2,1,30, 15000)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(2,3,31, 14500)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(2,5,26, 17000)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(2,7,37, 17000)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(2,8,16, 17000)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(2,9,16, 17000)
insert into ChiTietDonDatHang(MaDDH,MaSP,SoLuong,DonGia) values(2,10,16, 17000)








