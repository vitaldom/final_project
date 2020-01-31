-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `second_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('CLIENT','INSPECTOR') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Petrov','Ivan','ivan','test','CLIENT'),(17,'Snow','John','john','1234','CLIENT'),(18,'Scary','Inspector','inspector','1234','INSPECTOR'),(19,'Бородова','Наталія','boroda','1234','CLIENT'),(20,'Pupkin','Vasia','vasia','1234','CLIENT'),(21,'Кононученко','Анатолій','anar','1234','CLIENT');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `declarations`
--

DROP TABLE IF EXISTS `declarations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `declarations` (
  `id` int(11) NOT NULL,
  `author_login` varchar(45) NOT NULL,
  `inspector_login` varchar(45) DEFAULT NULL,
  `declaration_year` varchar(45) NOT NULL,
  `tax_category` varchar(45) NOT NULL,
  `income` bigint(20) NOT NULL,
  `tax_sum_declared` bigint(20) NOT NULL,
  `status` varchar(45) NOT NULL,
  `correction_message` varchar(700) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `author_login_idx` (`author_login`),
  KEY `inspector_login_idx` (`inspector_login`),
  CONSTRAINT `author_login` FOREIGN KEY (`author_login`) REFERENCES `clients` (`login`),
  CONSTRAINT `inspector_login` FOREIGN KEY (`inspector_login`) REFERENCES `inspectors` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `declarations`
--

LOCK TABLES `declarations` WRITE;
/*!40000 ALTER TABLE `declarations` DISABLE KEYS */;
INSERT INTO `declarations` VALUES (1,'john','ivan','2019','PREFERENTIAL',10000,1000,'SUBMITTED',NULL),(2,'boroda','ivan','2019','ENTREPRENEUR',300000,20000,'SUBMITTED',NULL),(3,'boroda','ivan','2019','PREFERENTIAL',10000,10,'SUBMITTED',NULL),(4,'john','ivan','2017','PREFERENTIAL',38888888,4224,'SUBMITTED',NULL),(5,'boroda','ivan','2019','EMPLOYEE',324242342,2342344,'SUBMITTED',NULL),(6,'boroda','ivan','2020','PREFERENTIAL',43424,234242432,'SUBMITTED',NULL);
/*!40000 ALTER TABLE `declarations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspectors`
--

DROP TABLE IF EXISTS `inspectors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspectors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `second_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `reports_assigned` int(10) unsigned NOT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspectors`
--

LOCK TABLES `inspectors` WRITE;
/*!40000 ALTER TABLE `inspectors` DISABLE KEYS */;
INSERT INTO `inspectors` VALUES (2,'Ivanov','Ivan','ivan','1234',50),(1,'Petrov','Petro','petr','1234',22),(3,'Sidorov','Sidor','sidr','1234',13);
/*!40000 ALTER TABLE `inspectors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-31 22:02:23
