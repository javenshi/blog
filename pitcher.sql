/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : syj

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-10-07 22:26:09
*/

SET FOREIGN_KEY_CHECKS=0;

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
