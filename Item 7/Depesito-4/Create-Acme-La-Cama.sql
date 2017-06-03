start transaction;

drop database if exists `Acme-La-Cama`;
create database `Acme-La-Cama`;

use `Acme-La-Cama`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
 on `Acme-La-Cama`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-La-Cama`.* to 'acme-manager'@'%';


-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-La-Cama
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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAdress` varchar(255) DEFAULT NULL,
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
INSERT INTO `administrator` VALUES (671,0,'theboss@us.es','Fran','698544236','calle carry','Wigueras Galvan',670);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
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
  `bannerUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1,0,'https://midietacojea.files.wordpress.com/2013/04/coca-cola.jpg'),(2,0,'http://files.canal-tv7.webnode.es/200000016-1c6931d63f/cruzcampo%2011.jpg'),(3,0,'http://media.istockphoto.com/vectors/banner-with-acorns-vector-id184387544'),(4,0,'http://www.casol.es/wp-content/uploads/2016/06/logo.jpg'),(5,0,'http://www.chillyintima.cz/popup/banner_soutez.jpg'),(6,0,'http://bazucastd.bazucaondemand.net.edgesuite.net/TVN.com/TVNX0013512301212840/PiratesWorldsEnd-1000x500-show_banner_featured_lg.png'),(7,0,'https://hydra-media.cursecdn.com/hearthstone.gamepedia.com/thumb/e/e0/Curse_of_Naxxramas_banner.jpg/600px-Curse_of_Naxxramas_banner.jpg?version=da783953f8d9a11dbd2c4f8b8c39747e'),(8,0,'https://www.stackoverflowbusiness.com/hubfs/logo-so-color.png?t=1495038995433');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `paid` bit(1) NOT NULL,
  `totalAmount` double NOT NULL,
  `offert_id` int(11) DEFAULT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_okj7j3wlcgpiy7oqw60lrc07u` (`offert_id`),
  KEY `FK_96kw9yy67g7efr923x9t8cb3t` (`reservation_id`),
  CONSTRAINT `FK_96kw9yy67g7efr923x9t8cb3t` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
  CONSTRAINT `FK_okj7j3wlcgpiy7oqw60lrc07u` FOREIGN KEY (`offert_id`) REFERENCES `offert` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (661,0,'',1000,659,NULL),(662,0,'',1000,NULL,651),(663,0,'\0',1000,NULL,652),(664,0,'\0',1000,NULL,653),(665,0,'\0',1000,NULL,654),(666,0,'\0',1000,NULL,655),(667,0,'\0',1000,NULL,656),(668,0,'\0',1000,660,NULL),(669,0,'\0',1000,NULL,657);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_billline`
--

DROP TABLE IF EXISTS `bill_billline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_billline` (
  `Bill_id` int(11) NOT NULL,
  `billLines_id` int(11) NOT NULL,
  UNIQUE KEY `UK_3ltk8gjbdfjoljw4a4axf6sq2` (`billLines_id`),
  KEY `FK_tffjeved1ogcm5sc4r3312cpx` (`Bill_id`),
  CONSTRAINT `FK_tffjeved1ogcm5sc4r3312cpx` FOREIGN KEY (`Bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FK_3ltk8gjbdfjoljw4a4axf6sq2` FOREIGN KEY (`billLines_id`) REFERENCES `billline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_billline`
--

LOCK TABLES `bill_billline` WRITE;
/*!40000 ALTER TABLE `bill_billline` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_billline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billline`
--

DROP TABLE IF EXISTS `billline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billline` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `bill_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jm425r3mmeaetbu5apo4trpxy` (`bill_id`),
  CONSTRAINT `FK_jm425r3mmeaetbu5apo4trpxy` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billline`
--

LOCK TABLES `billline` WRITE;
/*!40000 ALTER TABLE `billline` DISABLE KEYS */;
/*!40000 ALTER TABLE `billline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAdress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `accountNumber` varchar(255) DEFAULT NULL,
  `brandName` int(11) DEFAULT NULL,
  `cVV` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `dniNif` varchar(255) DEFAULT NULL,
  `finder_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9r9na2usnb9ylnvevarrssfdf` (`userAccount_id`),
  UNIQUE KEY `UK_n12q3ttn4iy9in1exjcq49otl` (`dniNif`),
  KEY `FK_osh93wcag7nm0vgsudkvvrbr2` (`finder_id`),
  CONSTRAINT `FK_9r9na2usnb9ylnvevarrssfdf` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_osh93wcag7nm0vgsudkvvrbr2` FOREIGN KEY (`finder_id`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (619,0,'pache@us.es','Dani','632114578','calle Estafeta','Pacheco Garcia',618,'4563804981065093',0,683,1,2019,'Tarjeta3','29836487J',NULL),(621,0,'lucy@us.es','Paco','632174578','calle Jeje XD','De Lucia',620,'4985474478693946',1,216,1,2019,'Tarjeta2','47668896Q',NULL),(623,0,'betis@us.es','Manolo','632114577','calle Betis','El de la Peña',622,'4149390898022867',0,396,1,2019,'Tarjeta1','56353978E',NULL),(625,0,'mora@us.es','Eugenio','732114578','calle mora','Garcia Morato',624,'4563804981065093',0,683,1,2019,'Tarjeta3','18349498Y',NULL),(627,0,'pet@us.es','Daniel','632114548','calle Estafeta','Ruiz Garcia',626,'4985474478693946',1,216,1,2019,'Tarjeta2','54185836K',NULL),(629,0,'pache@us.es','Luis','683698745','calle Estafeta','Pacheco Garcia',628,'4563804981065093',0,683,1,2019,'Tarjeta3','14936332V',NULL),(631,0,'oski@us.es','Oskira','632114578','calle Otero','Tir Her',630,'4149390898022867',0,396,1,2019,'Tarjeta1','09524983Q',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
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
  `creationDate` datetime DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `tittle` varchar(255) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sijaneofedb7fkcuhro1lc3n` (`client_id`),
  KEY `FK_sn1ac3iryd8f7nu6fpg78f8vt` (`hotel_id`),
  CONSTRAINT `FK_sn1ac3iryd8f7nu6fpg78f8vt` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FK_sijaneofedb7fkcuhro1lc3n` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (646,0,'2016-10-22 12:00:00',5,'Buena atencion me ha gustado mucho','Muy top',631,11),(647,0,'2016-10-22 12:00:00',5,'Buena atencion me ha gustado mucho','Muy bueno',629,11),(648,0,'2016-10-22 12:00:00',5,'Buena atencion me ha gustado mucho','Gran hotel',627,11),(649,0,'2016-10-22 12:00:00',1,'Mala atencion no me ha gustado mucho','Muy truño',631,216),(650,0,'2016-10-22 12:00:00',3,'Muy regular todo','Regular',631,468);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
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
  `capacity` int(11) DEFAULT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL,
  `hotelName` varchar(255) DEFAULT NULL,
  `kindOfOffert` int(11) DEFAULT NULL,
  `kindOfRoom` int(11) DEFAULT NULL,
  `maximumPrice` double DEFAULT NULL,
  `minimumPrice` double DEFAULT NULL,
  `population` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `terrain` int(11) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2whcorqkbthm9vnkhro7piswd` (`client_id`),
  CONSTRAINT `FK_2whcorqkbthm9vnkhro7piswd` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (581,0,'INBOX',10),(582,0,'OUTBOX',10),(583,0,'INBOX',215),(584,0,'OUTBOX',215),(599,0,'INBOX',586),(600,0,'OUTBOX',586),(601,0,'INBOX',588),(602,0,'OUTBOX',588),(603,0,'INBOX',590),(604,0,'OUTBOX',590),(605,0,'INBOX',592),(606,0,'OUTBOX',592),(607,0,'INBOX',594),(608,0,'OUTBOX',594),(609,0,'INBOX',596),(610,0,'OUTBOX',596),(611,0,'INBOX',598),(612,0,'OUTBOX',598),(632,0,'INBOX',619),(633,0,'OUTBOX',619),(634,0,'INBOX',621),(635,0,'OUTBOX',621),(636,0,'INBOX',623),(637,0,'OUTBOX',623),(638,0,'INBOX',625),(639,0,'OUTBOX',625),(640,0,'INBOX',627),(641,0,'OUTBOX',627),(642,0,'INBOX',629),(643,0,'OUTBOX',629),(644,0,'INBOX',631),(645,0,'OUTBOX',631),(672,0,'INBOX',671),(673,0,'OUTBOX',671),(674,0,'REPORTS',671);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global`
--

DROP TABLE IF EXISTS `global`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `global` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `requestPriceDay` double DEFAULT NULL,
  `season` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global`
--

