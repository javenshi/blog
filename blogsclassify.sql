/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : syj

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-10-07 22:25:53
*/

SET FOREIGN_KEY_CHECKS=0;

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
