package com.dev.alipay.constant;

import java.util.Properties;

import com.dev.base.utils.PropertiesUtils;

/**
 * 
		* <p>Title: 即时到账相关配置</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月21日下午1:47:47</p>
 */
public class DirectCfg {
	// 配置信息
	private final static Properties cfgProperties = PropertiesUtils.getProperties("pay/alipay-direct.properties");

	/**
	 * 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串
	 * 查看地址：https://b.alipay.com/order/pidAndKey.htm
	 */
	public static String PARTNER = cfgProperties.getProperty("alipay.direct.partner");
	
	/**
	 * 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	 * 一般情况下收款账号就是签约账号
	 */
	public static String SELLER_ID = PARTNER;

	/**
	 * MD5密钥，安全检验码，由数字和字母组成的32位字符串
	 * 查看地址：https://b.alipay.com/order/pidAndKey.htm
	 */
    public static String KEY = cfgProperties.getProperty("alipay.direct.key");

	/**
	 * 服务器异步通知页面路径  
	 * 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	 */
	public static String NOTIFY_URL = cfgProperties.getProperty("alipay.direct.notify.url");

	/**
	 * 页面跳转同步通知页面路径 
	 * 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	 */
	public static String RETURN_URL = cfgProperties.getProperty("alipay.direct.return.url");

	/**
	 * 调试用，创建TXT日志文件夹路径
	 * 见AlipayCore.java类中的logResult(String sWord)打印方法。
	 */
	public static String LOG_PATH = cfgProperties.getProperty("alipay.direct.log.path");
		
	/**
	 * 签名方式
	 */
	public static String SIGN_TYPE = "MD5";
	
	/**
	 * 字符编码格式 目前支持 gbk或 utf-8
	 */
	public static String CHARSET = "UTF-8";
		
	/**
	 * 支付类型 ，无需修改
	 */
	public static String PAYMENT_TYPE = "1";
		
	/**
	 * 调用的接口名，无需修改
	 */
	public static String SERVICE = "create_direct_pay_by_user";
	
	/**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
	public static final String GATEWAY = "https://mapi.alipay.com/gateway.do?";
	
	/**
     * 支付宝消息验证地址
     */
	public static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
}

