/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : permission_demo

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 15/01/2020 16:11:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(128) NOT NULL DEFAULT '' COMMENT '权限名称，如管理员权限，普通用户权限',
  `permission_code` varchar(128) NOT NULL DEFAULT '' COMMENT '权限代码，如PERMISSION_ADMIN，PERMISSON_USER',
  `permission_desc` varchar(255) DEFAULT '' COMMENT '权限说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_permission` VALUES (1, '查看权限', 'R_PERMISSION', '查看的权限');
INSERT INTO `tb_permission` VALUES (2, '增加权限', 'C_PERMISSION', '添加的权限');
INSERT INTO `tb_permission` VALUES (3, '修改权限', 'U_PERMISSION', '修改的权限');
INSERT INTO `tb_permission` VALUES (4, '删除权限', 'D_PERMISSION', '删除的权限');
COMMIT;

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT '' COMMENT '产品名称',
  `count` varchar(255) DEFAULT '' COMMENT '数量',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
BEGIN;
INSERT INTO `tb_product` VALUES (1, '手机', '1', '苹果');
INSERT INTO `tb_product` VALUES (2, '电脑', '2', '联想');
INSERT INTO `tb_product` VALUES (3, '电视', '3', '三星');
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名，如管理员，普通用户',
  `role_code` varchar(128) NOT NULL DEFAULT '' COMMENT '角色名对应代码，如ADMIN，USER',
  `role_desc` varchar(255) DEFAULT '' COMMENT '角色备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` VALUES (1, '管理员', 'ADMIN', '管理员角色');
INSERT INTO `tb_role` VALUES (2, 'CRU管理员', 'CRU_ADMIN', '可有查看，增加和修改的权限');
INSERT INTO `tb_role` VALUES (3, 'RD管理员', 'RD_ADMIN', '可有查看和删除的权限');
INSERT INTO `tb_role` VALUES (4, '普通用户', 'USER', '只有查看的权限');
COMMIT;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(32) NOT NULL COMMENT '角色表id',
  `permission_id` bigint(32) NOT NULL COMMENT '权限表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_permission` VALUES (1, 1, 1);
INSERT INTO `tb_role_permission` VALUES (2, 1, 2);
INSERT INTO `tb_role_permission` VALUES (3, 1, 3);
INSERT INTO `tb_role_permission` VALUES (4, 1, 4);
INSERT INTO `tb_role_permission` VALUES (5, 2, 1);
INSERT INTO `tb_role_permission` VALUES (6, 2, 2);
INSERT INTO `tb_role_permission` VALUES (7, 2, 3);
INSERT INTO `tb_role_permission` VALUES (8, 3, 1);
INSERT INTO `tb_role_permission` VALUES (9, 3, 4);
INSERT INTO `tb_role_permission` VALUES (10, 4, 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, '管理员', '123456');
INSERT INTO `tb_user` VALUES (2, '张三', '123456');
INSERT INTO `tb_user` VALUES (3, '李四', '123456');
INSERT INTO `tb_user` VALUES (4, '王五', '123456');
INSERT INTO `tb_user` VALUES (5, '赵六', '123456');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(32) NOT NULL COMMENT '用户表id',
  `role_id` bigint(32) NOT NULL COMMENT '角色表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` VALUES (1, 1, 1);
INSERT INTO `tb_user_role` VALUES (2, 2, 2);
INSERT INTO `tb_user_role` VALUES (3, 3, 3);
INSERT INTO `tb_user_role` VALUES (4, 4, 4);
INSERT INTO `tb_user_role` VALUES (5, 5, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
