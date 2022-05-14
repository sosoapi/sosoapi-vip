create table t_monitor_log
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
   spend_time           int comment '请求花费时间，毫秒',
   curl_cmd             varchar(512) comment 'curl命令',
   primary key (id)
);

alter table t_monitor_log comment '监控运行日志';

alter table t_monitor_alarm_log change resp_ms spend_time bigint(20) comment '请求花费时间，毫秒';
alter table t_monitor_alarm_log add monitor_log_id bigint(20) comment '运行日志id';
alter table t_monitor_alarm_log add receiver_info text comment '预警人员信息';

alter table t_monitor_http change max_resp_ms max_spend_time bigint(20) comment '最大请求花费时间，毫秒';
alter table t_monitor_http add resp_content_type varchar(32) comment '响应内容类型';

alter table t_order add goods_id bigint(20) comment '商品id';
alter table t_order add prod_type varchar(16) comment '商品类型';
update t_order set prod_type = 'src';

alter table t_inter add label varchar(128) comment '自定义标签';
alter table t_inter add dev_status varchar(16) comment '开发状态';
alter table t_inter add developer varchar(128) comment '负责人';
alter table t_inter add operation_id varchar(128) comment '接口标识';
update t_inter set dev_status = 'none';

