-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: qlnhahang
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `MaAccount` int NOT NULL,
  `Username` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `PassWord` char(200) COLLATE utf8_unicode_ci NOT NULL,
  `TypeUser` char(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaAccount`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Aminh','0&@#$a!&*#!!&*#7!&*#h&@#$i!&*#m!&*#n&@#$','NV'),(2,'Nsang','!!&*#2!&*#3!&*#4!&*#5!&*#6!&*#','NV'),(3,'Tlen','4!&*#5!&*#6!&*#7!&*#8!&*#9!&*#','NV'),(4,'Hnguyen','4!&*#5!&*#6!&*#7!&*#8!&*#9!&*#','NV'),(7,'nhatsang','0&@#$a!&*#!!&*#7!&*#h&@#$i!&*#m!&*#n&@#$','KH'),(8,'anhminh0710','0&@#$a!&*#!!&*#7!&*#h&@#$i!&*#m!&*#n&@#$','KH'),(9,'huynhnguyen','0&@#$a!&*#!!&*#7!&*#h&@#$i!&*#m!&*#n&@#$','KH');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bophan`
--

DROP TABLE IF EXISTS `bophan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bophan` (
  `MaBP` int NOT NULL,
  `TenBP` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoLuongNV` int DEFAULT '0',
  PRIMARY KEY (`MaBP`),
  UNIQUE KEY `TenBP_UNIQUE` (`TenBP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bophan`
--

LOCK TABLES `bophan` WRITE;
/*!40000 ALTER TABLE `bophan` DISABLE KEYS */;
INSERT INTO `bophan` VALUES (1,'Nhà bếp',3),(2,'Lễ tân',1),(3,'Phục vụ',1),(4,'Marketing',1);
/*!40000 ALTER TABLE `bophan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datdichvu`
--

DROP TABLE IF EXISTS `datdichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datdichvu` (
  `MaTiec` int NOT NULL,
  `MaDV` int NOT NULL,
  PRIMARY KEY (`MaTiec`,`MaDV`),
  KEY `fk_datdichvu_dichvu_idx` (`MaDV`),
  CONSTRAINT `fk_datdichvu_dattiec` FOREIGN KEY (`MaTiec`) REFERENCES `dattiec` (`MaTiec`) ON DELETE CASCADE,
  CONSTRAINT `fk_datdichvu_dichvu` FOREIGN KEY (`MaDV`) REFERENCES `dichvu` (`MaDV`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datdichvu`
--

LOCK TABLES `datdichvu` WRITE;
/*!40000 ALTER TABLE `datdichvu` DISABLE KEYS */;
INSERT INTO `datdichvu` VALUES (3,1),(4,2),(3,3),(4,4),(3,5);
/*!40000 ALTER TABLE `datdichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datmonan`
--

DROP TABLE IF EXISTS `datmonan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datmonan` (
  `MaTiec` int NOT NULL,
  `MaMA` int NOT NULL,
  `SoLuong` int NOT NULL,
  `ThanhTien` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`MaTiec`,`MaMA`),
  KEY `fk_datmonan_monan_idx` (`MaMA`),
  CONSTRAINT `fk_datmonan_dattiec` FOREIGN KEY (`MaTiec`) REFERENCES `dattiec` (`MaTiec`) ON DELETE CASCADE,
  CONSTRAINT `fk_datmonan_monan` FOREIGN KEY (`MaMA`) REFERENCES `monan` (`MaMA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datmonan`
--

LOCK TABLES `datmonan` WRITE;
/*!40000 ALTER TABLE `datmonan` DISABLE KEYS */;
INSERT INTO `datmonan` VALUES (2,3,10,850000),(3,1,10,1000000),(3,3,20,1700000),(3,5,20,1000000),(3,8,20,6000000),(4,1,100,10000000),(4,4,100,500000);
/*!40000 ALTER TABLE `datmonan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dattiec`
--

DROP TABLE IF EXISTS `dattiec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dattiec` (
  `MaTiec` int NOT NULL,
  `TenTiec` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MaSanh` int DEFAULT NULL,
  `MaKH` int DEFAULT NULL,
  `NgayDat` date DEFAULT NULL,
  `NgayToChuc` date DEFAULT NULL,
  `SoLuongBan` int DEFAULT NULL,
  `SoLuongKhach` int DEFAULT NULL,
  `Buoi` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaTiec`),
  KEY `fk_dattiec_sanh_idx` (`MaSanh`),
  KEY `fk_dattiec_khachhang_idx` (`MaKH`),
  CONSTRAINT `fk_dattiec_khachhang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE SET NULL,
  CONSTRAINT `fk_dattiec_sanh` FOREIGN KEY (`MaSanh`) REFERENCES `sanh` (`MaSanh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dattiec`
--

LOCK TABLES `dattiec` WRITE;
/*!40000 ALTER TABLE `dattiec` DISABLE KEYS */;
INSERT INTO `dattiec` VALUES (1,'Tiệc cưới',1,3,'2022-01-15','2022-01-15',10,100,'Tối'),(2,'Tiệc cưới Nhật Sang',3,3,'2022-01-15','2022-01-15',10,100,'Sáng'),(3,'Anh Minh',1,3,'2022-01-16','2022-01-23',40,400,'Sáng'),(4,'Tiệc cưới NSUT',1,4,'2022-01-16','2022-01-23',40,400,'Tối');
/*!40000 ALTER TABLE `dattiec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvu`
--

DROP TABLE IF EXISTS `dichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvu` (
  `MaDV` int NOT NULL,
  `TenDV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DonGia` decimal(18,0) NOT NULL,
  `isDeleted` date DEFAULT NULL,
  PRIMARY KEY (`MaDV`),
  UNIQUE KEY `TenDV_UNIQUE` (`TenDV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu`
--

LOCK TABLES `dichvu` WRITE;
/*!40000 ALTER TABLE `dichvu` DISABLE KEYS */;
INSERT INTO `dichvu` VALUES (1,'Karaoke',150000,NULL),(2,'Thuê DJ',100000,NULL),(3,'Diễn hài',50000,NULL),(4,'Thuê ca sĩ',2000000,NULL),(5,'Thuê MC',550000,NULL);
/*!40000 ALTER TABLE `dichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` int NOT NULL,
  `MaTiec` int DEFAULT NULL,
  `ThanhTien` decimal(18,0) DEFAULT NULL,
  `TinhTrang` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NgayLap` date DEFAULT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `fk_hoadon_dattiec_idx` (`MaTiec`),
  CONSTRAINT `fk_hoadon_dattiec` FOREIGN KEY (`MaTiec`) REFERENCES `dattiec` (`MaTiec`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,1000000,'Chưa thanh toán!','2022-01-15'),(2,2,3850000,'Đã thanh toán','2022-01-15'),(3,3,11450000,'Đã thanh toán','2022-01-16'),(4,4,13600000,'Đã thanh toán','2022-01-16');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` int NOT NULL,
  `TenKH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GioiTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CMND` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SDT` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MaAcc` int NOT NULL,
  PRIMARY KEY (`MaKH`),
  UNIQUE KEY `MaAcc_UNIQUE` (`MaAcc`),
  KEY `fk_KhachHang_Account_idx` (`MaAcc`),
  CONSTRAINT `fk_KhachHang_Account` FOREIGN KEY (`MaAcc`) REFERENCES `account` (`MaAccount`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (2,'Huỳnh Nguyễn','Nam','242424','9781931','ssafsfasf',7),(3,'Anh Minh','Nam','987654321','123456789','Biên Hoà',8),(4,'Lên Võ','Nam','345345','13214223','dfđ',9);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monan`
--

DROP TABLE IF EXISTS `monan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monan` (
  `MaMA` int NOT NULL,
  `TenMA` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DonGia` decimal(18,0) DEFAULT NULL,
  `Loai` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DonViTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `isDeleted` date DEFAULT NULL,
  PRIMARY KEY (`MaMA`),
  UNIQUE KEY `TenMA_UNIQUE` (`TenMA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monan`
--

LOCK TABLES `monan` WRITE;
/*!40000 ALTER TABLE `monan` DISABLE KEYS */;
INSERT INTO `monan` VALUES (1,'Cơm chiên dương châu hải sản',100000,'Thức ăn','Dĩa',NULL),(2,'Vi cá mập',5000000,'Thức ăn','Phần',NULL),(3,'Chim trắng mồ côi',85000,'Thức ăn','Dĩa',NULL),(4,'Chuối đông',5000,'Thức ăn','Dĩa',NULL),(5,'Tôm hùm Alaska',50000,'Thức ăn','Phần',NULL),(6,'Nước suối',15000,'Thức uống','Chai',NULL),(7,'Rượu vang',2000000,'Thức uống','Chai',NULL),(8,'Pepsi',300000,'Thức uống','Thùng',NULL),(9,'Bia Heineken',300000,'Thức uống','Thùng',NULL),(10,'Sting',400000,'Thức uống','Thùng',NULL);
/*!40000 ALTER TABLE `monan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` int NOT NULL,
  `TenNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SDT` char(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CMND` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaBP` int DEFAULT NULL,
  `ChucVu` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Luong` decimal(18,0) DEFAULT NULL,
  `MaAccount` int NOT NULL,
  PRIMARY KEY (`MaNV`),
  UNIQUE KEY `MaAccount_UNIQUE` (`MaAccount`),
  KEY `fk_nhanvien_account_idx` (`MaAccount`),
  KEY `fk_nhanvien_bophan_idx` (`MaBP`),
  CONSTRAINT `fk_nhanvien_account` FOREIGN KEY (`MaAccount`) REFERENCES `account` (`MaAccount`) ON DELETE CASCADE,
  CONSTRAINT `fk_nhanvien_bophan` FOREIGN KEY (`MaBP`) REFERENCES `bophan` (`MaBP`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Anh Minh','982527982','241647374',1,'Testers',2000,1),(2,'Nhật Sang','973776072','241347383',2,'Testers',3000,2),(3,'Thành Lên','917749254','241483738',3,'Testers',2500,3),(4,'Huỳnh Nguyễn','385626803','241939688',4,'Testers',2700,4);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanh`
--

DROP TABLE IF EXISTS `sanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanh` (
  `MaSanh` int NOT NULL,
  `TenSanh` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Tang` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DonGia` decimal(18,0) DEFAULT NULL,
  `SucChua` int DEFAULT NULL,
  `isDeleted` date DEFAULT NULL,
  PRIMARY KEY (`MaSanh`),
  UNIQUE KEY `TenSanh_UNIQUE` (`TenSanh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanh`
--

LOCK TABLES `sanh` WRITE;
/*!40000 ALTER TABLE `sanh` DISABLE KEYS */;
INSERT INTO `sanh` VALUES (1,'Sảnh A1','1',1000000,400,NULL),(2,'Sảnh A2','1',2000000,350,NULL),(3,'Sảnh B1','2',3000000,200,NULL),(4,'Sảnh B2','2',4000000,150,NULL),(5,'Sảnh C','3',5000000,100,NULL);
/*!40000 ALTER TABLE `sanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'qlnhahang'
--

--
-- Dumping routines for database 'qlnhahang'
--
/*!50003 DROP PROCEDURE IF EXISTS `thanhTienHoaDon` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `thanhTienHoaDon`(MaTiec1 int)
BEGIN
	UPDATE hoadon
	SET ThanhTien =  ifnull((SELECT SUM(DonGia) FROM dichvu d, datdichvu a WHERE d.MaDV = a.MaDV AND a.MaTiec = MaTiec1), 0) 
	+ ifnull((SELECT SUM(ThanhTien) FROM datmonan dm WHERE dm.MaTiec = MaTiec1), 0) + ifnull((SELECT DonGia FROM Sanh Where MaSanh = (SELECT MaSanh FROM dattiec WHERE MaTiec = MaTiec1)), 0)
	WHERE MaTiec = MaTiec1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_SoLuongNV` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_SoLuongNV`()
BEGIN
	DECLARE finished INTEGER DEFAULT 0;
    declare maBP1 int;
    declare SoLuong int;
	DECLARE cur CURSOR
	FOR(SELECT MaBP, COUNT(MANV)
		FROM nhanvien
		GROUP BY MaBP);
	DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;
	
	OPEN cur;
    read_loop: LOOP
		FETCH  cur into maBP1, SoLuong;
		IF finished = 1 THEN
		  LEAVE read_loop;
		END IF;
		UPDATE bophan
		SET SoLuongNV = SoLuong
		WHERE MaBP = maBP1;
	end loop;
	CLOSE cur;
    SET SQL_SAFE_UPDATES = 0;
    update bophan
	SET SoLuongNV = 0
	WHERE MaBP not in (SELECT distinct MaBP FROM nhanvien);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-16 19:08:35
