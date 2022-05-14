create table t_download_record
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '用户id',
   goods_id             bigint comment '商品id',
   goods_name           varchar(32) comment '商品名称',
   goods_desc           varchar(64) comment '商品描述',
   goods_type           varchar(16) comment '类型',
   download_ip          varchar(32) comment '下载ip',
   file_name            varchar(256) comment '文件名',
   primary key (id)
);

alter table t_download_record comment '下载记录';

alter table t_inter_param add sort_weight int(11) NOT NULL DEFAULT '0' comment '排序权重';

alter table t_inter add del_flag tinyint(4) NOT NULL DEFAULT '0' comment '删除标志';
update t_inter set del_flag = 0;

create table t_common_param
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   doc_id               bigint comment '文档id',
   code                 varchar(128) comment '编码',
   name                 varchar(128) comment '名称',
   description          text comment '描述',
   type                 varchar(32) comment '数据类型',
   format               varchar(64) comment '格式化',
   position             varchar(16) comment '参数位置',
   required             tinyint comment '是否必输项',
   cust_schema          text comment '自定结构体',
   ext_schema           text comment '扩展结构体',
   ref_schema_id        bigint comment '引用结构id',
   def_value            varchar(512) comment '默认值',
   sort_weight          int,
   primary key (id)
);

alter table t_common_param comment '公共参数';

create table t_api_evn
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   doc_id               bigint comment '文档id',
   name                 varchar(64) comment '名称',
   base_url             varchar(256) comment '基路径',
   status               varchar(16) comment '启用状态',
   def                  tinyint comment '是否默认',
   description          text comment '描述',
   variable             text comment '变量列表',
   primary key (id)
);

alter table t_api_evn comment 'api环境';

create table t_api_error_code
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   doc_id               bigint comment '文档id',
   module_id            bigint comment '模块id',
   code                 varchar(64) comment '错误码',
   msg                  varchar(256) comment '错误信息',
   description          varchar(512) comment '备注',
   sort_weight          int comment '排序权重',
   primary key (id)
);

alter table t_api_error_code comment '错误码';
