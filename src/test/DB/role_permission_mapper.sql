/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : manageplatform

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-28 09:22:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_permission_mapper
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_mapper`;
CREATE TABLE `role_permission_mapper` (
  `id` int(3) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(10) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `permission_id` int(10) NOT NULL DEFAULT '0' COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限表';
