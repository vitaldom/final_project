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
                                CONSTRAINT `author_login` FOREIGN KEY (`author_login`) REFERENCES `users` (`login`),
                                CONSTRAINT `inspector_login` FOREIGN KEY (`inspector_login`) REFERENCES `users` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `declarations`
--

LOCK TABLES `declarations` WRITE;
/*!40000 ALTER TABLE `declarations` DISABLE KEYS */;
INSERT INTO `declarations` VALUES (1,'john','sidr','2020','EMPLOYEE',1000,100,'SUBMITTED',NULL),(2,'john','ivan','2017','PREFERENTIAL',1000000,333,'SUBMITTED','Too high income'),(3,'boroda','ivan','2019','EMPLOYEE',42984824,42424,'APPEALED','Вказані не всі доходи'),(4,'boroda','ivan','2020','ENTREPRENEUR',48248287874327,42424,'APPEALED','Занижена сума податку'),(6,'john','ivan','2020','PREFERENTIAL',300000,2000,'APPROVED',NULL),(7,'boroda','ivan','2015','PREFERENTIAL',29349389,300000,'APPROVED','Занижена сума податку'),(8,'boroda','ivan','2016','PREFERENTIAL',8429874892,42342,'SUBMITTED',NULL),(9,'boroda','ivan','2015','EMPLOYEE',200000,2000,'UNDER_CORRECTION','Невірна сума доходу');
/*!40000 ALTER TABLE `declarations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspector_change_requests`
--

DROP TABLE IF EXISTS `inspector_change_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspector_change_requests` (
                                             `id` int(11) NOT NULL AUTO_INCREMENT,
                                             `declaration_id` int(11) NOT NULL,
                                             `author_login` varchar(45) NOT NULL,
                                             `inspector_login` varchar(45) NOT NULL,
                                             `request_reason` varchar(45) NOT NULL,
                                             PRIMARY KEY (`id`),
                                             KEY `declaration_id_idx` (`declaration_id`),
                                             KEY `author_login_idx` (`author_login`),
                                             KEY `inspector_idx` (`inspector_login`),
                                             CONSTRAINT `author` FOREIGN KEY (`author_login`) REFERENCES `users` (`login`),
                                             CONSTRAINT `declaration_id` FOREIGN KEY (`declaration_id`) REFERENCES `declarations` (`id`),
                                             CONSTRAINT `inspector` FOREIGN KEY (`inspector_login`) REFERENCES `users` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspector_change_requests`
--

LOCK TABLES `inspector_change_requests` WRITE;
/*!40000 ALTER TABLE `inspector_change_requests` DISABLE KEYS */;
INSERT INTO `inspector_change_requests` VALUES (1,3,'boroda','ivan','test reason'),(2,4,'boroda','ivan','Погане обслуговування');
/*!40000 ALTER TABLE `inspector_change_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `second_name` varchar(45) NOT NULL,
                         `first_name` varchar(45) NOT NULL,
                         `login` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         `role` enum('CLIENT','INSPECTOR') NOT NULL,
                         `reports_assigned` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `login_UNIQUE` (`login`),
                         UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (17,'Snow','John','john','1234','CLIENT',NULL),(18,'Ivanov','Ivan','ivan','1234','INSPECTOR',4),(19,'Бородова','Наталія','boroda','1234','CLIENT',NULL),(20,'Pupkin','Vasia','vasia','1234','CLIENT',NULL),(21,'Кононученко','Анатолій','anar','1234','CLIENT',NULL),(22,'Sidorov','Sidr','sidr','1234','INSPECTOR',33),(24,'Petrov','Petr','petr','1234','INSPECTOR',20),(25,'Антонов','Антон','anton','1234','CLIENT',NULL),(26,'Streizand','Barbara','barbara','1234','CLIENT',NULL),(27,'Шиндлер','Адам','adam','1234','CLIENT',NULL),(29,'Монтана','Монт','mont','1234','CLIENT',NULL),(30,'Test','Test','login','1111','CLIENT',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-16 23:05:42
