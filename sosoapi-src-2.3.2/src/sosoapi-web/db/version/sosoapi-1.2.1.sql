alter table t_sys_cfg modify column value varchar(512);
alter table t_sys_cfg add def_val varchar(512) default '' comment '默认值';
update t_sys_cfg set parent_id = 0 where parent_id is null;

INSERT INTO `t_sys_cfg` (`create_date`, `modify_date`, `name`, `code`, `value`, `status`, `parent_id`, `sort_weight`, `type`, `description`, `def_val`) VALUES ('2018-06-01 17:47:30', '2018-06-01 17:47:30', 'mock跨域自定义头部', 'mock.cors.header.cust', '', 'on', '14', '30', 'item', '用于处理mock请求时有自定义请求header的ajax跨域问题', NULL);

