/*
加入订单相关
Date: 2017-02-07 15:50:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`create_date`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`modify_date`  datetime NULL DEFAULT NULL COMMENT '修改时间' ,
`pub_date`  datetime NULL DEFAULT NULL COMMENT '发布时间' ,
`type`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型' ,
`name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称' ,
`description`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述' ,
`price`  int(11) NULL DEFAULT NULL COMMENT '单价' ,
`cover_url`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面' ,
`detail_info`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '详情' ,
`discount`  int(11) NULL DEFAULT NULL COMMENT '折扣价格' ,
`status`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态' ,
`sell_count`  int(11) NULL DEFAULT NULL COMMENT '已售数量' ,
`total_count`  int(11) NULL DEFAULT NULL COMMENT '总数量' ,
`download_url`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下载地址' ,
`file_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='商品信息'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识' ,
`create_date`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`modify_date`  datetime NULL DEFAULT NULL COMMENT '修改时间' ,
`order_no`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号' ,
`order_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单名称' ,
`order_desc`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单描述' ,
`prepay_id`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预支付id' ,
`qr_code_url`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付二维码' ,
`trade_no`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易号' ,
`out_trade_no`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付平台交易号' ,
`total_fee`  int(11) NULL DEFAULT NULL COMMENT '总金额' ,
`settlement_total_fee`  int(11) NULL DEFAULT NULL COMMENT '应结订单金额' ,
`total_item_count`  int(11) NULL DEFAULT NULL COMMENT '订单项数目' ,
`expire_date`  datetime NULL DEFAULT NULL COMMENT '订单失效时间' ,
`device_info`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '终端设备号' ,
`pay_date`  datetime NULL DEFAULT NULL COMMENT '支付时间' ,
`user_id`  bigint(20) NULL DEFAULT NULL COMMENT '用户id' ,
`trade_status`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易状态' ,
`trade_status_desc`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易状态描述' ,
`trade_ip`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单支付ip' ,
`trade_type`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易方式' ,
`trade_platform`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易平台' ,
`refund_fee`  int(11) NULL DEFAULT NULL COMMENT '退款金额' ,
`refund_item_count`  int(11) NULL DEFAULT NULL COMMENT '退款订单项' ,
`cancel_date`  datetime NULL DEFAULT NULL COMMENT '取消时间' ,
`cancel_remark`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '取消原因' ,
PRIMARY KEY (`id`),
INDEX `idx_trade_no` (`trade_no`) USING BTREE ,
INDEX `idx_out_trade_no` (`out_trade_no`) USING BTREE ,
INDEX `idx_user_id` (`user_id`) USING BTREE ,
INDEX `idx_order_no` (`order_no`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='订单'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识' ,
`create_date`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`modify_date`  datetime NULL DEFAULT NULL COMMENT '修改时间' ,
`order_id`  bigint(20) NULL DEFAULT NULL COMMENT '订单id' ,
`goods_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编号' ,
`goods_name`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称' ,
`goods_desc`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述' ,
`goods_count`  int(11) NULL DEFAULT NULL COMMENT '商品数量' ,
`price`  int(11) NULL DEFAULT NULL COMMENT '单价' ,
`total_fee`  int(11) NULL DEFAULT NULL COMMENT '总价格' ,
`refund_fee`  int(11) NULL DEFAULT NULL COMMENT '退款金额' ,
`refund_remark`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款备注' ,
`trade_status`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易状态' ,
PRIMARY KEY (`id`),
INDEX `idx_order_id` (`order_id`) USING BTREE ,
INDEX `idx_goods_id` (`goods_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='订单项'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for t_pay_notify
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify`;
CREATE TABLE `t_pay_notify` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识' ,
`create_date`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`modify_date`  datetime NULL DEFAULT NULL COMMENT '修改时间' ,
`order_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单id' ,
`trade_no`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商户交易号' ,
`out_trade_no`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付交易号' ,
`trade_status`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易状态' ,
`trade_platform`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易平台' ,
`pay_date`  datetime NULL DEFAULT NULL COMMENT '支付时间' ,
`notify_ip`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通知请求ip' ,
`return_code`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返回码' ,
`return_msg`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返回信息' ,
`result_code`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务结果' ,
`err_code`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '错误代码' ,
`err_code_des`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '错误代码描述' ,
`total_fee`  int(11) NULL DEFAULT NULL COMMENT '订单金额' ,
`settlement_total_fee`  int(11) NULL DEFAULT NULL COMMENT '应结订单金额' ,
`resp_content`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '通知内容' ,
PRIMARY KEY (`id`),
INDEX `idx_order_id` (`order_id`) USING BTREE ,
INDEX `idx_trade_no` (`trade_no`) USING BTREE ,
INDEX `idx_out_trade_no` (`out_trade_no`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='支付通知'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for t_refund
-- ----------------------------
DROP TABLE IF EXISTS `t_refund`;
CREATE TABLE `t_refund` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识' ,
`create_date`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`modify_date`  datetime NULL DEFAULT NULL COMMENT '修改时间' ,
`order_id`  bigint(20) NULL DEFAULT NULL COMMENT '订单id' ,
`refund_no`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款单号' ,
`out_refund_no`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部退款单号' ,
`refund_type`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款类型' ,
`refund_fee`  int(11) NULL DEFAULT NULL COMMENT '退款申请金额' ,
`refund_reason`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请退款原因' ,
`settlement_refund_fee`  int(11) NULL DEFAULT NULL COMMENT '退款金额' ,
`refund_status`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款状态' ,
`refund_status_desc`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款状态描述' ,
`refund_recv_accout`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款入账账户' ,
`user_id`  bigint(20) NULL DEFAULT NULL COMMENT '申请人' ,
`oper_id`  bigint(20) NULL DEFAULT NULL COMMENT '操作者id' ,
`approve_status`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批状态' ,
`approve_date`  datetime NULL DEFAULT NULL COMMENT '审批时间' ,
`approve_remark`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批说明' ,
`resp_content`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应信息' ,
PRIMARY KEY (`id`),
INDEX `idx_trade_no` (`order_id`) USING BTREE ,
INDEX `idx_refund_no` (`refund_no`) USING BTREE ,
INDEX `idx_order_id` (`order_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='退款'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for t_refund_item
-- ----------------------------
DROP TABLE IF EXISTS `t_refund_item`;
CREATE TABLE `t_refund_item` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识' ,
`create_date`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`modify_date`  datetime NULL DEFAULT NULL COMMENT '修改时间' ,
`order_id`  bigint(20) NULL DEFAULT NULL COMMENT '订单id' ,
`refund_id`  bigint(20) NULL DEFAULT NULL COMMENT '退款单id' ,
`order_item_id`  bigint(20) NULL DEFAULT NULL COMMENT '订单项id' ,
`refund_fee`  int(11) NULL DEFAULT NULL COMMENT '退款申请金额' ,
`settlement_refund_fee`  int(11) NULL DEFAULT NULL COMMENT '退款金额' ,
PRIMARY KEY (`id`),
INDEX `idx_order_id` (`order_id`) USING BTREE ,
INDEX `idx_refund_id` (`refund_id`) USING BTREE ,
INDEX `idx_order_item_id` (`order_item_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='退款项'
AUTO_INCREMENT=1

;
