CREATE DATABASE  IF NOT EXISTS `proyecto` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyecto`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `empresas`
--

DROP TABLE IF EXISTS `empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `identificacionFiscal` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresas`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `id` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retos`
--

DROP TABLE IF EXISTS `retos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `imagenes` varchar(45) DEFAULT NULL,
  `videos` varchar(45) DEFAULT NULL,
  `tecnologias` varchar(45) DEFAULT NULL,
  `id_creador` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_retos_empresas1_idx` (`id_creador`),
  CONSTRAINT `fk_retos_empresas1` FOREIGN KEY (`id_creador`) REFERENCES `empresas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retos`
--

LOCK TABLES `retos` WRITE;
/*!40000 ALTER TABLE `retos` DISABLE KEYS */;
/*!40000 ALTER TABLE `retos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'jcanals','123','Josep','Canals Barcelo','47853810X','jcanalsb98@gmail.com','636577185');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_has_grupos`
--

DROP TABLE IF EXISTS `usuarios_has_grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_has_grupos` (
  `usuarios_id` int NOT NULL,
  `grupos_id` int NOT NULL,
  PRIMARY KEY (`usuarios_id`,`grupos_id`),
  KEY `fk_usuarios_has_grupos_grupos1_idx` (`grupos_id`),
  KEY `fk_usuarios_has_grupos_usuarios_idx` (`usuarios_id`),
  CONSTRAINT `fk_usuarios_has_grupos_grupos1` FOREIGN KEY (`grupos_id`) REFERENCES `grupos` (`id`),
  CONSTRAINT `fk_usuarios_has_grupos_usuarios` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_has_grupos`
--

LOCK TABLES `usuarios_has_grupos` WRITE;
/*!40000 ALTER TABLE `usuarios_has_grupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_has_grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_has_retos`
--

DROP TABLE IF EXISTS `usuarios_has_retos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_has_retos` (
  `usuarios_id` int NOT NULL,
  `retos_id` int NOT NULL,
  `puntuacion` int DEFAULT NULL,
  PRIMARY KEY (`usuarios_id`,`retos_id`),
  KEY `fk_usuarios_has_retos_retos1_idx` (`retos_id`),
  KEY `fk_usuarios_has_retos_usuarios1_idx` (`usuarios_id`),
  CONSTRAINT `fk_usuarios_has_retos_retos1` FOREIGN KEY (`retos_id`) REFERENCES `retos` (`id`),
  CONSTRAINT `fk_usuarios_has_retos_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_has_retos`
--

LOCK TABLES `usuarios_has_retos` WRITE;
/*!40000 ALTER TABLE `usuarios_has_retos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_has_retos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-01 16:11:08
