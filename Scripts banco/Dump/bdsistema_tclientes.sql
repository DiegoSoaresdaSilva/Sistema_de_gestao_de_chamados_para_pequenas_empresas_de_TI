CREATE DATABASE  IF NOT EXISTS `bdsistema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bdsistema`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bdsistema
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
-- Table structure for table `tclientes`
--

DROP TABLE IF EXISTS `tclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tclientes` (
  `idclientes` int NOT NULL AUTO_INCREMENT,
  `nomecliente` varchar(100) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `telefone` varchar(50) NOT NULL,
  PRIMARY KEY (`idclientes`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tclientes`
--

LOCK TABLES `tclientes` WRITE;
/*!40000 ALTER TABLE `tclientes` DISABLE KEYS */;
INSERT INTO `tclientes` VALUES (1,'Contabilidade ABC','teste@teste','Rua ABC - Nº 200','Bairro bloco A','Guaíba','RS','9999-9999'),(4,'Ana da Silva','Ana@teste','Rua Ziraldo, Nº 1','Centro','Guaíba','RS','9999-9999'),(32,'Adão Silva','','','','Guaíba','RS','4444-4444'),(33,'Agropecuária ABC','agropecuaria@teste','Rua Vxxyz,  nº1000','Bairro do teste','Guaíba','RS','8888-8888'),(34,'Franz Kafka','Franz@hotmail.com','Rua Metamorfose, nº 1910','Austria','Guaíba','RS','9999-9999'),(51,'Bento Silva','','','','','RS','9999-9999'),(53,'Maria da silva','maria@maria','Rua ABCD, Nº 1000','Bairro do teste','Guaíba','RS','9999-9999'),(54,'Escritório Y','escritório@teste','Rua A, Nº 99','bairro do tcc','Guaíba','RS','8888-8888'),(55,'Escritório A','Escritório@teste','Rua ABCDEFG, Nº 1000','Bairro do teste','Guaíba','RS','9999-9999'),(57,'joão ','joão123@ bol','av Moura Jardin','Lisboa','Guaíba','RS','95164823');
/*!40000 ALTER TABLE `tclientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-16  0:11:53
