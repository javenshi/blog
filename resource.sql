/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : syj

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-10-07 22:26:21
*/

SET FOREIGN_KEY_CHECKS=0;

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
