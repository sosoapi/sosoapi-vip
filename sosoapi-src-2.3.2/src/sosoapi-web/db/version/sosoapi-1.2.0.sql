create table t_book
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '用户id',
   proj_id              bigint comment '项目id',
   title                varchar(128) comment '标题',
   brief                text comment '简介',
   tag                  varchar(128) comment '标签',
   open                 tinyint(4) comment '是否公开',
   share                tinyint comment '是否开启分享',
   share_password       varchar(256) comment '分享密码',
   share_url            varchar(256) comment '分享url',
   cover_url            varchar(256) comment '封面url',
   read_count           int comment '阅读数',
   sort_weight          int comment '排序权重',
   pub_status           varchar(16) comment '发布状态',
   primary key (id)
);

alter table t_book comment '书籍设置';

create table t_book_chapter
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   book_id              bigint comment '书籍id',
   title                varchar(128) comment '标题',
   sort_weight          int comment '排序权重',
   type                 varchar(16) comment '类型',
   parent_id            bigint comment '上级id',
   content_id           bigint comment '内容id',
   content_link         varchar(256) comment '内容链接',
   primary key (id)
);

alter table t_book_chapter comment '书籍章节';

create table t_book_content
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   book_id              bigint comment '书籍id',
   type                 varchar(16) comment '类型',
   rich_text_content    longtext comment '富文本内容',
   markdown_content     longtext comment 'markdown内容',
   html_content         longtext comment 'html内容',
   primary key (id)
);

alter table t_book_content comment '书籍内容';

create table t_invoice_apply
(
   id                   bigint not null auto_increment comment '主键',
   create_date          datetime comment '创建时间',
   modify_date          datetime comment '修改时间',
   user_id              bigint comment '用户id',
   type                 varchar(16) comment '发票类型',
   product_name         varchar(128) comment '产品名称',
   total_amount         int comment '总金额',
   company_name         varchar(128) comment '公司名称',
   company_tax_no       varchar(64) comment '公司税号',
   company_addr         varchar(256) comment '公司地址',
   company_tel          varchar(32) comment '公司电话',
   company_bank_name    varchar(256) comment '公司开户银行',
   company_bank_account varchar(32) comment '公司开户银行账号',
   mailing_addr         varchar(256) comment '邮寄地址',
   receiver_name        varchar(32) comment '收件人姓名',
   receiver_tel         varchar(32) comment '收件人电话',
   apply_remark         varchar(256) comment '申请备注信息',
   deal_status          varchar(8) comment '处理状态',
   deal_date            datetime comment '处理时间',
   deal_user_id         bigint comment '处理用户id',
   mailing_serv_name    varchar(32) comment '快递商名称',
   mailing_no           varchar(32) comment '快递单号',
   mailing_date         datetime comment '寄送时间',
   remark               varchar(256) comment '备注',
   primary key (id)
);

alter table t_invoice_apply comment '发票申请';


alter table t_user_detail add fee_amount int(11) default 0 comment '总费用金额';
alter table t_user_detail add invoice_amount int(11) default 0 comment '发票金额';

alter table t_goods add pub_status varchar(16) default 'on' comment '发布状态';

INSERT INTO `t_privilege` (`id`, `create_date`, `modify_date`, `name`, `code`, `url`, `type`, `parent_id`, `permission`, `status`, `access_verify`, `sort_weight`, `dis_position`, `icon_class`, `description`, `scope`, `img_url`) VALUES ('900', '2017-11-23 11:43:19', '2017-11-23 11:43:21', '其他文档', '19', 'auth/book/tree/list.htm', 'menu', NULL, 'bookTree:list', 'on', '1', '10', 'none', 'fa fa-book', NULL, 'proj', NULL);
INSERT INTO `t_privilege` (`id`, `create_date`, `modify_date`, `name`, `code`, `url`, `type`, `parent_id`, `permission`, `status`, `access_verify`, `sort_weight`, `dis_position`, `icon_class`, `description`, `scope`, `img_url`) VALUES ('14', '2017-07-04 16:33:49', '2017-07-04 16:33:49', '删除消息', '', 'admin/msg/json/del.htm', 'oper', '7', 'msgMgr:del', 'on', '1', '1', 'none', '', NULL, 'user', NULL);


