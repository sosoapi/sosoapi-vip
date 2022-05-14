alter table t_user_basic add role_id bigint(20) comment '角色id';
alter table t_sys_msg add role_id bigint(20) comment '接收消息角色id';

create table t_filter_chain
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   name                 varchar(64) comment '名称',
   url                  varchar(128) comment 'url',
   filter               varchar(128) comment '过滤链',
   status               varchar(8) comment '启用状态',
   sort_weight          int comment '排序权重',
   description          varchar(256) comment '描述',
   position				varchar(64) comment '位置',
   primary key (id)
);

alter table t_filter_chain comment 'shiro过滤链';

create table t_privilege
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   name                 varchar(64) comment '名称',
   code                 varchar(32) comment '编码',
   url                  varchar(128) comment 'url',
   type                 varchar(32) comment '类型',
   parent_id            bigint comment '上级id',
   permission           varchar(128) comment 'shiro权限',
   status               varchar(8) comment '启用状态',
   access_verify        tinyint comment '访问授权',
   sort_weight          int comment '排序权重',
   dis_position         varchar(16) comment '显示位置',
   icon_class			varchar(32) comment '图标样式',
   description          varchar(256) comment '描述',
   primary key (id)
);

alter table t_privilege comment '资源权限';

create table t_role
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   name                 varchar(64) comment '名称',
   code                 varchar(32) comment '编码',
   status               varchar(8) comment '启用状态',
   sort_weight          int comment '排序权重',
   home_url             varchar(255) comment '主页url',
   del_disabled			tinyint(4) comment '禁止删除',
   description          varchar(256) comment '描述',
   primary key (id)
);

alter table t_role comment '角色';

create table t_role_privilege
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   role_id              bigint comment '角色id',
   privilege_id         bigint comment '权限id',
   primary key (id)
);

alter table t_role_privilege comment '角色权限';

-- 过滤链相关
INSERT INTO `t_filter_chain` VALUES ('1', '2017-07-10 18:08:18', '2017-07-10 18:08:20', '管理后台', '/admin/**', 'user', 'on', '1', null, 'after');
INSERT INTO `t_filter_chain` VALUES ('2', '2017-07-10 18:09:12', '2017-07-10 18:09:14', '用户后台', '/auth/**', 'user', 'on', '2', null, 'after');

