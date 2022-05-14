alter table t_user_cube add total_vip_count int(11) comment '总vip用户数' default 0;
alter table t_user_cube add day_vip_count int(11) comment '日新增vip用户数' default 0;