CREATE DATABASE  IF NOT EXISTS `managermentreservation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `managermentreservation`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: managermentreservation
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `room_type_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl44l9icbif5subwsuovjg8tt0` (`room_type_id`),
  CONSTRAINT `FKl44l9icbif5subwsuovjg8tt0` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
INSERT INTO `asset` VALUES ('7ce4166a-aa53-4e77-9aec-2b159e474f4f','Giường','Giường lớn cao cấp loại A',NULL,NULL,'1baf540e-6e06-4243-874b-e960e1cfa7bc'),('a576dcdc-638f-467d-a75e-ae4befa14adc',NULL,'2 điều hòa',NULL,NULL,'1baf540e-6e06-4243-874b-e960e1cfa7bc'),('c005562f-d64a-47f3-b707-58c90fcaa596',NULL,'3 tủ đồ',NULL,NULL,'1baf540e-6e06-4243-874b-e960e1cfa7bc');
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `number_of_room` int NOT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `manager_phone` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `hotel_chk_1` CHECK ((`number_of_room` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES ('118306ef-758f-4c6a-9602-7c7c06990625','R&R','244 Kim mã, Ba Đình, Hà Nội',25,'Nguyễn Kim Sao','0335848634','2025-07-21','2025-07-21'),('27641b2e-86cf-477e-81b1-dd562762c573','Hùng Phương Apartment','89 Nguyễn Chí Thanh',80,'Văn Danh Phúc','978514878','2025-07-21','2025-07-21'),('41c4ec84-4ab4-4df8-af68-81ba8193544a','Linh Anh Hotel','82 Kim Mã',15,'Nguyễn Phương Thảo','0985645322','2025-07-21','2025-07-21'),('4c7b86cf-385e-41b5-bf7b-46197c5d9f0f','Hạ Long Apartment','90 Trần Phú',120,'Nguyễn Kim Cương','383822331','2025-07-21','2025-07-21'),('4fa3d57d-d05a-4642-920d-e42f9ae7cf25','B2B','45 Trân Thái Tông',40,'Nguyễn Nhật Hải','0987246351','2025-07-21','2025-07-21'),('9c7ff6a6-30f3-4cd6-ae0a-b62b68c81e16','Metro hotel','25 Cát Linh, Đống Đa, Hà Nội',35,'Phạm Văn Công','0911155801',NULL,NULL),('a8bf578b-adb9-4c7c-b58d-368818698097','Kinh Công Tower','255 Đ. Nguyễn TrãiThượng Đình, Thanh Xuân, Hà Nội',40,'Nịnh Văn Nam','0976553575',NULL,'2025-07-22'),('ad5c5f87-19af-46d1-90c7-822c35c070ce','ABC Holding','44 Hoàng Quốc Việt',40,'Nguyễn Công Anh','0956545131','2025-07-25','2025-07-25');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `room_id` varchar(255) NOT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `reservation_time` date DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'PENDING',
  `notes` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_reservation_user_room_time` (`user_id`,`room_id`,`start_time`,`end_time`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE,
  CONSTRAINT `reservation_chk_1` CHECK ((`end_time` > `start_time`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('003f5754-71cf-4ae2-b4d5-7259271a0cfe','1b7d6c45-759c-423a-85b1-da07b5b48be7','d37a5a78-4b37-4e63-9f07-875b6be3a299','2025-07-24','2025-07-26','2025-07-23','Pending','toi muôn thue 2 ngày 2 đêm','2025-07-23','2025-07-23','Thanh Kim _0383822331_2025-07-23'),('066196f9-2ce2-48d9-b3b7-7cfa313b3e4e','d186d252-a00a-416c-8545-29a21460bbf5','8a83bcc5-1c0f-40d0-9184-6352b0c3e754','2025-07-26','2025-07-28','2025-07-23','Pending','dsfsad','2025-07-23','2025-07-23','Văn Đức_0978519056_2025-07-23'),('22981c82-97af-480a-b6d4-a9df427a76db','403e0d6a-8492-46fb-a217-46e662aa8ea2','07363226-2d2a-45a9-a6e7-0f482180fb15','2025-07-25','2025-07-26','2025-07-23','Pending','toi muôn thue 2 ngày 2 đêm','2025-07-23','2025-07-23','Thanh Lệ_0383822341_2025-07-23'),('538968a2-2443-452f-aae7-901a98148baa','bc86d46c-ba7a-4a42-85e4-4f2856094af0','07363226-2d2a-45a9-a6e7-0f482180fb15','2025-07-09','2025-07-20','2025-07-23','Pending','dsfsad','2025-07-23','2025-07-23','Phương Lan _0988519056_2025-07-23'),('b2f99769-76b1-4897-bfd4-a05832e2cca9','73a8882f-063f-4547-904e-83cf1b3218cc','d37a5a78-4b37-4e63-9f07-875b6be3a299','2025-07-23','2025-07-24','2025-07-23','Pending','toi muon qua dem','2025-07-23','2025-07-23','Văn Công_0988519156_2025-07-23'),('b3ee240a-c14f-4b39-b026-11ce8b95c720','ea95aa54-1dc0-402f-824f-e67bc9a320fa','068c6f28-9ae5-43ac-8a6f-93fb6f1d5fbe','2025-07-26','2025-07-28','2025-07-25','Pending','Tôi muốn thuê phòng cả 2 ngày','2025-07-25','2025-07-25','Hoàng Anh_09530441_2025-07-25'),('ea78133f-1102-4ef1-937b-4258eeb93482','bc86d46c-ba7a-4a42-85e4-4f2856094af0','8a83bcc5-1c0f-40d0-9184-6352b0c3e754','2025-07-19','2025-07-20','2025-07-23','Checked_In','toi muon qua dem','2025-07-23','2025-07-23','Phương Lan_0988519056_2025-07-23');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `hotel_id` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `room_type_id` varchar(255) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_room_hotel_name` (`hotel_id`,`name`),
  KEY `room_type_id` (`room_type_id`),
  CONSTRAINT `room_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`) ON DELETE CASCADE,
  CONSTRAINT `room_ibfk_2` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `room_chk_1` CHECK ((`price` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('068c6f28-9ae5-43ac-8a6f-93fb6f1d5fbe','Phòng 201','118306ef-758f-4c6a-9602-7c7c06990625','available',2000000,'1baf540e-6e06-4243-874b-e960e1cfa7bc','2025-07-25','2025-07-25'),('07363226-2d2a-45a9-a6e7-0f482180fb15','101','118306ef-758f-4c6a-9602-7c7c06990625','available',500000,'1',NULL,'2025-07-22'),('8a83bcc5-1c0f-40d0-9184-6352b0c3e754','103','118306ef-758f-4c6a-9602-7c7c06990625','Unavailable',1200000,'3',NULL,'2025-07-22'),('d37a5a78-4b37-4e63-9f07-875b6be3a299','102','118306ef-758f-4c6a-9602-7c7c06990625','available',800000,'2','2025-07-22','2025-07-22');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_type` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `hotel_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK8sgnny12n0v74j6u7u94w7mxp` (`hotel_id`),
  CONSTRAINT `FK8sgnny12n0v74j6u7u94w7mxp` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES ('1','BT','binh thuong','2025-07-21','2025-07-21','118306ef-758f-4c6a-9602-7c7c06990625'),('1baf540e-6e06-4243-874b-e960e1cfa7bc','Phòng cho chủ tịch','Phòng chất lượng tốt nhất khách sạn',NULL,NULL,'118306ef-758f-4c6a-9602-7c7c06990625'),('2','CC','cao cap','2025-07-21','2025-07-21','118306ef-758f-4c6a-9602-7c7c06990625'),('3','VIP','vip','2025-07-21','2025-07-21','118306ef-758f-4c6a-9602-7c7c06990625'),('5ea7cdf0-3db7-4ad9-a19a-a44f1e0cbab6','Phòng B','Loại cao cấp','2025-07-25','2025-07-25','ad5c5f87-19af-46d1-90c7-822c35c070ce'),('9f7b3777-4228-45a9-bb74-7123eb15613e','Phòng A','Loại thường','2025-07-25','2025-07-25','ad5c5f87-19af-46d1-90c7-822c35c070ce'),('d40decf1-5b61-4c59-86f2-9b553baf9aeb','Phòng D','Loại đôi cao câp','2025-07-25','2025-07-25','ad5c5f87-19af-46d1-90c7-822c35c070ce'),('f5c72193-3b5e-433e-ba25-f729e9eb04b0','Phòng C','Loại đôi thường','2025-07-25','2025-07-25','ad5c5f87-19af-46d1-90c7-822c35c070ce');
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1b7d6c45-759c-423a-85b1-da07b5b48be7','Thanh Kim ',NULL,'0383822331',NULL,'2025-07-23','2025-07-23',NULL),('403e0d6a-8492-46fb-a217-46e662aa8ea2','Thanh Lệ',NULL,'0383822341',NULL,'2025-07-23','2025-07-23',NULL),('45b08e9a-18a2-41e7-9793-001fca680219','Phạm Thị Hà','2003-04-02','0338764635','phamHa2003@gmail.com',NULL,NULL,'135 đội cấn, ba đình, hà nội'),('4687ba7a-b81d-410b-94d6-e78128a44090','Trần Quang Khải',NULL,'35454654656','tqkhai@gmail.com','2025-07-21','2025-07-21','e rgewrg'),('511eeae6-0277-426d-8c49-99c303079c42','Nguyễn Thu Phương ',NULL,'3515615165','ntphuong@gmail,com','2025-07-21','2025-07-21','grewgrư egre'),('65350e15-9952-48c9-9c48-f0088e8bb25c','Phạm Hải Anh',NULL,'31654165','phanh@gmail.com','2025-07-21','2025-07-21','ẻwgregre e'),('71994b1d-5cc2-4eeb-a442-4cbff3f9394d','admin','2000-06-13','0397680603','trannguyen1306xxx@gmail.com',NULL,NULL,'ngo 192 kim ma'),('73a8882f-063f-4547-904e-83cf1b3218cc','Văn Công',NULL,'0988519156',NULL,'2025-07-23','2025-07-23',NULL),('767bc554-f0f3-4b76-9c7d-3be506e46aba','Nguyen Van B',NULL,'555112315','nba@gmail.com','2025-07-21','2025-07-21','fdsavrht'),('b2ab37f4-0937-4f74-b0d5-577d76d7c8f5','Nguyễn Anh Tuấn - 0334984445 - 07363226-2d2a-45a9-a6e7-0f482180fb15',NULL,'0334984445',NULL,'2025-07-23','2025-07-23',NULL),('bc86d46c-ba7a-4a42-85e4-4f2856094af0','Phương Lan',NULL,'0988519056',NULL,'2025-07-23','2025-07-23',NULL),('d186d252-a00a-416c-8545-29a21460bbf5','Văn Đức',NULL,'0978519056',NULL,'2025-07-23','2025-07-23',NULL),('ea95aa54-1dc0-402f-824f-e67bc9a320fa','Hoàng Anh',NULL,'09530441',NULL,'2025-07-25','2025-07-25',NULL),('fd06cb2e-157e-4e2f-90ea-34fdcc87fe5a','admin1','2000-06-13','0397655603','trannguyen136@gmail.com','2025-07-23','2025-07-23','ngo 192 kim ma');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-25 16:43:36
