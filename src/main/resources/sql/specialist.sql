-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mc2pd-specialist
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_login` (
  `login_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `password_hash` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`login_id`),
  KEY `fk_user_login_idx` (`user_id`),
  CONSTRAINT `fk_user_login` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_visions`
--

DROP TABLE IF EXISTS `user_visions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_visions` (
  `user_id` int(11) NOT NULL,
  `vision_comparison_id` int(11) NOT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`user_id`,`vision_comparison_id`),
  KEY `fk_id_user_tb_user_vision_pref_idx` (`user_id`),
  CONSTRAINT `fk_id_user_tb_user_vision_pref` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_visions`
--

LOCK TABLES `user_visions` WRITE;
/*!40000 ALTER TABLE `user_visions` DISABLE KEYS */;
INSERT INTO `user_visions` VALUES (1,1,3),(1,2,4),(1,3,5),(1,4,2),(1,5,0.25),(1,6,3),(1,7,4),(1,8,2),(1,9,1.66),(1,10,0.5),(1,11,0.2),(1,12,0.142),(1,13,0.2),(1,14,0.125),(1,15,0.2);
/*!40000 ALTER TABLE `user_visions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `dt_register` datetime DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `job_title` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Exemplo_Analista','01_E01','2019-08-27 17:55:23','Empresa_01','Analista de Sistemas'),(2,'Exemplo_Analista','02_E01','2019-08-27 17:55:23','Empresa_01','Analista de Sistemas'),(3,'Exemplo_Arquiteto','01_E01','2019-08-27 17:55:23','Empresa_01','Arquiteto de Software'),(4,'Exemplo_Arquiteto','02_E01','2019-08-27 17:55:23','Empresa_01','Arquiteto de Software'),(5,'Exemplo_Gerente','01_E01','2019-08-27 17:55:23','Empresa_01','Gerente de Projeto'),(6,'Exemplo_Gerente','02_E01','2019-08-27 17:55:23','Empresa_01','Gerente de Projeto'),(7,'Exemplo_Analista','01_E02','2019-08-27 17:55:23','Empresa_02','Analista de Sistemas'),(8,'Exemplo_Analista','02_E02','2019-08-27 17:55:23','Empresa_02','Analista de Sistemas'),(9,'Exemplo_Arquiteto','01_E02','2019-08-27 17:55:23','Empresa_02','Arquiteto de Software'),(10,'Exemplo_Arquiteto','02_E02','2019-08-27 17:55:23','Empresa_02','Arquiteto de Software'),(11,'Exemplo_Gerente','01_E02','2019-08-27 17:55:23','Empresa_02','Gerente de Projeto'),(12,'Exemplo_Gerente','02_E02','2019-08-27 17:55:23','Empresa_02','Gerente de Projeto');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vision_comparison`
--

DROP TABLE IF EXISTS `vision_comparison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vision_comparison` (
  `id` int(11) NOT NULL,
  `main_vision` int(11) DEFAULT NULL,
  `compared_vision` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vision_comparison`
--

LOCK TABLES `vision_comparison` WRITE;
/*!40000 ALTER TABLE `vision_comparison` DISABLE KEYS */;
INSERT INTO `vision_comparison` VALUES (1,1,2),(2,1,3),(3,1,4),(4,1,5),(5,1,6),(6,2,3),(7,2,4),(8,2,5),(9,2,6),(10,3,4),(11,3,5),(12,3,6),(13,4,5),(14,4,6),(15,5,6);
/*!40000 ALTER TABLE `vision_comparison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visions`
--

DROP TABLE IF EXISTS `visions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `visions` (
  `vision_id` int(11) NOT NULL,
  `vision_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`vision_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visions`
--

LOCK TABLES `visions` WRITE;
/*!40000 ALTER TABLE `visions` DISABLE KEYS */;
INSERT INTO `visions` VALUES (1,'Dependência de outros serviços'),(2,'Tempo de Resposta'),(3,'Cobertura de Testes Unitários'),(4,'Débito Técnico'),(5,'Consumo de Recursos Computacionais'),(6,'Disponibilidade');
/*!40000 ALTER TABLE `visions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mc2pd-specialist'
--

--
-- Dumping routines for database 'mc2pd-specialist'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-13 11:24:07
