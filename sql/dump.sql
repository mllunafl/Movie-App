-- MySQL dump 10.13  Distrib 5.7.16, for osx10.11 (x86_64)
--
-- Host: localhost    Database: sandbox
-- ------------------------------------------------------
-- Server version	5.7.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2017-02-17 13:43:05',1,'EXECUTED','7:a91966e379c49b41dd5b60b86be5e082','createTable tableName=jhi_user; createIndex indexName=idx_user_login, tableName=jhi_user; createIndex indexName=idx_user_email, tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableN...','',NULL,'3.5.3',NULL,NULL,'7360578141'),('20170207220703-1','jhipster','classpath:config/liquibase/changelog/20170207220703_added_entity_Movie.xml','2017-02-17 13:43:05',2,'EXECUTED','7:ef6d845fe3a781620bd2bdf00d0caebd','createTable tableName=movie','',NULL,'3.5.3',NULL,NULL,'7360578141'),('20170213161108-1','jhipster','classpath:config/liquibase/changelog/20170213161108_added_entity_Movie_wishlist.xml','2017-02-17 13:43:05',3,'EXECUTED','7:0d2167ca8313103d5ec744a1cd1e1234','createTable tableName=movie_wishlist','',NULL,'3.5.3',NULL,NULL,'7360578141'),('20170216212142-1','jhipster','classpath:config/liquibase/changelog/20170216212142_added_entity_Movie_watchlist.xml','2017-02-17 13:43:05',4,'EXECUTED','7:481a1f114f3adf54d7f4c6910cd7da92','createTable tableName=movie_watchlist','',NULL,'3.5.3',NULL,NULL,'7360578141'),('20170213161108-2','jhipster','classpath:config/liquibase/changelog/20170213161108_added_entity_constraints_Movie_wishlist.xml','2017-02-17 13:43:06',5,'EXECUTED','7:cd853519f4ae457776c111e7aad8fb31','addForeignKeyConstraint baseTableName=movie_wishlist, constraintName=fk_movie_wishlist_user_id, referencedTableName=jhi_user','',NULL,'3.5.3',NULL,NULL,'7360578141'),('20170216212142-2','jhipster','classpath:config/liquibase/changelog/20170216212142_added_entity_constraints_Movie_watchlist.xml','2017-02-17 13:43:06',6,'EXECUTED','7:45a35d6f2d8d5a9fd9228bd492f0b818','addForeignKeyConstraint baseTableName=movie_watchlist, constraintName=fk_movie_watchlist_user_id, referencedTableName=jhi_user','',NULL,'3.5.3',NULL,NULL,'7360578141'),('20170207220703-2','jhipster','classpath:config/liquibase/changelog/20170207220703_added_entity_constraints_Movie.xml','2017-02-17 13:43:07',7,'EXECUTED','7:a4dd8043173ef50ff412620aeb2b698b','addForeignKeyConstraint baseTableName=movie, constraintName=fk_movie_movie_wishlist_id, referencedTableName=movie_wishlist; addForeignKeyConstraint baseTableName=movie, constraintName=fk_movie_movie_watchlist_id, referencedTableName=movie_watchlist','',NULL,'3.5.3',NULL,NULL,'7360578141');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,'','2017-02-17 17:25:19','2605:6000:ee28:ea00:0:0:0:c (2605:6000:ee28:ea00:0:0:0:c)');
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2017-02-17 19:49:58','AUTHENTICATION_SUCCESS'),(2,'user','2017-02-17 20:33:17','AUTHENTICATION_SUCCESS'),(3,'user','2017-02-17 20:35:02','AUTHENTICATION_SUCCESS'),(4,'user','2017-02-17 22:13:31','AUTHENTICATION_SUCCESS'),(5,'user','2017-02-18 00:14:09','AUTHENTICATION_SUCCESS'),(6,'user','2017-02-18 02:20:00','AUTHENTICATION_SUCCESS'),(7,'user','2017-02-18 03:00:42','AUTHENTICATION_SUCCESS'),(8,'user','2017-02-18 03:33:54','AUTHENTICATION_SUCCESS'),(9,'user','2017-02-18 18:35:50','AUTHENTICATION_SUCCESS'),(10,'user','2017-02-18 19:24:15','AUTHENTICATION_SUCCESS'),(11,'','2017-02-18 20:27:25','AUTHENTICATION_FAILURE'),(12,'','2017-02-18 20:27:59','AUTHENTICATION_FAILURE'),(13,'','2017-02-18 20:28:09','AUTHENTICATION_FAILURE'),(14,'','2017-02-18 20:36:27','AUTHENTICATION_FAILURE'),(15,'','2017-02-18 20:36:54','AUTHENTICATION_FAILURE'),(16,'user','2017-02-18 20:39:19','AUTHENTICATION_SUCCESS'),(17,'user','2017-02-18 20:45:05','AUTHENTICATION_SUCCESS'),(18,'user','2017-02-18 21:15:01','AUTHENTICATION_SUCCESS'),(19,'admin','2017-02-18 21:19:23','AUTHENTICATION_SUCCESS'),(20,'user','2017-02-18 21:22:30','AUTHENTICATION_SUCCESS'),(21,'user','2017-02-18 21:25:26','AUTHENTICATION_SUCCESS'),(22,'user','2017-02-18 21:29:04','AUTHENTICATION_SUCCESS'),(23,'user','2017-02-18 21:30:30','AUTHENTICATION_SUCCESS'),(24,'user','2017-02-18 21:33:16','AUTHENTICATION_SUCCESS'),(25,'user','2017-02-18 21:36:54','AUTHENTICATION_SUCCESS'),(26,'user','2017-02-18 21:39:45','AUTHENTICATION_SUCCESS'),(27,'user','2017-02-18 21:40:28','AUTHENTICATION_SUCCESS'),(28,'admin','2017-02-19 19:39:28','AUTHENTICATION_SUCCESS'),(29,'user','2017-02-19 19:40:26','AUTHENTICATION_SUCCESS'),(30,'user','2017-02-19 19:52:02','AUTHENTICATION_SUCCESS'),(31,'user','2017-02-19 20:45:25','AUTHENTICATION_SUCCESS'),(32,'user','2017-02-19 20:54:40','AUTHENTICATION_SUCCESS'),(33,'user','2017-02-19 21:25:04','AUTHENTICATION_SUCCESS'),(34,'user','2017-02-19 21:52:06','AUTHENTICATION_SUCCESS'),(35,'user','2017-02-20 15:27:24','AUTHENTICATION_SUCCESS'),(36,'admin','2017-02-20 15:54:04','AUTHENTICATION_SUCCESS'),(37,'user','2017-02-20 16:34:49','AUTHENTICATION_SUCCESS'),(38,'user','2017-02-20 16:37:11','AUTHENTICATION_SUCCESS'),(39,'user','2017-02-20 16:47:48','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'remoteAddress','0:0:0:0:0:0:0:1'),(1,'sessionId','iLwGclY18poOXKDvbSNTYNmeamcDmBtjn9fcJnEz'),(2,'remoteAddress','0:0:0:0:0:0:0:1'),(2,'sessionId','5UHONCW4D2E4ZPK0SNikiqmgFXsPz3Neg6kz5-hx'),(3,'remoteAddress','0:0:0:0:0:0:0:1'),(3,'sessionId','j6TuAtgHEUszQxi73ShP1Ws_MVaLo0p73YNxOBt1'),(4,'remoteAddress','0:0:0:0:0:0:0:1'),(4,'sessionId','Lh-8pJsUu3OzaN_erbRbmhKjczcVeJV8HyAsYps3'),(5,'remoteAddress','0:0:0:0:0:0:0:1'),(5,'sessionId','fTtU2BFwMoVf4Rgn2uE5uv85p6dfg58Fsl6aDb9b'),(6,'remoteAddress','0:0:0:0:0:0:0:1'),(6,'sessionId','pSGxkGlQQlQrFrSXkrRmjqM_XNsjk6Z4WAcjHOCa'),(7,'remoteAddress','0:0:0:0:0:0:0:1'),(7,'sessionId','74beN44D04NOqGqNiKCTrEoCaP7qLAshnHivYeP1'),(8,'remoteAddress','0:0:0:0:0:0:0:1'),(8,'sessionId','oXet6lcXQbYgBj6zja9H7-TccwfrajIWR9WJMw6q'),(9,'remoteAddress','0:0:0:0:0:0:0:1'),(9,'sessionId','-nAyYGpBbNBvdutWkWdSQDn9WEjCoiTzqvHoMR6d'),(10,'remoteAddress','0:0:0:0:0:0:0:1'),(10,'sessionId','8EA-Vs1ev1vkujK42BZKZwMT_7An6wwIQ9CsOV0A'),(11,'message','Bad credentials'),(11,'remoteAddress','0:0:0:0:0:0:0:1'),(11,'sessionId','mrDJKls8Se2NgOq240_hrcEqmXdJzMnV5gpGiKmI'),(11,'type','org.springframework.security.authentication.BadCredentialsException'),(12,'message','Bad credentials'),(12,'remoteAddress','0:0:0:0:0:0:0:1'),(12,'sessionId','mrDJKls8Se2NgOq240_hrcEqmXdJzMnV5gpGiKmI'),(12,'type','org.springframework.security.authentication.BadCredentialsException'),(13,'message','Bad credentials'),(13,'remoteAddress','0:0:0:0:0:0:0:1'),(13,'sessionId','mrDJKls8Se2NgOq240_hrcEqmXdJzMnV5gpGiKmI'),(13,'type','org.springframework.security.authentication.BadCredentialsException'),(14,'message','Bad credentials'),(14,'remoteAddress','0:0:0:0:0:0:0:1'),(14,'sessionId','E4c38lhgBjwVheHuCm9vBqa2bnpST9Tf6XUBuAB5'),(14,'type','org.springframework.security.authentication.BadCredentialsException'),(15,'message','Bad credentials'),(15,'remoteAddress','0:0:0:0:0:0:0:1'),(15,'sessionId','E4c38lhgBjwVheHuCm9vBqa2bnpST9Tf6XUBuAB5'),(15,'type','org.springframework.security.authentication.BadCredentialsException'),(16,'remoteAddress','0:0:0:0:0:0:0:1'),(16,'sessionId','eB2z1K45Vwj5DqbvbkNOL0EqmiC41w-sn51vH-LF'),(17,'remoteAddress','0:0:0:0:0:0:0:1'),(17,'sessionId','-vSOEM81j_f8tmttKG2Ff3j4ac3MHi9-C_jdtHik'),(18,'remoteAddress','0:0:0:0:0:0:0:1'),(18,'sessionId','j2LFokGJS5uwPW0Ybp37C3XW6Obg4LtCV5-5NYke'),(19,'remoteAddress','0:0:0:0:0:0:0:1'),(19,'sessionId','kDnFH1tHkKfCgrK2xdKB4jkqJ3ZzI8ruPsK4XZnr'),(20,'remoteAddress','0:0:0:0:0:0:0:1'),(20,'sessionId','yNfwjyKpOLMLsCwYyp4W3dLyzDu0HzAbCR3J-QXO'),(21,'remoteAddress','0:0:0:0:0:0:0:1'),(21,'sessionId','2US30gmzgfYeDXN566oZhHgPvW1mjvnqg-qij1au'),(22,'remoteAddress','0:0:0:0:0:0:0:1'),(22,'sessionId','9lu-NTQmHFfRBkDtueXZM-CSux4Sm6ZqsQjl9fW8'),(23,'remoteAddress','0:0:0:0:0:0:0:1'),(23,'sessionId','MDtsvo6W-rSdUE78tLhnGpMeQVAaJy9z835804-i'),(24,'remoteAddress','0:0:0:0:0:0:0:1'),(24,'sessionId','bQbSYDDXM5vFsoe-YpReFu6TIhzPlq79M4NizbKV'),(25,'remoteAddress','0:0:0:0:0:0:0:1'),(25,'sessionId','PN6zbt4nK2WIolPE6UoBdI6bna7Dbf7QmC6tFXaY'),(26,'remoteAddress','0:0:0:0:0:0:0:1'),(26,'sessionId','0ZXqxqCUmaU7w7Cns10fscaIiUTN22ahjMTmkZEY'),(27,'remoteAddress','0:0:0:0:0:0:0:1'),(27,'sessionId','VKaUlXyvBdqP9lYdHKL6VAsbAl8EYNX1bUcuGSop'),(28,'remoteAddress','0:0:0:0:0:0:0:1'),(28,'sessionId','7gu5BrGfnFiRsaCwmbXnu8hpt9TnMq_4RZAfE0-9'),(29,'remoteAddress','0:0:0:0:0:0:0:1'),(29,'sessionId','YwikieLp16sPZNRGmplj-LGwwNo6XyoxMlNncHSB'),(30,'remoteAddress','0:0:0:0:0:0:0:1'),(30,'sessionId','g3TatRX1FP66mlPqg2vRlaJe36NL2eBrwoOYuFSO'),(31,'remoteAddress','0:0:0:0:0:0:0:1'),(31,'sessionId','381k07ZxirPZYHI8nTU-vZVH6ZRmgyFr9FGON8P5'),(32,'remoteAddress','0:0:0:0:0:0:0:1'),(32,'sessionId','_9bTZ_9DKMp28FKbAY8BNWgOpTm7G-WgLRboOJiy'),(33,'remoteAddress','0:0:0:0:0:0:0:1'),(33,'sessionId','UV4zyZlk_YIc9x7oZL3HYyyN56MSY9327UXMI44q'),(34,'remoteAddress','0:0:0:0:0:0:0:1'),(34,'sessionId','iK-5P3ILJkvPGt3VsGFUGvQQ2Pzj1h6enrFfm87E'),(35,'remoteAddress','0:0:0:0:0:0:0:1'),(35,'sessionId','vvpbSxzTDdj0vD35CDWDyAQdoPcnMcQoFDJ1NQBc'),(36,'remoteAddress','0:0:0:0:0:0:0:1'),(36,'sessionId','5nXINh8hWuW8q5ZtB_jC-pDtEZaa8EXNY_fwvhs1'),(37,'remoteAddress','0:0:0:0:0:0:0:1'),(37,'sessionId','XVheTEz1VdHtc9vGm2UjJeap6hjlraWpcU1B6-B0'),(38,'remoteAddress','0:0:0:0:0:0:0:1'),(38,'sessionId','70eadVJhwjIJSpRkzKLLg6Rn5Rj-vHwmxfaC-c-X'),(39,'remoteAddress','0:0:0:0:0:0:0:1'),(39,'sessionId','IJMkwPJbt3cUp8fAC0sfrAczMn2DFPAt94FfvJgC');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_token`
--

DROP TABLE IF EXISTS `jhi_persistent_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_token` (
  `series` varchar(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(20) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`series`),
  KEY `fk_user_persistent_token` (`user_id`),
  CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_token`
