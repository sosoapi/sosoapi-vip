alter table t_inter add example text comment '接口示例';
alter table t_inter add skip_common_param tinyint(4) comment '是否过滤公共参数';
update t_inter set skip_common_param = 0 where skip_common_param is null;

create table t_doc_archive
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   proj_id              bigint comment '项目id',
   doc_id               bigint comment '文档id',
   title                varchar(128) comment '标题',
   description          text comment '描述',
   label                varchar(128) comment '自定义标签',
   user_id              bigint comment '创建者',
   share                tinyint comment '是否开启分享',
   share_password       varchar(256) comment '分享密码',
   share_url            varchar(256) comment '分享url',
   type                 varchar(16) comment '归档方式',
   archive_url          varchar(256) comment '归档资源url',
   primary key (id)
);

alter table t_doc_archive comment '文档归档';

INSERT INTO `t_privilege` (`id`, `create_date`, `modify_date`, `name`, `code`, `url`, `type`, `parent_id`, `permission`, `status`, `access_verify`, `sort_weight`, `dis_position`, `icon_class`, `description`, `scope`, `img_url`) VALUES ('851', '2017-09-26 17:03:14', '2017-09-26 17:03:16', '接口归档', '18', 'auth/doc/archive/list.htm', 'menu', NULL, 'docArchive:list', 'on', '1', '9', 'none', 'fa fa-clock-o', NULL, 'proj', NULL);
INSERT INTO `t_privilege` (`id`, `create_date`, `modify_date`, `name`, `code`, `url`, `type`, `parent_id`, `permission`, `status`, `access_verify`, `sort_weight`, `dis_position`, `icon_class`, `description`, `scope`, `img_url`) VALUES ('852', '2017-09-26 17:03:14', '2017-09-26 17:03:14', '接口归档-新增', '1801', 'auth/doc/archive/json/add.htm', 'oper', '851', 'docArchive:add', 'on', '1', '1', 'none', '', NULL, 'proj', NULL);
INSERT INTO `t_privilege` (`id`, `create_date`, `modify_date`, `name`, `code`, `url`, `type`, `parent_id`, `permission`, `status`, `access_verify`, `sort_weight`, `dis_position`, `icon_class`, `description`, `scope`, `img_url`) VALUES ('853', '2017-09-26 17:03:14', '2017-09-26 17:03:14', '接口归档-编辑', '1802', 'auth/doc/archive/json/update.htm', 'oper', '851', 'docArchive:update', 'on', '1', '2', 'none', '', NULL, 'proj', NULL);
INSERT INTO `t_privilege` (`id`, `create_date`, `modify_date`, `name`, `code`, `url`, `type`, `parent_id`, `permission`, `status`, `access_verify`, `sort_weight`, `dis_position`, `icon_class`, `description`, `scope`, `img_url`) VALUES ('854', '2017-09-26 17:03:14', '2017-09-26 17:03:14', '接口归档-删除', '1803', 'auth/doc/archive/json/del.htm', 'oper', '851', 'docArchive:del', 'on', '1', '3', 'none', '', NULL, 'proj', NULL);
