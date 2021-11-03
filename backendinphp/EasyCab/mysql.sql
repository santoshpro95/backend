/*
SQLyog Enterprise - MySQL GUI v8.05 
MySQL - 5.6.21 : Database - cab
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`cab` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cab`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `fare_id` bigint(20) NOT NULL,
  `froms` varchar(100) NOT NULL,
  `too` varchar(100) NOT NULL,
  `total_km` varchar(20) NOT NULL,
  `total_fare` varchar(20) NOT NULL,
  `booking_date` datetime NOT NULL,
  `payment_status` varchar(50) DEFAULT NULL,
  `order_status` enum('Pending','Reject','Approve') DEFAULT 'Pending',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `book` */

insert  into `book`(`id`,`userid`,`fare_id`,`froms`,`too`,`total_km`,`total_fare`,`booking_date`,`payment_status`,`order_status`) values (1,36,2,'meerut','delhi','66','300','2016-03-18 10:16:48',NULL,'Pending'),(2,4,3,'Kolkata','Delhi','1000','400','2016-03-19 08:44:52',NULL,'Pending'),(3,4,4,'Mohan Nagar','Pitampura','25','455','2016-03-19 09:23:48',NULL,'Pending'),(4,4,3,'Kolkata','Delhi','1000','500','2016-03-19 09:24:49',NULL,'Pending'),(5,4,4,'Mohan Nagar','Pitampura','25','600','2016-03-19 09:30:55',NULL,'Pending'),(6,3,4,'Mohan Nagar','Pitampura','25','500','2016-03-19 09:35:05',NULL,'Pending'),(7,3,3,'Kolkata','Delhi','1000','10000','2016-03-19 09:36:15',NULL,'Pending');

/*Table structure for table `fare` */

DROP TABLE IF EXISTS `fare`;

CREATE TABLE `fare` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(10) DEFAULT NULL,
  `froms` varchar(255) DEFAULT NULL,
  `too` varchar(255) DEFAULT NULL,
  `total_km` varchar(255) DEFAULT NULL,
  `prize` varchar(255) DEFAULT NULL,
  `first_km` varchar(100) DEFAULT NULL,
  `rs` varchar(100) DEFAULT NULL,
  `adding_date` datetime DEFAULT NULL,
  `status` char(1) DEFAULT '0' COMMENT '0=> Pending, 1=>Publish , 2 => Sold',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `fare` */

insert  into `fare`(`id`,`userid`,`froms`,`too`,`total_km`,`prize`,`first_km`,`rs`,`adding_date`,`status`) values (2,3,'meerut','delhi','66','466','1','55','2016-03-18 11:25:46','0'),(3,4,'Kolkata','Delhi','1000','10000','1','50','2016-03-18 02:51:22','0'),(4,NULL,'Mohan Nagar','Pitampura','25','300','1','30','2016-03-19 08:40:48','0');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL,
  `propertyid` bigint(20) DEFAULT NULL,
  `card_type` varchar(255) DEFAULT NULL,
  `cardno` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `card_name` varchar(255) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`id`,`userid`,`propertyid`,`card_type`,`cardno`,`expiry_date`,`cvv`,`card_name`,`payment_date`) values (1,36,1,'debit','8788','23-03-2016','123','Mestro Card','2016-03-18 02:54:39'),(2,4,2,'credit','66665666','29-03-2024','455','Mestro Card','2016-03-19 08:45:27');

/*Table structure for table `register` */

DROP TABLE IF EXISTS `register`;

CREATE TABLE `register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL DEFAULT '',
  `lname` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(100) NOT NULL DEFAULT '',
  `contact` varchar(20) DEFAULT NULL,
  `password` varchar(25) NOT NULL DEFAULT '',
  `dob` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `pin` varchar(10) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `file_upload` varchar(255) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  `adding_date` datetime DEFAULT NULL,
  `emp_id` varchar(100) DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idIndex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `register` */

insert  into `register`(`id`,`fname`,`lname`,`email`,`contact`,`password`,`dob`,`address`,`pin`,`state`,`country`,`file_upload`,`user_type`,`adding_date`,`emp_id`,`bank_name`,`status`) values (1,'Admin','admin','admin@gmail.com','9878787885','admin','01-02-2016','','','','Indida','file_upload/10.jpg','admin','2016-02-20 03:10:05',NULL,NULL,'1'),(2,'Pawan','Kumar','pawan@gmail.com','98774455','pawan',NULL,NULL,NULL,NULL,NULL,NULL,'user','2016-03-19 08:25:56',NULL,NULL,'1'),(3,'Kamal','Kumar','kamal@gmail.com','5688786','kamal',NULL,NULL,NULL,NULL,NULL,NULL,'user','2016-03-19 08:26:49',NULL,NULL,'1'),(4,'Mohan','Kumar','mohan@gmail.com','987885555','mohan','11-03-1998','Jankapuri','100018','Delhi','India','file_upload/1.jpg','user','2016-03-19 08:27:36',NULL,NULL,'1'),(7,'Kishor','Kumar','kishor@gmail.com','8555785555','kishor',NULL,NULL,NULL,NULL,NULL,NULL,'user','2016-03-19 08:34:24',NULL,NULL,'1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
