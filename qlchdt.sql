-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: qlchdt
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cthd`
--

DROP TABLE IF EXISTS `cthd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cthd` (
  `MaHD` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  `DonGia` float DEFAULT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `Fk MaSP_2` (`MaSP`),
  CONSTRAINT `Fk` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Fk MaSP_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cthd`
--

LOCK TABLES `cthd` WRITE;
/*!40000 ALTER TABLE `cthd` DISABLE KEYS */;
INSERT INTO `cthd` VALUES ('B01','IPJC7',1,43900000);
/*!40000 ALTER TABLE `cthd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctpn`
--

DROP TABLE IF EXISTS `ctpn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctpn` (
  `MaPN` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int DEFAULT NULL,
  `DonGia` float DEFAULT NULL,
  PRIMARY KEY (`MaPN`),
  KEY `Fk MaSP_1` (`MaSP`),
  CONSTRAINT `Fk MaSP_1` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctpn`
--

LOCK TABLES `ctpn` WRITE;
/*!40000 ALTER TABLE `ctpn` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctpn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hangsanpham`
--

DROP TABLE IF EXISTS `hangsanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hangsanpham` (
  `MaHSP` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenHSP` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MaHSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hangsanpham`
--

LOCK TABLES `hangsanpham` WRITE;
/*!40000 ALTER TABLE `hangsanpham` DISABLE KEYS */;
INSERT INTO `hangsanpham` VALUES ('IP','Iphone'),('NO','Nokia'),('OP','Oppo'),('SS','Samsung'),('XI','Xiaomi');
/*!40000 ALTER TABLE `hangsanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgayLap` date DEFAULT NULL,
  `TongTien` float DEFAULT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `fk manv_idx` (`MaNV`),
  CONSTRAINT `Fk MaNv1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('B01','E01','2020-05-04',47600000);
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenKH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SDT` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES ('C01',' Nguyễn Hữu Tài','Hồ Chí Minh','0987765553'),('C02','Bùi Xuân Thái','Hà Nội','0368556782'),('C03','Trịnh Văn Quý','Bắc Cạn','0956777654'),('C04','Lê Văn Minh','Hải Phòng','0909634257'),('C05','Tiến','Wuhan','0815362211');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNCC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SDT` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SDT` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('E01','Lê Thanh Bảo','1997-12-03','Nam','0987684756 ','Hồ Chí Minh'),('E02','Nguyễn Thiện Hiếu','1998-06-07','Nam','0987654456','Hồ Chí Minh'),('E03','Phan Chí Tâm','1992-05-12','Nam','0909878888','Hồ Chí Minh'),('E04','Vũ Thái','1990-09-09','Nam','0907876976','Hồ Chí Minh'),('E05','Ưng Hoàng Phi','1997-03-04','Nam','0906543342','Hồ Chí Minh'),('E06','Tiến','2000-06-20','Nam','0214125555','HCM');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `MaPN` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaNCC` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgayNhap` date DEFAULT NULL,
  `TrangThai` int DEFAULT NULL,
  PRIMARY KEY (`MaPN`),
  KEY `Fk MaNCC1` (`MaNCC`),
  KEY `Fk MaNv2` (`MaNV`),
  CONSTRAINT `Fk MaNCC1` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  CONSTRAINT `Fk MaNv2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MaSP` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenSP` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `HinhAnh` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `DonGia` float NOT NULL,
  `SL` int DEFAULT NULL,
  `MaHSP` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaSP`),
  KEY `MaHSP_idx` (`MaHSP`),
  CONSTRAINT `Fk MaHSP` FOREIGN KEY (`MaHSP`) REFERENCES `hangsanpham` (`MaHSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES ('IP09K','iPhone 11','Chưa cập nhật',21900000,20,'IP'),('IPJC7','iPhone 11 Pro Max','Chưa cập nhật',43990000,50,'IP'),('IPTTT','iPhone 12','Chưa cập nhật',33990000,15,'IP'),('NO76H','Nokia 7300','Chưa cập nhật',3700000,10,'NO'),('OP1J3','Oppo A10','Chưa cập nhật',6990000,50,'OP'),('OP643','Oppo Reno 2','Chưa cập nhật',14990000,50,'OP'),('SS65F','Samsung Galaxy Note 9','Chưa cập nhật',22990000,10,'SS'),('SS776','Samsung Galaxy A8','Chưa cập nhật',9990000,130,'SS'),('SS8K7','Samsung Galaxy A9','Chưa cập nhật',10990000,25,'SS'),('SS8UJ','Samsung A10','Chưa cập nhật ',15000000,42,'SS'),('SS97V','Samsung Galaxy Note 10','Chưa cập nhật',26990000,15,'SS'),('XIFAK','Xiaomi Redmi 8','Chưa cập nhật',3500000,100,'XI');
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `TenTK` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MK` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `LoaiTK` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TrangThai` int DEFAULT NULL,
  PRIMARY KEY (`TenTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('admin','123456','Quản lý',1),('cus1','123456','Khách hàng',1),('cus2','123456','Khách hàng',0),('person1','123456','Nhân viên',1),('person2','123456','Nhân viên',1),('person3','123456','Nhân viên',1);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-25 12:01:36
