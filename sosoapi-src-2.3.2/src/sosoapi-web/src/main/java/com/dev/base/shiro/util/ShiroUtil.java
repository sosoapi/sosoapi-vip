package com.dev.base.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * 
		* <p>Title: shiro工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月15日上午10:33:43</p>
 */
public class ShiroUtil {
	//保存userBasic属性key
	public static final String SESSION_KEY_USER_BASIC = "shiro_session_user_basic";
	
	/**
	 * 
			*@name 设置属性
			*@Description  
			*@CreateDate 2017年6月15日上午10:35:56
	 */
	public static void setAttribute(Object key,Object value){
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 
			*@name 设置属性
			*@Description  
			*@CreateDate 2017年6月15日上午10:35:56
	 */
	public static Object getAttribute(Object key){
		return getSession().getAttribute(key);
	}
	
	/**
	 * 
			*@name 删除属性
			*@Description  
			*@CreateDate 2017年6月15日上午10:35:56
	 */
	public static void removeAttribute(Object key){
		getSession().removeAttribute(key);
	}
	
	/**
	 * 
			*@name 获取session
			*@Description  
			*@CreateDate 2017年6月15日上午10:35:24
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession(true);
	}
	
	/**
	 * 
			*@name 退出
			*@Description  
			*@CreateDate 2017年6月15日下午10:53:05
	 */
	public static void logout(){
		SecurityUtils.getSubject().logout();
	}
}
