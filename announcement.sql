/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : syj

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-10-07 22:25:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `Announcementid` int(20) NOT NULL AUTO_INCREMENT,
  `AnnouncementTitle` varchar(30) DEFAULT NULL,
  `AnnouncementContext` text,
  `announcementDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Announcementid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('1', '1111111111', '1111111111111111111111111111111111111111111', '2017-10-03 19:59:40');
INSERT INTO `announcement` VALUES ('2', '2222222222222222', '22222222222222222222222', '2017-10-06 19:59:43');
INSERT INTO `announcement` VALUES ('3', '333333333333333333333333333', '3333333333', '2017-10-18 19:59:46');
INSERT INTO `announcement` VALUES ('4', '4444444444444', '4444444444444444', '2017-10-19 19:59:50');

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

-- ----------------------------
-- Table structure for `blogsclassify`
-- ----------------------------
DROP TABLE IF EXISTS `blogsclassify`;
CREATE TABLE `blogsclassify` (
  `blogsClassifyId` int(200) NOT NULL AUTO_INCREMENT,
  `blogsClassifyName` varchar(200) DEFAULT NULL,
  `blogsClassifyClick` int(100) DEFAULT NULL,
  PRIMARY KEY (`blogsClassifyId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogsclassify
-- ----------------------------
INSERT INTO `blogsclassify` VALUES ('1', '测试1', '1');
INSERT INTO `blogsclassify` VALUES ('2', '测试2', '3');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` int(200) NOT NULL AUTO_INCREMENT,
  `commentName` varchar(200) DEFAULT NULL,
  `userId` int(200) DEFAULT NULL,
  `commentDate` datetime DEFAULT NULL,
  `blogsid` int(200) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `pitcher`
-- ----------------------------
DROP TABLE IF EXISTS `pitcher`;
CREATE TABLE `pitcher` (
  `pitcherId` int(200) NOT NULL AUTO_INCREMENT,
  `pitcherUrl` varchar(200) DEFAULT NULL,
  `blogsId` int(200) DEFAULT NULL,
  PRIMARY KEY (`pitcherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pitcher
-- ----------------------------

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resouceId` int(200) NOT NULL AUTO_INCREMENT,
  `resouceName` varchar(100) DEFAULT NULL,
  `resouceUrl` varchar(100) DEFAULT NULL,
  `resouceClick` int(50) DEFAULT NULL,
  `userid` int(200) DEFAULT NULL,
  PRIMARY KEY (`resouceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for `resourceclassify`
-- ----------------------------
DROP TABLE IF EXISTS `resourceclassify`;
CREATE TABLE `resourceclassify` (
  `ResourceClassifyid` int(200) NOT NULL AUTO_INCREMENT,
  `resourceClassifyName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ResourceClassifyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resourceclassify
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(200) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `integral` int(20) DEFAULT NULL,
  `userstatus` int(2) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', null, '2388386839@qq.com', '1', '1');
INSERT INTO `user` VALUES ('10', '111111', '111111', '2388386839@qq.com', '1', '1');
INSERT INTO `user` VALUES ('11', '1111111', '111111', '2388386839@qq.com', '1', '1');
