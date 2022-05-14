update t_privilege p1
inner join t_privilege p2 on (
	p1. code = p2. code
	and p2.type <> 'page'
)
set p1. code = '',
 p1.parent_id = p2.id
where
	p1.type = 'page';

INSERT INTO `t_privilege` (`id`, `create_date`, `modify_date`, `name`, `code`, `url`, `type`, `parent_id`, `permission`, `status`, `access_verify`, `sort_weight`, `dis_position`, `icon_class`, `description`) 
VALUES ('13', '2017-07-17 16:33:49', '2017-07-17 16:33:49', '系统参数', '0107', 'admin/sys/cfg/list.htm', 'menu', '1', 'sysCfgMgr:list', 'on', '1', '7', 'none', 'fa fa-cogs', NULL);

INSERT INTO `t_role_privilege` (`create_date`, `modify_date`, `role_id`, `privilege_id`) 
VALUES ('2017-07-17 16:27:38', '2017-07-17 16:27:38', '1', '13');
	

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='系统参数';

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

create table t_doc_view
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   proj_id              bigint comment '项目id',
   doc_id               bigint comment 'api文档id',
   title                varchar(32) comment '标题',
   description          text comment '描述信息',
   version              varchar(16) comment '版本',
   pub                  tinyint comment '是否发布',
   share                tinyint comment '是否开启分享',
   share_password       varchar(256) comment '分享密码',
   share_url            varchar(256) comment '分享url',
   primary key (id)
);

alter table t_doc_view comment 'api文档视图';

create table t_doc_view_resource
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   view_id              bigint comment '文档视图id',
   doc_id               bigint comment 'api文档id',
   type                 varchar(16) comment '资源类型',
   resource_id          bigint comment '资源id',
   primary key (id)
);

alter table t_doc_view_resource comment 'api文档视图资源';

update t_role set home_url = '/admin/cube/user/list.htm' where id = 1;
