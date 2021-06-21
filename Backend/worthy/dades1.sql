CREATE DATABASE  IF NOT EXISTS `db_worthy` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_worthy`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: db_worthy
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
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comentario` varchar(150) DEFAULT NULL,
  `posts_id` int NOT NULL,
  `usuarios_id` int DEFAULT NULL,
  `empresas_id` int DEFAULT NULL,
  `multimedia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comentarios_posts1_idx` (`posts_id`),
  KEY `fk_comentarios_usuarios1_idx` (`usuarios_id`),
  KEY `fk_comentarios_empresas1_idx` (`empresas_id`),
  CONSTRAINT `fk_comentarios_empresas1` FOREIGN KEY (`empresas_id`) REFERENCES `empresas` (`id`),
  CONSTRAINT `fk_comentarios_posts1` FOREIGN KEY (`posts_id`) REFERENCES `posts` (`id`),
  CONSTRAINT `fk_comentarios_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (15,'Conusta en el siguiente link https://flaviocopes.com/axios-urlencoded/',15,1,NULL,NULL);
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresas`
--

DROP TABLE IF EXISTS `empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `identificacionFiscal` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `ubicacion` varchar(45) DEFAULT NULL,
  `latlng` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresas`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
INSERT INTO `empresas` VALUES (1,'Amazon','amazon@amazon.com','123','123','Somos la empresa mas gradne del mundo','EEUU',NULL),(2,'SpaceX','Spacex@marte.com','1234','12324','Vamos para la luna','EEUU',''),(3,'Enfocat','enfocat@gmail.com','123','123','Proporcionamos nuevas oportunidades a jovenes','Mataro',''),(5,'aaaaaaaaaaaaaaaa','mama@noeal.comtt','btc','marte','nananannananannn','adfasdasdasdasdasd','1231,123'),(6,'SpaceX','Spacex@marte.com','123','12324','aaa','marte','1231,123');
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `usuarios_id` int DEFAULT NULL,
  `empresas_id` int DEFAULT NULL,
  `multimedia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_posts_usuarios1_idx` (`usuarios_id`),
  KEY `fk_posts_empresas1_idx` (`empresas_id`),
  CONSTRAINT `fk_posts_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `pk_posts_empresas1` FOREIGN KEY (`empresas_id`) REFERENCES `empresas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (15,'Error CORS API','It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.',1,NULL,NULL),(16,'Requerimientos minmos emrpesa','It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.',NULL,1,NULL),(20,'test43534','olaolaola3434343',1,NULL,'multi usuari'),(21,'test43534','olaolaola3434343',NULL,1,'multi empresa'),(22,'test43534','olaolaola3434343',1,NULL,NULL),(23,'test43534','olaolaola3434343',1,NULL,NULL),(24,'aa','uuuuuuuu',NULL,2,''),(25,'aa','bbbsdsdsd',NULL,1,'ccc');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retos`
--

DROP TABLE IF EXISTS `retos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_creador` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(2000) DEFAULT NULL,
  `tecnologias` varchar(45) DEFAULT NULL,
  `participantesMax` int DEFAULT NULL,
  `participantes` int DEFAULT NULL,
  `multimedia` varchar(45) DEFAULT NULL,
  `nivel` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_retos_empresas1_idx` (`id_creador`),
  CONSTRAINT `fk_retos_empresas1` FOREIGN KEY (`id_creador`) REFERENCES `empresas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retos`
--

LOCK TABLES `retos` WRITE;
/*!40000 ALTER TABLE `retos` DISABLE KEYS */;
INSERT INTO `retos` VALUES (13,1,'Dados de rol','It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).','C++',20,1,NULL,2),(14,2,'Creacion de un objeto','It is a long established fact ','C++',2,0,'',1),(15,1,'Creacion usuario','It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.','Phyton',10,1,NULL,2),(16,1,'Palabras pentavocalicas','It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).','Java',20,1,NULL,2);
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
  `ubicacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'jcanals','123','Josep','Canals','47853810X','jcanals@gmail.com','666\r','Centelles'),(2,'evilla','123','Edu','Villa','64785930J','evilla@gmail.com','666\r','Mataro'),(4,'cvalerio','123','Carlos','Valerio','46371948L','cvalerio@gmail.com','666','Londres'),(7,'pgrillo','123','Pedro','Grillo','64739356M','pgrillo@gmail.com','666','Barcelona');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
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
  `multimedia` varchar(45) DEFAULT NULL,
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
INSERT INTO `usuarios_has_retos` VALUES (2,13,NULL),(2,14,NULL),(4,14,'hjkhjkjhkkh'),(4,15,'hjkhjkjhkkh'),(7,13,NULL),(7,14,'abcdef');
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

-- Dump completed on 2021-06-21 14:33:23
