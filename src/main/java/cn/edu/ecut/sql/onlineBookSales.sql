/*
SQLyog Ultimate
MySQL - 5.6.27 : Database - onlinebooksales
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`onlinebooksales` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `onlinebooksales`;

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `bookID` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(20) DEFAULT NULL,
  `price` double NOT NULL,
  `picture` mediumblob NOT NULL,
  `nubmber` int(11) NOT NULL DEFAULT '1',
  `introduction` text NOT NULL,
  `category` varchar(12) NOT NULL,
  `sno` int(11) DEFAULT NULL,
  `author` varchar(20) NOT NULL,
  PRIMARY KEY (`bookID`),
  UNIQUE KEY `bookName` (`bookName`),
  KEY `sno` (`sno`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `sales` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `customers` */

DROP TABLE IF EXISTS `customers`;

CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `managers` */

DROP TABLE IF EXISTS `managers`;

CREATE TABLE `managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bno` int(11) DEFAULT NULL,
  `cno` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `messageTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `bno` (`bno`),
  KEY `cno` (`cno`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `books` (`bookID`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bno` int(11) DEFAULT NULL,
  `cno` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT '1',
  `orderTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `bno` (`bno`),
  KEY `cno` (`cno`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `books` (`bookID`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sales` */

DROP TABLE IF EXISTS `sales`;

CREATE TABLE `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `storges` */

DROP TABLE IF EXISTS `storges`;

CREATE TABLE `storges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` int(11) DEFAULT NULL,
  `storgeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `number` int(11) NOT NULL DEFAULT '1',
  `storgePrice` double NOT NULL,
  `bno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sno` (`sno`),
  KEY `bno` (`bno`),
  CONSTRAINT `storges_ibfk_2` FOREIGN KEY (`sno`) REFERENCES `sales` (`id`),
  CONSTRAINT `storges_ibfk_3` FOREIGN KEY (`bno`) REFERENCES `books` (`bookID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
