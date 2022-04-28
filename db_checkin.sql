/*
Navicat MySQL Data Transfer

Source Server         : pc
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : db_checkin

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-04-28 20:53:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `apply_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL,
  `during_time` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `emp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('1', '2022-04-07 16:11:17', '3', '我要结婚', '2', '0', '1');
INSERT INTO `apply` VALUES ('2', '2022-04-07 16:52:46', '2', '我老婆要生了', '4', '2', '1');
INSERT INTO `apply` VALUES ('4', '2022-04-08 09:41:27', '1', '哺乳', '5', '0', '1');
INSERT INTO `apply` VALUES ('6', '2022-04-07 21:03:32', '2', '我要去结婚！', '2', '2', '1');
INSERT INTO `apply` VALUES ('7', '2022-04-10 11:02:24', '2', '我要给孩子喂奶', '5', '2', '1');
INSERT INTO `apply` VALUES ('8', '2022-04-10 11:26:05', '5', '我要放年假', '1', '0', '1');
INSERT INTO `apply` VALUES ('17', '2022-04-10 16:00:00', '12', '132213', '4', '0', '2');
INSERT INTO `apply` VALUES ('18', '2022-04-04 16:00:00', '2', '我饿了', '5', '0', '1');
INSERT INTO `apply` VALUES ('23', '2022-04-03 16:00:00', '1', '啊的撒法发的', '5', '2', '1');
INSERT INTO `apply` VALUES ('24', '2022-04-03 16:00:00', '1', 'sfsdf sd', '5', '2', '1');
INSERT INTO `apply` VALUES ('25', '2022-03-28 16:00:00', '1', '123123123', '0', '2', '1');
INSERT INTO `apply` VALUES ('26', '2022-04-04 16:00:00', '1', '123123', '5', '2', '1');
INSERT INTO `apply` VALUES ('29', '2022-04-04 16:00:00', '1', '123', '0', '2', '1');

-- ----------------------------
-- Table structure for apply_record
-- ----------------------------
DROP TABLE IF EXISTS `apply_record`;
CREATE TABLE `apply_record` (
  `apply_record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `total_time` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`apply_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply_record
-- ----------------------------
INSERT INTO `apply_record` VALUES ('1', '1', '1', '1', '0');
INSERT INTO `apply_record` VALUES ('2', '1', '4', '2', '2');
INSERT INTO `apply_record` VALUES ('3', '1', '2', '2', '2');
INSERT INTO `apply_record` VALUES ('4', '1', '5', '2', '2');
INSERT INTO `apply_record` VALUES ('5', '1', '1', '5', '2');
INSERT INTO `apply_record` VALUES ('6', '1', '1', '3', '2');
INSERT INTO `apply_record` VALUES ('7', '1', '5', '2', '2');
INSERT INTO `apply_record` VALUES ('8', '2', '4', '12', '2');
INSERT INTO `apply_record` VALUES ('15', '1', '5', '1', '2');
INSERT INTO `apply_record` VALUES ('16', '1', '5', '1', '2');
INSERT INTO `apply_record` VALUES ('17', '1', '0', '1', '2');
INSERT INTO `apply_record` VALUES ('18', '1', '5', '1', '2');
INSERT INTO `apply_record` VALUES ('19', '1', '0', '1', '2');

-- ----------------------------
-- Table structure for check_record
-- ----------------------------
DROP TABLE IF EXISTS `check_record`;
CREATE TABLE `check_record` (
  `sign_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0：上班打卡\n1：下班打卡\n2：外出打卡\n3：返回打卡',
  `emp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_record
-- ----------------------------
INSERT INTO `check_record` VALUES ('1', '2022-04-06 08:06:54', '0', '1');
INSERT INTO `check_record` VALUES ('2', '2022-04-06 16:07:52', '1', '1');
INSERT INTO `check_record` VALUES ('3', '2022-04-04 16:08:02', '2', '1');
INSERT INTO `check_record` VALUES ('4', '2022-04-05 16:08:20', '3', '1');
INSERT INTO `check_record` VALUES ('5', '2022-04-06 17:18:30', '2', '1');
INSERT INTO `check_record` VALUES ('6', '2022-04-06 17:30:04', '2', '1');
INSERT INTO `check_record` VALUES ('7', '2022-04-06 17:31:34', '2', '1');
INSERT INTO `check_record` VALUES ('8', '2022-04-06 17:32:02', '1', '1');
INSERT INTO `check_record` VALUES ('9', '2022-04-06 21:49:26', '2', '1');
INSERT INTO `check_record` VALUES ('10', '2022-04-06 21:53:44', '3', '1');
INSERT INTO `check_record` VALUES ('11', '2022-04-09 21:01:03', '2', '1');
INSERT INTO `check_record` VALUES ('12', '2022-04-10 22:00:23', '2', '1');
INSERT INTO `check_record` VALUES ('13', '2022-04-14 00:24:28', '2', '1');
INSERT INTO `check_record` VALUES ('14', '2022-04-14 00:24:48', '0', '1');
INSERT INTO `check_record` VALUES ('15', '2022-04-14 00:25:06', '0', '1');
INSERT INTO `check_record` VALUES ('16', '2022-04-14 00:25:29', '0', '1');
INSERT INTO `check_record` VALUES ('17', '2022-04-14 00:25:32', '1', '1');
INSERT INTO `check_record` VALUES ('18', '2022-04-14 00:29:44', '0', '1');
INSERT INTO `check_record` VALUES ('19', '2022-04-14 00:29:50', '1', '1');
INSERT INTO `check_record` VALUES ('20', '2022-04-14 00:32:02', '0', '1');
INSERT INTO `check_record` VALUES ('21', '2022-04-14 00:32:04', '1', '1');
INSERT INTO `check_record` VALUES ('22', '2022-04-14 00:32:06', '2', '1');
INSERT INTO `check_record` VALUES ('23', '2022-04-14 22:50:32', '0', '2');
INSERT INTO `check_record` VALUES ('24', '2022-04-14 22:50:32', '1', '2');
INSERT INTO `check_record` VALUES ('25', '2022-04-14 22:50:33', '2', '2');
INSERT INTO `check_record` VALUES ('26', '2022-04-14 22:50:33', '3', '2');
INSERT INTO `check_record` VALUES ('27', '2022-04-14 22:50:34', '3', '2');
INSERT INTO `check_record` VALUES ('28', '2022-04-14 22:50:36', '3', '2');
INSERT INTO `check_record` VALUES ('29', '2022-04-14 22:50:38', '3', '2');
INSERT INTO `check_record` VALUES ('30', '2022-04-14 22:50:40', '3', '2');
INSERT INTO `check_record` VALUES ('31', '2022-04-14 22:50:40', '3', '2');
INSERT INTO `check_record` VALUES ('32', '2022-04-14 22:50:40', '3', '2');
INSERT INTO `check_record` VALUES ('33', '2022-04-14 22:50:49', '2', '2');
INSERT INTO `check_record` VALUES ('34', '2022-04-14 22:50:51', '1', '2');
INSERT INTO `check_record` VALUES ('35', '2022-04-15 00:23:00', '1', '2');
INSERT INTO `check_record` VALUES ('36', '2022-04-15 00:23:15', '1', '2');
INSERT INTO `check_record` VALUES ('37', '2022-04-15 00:23:48', '2', '2');
INSERT INTO `check_record` VALUES ('38', '2022-04-15 00:23:56', '1', '2');
INSERT INTO `check_record` VALUES ('39', '2022-04-15 00:24:00', '0', '2');
INSERT INTO `check_record` VALUES ('40', '2022-04-15 00:26:28', '0', '2');
INSERT INTO `check_record` VALUES ('41', '2022-04-15 00:28:35', '1', '2');
INSERT INTO `check_record` VALUES ('42', '2022-04-15 00:28:40', '0', '2');
INSERT INTO `check_record` VALUES ('43', '2022-04-15 00:28:47', '3', '2');
INSERT INTO `check_record` VALUES ('44', '2022-04-15 00:29:14', '1', '1');
INSERT INTO `check_record` VALUES ('45', '2022-04-15 00:29:36', '0', '1');
INSERT INTO `check_record` VALUES ('46', '2022-04-15 00:30:50', '1', '1');
INSERT INTO `check_record` VALUES ('47', '2022-04-15 00:31:56', '1', '1');
INSERT INTO `check_record` VALUES ('48', '2022-04-15 00:33:35', '1', '1');
INSERT INTO `check_record` VALUES ('49', '2022-04-15 00:34:46', '2', '1');
INSERT INTO `check_record` VALUES ('50', '2022-04-15 00:34:49', '3', '1');
INSERT INTO `check_record` VALUES ('51', '2022-04-15 00:35:15', '1', '1');
INSERT INTO `check_record` VALUES ('52', '2022-04-15 00:35:42', '0', '1');
INSERT INTO `check_record` VALUES ('53', '2022-04-15 00:35:55', '3', '1');
INSERT INTO `check_record` VALUES ('54', '2022-04-15 00:36:02', '1', '1');
INSERT INTO `check_record` VALUES ('55', '2022-04-15 00:40:30', '0', '1');
INSERT INTO `check_record` VALUES ('56', '2022-04-15 00:40:34', '1', '1');
INSERT INTO `check_record` VALUES ('57', '2022-04-15 00:40:36', '2', '1');
INSERT INTO `check_record` VALUES ('58', '2022-04-15 00:40:38', '3', '1');
INSERT INTO `check_record` VALUES ('59', '2022-04-15 00:41:50', '0', '1');
INSERT INTO `check_record` VALUES ('60', '2022-04-15 00:41:52', '1', '1');
INSERT INTO `check_record` VALUES ('61', '2022-04-15 00:41:53', '2', '1');
INSERT INTO `check_record` VALUES ('62', '2022-04-15 00:41:53', '3', '1');
INSERT INTO `check_record` VALUES ('63', '2022-04-15 00:41:57', '0', '1');
INSERT INTO `check_record` VALUES ('64', '2022-04-15 00:41:57', '1', '1');
INSERT INTO `check_record` VALUES ('65', '2022-04-15 00:41:58', '2', '1');
INSERT INTO `check_record` VALUES ('66', '2022-04-15 00:41:58', '3', '1');
INSERT INTO `check_record` VALUES ('67', '2022-04-15 00:44:27', '0', '1');
INSERT INTO `check_record` VALUES ('68', '2022-04-15 00:44:29', '1', '1');
INSERT INTO `check_record` VALUES ('69', '2022-04-15 00:44:30', '2', '1');
INSERT INTO `check_record` VALUES ('70', '2022-04-15 00:44:30', '3', '1');
INSERT INTO `check_record` VALUES ('71', '2022-04-15 00:51:02', '0', '1');
INSERT INTO `check_record` VALUES ('72', '2022-04-15 00:51:05', '1', '1');
INSERT INTO `check_record` VALUES ('73', '2022-04-15 00:51:06', '3', '1');
INSERT INTO `check_record` VALUES ('74', '2022-04-15 00:51:07', '2', '1');
INSERT INTO `check_record` VALUES ('75', '2022-04-18 17:58:35', '0', '1');
INSERT INTO `check_record` VALUES ('76', '2022-04-18 17:58:37', '1', '1');
INSERT INTO `check_record` VALUES ('77', '2022-04-20 21:33:56', '0', '1');
INSERT INTO `check_record` VALUES ('78', '2022-04-20 21:33:59', '2', '1');
INSERT INTO `check_record` VALUES ('79', '2022-04-20 21:34:03', '3', '1');

-- ----------------------------
-- Table structure for employer
-- ----------------------------
DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer` (
  `emp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL COMMENT '0：女\n1：男',
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employer
-- ----------------------------
INSERT INTO `employer` VALUES ('1', '2021229033', 'zyh', '1', '17320095328', '123456', 'tju_zyh@gamin.com', '2022-04-06 16:32:38', '1');
INSERT INTO `employer` VALUES ('2', '2021229038', 'zyq', '1', '18287828378', '123456', 'zyq182u3@163.com', '2022-04-05 20:33:18', '0');
INSERT INTO `employer` VALUES ('3', '2021229088', 'fy', '0', '15029839292', '123456', 'fy78382@11.com', '2022-04-10 20:20:53', '3');
INSERT INTO `employer` VALUES ('4', '2021229072', 'mxc', '1', '15829892981', '123456', 'mxc312@qq.com', '2022-04-10 20:52:42', '4');
INSERT INTO `employer` VALUES ('5', '234242', 'afdsa', '1', '123', '123', 'dfasaf', '2021-03-22 09:15:58', '2');
INSERT INTO `employer` VALUES ('6', '1231', '321213132', '0', '123123123', '13123', '12313', '2022-04-12 00:00:00', '2');
INSERT INTO `employer` VALUES ('7', '131', '1231312313123', '0', '1231313', '132', '12313', '2022-04-12 00:00:00', '2');
INSERT INTO `employer` VALUES ('8', '001', 'joe', '1', '15020146656', '123456', 'julietjobs6@gmail.com', '2022-04-20 00:00:00', '3');
INSERT INTO `employer` VALUES ('9', '1000', 'rs', '1', '123123123', '123', '123123', '2022-04-28 00:00:00', '4');
INSERT INTO `employer` VALUES ('10', '2000', 'cw', '1', '123123123', '123', '123123', '2022-04-28 00:00:00', '5');

-- ----------------------------
-- Table structure for emp_state
-- ----------------------------
DROP TABLE IF EXISTS `emp_state`;
CREATE TABLE `emp_state` (
  `state_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '0：未在班\n1：在班',
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp_state
-- ----------------------------
INSERT INTO `emp_state` VALUES ('1', '1', '1');
INSERT INTO `emp_state` VALUES ('2', '2', '1');
INSERT INTO `emp_state` VALUES ('3', '4', '0');
INSERT INTO `emp_state` VALUES ('4', '5', '0');
INSERT INTO `emp_state` VALUES ('5', '6', '0');
INSERT INTO `emp_state` VALUES ('6', '7', '0');
INSERT INTO `emp_state` VALUES ('7', '8', '0');
INSERT INTO `emp_state` VALUES ('8', '9', '0');
INSERT INTO `emp_state` VALUES ('9', '10', '0');

-- ----------------------------
-- Table structure for left_vacation
-- ----------------------------
DROP TABLE IF EXISTS `left_vacation`;
CREATE TABLE `left_vacation` (
  `left_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) DEFAULT NULL,
  `left_year` int(11) DEFAULT NULL,
  `left_hun_jia` int(11) DEFAULT NULL,
  `left_chan_jian` int(11) DEFAULT NULL,
  `left_chan_jia` int(11) DEFAULT NULL,
  `left_bu_ru` int(11) DEFAULT NULL,
  `left_pei_chan` int(11) DEFAULT NULL,
  PRIMARY KEY (`left_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of left_vacation
-- ----------------------------
INSERT INTO `left_vacation` VALUES ('1', '1', '0', '1', '3', '0', '9', '5');
INSERT INTO `left_vacation` VALUES ('2', '4', '7', '3', '3', '20', '30', '5');
INSERT INTO `left_vacation` VALUES ('3', '2', '7', '3', '3', '8', '30', '5');
INSERT INTO `left_vacation` VALUES ('4', '5', '7', '3', '3', '20', '30', '5');
INSERT INTO `left_vacation` VALUES ('5', '6', '7', '3', '3', '20', '30', '5');
INSERT INTO `left_vacation` VALUES ('6', '7', '7', '3', '3', '20', '30', '5');
INSERT INTO `left_vacation` VALUES ('7', '8', '7', '3', '3', '20', '30', '5');
INSERT INTO `left_vacation` VALUES ('8', '9', '7', '3', '3', '20', '30', '5');
INSERT INTO `left_vacation` VALUES ('9', '10', '7', '3', '3', '20', '30', '5');
