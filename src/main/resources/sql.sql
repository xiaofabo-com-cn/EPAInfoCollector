CREATE DATABASE IF NOT EXISTS crawl default charset utf8 COLLATE utf8_general_ci;

use crawl;
-- ----------------------------
-- Table structure for tb_crawl_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_content`;
CREATE TABLE `tb_crawl_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_url` varchar(500) DEFAULT NULL,
  `title` mediumblob,
  `body_content` mediumblob,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_un_url` (`start_url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_crawl_dict
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_dict`;
CREATE TABLE `tb_crawl_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `d_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crawl_dict
-- ----------------------------
INSERT INTO `tb_crawl_dict` VALUES (1, 'mail-notify', '718586216@qq.cn', '2019-07-02 11:47:26', '2019-07-04 17:38:22');
INSERT INTO `tb_crawl_dict` VALUES (2, 'craw-cron', '*/10 * * * * ?', '2019-07-03 10:17:53', '2019-07-04 13:16:22');
INSERT INTO `tb_crawl_dict` VALUES (3, 'mail-send-time', '*/30 * * * * ?', '2019-07-03 15:49:07', '2019-07-04 13:22:22');
INSERT INTO `tb_crawl_dict` VALUES (4, 'mail-last-time', '2019-07-06 14:02:02', '2019-07-03 16:38:15', '2019-07-03 16:38:15');
INSERT INTO `tb_crawl_dict` VALUES (5, 'mail-no-send', '否', '2019-07-04 12:47:14', '2019-07-04 12:47:14');
INSERT INTO `tb_crawl_dict` VALUES (6, 'data-expire-day', '30', '2019-07-04 13:40:57', '2019-07-05 11:10:55');
INSERT INTO `tb_crawl_dict` VALUES (7, 'data-expire-cron', '*/20 * * * * ?', '2019-07-04 16:11:28', '2019-07-04 16:11:34');

-- ----------------------------
-- Table structure for tb_crawl_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_file`;
CREATE TABLE `tb_crawl_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_md5` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_un_url`(`start_url`, `file_url`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 409403 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tb_crawl_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_menu`;
CREATE TABLE `tb_crawl_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `label` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `order` smallint(6) NULL DEFAULT 1,
  `level` smallint(6) NULL DEFAULT 1 COMMENT '层级，方便根据层级查询',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` smallint(6) NULL DEFAULT 1 COMMENT '扩展不同菜单时用',
  `style` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ui 样式',
  `disabled` smallint(6) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crawl_menu
-- ----------------------------
INSERT INTO `tb_crawl_menu` VALUES ('/business/collect', '手动抓取', '0,100', 10, 2, '/business/collect', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('100', '数据管理', '0', 11, 1, ' ', 0, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('1001', '数据列表', '0,100', 10, 2, '/business/list', 0, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('menu', '菜单管理', '0,system', 2, 1, '/menu', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('role', '角色管理', '0,system', 3, 3, '/role', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('setting', '用户设置', '0', 10, 1, ' ', 0, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('setting/expire', '过期设置', '0,setting', 10, 2, '/business/setting/expire', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('setting/notify', '邮件提醒', '0,setting', 10, 2, '/business/setting/notify', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('setting/rule', '爬虫规则', '0,setting', 10, 2, '/business/setting/rule', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('setting/task', '周期设置', '0,setting', 10, 2, '/business/setting/task', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('system', '系统管理', '0', 1, 1, '', 1, NULL, 0);
INSERT INTO `tb_crawl_menu` VALUES ('user', '用户管理', '0,system', 4, 2, '/user', 1, NULL, 0);

-- ----------------------------
-- Table structure for tb_crawl_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_resource`;
CREATE TABLE `tb_crawl_resource`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一资源编码',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `disabled` smallint(6) NOT NULL DEFAULT 0 COMMENT '状态 是否禁用',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `description` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_crawl_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_role`;
CREATE TABLE `tb_crawl_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  `disabled` smallint(6) NOT NULL DEFAULT 0,
  `description` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `rolename`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crawl_role
-- ----------------------------
INSERT INTO `tb_crawl_role` VALUES ('1', 'admin', 0, '管理员');
INSERT INTO `tb_crawl_role` VALUES ('f1d07c8f-57e9-4e00-a03f-348a96cd54e2', 'user', 0, '普通用户');

-- ----------------------------
-- Table structure for tb_crawl_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_role_menu`;
CREATE TABLE `tb_crawl_role_menu`  (
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `role_id_rm`(`role_id`) USING BTREE,
  INDEX `menu_code_rm`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crawl_role_menu
-- ----------------------------
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'menu');
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'role');
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'system');
INSERT INTO `tb_crawl_role_menu` VALUES ('1', 'user');
INSERT INTO `tb_crawl_role_menu` VALUES ('f1d07c8f-57e9-4e00-a03f-348a96cd54e2', 'menu');

