/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.13-log : Database - sign
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sign` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sign`;

/*Table structure for table `allow_sign` */

DROP TABLE IF EXISTS `allow_sign`;

CREATE TABLE `allow_sign` (
  `alow_sign_id` int(10) NOT NULL AUTO_INCREMENT,
  `course_id` int(10) DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  `target` int(11) DEFAULT NULL,
  PRIMARY KEY (`alow_sign_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `allow_sign_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `course_id` int(10) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) DEFAULT NULL,
  `teacher_id` int(10) DEFAULT NULL,
  `course_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `signname` */

DROP TABLE IF EXISTS `signname`;

CREATE TABLE `signname` (
  `sign_id` int(32) NOT NULL AUTO_INCREMENT,
  `sign_time` datetime DEFAULT NULL,
  `sign_state` varchar(10) DEFAULT NULL,
  `course_id` int(10) DEFAULT NULL,
  `student_id` int(10) DEFAULT NULL,
  `allow_sign_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`sign_id`),
  KEY `course_id` (`course_id`),
  KEY `student_id` (`student_id`),
  KEY `allow_sign_id` (`allow_sign_id`),
  CONSTRAINT `signname_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `signname_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `signname_ibfk_3` FOREIGN KEY (`allow_sign_id`) REFERENCES `allow_sign` (`alow_sign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(10) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(20) DEFAULT NULL,
  `student_sex` varchar(5) DEFAULT NULL,
  `student_num` varchar(20) DEFAULT NULL,
  `student_username` varchar(12) DEFAULT NULL,
  `student_password` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `username` (`student_username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `student_course` */

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
  `student_course_id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` int(10) DEFAULT NULL,
  `course_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`student_course_id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `teacher_id` int(10) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(20) DEFAULT NULL,
  `teacher_username` varchar(12) DEFAULT NULL,
  `teacher_password` varchar(12) DEFAULT NULL,
  `teacher_wifimac` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `teacher` (`teacher_username`)
) ENGINE=InnoDB AUTO_INCREMENT=3271 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
