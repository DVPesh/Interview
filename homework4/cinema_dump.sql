CREATE DATABASE  IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cinema`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `films`
--

DROP TABLE IF EXISTS `films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `films` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `duration` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='фильмы';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES (1,'Тайна Коко','01:30:00'),(2,'Назад в будущее','02:00:00'),(3,'Титаник','03:00:00'),(4,'Гарри Поттер и Тайная комната','02:00:00'),(5,'Один дома','01:00:00');
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `film_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_film_id_idx` (`film_id`),
  CONSTRAINT `fk_film_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='расписание фильмов';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2023-02-01 05:00:00',3),(2,'2023-02-01 08:10:00',1),(3,'2023-02-01 09:50:00',2),(4,'2023-02-01 11:30:00',5),(5,'2023-02-01 12:40:00',4),(6,'2023-02-01 14:50:00',3),(7,'2023-02-01 17:40:00',1),(8,'2023-02-01 19:50:00',2),(9,'2023-02-01 22:00:00',5),(10,'2023-02-01 23:40:00',4),(11,'2023-02-02 01:50:00',3),(12,'2023-02-02 03:50:00',1),(13,'2023-02-02 05:55:00',2),(14,'2023-02-02 07:50:00',4),(15,'2023-02-02 10:35:00',5);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `schedule_id` int unsigned NOT NULL,
  `seat` int unsigned NOT NULL,
  `price` decimal(7,2) unsigned NOT NULL,
  `taken` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_seat_at_session` (`schedule_id`,`seat`),
  KEY `fk_schedule_id_idx` (`schedule_id`),
  CONSTRAINT `fk_schedule_id` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='билеты';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (16,1,1,1000.00,0),(17,1,2,1050.00,1),(18,1,3,1100.00,1),(19,1,4,1050.00,0),(20,1,5,1000.00,0),(21,2,1,500.00,0),(22,2,2,550.00,1),(23,2,3,600.00,1),(24,2,4,550.00,0),(25,2,5,500.00,1),(26,3,1,700.00,1),(27,3,2,750.00,1),(28,3,3,800.00,1),(29,3,4,750.00,1),(30,3,5,700.00,1),(31,4,1,300.00,0),(32,4,2,350.00,1),(33,4,3,400.00,1),(34,4,4,350.00,0),(35,4,5,300.00,1),(36,5,1,900.00,0),(37,5,2,950.00,1),(38,5,3,1000.00,1),(39,5,4,950.00,1),(40,5,5,900.00,1),(41,6,1,1400.00,1),(42,6,2,1450.00,0),(43,6,3,1500.00,1),(44,6,4,1450.00,1),(45,6,5,1400.00,1),(46,7,1,700.00,1),(47,7,2,750.00,1),(48,7,3,800.00,1),(49,7,4,750.00,0),(50,7,5,700.00,1),(51,8,1,900.00,1),(52,8,2,950.00,1),(53,8,3,1000.00,1),(54,8,4,950.00,0),(55,8,5,900.00,1),(56,9,1,800.00,1),(57,9,2,850.00,1),(58,9,3,900.00,1),(59,9,4,850.00,1),(60,9,5,800.00,1),(61,10,1,850.00,0),(62,10,2,900.00,1),(63,10,3,950.00,1),(64,10,4,900.00,0),(65,10,5,850.00,1),(66,11,1,950.00,1),(67,11,2,1000.00,1),(68,11,3,1100.00,0),(69,11,4,1000.00,1),(70,11,5,950.00,0),(71,12,1,450.00,1),(72,12,2,500.00,0),(73,12,3,550.00,1),(74,12,4,500.00,0),(75,12,5,450.00,0),(76,13,1,500.00,0),(77,13,2,550.00,0),(78,13,3,600.00,0),(79,13,4,550.00,0),(80,13,5,500.00,0),(81,14,1,750.00,0),(82,14,2,800.00,0),(83,14,3,850.00,0),(84,14,4,800.00,0),(85,14,5,750.00,0),(86,15,1,450.00,0),(87,15,2,500.00,0),(88,15,3,550.00,0),(89,15,4,500.00,0),(90,15,5,450.00,0);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-31 23:52:38
