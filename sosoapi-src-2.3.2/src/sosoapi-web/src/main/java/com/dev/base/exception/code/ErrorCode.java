package com.dev.base.exception.code;

import com.dev.base.exception.errorcode.SysErrorCode;

/**
 * 
		* <p>Title: 系统公共异常码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午1:57:02</p>
 */
public interface ErrorCode extends SysErrorCode {
	/**
	 * 用户未注册
	 */
	public static final String LOGIN_001 = "51001";

	/**
	 * 用户已注册
	 */
	public static final String LOGIN_002 = "51002";

	/**
	 * 验证码错误
	 */
	public static final String LOGIN_003 = "51003";

	/**
	 * 用户名或密码错误
	 */
	public static final String LOGIN_004 = "51004";

	/**
	 * 账号未激活
	 */
	public static final String LOGIN_005 = "51005";

	/**
	 * 账号被锁定
	 */
	public static final String LOGIN_006 = "51006";
	
	/**
	 * 登陆/注册方式与提交信息不匹配
	 */
	public static final String LOGIN_007 = "51007";
	
	/**
	 * 原密码错误
	 */
	public static final String LOGIN_008 = "51008";
	
	/**
	 * 账号已激活
	 */
	public static final String LOGIN_009 = "51009";
	
	/**
	 * 登录失败次数超过限制，账号被锁定，请联系管理员或第二天自动解锁
	 */
	public static final String LOGIN_010 = "51010";
	
	/**
	 * 系统已关闭注册，请联系管理员
	 */
	public static final String LOGIN_011 = "51011";
	
	/**
	 * 项目至少需要一名管理员
	 */
	public static final String PROJ_001 = "52001";
	
	/**
	 * 只有管理员才有该权限
	 */
	public static final String PROJ_002 = "52002";
	
	/**
	 * 已存在相同的请求url和请求方式
	 */
	public static final String DOC_001 = "53001";
	
	/**
	 * swagger文档格式错误
	 */
	public static final String DOC_002 = "53002";
	
	/**
	 * 该接口未配置mock数据或mock规则
	 */
	public static final String DOC_003 = "53003";
	
	/**
	 * 未找到与请求url和请求方法匹配的接口信息
	 */
	public static final String DOC_004 = "53004";
	
	/**
	 * 商品不存在
	 */
	public static final String ORDER_001 = "54001";
	
	/**
	 * 商品已下架
	 */
	public static final String ORDER_002 = "54002";
	
	/**
	 * 无效订单
	 */
	public static final String ORDER_003 = "54003";
	
	/**
	 * 支付超时
	 */
	public static final String ORDER_004 = "54004";
	
	/**
	 * 只能支付未付款订单
	 */
	public static final String ORDER_005 = "54005";
	
	/**
	 * 只能取消未支付订单
	 */
	public static final String ORDER_006 = "54006";
	
	/**
	 * 未付款订单无法申请退款
	 */
	public static final String ORDER_007 = "54007";
	
	/**
	 * 总退款金额不能大于支付金额
	 */
	public static final String ORDER_008 = "54008";
	
	/**
	 * 该订单项处于退款中，无法再次发起退款
	 */
	public static final String ORDER_009 = "54009";
	
	/**
	 * 只有驳回和未处理才允许编辑
	 */
	public static final String INVOICE_001 = "54101";
	
	/**
	 * 已审核通过，不能删除
	 */
	public static final String INVOICE_002 = "54102";
	
	/**
	 * 存在处理中记录，请等待管理员审批后再提交新申请
	 */
	public static final String INVOICE_003 = "54103";

	/**
	 * 发票申请金额大于可开金额
	 */
	public static final String INVOICE_004 = "54104";
	
	/**
	 * 监控创建数目达到上限，无法新建
	 */
	public static final String MONITOR_001 = "55001";
}