-- ----------------------------
-- Table structure for tb_crawl_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_role_resource`;
CREATE TABLE `tb_crawl_role_resource`  (
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `resource_id`(`resource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_crawl_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_rule`;
CREATE TABLE `tb_crawl_rule`  (
  `id` int(11) NOT NULL COMMENT '自增id',
  `channel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '渠道',
  `match_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '匹配url',
  `start_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初始链接',
  `href` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '超链接',
  `post_time` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `body_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `body_file` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告附件',
  `current_page` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前页',
  `total_page` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总页数',
  `is_delete` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crawl_rule
-- ----------------------------
INSERT INTO `tb_crawl_rule` VALUES (1, '惠州市环保局-环评受理公告', 'http://www1.huizhou.gov.cn/pages/cms/hzhbj/html/{match},http://www1.huizhou.gov.cn/pages/cms/hzhbj/html/{date}/{md5}.html?cataId=670646a0b4484f5884543667a19eba56', 'http://www1.huizhou.gov.cn/pages/cms/hzhbj/html/artList.html?cataId=670646a0b4484f5884543667a19eba56', '#div_list a', '#div_list li.li_art_date', '#div_view > table > tbody > tr:nth-child(1) > td', '#divZoom', '#div_view > table > tbody > tr:nth-child(3) > td > table a', '', '', 1, '2019-06-26 12:52:09', '2019-07-04 17:39:19');
INSERT INTO `tb_crawl_rule` VALUES (2, '新疆生态环境厅', 'http://www.xjepb.gov.cn/xjepb/{1}/{2}/index.html,http://www.xjepb.gov.cn/xjepb/{1}/{2}/{3}/{4}/index.html', 'http://www.xjepb.gov.cn/xjepb/_639/_2766/index.html', 'div.ws_mk_box > div.ws_body > div.wr_san a', 'tbody td.wrsan_date', 'div > table > tbody > tr:nth-child(2) > td', '#zoom', '#zoom a', NULL, NULL, 1, '2019-07-01 20:15:56', '2019-07-06 13:50:07');
INSERT INTO `tb_crawl_rule` VALUES (3, '上海市环保局-信息公开', 'http://sthj.sh.gov.cn/hb/fa/cms/xxgk/{1},http://sthj.sh.gov.cn/fa/cms/xxgk//{1}//{2}/{3}/{4}.htm,http://sthj.sh.gov.cn/fa/cms/xxgk//{1}/{2}/{3}/{4}/{5}.htm,http://sthj.sh.gov.cn/fa/cms/xxgk//{1}/{2}/{3}/{4}/{5}/{6}.htm', 'http://sthj.sh.gov.cn/hb/fa/cms/xxgk/list_login.jsp', 'body > form > div > ul a', 'body > form > div > ul span', '#ivs_title > p', '#zoom', '#zoom a', NULL, NULL, 1, '2019-07-04 18:30:06', '2019-07-06 14:14:18');
INSERT INTO `tb_crawl_rule` VALUES (4, '重庆市环保局-通知通告', 'http://sthjj.cq.gov.cn/xxgk/zfxxgkml/xwdt/tzgg/{1}.shtml,http://sthjj.cq.gov.cn/{1}/{2}/index.shtml?spm={3}', 'http://sthjj.cq.gov.cn/xxgk/zfxxgkml/xwdt/tzgg/index.shtml', 'div.list_main_right_content a', 'div.list_main_right_content > ul span', 'div.detail_main_title', 'div.detail_main_content.neirong', 'div.detail_main_content.neirong a', NULL, NULL, 1, '2019-07-06 10:29:56', '2019-07-06 13:46:42');

-- ----------------------------
-- Table structure for tb_crawl_url
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_url`;
CREATE TABLE `tb_crawl_url`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `start_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初始url',
  `post_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_un_url`(`start_url`, `url`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1099601 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_crawl_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_user`;
CREATE TABLE `tb_crawl_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '密码的盐',
  `disabled` smallint(6) NOT NULL DEFAULT 1 COMMENT '0、禁用 1、正常',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `lastTime` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `loginname`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crawl_user
-- ----------------------------
INSERT INTO `tb_crawl_user` VALUES ('1', 'root', '5442b02dabc5ed9401be4dfe1ca8adb9', '718586216@qq.com', 'r', 0, '2016-09-27 19:53:20', '2016-09-27 19:53:22');

-- ----------------------------
-- Table structure for tb_crawl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_crawl_user_role`;
CREATE TABLE `tb_crawl_user_role`  (
  `uid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crawl_user_role
-- ----------------------------
INSERT INTO `tb_crawl_user_role` VALUES ('1', '1');
INSERT INTO `tb_crawl_user_role` VALUES ('8891e12f-81a7-43cd-8ab8-4accdf141f96', '1');
INSERT INTO `tb_crawl_user_role` VALUES ('aaf62456-d96c-4aae-bff0-90330a7d7a02', 'f1d07c8f-57e9-4e00-a03f-348a96cd54e2');
INSERT INTO `tb_crawl_user_role` VALUES ('a3f3ccd3-62f1-4815-b06d-663295066bc7', '1');
