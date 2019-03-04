/*
SQLyog Enterprise - MySQL GUI v6.14
MySQL - 5.5.62 : Database - rlzy2019
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `rlzy2019`;

USE `rlzy2019`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `privilege` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  UNIQUE KEY `userid` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`privilege`,`email`) values (1,'向问天','1234','user:addaaa','linghuchong@qq.com'),(2,'张三丰','1234','user:add','zhangsanfeng@qq.com'),(3,'令狐冲','1234','user:add','linghuchong@qq.com'),(4,'任我行','1234','user:addaaa','linghuchong@qq.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
