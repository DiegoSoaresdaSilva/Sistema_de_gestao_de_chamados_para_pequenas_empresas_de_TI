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
-- Table structure for table `tchamado`
--

DROP TABLE IF EXISTS `tchamado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tchamado` (
  `idchamado` int NOT NULL AUTO_INCREMENT,
  `dataehora` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(15) NOT NULL,
  `chamado` varchar(100) NOT NULL,
  `descricao` longtext NOT NULL,
  `resolucao` longtext,
  `analista` varchar(30) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `idclientes` int NOT NULL,
  PRIMARY KEY (`idchamado`),
  KEY `idclientes` (`idclientes`),
  CONSTRAINT `tchamado_ibfk_1` FOREIGN KEY (`idclientes`) REFERENCES `tclientes` (`idclientes`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tchamado`
--

LOCK TABLES `tchamado` WRITE;
/*!40000 ALTER TABLE `tchamado` DISABLE KEYS */;
INSERT INTO `tchamado` VALUES (1,'2022-09-29 05:49:42','Concluído','Estudo de melhoria','Cliente solicita estudo de melhoria na infraestrutura de Rede','Estudo realizado e enviado para o cliente.','Diego S.S',300.00,1),(13,'2022-10-21 09:16:51','Em atendimento','Computador sem acesso à Internet','Cliente sem acesso à Internet nos computadores da recepção','','Diego S.S',90.00,1),(60,'2022-10-22 01:06:27','Concluído','teste','teste2','teste\n','Diego S.S',90.00,1),(63,'2022-10-22 01:16:05','Em atendimento','teste','teste		','','Diego S.S',0.00,4),(75,'2022-10-27 13:23:01','Em atendimento','Cliente solicita implementação de Firewall','Cliente solicita implementação de Firewall','','Diego S.S',0.00,33),(85,'2022-12-01 21:08:03','Fila','teste','teste','teste','',0.00,32),(86,'2022-12-08 20:25:57','Concluído','Teste','teste','teste','Diego S.S',100.00,4),(87,'2022-12-12 03:35:04','Em atendimento','teste','teste','','Diego S.S',0.00,33),(88,'2022-12-13 21:03:21','Em atendimento','teste','teste','','',0.00,4),(89,'2022-12-15 03:02:42','Em atendimento','teste','teste','teste','Diego S.S',0.00,33);
/*!40000 ALTER TABLE `tchamado` ENABLE KEYS */;
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
