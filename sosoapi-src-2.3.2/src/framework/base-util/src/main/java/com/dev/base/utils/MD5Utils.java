package com.dev.base.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: MD5工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:50:12</p>
 */
public class MD5Utils {
	
	/**
	 * 
			*@name 签名
			*@Description  
			*@CreateDate 2017年1月21日下午1:39:10
	 */
	@Deprecated
	public static String Md5(String text){
		return sign(text, "");
	}
	
	/**
	 * 
			*@name 签名字符串
			*@Description  
			*@CreateDate 2017年1月21日下午1:32:56
	 */
    public static String sign(String text, String key) {
    	text = text + key;
    	if (!StringUtils.isEmpty(text)) {
    		try {
				return DigestUtils.md5Hex(text.getBytes("UTF-8"));
			} 
	        catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
    	
        return null;
    }
    
    /**
     * 
    		*@name 验证签名
    		*@Description  
    		*@CreateDate 2017年1月21日下午1:36:12
     */
    public static boolean verify(String sign,String text,String key) {
    	return StringUtils.isEmpty(sign) ? false : sign.equals(sign(text, key));
    }
}