--

LOCK TABLES `jhi_persistent_token` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','','','en',NULL,NULL,'system','2017-02-17 19:43:03',NULL,'system',NULL),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','','en',NULL,NULL,'system','2017-02-17 19:43:03',NULL,'system',NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','','en',NULL,NULL,'system','2017-02-17 19:43:03',NULL,'system',NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','','en',NULL,NULL,'system','2017-02-17 19:43:03',NULL,'system',NULL),(5,'1604719496209796',NULL,NULL,NULL,NULL,NULL,'\0','en','71650267879186869689',NULL,'1604719496209796','2017-02-20 15:48:28',NULL,'1604719496209796','2017-02-20 15:48:28');
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `dbmovie_id` int(11) NOT NULL,
  `poster_path` varchar(255) DEFAULT NULL,
  `poster_url` varchar(255) DEFAULT NULL,
  `interest` varchar(255) DEFAULT NULL,
  `movie_wishlist_id` bigint(20) DEFAULT NULL,
  `movie_watchlist_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movie_movie_wishlist_id` (`movie_wishlist_id`),
  KEY `fk_movie_movie_watchlist_id` (`movie_watchlist_id`),
  CONSTRAINT `fk_movie_movie_watchlist_id` FOREIGN KEY (`movie_watchlist_id`) REFERENCES `movie_watchlist` (`id`),
  CONSTRAINT `fk_movie_movie_wishlist_id` FOREIGN KEY (`movie_wishlist_id`) REFERENCES `movie_wishlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_watchlist`
