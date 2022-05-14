package com.dev.base.qiniu.common;

import java.util.Properties;

import org.springframework.util.StringUtils;

import com.dev.base.utils.PropertiesUtils;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;

/**
 * 
		* <p>Title: 七牛云配置信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午5:01:08</p>
 */
public class QiniuCfg {
	// 七牛云接口配置信息
	private final static Properties qiniuCfg= PropertiesUtils.getProperties("qiniu-cfg.properties");

	/**
	 * 应用密钥
	 */
	private final static String ACCESS_KEY = qiniuCfg.getProperty("access.key");
	
	/**
	 * 应用密钥
	 */
	private final static String SECRET_KEY = qiniuCfg.getProperty("secret.key");
	
	/**
	 * 公共资源库-名称
	 */
	public final static String BUCKET_PUB_NAME = qiniuCfg.getProperty("bucket.pub.name");
	
	/**
	 * 公共资源库-域名,前缀不以"/"开头
	 */
	public final static String BUCKET_PUB_DOMAIN = qiniuCfg.getProperty("bucket.pub.domain");
	
	/**
	 * 私有资源库-名称
	 */
	public final static String BUCKET_PRIV_NAME = qiniuCfg.getProperty("bucket.priv.name");
	
	/**
	 * 私有资源库-域名,前缀不以"/"开头
	 */
	public final static String BUCKET_PRIV_DOMAIN = qiniuCfg.getProperty("bucket.priv.domain");
	
	/**
	 * token失效时间
	 */
	public final static long TOKEN_EXPIRE_SECONDS = Long.parseLong(qiniuCfg.getProperty("token.expire.seconds"));
	
	/**
	 * 私有空间请求url失效时间
	 */
	public final static long PRIV_URL_EXPIRE_SECONDS = Long.parseLong(qiniuCfg.getProperty("priv.url.expire.seconds"));
	
	
	/**
	 * 配置信息
	 */
	public static Configuration cfg = null;
	
	/**
	 * 
			*@name 获取应用密钥
			*@Description  
			*@CreateDate 2015年12月31日下午5:02:10
	 */
	public static String getAccessKey(){
		return QiniuCfg.ACCESS_KEY;
	}
	
	/**
	 * 
			*@name 获取应用密钥
			*@Description  
			*@CreateDate 2015年12月31日下午5:02:19
	 */
	public static String getSecretKey(){
		return QiniuCfg.SECRET_KEY;
	}
	
	/**
	 * 
			*@name 获取配置
			*@Description  
			*@CreateDate 2017年9月14日下午3:40:51
	 */
	public static Configuration getConfiguration(){
		if (cfg == null) {
			cfg = getConfiguration(qiniuCfg.getProperty("zone"));
		}
		
		return cfg;
	}
	
	/**
	 * 
			*@name 获取配置
			*@Description  
			*
			*	
			*	华东	Zone.zone0()
				华北	Zone.zone1()
				华南	Zone.zone2()
				北美	Zone.zoneNa0()
			*@CreateDate 2017年9月14日下午3:40:51
	 */
	public static Configuration getConfiguration(String zone){
		Configuration configuration = null;
		if (StringUtils.isEmpty(zone)) {//设置默认
			configuration = new Configuration(Zone.autoZone());
		}
		else if ("zone0".equals(zone)) {
			configuration = new Configuration(Zone.zone0());
		}
		else if ("zone1".equals(zone)) {
			configuration = new Configuration(Zone.zone1());
		}
		else if ("zone2".equals(zone)) {
			configuration = new Configuration(Zone.zone2());
		}
		else if ("zoneNa0".equals(zone)) {
			configuration = new Configuration(Zone.zoneNa0());
		}
		
		return configuration;
	}
}
