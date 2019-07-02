DROP TABLE IF EXISTS `tb_crawl_menu`;
CREATE TABLE `tb_crawl_menu` (
  `id` varchar(50) NOT NULL,
  `label` varchar(20) NOT NULL,
  `path` varchar(200) DEFAULT '0',
  `order` smallint(6) DEFAULT '1',
  `level` smallint(6) DEFAULT '1' COMMENT '层级，方便根据层级查询',
  `url` varchar(200) DEFAULT NULL,
  `type` smallint(6) DEFAULT '1' COMMENT '扩展不同菜单时用',
  `style` varchar(50) DEFAULT NULL COMMENT 'ui 样式',
  `disabled` smallint(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `tb_crawl_menu` VALUES ('menu', '菜单管理', '0,system', '2', '1', '/menu', '1', null, '0');
INSERT INTO `tb_crawl_menu` VALUES ('role', '角色管理', '0,system', '3', '3', '/role', '1', null, '0');
INSERT INTO `tb_crawl_menu` VALUES ('system', '系统管理', '0', '1', '1', '', '1', null, '0');
INSERT INTO `tb_crawl_menu` VALUES ('user', '用户管理', '0,system', '4', '2', '/user', '1', null, '0');

-- ----------------------------
-- Table structure for `tb_crawl_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_role`;
CREATE TABLE `tb_crawl_role` (
  `id` varchar(50) NOT NULL,
  `name` varchar(30) NOT NULL COMMENT '角色',
  `disabled` smallint(6) NOT NULL DEFAULT '0',
  `description` varchar(60) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rolename` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `tb_crawl_role` VALUES ('1', 'admin', '0', '管理员');
INSERT INTO `tb_crawl_role` VALUES ('f1d07c8f-57e9-4e00-a03f-348a96cd54e2', 'user', '0', '普通用户');

-- ----------------------------
-- Table structure for `tb_crawl_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_role_menu`;
CREATE TABLE `tb_crawl_role_menu` (
  `role_id` varchar(50) DEFAULT NULL,
  `menu_id` varchar(50) DEFAULT NULL,
  KEY `role_id_rm` (`role_id`),
  KEY `menu_code_rm` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'menu');
-- INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'resource');
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'role');
-- INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'syslog');
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'system');
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'user');
INSERT INTO `tb_crawl_role_menu` VALUES ('f1d07c8f-57e9-4e00-a03f-348a96cd54e2', 'menu');



-- ----------------------------
-- Table structure for `tb_crawl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_user`;
CREATE TABLE `tb_crawl_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `username` varchar(20) NOT NULL COMMENT '登录名称',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `salt` varchar(50) DEFAULT '0' COMMENT '密码的盐',
  `disabled` smallint(6) NOT NULL DEFAULT '1' COMMENT '0、禁用 1、正常',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginname` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `tb_crawl_user` VALUES ('1', 'root', '5442b02dabc5ed9401be4dfe1ca8adb9', 'jonsychen@hotmail.com', 'r', '0', '2016-09-27 19:53:20', '2016-09-27 19:53:22');
INSERT INTO `tb_crawl_user` VALUES ('8891e12f-81a7-43cd-8ab8-4accdf141f96', 'jonsy', '2e9ca4d0b1a586fd80bc2ba782ac36bc', 'jonsychen@hotmail.com', '0', '0', '2016-10-31 21:16:47', null);
INSERT INTO `tb_crawl_user` VALUES ('aaf62456-d96c-4aae-bff0-90330a7d7a02', 'frank', '52d76781a799f857f35e3bb50c94c21e', 'jonsychen@hotmail.com', '0', '0', '2016-11-04 18:12:34', null);

-- ----------------------------
-- Table structure for `tb_crawl_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_user_role`;
CREATE TABLE `tb_crawl_user_role` (
  `uid` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  KEY `uid` (`uid`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `tb_crawl_user_role` VALUES ('1', '1');
INSERT INTO `tb_crawl_user_role` VALUES ('8891e12f-81a7-43cd-8ab8-4accdf141f96', '1');
INSERT INTO `tb_crawl_user_role` VALUES ('aaf62456-d96c-4aae-bff0-90330a7d7a02', 'f1d07c8f-57e9-4e00-a03f-348a96cd54e2');


DROP TABLE IF EXISTS `tb_crawl_resource`;
CREATE TABLE `tb_crawl_resource` (
  `id` varchar(50) NOT NULL COMMENT '唯一资源编码',
  `title` varchar(50) NOT NULL COMMENT '资源名称',
  `disabled` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 是否禁用',
  `url` varchar(200) DEFAULT NULL COMMENT '地址',
  `description` varchar(60) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_crawl_role_resource`;
CREATE TABLE `tb_crawl_role_resource` (
  `role_id` varchar(50) NOT NULL,
  `resource_id` varchar(50) NOT NULL,
  KEY `role_id` (`role_id`),
  KEY `resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


COMMIT;