-- 权限相关信息
INSERT INTO `t_privilege` VALUES ('1', '2017-07-04 16:33:49', '2017-07-04 18:59:10', '系统管理', '01', '#', 'cate', '0', 'sysMgr:view', 'on', '0', '100', 'none', 'fa fa-cog', ' ');
INSERT INTO `t_privilege` VALUES ('2', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '用户管理', '0101', 'admin/user/list.htm', 'menu', '1', 'userMgr:list', 'on', '1', '1', 'none', 'fa fa-users', null);
INSERT INTO `t_privilege` VALUES ('3', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '角色管理', '0102', 'admin/role/list.htm', 'menu', '1', 'roleMgr:list', 'on', '1', '2', 'none', 'fa fa-address-card-o', null);
INSERT INTO `t_privilege` VALUES ('4', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '权限管理', '0103', 'admin/priv/list.htm', 'menu', '1', 'privMgr:list', 'off', '1', '3', 'none', 'fa fa-filter', null);
INSERT INTO `t_privilege` VALUES ('5', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'Shiro过滤链管理', '0104', 'admin/filter/chain/list.htm', 'menu', '1', 'chainMgr:list', 'off', '1', '4', 'none', 'fa fa-exchange', null);
INSERT INTO `t_privilege` VALUES ('6', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '版本管理', '0105', 'admin/goods/list.htm', 'menu', '1', 'goodsMgr:list', 'off', '1', '5', 'none', 'fa fa-gift', '');
INSERT INTO `t_privilege` VALUES ('7', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '消息管理', '0106', 'admin/msg/list.htm', 'menu', '1', 'msgMgr:list', 'on', '1', '6', 'none', 'fa fa-bell-o', null);
INSERT INTO `t_privilege` VALUES ('8', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【角色权限设置】', '0102', 'admin/role/forwardPrivSet.htm', 'page', '1', 'roleMgr:forwardPrivSet', 'on', '1', '999', 'none', 'fa fa-hourglass-start', null);
INSERT INTO `t_privilege` VALUES ('9', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【发布版本】', '0105', 'admin/goods/forwardAdd.htm', 'page', '1', 'goodsMgr:forwardAdd', 'off', '1', '5', 'none', 'fa fa-gift', '');
INSERT INTO `t_privilege` VALUES ('10', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑版本】', '0105', 'admin/goods/forwardUpdate.htm', 'page', '1', 'goodsMgr:forwardUpdate', 'off', '1', '5', 'none', 'fa fa-gift', '');
INSERT INTO `t_privilege` VALUES ('11', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【发布消息】', '0106', 'admin/msg/forwardAdd.htm', 'page', '1', 'msgMgr:forwardAdd', 'on', '1', '6', 'none', 'fa fa-bell-o', null);
INSERT INTO `t_privilege` VALUES ('12', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑消息】', '0106', 'admin/msg/forwardUpdate.htm', 'page', '1', 'msgMgr:forwardUpdate', 'on', '1', '6', 'none', 'fa fa-bell-o', null);
INSERT INTO `t_privilege` VALUES ('51', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '运营管理', '02', '#', 'cate', null, 'operMgr:view', 'on', '0', '200', 'none', 'fa fa-hourglass-start', null);
INSERT INTO `t_privilege` VALUES ('52', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '用户统计', '0201', 'admin/cube/user/list.htm', 'menu', '51', 'userCube:list', 'on', '1', '1', 'none', 'fa fa-bar-chart', null);
INSERT INTO `t_privilege` VALUES ('53', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '项目管理', '0202', 'admin/proj/list.htm', 'menu', '51', 'projMgr:list', 'on', '1', '2', 'none', 'fa fa-sitemap', null);
INSERT INTO `t_privilege` VALUES ('54', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '订单管理', '0203', 'admin/order/list.htm', 'menu', '51', 'orderMgr:list', 'off', '1', '3', 'none', 'fa fa-cart-plus', null);
INSERT INTO `t_privilege` VALUES ('55', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '下载记录', '0204', 'admin/goods/listDownloadRecord.htm', 'menu', '51', 'goodsMgr:listDownloadRecord', 'off', '1', '4', 'none', 'fa fa-signal', null);
INSERT INTO `t_privilege` VALUES ('56', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '用户反馈', '0205', 'admin/suggest/list.htm', 'menu', '51', 'suggestMgr:list', 'on', '1', '5', 'none', 'fa fa-commenting', null);
INSERT INTO `t_privilege` VALUES ('57', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【回复反馈】', '0205', 'admin/suggest/forwardReply.htm', 'page', '51', 'suggestMgr:forwardReply', 'on', '1', '5', 'none', 'fa fa-commenting', null);
INSERT INTO `t_privilege` VALUES ('101', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '网站监控', '03', '#', 'cate', null, 'monitorMgr:view', 'on', '0', '300', 'none', 'fa fa-thermometer-half', null);
INSERT INTO `t_privilege` VALUES ('102', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'http监控管理', '0301', 'admin/monitor/http/list.htm', 'menu', '101', 'httpMonitorMgr:list', 'on', '1', '1', 'none', 'fa fa-h-square', null);
INSERT INTO `t_privilege` VALUES ('103', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '监控日志', '0302', 'admin/monitor/log/list.htm', 'menu', '101', 'monitorLogMgr:list', 'on', '1', '2', 'none', 'fa fa-video-camera', null);
INSERT INTO `t_privilege` VALUES ('104', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报日志', '0303', 'admin/monitor/alarm/log/list.htm', 'menu', '101', 'monitorAlarmLogMgr:list', 'on', '1', '3', 'none', 'fa fa-warning', null);
INSERT INTO `t_privilege` VALUES ('151', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '首页', '04', 'auth/home/home.htm', 'menu', null, 'home:view', 'on', '1', '400', 'none', 'fa fa-home', null);
INSERT INTO `t_privilege` VALUES ('201', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '项目管理', '05', 'auth/proj/list.htm', 'menu', null, 'proj:list', 'on', '1', '500', 'none', 'fa fa-sitemap', null);
INSERT INTO `t_privilege` VALUES ('251', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'API文档管理', '06', 'auth/doc/list.htm', 'menu', null, 'doc:list', 'on', '1', '600', 'none', 'fa fa-file-code-o', null);
INSERT INTO `t_privilege` VALUES ('301', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '网站监控', '07', '#', 'cate', null, 'monitor:view', 'on', '0', '700', 'none', 'fa fa-thermometer-half', null);
INSERT INTO `t_privilege` VALUES ('302', '2017-07-04 16:33:49', '2017-07-04 16:33:49', 'http监控管理', '0701', 'auth/monitor/http/list.htm', 'menu', '301', 'httpMonitor:list', 'on', '1', '1', 'none', 'fa fa-h-square', null);
INSERT INTO `t_privilege` VALUES ('303', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '监控日志', '0702', 'auth/monitor/log/list.htm', 'menu', '301', 'monitorLog:list', 'on', '1', '2', 'none', 'fa fa-video-camera', null);
INSERT INTO `t_privilege` VALUES ('304', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报日志', '0703', 'auth/monitor/alarm/log/list.htm', 'menu', '301', 'monitorAlarmLog:list', 'on', '1', '3', 'none', 'fa fa-warning', null);
INSERT INTO `t_privilege` VALUES ('305', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报接收者管理', '0704', 'auth/monitor/alarm/receiver/list.htm', 'menu', '301', 'monitorAlarmReceiver:list', 'on', '1', '4', 'none', 'fa fa-user-circle', null);
INSERT INTO `t_privilege` VALUES ('306', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '警报接收组管理', '0705', 'auth/monitor/alarm/group/list.htm', 'menu', '301', 'monitorAlarmGroup:list', 'on', '1', '5', 'none', 'fa fa-users', null);
INSERT INTO `t_privilege` VALUES ('307', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【新增http监控】', '0701', 'auth/monitor/http/forwardAdd.htm', 'page', '301', 'httpMonitor:forwardAdd', 'on', '1', '1', 'none', 'fa fa-h-square', null);
INSERT INTO `t_privilege` VALUES ('308', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑http监控】', '0701', 'auth/monitor/http/forwardUpdate.htm', 'page', '301', 'httpMonitor:forwardUpdate', 'on', '1', '1', 'none', 'fa fa-h-square', null);
INSERT INTO `t_privilege` VALUES ('309', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【监控日志详情】', '0702', 'auth/monitor/log/info.htm', 'page', '301', 'monitorLog:view', 'on', '1', '2', 'none', 'fa fa-video-camera', null);
INSERT INTO `t_privilege` VALUES ('310', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【警报日志详情】', '0703', 'auth/monitor/alarm/log/info.htm', 'page', '301', 'monitorAlarmLog:view', 'on', '1', '3', 'none', 'fa fa-warning', null);
INSERT INTO `t_privilege` VALUES ('311', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【新增警报接收组】', '0705', 'auth/monitor/alarm/group/forwardAdd.htm', 'page', '301', 'monitorAlarmGroup:forwardAdd', 'on', '1', '5', 'none', 'fa fa-users', null);
INSERT INTO `t_privilege` VALUES ('312', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '【编辑警报接收组】', '0705', 'auth/monitor/alarm/group/forwardUpdate.htm', 'page', '301', 'monitorAlarmGroup:forwardUpdate', 'on', '1', '5', 'none', 'fa fa-users', null);
INSERT INTO `t_privilege` VALUES ('351', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '个人中心', '08', '#', 'cate', null, 'userCenter:view', 'on', '0', '800', 'none', 'fa fa-user', null);
INSERT INTO `t_privilege` VALUES ('352', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '账号管理', '0801', 'auth/user/setting.htm', 'menu', '351', 'userSetting:view', 'on', '1', '1', 'none', 'fa fa-cog', null);
INSERT INTO `t_privilege` VALUES ('353', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '消息管理', '0802', 'auth/msg/list.htm', 'menu', '351', 'msg:list', 'on', '1', '2', 'none', 'fa fa-bell-o', null);
INSERT INTO `t_privilege` VALUES ('354', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '购买记录', '0803', 'auth/order/list.htm', 'menu', '351', 'order:list', 'on', '1', '3', 'none', 'fa fa-cart-plus', null);
INSERT INTO `t_privilege` VALUES ('401', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '意见反馈', '09', 'auth/suggest/forwardSuggest.htm', 'menu', null, 'suggest:forwardSuggest', 'on', '1', '900', 'none', 'fa fa-commenting', null);

-- 角色相关信息
insert into t_role values ('1', '2017-07-01 22:36:47', '2017-07-01 22:36:47', '管理员', 'admin', 'on', '1', 'admin/cube/user/list.htm', '1', null);
insert into t_role values ('2', '2017-07-01 22:37:00', '2017-07-01 22:37:00', 'vip用户', 'vip', 'on', '2', '', '1', null);
insert into t_role values ('3', '2017-07-01 22:37:11', '2017-07-01 22:37:11', '普通用户', 'normal', 'on', '3', '', '1', null);

-- 角色权限关联信息
insert into t_role_privilege values ('1', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '1');
insert into t_role_privilege values ('2', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '2');
insert into t_role_privilege values ('3', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '3');
insert into t_role_privilege values ('4', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '4');
insert into t_role_privilege values ('5', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '5');
insert into t_role_privilege values ('6', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '10');
insert into t_role_privilege values ('7', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '6');
insert into t_role_privilege values ('8', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '9');
insert into t_role_privilege values ('9', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '11');
insert into t_role_privilege values ('10', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '12');
insert into t_role_privilege values ('11', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '7');
insert into t_role_privilege values ('12', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '8');
insert into t_role_privilege values ('13', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '51');
insert into t_role_privilege values ('14', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '52');
insert into t_role_privilege values ('15', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '53');
insert into t_role_privilege values ('16', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '54');
insert into t_role_privilege values ('17', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '55');
insert into t_role_privilege values ('18', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '57');
insert into t_role_privilege values ('19', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '56');
insert into t_role_privilege values ('20', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '101');
insert into t_role_privilege values ('21', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '102');
insert into t_role_privilege values ('22', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '103');
insert into t_role_privilege values ('23', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '104');
insert into t_role_privilege values ('24', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '351');
insert into t_role_privilege values ('25', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '352');
insert into t_role_privilege values ('26', '2017-07-10 11:07:45', '2017-07-10 11:07:45', '1', '353');
insert into t_role_privilege values ('27', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '151');
insert into t_role_privilege values ('28', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '201');
insert into t_role_privilege values ('29', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '251');
insert into t_role_privilege values ('30', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '301');
insert into t_role_privilege values ('31', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '302');
insert into t_role_privilege values ('32', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '307');
insert into t_role_privilege values ('33', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '308');
insert into t_role_privilege values ('34', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '303');
insert into t_role_privilege values ('35', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '309');
insert into t_role_privilege values ('36', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '304');
insert into t_role_privilege values ('37', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '310');
insert into t_role_privilege values ('38', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '305');
insert into t_role_privilege values ('39', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '311');
insert into t_role_privilege values ('40', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '312');
insert into t_role_privilege values ('41', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '306');
insert into t_role_privilege values ('42', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '351');
insert into t_role_privilege values ('43', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '352');
insert into t_role_privilege values ('44', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '353');
insert into t_role_privilege values ('45', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '354');
insert into t_role_privilege values ('46', '2017-07-10 11:08:01', '2017-07-10 11:08:01', '2', '401');
insert into t_role_privilege values ('47', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '151');
insert into t_role_privilege values ('48', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '201');
insert into t_role_privilege values ('49', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '251');
insert into t_role_privilege values ('50', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '301');
insert into t_role_privilege values ('51', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '302');
insert into t_role_privilege values ('52', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '307');
insert into t_role_privilege values ('53', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '308');
insert into t_role_privilege values ('54', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '303');
insert into t_role_privilege values ('55', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '309');
insert into t_role_privilege values ('56', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '304');
insert into t_role_privilege values ('57', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '310');
insert into t_role_privilege values ('58', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '305');
insert into t_role_privilege values ('59', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '311');
insert into t_role_privilege values ('60', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '312');
insert into t_role_privilege values ('61', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '306');
insert into t_role_privilege values ('62', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '351');
insert into t_role_privilege values ('63', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '352');
insert into t_role_privilege values ('64', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '353');
insert into t_role_privilege values ('65', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '354');
insert into t_role_privilege values ('66', '2017-07-10 11:08:13', '2017-07-10 11:08:13', '3', '401');

update t_user_basic set role_id = 1 where role = 'admin';
update t_user_basic set role_id = 2 where role = 'vip';
update t_user_basic set role_id = 3 where role = 'normal';

update t_sys_msg set role_id = 1 where user_role = 'admin';
update t_sys_msg set role_id = 2 where user_role = 'vip';
update t_sys_msg set role_id = 3 where user_role = 'normal';
-- alter table t_sys_msg drop column user_role;
-- alter table t_user_basic drop column role;


