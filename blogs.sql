/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : syj

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-10-07 22:25:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blogs`
-- ----------------------------
DROP TABLE IF EXISTS `blogs`;
CREATE TABLE `blogs` (
  `Blogsid` int(200) NOT NULL AUTO_INCREMENT,
  `blogsName` varchar(200) DEFAULT NULL,
  `blogsContext` text,
  `blogsStatus` int(2) DEFAULT NULL,
  `blogsClick` int(100) DEFAULT NULL,
  `blogsDate` datetime DEFAULT NULL,
  `blogsClassifyId` int(200) DEFAULT NULL,
  `userId` int(200) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Blogsid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogs
-- ----------------------------
INSERT INTO `blogs` VALUES ('1', '1', '1', '1', '1', '2017-09-30 10:28:19', '1', '1', '1', 'fs');
INSERT INTO `blogs` VALUES ('2', '2', '2', '2', '2', '2017-10-03 11:28:51', '2', '1', '1', 'dsad');
INSERT INTO `blogs` VALUES ('3', '3', '3', '1', '5', '2017-10-04 11:29:17', '1', '1', '1', 'dsa');
INSERT INTO `blogs` VALUES ('4', '4', '5', '2', '10', '2017-10-02 11:30:12', '2', '1', '1', 'dasd');
INSERT INTO `blogs` VALUES ('5', '5', '6', '1', '3', '2017-10-08 11:30:16', '1', '1', '1', 'dsad');
INSERT INTO `blogs` VALUES ('6', '6', '7', '1', '1', '2017-10-18 11:30:22', '2', '1', '1', 'das');
INSERT INTO `blogs` VALUES ('7', '7', '8', '2', '1', '2017-10-12 17:33:53', '1', '1', '1', 'dsa');
INSERT INTO `blogs` VALUES ('8', '8', '9', '1', '99', '2017-10-17 17:33:57', '2', '1', '1', 'dsa');
