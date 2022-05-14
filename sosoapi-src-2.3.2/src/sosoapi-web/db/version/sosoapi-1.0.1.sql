alter table t_proj_mem add proj_nick_name varchar(255) comment '所在项目昵称';

alter table t_resp_schema add mock_data text comment 'mock数据';
alter table t_resp_schema add mock_rule text comment 'mock规则';
alter table t_resp_schema add mock_type varchar(16) comment 'mock类型';

alter table t_inter_resp add mock_data text comment 'mock数据';
alter table t_inter_resp add mock_rule text comment 'mock规则';
alter table t_inter_resp add mock_type varchar(16) comment 'mock类型';

alter table t_inter add path_regex varchar(255) comment '请求url对应的正则表达式';

alter table t_api_doc add share tinyint(4) comment '是否开启分享';
alter table t_api_doc add share_password varchar(32) comment '分享访问密码';
alter table t_api_doc add share_url varchar(256) comment '分享访问url';

update t_api_doc set share = 0;
update t_proj set name = code where name is null;
