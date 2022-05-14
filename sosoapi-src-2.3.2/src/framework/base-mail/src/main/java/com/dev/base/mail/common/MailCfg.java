package com.dev.base.mail.common;

import java.util.Properties;

import org.springframework.util.StringUtils;

import com.dev.base.utils.PropertiesUtils;

/**
 * 
		* <p>Title: 邮件配置信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日上午10:27:04</p>
 */
public class MailCfg {
	//邮件配置信息
	private final static Properties mailCfg = PropertiesUtils.getProperties("mail-cfg.properties");

	/**
	 * 邮件服务器
	 */
	public final static String HOST = mailCfg.getProperty("host");
	
	/**
	 * smtp端口
	 */
	public final static String SMTP_PORT = getProperty("smtpPort", "25");
	
	/**
	 * ssl smtp端口
	 */
	public final static String SSL_SMTP_PORT = getProperty("SSLSmtpPort", "465");
	
	/**
	 * 邮件编码
	 */
	public final static String CHARSET = mailCfg.getProperty("charset");
	
	/**
	 * 是否开启调试
	 */
	public final static boolean DEBUG = Boolean.parseBoolean(mailCfg.getProperty("debug"));
	
	/**
	 * 是否开启SSL加密
	 */
	public final static boolean SSL_ON_CONNECT = Boolean.parseBoolean(mailCfg.getProperty("SSLOnConnect"));
	
	/**
	 * 是否开启TLS加密 
	 */
	public final static boolean START_TLS_ENABLED = Boolean.parseBoolean(mailCfg.getProperty("startTLSEnabled"));
	
	/**
	 * 发送者邮箱
	 */
	public final static String DEFAULT_FROM_EMAIL = mailCfg.getProperty("default.from.email");
	
	/**
	 * 发送者密码
	 */
	public final static String DEFAULT_FROM_PASSWD = mailCfg.getProperty("default.from.passwd");
	
	/**
	 * 发送者名称
	 */
	public final static String DEFAULT_FROM_NAME = mailCfg.getProperty("default.from.name");

	//默认值
	private static String getProperty(String key,String defVal){
		String val = mailCfg.getProperty(key);
		return StringUtils.isEmpty(val) ? defVal : val;
	}
}
