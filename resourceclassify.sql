/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : syj

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-10-07 22:26:27
*/

SET FOREIGN_KEY_CHECKS=0;

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
