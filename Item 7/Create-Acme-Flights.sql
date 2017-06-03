start transaction;

create database `Acme-Flights`;
use `Acme-Flights`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Flights`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Flights`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Flights
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `contactPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `contactPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (105,0,'+34 (954) 596093','admin1@gmail.com','Admin 1','Surname Admin 1',97);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `contactPhone` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline`
--

LOCK TABLES `airline` WRITE;
/*!40000 ALTER TABLE `airline` DISABLE KEYS */;
INSERT INTO `airline` VALUES (118,0,'+34 902808005','\0','clientes@vueling.com','Vueling Airlines','http://blog.studentsville.it/wp-content/uploads/2013/05/Vueling_logo.jpg',4),(119,0,'(44) 8712460002','\0','clientes@ryanair.com','Ryanair','https://www.ryanair.com/etc/designs/ryanair/favicon/logo.png',2.5),(120,0,'+34 901111500','\0','clientes@transavia.com','Transavia airlines','https://www.aeropuertoinfo.com/wp-content/uploads/Logo-Transavia.jpg',3.5);
/*!40000 ALTER TABLE `airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airlineconfiguration`
--

DROP TABLE IF EXISTS `airlineconfiguration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airlineconfiguration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `childrenDiscount` double DEFAULT NULL,
  `maxBagWeight` double DEFAULT NULL,
  `maxCancellationDays` int(11) DEFAULT NULL,
  `maxChildrenAge` int(11) DEFAULT NULL,
  `overweightBagPrice` double DEFAULT NULL,
  `airline_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o55r0un53jvvhsou53yyxlf6p` (`airline_id`),
  CONSTRAINT `FK_o55r0un53jvvhsou53yyxlf6p` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airlineconfiguration`
--

LOCK TABLES `airlineconfiguration` WRITE;
/*!40000 ALTER TABLE `airlineconfiguration` DISABLE KEYS */;
INSERT INTO `airlineconfiguration` VALUES (121,0,10,15.5,7,13,30,118),(122,0,12,20,14,16,25,119),(123,0,9.5,15.5,30,15,20,120);
/*!40000 ALTER TABLE `airlineconfiguration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `airportName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `iataCode` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6soen31pm19i85p6nb4wtv6k0` (`iataCode`),
  KEY `UK_jjb3hjpdxpv9ems239lgwgtxa` (`deleted`),
  KEY `UK_3cljj2km9s186xl30lerr8m0y` (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (113,0,'Aeropuerto de Barcelona-El Prat','El Prat de Llobregat','España','\0','BCN','Barcelona',8.76,'Cataluña'),(114,0,'Aeropuerto de Sant\'Angelo Treviso','Treviso','Italia','\0','TSF',NULL,5.2,'Véneto'),(115,0,'Aeropuerto de Oslo-Gardermoen','Gardermoen','Noruega','\0','OSL',NULL,9.9,NULL),(116,0,'Aeropuerto de Sevilla','Sevilla','España','\0','SVQ',NULL,4.25,NULL),(117,0,'Aeropuerto de Schiphol','Amsterdam','Paises Bajos','\0','AMS',NULL,3.99,NULL);
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applies`
--

DROP TABLE IF EXISTS `applies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applies` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `usedPoints` int(11) DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  `flight_id` int(11) NOT NULL,
  `pointsCard_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g8dq1n9hjitg5wceoahunfbnu` (`book_id`),
  KEY `FK_2tbll8fand4sfc0fryp68rj1l` (`flight_id`),
  KEY `FK_8dys6w767y358kbrv138gav16` (`pointsCard_id`),
  CONSTRAINT `FK_8dys6w767y358kbrv138gav16` FOREIGN KEY (`pointsCard_id`) REFERENCES `pointscard` (`id`),
  CONSTRAINT `FK_2tbll8fand4sfc0fryp68rj1l` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
  CONSTRAINT `FK_g8dq1n9hjitg5wceoahunfbnu` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applies`
--

LOCK TABLES `applies` WRITE;
/*!40000 ALTER TABLE `applies` DISABLE KEYS */;
INSERT INTO `applies` VALUES (185,0,1,180,140,174),(186,0,2,180,141,174);
/*!40000 ALTER TABLE `applies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `numDisplayed` int(11) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `campaign_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_25923yjrpa6088equ6cgl8c2m` (`numDisplayed`),
  KEY `FK_ms34j6jf1uspiim756sjkasni` (`campaign_id`),
  CONSTRAINT `FK_ms34j6jf1uspiim756sjkasni` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (153,2,0,'http://ultimallamada.com/wp-content/uploads/2016/09/Vueling-680x350.jpg',149),(154,0,0,'http://jubbaairways.com/images/schedules-banner-img.jpg',150),(155,3,0,'http://img.net-v.ca/images/en/flights_banner.jpg',151),(156,2,0,'http://recursos.anuncios.com/files/781/55.jpg',149),(157,2,0,'http://3.bp.blogspot.com/-5ZlFlC5qsFc/VVwgeILPVpI/AAAAAAAAZd8/C80F-tnLh3U/s1600/vueling5.jpg',149),(158,2,0,'https://img.blogs.es/zenithmedia/wp-content/uploads/2015/07/vueling1.jpg',149),(159,2,0,'http://cdn.airportthai.co.th/uploads/profiles/0000000007/filemanager/images/banner_flight_schedule%281%29.jpg',150),(160,2,0,'http://skykingtravel.in/wp-content/uploads/2016/04/flight-booking-banner-1.jpg',150);
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cancelationMoment` datetime DEFAULT NULL,
  `childrenNumber` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `isBusiness` bit(1) DEFAULT NULL,
  `passengersNumber` int(11) DEFAULT NULL,
  `totalFee` double DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_59grjba8ahqtd7vpr1o6q852h` (`cancelationMoment`),
  KEY `UK_j5fscyrpsij5j6cvtjel8jlw6` (`isBusiness`),
  KEY `FK_syxpufh0r5fl8dprjcoli6v8c` (`user_id`),
  CONSTRAINT `FK_syxpufh0r5fl8dprjcoli6v8c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (179,0,NULL,0,NULL,'2017-05-11 12:30:00','\0',2,191.68,164),(180,0,NULL,1,NULL,'2016-07-02 10:34:00','',2,759.59,164),(181,0,NULL,0,NULL,'2017-05-20 10:34:00','\0',3,223.15,165),(182,0,'2016-08-02 10:34:00',0,NULL,'2016-07-02 10:34:00','\0',3,462.23,165),(183,0,NULL,0,NULL,'2017-05-28 22:34:10','\0',4,219.7,165),(184,0,NULL,0,NULL,'2017-04-28 13:29:10','',1,134.04,166);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_flight`
--

DROP TABLE IF EXISTS `book_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_flight` (
  `Book_id` int(11) NOT NULL,
  `flights_id` int(11) NOT NULL,
  KEY `FK_olwhucn11r76kin8camejyj3c` (`flights_id`),
  KEY `FK_kaqi1hlwdeolkog0o208a8nt4` (`Book_id`),
  CONSTRAINT `FK_kaqi1hlwdeolkog0o208a8nt4` FOREIGN KEY (`Book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_olwhucn11r76kin8camejyj3c` FOREIGN KEY (`flights_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_flight`
--

LOCK TABLES `book_flight` WRITE;
/*!40000 ALTER TABLE `book_flight` DISABLE KEYS */;
INSERT INTO `book_flight` VALUES (179,138),(179,139),(180,140),(180,141),(181,133),(181,134),(182,140),(182,141),(183,135),(184,141);
/*!40000 ALTER TABLE `book_flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_offer`
--

DROP TABLE IF EXISTS `book_offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_offer` (
  `Book_id` int(11) NOT NULL,
  `offers_id` int(11) NOT NULL,
  KEY `FK_h89y4oyl0a35jw62cjbf6sgs8` (`offers_id`),
  KEY `FK_h6dxb4n834o3ug97wyfh99ggk` (`Book_id`),
  CONSTRAINT `FK_h6dxb4n834o3ug97wyfh99ggk` FOREIGN KEY (`Book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_h89y4oyl0a35jw62cjbf6sgs8` FOREIGN KEY (`offers_id`) REFERENCES `offer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_offer`
--

LOCK TABLES `book_offer` WRITE;
/*!40000 ALTER TABLE `book_offer` DISABLE KEYS */;
INSERT INTO `book_offer` VALUES (181,143),(181,143),(183,143);
/*!40000 ALTER TABLE `book_offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_season`
--

DROP TABLE IF EXISTS `book_season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_season` (
  `Book_id` int(11) NOT NULL,
  `seasons_id` int(11) NOT NULL,
  KEY `FK_e8lvw1cslfs6pxbrifrxsvr6g` (`seasons_id`),
  KEY `FK_i5n1hohpxvysxvbo776c1r2p0` (`Book_id`),
  CONSTRAINT `FK_i5n1hohpxvysxvbo776c1r2p0` FOREIGN KEY (`Book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_e8lvw1cslfs6pxbrifrxsvr6g` FOREIGN KEY (`seasons_id`) REFERENCES `season` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_season`
--

LOCK TABLES `book_season` WRITE;
/*!40000 ALTER TABLE `book_season` DISABLE KEYS */;
INSERT INTO `book_season` VALUES (179,130),(180,124),(181,131),(181,131),(182,124),(183,127),(184,130);
/*!40000 ALTER TABLE `book_season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campaign` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `maxDisplayed` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `airline_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_gkhkrt8tn5m9hhkirio0h9505` (`startDate`),
  KEY `UK_d3b912m7cf9ake3d5jubsire9` (`endDate`),
  KEY `UK_etwxped8psr9crbl9o1i0jdti` (`deleted`),
  KEY `FK_c8jvrvkb2mv28bxliy2yfo2ng` (`airline_id`),
  CONSTRAINT `FK_c8jvrvkb2mv28bxliy2yfo2ng` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign`
--

LOCK TABLES `campaign` WRITE;
/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` VALUES (149,1,'\0','2017-06-30 23:59:59',10,'Campaign 1','2017-02-01 00:00:01',118),(150,0,'\0','2017-08-30 23:59:59',4,'Campaign 2','2017-04-01 00:00:01',119),(151,0,'\0','2017-12-20 23:59:59',2,'Campaign 3','2017-12-01 00:00:01',120),(152,0,'','2017-08-30 23:59:59',1,'Campaign 4','2017-07-01 00:00:01',118),(32768,0,'\0','2017-09-20 10:00:00',10,'Lessor1','2017-09-10 00:00:00',118);
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `commentText` varchar(255) DEFAULT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `airline` int(11) DEFAULT NULL,
  `comfort` int(11) DEFAULT NULL,
  `flight` int(11) DEFAULT NULL,
  `service` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `airline_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_2nd2xx02ob2p3pdke27dniw92` (`type`),
  KEY `FK_gu1q7beu5ahouqswfg951id6o` (`airline_id`),
  KEY `FK_jhvt6d9ap8gxv67ftrmshdfhj` (`user_id`),
  CONSTRAINT `FK_jhvt6d9ap8gxv67ftrmshdfhj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_gu1q7beu5ahouqswfg951id6o` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (188,0,'Muy cómodo y muy buen servicio. Un placer volar con ellos.','2017-04-04 13:47:00',5,0,5,5,'Positive',119,164),(189,0,'Me perdieron la maleta','2017-04-03 12:40:00',0,4,4,1,'Negative',119,165),(190,0,'He volado mucho con esta compañía y no tengo muchas quejas aunque las hay mejores','2017-03-27 10:40:00',4,3,4,2,'Neutral',118,166),(191,0,'Todo muy bien, aunque mejoraría la comida que ofrecen en los viajes largos','2017-03-29 15:58:00',4,1,4,4,'Positive',120,164),(192,0,'Tuvieron un poco de retraso en la salida pero luego el servicio fue bastante bueno en general','2017-04-21 21:36:00',3,3,2,4,'Neutral',120,164);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `campaignFee` double DEFAULT NULL,
  `fee` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (106,0,1,1);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2h62gb07aah6rtc8hgu3jgm94` (`user_id`),
  KEY `UK_evhfhpjdhcmyygxjo8sa6opcy` (`expirationMonth`),
  KEY `UK_12talwm2jns3cits0lnbbckl8` (`expirationYear`),
  CONSTRAINT `FK_2h62gb07aah6rtc8hgu3jgm94` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (167,0,'VISA',290,12,2017,'Gloria Bustos Cuéllar','4015224727093842',164),(168,0,'MASTERCARD',270,11,2018,'Rafael Raimundo Rana','4810100212629307',165),(169,0,'DISCOVER',270,4,2017,'Silvia Sánchez Casal','6011179219437494',166);
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchangerate`
--

DROP TABLE IF EXISTS `exchangerate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exchangerate` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `isoCode` varchar(255) DEFAULT NULL,
  `value1EUR` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l4cvgekhn1o4bms40hhnfoqj0` (`isoCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchangerate`
--

LOCK TABLES `exchangerate` WRITE;
/*!40000 ALTER TABLE `exchangerate` DISABLE KEYS */;
INSERT INTO `exchangerate` VALUES (107,0,'US Dollar','USD',1.09302),(108,0,'British Pound','GBP',0.848191),(109,0,'Norwegian Krone','NOK',9.35647),(110,0,'Russian Ruble','RUB',62.4186),(111,0,'Japanese Yen','JPY',123.874),(112,0,'Chinese Yuan Renminbi','CNY',7.54086);
/*!40000 ALTER TABLE `exchangerate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `childrenNumber` int(11) DEFAULT NULL,
  `departureDate` date DEFAULT NULL,
  `isBusiness` bit(1) DEFAULT NULL,
  `passengersNumber` int(11) DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `returnFlight` bit(1) DEFAULT NULL,
  `departure_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4pnq0idm6d7r5cfqkbgxopk3d` (`user_id`),
  KEY `FK_dcotu5tawfp8x7bi8s4p5b3kv` (`departure_id`),
  KEY `FK_pss0ps98h6fjv0wnpvkqiiumg` (`destination_id`),
  CONSTRAINT `FK_4pnq0idm6d7r5cfqkbgxopk3d` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_dcotu5tawfp8x7bi8s4p5b3kv` FOREIGN KEY (`departure_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FK_pss0ps98h6fjv0wnpvkqiiumg` FOREIGN KEY (`destination_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (170,0,0,'2017-06-15','\0',2,'2017-06-22','',113,115,164),(171,0,0,'2017-06-26','\0',1,NULL,'\0',114,113,165),(172,0,0,'2017-04-01','\0',1,NULL,'\0',113,116,166);
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `arrivalDate` datetime DEFAULT NULL,
  `availableBusinessSeats` int(11) DEFAULT NULL,
  `availableEconomySeats` int(11) DEFAULT NULL,
  `businessPrice` double DEFAULT NULL,
  `businessSeats` int(11) DEFAULT NULL,
  `cancelled` bit(1) DEFAULT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `departureDate` datetime DEFAULT NULL,
  `economyPrice` double DEFAULT NULL,
  `economySeats` int(11) DEFAULT NULL,
  `airline_id` int(11) NOT NULL,
  `departure_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_p8too7noua7gve1nwjd31k7cl` (`departureDate`),
  KEY `UK_rd95ps8pbl2cvyt3qw5dvr6r8` (`arrivalDate`),
  KEY `UK_6rkajwg1kn9bs6eb596o7sy5h` (`cancelled`),
  KEY `UK_860oqvgf10e6tkvnxs0vm1nb4` (`availableEconomySeats`),
  KEY `UK_so4tlg550kriw07nheb5y6y9n` (`availableBusinessSeats`),
  KEY `FK_4q2tr6gmioagvkmn6w92xf0a0` (`airline_id`),
  KEY `FK_coimshx5xfedl8wbowbronwni` (`departure_id`),
  KEY `FK_nsjwyrnmt9m7c0qq0ml2ckstd` (`destination_id`),
  CONSTRAINT `FK_nsjwyrnmt9m7c0qq0ml2ckstd` FOREIGN KEY (`destination_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FK_4q2tr6gmioagvkmn6w92xf0a0` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`),
  CONSTRAINT `FK_coimshx5xfedl8wbowbronwni` FOREIGN KEY (`departure_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (133,0,'2017-06-15 15:30:00',10,97,60.6,10,'\0','2017-01-01 22:15:00','2017-06-15 11:55:00',32.97,100,118,113,115),(134,0,'2017-06-22 19:40:00',10,97,54.6,10,'\0','2017-01-01 22:15:00','2017-06-22 16:15:00',26.05,100,118,115,113),(135,0,'2017-12-12 23:00:00',40,149,92.8,40,'\0','2017-01-01 22:15:00','2017-12-12 20:05:00',49.5,149,120,116,117),(136,0,'2017-06-15 15:30:00',30,175,100.7,30,'\0','2017-01-01 22:15:00','2017-06-15 11:55:00',44.75,175,120,113,115),(137,0,'2017-06-22 19:40:00',24,132,59.82,24,'\0','2017-01-01 22:15:00','2017-06-22 16:15:00',30.99,132,120,115,113),(138,0,'2017-06-23 13:35:00',10,98,70,20,'\0','2017-01-01 22:15:00','2017-06-23 11:35:00',20.5,120,119,113,114),(139,0,'2017-06-27 00:30:00',10,98,69.9,20,'\0','2017-01-01 22:15:00','2017-06-26 22:35:00',44.98,130,119,114,113),(140,0,'2017-05-25 08:15:00',20,117,70,20,'\0','2016-05-01 22:15:00','2017-05-25 06:35:00',42.18,120,119,116,113),(141,0,'2017-05-30 23:40:00',20,127,69.9,20,'\0','2016-05-01 22:15:00','2017-05-30 22:35:00',36.19,130,119,113,116),(142,0,'2017-06-18 15:30:00',10,100,60.6,10,'\0','2017-01-01 22:15:00','2017-06-18 11:55:00',32.97,100,118,113,115);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',5);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `paidMoment` datetime DEFAULT NULL,
  `totalFee` double DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bbmrcv7d29j6vd6vc1w2p2wox` (`book_id`),
  CONSTRAINT `FK_bbmrcv7d29j6vd6vc1w2p2wox` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (187,0,'2017-04-02 23:40:00','2017-04-04 12:40:00',305.82,180);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `contactPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `airline_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  KEY `FK_9o32d1hsqnytjm4p61oi27i5w` (`airline_id`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_9o32d1hsqnytjm4p61oi27i5w` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (146,0,'+34 (954) 15948','rorore@gmail.com','Rogelio','Rosa Retorcido',101,118),(147,0,'+34 (954) 98514','jormarle@gmail.com','Jorge','Martínez León',102,119),(148,0,'+34 (954) 32615','manager3@gmail.com','Manager 3','Surname Manager 3',103,120);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthlybill`
--

DROP TABLE IF EXISTS `monthlybill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthlybill` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `paidMoment` datetime DEFAULT NULL,
  `totalFee` double DEFAULT NULL,
  `airline_id` int(11) NOT NULL,
  `campaign_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_qyjfs64qr1oaxf7481c5u5650` (`paidMoment`),
  KEY `FK_9g16ife8rlx6br0hx8yrdhg1h` (`airline_id`),
  KEY `FK_gqmv0h9qes3i8bke2iq0ftpot` (`campaign_id`),
  CONSTRAINT `FK_gqmv0h9qes3i8bke2iq0ftpot` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`),
  CONSTRAINT `FK_9g16ife8rlx6br0hx8yrdhg1h` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthlybill`
--

LOCK TABLES `monthlybill` WRITE;
/*!40000 ALTER TABLE `monthlybill` DISABLE KEYS */;
INSERT INTO `monthlybill` VALUES (161,0,'2017-03-01 22:30:23','description monthlyBill 1','2017-03-01 22:30:56',18.76,118,149),(162,0,'2017-04-01 22:30:23','description monthlyBill 2',NULL,18.76,118,149),(163,0,'2017-05-01 12:20:43','description monthlyBill 2','2017-05-01 12:21:56',26.97,119,150);
/*!40000 ALTER TABLE `monthlybill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `discount` double DEFAULT NULL,
  `endMoment` datetime DEFAULT NULL,
  `startMoment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_u10d93qvkm6gj0oe73aj6qjs` (`startMoment`),
  KEY `UK_s3seamr8192xco86fwya80xeh` (`endMoment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (143,0,2.5,'2017-05-23 23:59:00','2017-05-15 00:01:00'),(144,0,5,'2017-06-10 23:59:00','2017-05-25 00:01:00'),(145,0,1,'2017-03-31 23:59:00','2017-03-01 00:01:00');
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer_offertable`
--

DROP TABLE IF EXISTS `offer_offertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer_offertable` (
  `Offer_id` int(11) NOT NULL,
  `offertables_id` int(11) NOT NULL,
  KEY `FK_58vnnag7ufrdr5tr5s7dmh7gj` (`Offer_id`),
  CONSTRAINT `FK_58vnnag7ufrdr5tr5s7dmh7gj` FOREIGN KEY (`Offer_id`) REFERENCES `offer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer_offertable`
--

LOCK TABLES `offer_offertable` WRITE;
/*!40000 ALTER TABLE `offer_offertable` DISABLE KEYS */;
INSERT INTO `offer_offertable` VALUES (143,118),(144,118),(145,135);
/*!40000 ALTER TABLE `offer_offertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offertable`
--

DROP TABLE IF EXISTS `offertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offertable` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offertable`
--

LOCK TABLES `offertable` WRITE;
/*!40000 ALTER TABLE `offertable` DISABLE KEYS */;
/*!40000 ALTER TABLE `offertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pointscard`
--

DROP TABLE IF EXISTS `pointscard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pointscard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `expirationMoment` datetime DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `airline_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g7387sf6exl261khp8jftkdiv` (`airline_id`),
  KEY `FK_itxa8qfxtldfalsiqoux4vnex` (`user_id`),
  CONSTRAINT `FK_itxa8qfxtldfalsiqoux4vnex` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_g7387sf6exl261khp8jftkdiv` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pointscard`
--

LOCK TABLES `pointscard` WRITE;
/*!40000 ALTER TABLE `pointscard` DISABLE KEYS */;
INSERT INTO `pointscard` VALUES (173,0,'2017-10-31 12:30:00',2,118,164),(174,0,'2017-07-10 15:30:01',2,119,164),(175,0,'2017-02-14 14:02:17',10,120,164),(176,0,'2017-10-31 12:30:00',1,118,165),(177,0,'2017-07-10 15:30:01',1,119,165),(178,0,'2017-10-31 12:30:00',0,118,166);
/*!40000 ALTER TABLE `pointscard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `season`
--

DROP TABLE IF EXISTS `season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `season` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `endDay` int(11) DEFAULT NULL,
  `endMonth` int(11) DEFAULT NULL,
  `inactive` bit(1) DEFAULT NULL,
  `pricePercentage` double DEFAULT NULL,
  `startDay` int(11) DEFAULT NULL,
  `startMonth` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `airline_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_45169mutpw6odqav2ogya82vn` (`inactive`),
  KEY `UK_3w3g0y6sjeaoqb6wyvied23x6` (`startMonth`),
  KEY `UK_52q4fybjuy1oogtuk9upk5iju` (`startDay`),
  KEY `UK_p3kwat1nn81hs3h9g3wls12sd` (`endMonth`),
  KEY `UK_3t4pfvlvunsnw0c2920gxxmv7` (`endDay`),
  KEY `UK_57m5lv59r9f9032hh56nix7d8` (`type`),
  KEY `FK_ehv4cvoetfi9j2sepfnesvbbq` (`airline_id`),
  CONSTRAINT `FK_ehv4cvoetfi9j2sepfnesvbbq` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `season`
--

LOCK TABLES `season` WRITE;
/*!40000 ALTER TABLE `season` DISABLE KEYS */;
INSERT INTO `season` VALUES (124,0,31,3,'\0',80,10,1,'Season 1','discount',119),(125,0,1,4,'\0',25,2,3,'Season 2','increase',118),(126,0,1,5,'\0',10,2,4,'Season 3','discount',118),(127,0,30,6,'\0',5,2,5,'Season 4','increase',120),(128,0,1,9,'\0',60,1,7,'Season 5','increase',118),(129,0,30,11,'\0',79,22,9,'Season 6','discount',120),(130,0,25,6,'\0',80,27,4,'Season 2','increase',119),(131,0,30,6,'\0',5,2,5,'Season 4','increase',118),(132,0,30,11,'\0',79,22,9,'Season 6','discount',118);
/*!40000 ALTER TABLE `season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `contactPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (164,0,'1234','user1@gmail.com','User 1','Surname 1',98),(165,0,'+34 (955) 555555','user2@gmail.com','User 2','Surname 2',99),(166,0,'5678','user3@gmail.com','User 3','Surname 3',100);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (97,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(98,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(99,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(100,0,'92877af70a45fd6a2ed7fe81e1236b78','user3'),(101,0,'c240642ddef994358c96da82c0361a58','manager1'),(102,0,'8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(103,0,'2d3a5db4a2a9717b43698520a8de57d0','manager3'),(104,0,'1b3231655cebb7a1f783eddf27d254ca','super');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (97,'ADMIN'),(98,'USER'),(99,'USER'),(100,'USER'),(101,'MANAGER'),(102,'MANAGER'),(103,'MANAGER'),(104,'ADMIN'),(104,'USER'),(104,'MANAGER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-03  6:21:13

commit;
