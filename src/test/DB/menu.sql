/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : manageplatform

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-28 09:21:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(45) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `p_id` int(10) NOT NULL DEFAULT '0' COMMENT '上一层级菜单ID',
  `level` int(2) NOT NULL DEFAULT '0' COMMENT '菜单层级',
  `menu_url` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单url',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';
