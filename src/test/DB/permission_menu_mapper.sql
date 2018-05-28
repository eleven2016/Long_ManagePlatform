/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : manageplatform

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-28 09:22:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission_menu_mapper
-- ----------------------------
DROP TABLE IF EXISTS `permission_menu_mapper`;
CREATE TABLE `permission_menu_mapper` (
  `id` int(3) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_id` int(10) NOT NULL DEFAULT '0' COMMENT '权限ID',
  `menu_id` int(10) NOT NULL DEFAULT '0' COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限-菜单表';
