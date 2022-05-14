package com.dev.base.utils.cfg;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.utils.ResourceUtils;

/**
 * 
		* <p>Title: properties参数配置工具</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年12月20日下午6:45:44</p>
 */
public class CfgPropertyUtils {
	private static Properties prop = new Properties();
	static {
		//加载默认配置
		loadDef();
	}
	
	private CfgPropertyUtils() {

	}

	/**
	 * 
			*@name 获取集合
			*@Description  
			*@CreateDate 2017年12月21日上午11:34:42
	 */
	public static Properties getProperties(){
		return prop;
	}
	
	/**
	 * 
			*@name 设置值
			*@Description  
			*@CreateDate 2017年12月20日下午6:54:08
	 */
	public static void setProperty(String key, String value) {
		getProperties().setProperty(key, value);
	}

	/**
	 * 
			*@name 获取值
			*@Description  
			*@CreateDate 2017年12月20日下午6:54:15
	 */
	public static String getProperty(String key) {
		return getProperties().getProperty(key);
	}
	
	/**
	 * 
			*@name 填充
			*@Description  
			*@CreateDate 2017年12月20日下午6:55:40
	 */
	public static void putAll(Map properties){
		if (!CollectionUtils.isEmpty(properties)) {
			getProperties().putAll(properties);
		}
	}
	
	/**
	 * 
			*@name 获取字符串
			*@Description  
			*@CreateDate 2017年12月20日下午6:54:22
	 */
	public static String getProperty(String key,String def){
		String result = getProperty(key);
		return StringUtils.isEmpty(result) ? def : result;
	}
	
	/**
	 * 
			*@name 获取整型
			*@Description  
			*@CreateDate 2017年12月20日下午6:54:35
	 */
	public static Integer getInteger(String key,Integer def){
		Integer result = null;
		try{
			result = Integer.valueOf(getProperty(key));
		}
		catch (Exception e) {
			result = def;
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 获取长整型
			*@Description  
			*@CreateDate 2017年12月20日下午6:55:00
	 */
	public static Long getLong(String key,Long def){
		Long result = null;
		try{
			result = Long.valueOf(getProperty(key));
		}
		catch (Exception e) {
			result = def;
		}
		
		return result;
	} 
	
	/**
	 * 
			*@name 获取bool型
			*@Description  
			*@CreateDate 2017年12月20日下午6:55:20
	 */
	public static Boolean getBoolean(String key){
		return Boolean.valueOf(getProperty(key));
	}
	
	/**
	 * 
			*@name 加载默认配置
			*@Description  
			*@CreateDate 2017年12月21日下午1:13:21
	 */
	public static void loadDef(){
		loadProperties("classpath:config/**/*.properties");
	}
	
	/**
	 * 
			*@name 加载配置文件
			*@Description  
			*@CreateDate 2017年12月21日下午12:00:16
	 */
	public static void loadProperties(String locationPattern){
		loadProperties(ResourceUtils.load(locationPattern));
	}
	
	/**
	 * 
			*@name 加载配置文件
			*@Description  
			*@CreateDate 2017年12月21日下午12:00:16
	 */
	public static void loadProperties(Resource[] resources){
		if (resources == null) {
			return ;
		}
		
		Properties prop = null;
		for (Resource location : resources) {
			if (location == null) {
				continue;
			}
			
			try {
				prop = PropertiesLoaderUtils.loadProperties(new EncodedResource(location,"UTF-8"));
				putAll(prop);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