LOCK TABLES `global` WRITE;
/*!40000 ALTER TABLE `global` DISABLE KEYS */;
INSERT INTO `global` VALUES (675,0,0.02,0);
/*!40000 ALTER TABLE `global` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hotelChain` varchar(255) DEFAULT NULL,
  `gpsCoords` varchar(255) DEFAULT NULL,
  `population` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `roomPrice` double DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `terrain` int(11) DEFAULT NULL,
  `totalRooms` int(11) DEFAULT NULL,
  `request_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4c6dc153t27hhk4uwr14a2das` (`request_id`),
  CONSTRAINT `FK_4c6dc153t27hhk4uwr14a2das` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (11,1,'Esto es una descripcion de prueba','Barcelo',NULL,'Cadiz','Cadiz','Barcelo4','http://www.barcelo.com/es/images/268-views-5-hotel-barcelo-punta-umbria-beach-resort_tcm7-9680.jpg',20,2,1,200,12),(216,1,'Esto es una descripcion de prueba','Puntumbria Hoteles',NULL,'Sevilla','Almeria','Pumtumbria Hotel 1','http://www.barcelo.com/es/images/268-views-5-hotel-barcelo-punta-umbria-beach-resort_tcm7-9680.jpg',30,2,0,250,217),(468,1,'Esto es una descripcion de prueba','Puntumbria Hoteles',NULL,'Coruña','Sevilla','Pumtumbria Hotel 2','http://www.barcelo.com/es/images/268-views-5-hotel-barcelo-punta-umbria-beach-resort_tcm7-9680.jpg',40,3,0,100,469);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
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
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAdress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  `accountNumber` varchar(255) DEFAULT NULL,
  `brandName` int(11) DEFAULT NULL,
  `cVV` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (10,0,'Antonio@us.es','Bartolo','698541236','calleAlmeria','Muñoz Garcia',9,'\0','4563804981065093',0,683,1,2019,'Tarjeta3'),(215,0,'Antonio@us.es','Manuel','698541236','calleAlmeria','Perez Garcia',214,'\0','4985474478693946',1,216,1,2019,'Tarjeta2');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7t1ls63lqb52igs4ms20cf94t` (`folder_id`),
  CONSTRAINT `FK_7t1ls63lqb52igs4ms20cf94t` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offert`
--

DROP TABLE IF EXISTS `offert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offert` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL,
  `kindOfOffert` int(11) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `bill_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `rooms_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jvt0q9wsmfbcap7vqdaefdv4m` (`bill_id`),
  KEY `FK_353dv86y725w506ff7igcsugd` (`client_id`),
  KEY `FK_7piruj7d9x6wtaqxgf0wsq23t` (`rooms_id`),
  CONSTRAINT `FK_7piruj7d9x6wtaqxgf0wsq23t` FOREIGN KEY (`rooms_id`) REFERENCES `room` (`id`),
  CONSTRAINT `FK_353dv86y725w506ff7igcsugd` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FK_jvt0q9wsmfbcap7vqdaefdv4m` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offert`
--

LOCK TABLES `offert` WRITE;
/*!40000 ALTER TABLE `offert` DISABLE KEYS */;
INSERT INTO `offert` VALUES (658,0,'2017-07-10','2017-07-17',3,130,NULL,NULL,439),(659,1,'2016-07-10','2016-07-17',3,130,661,619,14),(660,1,'2017-07-10','2017-07-17',3,130,668,621,461);
/*!40000 ALTER TABLE `offert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optionaltrip`
--

DROP TABLE IF EXISTS `optionaltrip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `optionaltrip` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `closingTime` time DEFAULT NULL,
  `gpsCoords` varchar(255) DEFAULT NULL,
  `population` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `openingTime` time DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5l98gmorn0serwqi72us7m5lp` (`hotel_id`),
  CONSTRAINT `FK_5l98gmorn0serwqi72us7m5lp` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optionaltrip`
--

LOCK TABLES `optionaltrip` WRITE;
/*!40000 ALTER TABLE `optionaltrip` DISABLE KEYS */;
INSERT INTO `optionaltrip` VALUES (613,0,'00:00:00',NULL,'Cadiz','Coruña','00:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYpvYhFnMIJdcJIKGlTTKYnxHA7y-5Cm4P_7kAVzsYgHolbpO1OGLmNp4',32.2,'Excursion al balneario',11),(614,0,'00:00:00',NULL,'Valencia','Madrid','00:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYpvYhFnMIJdcJIKGlTTKYnxHA7y-5Cm4P_7kAVzsYgHolbpO1OGLmNp4',10.2,'Excursion al cine',11),(615,0,'00:00:00',NULL,'Sevilla','Almeria','00:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYpvYhFnMIJdcJIKGlTTKYnxHA7y-5Cm4P_7kAVzsYgHolbpO1OGLmNp4',32.2,'Excursion al balneario',216),(616,0,'00:00:00',NULL,'Sevilla','Almeria','00:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYpvYhFnMIJdcJIKGlTTKYnxHA7y-5Cm4P_7kAVzsYgHolbpO1OGLmNp4',32.2,'Excursion al campo',216),(617,0,'00:00:00',NULL,'Coruña','Sevilla','00:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYpvYhFnMIJdcJIKGlTTKYnxHA7y-5Cm4P_7kAVzsYgHolbpO1OGLmNp4',32.2,'Excursion al balneario',468);
/*!40000 ALTER TABLE `optionaltrip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optionaltrip_client`
--

DROP TABLE IF EXISTS `optionaltrip_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `optionaltrip_client` (
  `OptionalTrip_id` int(11) NOT NULL,
  `clients_id` int(11) NOT NULL,
  KEY `FK_hvlixodh65y0qg17be0k4xni4` (`clients_id`),
  KEY `FK_9mg3l9qbmhpqedv6al46v3hsx` (`OptionalTrip_id`),
  CONSTRAINT `FK_9mg3l9qbmhpqedv6al46v3hsx` FOREIGN KEY (`OptionalTrip_id`) REFERENCES `optionaltrip` (`id`),
  CONSTRAINT `FK_hvlixodh65y0qg17be0k4xni4` FOREIGN KEY (`clients_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optionaltrip_client`
--

LOCK TABLES `optionaltrip_client` WRITE;
/*!40000 ALTER TABLE `optionaltrip_client` DISABLE KEYS */;
INSERT INTO `optionaltrip_client` VALUES (615,631);
/*!40000 ALTER TABLE `optionaltrip_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optionaltrip_tripdays`
--

DROP TABLE IF EXISTS `optionaltrip_tripdays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `optionaltrip_tripdays` (
  `OptionalTrip_id` int(11) NOT NULL,
  `tripDays` date DEFAULT NULL,
  KEY `FK_rqc1n795xfdw66s7abqm9mlj8` (`OptionalTrip_id`),
  CONSTRAINT `FK_rqc1n795xfdw66s7abqm9mlj8` FOREIGN KEY (`OptionalTrip_id`) REFERENCES `optionaltrip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optionaltrip_tripdays`
--

LOCK TABLES `optionaltrip_tripdays` WRITE;
/*!40000 ALTER TABLE `optionaltrip_tripdays` DISABLE KEYS */;
INSERT INTO `optionaltrip_tripdays` VALUES (613,'2017-05-22'),(613,'2017-05-29'),(613,'2017-05-23'),(613,'2017-05-24'),(613,'2017-05-25'),(613,'2017-05-26'),(613,'2017-05-27'),(613,'2017-05-28'),(614,'2017-10-22'),(614,'2017-11-29'),(614,'2017-10-23'),(614,'2017-10-24'),(614,'2017-10-25'),(614,'2017-10-26'),(614,'2017-10-27'),(614,'2017-10-28'),(614,'2017-10-29'),(614,'2017-10-30'),(614,'2017-10-31'),(614,'2017-11-01'),(614,'2017-11-02'),(614,'2017-11-03'),(614,'2017-11-04'),(614,'2017-11-05'),(614,'2017-11-06'),(614,'2017-11-07'),(614,'2017-11-08'),(614,'2017-11-09'),(614,'2017-11-10'),(614,'2017-11-11'),(614,'2017-11-12'),(614,'2017-11-13'),(614,'2017-11-14'),(614,'2017-11-15'),(614,'2017-11-16'),(614,'2017-11-17'),(614,'2017-11-18'),(614,'2017-11-19'),(614,'2017-11-20'),(614,'2017-11-21'),(614,'2017-11-22'),(614,'2017-11-23'),(614,'2017-11-24'),(614,'2017-11-25'),(614,'2017-11-26'),(614,'2017-11-27'),(614,'2017-11-28'),(615,'2017-07-22'),(615,'2017-07-29'),(615,'2017-07-23'),(615,'2017-07-24'),(615,'2017-07-25'),(615,'2017-07-26'),(615,'2017-07-27'),(615,'2017-07-28'),(616,'2017-04-20'),(616,'2017-05-02'),(616,'2017-04-21'),(616,'2017-04-22'),(616,'2017-04-23'),(616,'2017-04-24'),(616,'2017-04-25'),(616,'2017-04-26'),(616,'2017-04-27'),(616,'2017-04-28'),(616,'2017-04-29'),(616,'2017-04-30'),(616,'2017-05-01'),(617,'2017-10-22'),(617,'2017-10-29'),(617,'2017-10-23'),(617,'2017-10-24'),(617,'2017-10-25'),(617,'2017-10-26'),(617,'2017-10-27'),(617,'2017-10-28');
/*!40000 ALTER TABLE `optionaltrip_tripdays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `timeIn` datetime DEFAULT NULL,
  `timeOut` datetime DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k9hjyj3klme8d84c4ti413ii4` (`hotel_id`),
  KEY `FK_5rf4c479q8ksknvkvpk30bbjo` (`manager_id`),
  CONSTRAINT `FK_5rf4c479q8ksknvkvpk30bbjo` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FK_k9hjyj3klme8d84c4ti413ii4` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (12,0,32.2,0,'1996-10-22 00:00:00','1996-10-25 00:00:00',11,10),(217,0,23.65,0,'1996-10-22 00:00:00','2030-10-25 00:00:00',216,215),(469,0,23.65,0,'1996-10-22 00:00:00','2030-10-25 00:00:00',468,215);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL,
  `kindOfOffert` int(11) DEFAULT NULL,
  `numDays` int(11) DEFAULT NULL,
  `priceDay` double DEFAULT NULL,
  `bill_id` int(11) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `rooms_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7w89ytpn78xuvml76ic8l7i33` (`bill_id`),
  KEY `FK_ra534k0q9b0wra6xwhbjahm8l` (`client_id`),
  KEY `FK_rv64riutnme0jlnn5y2e1s1x4` (`rooms_id`),
  CONSTRAINT `FK_rv64riutnme0jlnn5y2e1s1x4` FOREIGN KEY (`rooms_id`) REFERENCES `room` (`id`),
  CONSTRAINT `FK_7w89ytpn78xuvml76ic8l7i33` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FK_ra534k0q9b0wra6xwhbjahm8l` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (651,1,'2016-10-22','2016-10-29',1,7,30,662,631,13),(652,1,'2016-10-22','2016-10-29',0,7,33,663,631,317),(653,1,'2017-05-18','2017-06-29',2,41,30,664,629,38),(654,1,'2017-05-18','2017-06-18',1,30,30,665,627,113),(655,1,'2017-07-22','2017-07-29',1,7,30,666,631,417),(656,1,'2017-07-10','2017-07-17',1,7,130,667,631,519),(657,1,'2017-05-18','2017-06-29',1,7,130,669,619,267);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `kids` bit(1) NOT NULL,
  `kindOfRoom` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `originalPriceDays` double DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3hcrnvtvo40py9dymk7me1ciu` (`hotel_id`),
  CONSTRAINT `FK_3hcrnvtvo40py9dymk7me1ciu` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (13,0,2,'Esto es la descripcion de una habitacion','',1,0,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(14,0,2,'Esto es la descripcion de una habitacion','',1,1,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(15,0,2,'Esto es la descripcion de una habitacion','',1,2,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(16,0,2,'Esto es la descripcion de una habitacion','',1,3,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(17,0,2,'Esto es la descripcion de una habitacion','',1,4,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(18,0,2,'Esto es la descripcion de una habitacion','',1,5,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(19,0,2,'Esto es la descripcion de una habitacion','',1,6,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(20,0,2,'Esto es la descripcion de una habitacion','',1,7,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(21,0,2,'Esto es la descripcion de una habitacion','',1,8,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(22,0,2,'Esto es la descripcion de una habitacion','',1,9,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(23,0,2,'Esto es la descripcion de una habitacion','',1,10,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(24,0,2,'Esto es la descripcion de una habitacion','',1,11,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(25,0,2,'Esto es la descripcion de una habitacion','',1,12,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(26,0,2,'Esto es la descripcion de una habitacion','',1,13,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(27,0,2,'Esto es la descripcion de una habitacion','',1,14,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(28,0,2,'Esto es la descripcion de una habitacion','',1,15,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(29,0,2,'Esto es la descripcion de una habitacion','',1,16,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(30,0,2,'Esto es la descripcion de una habitacion','',1,17,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(31,0,2,'Esto es la descripcion de una habitacion','',1,18,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(32,0,2,'Esto es la descripcion de una habitacion','',1,19,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(33,0,2,'Esto es la descripcion de una habitacion','',1,20,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(34,0,1,'Esto es la descripcion de una habitacion','\0',0,21,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(35,0,1,'Esto es la descripcion de una habitacion','\0',0,22,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(36,0,1,'Esto es la descripcion de una habitacion','\0',0,23,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(37,0,1,'Esto es la descripcion de una habitacion','\0',0,24,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(38,0,1,'Esto es la descripcion de una habitacion','\0',0,25,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(39,0,1,'Esto es la descripcion de una habitacion','\0',0,26,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(40,0,1,'Esto es la descripcion de una habitacion','\0',0,27,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(41,0,1,'Esto es la descripcion de una habitacion','\0',0,28,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(42,0,1,'Esto es la descripcion de una habitacion','\0',0,29,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(43,0,1,'Esto es la descripcion de una habitacion','\0',0,30,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(44,0,1,'Esto es la descripcion de una habitacion','\0',0,31,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(45,0,1,'Esto es la descripcion de una habitacion','\0',0,32,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(46,0,1,'Esto es la descripcion de una habitacion','\0',0,33,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(47,0,1,'Esto es la descripcion de una habitacion','\0',0,34,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(48,0,1,'Esto es la descripcion de una habitacion','\0',0,35,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(49,0,1,'Esto es la descripcion de una habitacion','\0',0,36,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(50,0,1,'Esto es la descripcion de una habitacion','\0',0,37,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(51,0,1,'Esto es la descripcion de una habitacion','\0',0,38,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(52,0,1,'Esto es la descripcion de una habitacion','\0',0,39,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(53,0,1,'Esto es la descripcion de una habitacion','\0',0,40,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(54,0,1,'Esto es la descripcion de una habitacion','\0',0,41,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(55,0,1,'Esto es la descripcion de una habitacion','\0',0,42,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(56,0,1,'Esto es la descripcion de una habitacion','\0',0,43,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(57,0,1,'Esto es la descripcion de una habitacion','\0',0,44,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(58,0,1,'Esto es la descripcion de una habitacion','\0',0,45,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(59,0,1,'Esto es la descripcion de una habitacion','\0',0,46,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(60,0,1,'Esto es la descripcion de una habitacion','\0',0,47,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(61,0,1,'Esto es la descripcion de una habitacion','\0',0,48,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(62,0,1,'Esto es la descripcion de una habitacion','\0',0,49,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(63,0,1,'Esto es la descripcion de una habitacion','\0',0,50,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(64,0,2,'Esto es la descripcion de una habitacion','\0',3,51,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(65,0,2,'Esto es la descripcion de una habitacion','\0',3,52,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(66,0,2,'Esto es la descripcion de una habitacion','\0',3,53,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(67,0,2,'Esto es la descripcion de una habitacion','\0',3,54,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(68,0,2,'Esto es la descripcion de una habitacion','\0',3,55,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(69,0,2,'Esto es la descripcion de una habitacion','\0',3,56,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(70,0,2,'Esto es la descripcion de una habitacion','\0',3,57,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(71,0,2,'Esto es la descripcion de una habitacion','\0',3,58,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(72,0,2,'Esto es la descripcion de una habitacion','\0',3,59,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(73,0,2,'Esto es la descripcion de una habitacion','\0',3,60,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(74,0,2,'Esto es la descripcion de una habitacion','\0',3,61,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(75,0,2,'Esto es la descripcion de una habitacion','\0',3,62,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(76,0,2,'Esto es la descripcion de una habitacion','\0',3,63,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(77,0,2,'Esto es la descripcion de una habitacion','\0',3,64,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(78,0,2,'Esto es la descripcion de una habitacion','\0',3,65,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(79,0,2,'Esto es la descripcion de una habitacion','\0',3,66,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(80,0,2,'Esto es la descripcion de una habitacion','\0',3,67,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(81,0,2,'Esto es la descripcion de una habitacion','\0',3,68,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(82,0,2,'Esto es la descripcion de una habitacion','\0',3,69,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(83,0,2,'Esto es la descripcion de una habitacion','\0',3,70,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(84,0,2,'Esto es la descripcion de una habitacion','\0',3,71,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(85,0,2,'Esto es la descripcion de una habitacion','\0',3,72,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(86,0,2,'Esto es la descripcion de una habitacion','\0',3,73,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(87,0,2,'Esto es la descripcion de una habitacion','\0',3,74,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(88,0,2,'Esto es la descripcion de una habitacion','\0',3,75,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(89,0,2,'Esto es la descripcion de una habitacion','\0',3,76,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(90,0,2,'Esto es la descripcion de una habitacion','\0',3,77,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(91,0,2,'Esto es la descripcion de una habitacion','\0',3,78,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(92,0,2,'Esto es la descripcion de una habitacion','\0',3,79,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(93,0,2,'Esto es la descripcion de una habitacion','\0',3,80,500,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(94,0,3,'Esto es la descripcion de una habitacion','\0',4,81,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(95,0,3,'Esto es la descripcion de una habitacion','\0',4,82,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(96,0,3,'Esto es la descripcion de una habitacion','\0',4,83,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(97,0,3,'Esto es la descripcion de una habitacion','\0',4,84,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(98,0,3,'Esto es la descripcion de una habitacion','\0',4,85,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(99,0,3,'Esto es la descripcion de una habitacion','\0',4,86,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(100,0,3,'Esto es la descripcion de una habitacion','\0',4,87,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(101,0,3,'Esto es la descripcion de una habitacion','\0',4,88,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(102,0,3,'Esto es la descripcion de una habitacion','\0',4,89,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(103,0,3,'Esto es la descripcion de una habitacion','\0',4,90,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(104,0,3,'Esto es la descripcion de una habitacion','\0',4,91,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(105,0,3,'Esto es la descripcion de una habitacion','\0',4,92,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(106,0,3,'Esto es la descripcion de una habitacion','\0',4,93,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(107,0,3,'Esto es la descripcion de una habitacion','\0',4,94,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(108,0,3,'Esto es la descripcion de una habitacion','\0',4,95,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(109,0,3,'Esto es la descripcion de una habitacion','\0',4,96,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(110,0,3,'Esto es la descripcion de una habitacion','\0',4,97,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(111,0,3,'Esto es la descripcion de una habitacion','\0',4,98,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(112,0,3,'Esto es la descripcion de una habitacion','\0',4,99,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(113,0,3,'Esto es la descripcion de una habitacion','\0',4,100,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(114,0,3,'Esto es la descripcion de una habitacion','\0',4,101,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(115,0,3,'Esto es la descripcion de una habitacion','\0',4,102,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(116,0,3,'Esto es la descripcion de una habitacion','\0',4,103,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(117,0,3,'Esto es la descripcion de una habitacion','\0',4,104,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(118,0,3,'Esto es la descripcion de una habitacion','\0',4,105,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(119,0,3,'Esto es la descripcion de una habitacion','\0',4,106,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(120,0,3,'Esto es la descripcion de una habitacion','\0',4,107,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(121,0,3,'Esto es la descripcion de una habitacion','\0',4,108,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(122,0,3,'Esto es la descripcion de una habitacion','\0',4,109,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(123,0,3,'Esto es la descripcion de una habitacion','\0',4,110,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(124,0,3,'Esto es la descripcion de una habitacion','\0',4,111,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(125,0,3,'Esto es la descripcion de una habitacion','\0',4,112,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(126,0,3,'Esto es la descripcion de una habitacion','\0',4,113,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(127,0,3,'Esto es la descripcion de una habitacion','\0',4,114,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(128,0,3,'Esto es la descripcion de una habitacion','\0',4,115,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(129,0,3,'Esto es la descripcion de una habitacion','\0',4,116,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(130,0,3,'Esto es la descripcion de una habitacion','\0',4,117,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(131,0,3,'Esto es la descripcion de una habitacion','\0',4,118,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(132,0,3,'Esto es la descripcion de una habitacion','\0',4,119,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(133,0,3,'Esto es la descripcion de una habitacion','\0',4,120,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(134,0,3,'Esto es la descripcion de una habitacion','\0',4,121,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(135,0,3,'Esto es la descripcion de una habitacion','\0',4,122,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(136,0,3,'Esto es la descripcion de una habitacion','\0',4,123,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(137,0,3,'Esto es la descripcion de una habitacion','\0',4,124,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(138,0,3,'Esto es la descripcion de una habitacion','\0',4,125,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(139,0,3,'Esto es la descripcion de una habitacion','\0',4,126,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(140,0,3,'Esto es la descripcion de una habitacion','\0',4,127,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(141,0,3,'Esto es la descripcion de una habitacion','\0',4,128,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(142,0,3,'Esto es la descripcion de una habitacion','\0',4,129,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(143,0,3,'Esto es la descripcion de una habitacion','\0',4,130,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(144,0,1,'Esto es la descripcion de una habitacion','\0',2,131,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(145,0,1,'Esto es la descripcion de una habitacion','\0',2,132,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(146,0,1,'Esto es la descripcion de una habitacion','\0',2,133,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(147,0,1,'Esto es la descripcion de una habitacion','\0',2,134,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(148,0,1,'Esto es la descripcion de una habitacion','\0',2,135,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(149,0,1,'Esto es la descripcion de una habitacion','\0',2,136,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(150,0,1,'Esto es la descripcion de una habitacion','\0',2,137,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(151,0,1,'Esto es la descripcion de una habitacion','\0',2,138,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(152,0,1,'Esto es la descripcion de una habitacion','\0',2,139,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(153,0,1,'Esto es la descripcion de una habitacion','\0',2,140,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(154,0,1,'Esto es la descripcion de una habitacion','\0',2,141,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(155,0,1,'Esto es la descripcion de una habitacion','\0',2,142,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(156,0,1,'Esto es la descripcion de una habitacion','\0',2,143,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(157,0,1,'Esto es la descripcion de una habitacion','\0',2,144,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(158,0,1,'Esto es la descripcion de una habitacion','\0',2,145,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(159,0,1,'Esto es la descripcion de una habitacion','\0',2,146,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(160,0,1,'Esto es la descripcion de una habitacion','\0',2,147,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(161,0,1,'Esto es la descripcion de una habitacion','\0',2,148,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(162,0,1,'Esto es la descripcion de una habitacion','\0',2,149,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(163,0,1,'Esto es la descripcion de una habitacion','\0',2,150,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(164,0,1,'Esto es la descripcion de una habitacion','\0',2,151,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(165,0,1,'Esto es la descripcion de una habitacion','\0',2,152,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(166,0,1,'Esto es la descripcion de una habitacion','\0',2,153,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(167,0,1,'Esto es la descripcion de una habitacion','\0',2,154,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(168,0,1,'Esto es la descripcion de una habitacion','\0',2,155,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(169,0,1,'Esto es la descripcion de una habitacion','\0',2,156,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(170,0,1,'Esto es la descripcion de una habitacion','\0',2,157,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(171,0,1,'Esto es la descripcion de una habitacion','\0',2,158,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(172,0,1,'Esto es la descripcion de una habitacion','\0',2,159,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(173,0,1,'Esto es la descripcion de una habitacion','\0',2,160,10,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(174,0,5,'Esto es la descripcion de una habitacion','',5,161,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(175,0,5,'Esto es la descripcion de una habitacion','',5,162,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(176,0,5,'Esto es la descripcion de una habitacion','',5,163,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(177,0,5,'Esto es la descripcion de una habitacion','',5,164,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(178,0,5,'Esto es la descripcion de una habitacion','',5,165,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(179,0,5,'Esto es la descripcion de una habitacion','',5,166,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(180,0,5,'Esto es la descripcion de una habitacion','',5,167,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(181,0,5,'Esto es la descripcion de una habitacion','',5,168,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(182,0,5,'Esto es la descripcion de una habitacion','',5,169,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(183,0,5,'Esto es la descripcion de una habitacion','',5,170,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(184,0,5,'Esto es la descripcion de una habitacion','',5,171,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(185,0,5,'Esto es la descripcion de una habitacion','',5,172,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(186,0,5,'Esto es la descripcion de una habitacion','',5,173,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(187,0,5,'Esto es la descripcion de una habitacion','',5,174,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(188,0,5,'Esto es la descripcion de una habitacion','',5,175,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(189,0,5,'Esto es la descripcion de una habitacion','',5,176,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(190,0,5,'Esto es la descripcion de una habitacion','',5,177,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(191,0,5,'Esto es la descripcion de una habitacion','',5,178,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(192,0,5,'Esto es la descripcion de una habitacion','',5,179,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(193,0,5,'Esto es la descripcion de una habitacion','',5,180,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(194,0,5,'Esto es la descripcion de una habitacion','',5,181,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(195,0,5,'Esto es la descripcion de una habitacion','',5,182,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(196,0,5,'Esto es la descripcion de una habitacion','',5,183,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(197,0,5,'Esto es la descripcion de una habitacion','',5,184,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(198,0,5,'Esto es la descripcion de una habitacion','',5,185,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(199,0,5,'Esto es la descripcion de una habitacion','',5,186,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(200,0,5,'Esto es la descripcion de una habitacion','',5,187,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(201,0,5,'Esto es la descripcion de una habitacion','',5,188,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(202,0,5,'Esto es la descripcion de una habitacion','',5,189,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(203,0,5,'Esto es la descripcion de una habitacion','',5,190,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(204,0,5,'Esto es la descripcion de una habitacion','',5,191,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(205,0,5,'Esto es la descripcion de una habitacion','',5,192,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(206,0,5,'Esto es la descripcion de una habitacion','',5,193,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(207,0,5,'Esto es la descripcion de una habitacion','',5,194,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(208,0,5,'Esto es la descripcion de una habitacion','',5,195,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(209,0,5,'Esto es la descripcion de una habitacion','',5,196,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(210,0,5,'Esto es la descripcion de una habitacion','',5,197,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(211,0,5,'Esto es la descripcion de una habitacion','',5,198,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(212,0,5,'Esto es la descripcion de una habitacion','',5,199,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(213,0,5,'Esto es la descripcion de una habitacion','',5,200,100,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',11),(218,0,2,'Esto es la descripcion de una habitacion','',1,201,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(219,0,2,'Esto es la descripcion de una habitacion','',1,202,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(220,0,2,'Esto es la descripcion de una habitacion','',1,203,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(221,0,2,'Esto es la descripcion de una habitacion','',1,204,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(222,0,2,'Esto es la descripcion de una habitacion','',1,205,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(223,0,2,'Esto es la descripcion de una habitacion','',1,206,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(224,0,2,'Esto es la descripcion de una habitacion','',1,207,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(225,0,2,'Esto es la descripcion de una habitacion','',1,208,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(226,0,2,'Esto es la descripcion de una habitacion','',1,209,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(227,0,2,'Esto es la descripcion de una habitacion','',1,210,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(228,0,2,'Esto es la descripcion de una habitacion','',1,211,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(229,0,2,'Esto es la descripcion de una habitacion','',1,212,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(230,0,2,'Esto es la descripcion de una habitacion','',1,213,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(231,0,2,'Esto es la descripcion de una habitacion','',1,214,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(232,0,2,'Esto es la descripcion de una habitacion','',1,215,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(233,0,2,'Esto es la descripcion de una habitacion','',1,216,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(234,0,2,'Esto es la descripcion de una habitacion','',1,217,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(235,0,2,'Esto es la descripcion de una habitacion','',1,218,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(236,0,2,'Esto es la descripcion de una habitacion','',1,219,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(237,0,2,'Esto es la descripcion de una habitacion','',1,220,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(238,0,2,'Esto es la descripcion de una habitacion','',1,221,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(239,0,2,'Esto es la descripcion de una habitacion','',1,222,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(240,0,2,'Esto es la descripcion de una habitacion','',1,223,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(241,0,2,'Esto es la descripcion de una habitacion','',1,224,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(242,0,2,'Esto es la descripcion de una habitacion','',1,225,300,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(243,0,1,'Esto es la descripcion de una habitacion','\0',0,226,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(244,0,1,'Esto es la descripcion de una habitacion','\0',0,227,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(245,0,1,'Esto es la descripcion de una habitacion','\0',0,228,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(246,0,1,'Esto es la descripcion de una habitacion','\0',0,229,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(247,0,1,'Esto es la descripcion de una habitacion','\0',0,230,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(248,0,1,'Esto es la descripcion de una habitacion','\0',0,231,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(249,0,1,'Esto es la descripcion de una habitacion','\0',0,232,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(250,0,1,'Esto es la descripcion de una habitacion','\0',0,233,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(251,0,1,'Esto es la descripcion de una habitacion','\0',0,234,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(252,0,1,'Esto es la descripcion de una habitacion','\0',0,235,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(253,0,1,'Esto es la descripcion de una habitacion','\0',0,236,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(254,0,1,'Esto es la descripcion de una habitacion','\0',0,237,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(255,0,1,'Esto es la descripcion de una habitacion','\0',0,238,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(256,0,1,'Esto es la descripcion de una habitacion','\0',0,239,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(257,0,1,'Esto es la descripcion de una habitacion','\0',0,240,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(258,0,1,'Esto es la descripcion de una habitacion','\0',0,241,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(259,0,1,'Esto es la descripcion de una habitacion','\0',0,242,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(260,0,1,'Esto es la descripcion de una habitacion','\0',0,243,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(261,0,1,'Esto es la descripcion de una habitacion','\0',0,244,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(262,0,1,'Esto es la descripcion de una habitacion','\0',0,245,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(263,0,1,'Esto es la descripcion de una habitacion','\0',0,246,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(264,0,1,'Esto es la descripcion de una habitacion','\0',0,247,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(265,0,1,'Esto es la descripcion de una habitacion','\0',0,248,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(266,0,1,'Esto es la descripcion de una habitacion','\0',0,249,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(267,0,1,'Esto es la descripcion de una habitacion','\0',0,250,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(268,0,1,'Esto es la descripcion de una habitacion','\0',0,251,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(269,0,1,'Esto es la descripcion de una habitacion','\0',0,252,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(270,0,1,'Esto es la descripcion de una habitacion','\0',0,253,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(271,0,1,'Esto es la descripcion de una habitacion','\0',0,254,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(272,0,1,'Esto es la descripcion de una habitacion','\0',0,255,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(273,0,1,'Esto es la descripcion de una habitacion','\0',0,256,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(274,0,1,'Esto es la descripcion de una habitacion','\0',0,257,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(275,0,1,'Esto es la descripcion de una habitacion','\0',0,258,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(276,0,1,'Esto es la descripcion de una habitacion','\0',0,259,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(277,0,1,'Esto es la descripcion de una habitacion','\0',0,260,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(278,0,1,'Esto es la descripcion de una habitacion','\0',0,261,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(279,0,1,'Esto es la descripcion de una habitacion','\0',0,262,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(280,0,1,'Esto es la descripcion de una habitacion','\0',0,263,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(281,0,1,'Esto es la descripcion de una habitacion','\0',0,264,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(282,0,1,'Esto es la descripcion de una habitacion','\0',0,265,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(283,0,1,'Esto es la descripcion de una habitacion','\0',0,266,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(284,0,1,'Esto es la descripcion de una habitacion','\0',0,267,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(285,0,1,'Esto es la descripcion de una habitacion','\0',0,268,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(286,0,1,'Esto es la descripcion de una habitacion','\0',0,269,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(287,0,1,'Esto es la descripcion de una habitacion','\0',0,270,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(288,0,1,'Esto es la descripcion de una habitacion','\0',0,271,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(289,0,1,'Esto es la descripcion de una habitacion','\0',0,272,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(290,0,1,'Esto es la descripcion de una habitacion','\0',0,273,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(291,0,1,'Esto es la descripcion de una habitacion','\0',0,274,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(292,0,1,'Esto es la descripcion de una habitacion','\0',0,275,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(293,0,1,'Esto es la descripcion de una habitacion','\0',0,276,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(294,0,1,'Esto es la descripcion de una habitacion','\0',0,277,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(295,0,1,'Esto es la descripcion de una habitacion','\0',0,278,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(296,0,1,'Esto es la descripcion de una habitacion','\0',0,279,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(297,0,1,'Esto es la descripcion de una habitacion','\0',0,280,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(298,0,2,'Esto es la descripcion de una habitacion','\0',3,281,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(299,0,2,'Esto es la descripcion de una habitacion','\0',3,282,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(300,0,2,'Esto es la descripcion de una habitacion','\0',3,283,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(301,0,2,'Esto es la descripcion de una habitacion','\0',3,284,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(302,0,2,'Esto es la descripcion de una habitacion','\0',3,285,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(303,0,2,'Esto es la descripcion de una habitacion','\0',3,286,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(304,0,2,'Esto es la descripcion de una habitacion','\0',3,287,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(305,0,2,'Esto es la descripcion de una habitacion','\0',3,288,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(306,0,2,'Esto es la descripcion de una habitacion','\0',3,289,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(307,0,2,'Esto es la descripcion de una habitacion','\0',3,290,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(308,0,2,'Esto es la descripcion de una habitacion','\0',3,291,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(309,0,2,'Esto es la descripcion de una habitacion','\0',3,292,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(310,0,2,'Esto es la descripcion de una habitacion','\0',3,293,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(311,0,2,'Esto es la descripcion de una habitacion','\0',3,294,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(312,0,2,'Esto es la descripcion de una habitacion','\0',3,295,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(313,0,2,'Esto es la descripcion de una habitacion','\0',3,296,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(314,0,2,'Esto es la descripcion de una habitacion','\0',3,297,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(315,0,2,'Esto es la descripcion de una habitacion','\0',3,298,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(316,0,2,'Esto es la descripcion de una habitacion','\0',3,299,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(317,0,2,'Esto es la descripcion de una habitacion','\0',3,300,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(318,0,2,'Esto es la descripcion de una habitacion','\0',3,301,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(319,0,2,'Esto es la descripcion de una habitacion','\0',3,302,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(320,0,2,'Esto es la descripcion de una habitacion','\0',3,303,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(321,0,2,'Esto es la descripcion de una habitacion','\0',3,304,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(322,0,2,'Esto es la descripcion de una habitacion','\0',3,305,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(323,0,2,'Esto es la descripcion de una habitacion','\0',3,306,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(324,0,2,'Esto es la descripcion de una habitacion','\0',3,307,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(325,0,2,'Esto es la descripcion de una habitacion','\0',3,308,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(326,0,2,'Esto es la descripcion de una habitacion','\0',3,309,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(327,0,2,'Esto es la descripcion de una habitacion','\0',3,310,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(328,0,2,'Esto es la descripcion de una habitacion','\0',3,311,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(329,0,2,'Esto es la descripcion de una habitacion','\0',3,312,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(330,0,2,'Esto es la descripcion de una habitacion','\0',3,313,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(331,0,2,'Esto es la descripcion de una habitacion','\0',3,314,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(332,0,2,'Esto es la descripcion de una habitacion','\0',3,315,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(333,0,2,'Esto es la descripcion de una habitacion','\0',3,316,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(334,0,2,'Esto es la descripcion de una habitacion','\0',3,317,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(335,0,2,'Esto es la descripcion de una habitacion','\0',3,318,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(336,0,2,'Esto es la descripcion de una habitacion','\0',3,319,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(337,0,2,'Esto es la descripcion de una habitacion','\0',3,320,600,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(338,0,3,'Esto es la descripcion de una habitacion','\0',4,321,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(339,0,3,'Esto es la descripcion de una habitacion','\0',4,322,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(340,0,3,'Esto es la descripcion de una habitacion','\0',4,323,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(341,0,3,'Esto es la descripcion de una habitacion','\0',4,324,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(342,0,3,'Esto es la descripcion de una habitacion','\0',4,325,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(343,0,3,'Esto es la descripcion de una habitacion','\0',4,326,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(344,0,3,'Esto es la descripcion de una habitacion','\0',4,327,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(345,0,3,'Esto es la descripcion de una habitacion','\0',4,328,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(346,0,3,'Esto es la descripcion de una habitacion','\0',4,329,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(347,0,3,'Esto es la descripcion de una habitacion','\0',4,330,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(348,0,3,'Esto es la descripcion de una habitacion','\0',4,331,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(349,0,3,'Esto es la descripcion de una habitacion','\0',4,332,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(350,0,3,'Esto es la descripcion de una habitacion','\0',4,333,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(351,0,3,'Esto es la descripcion de una habitacion','\0',4,334,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(352,0,3,'Esto es la descripcion de una habitacion','\0',4,335,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(353,0,3,'Esto es la descripcion de una habitacion','\0',4,336,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(354,0,3,'Esto es la descripcion de una habitacion','\0',4,337,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(355,0,3,'Esto es la descripcion de una habitacion','\0',4,338,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(356,0,3,'Esto es la descripcion de una habitacion','\0',4,339,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(357,0,3,'Esto es la descripcion de una habitacion','\0',4,340,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(358,0,3,'Esto es la descripcion de una habitacion','\0',4,341,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(359,0,3,'Esto es la descripcion de una habitacion','\0',4,342,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(360,0,3,'Esto es la descripcion de una habitacion','\0',4,343,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(361,0,3,'Esto es la descripcion de una habitacion','\0',4,344,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(362,0,3,'Esto es la descripcion de una habitacion','\0',4,345,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(363,0,3,'Esto es la descripcion de una habitacion','\0',4,346,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(364,0,3,'Esto es la descripcion de una habitacion','\0',4,347,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(365,0,3,'Esto es la descripcion de una habitacion','\0',4,348,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(366,0,3,'Esto es la descripcion de una habitacion','\0',4,349,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(367,0,3,'Esto es la descripcion de una habitacion','\0',4,350,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(368,0,3,'Esto es la descripcion de una habitacion','\0',4,351,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(369,0,3,'Esto es la descripcion de una habitacion','\0',4,352,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(370,0,3,'Esto es la descripcion de una habitacion','\0',4,353,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(371,0,3,'Esto es la descripcion de una habitacion','\0',4,354,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(372,0,3,'Esto es la descripcion de una habitacion','\0',4,355,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(373,0,3,'Esto es la descripcion de una habitacion','\0',4,356,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(374,0,3,'Esto es la descripcion de una habitacion','\0',4,357,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(375,0,3,'Esto es la descripcion de una habitacion','\0',4,358,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(376,0,3,'Esto es la descripcion de una habitacion','\0',4,359,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(377,0,3,'Esto es la descripcion de una habitacion','\0',4,360,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(378,0,3,'Esto es la descripcion de una habitacion','\0',4,361,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(379,0,3,'Esto es la descripcion de una habitacion','\0',4,362,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(380,0,3,'Esto es la descripcion de una habitacion','\0',4,363,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(381,0,3,'Esto es la descripcion de una habitacion','\0',4,364,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(382,0,3,'Esto es la descripcion de una habitacion','\0',4,365,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(383,0,3,'Esto es la descripcion de una habitacion','\0',4,366,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(384,0,3,'Esto es la descripcion de una habitacion','\0',4,367,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(385,0,3,'Esto es la descripcion de una habitacion','\0',4,368,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(386,0,3,'Esto es la descripcion de una habitacion','\0',4,369,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(387,0,3,'Esto es la descripcion de una habitacion','\0',4,370,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(388,0,3,'Esto es la descripcion de una habitacion','\0',4,371,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(389,0,3,'Esto es la descripcion de una habitacion','\0',4,372,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(390,0,3,'Esto es la descripcion de una habitacion','\0',4,373,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(391,0,3,'Esto es la descripcion de una habitacion','\0',4,374,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(392,0,3,'Esto es la descripcion de una habitacion','\0',4,375,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(393,0,3,'Esto es la descripcion de una habitacion','\0',4,376,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(394,0,3,'Esto es la descripcion de una habitacion','\0',4,377,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(395,0,3,'Esto es la descripcion de una habitacion','\0',4,378,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(396,0,3,'Esto es la descripcion de una habitacion','\0',4,379,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(397,0,3,'Esto es la descripcion de una habitacion','\0',4,380,400,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(398,0,1,'Esto es la descripcion de una habitacion','\0',2,381,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(399,0,1,'Esto es la descripcion de una habitacion','\0',2,382,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(400,0,1,'Esto es la descripcion de una habitacion','\0',2,383,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(401,0,1,'Esto es la descripcion de una habitacion','\0',2,384,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(402,0,1,'Esto es la descripcion de una habitacion','\0',2,385,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(403,0,1,'Esto es la descripcion de una habitacion','\0',2,386,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(404,0,1,'Esto es la descripcion de una habitacion','\0',2,387,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(405,0,1,'Esto es la descripcion de una habitacion','\0',2,388,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(406,0,1,'Esto es la descripcion de una habitacion','\0',2,389,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(407,0,1,'Esto es la descripcion de una habitacion','\0',2,390,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(408,0,1,'Esto es la descripcion de una habitacion','\0',2,391,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(409,0,1,'Esto es la descripcion de una habitacion','\0',2,392,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(410,0,1,'Esto es la descripcion de una habitacion','\0',2,393,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(411,0,1,'Esto es la descripcion de una habitacion','\0',2,394,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(412,0,1,'Esto es la descripcion de una habitacion','\0',2,395,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(413,0,1,'Esto es la descripcion de una habitacion','\0',2,396,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(414,0,1,'Esto es la descripcion de una habitacion','\0',2,397,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(415,0,1,'Esto es la descripcion de una habitacion','\0',2,398,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(416,0,1,'Esto es la descripcion de una habitacion','\0',2,399,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(417,0,1,'Esto es la descripcion de una habitacion','\0',2,400,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(418,0,1,'Esto es la descripcion de una habitacion','\0',2,401,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(419,0,1,'Esto es la descripcion de una habitacion','\0',2,402,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(420,0,1,'Esto es la descripcion de una habitacion','\0',2,403,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(421,0,1,'Esto es la descripcion de una habitacion','\0',2,404,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(422,0,1,'Esto es la descripcion de una habitacion','\0',2,405,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(423,0,1,'Esto es la descripcion de una habitacion','\0',2,406,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(424,0,1,'Esto es la descripcion de una habitacion','\0',2,407,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(425,0,1,'Esto es la descripcion de una habitacion','\0',2,408,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(426,0,1,'Esto es la descripcion de una habitacion','\0',2,409,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(427,0,1,'Esto es la descripcion de una habitacion','\0',2,410,20,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(428,0,5,'Esto es la descripcion de una habitacion','',5,411,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(429,0,5,'Esto es la descripcion de una habitacion','',5,412,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(430,0,5,'Esto es la descripcion de una habitacion','',5,413,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(431,0,5,'Esto es la descripcion de una habitacion','',5,414,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(432,0,5,'Esto es la descripcion de una habitacion','',5,415,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(433,0,5,'Esto es la descripcion de una habitacion','',5,416,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(434,0,5,'Esto es la descripcion de una habitacion','',5,417,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(435,0,5,'Esto es la descripcion de una habitacion','',5,418,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(436,0,5,'Esto es la descripcion de una habitacion','',5,419,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(437,0,5,'Esto es la descripcion de una habitacion','',5,420,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(438,0,5,'Esto es la descripcion de una habitacion','',5,421,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(439,0,5,'Esto es la descripcion de una habitacion','',5,422,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(440,0,5,'Esto es la descripcion de una habitacion','',5,423,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(441,0,5,'Esto es la descripcion de una habitacion','',5,424,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(442,0,5,'Esto es la descripcion de una habitacion','',5,425,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(443,0,5,'Esto es la descripcion de una habitacion','',5,426,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(444,0,5,'Esto es la descripcion de una habitacion','',5,427,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(445,0,5,'Esto es la descripcion de una habitacion','',5,428,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(446,0,5,'Esto es la descripcion de una habitacion','',5,429,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(447,0,5,'Esto es la descripcion de una habitacion','',5,430,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(448,0,5,'Esto es la descripcion de una habitacion','',5,431,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(449,0,5,'Esto es la descripcion de una habitacion','',5,432,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(450,0,5,'Esto es la descripcion de una habitacion','',5,433,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(451,0,5,'Esto es la descripcion de una habitacion','',5,434,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(452,0,5,'Esto es la descripcion de una habitacion','',5,435,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(453,0,5,'Esto es la descripcion de una habitacion','',5,436,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(454,0,5,'Esto es la descripcion de una habitacion','',5,437,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(455,0,5,'Esto es la descripcion de una habitacion','',5,438,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(456,0,5,'Esto es la descripcion de una habitacion','',5,439,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(457,0,5,'Esto es la descripcion de una habitacion','',5,440,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(458,0,5,'Esto es la descripcion de una habitacion','',5,441,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(459,0,5,'Esto es la descripcion de una habitacion','',5,442,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(460,0,5,'Esto es la descripcion de una habitacion','',5,443,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(461,0,5,'Esto es la descripcion de una habitacion','',5,444,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(462,0,5,'Esto es la descripcion de una habitacion','',5,445,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(463,0,5,'Esto es la descripcion de una habitacion','',5,446,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(464,0,5,'Esto es la descripcion de una habitacion','',5,447,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(465,0,5,'Esto es la descripcion de una habitacion','',5,448,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(466,0,5,'Esto es la descripcion de una habitacion','',5,449,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(467,0,5,'Esto es la descripcion de una habitacion','',5,450,200,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',216),(470,0,2,'Esto es la descripcion de una habitacion','',1,451,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(471,0,2,'Esto es la descripcion de una habitacion','',1,452,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(472,0,2,'Esto es la descripcion de una habitacion','',1,453,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(473,0,2,'Esto es la descripcion de una habitacion','',1,454,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(474,0,2,'Esto es la descripcion de una habitacion','',1,455,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(475,0,2,'Esto es la descripcion de una habitacion','',1,456,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(476,0,2,'Esto es la descripcion de una habitacion','',1,457,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(477,0,2,'Esto es la descripcion de una habitacion','',1,458,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(478,0,2,'Esto es la descripcion de una habitacion','',1,459,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(479,0,2,'Esto es la descripcion de una habitacion','',1,460,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(480,0,2,'Esto es la descripcion de una habitacion','',1,461,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(481,0,2,'Esto es la descripcion de una habitacion','',1,462,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(482,0,2,'Esto es la descripcion de una habitacion','',1,463,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(483,0,2,'Esto es la descripcion de una habitacion','',1,464,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(484,0,2,'Esto es la descripcion de una habitacion','',1,465,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(485,0,2,'Esto es la descripcion de una habitacion','',1,466,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(486,0,2,'Esto es la descripcion de una habitacion','',1,467,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(487,0,2,'Esto es la descripcion de una habitacion','',1,468,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(488,0,2,'Esto es la descripcion de una habitacion','',1,469,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(489,0,2,'Esto es la descripcion de una habitacion','',1,470,350,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(490,0,1,'Esto es la descripcion de una habitacion','\0',0,471,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(491,0,1,'Esto es la descripcion de una habitacion','\0',0,472,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(492,0,1,'Esto es la descripcion de una habitacion','\0',0,473,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(493,0,1,'Esto es la descripcion de una habitacion','\0',0,474,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(494,0,1,'Esto es la descripcion de una habitacion','\0',0,475,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(495,0,1,'Esto es la descripcion de una habitacion','\0',0,476,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(496,0,1,'Esto es la descripcion de una habitacion','\0',0,477,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(497,0,1,'Esto es la descripcion de una habitacion','\0',0,478,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(498,0,1,'Esto es la descripcion de una habitacion','\0',0,479,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(499,0,1,'Esto es la descripcion de una habitacion','\0',0,480,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(500,0,1,'Esto es la descripcion de una habitacion','\0',0,481,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(501,0,1,'Esto es la descripcion de una habitacion','\0',0,482,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(502,0,1,'Esto es la descripcion de una habitacion','\0',0,483,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(503,0,1,'Esto es la descripcion de una habitacion','\0',0,484,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(504,0,1,'Esto es la descripcion de una habitacion','\0',0,485,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(505,0,1,'Esto es la descripcion de una habitacion','\0',0,486,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(506,0,1,'Esto es la descripcion de una habitacion','\0',0,487,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(507,0,1,'Esto es la descripcion de una habitacion','\0',0,488,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(508,0,1,'Esto es la descripcion de una habitacion','\0',0,489,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(509,0,1,'Esto es la descripcion de una habitacion','\0',0,490,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(510,0,2,'Esto es la descripcion de una habitacion','\0',3,491,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(511,0,2,'Esto es la descripcion de una habitacion','\0',3,492,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(512,0,2,'Esto es la descripcion de una habitacion','\0',3,493,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(513,0,2,'Esto es la descripcion de una habitacion','\0',3,494,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(514,0,2,'Esto es la descripcion de una habitacion','\0',3,495,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(515,0,2,'Esto es la descripcion de una habitacion','\0',3,496,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(516,0,2,'Esto es la descripcion de una habitacion','\0',3,497,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(517,0,2,'Esto es la descripcion de una habitacion','\0',3,498,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(518,0,2,'Esto es la descripcion de una habitacion','\0',3,499,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(519,0,2,'Esto es la descripcion de una habitacion','\0',3,500,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(520,0,2,'Esto es la descripcion de una habitacion','\0',3,501,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(521,0,2,'Esto es la descripcion de una habitacion','\0',3,502,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(522,0,2,'Esto es la descripcion de una habitacion','\0',3,503,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(523,0,2,'Esto es la descripcion de una habitacion','\0',3,504,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(524,0,2,'Esto es la descripcion de una habitacion','\0',3,505,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(525,0,2,'Esto es la descripcion de una habitacion','\0',3,506,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(526,0,2,'Esto es la descripcion de una habitacion','\0',3,507,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(527,0,2,'Esto es la descripcion de una habitacion','\0',3,508,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(528,0,2,'Esto es la descripcion de una habitacion','\0',3,509,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(529,0,2,'Esto es la descripcion de una habitacion','\0',3,510,650,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(530,0,3,'Esto es la descripcion de una habitacion','\0',4,511,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(531,0,3,'Esto es la descripcion de una habitacion','\0',4,512,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(532,0,3,'Esto es la descripcion de una habitacion','\0',4,513,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(533,0,3,'Esto es la descripcion de una habitacion','\0',4,514,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(534,0,3,'Esto es la descripcion de una habitacion','\0',4,515,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(535,0,3,'Esto es la descripcion de una habitacion','\0',4,516,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(536,0,3,'Esto es la descripcion de una habitacion','\0',4,517,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(537,0,3,'Esto es la descripcion de una habitacion','\0',4,518,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(538,0,3,'Esto es la descripcion de una habitacion','\0',4,519,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(539,0,3,'Esto es la descripcion de una habitacion','\0',4,520,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(540,0,3,'Esto es la descripcion de una habitacion','\0',4,521,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(541,0,3,'Esto es la descripcion de una habitacion','\0',4,522,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(542,0,3,'Esto es la descripcion de una habitacion','\0',4,523,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(543,0,3,'Esto es la descripcion de una habitacion','\0',4,524,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(544,0,3,'Esto es la descripcion de una habitacion','\0',4,525,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(545,0,3,'Esto es la descripcion de una habitacion','\0',4,526,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(546,0,3,'Esto es la descripcion de una habitacion','\0',4,527,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(547,0,3,'Esto es la descripcion de una habitacion','\0',4,528,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(548,0,3,'Esto es la descripcion de una habitacion','\0',4,529,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(549,0,3,'Esto es la descripcion de una habitacion','\0',4,530,450,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(550,0,1,'Esto es la descripcion de una habitacion','\0',2,531,80,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(551,0,1,'Esto es la descripcion de una habitacion','\0',2,532,80,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(552,0,1,'Esto es la descripcion de una habitacion','\0',2,533,80,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(553,0,1,'Esto es la descripcion de una habitacion','\0',2,534,80,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(554,0,1,'Esto es la descripcion de una habitacion','\0',2,535,80,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(555,0,5,'Esto es la descripcion de una habitacion','',5,536,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(556,0,5,'Esto es la descripcion de una habitacion','',5,537,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(557,0,5,'Esto es la descripcion de una habitacion','',5,538,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(558,0,5,'Esto es la descripcion de una habitacion','',5,539,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(559,0,5,'Esto es la descripcion de una habitacion','',5,540,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(560,0,5,'Esto es la descripcion de una habitacion','',5,541,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(561,0,5,'Esto es la descripcion de una habitacion','',5,542,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(562,0,5,'Esto es la descripcion de una habitacion','',5,543,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(563,0,5,'Esto es la descripcion de una habitacion','',5,544,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(564,0,5,'Esto es la descripcion de una habitacion','',5,545,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(565,0,5,'Esto es la descripcion de una habitacion','',5,546,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(566,0,5,'Esto es la descripcion de una habitacion','',5,547,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(567,0,5,'Esto es la descripcion de una habitacion','',5,548,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(568,0,5,'Esto es la descripcion de una habitacion','',5,549,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468),(569,0,5,'Esto es la descripcion de una habitacion','',5,550,250,'https://a.otcdn.com/imglib/hotelfotos/8/172/hotel-barcelo-aranjuez-051.jpg',468);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_occupieddays`
--

DROP TABLE IF EXISTS `room_occupieddays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_occupieddays` (
  `Room_id` int(11) NOT NULL,
  `occupiedDays` date DEFAULT NULL,
  KEY `FK_ddg6cdxmc1gkuq5y3jo5upkok` (`Room_id`),
  CONSTRAINT `FK_ddg6cdxmc1gkuq5y3jo5upkok` FOREIGN KEY (`Room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_occupieddays`
--

LOCK TABLES `room_occupieddays` WRITE;
/*!40000 ALTER TABLE `room_occupieddays` DISABLE KEYS */;
INSERT INTO `room_occupieddays` VALUES (13,'2016-10-22'),(13,'2016-10-29'),(13,'2016-10-23'),(13,'2016-10-24'),(13,'2016-10-25'),(13,'2016-10-26'),(13,'2016-10-27'),(13,'2016-10-28'),(14,'2016-07-10'),(14,'2016-07-17'),(14,'2016-07-11'),(14,'2016-07-12'),(14,'2016-07-13'),(14,'2016-07-14'),(14,'2016-07-15'),(14,'2016-07-16'),(113,'2017-05-18'),(113,'2017-06-18'),(113,'2017-05-19'),(113,'2017-05-20'),(113,'2017-05-21'),(113,'2017-05-22'),(113,'2017-05-23'),(113,'2017-05-24'),(113,'2017-05-25'),(113,'2017-05-26'),(113,'2017-05-27'),(113,'2017-05-28'),(113,'2017-05-29'),(113,'2017-05-30'),(113,'2017-05-31'),(113,'2017-06-01'),(113,'2017-06-02'),(113,'2017-06-03'),(113,'2017-06-04'),(113,'2017-06-05'),(113,'2017-06-06'),(113,'2017-06-07'),(113,'2017-06-08'),(113,'2017-06-09'),(113,'2017-06-10'),(113,'2017-06-11'),(113,'2017-06-12'),(113,'2017-06-13'),(113,'2017-06-14'),(113,'2017-06-15'),(113,'2017-06-16'),(113,'2017-06-17'),(267,'2017-05-18'),(267,'2017-06-29'),(267,'2017-05-19'),(267,'2017-05-20'),(267,'2017-05-21'),(267,'2017-05-22'),(267,'2017-05-23'),(267,'2017-05-24'),(267,'2017-05-25'),(267,'2017-05-26'),(267,'2017-05-27'),(267,'2017-05-28'),(267,'2017-05-29'),(267,'2017-05-30'),(267,'2017-05-31'),(267,'2017-06-01'),(267,'2017-06-02'),(267,'2017-06-03'),(267,'2017-06-04'),(267,'2017-06-05'),(267,'2017-06-06'),(267,'2017-06-07'),(267,'2017-06-08'),(267,'2017-06-09'),(267,'2017-06-10'),(267,'2017-06-11'),(267,'2017-06-12'),(267,'2017-06-13'),(267,'2017-06-14'),(267,'2017-06-15'),(267,'2017-06-16'),(267,'2017-06-17'),(267,'2017-06-18'),(267,'2017-06-19'),(267,'2017-06-20'),(267,'2017-06-21'),(267,'2017-06-22'),(267,'2017-06-23'),(267,'2017-06-24'),(267,'2017-06-25'),(267,'2017-06-26'),(267,'2017-06-27'),(267,'2017-06-28'),(317,'2016-10-22'),(317,'2016-10-29'),(317,'2016-10-23'),(317,'2016-10-24'),(317,'2016-10-25'),(317,'2016-10-26'),(317,'2016-10-27'),(317,'2016-10-28'),(417,'2017-07-22'),(417,'2017-07-29'),(417,'2017-07-23'),(417,'2017-07-24'),(417,'2017-07-25'),(417,'2017-07-26'),(417,'2017-07-27'),(417,'2017-07-28'),(459,'2017-07-10'),(459,'2017-07-17'),(459,'2017-07-11'),(459,'2017-07-12'),(459,'2017-07-13'),(459,'2017-07-14'),(459,'2017-07-15'),(459,'2017-07-16'),(461,'2017-07-10'),(461,'2017-07-17'),(461,'2017-07-11'),(461,'2017-07-12'),(461,'2017-07-13'),(461,'2017-07-14'),(461,'2017-07-15'),(461,'2017-07-16'),(519,'2017-07-10'),(519,'2017-07-17'),(519,'2017-07-11'),(519,'2017-07-12'),(519,'2017-07-13'),(519,'2017-07-14'),(519,'2017-07-15'),(519,'2017-07-16');
/*!40000 ALTER TABLE `room_occupieddays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `imageURL` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j180x109do4umtn4ppnmepoyf` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (570,0,'http://alquilerparavacaciones.es/wp-content/uploads/2013/12/wifi-gratis.png','Wifi gratis'),(571,0,'http://www.helpbooking.com/FeatureImages/desayuno.png','Desayuno'),(572,0,'http://hotelvillaarce.es/wp-content/uploads/2014/08/parking_inv.png','Parking'),(573,0,'http://www.alyeskaaccommodations.com/uploads/1/3/5/5/13554230/5696594_orig.gif','Se permiten mascotas'),(574,0,'https://d30y9cdsu7xlg0.cloudfront.net/png/26971-200.png','Jacuzzi'),(575,0,'https://cdn4.iconfinder.com/data/icons/hotel-facilities-1/512/airconditioning-512.png','Aire acondicionado'),(576,0,'https://d30y9cdsu7xlg0.cloudfront.net/png/75699-200.png','Gimnasio'),(577,0,'https://d30y9cdsu7xlg0.cloudfront.net/png/6701-200.png','Caja fuerte'),(578,0,'https://cdn3.iconfinder.com/data/icons/communication-1/100/old_phone-512.png','Teléfono'),(579,0,'https://cdn0.iconfinder.com/data/icons/communication-18/512/tv-512.png','Televisión'),(580,0,'https://image.freepik.com/iconos-gratis/cable-de-senal-de-television-con-monitor_318-51360.jpg','Televisión por cable');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services_room`
--

DROP TABLE IF EXISTS `services_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services_room` (
  `services_id` int(11) NOT NULL,
  `rooms_id` int(11) NOT NULL,
  KEY `FK_skrnwcnpwfob5cyeldem6r3em` (`rooms_id`),
  KEY `FK_nttx2is4gqhbsw5rg9rif5in2` (`services_id`),
  CONSTRAINT `FK_nttx2is4gqhbsw5rg9rif5in2` FOREIGN KEY (`services_id`) REFERENCES `services` (`id`),
  CONSTRAINT `FK_skrnwcnpwfob5cyeldem6r3em` FOREIGN KEY (`rooms_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services_room`
--

LOCK TABLES `services_room` WRITE;
/*!40000 ALTER TABLE `services_room` DISABLE KEYS */;
INSERT INTO `services_room` VALUES (570,13),(570,14),(570,15),(570,16),(570,17),(570,18),(570,19),(570,20),(570,21),(570,22),(570,23),(570,24),(570,25),(570,26),(570,27),(570,28),(570,29),(570,30),(570,31),(570,32),(570,33),(570,34),(570,35),(570,36),(570,37),(570,38),(570,39),(570,40),(570,41),(570,42),(570,43),(570,44),(570,45),(570,46),(570,47),(570,48),(570,49),(570,50),(570,51),(570,52),(570,53),(570,54),(570,55),(570,56),(570,57),(570,58),(570,59),(570,60),(570,61),(570,62),(570,63),(570,64),(570,65),(570,66),(570,67),(570,68),(570,69),(570,70),(570,71),(570,72),(570,73),(570,74),(570,75),(570,76),(570,77),(570,78),(570,79),(570,80),(570,81),(570,82),(570,83),(570,84),(570,85),(570,86),(570,87),(570,88),(570,89),(570,90),(570,91),(570,92),(570,93),(570,94),(570,95),(570,96),(570,97),(570,98),(570,99),(570,100),(570,101),(570,102),(570,103),(570,104),(570,105),(570,106),(570,107),(570,108),(570,109),(570,110),(570,111),(570,112),(570,113),(570,114),(570,115),(570,116),(570,117),(570,118),(570,119),(570,120),(570,121),(570,122),(570,123),(570,124),(570,125),(570,126),(570,127),(570,128),(570,129),(570,130),(570,131),(570,132),(570,133),(570,134),(570,135),(570,136),(570,137),(570,138),(570,139),(570,140),(570,141),(570,142),(570,143),(570,144),(570,145),(570,146),(570,147),(570,148),(570,149),(570,150),(570,151),(570,152),(570,153),(570,154),(570,155),(570,156),(570,157),(570,158),(570,159),(570,160),(570,161),(570,162),(570,163),(570,164),(570,165),(570,166),(570,167),(570,168),(570,169),(570,170),(570,171),(570,172),(570,173),(570,174),(570,175),(570,176),(570,177),(570,178),(570,179),(570,180),(570,181),(570,182),(570,183),(570,184),(570,185),(570,186),(570,187),(570,188),(570,189),(570,190),(570,191),(570,192),(570,193),(570,194),(570,195),(570,196),(570,197),(570,198),(570,199),(570,200),(570,201),(570,202),(570,203),(570,204),(570,205),(570,206),(570,207),(570,208),(570,209),(570,210),(570,211),(570,212),(570,213),(570,218),(570,219),(570,220),(570,221),(570,222),(570,223),(570,224),(570,225),(570,226),(570,227),(570,228),(570,229),(570,230),(570,231),(570,232),(570,233),(570,234),(570,235),(570,236),(570,237),(570,238),(570,239),(570,240),(570,241),(570,242),(570,243),(570,244),(570,245),(570,246),(570,247),(570,248),(570,249),(570,250),(570,251),(570,252),(570,253),(570,254),(570,255),(570,256),(570,257),(570,258),(570,259),(570,260),(570,261),(570,262),(570,263),(570,264),(570,265),(570,266),(570,267),(570,268),(570,269),(570,270),(570,271),(570,272),(570,273),(570,274),(570,275),(570,276),(570,277),(570,278),(570,279),(570,280),(570,281),(570,282),(570,283),(570,284),(570,285),(570,286),(570,287),(570,288),(570,289),(570,290),(570,291),(570,292),(570,293),(570,294),(570,295),(570,296),(570,297),(570,298),(570,299),(570,300),(570,301),(570,302),(570,303),(570,304),(570,305),(570,306),(570,307),(570,308),(570,309),(570,310),(570,311),(570,312),(570,313),(570,314),(570,315),(570,316),(570,317),(570,318),(570,319),(570,320),(570,321),(570,322),(570,323),(570,324),(570,325),(570,326),(570,327),(570,328),(570,329),(570,330),(570,331),(570,332),(570,333),(570,334),(570,335),(570,336),(570,337),(570,338),(570,339),(570,340),(570,341),(570,342),(570,343),(570,344),(570,345),(570,346),(570,347),(570,348),(570,349),(570,350),(570,351),(570,352),(570,353),(570,354),(570,355),(570,356),(570,357),(570,358),(570,359),(570,360),(570,361),(570,362),(570,363),(570,364),(570,365),(570,366),(570,367),(570,368),(570,369),(570,370),(570,371),(570,372),(570,373),(570,374),(570,375),(570,376),(570,377),(570,378),(570,379),(570,380),(570,381),(570,382),(570,383),(570,384),(570,385),(570,386),(570,387),(570,388),(570,389),(570,390),(570,391),(570,392),(570,393),(570,394),(570,395),(570,396),(570,397),(570,398),(570,399),(570,400),(570,401),(570,402),(570,403),(570,404),(570,405),(570,406),(570,407),(570,408),(570,409),(570,410),(570,411),(570,412),(570,413),(570,414),(570,415),(570,416),(570,417),(570,418),(570,419),(570,420),(570,421),(570,422),(570,423),(570,424),(570,425),(570,426),(570,427),(570,428),(570,429),(570,430),(570,431),(570,432),(570,433),(570,434),(570,435),(570,436),(570,437),(570,438),(570,439),(570,440),(570,441),(570,442),(570,443),(570,444),(570,445),(570,446),(570,447),(570,448),(570,449),(570,450),(570,451),(570,452),(570,453),(570,454),(570,455),(570,456),(570,457),(570,458),(570,459),(570,460),(570,461),(570,462),(570,463),(570,464),(570,465),(570,466),(570,467),(570,470),(570,471),(570,472),(570,473),(570,474),(570,475),(570,476),(570,477),(570,478),(570,479),(570,480),(570,481),(570,482),(570,483),(570,484),(570,485),(570,486),(570,487),(570,488),(570,489),(570,490),(570,491),(570,492),(570,493),(570,494),(570,495),(570,496),(570,497),(570,498),(570,499),(570,500),(570,501),(570,502),(570,503),(570,504),(570,505),(570,506),(570,507),(570,508),(570,509),(570,510),(570,511),(570,512),(570,513),(570,514),(570,515),(570,516),(570,517),(570,518),(570,519),(570,520),(570,521),(570,522),(570,523),(570,524),(570,525),(570,526),(570,527),(570,528),(570,529),(570,530),(570,531),(570,532),(570,533),(570,534),(570,535),(570,536),(570,537),(570,538),(570,539),(570,540),(570,541),(570,542),(570,543),(570,544),(570,545),(570,546),(570,547),(570,548),(570,549),(570,550),(570,551),(570,552),(570,553),(570,554),(570,555),(570,556),(570,557),(570,558),(570,559),(570,560),(570,561),(570,562),(570,563),(570,564),(570,565),(570,566),(570,567),(570,568),(570,569),(571,13),(571,14),(571,15),(571,16),(571,17),(571,18),(571,19),(571,20),(571,21),(571,22),(571,23),(571,24),(571,25),(571,26),(571,27),(571,28),(571,29),(571,30),(571,31),(571,32),(571,33),(571,34),(571,35),(571,36),(571,37),(571,38),(571,39),(571,40),(571,41),(571,42),(571,43),(571,44),(571,45),(571,46),(571,47),(571,48),(571,49),(571,50),(571,51),(571,52),(571,53),(571,54),(571,55),(571,56),(571,57),(571,58),(571,59),(571,60),(571,61),(571,62),(571,63),(571,64),(571,65),(571,66),(571,67),(571,68),(571,69),(571,70),(571,71),(571,72),(571,73),(571,74),(571,75),(571,76),(571,77),(571,78),(571,79),(571,80),(571,81),(571,82),(571,83),(571,84),(571,85),(571,86),(571,87),(571,88),(571,89),(571,90),(571,91),(571,92),(571,93),(571,94),(571,95),(571,96),(571,97),(571,98),(571,99),(571,100),(571,101),(571,102),(571,103),(571,104),(571,105),(571,106),(571,107),(571,108),(571,109),(571,110),(571,111),(571,112),(571,113),(571,114),(571,115),(571,116),(571,117),(571,118),(571,119),(571,120),(571,121),(571,122),(571,123),(571,124),(571,125),(571,126),(571,127),(571,128),(571,129),(571,130),(571,131),(571,132),(571,133),(571,134),(571,135),(571,136),(571,137),(571,138),(571,139),(571,140),(571,141),(571,142),(571,143),(571,144),(571,145),(571,146),(571,147),(571,148),(571,149),(571,150),(571,151),(571,152),(571,153),(571,154),(571,155),(571,156),(571,157),(571,158),(571,159),(571,160),(571,161),(571,162),(571,163),(571,164),(571,165),(571,166),(571,167),(571,168),(571,169),(571,170),(571,171),(571,172),(571,173),(571,174),(571,175),(571,176),(571,177),(571,178),(571,179),(571,180),(571,181),(571,182),(571,183),(571,184),(571,185),(571,186),(571,187),(571,188),(571,189),(571,190),(571,191),(571,192),(571,193),(571,194),(571,195),(571,196),(571,197),(571,198),(571,199),(571,200),(571,201),(571,202),(571,203),(571,204),(571,205),(571,206),(571,207),(571,208),(571,209),(571,210),(571,211),(571,212),(571,213),(571,218),(571,219),(571,220),(571,221),(571,222),(571,223),(571,224),(571,225),(571,226),(571,227),(571,228),(571,229),(571,230),(571,231),(571,232),(571,233),(571,234),(571,235),(571,236),(571,237),(571,238),(571,239),(571,240),(571,241),(571,242),(571,243),(571,244),(571,245),(571,246),(571,247),(571,248),(571,249),(571,250),(571,251),(571,252),(571,253),(571,254),(571,255),(571,256),(571,257),(571,258),(571,259),(571,260),(571,261),(571,262),(571,263),(571,264),(571,265),(571,266),(571,267),(571,268),(571,269),(571,270),(571,271),(571,272),(571,273),(571,274),(571,275),(571,276),(571,277),(571,278),(571,279),(571,280),(571,281),(571,282),(571,283),(571,284),(571,285),(571,286),(571,287),(571,288),(571,289),(571,290),(571,291),(571,292),(571,293),(571,294),(571,295),(571,296),(571,297),(571,298),(571,299),(571,300),(571,301),(571,302),(571,303),(571,304),(571,305),(571,306),(571,307),(571,308),(571,309),(571,310),(571,311),(571,312),(571,313),(571,314),(571,315),(571,316),(571,317),(571,318),(571,319),(571,320),(571,321),(571,322),(571,323),(571,324),(571,325),(571,326),(571,327),(571,328),(571,329),(571,330),(571,331),(571,332),(571,333),(571,334),(571,335),(571,336),(571,337),(571,338),(571,339),(571,340),(571,341),(571,342),(571,343),(571,344),(571,345),(571,346),(571,347),(571,348),(571,349),(571,350),(571,351),(571,352),(571,353),(571,354),(571,355),(571,356),(571,357),(571,358),(571,359),(571,360),(571,361),(571,362),(571,363),(571,364),(571,365),(571,366),(571,367),(571,368),(571,369),(571,370),(571,371),(571,372),(571,373),(571,374),(571,375),(571,376),(571,377),(571,378),(571,379),(571,380),(571,381),(571,382),(571,383),(571,384),(571,385),(571,386),(571,387),(571,388),(571,389),(571,390),(571,391),(571,392),(571,393),(571,394),(571,395),(571,396),(571,397),(571,398),(571,399),(571,400),(571,401),(571,402),(571,403),(571,404),(571,405),(571,406),(571,407),(571,408),(571,409),(571,410),(571,411),(571,412),(571,413),(571,414),(571,415),(571,416),(571,417),(571,418),(571,419),(571,420),(571,421),(571,422),(571,423),(571,424),(571,425),(571,426),(571,427),(571,428),(571,429),(571,430),(571,431),(571,432),(571,433),(571,434),(571,435),(571,436),(571,437),(571,438),(571,439),(571,440),(571,441),(571,442),(571,443),(571,444),(571,445),(571,446),(571,447),(571,448),(571,449),(571,450),(571,451),(571,452),(571,453),(571,454),(571,455),(571,456),(571,457),(571,458),(571,459),(571,460),(571,461),(571,462),(571,463),(571,464),(571,465),(571,466),(571,467),(571,470),(571,471),(571,472),(571,473),(571,474),(571,475),(571,476),(571,477),(571,478),(571,479),(571,480),(571,481),(571,482),(571,483),(571,484),(571,485),(571,486),(571,487),(571,488),(571,489),(571,490),(571,491),(571,492),(571,493),(571,494),(571,495),(571,496),(571,497),(571,498),(571,499),(571,500),(571,501),(571,502),(571,503),(571,504),(571,505),(571,506),(571,507),(571,508),(571,509),(571,510),(571,511),(571,512),(571,513),(571,514),(571,515),(571,516),(571,517),(571,518),(571,519),(571,520),(571,521),(571,522),(571,523),(571,524),(571,525),(571,526),(571,527),(571,528),(571,529),(571,530),(571,531),(571,532),(571,533),(571,534),(571,535),(571,536),(571,537),(571,538),(571,539),(571,540),(571,541),(571,542),(571,543),(571,544),(571,545),(571,546),(571,547),(571,548),(571,549),(571,550),(571,551),(571,552),(571,553),(571,554),(571,555),(571,556),(571,557),(571,558),(571,559),(571,560),(571,561),(571,562),(571,563),(571,564),(571,565),(571,566),(571,567),(571,568),(571,569),(572,13),(572,14),(572,15),(572,16),(572,17),(572,18),(572,19),(572,20),(572,21),(572,22),(572,23),(572,24),(572,25),(572,26),(572,27),(572,28),(572,29),(572,30),(572,31),(572,32),(572,33),(572,34),(572,35),(572,36),(572,37),(572,38),(572,39),(572,40),(572,41),(572,42),(572,43),(572,44),(572,45),(572,46),(572,47),(572,48),(572,49),(572,50),(572,51),(572,52),(572,53),(572,54),(572,55),(572,56),(572,57),(572,58),(572,59),(572,60),(572,61),(572,62),(572,63),(572,64),(572,65),(572,66),(572,67),(572,68),(572,69),(572,70),(572,71),(572,72),(572,73),(572,74),(572,75),(572,76),(572,77),(572,78),(572,79),(572,80),(572,81),(572,82),(572,83),(572,84),(572,85),(572,86),(572,87),(572,88),(572,89),(572,90),(572,91),(572,92),(572,93),(572,94),(572,95),(572,96),(572,97),(572,98),(572,99),(572,100),(572,101),(572,102),(572,103),(572,104),(572,105),(572,106),(572,107),(572,108),(572,109),(572,110),(572,111),(572,112),(572,113),(572,114),(572,115),(572,116),(572,117),(572,118),(572,119),(572,120),(572,121),(572,122),(572,123),(572,124),(572,125),(572,126),(572,127),(572,128),(572,129),(572,130),(572,131),(572,132),(572,133),(572,134),(572,135),(572,136),(572,137),(572,138),(572,139),(572,140),(572,141),(572,142),(572,143),(572,144),(572,145),(572,146),(572,147),(572,148),(572,149),(572,150),(572,151),(572,152),(572,153),(572,154),(572,155),(572,156),(572,157),(572,158),(572,159),(572,160),(572,161),(572,162),(572,163),(572,164),(572,165),(572,166),(572,167),(572,168),(572,169),(572,170),(572,171),(572,172),(572,173),(572,174),(572,175),(572,176),(572,177),(572,178),(572,179),(572,180),(572,181),(572,182),(572,183),(572,184),(572,185),(572,186),(572,187),(572,188),(572,189),(572,190),(572,191),(572,192),(572,193),(572,194),(572,195),(572,196),(572,197),(572,198),(572,199),(572,200),(572,201),(572,202),(572,203),(572,204),(572,205),(572,206),(572,207),(572,208),(572,209),(572,210),(572,211),(572,212),(572,213),(572,218),(572,219),(572,220),(572,221),(572,222),(572,223),(572,224),(572,225),(572,226),(572,227),(572,228),(572,229),(572,230),(572,231),(572,232),(572,233),(572,234),(572,235),(572,236),(572,237),(572,238),(572,239),(572,240),(572,241),(572,242),(572,243),(572,244),(572,245),(572,246),(572,247),(572,248),(572,249),(572,250),(572,251),(572,252),(572,253),(572,254),(572,255),(572,256),(572,257),(572,258),(572,259),(572,260),(572,261),(572,262),(572,263),(572,264),(572,265),(572,266),(572,267),(572,268),(572,269),(572,270),(572,271),(572,272),(572,273),(572,274),(572,275),(572,276),(572,277),(572,278),(572,279),(572,280),(572,281),(572,282),(572,283),(572,284),(572,285),(572,286),(572,287),(572,288),(572,289),(572,290),(572,291),(572,292),(572,293),(572,294),(572,295),(572,296),(572,297),(572,298),(572,299),(572,300),(572,301),(572,302),(572,303),(572,304),(572,305),(572,306),(572,307),(572,308),(572,309),(572,310),(572,311),(572,312),(572,313),(572,314),(572,315),(572,316),(572,317),(572,318),(572,319),(572,320),(572,321),(572,322),(572,323),(572,324),(572,325),(572,326),(572,327),(572,328),(572,329),(572,330),(572,331),(572,332),(572,333),(572,334),(572,335),(572,336),(572,337),(572,338),(572,339),(572,340),(572,341),(572,342),(572,343),(572,344),(572,345),(572,346),(572,347),(572,348),(572,349),(572,350),(572,351),(572,352),(572,353),(572,354),(572,355),(572,356),(572,357),(572,358),(572,359),(572,360),(572,361),(572,362),(572,363),(572,364),(572,365),(572,366),(572,367),(572,368),(572,369),(572,370),(572,371),(572,372),(572,373),(572,374),(572,375),(572,376),(572,377),(572,378),(572,379),(572,380),(572,381),(572,382),(572,383),(572,384),(572,385),(572,386),(572,387),(572,388),(572,389),(572,390),(572,391),(572,392),(572,393),(572,394),(572,395),(572,396),(572,397),(572,398),(572,399),(572,400),(572,401),(572,402),(572,403),(572,404),(572,405),(572,406),(572,407),(572,408),(572,409),(572,410),(572,411),(572,412),(572,413),(572,414),(572,415),(572,416),(572,417),(572,418),(572,419),(572,420),(572,421),(572,422),(572,423),(572,424),(572,425),(572,426),(572,427),(572,428),(572,429),(572,430),(572,431),(572,432),(572,433),(572,434),(572,435),(572,436),(572,437),(572,438),(572,439),(572,440),(572,441),(572,442),(572,443),(572,444),(572,445),(572,446),(572,447),(572,448),(572,449),(572,450),(572,451),(572,452),(572,453),(572,454),(572,455),(572,456),(572,457),(572,458),(572,459),(572,460),(572,461),(572,462),(572,463),(572,464),(572,465),(572,466),(572,467),(572,470),(572,471),(572,472),(572,473),(572,474),(572,475),(572,476),(572,477),(572,478),(572,479),(572,480),(572,481),(572,482),(572,483),(572,484),(572,485),(572,486),(572,487),(572,488),(572,489),(572,490),(572,491),(572,492),(572,493),(572,494),(572,495),(572,496),(572,497),(572,498),(572,499),(572,500),(572,501),(572,502),(572,503),(572,504),(572,505),(572,506),(572,507),(572,508),(572,509),(572,510),(572,511),(572,512),(572,513),(572,514),(572,515),(572,516),(572,517),(572,518),(572,519),(572,520),(572,521),(572,522),(572,523),(572,524),(572,525),(572,526),(572,527),(572,528),(572,529),(572,530),(572,531),(572,532),(572,533),(572,534),(572,535),(572,536),(572,537),(572,538),(572,539),(572,540),(572,541),(572,542),(572,543),(572,544),(572,545),(572,546),(572,547),(572,548),(572,549),(572,550),(572,551),(572,552),(572,553),(572,554),(572,555),(572,556),(572,557),(572,558),(572,559),(572,560),(572,561),(572,562),(572,563),(572,564),(572,565),(572,566),(572,567),(572,568),(572,569),(573,13),(573,14),(573,15),(573,16),(573,17),(573,18),(573,19),(573,20),(573,21),(573,22),(573,23),(573,24),(573,25),(573,26),(573,27),(573,28),(573,29),(573,30),(573,31),(573,32),(573,33),(573,34),(573,35),(573,36),(573,37),(573,38),(573,39),(573,40),(573,41),(573,42),(573,43),(573,44),(573,45),(573,46),(573,47),(573,48),(573,49),(573,50),(573,51),(573,52),(573,53),(573,54),(573,55),(573,56),(573,57),(573,58),(573,59),(573,60),(573,61),(573,62),(573,63),(573,64),(573,65),(573,66),(573,67),(573,68),(573,69),(573,70),(573,71),(573,72),(573,73),(573,74),(573,75),(573,76),(573,77),(573,78),(573,79),(573,80),(573,81),(573,82),(573,83),(573,84),(573,85),(573,86),(573,87),(573,88),(573,89),(573,90),(573,91),(573,92),(573,93),(573,94),(573,95),(573,96),(573,97),(573,98),(573,99),(573,100),(573,101),(573,102),(573,103),(573,104),(573,105),(573,106),(573,107),(573,108),(573,109),(573,110),(573,111),(573,112),(573,113),(573,114),(573,115),(573,116),(573,117),(573,118),(573,119),(573,120),(573,121),(573,122),(573,123),(573,124),(573,125),(573,126),(573,127),(573,128),(573,129),(573,130),(573,131),(573,132),(573,133),(573,134),(573,135),(573,136),(573,137),(573,138),(573,139),(573,140),(573,141),(573,142),(573,143),(573,144),(573,145),(573,146),(573,147),(573,148),(573,149),(573,150),(573,151),(573,152),(573,153),(573,154),(573,155),(573,156),(573,157),(573,158),(573,159),(573,160),(573,161),(573,162),(573,163),(573,164),(573,165),(573,166),(573,167),(573,168),(573,169),(573,170),(573,171),(573,172),(573,173),(573,174),(573,175),(573,176),(573,177),(573,178),(573,179),(573,180),(573,181),(573,182),(573,183),(573,184),(573,185),(573,186),(573,187),(573,188),(573,189),(573,190),(573,191),(573,192),(573,193),(573,194),(573,195),(573,196),(573,197),(573,198),(573,199),(573,200),(573,201),(573,202),(573,203),(573,204),(573,205),(573,206),(573,207),(573,208),(573,209),(573,210),(573,211),(573,212),(573,213),(573,218),(573,219),(573,220),(573,221),(573,222),(573,223),(573,224),(573,225),(573,226),(573,227),(573,228),(573,229),(573,230),(573,231),(573,232),(573,233),(573,234),(573,235),(573,236),(573,237),(573,238),(573,239),(573,240),(573,241),(573,242),(573,243),(573,244),(573,245),(573,246),(573,247),(573,248),(573,249),(573,250),(573,251),(573,252),(573,253),(573,254),(573,255),(573,256),(573,257),(573,258),(573,259),(573,260),(573,261),(573,262),(573,263),(573,264),(573,265),(573,266),(573,267),(573,268),(573,269),(573,270),(573,271),(573,272),(573,273),(573,274),(573,275),(573,276),(573,277),(573,278),(573,279),(573,280),(573,281),(573,282),(573,283),(573,284),(573,285),(573,286),(573,287),(573,288),(573,289),(573,290),(573,291),(573,292),(573,293),(573,294),(573,295),(573,296),(573,297),(573,298),(573,299),(573,300),(573,301),(573,302),(573,303),(573,304),(573,305),(573,306),(573,307),(573,308),(573,309),(573,310),(573,311),(573,312),(573,313),(573,314),(573,315),(573,316),(573,317),(573,318),(573,319),(573,320),(573,321),(573,322),(573,323),(573,324),(573,325),(573,326),(573,327),(573,328),(573,329),(573,330),(573,331),(573,332),(573,333),(573,334),(573,335),(573,336),(573,337),(573,338),(573,339),(573,340),(573,341),(573,342),(573,343),(573,344),(573,345),(573,346),(573,347),(573,348),(573,349),(573,350),(573,351),(573,352),(573,353),(573,354),(573,355),(573,356),(573,357),(573,358),(573,359),(573,360),(573,361),(573,362),(573,363),(573,364),(573,365),(573,366),(573,367),(573,368),(573,369),(573,370),(573,371),(573,372),(573,373),(573,374),(573,375),(573,376),(573,377),(573,378),(573,379),(573,380),(573,381),(573,382),(573,383),(573,384),(573,385),(573,386),(573,387),(573,388),(573,389),(573,390),(573,391),(573,392),(573,393),(573,394),(573,395),(573,396),(573,397),(573,398),(573,399),(573,400),(573,401),(573,402),(573,403),(573,404),(573,405),(573,406),(573,407),(573,408),(573,409),(573,410),(573,411),(573,412),(573,413),(573,414),(573,415),(573,416),(573,417),(573,418),(573,419),(573,420),(573,421),(573,422),(573,423),(573,424),(573,425),(573,426),(573,427),(573,428),(573,429),(573,430),(573,431),(573,432),(573,433),(573,434),(573,435),(573,436),(573,437),(573,438),(573,439),(573,440),(573,441),(573,442),(573,443),(573,444),(573,445),(573,446),(573,447),(573,448),(573,449),(573,450),(573,451),(573,452),(573,453),(573,454),(573,455),(573,456),(573,457),(573,458),(573,459),(573,460),(573,461),(573,462),(573,463),(573,464),(573,465),(573,466),(573,467),(573,470),(573,471),(573,472),(573,473),(573,474),(573,475),(573,476),(573,477),(573,478),(573,479),(573,480),(573,481),(573,482),(573,483),(573,484),(573,485),(573,486),(573,487),(573,488),(573,489),(573,490),(573,491),(573,492),(573,493),(573,494),(573,495),(573,496),(573,497),(573,498),(573,499),(573,500),(573,501),(573,502),(573,503),(573,504),(573,505),(573,506),(573,507),(573,508),(573,509),(573,510),(573,511),(573,512),(573,513),(573,514),(573,515),(573,516),(573,517),(573,518),(573,519),(573,520),(573,521),(573,522),(573,523),(573,524),(573,525),(573,526),(573,527),(573,528),(573,529),(573,530),(573,531),(573,532),(573,533),(573,534),(573,535),(573,536),(573,537),(573,538),(573,539),(573,540),(573,541),(573,542),(573,543),(573,544),(573,545),(573,546),(573,547),(573,548),(573,549),(573,550),(573,551),(573,552),(573,553),(573,554),(573,555),(573,556),(573,557),(573,558),(573,559),(573,560),(573,561),(573,562),(573,563),(573,564),(573,565),(573,566),(573,567),(573,568),(573,569),(574,13),(574,14),(574,15),(574,16),(574,17),(574,18),(574,19),(574,20),(574,21),(574,22),(574,23),(574,24),(574,25),(574,26),(574,27),(574,28),(574,29),(574,30),(574,31),(574,32),(574,33),(574,34),(574,35),(574,36),(574,37),(574,38),(574,39),(574,40),(574,41),(574,42),(574,43),(574,44),(574,45),(574,46),(574,47),(574,48),(574,49),(574,50),(574,51),(574,52),(574,53),(574,54),(574,55),(574,56),(574,57),(574,58),(574,59),(574,60),(574,61),(574,62),(574,63),(574,64),(574,65),(574,66),(574,67),(574,68),(574,69),(574,70),(574,71),(574,72),(574,73),(574,74),(574,75),(574,76),(574,77),(574,78),(574,79),(574,80),(574,81),(574,82),(574,83),(574,84),(574,85),(574,86),(574,87),(574,88),(574,89),(574,90),(574,91),(574,92),(574,93),(574,94),(574,95),(574,96),(574,97),(574,98),(574,99),(574,100),(574,101),(574,102),(574,103),(574,104),(574,105),(574,106),(574,107),(574,108),(574,109),(574,110),(574,111),(574,112),(574,113),(574,114),(574,115),(574,116),(574,117),(574,118),(574,119),(574,120),(574,121),(574,122),(574,123),(574,124),(574,125),(574,126),(574,127),(574,128),(574,129),(574,130),(574,131),(574,132),(574,133),(574,134),(574,135),(574,136),(574,137),(574,138),(574,139),(574,140),(574,141),(574,142),(574,143),(574,144),(574,145),(574,146),(574,147),(574,148),(574,149),(574,150),(574,151),(574,152),(574,153),(574,154),(574,155),(574,156),(574,157),(574,158),(574,159),(574,160),(574,161),(574,162),(574,163),(574,164),(574,165),(574,166),(574,167),(574,168),(574,169),(574,170),(574,171),(574,172),(574,173),(574,174),(574,175),(574,176),(574,177),(574,178),(574,179),(574,180),(574,181),(574,182),(574,183),(574,184),(574,185),(574,186),(574,187),(574,188),(574,189),(574,190),(574,191),(574,192),(574,193),(574,194),(574,195),(574,196),(574,197),(574,198),(574,199),(574,200),(574,201),(574,202),(574,203),(574,204),(574,205),(574,206),(574,207),(574,208),(574,209),(574,210),(574,211),(574,212),(574,213),(574,218),(574,219),(574,220),(574,221),(574,222),(574,223),(574,224),(574,225),(574,226),(574,227),(574,228),(574,229),(574,230),(574,231),(574,232),(574,233),(574,234),(574,235),(574,236),(574,237),(574,238),(574,239),(574,240),(574,241),(574,242),(574,243),(574,244),(574,245),(574,246),(574,247),(574,248),(574,249),(574,250),(574,251),(574,252),(574,253),(574,254),(574,255),(574,256),(574,257),(574,258),(574,259),(574,260),(574,261),(574,262),(574,263),(574,264),(574,265),(574,266),(574,267),(574,268),(574,269),(574,270),(574,271),(574,272),(574,273),(574,274),(574,275),(574,276),(574,277),(574,278),(574,279),(574,280),(574,281),(574,282),(574,283),(574,284),(574,285),(574,286),(574,287),(574,288),(574,289),(574,290),(574,291),(574,292),(574,293),(574,294),(574,295),(574,296),(574,297),(574,298),(574,299),(574,300),(574,301),(574,302),(574,303),(574,304),(574,305),(574,306),(574,307),(574,308),(574,309),(574,310),(574,311),(574,312),(574,313),(574,314),(574,315),(574,316),(574,317),(574,318),(574,319),(574,320),(574,321),(574,322),(574,323),(574,324),(574,325),(574,326),(574,327),(574,328),(574,329),(574,330),(574,331),(574,332),(574,333),(574,334),(574,335),(574,336),(574,337),(574,338),(574,339),(574,340),(574,341),(574,342),(574,343),(574,344),(574,345),(574,346),(574,347),(574,348),(574,349),(574,350),(574,351),(574,352),(574,353),(574,354),(574,355),(574,356),(574,357),(574,358),(574,359),(574,360),(574,361),(574,362),(574,363),(574,364),(574,365),(574,366),(574,367),(574,368),(574,369),(574,370),(574,371),(574,372),(574,373),(574,374),(574,375),(574,376),(574,377),(574,378),(574,379),(574,380),(574,381),(574,382),(574,383),(574,384),(574,385),(574,386),(574,387),(574,388),(574,389),(574,390),(574,391),(574,392),(574,393),(574,394),(574,395),(574,396),(574,397),(574,398),(574,399),(574,400),(574,401),(574,402),(574,403),(574,404),(574,405),(574,406),(574,407),(574,408),(574,409),(574,410),(574,411),(574,412),(574,413),(574,414),(574,415),(574,416),(574,417),(574,418),(574,419),(574,420),(574,421),(574,422),(574,423),(574,424),(574,425),(574,426),(574,427),(574,428),(574,429),(574,430),(574,431),(574,432),(574,433),(574,434),(574,435),(574,436),(574,437),(574,438),(574,439),(574,440),(574,441),(574,442),(574,443),(574,444),(574,445),(574,446),(574,447),(574,448),(574,449),(574,450),(574,451),(574,452),(574,453),(574,454),(574,455),(574,456),(574,457),(574,458),(574,459),(574,460),(574,461),(574,462),(574,463),(574,464),(574,465),(574,466),(574,467),(574,470),(574,471),(574,472),(574,473),(574,474),(574,475),(574,476),(574,477),(574,478),(574,479),(574,480),(574,481),(574,482),(574,483),(574,484),(574,485),(574,486),(574,487),(574,488),(574,489),(574,490),(574,491),(574,492),(574,493),(574,494),(574,495),(574,496),(574,497),(574,498),(574,499),(574,500),(574,501),(574,502),(574,503),(574,504),(574,505),(574,506),(574,507),(574,508),(574,509),(574,510),(574,511),(574,512),(574,513),(574,514),(574,515),(574,516),(574,517),(574,518),(574,519),(574,520),(574,521),(574,522),(574,523),(574,524),(574,525),(574,526),(574,527),(574,528),(574,529),(574,530),(574,531),(574,532),(574,533),(574,534),(574,535),(574,536),(574,537),(574,538),(574,539),(574,540),(574,541),(574,542),(574,543),(574,544),(574,545),(574,546),(574,547),(574,548),(574,549),(574,550),(574,551),(574,552),(574,553),(574,554),(574,555),(574,556),(574,557),(574,558),(574,559),(574,560),(574,561),(574,562),(574,563),(574,564),(574,565),(574,566),(574,567),(574,568),(574,569);
/*!40000 ALTER TABLE `services_room` ENABLE KEYS */;
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
INSERT INTO `useraccount` VALUES (9,0,'c240642ddef994358c96da82c0361a58','manager1'),(214,0,'8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(585,0,'ebad64149cc767ba26ef069819279fd5','worker1'),(587,0,'01925b7892e2ca194993afdd20a7c761','worker2'),(589,0,'b504f5a746deafd3e78b9c85c20ca653','worker3'),(591,0,'221393135bcf2a755b8ac9da40365c67','worker4'),(593,0,'6a21db8adb7572cb5d1b15dfaa4469e4','worker5'),(595,0,'16b7e21c964d2e0b98a5a4712a0df241','worker6'),(597,0,'e2954e1abf0b7138d9dd7a3c13f5aaec','worker7'),(618,0,'a165dd3c2e98d5d607181d0b87a4c66b','client1'),(620,0,'2c66045d4e4a90814ce9280272e510ec','client2'),(622,0,'c27af3f6460eb10979adb366fc7f6856','client3'),(624,0,'de285ec98e0f83211da217a4e1c5923e','client4'),(626,0,'7e8670fef6f81f377fe6e162ea1077e5','client5'),(628,0,'9dc5beb30a97d0e3ad847db4774c6ac9','client6'),(630,0,'ab5b1c502ce04b8960af53d4e05d651b','client7'),(670,0,'21232f297a57a5a743894a0e4a801fc3','admin');
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
INSERT INTO `useraccount_authorities` VALUES (9,'MANAGER'),(214,'MANAGER'),(585,'WORKER'),(587,'WORKER'),(589,'WORKER'),(591,'WORKER'),(593,'WORKER'),(595,'WORKER'),(597,'WORKER'),(618,'CLIENT'),(620,'CLIENT'),(622,'CLIENT'),(624,'CLIENT'),(626,'CLIENT'),(628,'CLIENT'),(630,'CLIENT'),(670,'ADMINISTRATOR');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAdress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `accountNumber` varchar(255) DEFAULT NULL,
  `brandName` int(11) DEFAULT NULL,
  `cVV` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e7rogu7ammlrb70qkl4uouhs6` (`userAccount_id`),
  KEY `FK_94ek88ldlm6iyb9ydi3opiwai` (`hotel_id`),
  CONSTRAINT `FK_e7rogu7ammlrb70qkl4uouhs6` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_94ek88ldlm6iyb9ydi3opiwai` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (586,0,'torri@us.es','Sergio','698541236','calle Lucena','Torrijos Garcia',585,'4985474478693946',1,216,1,2019,'Tarjeta2',2,11),(588,0,'cande10@us.es','Luis','698545236','calle Rossi','Candelario Luna',587,'4149390898022867',0,396,1,2019,'Tarjeta1',25,11),(590,0,'p90@us.es','Alex','678541236','calle Cancer','Garcia Vera',589,'4149390898022867',0,396,1,2019,'Tarjeta1',2,11),(592,0,'retraso@us.es','Angel','698541666','calle Cazi','Pica Codigo',591,'4985474478693946',1,216,1,2019,'Tarjeta2',2,11),(594,0,'voice@us.es','Voice','692241236','calle LA voz','Callate La Boca',593,'4985474478693946',1,216,1,2019,'Tarjeta2',22,216),(596,0,'red@us.es','Burgo','698571236','calle REd vil','Corta Hacha',595,'4985474478693946',1,216,1,2019,'Tarjeta2',27,216),(598,0,'orentiacion@us.es','Manolo','698541237','calle Rip','Ni idea',597,'4985474478693946',1,216,1,2019,'Tarjeta2',25,468);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-31  2:59:34

commit;