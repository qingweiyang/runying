-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: runying
-- ------------------------------------------------------
-- Server version	5.6.22

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
-- Table structure for table `orders`
--
use runying;

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `releaseTime` datetime DEFAULT NULL,
  `deliveryTime` datetime DEFAULT NULL,
  `systemTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `operatorID` int(11) DEFAULT NULL,
  `contractNumber` varchar(255) DEFAULT NULL,
  `pictureNumber` varchar(255) DEFAULT NULL,
  `hasFinished` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r8tf7fj45ediewmvmshhfof7b` (`productID`),
  KEY `FK_c80cpp5gcwyxfua0eaa6j9k73` (`operatorID`),
  CONSTRAINT `FK_c80cpp5gcwyxfua0eaa6j9k73` FOREIGN KEY (`operatorID`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_r8tf7fj45ediewmvmshhfof7b` FOREIGN KEY (`productID`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process`
--

DROP TABLE IF EXISTS `process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processNum` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `systemTime` datetime DEFAULT NULL,
  `receiverID` int(11) DEFAULT NULL,
  `ordersID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8xcgycon4p9breiggi4itp0e5` (`receiverID`),
  KEY `FK_rolrcdm3o0k35lihoevq6xe82` (`ordersID`),
  CONSTRAINT `FK_8xcgycon4p9breiggi4itp0e5` FOREIGN KEY (`receiverID`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_rolrcdm3o0k35lihoevq6xe82` FOREIGN KEY (`ordersID`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process`
--

LOCK TABLES `process` WRITE;
/*!40000 ALTER TABLE `process` DISABLE KEYS */;
/*!40000 ALTER TABLE `process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `materialName` varchar(255) DEFAULT NULL,
  `size1` varchar(255) DEFAULT NULL,
  `size2` varchar(255) DEFAULT NULL,
  `materialCode` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (6,'阀杆转换接头','Φ61.95×111.25','','PN.0265WI25',NULL,1,'',0,'fgzhjt '),(7,'螺母','1.3/8\'\'—8UN','','PN.02030060138081',NULL,1,'',0,'lm '),(8,'双头螺柱','1.3/8\'\'—8UN×6.81\'\'(173)','','PN.LZ875034131',NULL,1,'',0,'stlz '),(9,'填料压盖','PFFA70/103WI—14/AISI4135','','PN.0267WI14',NULL,1,'',0,'tlyg '),(10,'填料压盖','PFFA70/78WI—13/AISI4135','','PN.0265WI13',NULL,1,'',0,'tlyg '),(11,'截止阀（内）','JZ105E—00/PSL2','1/2NPT(公）XM20X1.5(公）直角式 带泄压螺钉','PN.607E0021J41',NULL,1,'',0,'jzf（n） '),(12,'截止阀（内）','JZ70—00/PSL2','1/2NPT(公）×1/2NPT(母） 直角式  带泄压螺钉','PN.6060021J41',NULL,1,'',0,'jzf（n） '),(13,'螺母','M22','','PN0203006902211',NULL,1,'',0,'lm '),(14,'双头螺栓','M22×113','','PN.LZ2200221331',NULL,1,'42CrMo',0,'stls '),(15,'双头螺栓','M22×165','','PN.LZ2200323331',NULL,1,'42CrMo',0,'stls '),(16,'双头螺栓','7/8\'\'—9UNC×88','','',NULL,1,'42CrMo',0,'stls '),(17,'螺母','7/8\'\'‐9UNC','','',NULL,1,'45#',0,'lm '),(19,'双头螺栓','M30×3×200','','',NULL,1,'42CrMo',0,'stls '),(20,'螺母','M30×3','','',NULL,1,'45#',0,'lm '),(21,'双头螺栓','M30×3×153','','',NULL,1,'42CrMo',0,'stls '),(22,'螺母','M24','','',NULL,1,'45#',0,'lm '),(23,'双头螺栓','M24×136','','',NULL,1,'42CrMo',0,'stls '),(24,'双头螺栓','M30×3×195','','',NULL,1,'42CrMo',0,'stls '),(26,'顶丝','','','',NULL,1,'42CrMo',0,'ds '),(27,'压帽','','','',NULL,1,'45#',0,'ym '),(28,'双头螺栓','M42×3×335','','',NULL,1,'42CrMo',0,'stls '),(29,'螺母','M42×3','','',NULL,1,'45#',0,'lm '),(30,'双头螺栓','5/8\'\'—11UNC×3.07\'\'(78)','','',NULL,1,'42CrMo',0,'stls '),(31,'螺母','5/8\'\'—11UNC','','',NULL,1,'45#',0,'lm '),(32,'双头螺栓','M20×150','','',NULL,1,'42CrMo',0,'stls '),(33,'螺母','M20','','',NULL,1,'45#',0,'lm '),(34,'栽丝螺栓','M20×103','','',NULL,1,'42CrMo',0,'zsls '),(35,'双头螺栓','M27×128','','',NULL,1,'42CrMo',0,'stls '),(36,'螺母','M27','','',NULL,1,'45#',0,'lm '),(37,'双头螺栓','M45×3×400','','',NULL,1,'42CrMo',0,'stls '),(38,'螺母','M45×3','','',NULL,1,'45#',0,'lm '),(39,'顶丝','','','',NULL,1,'35CrMo',0,'ds '),(40,'阀杆盘根帽','','','',NULL,1,'35CrMo',0,'fgpgm '),(41,'压帽','','','',NULL,1,'35CrMo',0,'ym '),(42,'尾杆盘根压帽','','','',NULL,1,'35CrMo',0,'wgpgym'),(44,'栽丝螺栓','M27×118','','',NULL,1,'42CrMo',0,'zsls '),(45,'双头螺栓','M39×3×310','','',NULL,1,'42CrMo',0,'stls '),(46,'螺母','M39×3','','',NULL,1,'45#',0,'lm '),(47,'双头螺栓','M42×3×335','','',NULL,1,'42CrMo',0,'stls '),(48,'螺母','M42×3','','',NULL,1,'45#',0,'lm '),(49,'双头螺栓','M27×235','','',NULL,1,'42CrMo',0,'stls '),(50,'双头螺栓','M27×175','','',NULL,1,'42CrMo',0,'stls '),(51,'双头螺栓','3/4\'\'—10UNC×3.66\'\'(93)','','',NULL,1,'42CrMo',0,'stls '),(52,'螺母','3/4\'\'—10UNC','','',NULL,0,'45#',0,'lm '),(59,'螺母','3/4\'\'—10UNC','','',NULL,1,'45#',0,'lm '),(60,'丝堵','1/2NPT','','',NULL,1,'35CrMo',0,'sd '),(61,'手轮螺母','1/2\'\'—13UNC','','',NULL,1,'45#',0,'sllm '),(62,'双头螺栓','M20×145','','',NULL,1,'42CrMo',0,'stls '),(63,'双头螺栓','M30×3×140','','',NULL,1,'42CrMo',0,'stls '),(64,'螺母','M36×3','','',NULL,1,'45#',0,'lm '),(66,'双头螺栓','1.1/4\'\'—8UN×6.5(165)','','',NULL,1,'42CrMo',0,'stls '),(67,'螺母','1.1/4\'\'—8UN','','',NULL,1,'45#',0,'lm '),(68,'双头螺栓','M48×3×365','','',NULL,1,'42CrMo',0,'stls '),(69,'螺母','M48×3','','',NULL,1,'45#',0,'lm '),(70,'双头螺栓','M33×3×153','','',NULL,1,'42CrMo',0,'stls '),(71,'螺母','M33×3','','',NULL,1,'45#',0,'lm '),(72,'双头螺栓','1.7/8\'\'—8UN×14.17\'\'(360)','','',NULL,1,'42CrMo',0,'stls '),(73,'螺母','1.7/8\'\'—8UN','','',NULL,1,'45#',0,'lm '),(74,'双头螺栓','1.1/8\'\'—8UN×5.24\'\'(133)','','',NULL,1,'42CrMo',0,'stls '),(75,'双头螺栓','1.1/8\'\'—8UN×7.68（195）','','',NULL,1,'42CrMo',0,'stls '),(76,'螺母','1.1/8\'\'—8UN','','',NULL,1,'45#',0,'lm '),(77,'双头螺栓','1\'\'—8UN×4.84\'\'(123)','','',NULL,1,'42CrMo',0,'stls '),(78,'双头螺栓','1\'\'—8UN×6.89(175)','','',NULL,1,'42CrMo',0,'stls '),(79,'螺母','1\'\'—8UN','','',NULL,1,'45#',0,'lm '),(80,'螺母','3/4\'\'—10UNC','','',NULL,1,'42CrMo',0,'lm '),(81,'双头螺栓','7/8\'\'—9UNC×3.86(98)','','',NULL,1,'42CrMo',0,'stls '),(82,'螺母','7/8\'\'—9UNC','','',NULL,1,'45#',0,'lm '),(83,'双头螺栓','5/8\'\'—11UNC×3.07(78)','','',NULL,1,'42CrMo',0,'stls '),(84,'双头螺栓','7/8\'\'—9UNC×6.3（160）','','',NULL,1,'45#',0,'stls '),(85,'双头螺栓','1.3/8\'\'—8UN×10.63(270)','','',NULL,1,'42CrMo',0,'stls '),(87,'螺母','1.3/8\'\'—8UN','','',NULL,1,'45#',0,'lm '),(88,'双头螺栓','7/8\'\'—9UN×4.33(110)','','',NULL,1,'42CrMo',0,'stls '),(89,'双头螺栓','1.3/8\'\'—8UN×9.84(250)','','',NULL,1,'42CrMo',0,'stls '),(90,'双头螺栓','1‘’—8UN×4.84(123)','','',NULL,1,'42CrMo',0,'stls '),(91,'双头螺栓','1.1/8\'\'—8UN×8.27(210)','','',NULL,1,'42CrMo',0,'stls '),(92,'螺母','3/4\'\'—10UNC×3.66(93)','','',NULL,1,'42CrMo',0,'lm '),(93,'螺母','3/4\'\'—10UNC','','',NULL,1,'45#',0,'lm '),(94,'双头螺栓','M30×3×168','','',NULL,1,'42CrMo',0,'stls '),(96,'双头螺栓','M30×3×163','','',NULL,1,'42CrMo',0,'stls '),(97,'阀杆盘根压帽','','','',NULL,1,'35CrMo',0,'fgpgym '),(98,'双头螺栓','M27×195','','',NULL,1,'42CrMo',0,'stls '),(99,'栽丝螺栓','M27×128','','',NULL,1,'42CrMo',0,'zsls '),(100,'双头螺栓','M22×170','','',NULL,1,'42CrMo',0,'stls '),(101,'螺母','M22','','',NULL,1,'45#',0,'lm '),(102,'双头螺栓','M22×220','','',NULL,1,'42CrMo',0,'stls '),(103,'栽丝螺栓','M22×110','','',NULL,1,'42CrMo',0,'zsls '),(104,'双头螺栓','1.3/8\'\'—8UN×6.69(170)','','',NULL,1,'42CrMo',0,'stls '),(105,'双头螺栓','1.1/4\'\'—8UN×153','','',NULL,1,'42CrMo',0,'stls '),(106,'双头螺栓','1\'\'—8UN×4.84（123）','','',NULL,1,'42CrMo',0,'stls '),(107,'双头螺栓','M20×100','','',NULL,1,'42CrMo',0,'stls '),(108,'盘根压帽','','','',NULL,1,'35CrMo',0,'pgym '),(109,'锁紧螺钉','','','',NULL,1,'35CrMo',0,'sjld '),(110,'调节支承螺钉','','','',NULL,1,'35CrMo',0,'tjzcld '),(111,'双头螺栓','M52×3×500','','',NULL,1,'42CrMo',0,'stls '),(112,'螺母','M52×3','','',NULL,1,'45#',0,'lm '),(113,'双头螺栓','M22×110','','',NULL,1,'42CrMo',0,'stls '),(114,'双头螺栓','7/8\'\'—9UNC×4.65(118)','','',NULL,1,'42CrMo',0,'stls '),(115,'双头螺栓','1.3/8\'\'—8UN×6.81（173）','','',NULL,1,'42CrMo',0,'stls '),(116,'双头螺栓','1.5/8\'\'—8UN×7.99(203)','','',NULL,1,'45#',0,'stls '),(117,'螺母','1.5/8\'\'—8UN','','',NULL,1,'45#',0,'lm '),(118,'螺母','5/8\'\'—11UNC','','',NULL,1,'42CrMo',0,'lm '),(119,'双头螺栓','5/8\'\'—11UNC×3.66(93)','','',NULL,1,'45#',0,'stls '),(120,'双头螺栓','7/8\'\'—9UNC×4.65（118）','','',NULL,1,'42CrMo',0,'stls '),(121,'双头螺栓','1\'\'—8UNC×4.06（103）','','',NULL,1,'42CrMo',0,'stls '),(122,'螺母','1‘’—8UN','','',NULL,1,'45#',0,'lm '),(123,'调节螺钉','','','',NULL,1,'42CrMo',0,'tjld '),(124,'螺母','M18','','',NULL,1,'45#',0,'lm '),(125,'双头螺栓','7/8\'\'—9UNC×3.46‘’（88）','','',NULL,1,'42CrMo',0,'stls '),(127,'双头螺栓','M58×3×550','','',NULL,1,'42CrMo',0,'stls '),(128,'螺母','M58×3','','',NULL,1,'45#',0,'lm '),(129,'栽丝螺栓','M20×100','','',NULL,1,'42CrMo',0,'zsls '),(130,'双头螺栓','1.1/8\'\'—8UN×5.43(138)','','',NULL,1,'42CrMo',0,'stls ');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesBill`
--

DROP TABLE IF EXISTS `salesBill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesBill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `systemTime` datetime DEFAULT NULL,
  `operatorID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n3db73qy5gtc9l0pal9et0js0` (`operatorID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesBill`
--

LOCK TABLES `salesBill` WRITE;
/*!40000 ALTER TABLE `salesBill` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesBill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesBill_orders`
--

DROP TABLE IF EXISTS `salesBill_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesBill_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `ordersID` int(11) DEFAULT NULL,
  `saleBillID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1btnxa2p4qk4r06beyvuinyqu` (`ordersID`),
  KEY `FK_jitmjw97aybrd2l9e82n5chq9` (`saleBillID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesBill_orders`
--

LOCK TABLES `salesBill_orders` WRITE;
/*!40000 ALTER TABLE `salesBill_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesBill_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `privilege` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','202cb962ac59075b964b07152d234b70',7,0),(2,'yqw','202cb962ac59075b964b07152d234b70',12,1),(6,'adm','202cb962ac59075b964b07152d234b70',7,0),(7,'zw','202cb962ac59075b964b07152d234b70',3,0),(8,'杨庆莹','202cb962ac59075b964b07152d234b70',7,0),(9,'','202cb962ac59075b964b07152d234b70',4,0),(10,'张建','202cb962ac59075b964b07152d234b70',7,0),(11,'居莉','202cb962ac59075b964b07152d234b70',7,0),(12,'曹秀军','202cb962ac59075b964b07152d234b70',7,0),(13,'张键','202cb962ac59075b964b07152d234b70',1,0),(14,'guest','084e0343a0486ff05530df6c705c8bb4',1,0),(15,'t','202cb962ac59075b964b07152d234b70',2,0),(16,'123','202cb962ac59075b964b07152d234b70',3,0),(17,'张所振','202cb962ac59075b964b07152d234b70',2,1),(18,'guest','084e0343a0486ff05530df6c705c8bb4',0,0),(19,'','202cb962ac59075b964b07152d234b70',0,0),(20,'','202cb962ac59075b964b07152d234b70',0,0),(21,'张健','202cb962ac59075b964b07152d234b70',1,1),(22,'xy','01faa49cb375fd76fbad9a98fdbef048',0,1),(23,'test','098f6bcd4621d373cade4e832627b4f6',0,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8nb8tm7dy7ucy9lkhv79y3wfx` (`productID`),
  CONSTRAINT `FK_8nb8tm7dy7ucy9lkhv79y3wfx` FOREIGN KEY (`productID`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,0,6),(2,0,7),(3,0,8),(4,0,9),(5,0,10),(6,0,11),(7,0,12),(8,0,13),(9,0,14),(10,0,15),(11,0,16),(12,0,17),(13,0,19),(14,0,20),(15,0,21),(16,0,22),(17,0,23),(18,0,24),(19,0,26),(20,0,27),(21,0,28),(22,0,29),(23,0,30),(24,0,31),(25,0,32),(26,0,33),(27,0,34),(28,0,35),(29,0,36),(30,0,37),(31,0,38),(32,0,39),(33,0,40),(34,0,41),(35,0,42),(36,0,44),(37,0,45),(38,0,46),(39,0,47),(40,0,48),(41,0,49),(42,0,50),(43,0,51),(44,0,59),(45,0,60),(46,0,61),(47,0,62),(48,0,63),(49,0,64),(50,0,66),(51,0,67),(52,0,68),(53,0,69),(54,0,70),(55,0,71),(56,0,72),(57,0,73),(58,0,74),(59,0,75),(60,0,76),(61,0,77),(62,0,78),(63,0,79),(64,0,80),(65,0,81),(66,0,82),(67,0,83),(68,0,84),(69,0,85),(70,0,87),(71,0,88),(72,0,89),(73,0,90),(74,0,91),(75,0,92),(76,0,93),(77,0,94),(78,0,96),(79,0,97),(80,0,98),(81,0,99),(82,0,100),(83,0,101),(84,0,102),(85,0,103),(86,0,104),(87,0,105),(88,0,106),(89,0,107),(90,0,108),(91,0,109),(92,0,110),(93,0,111),(94,0,112),(95,0,113),(96,0,114),(97,0,115),(98,0,116),(99,0,117),(100,0,118),(101,0,119),(102,0,120),(103,0,121),(104,0,122),(105,0,123),(106,0,124),(107,0,125),(108,0,127),(109,0,128),(110,0,129),(111,0,130);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-09 23:49:46
