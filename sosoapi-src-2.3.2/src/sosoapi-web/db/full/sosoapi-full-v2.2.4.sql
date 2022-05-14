/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3308
Source Database       : sosoapi

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-10-08 15:51:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_api_doc
-- ----------------------------
DROP TABLE IF EXISTS `t_api_doc`;
CREATE TABLE `t_api_doc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '描述信息',
  `host` varchar(64) DEFAULT NULL COMMENT '访问主机',
  `base_path` varchar(128) DEFAULT NULL COMMENT '基路径',
  `pub` tinyint(4) DEFAULT '0',
  `open` tinyint(4) NOT NULL DEFAULT '0',
  `scheme` varchar(32) DEFAULT NULL,
  `consume` varchar(128) DEFAULT NULL,
  `produce` varchar(128) DEFAULT NULL,
  `version` varchar(16) DEFAULT NULL COMMENT '版本',
  `share` tinyint(4) DEFAULT NULL COMMENT '是否开启分享',
  `share_password` varchar(32) DEFAULT NULL COMMENT '分享访问密码',
  `share_url` varchar(256) DEFAULT NULL COMMENT '分享访问url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_proj_id` (`proj_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='api文档信息';

-- ----------------------------
-- Records of t_api_doc
-- ----------------------------
INSERT INTO `t_api_doc` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', 'sosoapi_demo', '该demo主要用于汇集常见的接口编辑事例。\n<br/>\n接口测试过程中如果出现ajax跨域请求问题可参考\"常见问题->线下部署\"中的跨域请求相关资料。\n<br/>\n<br/>\n技术交流群:305629848', 'www.sosoapi.com', '/demo', '0', '1', '', '', '', '1.0.0', '0', null, null);

-- ----------------------------
-- Table structure for t_api_env
-- ----------------------------
DROP TABLE IF EXISTS `t_api_env`;
CREATE TABLE `t_api_env` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '文档id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `base_url` varchar(256) DEFAULT NULL COMMENT '基路径',
  `status` varchar(16) DEFAULT NULL COMMENT '启用状态',
  `def` tinyint(4) DEFAULT NULL COMMENT '是否默认',
  `description` text COMMENT '描述',
  `variable` text COMMENT '变量列表',
  `sort_weight` int(11) NOT NULL DEFAULT '0' COMMENT '排序权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='api环境';

-- ----------------------------
-- Records of t_api_env
-- ----------------------------
INSERT INTO `t_api_env` VALUES ('1', '2017-09-18 17:59:08', '2017-09-18 17:59:08', '1', 'default', 'http://www.sosoapi.com/demo/swagger', 'on', '0', null, null, '1');

-- ----------------------------
-- Table structure for t_api_error_code
-- ----------------------------
DROP TABLE IF EXISTS `t_api_error_code`;
CREATE TABLE `t_api_error_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '文档id',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块id',
  `code` varchar(64) DEFAULT NULL COMMENT '错误码',
  `msg` varchar(256) DEFAULT NULL COMMENT '错误信息',
  `description` varchar(512) DEFAULT NULL COMMENT '备注',
  `sort_weight` int(11) DEFAULT NULL COMMENT '排序权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='错误码';

-- ----------------------------
-- Records of t_api_error_code
-- ----------------------------

-- ----------------------------
-- Table structure for t_common_param
-- ----------------------------
DROP TABLE IF EXISTS `t_common_param`;
CREATE TABLE `t_common_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '文档id',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `type` varchar(32) DEFAULT NULL COMMENT '数据类型',
  `format` varchar(64) DEFAULT NULL COMMENT '格式化',
  `position` varchar(16) DEFAULT NULL COMMENT '参数位置',
  `required` tinyint(4) DEFAULT NULL COMMENT '是否必输项',
  `cust_schema` text COMMENT '自定结构体',
  `ext_schema` text COMMENT '扩展结构体',
  `ref_schema_id` bigint(20) DEFAULT NULL COMMENT '引用结构id',
  `def_value` varchar(512) DEFAULT NULL COMMENT '默认值',
  `sort_weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公共参数';

-- ----------------------------
-- Records of t_common_param
-- ----------------------------

-- ----------------------------
-- Table structure for t_doc_archive
-- ----------------------------
DROP TABLE IF EXISTS `t_doc_archive`;
CREATE TABLE `t_doc_archive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '文档id',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '描述',
  `label` varchar(128) DEFAULT NULL COMMENT '自定义标签',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建者',
  `share` tinyint(4) DEFAULT NULL COMMENT '是否开启分享',
  `share_password` varchar(256) DEFAULT NULL COMMENT '分享密码',
  `share_url` varchar(256) DEFAULT NULL COMMENT '分享url',
  `type` varchar(16) DEFAULT NULL COMMENT '归档方式',
  `archive_url` varchar(256) DEFAULT NULL COMMENT '归档资源url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文档归档';

-- ----------------------------
-- Records of t_doc_archive
-- ----------------------------

-- ----------------------------
-- Table structure for t_doc_view
-- ----------------------------
DROP TABLE IF EXISTS `t_doc_view`;
CREATE TABLE `t_doc_view` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '描述信息',
  `version` varchar(16) DEFAULT NULL COMMENT '版本',
  `pub` tinyint(4) DEFAULT NULL COMMENT '是否发布',
  `share` tinyint(4) DEFAULT NULL COMMENT '是否开启分享',
  `share_password` varchar(256) DEFAULT NULL COMMENT '分享密码',
  `share_url` varchar(256) DEFAULT NULL COMMENT '分享url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='api文档视图';

-- ----------------------------
-- Records of t_doc_view
-- ----------------------------

-- ----------------------------
-- Table structure for t_doc_view_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_doc_view_resource`;
CREATE TABLE `t_doc_view_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `view_id` bigint(20) DEFAULT NULL COMMENT '文档视图id',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `type` varchar(16) DEFAULT NULL COMMENT '资源类型',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='api文档视图资源';

-- ----------------------------
-- Records of t_doc_view_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_download_record
-- ----------------------------
DROP TABLE IF EXISTS `t_download_record`;
CREATE TABLE `t_download_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(32) DEFAULT NULL COMMENT '商品名称',
  `goods_desc` varchar(64) DEFAULT NULL COMMENT '商品描述',
  `goods_type` varchar(16) DEFAULT NULL COMMENT '类型',
  `download_ip` varchar(32) DEFAULT NULL COMMENT '下载ip',
  `file_name` varchar(256) DEFAULT NULL COMMENT '文件名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='下载记录';

-- ----------------------------
-- Records of t_download_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_filter_chain
-- ----------------------------
DROP TABLE IF EXISTS `t_filter_chain`;
CREATE TABLE `t_filter_chain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `url` varchar(128) DEFAULT NULL COMMENT 'url',
  `filter` varchar(128) DEFAULT NULL COMMENT '过滤链',
  `status` varchar(8) DEFAULT NULL COMMENT '启用状态',
  `sort_weight` int(11) DEFAULT NULL COMMENT '排序权重',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `position` varchar(64) DEFAULT NULL COMMENT '位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='shiro过滤链';

-- ----------------------------
-- Records of t_filter_chain
-- ----------------------------
INSERT INTO `t_filter_chain` VALUES ('1', '2017-07-10 18:08:18', '2017-07-10 18:08:20', '管理后台', '/admin/**', 'user', 'on', '1', null, 'after');
INSERT INTO `t_filter_chain` VALUES ('2', '2017-07-10 18:09:12', '2017-07-10 18:09:14', '用户后台', '/auth/**', 'user', 'on', '2', null, 'after');

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `price` int(11) DEFAULT NULL COMMENT '单价',
  `cover_url` varchar(255) DEFAULT NULL COMMENT '封面',
  `detail_info` text COMMENT '详情',
  `discount` int(11) DEFAULT NULL COMMENT '折扣价格',
  `status` varchar(16) DEFAULT NULL COMMENT '状态',
  `sell_count` int(11) DEFAULT NULL COMMENT '已售数量',
  `total_count` int(11) DEFAULT NULL COMMENT '总数量',
  `download_url` varchar(256) DEFAULT NULL COMMENT '下载地址',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息';

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for t_inter
-- ----------------------------
DROP TABLE IF EXISTS `t_inter`;
CREATE TABLE `t_inter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块id',
  `name` varchar(64) DEFAULT NULL COMMENT '接口方法名称',
  `path` varchar(128) DEFAULT NULL COMMENT '请求url',
  `method` varchar(8) DEFAULT NULL COMMENT '请求方法',
  `scheme` varchar(32) DEFAULT NULL COMMENT '请求协议',
  `summary` varchar(64) DEFAULT NULL COMMENT '概要信息',
  `description` text COMMENT '描述信息',
  `consume` varchar(128) DEFAULT NULL COMMENT '请求格式',
  `produce` varchar(128) DEFAULT NULL COMMENT '响应格式',
  `deprecated` tinyint(4) DEFAULT NULL COMMENT '是否弃用',
  `sort_weight` int(11) NOT NULL DEFAULT '0',
  `path_regex` varchar(255) DEFAULT NULL COMMENT '请求url对应的正则表达式',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `label` varchar(128) DEFAULT NULL COMMENT '自定义标签',
  `dev_status` varchar(16) DEFAULT NULL COMMENT '开发状态',
  `developer` varchar(128) DEFAULT NULL COMMENT '负责人',
  `operation_id` varchar(128) DEFAULT NULL COMMENT '接口标识',
  `example` text COMMENT '接口示例',
  `skip_common_param` tinyint(4) DEFAULT NULL COMMENT '是否过滤公共参数',
  PRIMARY KEY (`id`),
  KEY `idx_doc_id` (`doc_id`) USING BTREE,
  KEY `idx_module_id` (`module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='接口信息';

-- ----------------------------
-- Records of t_inter
-- ----------------------------
INSERT INTO `t_inter` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', '2', '新增用户(自定义参数)', '/user/complex/add.htm', 'POST', 'HTTP', null, '新增用户(自定义参数)', 'application/json', 'application/json', '0', '3', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('2', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '2', '查询用户列表(多层嵌套)', '/user/complex/list.htm', 'GET', 'HTTP', null, '查询用户列表(多层嵌套)', 'application/json', 'application/json', '0', '1', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('3', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '2', '获取用户详情(多层嵌套)', '/user/complex/{userId}/info.htm', 'GET', 'HTTP', null, '获取用户详情(多层嵌套)', 'application/json', 'application/json', '0', '2', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('4', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '新增用户', '/user/simple/add.htm', 'POST', 'HTTP', null, '新增用户信息', 'application/json', 'application/json', '0', '2', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('5', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '查询用户列表', '/user/simple/list.htm', 'GET', 'HTTP', null, '获取用户列表', 'application/json', 'application/json', '0', '3', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('6', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '删除用户', '/user/simple/{userId}/del.htm', 'DELETE', 'HTTP', null, '删除指定用户', 'application/json', 'application/json', '0', '4', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('7', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '查询用户', '/user/simple/{userId}/info.htm', 'GET', 'HTTP', null, '查询用户信息', 'application/json', 'application/json', '0', '5', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('8', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '更新用户', '/user/simple/{userId}/update.htm', 'POST', 'HTTP', null, '更新用户信息', 'application/json', 'application/json', '0', '6', null, '0', null, 'none', null, null, null, '0');
INSERT INTO `t_inter` VALUES ('9', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '1', '上传图片', '/user/simple/{userId}/upload.htm', 'POST', 'HTTP', null, '上传', 'application/json', 'application/json', '0', '1', null, '0', null, 'none', null, null, null, '0');

-- ----------------------------
-- Table structure for t_inter_param
-- ----------------------------
DROP TABLE IF EXISTS `t_inter_param`;
CREATE TABLE `t_inter_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL,
  `inter_id` bigint(20) DEFAULT NULL COMMENT '接口id',
  `code` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `type` varchar(32) DEFAULT NULL COMMENT '数据类型',
  `format` varchar(64) DEFAULT NULL COMMENT '格式化',
  `position` varchar(16) DEFAULT NULL COMMENT '参数位置',
  `required` tinyint(4) DEFAULT NULL COMMENT '是否必输项',
  `cust_schema` text,
  `ext_schema` text,
  `ref_schema_id` bigint(20) DEFAULT NULL,
  `def_value` varchar(512) DEFAULT NULL COMMENT '默认值',
  `sort_weight` int(11) NOT NULL DEFAULT '0' COMMENT '排序权重',
  PRIMARY KEY (`id`),
  KEY `idx_inter_id` (`inter_id`) USING BTREE,
  KEY `idx_doc_id` (`doc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='接口参数';

-- ----------------------------
-- Records of t_inter_param
-- ----------------------------
INSERT INTO `t_inter_param` VALUES ('1', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', 'userInfo', null, '用户信息', 'cust_json', 'json', 'body', '1', null, '{\n	\"email\": \"string,邮箱\",\n        \"nickName\": \"string,昵称\"\n}', null, null, '1');
INSERT INTO `t_inter_param` VALUES ('2', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null, '1');
INSERT INTO `t_inter_param` VALUES ('3', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'email', null, '邮箱', 'sys_string', '', 'formData', '1', null, null, null, null, '1');
INSERT INTO `t_inter_param` VALUES ('4', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'nickName', null, '昵称', 'sys_string', '', 'formData', '1', null, null, null, null, '2');
INSERT INTO `t_inter_param` VALUES ('5', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'age', null, '年龄', 'sys_integer_int32', 'int32', 'formData', '0', null, null, null, null, '3');
INSERT INTO `t_inter_param` VALUES ('6', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '6', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null, '1');
INSERT INTO `t_inter_param` VALUES ('7', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '7', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null, '1');
INSERT INTO `t_inter_param` VALUES ('8', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '8', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null, '1');
INSERT INTO `t_inter_param` VALUES ('9', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '8', 'nickName', null, '昵称', 'sys_string', '', 'query', '1', null, null, null, null, '2');
INSERT INTO `t_inter_param` VALUES ('10', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '9', 'userId', null, '', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, '', '1');
INSERT INTO `t_inter_param` VALUES ('11', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '9', 'img', null, '', 'sys_file', '', 'formData', '1', null, null, null, '', '2');

-- ----------------------------
-- Table structure for t_inter_resp
-- ----------------------------
DROP TABLE IF EXISTS `t_inter_resp`;
CREATE TABLE `t_inter_resp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL,
  `inter_id` bigint(20) DEFAULT NULL COMMENT '接口id',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL,
  `description` text COMMENT '描述信息',
  `type` varchar(32) DEFAULT NULL COMMENT '响应类型',
  `ref_schema_id` bigint(20) DEFAULT NULL COMMENT '响应数据结构id',
  `def` tinyint(4) DEFAULT NULL COMMENT '是否是默认',
  `required` tinyint(4) DEFAULT NULL,
  `cust_schema` text COMMENT '自定义结构体',
  `ext_schema` text,
  `sort_weight` int(11) NOT NULL DEFAULT '0',
  `mock_data` text COMMENT 'mock数据',
  `mock_rule` text COMMENT 'mock规则',
  `mock_type` varchar(16) DEFAULT NULL COMMENT 'mock类型',
  PRIMARY KEY (`id`),
  KEY `idx_inter_id` (`inter_id`) USING BTREE,
  KEY `idx_doc_id` (`doc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='接口响应';

-- ----------------------------
-- Records of t_inter_resp
-- ----------------------------
INSERT INTO `t_inter_resp` VALUES ('1', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '1', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('2', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '2', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"data\",\"description\":\"响应信息\",\"type\":\"sys_object\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"totalCount\",\"description\":\"总记录数\",\"type\":\"sys_integer_int32\",\"nodeId\":\"1010\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"list\",\"description\":\"用户列表\",\"type\":\"sys_array\",\"nodeId\":\"1011\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"SimpleUserInfo\",\"description\":\"用户列表\",\"type\":\"sys_ref\",\"refSchemaId\":2,\"nodeId\":\"101110\",\"parentId\":\"1011\",\"childList\":[],\"childRefSchemaIdList\":[2]},{\"code\":\"errorCode\",\"description\":\"错误码\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '1', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('3', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"addressList\",\"description\":\"地址列表\",\"type\":\"sys_array\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"street\",\"description\":\"街道名称\",\"type\":\"sys_string\",\"nodeId\":\"1010\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"city\",\"description\":\"城市名称\",\"type\":\"sys_string\",\"nodeId\":\"1011\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"nickName\",\"description\":\"昵称\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"age\",\"description\":\"年龄\",\"type\":\"sys_integer_int32\",\"nodeId\":\"12\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"13\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '1', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('4', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', 'error', null, '错误信息', 'sys_ref', '1', '0', '0', null, null, '2', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('5', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', '001', null, '管理员', 'sys_object', null, '0', '0', '[{\"code\":\"admin\",\"description\":\"管理员信息\",\"type\":\"sys_ref\",\"refSchemaId\":2,\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[2]}]', null, '3', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('6', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '1', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('7', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', '404', null, '无法找到对应服务', 'sys_string', null, '0', '0', null, null, '2', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('8', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '5', 'default', null, '默认响应', 'sys_array', null, '0', '0', '[{\"code\":\"email\",\"description\":\"用户邮箱\",\"type\":\"sys_string\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"nickName\",\"description\":\"用户昵称\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"12\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '1', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('9', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '6', 'default', null, '默认响应', 'sys_ref', '1', '0', '0', null, null, '1', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('10', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '7', 'default', null, '默认响应', 'sys_ref', '2', '0', '0', null, null, '1', null, null, null);
INSERT INTO `t_inter_resp` VALUES ('11', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '8', 'default', null, '默认响应', 'sys_ref', '1', '0', '0', null, null, '1', null, null, null);

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `sort_weight` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_doc_id` (`doc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='模块信息';

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, '入门', '', '1');
INSERT INTO `t_module` VALUES ('2', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, '进阶', '', '2');

-- ----------------------------
-- Table structure for t_monitor_alarm_group
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor_alarm_group`;
CREATE TABLE `t_monitor_alarm_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `email_alarm` tinyint(4) DEFAULT NULL COMMENT '邮件报警',
  `phone_alarm` tinyint(4) DEFAULT NULL COMMENT '短信报警',
  `start_time` varchar(16) DEFAULT NULL COMMENT '接收报警开始时间',
  `end_time` varchar(16) DEFAULT NULL COMMENT '接收报警结束时间',
  `description` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='警报接收组';

-- ----------------------------
-- Records of t_monitor_alarm_group
-- ----------------------------

-- ----------------------------
-- Table structure for t_monitor_alarm_group_receiver
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor_alarm_group_receiver`;
CREATE TABLE `t_monitor_alarm_group_receiver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '组id',
  `receiver_id` bigint(20) DEFAULT NULL COMMENT '接收者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='警报接收组人员';

-- ----------------------------
-- Records of t_monitor_alarm_group_receiver
-- ----------------------------

-- ----------------------------
-- Table structure for t_monitor_alarm_log
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor_alarm_log`;
CREATE TABLE `t_monitor_alarm_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `monitor_id` bigint(20) DEFAULT NULL COMMENT '监控id',
  `monitor_type` varchar(8) DEFAULT NULL COMMENT '监控类型',
  `sub` varchar(256) DEFAULT NULL COMMENT '监控对象',
  `sub_status` varchar(16) DEFAULT NULL COMMENT '对象状态',
  `error_brief` varchar(128) DEFAULT NULL COMMENT '异常概要',
  `error_detail` varchar(256) DEFAULT NULL COMMENT '异常详情',
  `resp_content` text COMMENT '响应内容',
  `spend_time` bigint(20) DEFAULT NULL COMMENT '请求花费时间，毫秒',
  `alarm_group_id` bigint(20) DEFAULT NULL COMMENT '警报组',
  `monitor_log_id` bigint(20) DEFAULT NULL COMMENT '运行日志id',
  `receiver_info` text COMMENT '预警人员信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='监控警报日志';

-- ----------------------------
-- Records of t_monitor_alarm_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_monitor_alarm_receiver
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor_alarm_receiver`;
CREATE TABLE `t_monitor_alarm_receiver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `sys_flag` tinyint(4) DEFAULT NULL COMMENT '是否系统用户',
  `sys_user_id` bigint(20) DEFAULT NULL COMMENT '系统用户id',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone_valid` tinyint(4) DEFAULT NULL COMMENT '手机号是否验证',
  `email_valid` tinyint(4) DEFAULT NULL COMMENT '邮箱是否验证',
  `email_alarm` tinyint(4) DEFAULT NULL COMMENT '邮件报警',
  `phone_alarm` tinyint(4) DEFAULT NULL COMMENT '短信报警',
  `start_time` varchar(16) DEFAULT NULL COMMENT '接收报警开始时间',
  `end_time` varchar(16) DEFAULT NULL COMMENT '接收报警结束时间',
  `description` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='警报接收人';

-- ----------------------------
-- Records of t_monitor_alarm_receiver
-- ----------------------------

-- ----------------------------
-- Table structure for t_monitor_http
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor_http`;
CREATE TABLE `t_monitor_http` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `sub` varchar(256) DEFAULT NULL COMMENT '监控url',
  `req_param` text COMMENT '请求参数',
  `rate` int(11) DEFAULT NULL COMMENT '监控频率，单位分',
  `req_method` varchar(8) DEFAULT NULL COMMENT '请求方法',
  `resp_content` text COMMENT '验证内容',
  `valid_resp` tinyint(4) DEFAULT NULL COMMENT '是否验证响应',
  `sub_status` varchar(16) DEFAULT NULL COMMENT '监控对象状态',
  `max_spend_time` bigint(20) DEFAULT NULL COMMENT '最大请求花费时间，毫秒',
  `max_error_count` int(11) DEFAULT NULL COMMENT '最大错误次数',
  `max_alarm_count` int(11) DEFAULT NULL COMMENT '最大预警次数',
  `alarm_group_id` bigint(20) DEFAULT NULL COMMENT '警报组',
  `alarm_count` int(11) DEFAULT NULL COMMENT '已发出预警次数',
  `error_count` int(11) DEFAULT NULL COMMENT '已错误次数',
  `resp_content_type` varchar(32) DEFAULT NULL COMMENT '响应内容类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='http监控';

-- ----------------------------
-- Records of t_monitor_http
-- ----------------------------

-- ----------------------------
-- Table structure for t_monitor_log
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor_log`;
CREATE TABLE `t_monitor_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `monitor_id` bigint(20) DEFAULT NULL COMMENT '监控id',
  `monitor_type` varchar(8) DEFAULT NULL COMMENT '监控类型',
  `sub` varchar(256) DEFAULT NULL COMMENT '监控对象',
  `sub_status` varchar(16) DEFAULT NULL COMMENT '对象状态',
  `error_brief` varchar(128) DEFAULT NULL COMMENT '异常概要',
  `error_detail` varchar(256) DEFAULT NULL COMMENT '异常详情',
  `resp_content` text COMMENT '响应内容',
  `spend_time` int(11) DEFAULT NULL COMMENT '请求花费时间，毫秒',
  `curl_cmd` varchar(512) DEFAULT NULL COMMENT 'curl命令',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='监控运行日志';

-- ----------------------------
-- Records of t_monitor_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `order_name` varchar(32) DEFAULT NULL COMMENT '订单名称',
  `order_desc` varchar(64) DEFAULT NULL COMMENT '订单描述',
  `prepay_id` varchar(64) DEFAULT NULL COMMENT '预支付id',
  `qr_code_url` varchar(64) DEFAULT NULL COMMENT '支付二维码',
  `trade_no` varchar(32) DEFAULT NULL COMMENT '交易号',
  `out_trade_no` varchar(32) DEFAULT NULL COMMENT '支付平台交易号',
  `total_fee` int(11) DEFAULT NULL COMMENT '总金额',
  `settlement_total_fee` int(11) DEFAULT NULL COMMENT '应结订单金额',
  `total_item_count` int(11) DEFAULT NULL COMMENT '订单项数目',
  `expire_date` datetime DEFAULT NULL COMMENT '订单失效时间',
  `device_info` varchar(64) DEFAULT NULL COMMENT '终端设备号',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `trade_status` varchar(16) DEFAULT NULL COMMENT '交易状态',
  `trade_status_desc` varchar(256) DEFAULT NULL COMMENT '交易状态描述',
  `trade_ip` varchar(16) DEFAULT NULL COMMENT '订单支付ip',
  `trade_type` varchar(16) DEFAULT NULL COMMENT '交易方式',
  `trade_platform` varchar(16) DEFAULT NULL COMMENT '交易平台',
  `refund_fee` int(11) DEFAULT NULL COMMENT '退款金额',
  `refund_item_count` int(11) DEFAULT NULL COMMENT '退款订单项',
  `cancel_date` datetime DEFAULT NULL COMMENT '取消时间',
  `cancel_remark` varchar(255) DEFAULT NULL COMMENT '取消原因',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `prod_type` varchar(16) DEFAULT NULL COMMENT '商品类型',
  PRIMARY KEY (`id`),
  KEY `idx_trade_no` (`trade_no`) USING BTREE,
  KEY `idx_out_trade_no` (`out_trade_no`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `goods_name` varchar(256) DEFAULT NULL COMMENT '商品名称',
  `goods_desc` varchar(256) DEFAULT NULL COMMENT '商品描述',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `price` int(11) DEFAULT NULL COMMENT '单价',
  `total_fee` int(11) DEFAULT NULL COMMENT '总价格',
  `refund_fee` int(11) DEFAULT NULL COMMENT '退款金额',
  `refund_remark` varchar(255) DEFAULT NULL COMMENT '退款备注',
  `trade_status` varchar(16) DEFAULT NULL COMMENT '交易状态',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`) USING BTREE,
  KEY `idx_goods_id` (`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单项';

-- ----------------------------
-- Records of t_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify`;
CREATE TABLE `t_pay_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单id',
  `trade_no` varchar(32) DEFAULT NULL COMMENT '商户交易号',
  `out_trade_no` varchar(32) DEFAULT NULL COMMENT '支付交易号',
  `trade_status` varchar(32) DEFAULT NULL COMMENT '交易状态',
  `trade_platform` varchar(16) DEFAULT NULL COMMENT '交易平台',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  `notify_ip` varchar(16) DEFAULT NULL COMMENT '通知请求ip',
  `return_code` varchar(16) DEFAULT NULL COMMENT '返回码',
  `return_msg` varchar(128) DEFAULT NULL COMMENT '返回信息',
  `result_code` varchar(16) DEFAULT NULL COMMENT '业务结果',
  `err_code` varchar(32) DEFAULT NULL COMMENT '错误代码',
  `err_code_des` varchar(128) DEFAULT NULL COMMENT '错误代码描述',
  `total_fee` int(11) DEFAULT NULL COMMENT '订单金额',
  `settlement_total_fee` int(11) DEFAULT NULL COMMENT '应结订单金额',
  `resp_content` text COMMENT '通知内容',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`) USING BTREE,
  KEY `idx_trade_no` (`trade_no`) USING BTREE,
  KEY `idx_out_trade_no` (`out_trade_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付通知';

-- ----------------------------
-- Records of t_pay_notify
-- ----------------------------

-- ----------------------------
-- Table structure for t_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege`;
CREATE TABLE `t_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `url` varchar(128) DEFAULT NULL COMMENT 'url',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级id',
  `permission` varchar(128) DEFAULT NULL COMMENT 'shiro权限',
  `status` varchar(8) DEFAULT NULL COMMENT '启用状态',
  `access_verify` tinyint(4) DEFAULT NULL COMMENT '访问授权',
  `sort_weight` int(11) DEFAULT NULL COMMENT '排序权重',
  `dis_position` varchar(16) DEFAULT NULL COMMENT '显示位置',
  `icon_class` varchar(32) DEFAULT NULL COMMENT '图标样式',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `img_url` varchar(255) DEFAULT NULL COMMENT '扩展图片url',
  `scope` varchar(16) DEFAULT NULL COMMENT '适用范围',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=855 DEFAULT CHARSET=utf8mb4 COMMENT='资源权限';

-- ----------------------------
-- Records of t_privilege
-- ----------------------------
INSERT INTO `t_privilege` VALUES ('1', '2017-07-04 16:33:49', '2017-07-04 18:59:10', '系统管理', '01', '#', 'cate', '0', 'sysMgr:view', 'on', '0', '100', 'none', 'fa fa-cog', ' ', null, 'user');
INSERT INTO `t_privilege` VALUES ('2', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '用户管理', '0101', 'admin/user/list.htm', 'menu', '1', 'userMgr:list', 'on', '1', '1', 'none', 'fa fa-users', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('3', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '角色管理', '0102', 'admin/role/list.htm', 'menu', '1', 'roleMgr:list', 'on', '1', '2', 'none', 'fa fa-address-card-o', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('4', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '权限管理', '0103', 'admin/priv/list.htm', 'menu', '1', 'privMgr:list', 'off', '1', '3', 'none', 'fa fa-filter', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('5', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'Shiro过滤链管理', '0104', 'admin/filter/chain/list.htm', 'menu', '1', 'chainMgr:list', 'off', '1', '4', 'none', 'fa fa-exchange', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('6', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '版本管理', '0105', 'admin/goods/list.htm', 'menu', '1', 'goodsMgr:list', 'off', '1', '5', 'none', 'fa fa-gift', '', null, 'user');
INSERT INTO `t_privilege` VALUES ('7', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '消息管理', '0106', 'admin/msg/list.htm', 'menu', '1', 'msgMgr:list', 'on', '1', '6', 'none', 'fa fa-bell-o', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('8', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【角色权限设置】', '', 'admin/role/forwardPrivSet.htm', 'page', '3', 'roleMgr:forwardPrivSet', 'on', '1', '999', 'none', 'fa fa-hourglass-start', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('9', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【发布版本】', '', 'admin/goods/forwardAdd.htm', 'page', '6', 'goodsMgr:forwardAdd', 'off', '1', '5', 'none', 'fa fa-gift', '', null, 'user');
INSERT INTO `t_privilege` VALUES ('10', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑版本】', '', 'admin/goods/forwardUpdate.htm', 'page', '6', 'goodsMgr:forwardUpdate', 'off', '1', '5', 'none', 'fa fa-gift', '', null, 'user');
INSERT INTO `t_privilege` VALUES ('11', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【发布消息】', '', 'admin/msg/forwardAdd.htm', 'page', '7', 'msgMgr:forwardAdd', 'on', '1', '6', 'none', 'fa fa-bell-o', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('12', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑消息】', '', 'admin/msg/forwardUpdate.htm', 'page', '7', 'msgMgr:forwardUpdate', 'on', '1', '6', 'none', 'fa fa-bell-o', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('13', '2017-07-17 16:33:49', '2017-07-17 16:33:49', '系统参数', '0107', 'admin/sys/cfg/list.htm', 'menu', '1', 'sysCfgMgr:list', 'on', '1', '7', 'none', 'fa fa-cogs', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('51', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '运营管理', '02', '#', 'cate', null, 'operMgr:view', 'on', '0', '200', 'none', 'fa fa-hourglass-start', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('52', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '用户统计', '0201', 'admin/cube/user/list.htm', 'menu', '51', 'userCube:list', 'on', '1', '1', 'none', 'fa fa-bar-chart', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('53', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '项目管理', '0202', 'admin/proj/list.htm', 'menu', '51', 'projMgr:list', 'on', '1', '2', 'none', 'fa fa-sitemap', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('54', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '订单管理', '0203', 'admin/order/list.htm', 'menu', '51', 'orderMgr:list', 'off', '1', '3', 'none', 'fa fa-cart-plus', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('55', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '下载记录', '0204', 'admin/goods/listDownloadRecord.htm', 'menu', '51', 'goodsMgr:listDownloadRecord', 'off', '1', '4', 'none', 'fa fa-signal', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('56', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '用户反馈', '0205', 'admin/suggest/list.htm', 'menu', '51', 'suggestMgr:list', 'on', '1', '5', 'none', 'fa fa-commenting', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('57', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【回复反馈】', '', 'admin/suggest/forwardReply.htm', 'page', '56', 'suggestMgr:forwardReply', 'on', '1', '5', 'none', 'fa fa-commenting', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('58', '2017-09-03 16:33:49', '2017-09-03 16:33:49', '系统项目角色', '0206', 'admin/proj/role/list.htm', 'menu', '51', 'projRoleMgr:list', 'on', '1', '6', 'none', 'fa fa-address-card-o', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('59', '2017-09-03 16:33:49', '2017-09-03 16:33:49', '【系统项目角色权限设置】', '', 'admin/proj/role/forwardPrivSet.htm', 'page', '58', 'projRoleMgr:forwardPrivSet', 'on', '1', '999', 'none', 'fa fa-hourglass-start', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('101', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '网站监控', '03', '#', 'cate', null, 'monitorMgr:view', 'on', '0', '300', 'none', 'fa fa-thermometer-half', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('102', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'http监控管理', '0301', 'admin/monitor/http/list.htm', 'menu', '101', 'httpMonitorMgr:list', 'on', '1', '1', 'none', 'fa fa-h-square', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('103', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '监控日志', '0302', 'admin/monitor/log/list.htm', 'menu', '101', 'monitorLogMgr:list', 'on', '1', '2', 'none', 'fa fa-video-camera', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('104', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报日志', '0303', 'admin/monitor/alarm/log/list.htm', 'menu', '101', 'monitorAlarmLogMgr:list', 'on', '1', '3', 'none', 'fa fa-warning', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('151', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '首页', '04', 'auth/home/home.htm', 'menu', null, 'home:view', 'on', '1', '400', 'none', 'fa fa-home', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('201', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '项目管理', '05', 'auth/proj/list.htm', 'menu', null, 'proj:list', 'on', '1', '500', 'none', 'fa fa-sitemap', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('251', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'API文档管理', '06', 'auth/doc/list.htm', 'menu', null, 'doc:list', 'on', '1', '600', 'none', 'fa fa-file-code-o', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('301', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '网站监控', '07', '#', 'cate', null, 'monitor:view', 'on', '0', '700', 'none', 'fa fa-thermometer-half', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('302', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'http监控管理', '0701', 'auth/monitor/http/list.htm', 'menu', '301', 'httpMonitor:list', 'on', '1', '1', 'none', 'fa fa-h-square', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('303', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '监控日志', '0702', 'auth/monitor/log/list.htm', 'menu', '301', 'monitorLog:list', 'on', '1', '2', 'none', 'fa fa-video-camera', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('304', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报日志', '0703', 'auth/monitor/alarm/log/list.htm', 'menu', '301', 'monitorAlarmLog:list', 'on', '1', '3', 'none', 'fa fa-warning', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('305', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报接收者管理', '0704', 'auth/monitor/alarm/receiver/list.htm', 'menu', '301', 'monitorAlarmReceiver:list', 'on', '1', '4', 'none', 'fa fa-user-circle', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('306', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报接收组管理', '0705', 'auth/monitor/alarm/group/list.htm', 'menu', '301', 'monitorAlarmGroup:list', 'on', '1', '5', 'none', 'fa fa-users', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('307', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【新增http监控】', '', 'auth/monitor/http/forwardAdd.htm', 'page', '302', 'httpMonitor:forwardAdd', 'on', '1', '1', 'none', 'fa fa-h-square', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('308', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑http监控】', '', 'auth/monitor/http/forwardUpdate.htm', 'page', '302', 'httpMonitor:forwardUpdate', 'on', '1', '1', 'none', 'fa fa-h-square', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('309', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【监控日志详情】', '', 'auth/monitor/log/info.htm', 'page', '303', 'monitorLog:view', 'on', '1', '2', 'none', 'fa fa-video-camera', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('310', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【警报日志详情】', '', 'auth/monitor/alarm/log/info.htm', 'page', '304', 'monitorAlarmLog:view', 'on', '1', '3', 'none', 'fa fa-warning', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('311', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【新增警报接收组】', '', 'auth/monitor/alarm/group/forwardAdd.htm', 'page', '306', 'monitorAlarmGroup:forwardAdd', 'on', '1', '5', 'none', 'fa fa-users', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('312', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑警报接收组】', '', 'auth/monitor/alarm/group/forwardUpdate.htm', 'page', '306', 'monitorAlarmGroup:forwardUpdate', 'on', '1', '5', 'none', 'fa fa-users', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('351', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '个人中心', '08', '#', 'cate', null, 'userCenter:view', 'on', '0', '800', 'none', 'fa fa-user', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('352', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '账号管理', '0801', 'auth/user/setting.htm', 'menu', '351', 'userSetting:view', 'on', '1', '1', 'none', 'fa fa-cog', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('353', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '消息管理', '0802', 'auth/msg/list.htm', 'menu', '351', 'msg:list', 'on', '1', '2', 'none', 'fa fa-bell-o', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('354', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '购买记录', '0803', 'auth/order/list.htm', 'menu', '351', 'order:list', 'on', '1', '3', 'none', 'fa fa-cart-plus', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('401', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '意见反馈', '09', 'auth/suggest/forwardSuggest.htm', 'menu', null, 'suggest:forwardSuggest', 'on', '1', '900', 'none', 'fa fa-commenting', null, null, 'user');
INSERT INTO `t_privilege` VALUES ('451', '2017-09-05 10:26:13', '2017-09-05 10:26:15', '项目基本信息', '10', 'auth/proj/info.htm', 'menu', null, 'projInfo:info', 'on', '1', '1', 'none', 'fa fa-info-circle', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('452', '2017-09-07 15:04:54', '2017-09-07 15:04:57', '项目基本信息-编辑', '1001', 'auth/proj/json/update.htm', 'oper', '451', 'projInfo:update', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('453', '2017-09-12 10:52:21', '2017-09-12 10:52:23', '删除项目', '1002', '/auth/proj/json/del.htm', 'oper', '451', 'projInfo:del', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('454', '2017-09-12 10:53:41', '2017-09-12 10:53:42', '复制项目', '1003', '/auth/proj/json/copy.htm', 'oper', '451', 'projInfo:copy', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('501', '2017-09-05 10:30:58', '2017-09-05 10:31:01', '项目成员', '11', 'auth/proj/mem/list.htm', 'menu', null, 'projMem:list', 'on', '1', '2', 'none', 'fa fa-users', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('502', '2017-09-07 15:27:17', '2017-09-07 15:27:20', '项目成员-邀请', '1101', 'auth/proj/mem/listForAdd.htm', 'page', '501', 'projMem:listForAdd', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('503', '2017-09-07 15:28:01', '2017-09-07 15:28:03', '项目成员-编辑', '1102', 'auth/proj/mem/json/update.htm', 'oper', '501', 'projMem:update', 'on', '1', '2', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('504', '2017-09-07 15:29:06', '2017-09-07 15:29:08', '项目成员-删除', '1103', 'auth/proj/mem/json/del.htm', 'oper', '501', 'projMem:del', 'on', '1', '3', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('505', '2017-09-07 15:30:27', '2017-09-07 15:30:29', '项目成员-添加', '1104', 'auth/proj/mem/json/add.htm', 'oper', '501', 'projMem:add', 'on', '1', '4', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('551', '2017-09-05 10:32:02', '2017-09-05 10:32:05', '文档视图', '12', 'auth/doc/view/list.htm', 'menu', null, 'docView:list', 'on', '1', '3', 'none', 'fa fa-camera', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('552', '2017-09-07 15:20:14', '2017-09-07 15:20:16', '文档视图-视图配置', '1201', 'auth/doc/view/forwardResSet.htm', 'page', '551', 'docView:forwardResSet', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('553', '2017-09-07 15:22:22', '2017-09-07 15:22:25', '文档视图-新增', '1202', 'auth/doc/view/json/add.htm', 'oper', '551', 'docView:add', 'on', '1', '2', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('554', '2017-09-07 15:23:05', '2017-09-07 15:23:07', '文档视图-编辑', '1203', 'auth/doc/view/json/update.htm', 'oper', '551', 'docView:update', 'on', '1', '3', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('555', '2017-09-07 15:23:41', '2017-09-07 15:23:43', '文档视图-删除', '1204', 'auth/doc/view/json/del.htm', 'oper', '551', 'docView:del', 'on', '1', '4', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('601', '2017-09-05 10:33:06', '2017-09-05 10:33:09', '变更通知', '13', 'auth/proj/mem/forwardSendNotice.htm', 'menu', null, 'projMem:forwardSendNotice', 'on', '1', '4', 'none', 'fa fa-envelope-o', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('651', '2017-09-05 10:33:50', '2017-09-05 10:33:52', '变更历史', '14', 'auth/proj/log/list.htm', 'menu', null, 'projLog:list', 'on', '1', '5', 'none', 'fa fa-calendar', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('652', '2017-09-07 15:11:24', '2017-09-07 15:11:26', '变更历史-新增', '1401', 'auth/proj/log/forwardAddLog.htm', 'oper', '651', 'projLog:forwardAddLog', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('653', '2017-09-07 15:17:04', '2017-09-07 15:17:07', '变更历史-删除', '1402', 'auth/proj/log/json/del.htm', 'oper', '651', 'projLog:del', 'on', '1', '2', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('701', '2017-09-05 10:35:55', '2017-09-05 10:35:57', '接口管理', '15', 'auth/doc/inter/tree/list.htm', 'menu', null, 'docInterTree:list', 'on', '1', '6', 'none', 'fa fa-table', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('702', '2017-09-06 16:05:32', '2017-09-06 16:05:34', '文档设置-保存', '1501', 'auth/doc/json/update.htm', 'oper', '701', 'docInfo:update', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('703', '2017-09-06 16:11:52', '2017-09-06 16:11:53', '请求基路径-保存', '1502', 'auth/doc/env/json/add.htm', 'oper', '701', 'docEnv:add', 'on', '1', '2', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('704', '2017-09-06 16:13:52', '2017-09-06 16:13:55', '公共请求参数-保存', '1503', 'auth/doc/common/param/json/add.htm', 'oper', '701', 'docCommonParam:add', 'on', '1', '3', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('705', '2017-09-07 11:33:46', '2017-09-07 11:33:49', '模块-新增', '1504', 'auth/doc/module/json/add.htm', 'oper', '701', 'docModule:add', 'on', '1', '4', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('706', '2017-09-07 11:34:45', '2017-09-07 11:34:47', '模块-编辑', '1505', 'auth/doc/module/json/update.htm', 'oper', '701', 'docModule:update', 'on', '1', '5', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('707', '2017-09-07 11:35:29', '2017-09-07 11:35:31', '返回码-新增', '1506', 'auth/doc/code/json/add.htm', 'oper', '701', 'docCode:add', 'on', '1', '6', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('708', '2017-09-07 11:37:17', '2017-09-07 11:37:19', '返回码-编辑', '1507', 'auth/doc/code/json/update.htm', 'oper', '701', 'docCode:update', 'on', '1', '7', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('709', '2017-09-07 14:51:11', '2017-09-07 14:51:14', '接口树-查看', '1508', 'auth/doc/inter/tree/json/load.htm', 'oper', '701', 'interTree:load', 'on', '1', '8', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('710', '2017-09-07 14:52:09', '2017-09-07 14:52:11', '接口树-排序', '1509', 'auth/doc/inter/tree/json/sort.htm', 'oper', '701', 'interTree:sort', 'on', '1', '9', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('711', '2017-09-07 14:52:55', '2017-09-07 14:52:57', '接口树-删除节点', '1510', 'auth/doc/inter/tree/json/del.htm', 'oper', '701', 'interTree:del', 'on', '1', '10', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('712', '2017-09-07 14:54:34', '2017-09-07 14:54:36', '接口-查看', '1511', 'auth/doc/inter/json/detailInfo.htm', 'oper', '701', 'inter:detailInfo', 'on', '1', '11', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('713', '2017-09-07 14:56:27', '2017-09-07 14:56:29', '接口-新增', '1512', 'auth/doc/inter/json/addDetail.htm', 'oper', '701', 'inter:addDetail', 'on', '1', '12', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('714', '2017-09-07 14:57:17', '2017-09-07 14:57:19', '接口-编辑', '1513', 'auth/doc/inter/json/updateDetail.htm', 'oper', '701', 'inter:updateDetail', 'on', '1', '13', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('715', '2017-09-12 14:40:07', '2017-09-12 14:40:10', '分享设置', '1514', 'auth/doc/json/updateShareInfo.htm', 'oper', '701', 'docShare:update', 'on', '1', '14', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('751', '2017-09-05 10:36:42', '2017-09-05 10:36:44', '数据结构', '16', 'auth/doc/schema/list.htm', 'menu', null, 'docSchema:list', 'on', '1', '7', 'none', 'fa fa-object-group', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('752', '2017-09-07 15:07:40', '2017-09-07 15:07:43', '数据结构-新增', '1601', 'auth/doc/schema/json/add.htm', 'oper', '751', 'docSchema:add', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('753', '2017-09-07 15:08:34', '2017-09-07 15:08:36', '数据结构-删除', '1602', 'auth/doc/schema/json/del.htm', 'oper', '751', 'docSchema:del', 'on', '1', '2', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('754', '2017-09-07 15:09:21', '2017-09-07 15:09:24', '数据结构-编辑', '1603', 'auth/doc/schema/json/update.htm', 'oper', '751', 'docSchema:update', 'on', '1', '3', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('755', '2017-09-07 15:10:02', '2017-09-07 15:10:07', '数据结构-复制', '1604', 'auth/doc/schema/json/copy.htm', 'oper', '751', 'docSchema:copy', 'on', '1', '4', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('801', '2017-09-03 16:33:49', '2017-09-03 16:33:49', '项目角色', '17', 'auth/proj/role/list.htm', 'menu', null, 'projRole:list', 'on', '1', '8', 'none', 'fa fa-address-card-o', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('802', '2017-09-03 16:33:49', '2017-09-03 16:33:49', '【项目角色权限设置】', '1701', 'auth/proj/role/forwardPrivSet.htm', 'page', '801', 'projRole:forwardPrivSet', 'on', '1', '999', 'none', 'fa fa-hourglass-start', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('851', '2017-09-26 17:03:14', '2017-09-26 17:03:16', '接口归档', '18', 'auth/doc/archive/list.htm', 'menu', null, 'docArchive:list', 'on', '1', '9', 'none', 'fa fa-clock-o', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('852', '2017-09-26 17:03:14', '2017-09-26 17:03:14', '接口归档-新增', '1801', 'auth/doc/archive/json/add.htm', 'oper', '851', 'docArchive:add', 'on', '1', '1', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('853', '2017-09-26 17:03:14', '2017-09-26 17:03:14', '接口归档-编辑', '1802', 'auth/doc/archive/json/update.htm', 'oper', '851', 'docArchive:update', 'on', '1', '2', 'none', '', null, null, 'proj');
INSERT INTO `t_privilege` VALUES ('854', '2017-09-26 17:03:14', '2017-09-26 17:03:14', '接口归档-删除', '1803', 'auth/doc/archive/json/del.htm', 'oper', '851', 'docArchive:del', 'on', '1', '3', 'none', '', null, null, 'proj');

-- ----------------------------
-- Table structure for t_proj
-- ----------------------------
DROP TABLE IF EXISTS `t_proj`;
CREATE TABLE `t_proj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `status` varchar(16) DEFAULT NULL COMMENT '项目状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='项目信息';

-- ----------------------------
-- Records of t_proj
-- ----------------------------
INSERT INTO `t_proj` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', 'sosoapi_demo', 'sosoapi_demo', '该demo主要用于汇集常见的接口编辑事例。\n<br/>\n接口测试过程中如果出现ajax跨域请求问题可参考\"常见问题->线下部署\"中的跨域请求相关资料。\n<br/>\n<br/>\n技术交流群:305629848', 'open');

-- ----------------------------
-- Table structure for t_proj_log
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_log`;
CREATE TABLE `t_proj_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '发布者id',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='变更记录';

-- ----------------------------
-- Records of t_proj_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_proj_mem
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_mem`;
CREATE TABLE `t_proj_mem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role` varchar(32) DEFAULT NULL,
  `proj_role_id` bigint(20) DEFAULT NULL COMMENT '项目角色id',
  `proj_nick_name` varchar(255) DEFAULT NULL COMMENT '所在项目昵称',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_proj_id` (`proj_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='项目成员';

-- ----------------------------
-- Records of t_proj_mem
-- ----------------------------
INSERT INTO `t_proj_mem` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', '1', 'admin', '1', null);

-- ----------------------------
-- Table structure for t_proj_role
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_role`;
CREATE TABLE `t_proj_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `description` text COMMENT '角色描述',
  `proj_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `status` varchar(8) DEFAULT NULL COMMENT '启用状态',
  `sort_weight` int(11) DEFAULT NULL COMMENT '排序权重',
  `home_url` varchar(255) DEFAULT NULL COMMENT '主页url',
  `del_disabled` tinyint(4) DEFAULT NULL COMMENT '禁止删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='项目角色';

-- ----------------------------
-- Records of t_proj_role
-- ----------------------------
INSERT INTO `t_proj_role` VALUES ('1', '2017-09-10 18:45:55', '2017-09-10 18:45:55', 'admin', '管理员', '', null, 'on', '1', '', '0');
INSERT INTO `t_proj_role` VALUES ('2', '2017-09-10 18:46:11', '2017-09-10 18:46:11', 'guest', '访客', '', null, 'on', '2', '', '0');
INSERT INTO `t_proj_role` VALUES ('3', '2017-09-10 18:48:39', '2017-09-10 18:49:07', 'demo', 'demo', '', null, 'on', '3', '', '0');

-- ----------------------------
-- Table structure for t_proj_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_role_privilege`;
CREATE TABLE `t_proj_role_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_role_id` bigint(20) DEFAULT NULL COMMENT '项目角色id',
  `proj_privilege_id` bigint(20) DEFAULT NULL COMMENT '项目权限id',
  PRIMARY KEY (`id`),
  KEY `idx_proj_role_id` (`proj_role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COMMENT='项目角色-权限';

-- ----------------------------
-- Records of t_proj_role_privilege
-- ----------------------------
INSERT INTO `t_proj_role_privilege` VALUES ('41', '2017-09-18 17:47:34', '2017-09-18 17:47:34', '2', '451');
INSERT INTO `t_proj_role_privilege` VALUES ('42', '2017-09-18 17:47:34', '2017-09-18 17:47:34', '2', '701');
INSERT INTO `t_proj_role_privilege` VALUES ('43', '2017-09-18 17:47:34', '2017-09-18 17:47:34', '2', '709');
INSERT INTO `t_proj_role_privilege` VALUES ('44', '2017-09-18 17:47:34', '2017-09-18 17:47:34', '2', '712');
INSERT INTO `t_proj_role_privilege` VALUES ('45', '2017-09-18 17:47:34', '2017-09-18 17:47:34', '2', '751');
INSERT INTO `t_proj_role_privilege` VALUES ('46', '2017-09-18 17:48:06', '2017-09-18 17:48:06', '3', '451');
INSERT INTO `t_proj_role_privilege` VALUES ('47', '2017-09-18 17:48:06', '2017-09-18 17:48:06', '3', '551');
INSERT INTO `t_proj_role_privilege` VALUES ('48', '2017-09-18 17:48:06', '2017-09-18 17:48:06', '3', '701');
INSERT INTO `t_proj_role_privilege` VALUES ('49', '2017-09-18 17:48:06', '2017-09-18 17:48:06', '3', '709');
INSERT INTO `t_proj_role_privilege` VALUES ('50', '2017-09-18 17:48:06', '2017-09-18 17:48:06', '3', '712');
INSERT INTO `t_proj_role_privilege` VALUES ('51', '2017-09-18 17:48:06', '2017-09-18 17:48:06', '3', '751');
INSERT INTO `t_proj_role_privilege` VALUES ('52', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '451');
INSERT INTO `t_proj_role_privilege` VALUES ('53', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '454');
INSERT INTO `t_proj_role_privilege` VALUES ('54', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '453');
INSERT INTO `t_proj_role_privilege` VALUES ('55', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '452');
INSERT INTO `t_proj_role_privilege` VALUES ('56', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '501');
INSERT INTO `t_proj_role_privilege` VALUES ('57', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '502');
INSERT INTO `t_proj_role_privilege` VALUES ('58', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '503');
INSERT INTO `t_proj_role_privilege` VALUES ('59', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '504');
INSERT INTO `t_proj_role_privilege` VALUES ('60', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '505');
INSERT INTO `t_proj_role_privilege` VALUES ('61', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '551');
INSERT INTO `t_proj_role_privilege` VALUES ('62', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '552');
INSERT INTO `t_proj_role_privilege` VALUES ('63', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '553');
INSERT INTO `t_proj_role_privilege` VALUES ('64', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '554');
INSERT INTO `t_proj_role_privilege` VALUES ('65', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '555');
INSERT INTO `t_proj_role_privilege` VALUES ('66', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '601');
INSERT INTO `t_proj_role_privilege` VALUES ('67', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '651');
INSERT INTO `t_proj_role_privilege` VALUES ('68', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '652');
INSERT INTO `t_proj_role_privilege` VALUES ('69', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '653');
INSERT INTO `t_proj_role_privilege` VALUES ('70', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '701');
INSERT INTO `t_proj_role_privilege` VALUES ('71', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '702');
INSERT INTO `t_proj_role_privilege` VALUES ('72', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '703');
INSERT INTO `t_proj_role_privilege` VALUES ('73', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '704');
INSERT INTO `t_proj_role_privilege` VALUES ('74', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '705');
INSERT INTO `t_proj_role_privilege` VALUES ('75', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '706');
INSERT INTO `t_proj_role_privilege` VALUES ('76', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '707');
INSERT INTO `t_proj_role_privilege` VALUES ('77', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '708');
INSERT INTO `t_proj_role_privilege` VALUES ('78', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '709');
INSERT INTO `t_proj_role_privilege` VALUES ('79', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '710');
INSERT INTO `t_proj_role_privilege` VALUES ('80', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '711');
INSERT INTO `t_proj_role_privilege` VALUES ('81', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '712');
INSERT INTO `t_proj_role_privilege` VALUES ('82', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '713');
INSERT INTO `t_proj_role_privilege` VALUES ('83', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '714');
INSERT INTO `t_proj_role_privilege` VALUES ('84', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '715');
INSERT INTO `t_proj_role_privilege` VALUES ('85', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '751');
INSERT INTO `t_proj_role_privilege` VALUES ('86', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '752');
INSERT INTO `t_proj_role_privilege` VALUES ('87', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '753');
INSERT INTO `t_proj_role_privilege` VALUES ('88', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '754');
INSERT INTO `t_proj_role_privilege` VALUES ('89', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '755');
INSERT INTO `t_proj_role_privilege` VALUES ('90', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '801');
INSERT INTO `t_proj_role_privilege` VALUES ('91', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '802');
INSERT INTO `t_proj_role_privilege` VALUES ('92', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '851');
INSERT INTO `t_proj_role_privilege` VALUES ('93', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '852');
INSERT INTO `t_proj_role_privilege` VALUES ('94', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '853');
INSERT INTO `t_proj_role_privilege` VALUES ('95', '2017-10-08 15:50:24', '2017-10-08 15:50:24', '1', '854');

-- ----------------------------
-- Table structure for t_refund
-- ----------------------------
DROP TABLE IF EXISTS `t_refund`;
CREATE TABLE `t_refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `refund_no` varchar(32) DEFAULT NULL COMMENT '退款单号',
  `out_refund_no` varchar(32) DEFAULT NULL COMMENT '外部退款单号',
  `refund_type` varchar(16) DEFAULT NULL COMMENT '退款类型',
  `refund_fee` int(11) DEFAULT NULL COMMENT '退款申请金额',
  `refund_reason` varchar(256) DEFAULT NULL COMMENT '申请退款原因',
  `settlement_refund_fee` int(11) DEFAULT NULL COMMENT '退款金额',
  `refund_status` varchar(16) DEFAULT NULL COMMENT '退款状态',
  `refund_status_desc` varchar(256) DEFAULT NULL COMMENT '退款状态描述',
  `refund_recv_accout` varchar(64) DEFAULT NULL COMMENT '退款入账账户',
  `user_id` bigint(20) DEFAULT NULL COMMENT '申请人',
  `oper_id` bigint(20) DEFAULT NULL COMMENT '操作者id',
  `approve_status` varchar(16) DEFAULT NULL COMMENT '审批状态',
  `approve_date` datetime DEFAULT NULL COMMENT '审批时间',
  `approve_remark` varchar(256) DEFAULT NULL COMMENT '审批说明',
  `resp_content` text COMMENT '响应信息',
  PRIMARY KEY (`id`),
  KEY `idx_trade_no` (`order_id`) USING BTREE,
  KEY `idx_refund_no` (`refund_no`) USING BTREE,
  KEY `idx_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款';

-- ----------------------------
-- Records of t_refund
-- ----------------------------

-- ----------------------------
-- Table structure for t_refund_item
-- ----------------------------
DROP TABLE IF EXISTS `t_refund_item`;
CREATE TABLE `t_refund_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `refund_id` bigint(20) DEFAULT NULL COMMENT '退款单id',
  `order_item_id` bigint(20) DEFAULT NULL COMMENT '订单项id',
  `refund_fee` int(11) DEFAULT NULL COMMENT '退款申请金额',
  `settlement_refund_fee` int(11) DEFAULT NULL COMMENT '退款金额',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`) USING BTREE,
  KEY `idx_refund_id` (`refund_id`) USING BTREE,
  KEY `idx_order_item_id` (`order_item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款项';

-- ----------------------------
-- Records of t_refund_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_resp_schema
-- ----------------------------
DROP TABLE IF EXISTS `t_resp_schema`;
CREATE TABLE `t_resp_schema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `cust_schema` text COMMENT '结构',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ref_schema_id` bigint(20) DEFAULT NULL,
  `mock_data` text COMMENT 'mock数据',
  `mock_rule` text COMMENT 'mock规则',
  `mock_type` varchar(16) DEFAULT NULL COMMENT 'mock类型',
  PRIMARY KEY (`id`),
  KEY `idx_doc_id` (`doc_id`) USING BTREE,
  KEY `idx_module_id` (`module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='响应数据结构';

-- ----------------------------
-- Records of t_resp_schema
-- ----------------------------
INSERT INTO `t_resp_schema` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, 'ErrorCode', null, '错误码', '[{\"code\":\"errorCode\",\"description\":\"错误编码\",\"type\":\"sys_string\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"errorMsg\",\"description\":\"错误提示信息\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', 'sys_object', null, null, null, null);
INSERT INTO `t_resp_schema` VALUES ('2', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, 'SimpleUserInfo', null, '简单用户信息', '[{\"code\":\"nickName\",\"description\":\"昵称\",\"type\":\"sys_string\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"email\",\"description\":\"邮箱\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"age\",\"description\":\"年龄\",\"type\":\"sys_integer_int32\",\"nodeId\":\"12\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', 'sys_object', null, null, null, null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `status` varchar(8) DEFAULT NULL COMMENT '启用状态',
  `sort_weight` int(11) DEFAULT NULL COMMENT '排序权重',
  `home_url` varchar(255) DEFAULT NULL COMMENT '主页url',
  `del_disabled` tinyint(4) DEFAULT NULL COMMENT '禁止删除',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '2017-07-01 22:36:47', '2017-07-01 22:36:47', '管理员', 'admin', 'on', '1', '/admin/cube/user/list.htm', '1', null);
INSERT INTO `t_role` VALUES ('2', '2017-07-01 22:37:00', '2017-07-01 22:37:00', 'vip用户', 'vip', 'on', '2', '', '1', null);
INSERT INTO `t_role` VALUES ('3', '2017-07-01 22:37:11', '2017-07-01 22:37:11', '普通用户', 'normal', 'on', '3', '', '1', null);

-- ----------------------------
-- Table structure for t_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_role_privilege`;
CREATE TABLE `t_role_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `privilege_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限';

-- ----------------------------
-- Records of t_role_privilege
-- ----------------------------
INSERT INTO `t_role_privilege` VALUES ('1', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '1');
INSERT INTO `t_role_privilege` VALUES ('2', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '2');
INSERT INTO `t_role_privilege` VALUES ('3', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '3');
INSERT INTO `t_role_privilege` VALUES ('4', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '4');
INSERT INTO `t_role_privilege` VALUES ('5', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '5');
INSERT INTO `t_role_privilege` VALUES ('6', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '10');
INSERT INTO `t_role_privilege` VALUES ('7', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '6');
INSERT INTO `t_role_privilege` VALUES ('8', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '9');
INSERT INTO `t_role_privilege` VALUES ('9', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '11');
INSERT INTO `t_role_privilege` VALUES ('10', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '12');
INSERT INTO `t_role_privilege` VALUES ('11', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '7');
INSERT INTO `t_role_privilege` VALUES ('12', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '8');
INSERT INTO `t_role_privilege` VALUES ('13', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '51');
INSERT INTO `t_role_privilege` VALUES ('14', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '52');
INSERT INTO `t_role_privilege` VALUES ('15', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '53');
INSERT INTO `t_role_privilege` VALUES ('16', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '54');
INSERT INTO `t_role_privilege` VALUES ('17', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '55');
INSERT INTO `t_role_privilege` VALUES ('18', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '57');
INSERT INTO `t_role_privilege` VALUES ('19', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '56');
INSERT INTO `t_role_privilege` VALUES ('20', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '101');
INSERT INTO `t_role_privilege` VALUES ('21', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '102');
INSERT INTO `t_role_privilege` VALUES ('22', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '103');
INSERT INTO `t_role_privilege` VALUES ('23', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '104');
INSERT INTO `t_role_privilege` VALUES ('24', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '351');
INSERT INTO `t_role_privilege` VALUES ('25', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '352');
INSERT INTO `t_role_privilege` VALUES ('26', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '353');
INSERT INTO `t_role_privilege` VALUES ('27', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '151');
INSERT INTO `t_role_privilege` VALUES ('28', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '201');
INSERT INTO `t_role_privilege` VALUES ('29', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '251');
INSERT INTO `t_role_privilege` VALUES ('30', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '301');
INSERT INTO `t_role_privilege` VALUES ('31', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '302');
INSERT INTO `t_role_privilege` VALUES ('32', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '307');
INSERT INTO `t_role_privilege` VALUES ('33', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '308');
INSERT INTO `t_role_privilege` VALUES ('34', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '303');
INSERT INTO `t_role_privilege` VALUES ('35', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '309');
INSERT INTO `t_role_privilege` VALUES ('36', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '304');
INSERT INTO `t_role_privilege` VALUES ('37', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '310');
INSERT INTO `t_role_privilege` VALUES ('38', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '305');
INSERT INTO `t_role_privilege` VALUES ('39', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '311');
INSERT INTO `t_role_privilege` VALUES ('40', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '312');
INSERT INTO `t_role_privilege` VALUES ('41', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '306');
INSERT INTO `t_role_privilege` VALUES ('42', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '351');
INSERT INTO `t_role_privilege` VALUES ('43', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '352');
INSERT INTO `t_role_privilege` VALUES ('44', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '353');
INSERT INTO `t_role_privilege` VALUES ('45', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '354');
INSERT INTO `t_role_privilege` VALUES ('46', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '401');
INSERT INTO `t_role_privilege` VALUES ('47', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '151');
INSERT INTO `t_role_privilege` VALUES ('48', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '201');
INSERT INTO `t_role_privilege` VALUES ('49', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '251');
INSERT INTO `t_role_privilege` VALUES ('50', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '301');
INSERT INTO `t_role_privilege` VALUES ('51', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '302');
INSERT INTO `t_role_privilege` VALUES ('52', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '307');
INSERT INTO `t_role_privilege` VALUES ('53', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '308');
INSERT INTO `t_role_privilege` VALUES ('54', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '303');
INSERT INTO `t_role_privilege` VALUES ('55', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '309');
INSERT INTO `t_role_privilege` VALUES ('56', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '304');
INSERT INTO `t_role_privilege` VALUES ('57', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '310');
INSERT INTO `t_role_privilege` VALUES ('58', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '305');
INSERT INTO `t_role_privilege` VALUES ('59', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '311');
INSERT INTO `t_role_privilege` VALUES ('60', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '312');
INSERT INTO `t_role_privilege` VALUES ('61', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '306');
INSERT INTO `t_role_privilege` VALUES ('62', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '351');
INSERT INTO `t_role_privilege` VALUES ('63', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '352');
INSERT INTO `t_role_privilege` VALUES ('64', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '353');
INSERT INTO `t_role_privilege` VALUES ('65', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '354');
INSERT INTO `t_role_privilege` VALUES ('66', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '401');
INSERT INTO `t_role_privilege` VALUES ('67', '2017-07-17 16:27:38', '2017-07-17 16:27:38', '1', '13');
INSERT INTO `t_role_privilege` VALUES ('68', '2017-09-18 17:41:39', '2017-09-18 17:41:39', '1', '58');
INSERT INTO `t_role_privilege` VALUES ('69', '2017-09-18 17:41:39', '2017-09-18 17:41:39', '1', '59');

-- ----------------------------
-- Table structure for t_suggest
-- ----------------------------
DROP TABLE IF EXISTS `t_suggest`;
CREATE TABLE `t_suggest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` text,
  `deal_date` datetime DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT 'bug',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板';

-- ----------------------------
-- Records of t_suggest
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_cfg
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_cfg`;
CREATE TABLE `t_sys_cfg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `value` varchar(64) DEFAULT NULL COMMENT '值',
  `status` varchar(8) DEFAULT NULL COMMENT '启用状态',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `sort_weight` int(11) DEFAULT NULL COMMENT '排序权重',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='系统参数';

-- ----------------------------
-- Records of t_sys_cfg
-- ----------------------------
INSERT INTO `t_sys_cfg` VALUES ('1', '2017-07-18 15:11:25', '2017-07-18 16:00:44', '邮件设置', 'mailSetting', '', 'on', null, '20', 'cate', '用于配置发送邮件时，内部的标题等信息');
INSERT INTO `t_sys_cfg` VALUES ('2', '2017-07-18 15:12:41', '2017-07-18 18:31:45', '网站名称', 'web.name', 'SosoApi', 'on', '1', '10', 'item', '');
INSERT INTO `t_sys_cfg` VALUES ('3', '2017-07-18 15:14:33', '2017-07-18 18:31:49', '网站地址', 'web.site', 'http://www.sosoapi.com', 'on', '1', '20', 'item', '');
INSERT INTO `t_sys_cfg` VALUES ('4', '2017-07-18 15:15:46', '2017-07-18 18:31:52', '网站联系人邮箱', 'web.service.email', 'service@sosoapi.com', 'on', '1', '30', 'item', '');
INSERT INTO `t_sys_cfg` VALUES ('5', '2017-07-18 15:16:34', '2017-07-18 18:31:58', '网站联系人电话', 'web.service.tel', '', 'on', '1', '40', 'item', '');
INSERT INTO `t_sys_cfg` VALUES ('6', '2017-07-18 15:17:03', '2017-07-18 16:00:37', '站点设置', 'siteSetting', '', 'on', null, '10', 'cate', '用于设置网站相关功能开关');
INSERT INTO `t_sys_cfg` VALUES ('7', '2017-07-18 15:19:42', '2017-07-18 18:28:18', '启用资源cdn', 'web.cdn.enable', 'false', 'on', '6', '10', 'item', '用于静态资源如jquery等是否走外网cdn。\ntrue：启用\nfalse：不启用，如果无法访问外网则设置为false.');
INSERT INTO `t_sys_cfg` VALUES ('8', '2017-07-18 15:21:41', '2017-07-18 18:01:19', '允许注册', 'enable.regist', 'true', 'on', '6', '20', 'item', '用于设置是否允许用户注册。\ntrue：登录页有注册入口\nfalse：登录页无注册入口');
INSERT INTO `t_sys_cfg` VALUES ('9', '2017-07-18 15:22:29', '2017-07-18 18:29:05', '分页展示页码数', 'show.page.number', '5', 'on', '6', '30', 'item', '用于列表分页展示的页码数量。');
INSERT INTO `t_sys_cfg` VALUES ('10', '2017-07-18 15:23:42', '2017-07-18 18:29:10', '每页显示记录数', 'show.page.size', '10', 'on', '6', '40', 'item', '分页每页展示的记录数');
INSERT INTO `t_sys_cfg` VALUES ('11', '2017-07-18 15:24:31', '2017-07-18 18:30:43', '最多登录失败次数', 'limit.login.fail.count', '5', 'on', '6', '50', 'item', '一天最多登录失败次数，超过账号会被锁定，次日自动解锁。');
INSERT INTO `t_sys_cfg` VALUES ('12', '2017-07-18 15:25:52', '2017-07-18 15:25:55', '监控设置', 'monitorSetting', null, 'on', null, '30', 'cate', '用于监控功能相关设置');
INSERT INTO `t_sys_cfg` VALUES ('13', '2017-07-18 15:27:14', '2017-07-18 18:05:29', '单用户允许创建数', 'limit.monitor.count', '2', 'on', '12', '10', 'item', '单用户允许创建的最多监控数目。');
INSERT INTO `t_sys_cfg` VALUES ('14', '2017-07-18 15:29:06', '2017-07-18 15:29:08', '项目设置', 'projSetting', null, 'on', null, '40', 'cate', '接口管理相关配置');
INSERT INTO `t_sys_cfg` VALUES ('15', '2017-07-18 15:30:50', '2017-07-18 18:42:18', '接口树是否展示列表', 'enable.inter.list', 'false', 'on', '14', '10', 'item', '用于接口管理的树结构模块节点菜单是否展示接口列表设置。');
INSERT INTO `t_sys_cfg` VALUES ('16', '2017-07-18 16:01:07', '2017-07-18 16:01:09', 'SEO设置', 'seoSetting', null, 'on', null, '50', 'cate', 'seo相关设置');
INSERT INTO `t_sys_cfg` VALUES ('17', '2017-07-18 16:01:42', '2017-07-18 18:40:43', '网页tittle后缀', 'web.page.title.postfix', '', 'on', '16', '10', 'item', '');
INSERT INTO `t_sys_cfg` VALUES ('18', '2017-07-18 16:02:50', '2017-07-18 18:40:48', '网页description', 'web.page.meta.description', '', 'on', '16', '20', 'item', '');
INSERT INTO `t_sys_cfg` VALUES ('19', '2017-07-18 16:03:58', '2017-07-18 18:40:53', '网页keywords', 'web.page.meta.keywords', '', 'on', '16', '30', 'item', '');
INSERT INTO `t_sys_cfg` VALUES ('20', '2017-07-25 09:41:23', '2017-07-25 09:41:25', '允许邀请注册用户为成员', 'enable.proj.mem.all', 'false', 'on', '14', '20', 'item', '项目成员管理是否可直接邀请已注册用户，默认只能直接邀请之前有一起参与过项目的用户。');
INSERT INTO `t_sys_cfg` VALUES ('21', '2017-07-31 11:15:03', '2017-07-31 11:15:05', '注册用户邮箱验证', 'regist.email.valid', 'true', 'on', '6', '60', 'item', '注册用户是否需要进行邮箱验证');

-- ----------------------------
-- Table structure for t_sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_msg`;
CREATE TABLE `t_sys_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '发布者id',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `cancel` tinyint(4) DEFAULT NULL COMMENT '撤销',
  `cancel_date` datetime DEFAULT NULL COMMENT '撤销时间',
  `msg_type` varchar(16) DEFAULT NULL COMMENT '消息类型',
  `msg_status` varchar(16) DEFAULT NULL COMMENT '消息状态',
  `role_id` bigint(20) DEFAULT NULL COMMENT '接收消息角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统消息';

-- ----------------------------
-- Records of t_sys_msg
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_user_basic`;
CREATE TABLE `t_user_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否验证',
  `locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  `locked_date` datetime DEFAULT NULL COMMENT '锁定时间',
  `register_ip` varchar(32) DEFAULT NULL COMMENT '注册ip',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息';

-- ----------------------------
-- Records of t_user_basic
-- ----------------------------
INSERT INTO `t_user_basic` VALUES ('1', '2016-10-18 16:46:02', '2016-10-18 16:46:02', null, 'admin@qq.com', 'b5cf498b70a176efeacbc5b07d88e0da76a7f4cb', '1', '0', null, '0:0:0:0:0:0:0:1', '1');

-- ----------------------------
-- Table structure for t_user_cube
-- ----------------------------
DROP TABLE IF EXISTS `t_user_cube`;
CREATE TABLE `t_user_cube` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `total_regist_count` int(11) DEFAULT NULL COMMENT '总注册用户数',
  `day_regist_count` int(11) DEFAULT NULL COMMENT '日注册用户数',
  `day_login_count` int(11) DEFAULT NULL COMMENT '日登录用户数',
  `day_old_login_count` int(11) DEFAULT NULL COMMENT '日登陆老用户数',
  `total_proj_count` int(11) DEFAULT NULL COMMENT '总项目数',
  `day_proj_count` int(11) DEFAULT NULL COMMENT '日新增项目数',
  `total_vip_count` int(11) DEFAULT '0' COMMENT '总vip用户数',
  `day_vip_count` int(11) DEFAULT '0' COMMENT '日新增vip用户数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户统计';

-- ----------------------------
-- Records of t_user_cube
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_user_detail`;
CREATE TABLE `t_user_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `area_id` bigint(20) DEFAULT '-1' COMMENT '区域id',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `gender` varchar(11) DEFAULT '1' COMMENT '性别',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `head_url` varchar(255) DEFAULT NULL COMMENT '头像url',
  `university` varchar(32) DEFAULT NULL COMMENT '毕业院校',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户详细信息';

-- ----------------------------
-- Records of t_user_detail
-- ----------------------------
INSERT INTO `t_user_detail` VALUES ('1', '2016-10-18 16:46:02', '2016-10-18 16:46:02', '1', null, null, null, null, null, null, 'admin', null, null, null);

-- ----------------------------
-- Table structure for t_user_ext
-- ----------------------------
DROP TABLE IF EXISTS `t_user_ext`;
CREATE TABLE `t_user_ext` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `last_fetch_sys_msg_date` datetime DEFAULT NULL COMMENT '最后获取系统消息时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展信息';

-- ----------------------------
-- Records of t_user_ext
-- ----------------------------
INSERT INTO `t_user_ext` VALUES ('1', '2016-10-18 16:46:46', '2016-10-18 16:46:46', '1', '2017-10-08 15:48:57');

-- ----------------------------
-- Table structure for t_user_login
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login`;
CREATE TABLE `t_user_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `login_date` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_failure_count` int(11) NOT NULL COMMENT '连续登陆失败次数',
  `login_count` int(11) DEFAULT '0',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登陆ip',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `login_type` varchar(16) DEFAULT '0' COMMENT '登陆方式',
  `login_status` varchar(16) DEFAULT NULL,
  `auth_code` varchar(8) DEFAULT NULL COMMENT '登陆验证码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户登陆信息';

-- ----------------------------
-- Records of t_user_login
-- ----------------------------
INSERT INTO `t_user_login` VALUES ('1', '2016-10-18 16:46:02', '2017-10-08 15:50:53', '2017-10-08 15:48:56', '0', '4', '0:0:0:0:0:0:0:1', '1', 'email', 'offline', null);

-- ----------------------------
-- Table structure for t_user_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_user_msg`;
CREATE TABLE `t_user_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `sys` tinyint(4) DEFAULT NULL COMMENT '是否系统消息',
  `sys_msg_id` bigint(20) DEFAULT NULL COMMENT '系统消息id',
  `sender_id` bigint(20) DEFAULT NULL COMMENT '发送者id',
  `receiver_id` bigint(20) DEFAULT NULL COMMENT '接收者id',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `msg_type` varchar(16) DEFAULT NULL COMMENT '消息类型',
  `deal` tinyint(4) DEFAULT NULL COMMENT '是否已读',
  `deal_date` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户消息';

-- ----------------------------
-- Records of t_user_msg
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_token
-- ----------------------------
DROP TABLE IF EXISTS `t_user_token`;
CREATE TABLE `t_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `login_ip` varchar(64) DEFAULT NULL COMMENT '登陆ip',
  `token` varchar(128) DEFAULT NULL COMMENT 'token信息',
  `expire_date` datetime DEFAULT NULL COMMENT '失效时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_token` (`token`) USING BTREE,
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='登陆凭证';

-- ----------------------------
-- Records of t_user_token
-- ----------------------------
INSERT INTO `t_user_token` VALUES ('1', '2016-10-18 16:46:47', '2016-10-18 16:46:47', '1', '0:0:0:0:0:0:0:1', 'c5f3b2a90c2a888dac5b33c5884d1c30', '2017-10-15 15:48:57');
