alter table t_api_evn rename to t_api_env;
alter table t_api_env add sort_weight int(11) NOT NULL DEFAULT '0' comment '排序权重';

insert into t_api_env (
	create_date,
	modify_date,
	doc_id,
	name,
	base_url,
	status,
	def,
	sort_weight
) select
	now(),
	now(),
	id,
	'default',
	case locate('http:', host)
		when 0 then
			concat(
				'http://',
				ifnull(host, ''),
				ifnull(base_path, '')
			)
		else
			concat(
				ifnull(host, ''),
				ifnull(base_path, '')
			)
		end as base_url,
	 'on',
	 0,
	 1
	from
		t_api_doc
	where
		length(host) > 0
	or 
		length(base_path) > 0;
		

create table t_monitor_alarm_group
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '创建者id',
   name                 varchar(64) comment '名称',
   email_alarm          tinyint comment '邮件报警',
   phone_alarm          tinyint comment '短信报警',
   start_time           varchar(16) comment '接收报警开始时间',
   end_time             varchar(16) comment '接收报警结束时间',
   description          varchar(256) comment '备注',
   status               varchar(8) comment '状态',
   primary key (id)
);

alter table t_monitor_alarm_group comment '警报接收组';


create table t_monitor_alarm_group_receiver
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '用户id',
   group_id             bigint comment '组id',
   receiver_id          bigint comment '接收者id',
   primary key (id)
);

alter table t_monitor_alarm_group_receiver comment '警报接收组人员';


create table t_monitor_alarm_log
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '用户id',
   monitor_id           bigint comment '监控id',
   monitor_type         varchar(8) comment '监控类型',
   sub                  varchar(256) comment '监控对象',
   sub_status           varchar(16) comment '对象状态',
   error_brief          varchar(128) comment '异常概要',
   error_detail         varchar(256) comment '异常详情',
   resp_content         text comment '响应内容',
   resp_ms              int comment '响应时间，毫秒',
   alarm_group_id       bigint comment '警报组',
   primary key (id)
);

alter table t_monitor_alarm_log comment '监控警报日志';


create table t_monitor_alarm_receiver
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '创建者id',
   sys_flag             tinyint comment '是否系统用户',
   sys_user_id          bigint comment '系统用户id',
   name                 varchar(64) comment '姓名',
   phone                varchar(16) comment '手机号',
   email                varchar(64) comment '邮箱',
   phone_valid          tinyint comment '手机号是否验证',
   email_valid          tinyint comment '邮箱是否验证',
   email_alarm          tinyint comment '邮件报警',
   phone_alarm          tinyint comment '短信报警',
   start_time           varchar(16) comment '接收报警开始时间',
   end_time             varchar(16) comment '接收报警结束时间',
   description          varchar(256) comment '备注',
   status               varchar(8) comment '状态',
   primary key (id)
);

alter table t_monitor_alarm_receiver comment '警报接收人';


create table t_monitor_http
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '用户id',
   description          varchar(256) comment '描述',
   status               varchar(8) comment '状态',
   name                 varchar(64) comment '名称',
   sub                  varchar(256) comment '监控url',
   req_param            text comment '请求参数',
   rate                 int comment '监控频率，单位分',
   req_method           varchar(8) comment '请求方法',
   resp_content         text comment '验证内容',
   valid_resp           tinyint comment '是否验证响应',
   sub_status           varchar(16) comment '监控对象状态',
   max_resp_ms          int comment '最大响应时间，毫秒',
   max_error_count      int comment '最大错误次数',
   max_alarm_count      int comment '最大预警次数',
   alarm_group_id       bigint comment '警报组',
   alarm_count          int comment '已发出预警次数',
   error_count          int comment '已错误次数',
   primary key (id)
);

alter table t_monitor_http comment 'http监控';