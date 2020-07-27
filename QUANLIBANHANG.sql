﻿CREATE DATABASE QUANLIBANHANGDB
ON
(
	NAME="QUANLIBANHANG",
	filename="H:\align\QUANLIBANHANG\QUANLIBANHANG.MDF",
	SIZE=100MB,
	MAXSIZE= 200MB,
	FILEGROWTH=10MB
)
LOG ON
(
	NAME="QUANLIBANHANG_LOG",
	FILENAME = "H:\align\QUANLIBANHANG\QUANLIBANHANG_LOG.LDF",
	SIZE=100MB,
	MAXSIZE= 200MB,
	FILEGROWTH=10MB
)
go
USE  QUANLIBANHANGDB
GO

-- SẢN PHẨM
CREATE TABLE SANPHAM
(
MASANPHAM VARCHAR(10) NOT NULL,
TENSANPHAM NVARCHAR(50) NOT NULL,
SOLUONG INT NOT NULL,
GIA MONEY NOT NULL,
KHUYENMAI INT NOT NULL,
NGAYNHAPKHO DATE NOT NULL,
TINHTRANG NVARCHAR(30) ,
CAUHINH NVARCHAR(100) ,
CONSTRAINT PK_SANPHAM PRIMARY KEY(MASANPHAM)
)
GO

CREATE TABLE KHACHHANG
(
MAKHACHHANG VARCHAR(10) unique NOT NULL,
--HOKHACHHANG NVARCHAR(7) NOT NULL,
--TENDEMKHACHHANG NVARCHAR(15) NOT NULL,
--TENKHACHHANG NVARCHAR(7) NOT NULL,
HOTENKHACHHANG NVARCHAR(30) NOT NULL,
GIOITINH BIT NOT NULL, -- 0 NỮ 1 NAM
--NGAYSINH DATE NULL,
SODIENTHOAI VARCHAR(13) NOT NULL,
DIACHI NVARCHAR(55) NULL,
--KHACHHANGVIP BIT NOT NULL,
CONSTRAINT PK_KHACHHANG PRIMARY KEY(MAKHACHHANG)
)
GO

CREATE TABLE TAIKHOAN
(
ID int identity (1,1)  NOT NULL,
TAIKHOAN VARCHAR(20) NOT NULL,
MATKHAU VARCHAR(20) NOT NULL,

--HOQUANLI NVARCHAR(7) NOT NULL,
--TENDEMQUANLI NVARCHAR(15) NOT NULL,
--TENQUANLI NVARCHAR(7) NOT NULL,
HOTENQUANLI NVARCHAR(30) NOT NULL,
SODIENTHOAI VARCHAR(13) NOT NULL,
CHUCVU NVARCHAR(30) NOT NULL,
CONSTRAINT PK_QUANLI PRIMARY KEY(ID)
)
GO

CREATE TABLE HOADON
(
MAHOADON VARCHAR(15) NOT NULL,
MAKHACHHANG VARCHAR(10) NOT NULL,
ID int NOT NULL,
--TRAGOP BIT ,
CONSTRAINT PK_HOADON PRIMARY KEY (MAHOADON), 
CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (MAKHACHHANG) REFERENCES KHACHHANG(MAKHACHHANG),
CONSTRAINT FK_HOADON_TAIKHOAN FOREIGN KEY (ID) REFERENCES TAIKHOAN(ID)
)
GO
CREATE TABLE HOADONCHITIET
(
--MAHOADONCHITIET VARCHAR(15) NOT NULL,
MAHOADON VARCHAR(15) NOT NULL,
MASANPHAM VARCHAR(10) NOT NULL,
SOLUONG INT NOT NULL,
NGAYMUAHANG DATE NOT NULL,
GIA MONEY NOT NULL,
--KHUYENMAI INT ,
TONGTIEN  MONEY NOT NULL,
CONSTRAINT PK_HOADONCHITIET PRIMARY KEY (MAHOADON,MASANPHAM), 
CONSTRAINT FK_MAHOADONCHITIET_SANPHAM FOREIGN KEY (MASANPHAM) REFERENCES SANPHAM(MASANPHAM),
CONSTRAINT FK_MAHOADONCHITIET_HOADON FOREIGN KEY (MAHOADON) REFERENCES HOADON(MAHOADON)
)
GO
CREATE TABLE BAOHANH
(
MABAOHANH VARCHAR(10) PRIMARY KEY NOT NULL,
MASANPHAM VARCHAR(10) NOT NULL,
MAKHACHHANG VARCHAR(10) NOT NULL,
HOTENKHACHHANG NVARCHAR(30) NOT NULL,
SODIENTHOAI VARCHAR(13) NOT NULL,
NGAYMUAHANG DATE NOT NULL, --()
THOIGIANBAOHANH INT NOT NULL, --(NĂM)
NGAYHETBAOHANH DATE NOT NULL, --()
TRANGTHAIBAOHANH BIT NOT NULL,-- 0 CÒN BH, 1 HẾT BH
CONSTRAINT FK_BAOHANH_SANPHAM FOREIGN KEY (MASANPHAM) REFERENCES SANPHAM(MASANPHAM),
CONSTRAINT FK_BAOHANH_KHACHHANG FOREIGN KEY (MAKHACHHANG) REFERENCES KHACHHANG(MAKHACHHANG),
)
GO



create view sanphambanchay
as
select TENSANPHAM,HOADONCHITIET.SOLUONG 
from  SANPHAM inner join HOADONCHITIET on SANPHAM.MASANPHAM = HOADONCHITIET.MASANPHAM
--order by  HOADONCHITIET.SOLUONG desc
--where MASANPHAM like 
select * from sanphambanchay
select TENSANPHAM,HOADONCHITIET.SOLUONG 
from  SANPHAM inner join HOADONCHITIET on SANPHAM.MASANPHAM = HOADONCHITIET.MASANPHAM
order by TENSANPHAM desc


select TENSANPHAM,HOADONCHITIET.SOLUONG 
from  SANPHAM inner join HOADONCHITIET on SANPHAM.MASANPHAM = HOADONCHITIET.MASANPHAM
where NGAYMUAHANG   between '2000-02-02'and '2003-02-02'
order by  HOADONCHITIET.SOLUONG desc
select * from HOADONCHITIET
select MAKHACHHANG,HOTENKHACHHANG,GIOITINH,SODIENTHOAI,DIACHI from KHACHHANG
update KHACHHANG
set 
HOTENKHACHHANG = ?,
GIOITINH =?,
SODIENTHOAI=?,
DIACHI=?
where MAKHACHHANG = ?
00

select MAKHACHHANG,HOTENKHACHHANG,GIOITINH,SODIENTHOAI,DIACHI from KHACHHANG where MAKHACHHANG = 0 or HOTENKHACHHANG = 0 or SODIENTHOAI =2