--

DROP TABLE IF EXISTS `movie_watchlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_watchlist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dbmovie_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movie_watchlist_user_id` (`user_id`),
  CONSTRAINT `fk_movie_watchlist_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_watchlist`
--

LOCK TABLES `movie_watchlist` WRITE;
/*!40000 ALTER TABLE `movie_watchlist` DISABLE KEYS */;
INSERT INTO `movie_watchlist` VALUES (22,328111,4),(23,177572,4),(24,269149,4),(26,211672,4),(27,10193,4),(28,862,4),(29,109445,4),(30,150540,4),(31,127380,4),(32,335797,4),(33,671,4),(34,12444,4),(35,12445,4),(37,76341,4),(38,271110,4),(42,293660,4),(44,274870,4),(45,207932,3),(46,122917,3),(47,27205,3),(48,343611,3),(49,283366,4);
/*!40000 ALTER TABLE `movie_watchlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_wishlist`
--

DROP TABLE IF EXISTS `movie_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_wishlist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dbmovie_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movie_wishlist_user_id` (`user_id`),
  CONSTRAINT `fk_movie_wishlist_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_wishlist`
--

LOCK TABLES `movie_wishlist` WRITE;
/*!40000 ALTER TABLE `movie_wishlist` DISABLE KEYS */;
INSERT INTO `movie_wishlist` VALUES (13,14160,4),(16,321612,4),(20,150689,4),(22,329865,4),(23,381288,4),(25,19995,4),(26,157336,4),(27,284052,4),(29,313369,4),(30,324849,4),(31,155,3),(32,324849,3),(33,293660,3);
/*!40000 ALTER TABLE `movie_wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-20 10:52:32
