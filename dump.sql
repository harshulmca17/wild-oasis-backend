-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: production
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cabin_id` int DEFAULT NULL,
  `cabin_price` float DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `extras_price` float DEFAULT NULL,
  `guest_id` int DEFAULT NULL,
  `has_breakfast` bit(1) DEFAULT NULL,
  `is_paid` bit(1) DEFAULT NULL,
  `num_guests` int DEFAULT NULL,
  `num_nights` int DEFAULT NULL,
  `observations` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (6,1,160,'2024-04-06 16:53:30.849851','2024-04-24 05:30:00.000000',41,4,_binary '\0',_binary '',2,6,'','2024-04-18 05:30:00.000000','checked-out',201),(10,3,323,'2024-04-06 16:53:30.850318','2024-03-17 05:30:00.000000',215,8,_binary '',_binary '',4,5,'','2024-03-12 05:30:00.000000','checked-out',538),(11,8,379,'2024-04-06 16:53:30.896002','2024-04-06 05:30:00.000000',353,1,_binary '',_binary '',9,5,'My wife has a gluten allergy so I would like to request a gluten-free breakfast if possible','2024-04-01 05:30:00.000000','checked-out',732),(13,3,593,'2024-04-06 16:53:30.896276','2024-03-26 05:30:00.000000',540,10,_binary '',_binary '',4,3,'','2024-03-23 05:30:00.000000','checked-in',1133),(14,4,262,'2024-04-06 16:53:30.895999','2024-04-14 05:30:00.000000',133,11,_binary '',_binary '',4,12,'','2024-04-02 05:30:00.000000','checked-out',395),(15,1,87,'2024-04-06 16:53:30.895999','2024-04-13 05:30:00.000000',82,2,_binary '',_binary '',1,7,'I have a gluten allergy and would like to request a gluten-free breakfast.','2024-04-06 05:30:00.000000','checked-out',169),(16,2,343,'2024-04-06 16:53:30.898995','2024-05-24 05:30:00.000000',314,7,_binary '',_binary '',2,15,'','2024-05-09 05:30:00.000000','checked-out',657),(17,2,99,'2024-04-06 16:53:30.899216','2024-03-08 05:30:00.000000',74,5,_binary '\0',_binary '',2,16,'','2024-02-21 05:30:00.000000','checked-out',173),(18,4,656,'2024-04-06 16:53:30.899652','2024-04-23 05:30:00.000000',595,12,_binary '',_binary '',4,5,'','2024-04-18 05:30:00.000000','checked-in',1251),(19,7,531,'2024-04-06 16:53:30.899004','2024-05-26 05:30:00.000000',207,21,_binary '',_binary '',7,10,'','2024-05-16 05:30:00.000000','checked-in',738),(20,2,29,'2024-04-06 16:53:30.898990','2024-04-24 05:30:00.000000',7,6,_binary '',_binary '',2,3,'','2024-04-21 05:30:00.000000','checked-out',36),(21,5,710,'2024-04-06 16:53:30.923029','2024-04-05 05:30:00.000000',259,16,_binary '\0',_binary '\0',6,3,'','2024-04-02 05:30:00.000000','unconfirmed',969),(22,3,972,'2024-04-06 16:53:30.932053','2024-04-06 05:30:00.000000',90,9,_binary '',_binary '',3,2,'We will be bringing our small dog with us','2024-04-04 05:30:00.000000','checked-in',2021),(23,5,76,'2024-04-06 16:53:30.932044','2024-04-27 05:30:00.000000',65,14,_binary '',_binary '',5,7,'','2024-04-20 05:30:00.000000','checked-out',141),(24,7,681,'2024-04-06 16:53:30.932049','2024-05-13 05:30:00.000000',371,22,_binary '',_binary '\0',6,5,'','2024-05-08 05:30:00.000000','unconfirmed',1052);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cabin`
--

DROP TABLE IF EXISTS `cabin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `max_capacity` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `regular_price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabin`
--

LOCK TABLES `cabin` WRITE;
/*!40000 ALTER TABLE `cabin` DISABLE KEYS */;
INSERT INTO `cabin` VALUES (1,'2024-04-06 16:53:24.703000','Experience luxury family living in our medium-sized wooden cabin 003. Perfect for families of up to 4 people, this cabin offers a comfortable and inviting space with all modern amenities. Inside, you will find warm and inviting interiors crafted from high-quality wood, a comfortable living area, a fireplace, and a fully-equipped kitchen. The bedrooms feature plush beds and spa-like bathrooms. The cabin has a private deck with a hot tub and outdoor seating area, perfect for taking in the natural surroundings.',40,'/storage/v1/object/public/cabin-images/0.7227562219359225-cabin-003.jpg',14,'003',3001),(2,'2024-04-06 16:53:24.703000','Escape to the serenity of nature and indulge in luxury in our cozy cabin 002. Perfect for couples, this cabin offers a secluded and intimate retreat in the heart of a picturesque forest. Inside, you will find warm and inviting interiors crafted from high-quality wood, a comfortable living area, a fireplace and a fully-equipped kitchen. The luxurious bedroom features a plush king-size bed and spa-like shower. Relax on the private deck with hot tub and take in the beauty of nature.',25,'/storage/v1/object/public/cabin-images/0.21990351380709194-cabin-008.jpg',2,'002',350),(3,'2024-04-06 16:53:24.703381','Indulge in the ultimate luxury family vacation in this medium-sized cabin 004. Designed for families of up to 4, this cabin offers a sumptuous retreat for the discerning traveler. Inside, the cabin boasts of opulent interiors crafted from the finest quality wood, a comfortable living area, a fireplace, and a fully-equipped gourmet kitchen. The bedrooms are adorned with plush beds and spa-inspired en-suite bathrooms. Step outside to your private deck and soak in the natural surroundings while relaxing in your own hot tub.',50,'/storage/v1/object/public/cabin-images/cabin-004.jpg',4,'004',500),(4,'2024-04-06 16:53:24.703382','Experience the epitome of luxury with your group or family in our spacious wooden cabin 006. Designed to comfortably accommodate up to 6 people, this cabin offers a lavish retreat in the heart of nature. Inside, the cabin features opulent interiors crafted from premium wood, a grand living area with fireplace, and a fully-equipped gourmet kitchen. The bedrooms are adorned with plush beds and spa-like en-suite bathrooms. Step outside to your private deck and soak in the natural surroundings while relaxing in your own hot tub.',100,'/storage/v1/object/public/cabin-images/cabin-006.jpg',6,'006',800),(5,'2024-04-06 16:53:24.703381','Enjoy a comfortable and cozy getaway with your group or family in our spacious cabin 005. Designed to accommodate up to 6 people, this cabin offers a secluded retreat in the heart of nature. Inside, the cabin features warm and inviting interiors crafted from quality wood, a living area with fireplace, and a fully-equipped kitchen. The bedrooms are comfortable and equipped with en-suite bathrooms. Step outside to your private deck and take in the natural surroundings while relaxing in your own hot tub.',0,'/storage/v1/object/public/cabin-images/cabin-005.jpg',6,'005',350),(6,'2024-05-02 21:40:57.621242','testing',12,'/storage/v1/object/public/cabin-images/0.2978712099766725-cabin-006.jpg',12,'007',2341),(7,'2024-05-02 21:44:31.839734','new cabin 2',12,'/storage/v1/object/public/cabin-images/0.30873417502871403-cabin-008.jpg',3,'008',1234),(8,'2024-05-05 19:32:45.107874','new cabin',12,'/storage/v1/object/public/cabin-images/0.28018245570290934-cabin-008.jpg',123,'009',12342);
/*!40000 ALTER TABLE `cabin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country_flag` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `national` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,'https://flagcdn.com/bo.svg','2024-04-06 16:53:18.203738','anderson@example.com','Jonas Anderson',NULL,'Bolivia (Plurinational State of)'),(2,'https://flagcdn.com/eg.svg','2024-04-06 16:53:18.203735','ahmed@gmail.com','Ahmed Hassan',NULL,'Egypt'),(3,'https://flagcdn.com/us.svg','2024-04-06 16:53:18.203737','jowi@gmail.com','Jonathan Williams',NULL,'United States of America'),(4,'https://flagcdn.com/au.svg','2024-04-06 16:53:18.203734','david@gmail.com','David Smith',NULL,'Australia'),(5,'https://flagcdn.com/br.svg','2024-04-06 16:53:18.203734','gabriel@gmail.com','Gabriel Silva',NULL,'Brazil'),(6,'https://flagcdn.com/gb.svg','2024-04-06 16:53:18.203734','emma@gmail.com','Emma Watson',NULL,'United Kingdom'),(7,'https://flagcdn.com/sd.svg','2024-04-06 16:53:18.203734','khadija@gmail.com','Khadija Ahmed',NULL,'Sudan'),(8,'https://flagcdn.com/ca.svg','2024-04-06 16:53:18.203734','emma@gmail.com','Emma Brown',NULL,'Canada'),(9,'https://flagcdn.com/tw.svg','2024-04-06 16:53:18.203734','mei@gmail.com','Mei Chen',NULL,'Taiwan'),(10,'https://flagcdn.com/kr.svg','2024-04-06 16:53:18.203734','sara@gmail.com','Sara Lee',NULL,'South Korea'),(11,'https://flagcdn.com/eg.svg','2024-04-06 16:53:18.295540','mohammedali@yahoo.com','Mohammed Ali',NULL,'Egypt'),(12,'https://flagcdn.com/za.svg','2024-04-06 16:53:18.295540','nina@hotmail.com','Nina Williams',NULL,'South Africa'),(13,'https://flagcdn.com/ng.svg','2024-04-06 16:53:18.295540','ibrahim@yahoo.com','Ibrahim Ahmed',NULL,'Nigeria'),(14,'https://flagcdn.com/kw.svg','2024-04-06 16:53:18.295540','fatimah@gmail.com','Fatimah Al-Sayed',NULL,'Kuwait'),(15,'https://flagcdn.com/co.svg','2024-04-06 16:53:18.296021','carlos@yahoo.com','Carlos Gomez',NULL,'Colombia'),(16,'https://flagcdn.com/ar.svg','2024-04-06 16:53:18.295540','juan@yahoo.com','Juan Hernandez',NULL,'Argentina'),(17,'https://flagcdn.com/fr.svg','2024-04-06 16:53:18.295540','marie@gmail.com','Marie Dupont',NULL,'France'),(18,'https://flagcdn.com/de.svg','2024-04-06 16:53:18.296309','jonas@example.eu','Jonas Mueller',NULL,'Germany'),(19,'https://flagcdn.com/gb.svg','2024-04-06 16:53:18.295540','johnsmith@test.eu','Jonathan Smith',NULL,'Great Britain'),(20,'https://flagcdn.com/jp.svg','2024-04-06 16:53:18.295683','taro@gmail.com','Taro Tanaka',NULL,'Japan'),(21,'https://flagcdn.com/vn.svg','2024-04-06 16:53:18.321775','julie@gmail.com','Julie Nguyen',NULL,'Vietnam'),(22,'https://flagcdn.com/cn.svg','2024-04-06 16:53:18.330023','li.mei@hotmail.com','Li Mei',NULL,'China'),(23,'https://flagcdn.com/pt.svg','2024-04-06 16:53:18.329600','hello@jonas.io','Jonas Schmedtmann',NULL,'Portugal'),(24,'https://flagcdn.com/us.svg','2024-04-06 16:53:18.329619','johndoe@gmail.com','John Doe',NULL,'United States'),(25,'https://flagcdn.com/pk.svg','2024-04-06 16:53:18.329600','fatima@example.com','Fatima Ahmed',NULL,'Pakistan'),(26,'https://flagcdn.com/fi.svg','2024-04-06 16:53:18.329642','jonatan@example.com','Jonatan Johansson',NULL,'Finland'),(27,'https://flagcdn.com/in.svg','2024-04-06 16:53:18.330453','ramesh@gmail.com','Ramesh Patel',NULL,'India'),(28,'https://flagcdn.com/mx.svg','2024-04-06 16:53:18.329600','maria@example.com','Maria Gomez',NULL,'Mexico'),(29,'https://flagcdn.com/sa.svg','2024-04-06 16:53:18.329818','abdul@gmail.com','Abdul Rahman',NULL,'Saudi Arabia'),(30,'https://flagcdn.com/es.svg','2024-04-06 16:53:18.329600','maria@gmail.com','Maria Rodriguez',NULL,'Spain');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `id` int NOT NULL,
  `break_fast_price` float DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `max_booking_length` int DEFAULT NULL,
  `max_guests_per_booking` int DEFAULT NULL,
  `min_booking_length` int DEFAULT NULL,
  `max_bookin_length` int DEFAULT NULL,
  `min_bookin_length` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,15,NULL,10,24,2,NULL,NULL);
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-19 19:01:45
