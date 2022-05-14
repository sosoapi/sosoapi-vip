package com.dev.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: 正则表达式工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午2:02:59</p>
 */
public class RegexUtil {
	private static final String REGEX_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	private static final String REGEX_IP = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
	private static final String REGEX_POST_CODE = "^[1-9]{1}\\d{5}$";
	private static final String REGEX_ID_CARD = "^(\\d{14}|\\d{17})(\\d|[xX])$";
	private static final String REGEX_MOBILE = "^(0)?[1]\\d{10}$";

	/**
	 * 验证邮箱格式
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		return regex(str,REGEX_EMAIL);
	}

	/**
	 * 验证ip格式
	 * @param str
	 * @return
	 */
	public static boolean isIp(String str) {
		return regex(str, REGEX_IP);
	}

	/**
	 * 验证固话格式
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		return regex(str, REGEX_MOBILE);
	}

	/**
	 * 验证post卡格式
	 * @param str
	 * @return
	 */
	public static boolean isPostCode(String str) {
		return regex(str, REGEX_POST_CODE);
	}

	/**
	 * 验证id卡格式
	 * @param str
	 * @return
	 */
	public static boolean isIdCard(String str) {
		return regex(str, REGEX_ID_CARD);
	}

	/**
	 * 验证指定字符串是否匹配相应正则表达式
	 * @param testStr
	 * @param regStr
	 * @return
	 * @throws Exception
	 */
	public static final boolean regex(String testStr, String regStr){
		Pattern p = Pattern.compile(regStr);
		Matcher m = p.matcher(testStr);
		return m.matches();
	}

	/**
	 * 获取匹配指定正则表达式的字符串
	 * @param testStr
	 * @param regStr
	 * @param defaultValue
	 * @return
	 */
	public static final String getMatchValue(String testStr, String regStr,String defaultValue) {
		try {
			Pattern p = Pattern.compile(regStr);
			Matcher m = p.matcher(testStr);
			if (m.find()){
				return m.group(1);
			}
		} catch (Exception e) {
			return defaultValue;
		}
		return defaultValue;
	}
	
	/**
	 * 
			*@name 获取请求url对应的正则表达式
			*@Description  
			*@CreateDate 2016年10月26日下午3:51:40
	 */
	//示例：
	//输入：list.do?userName={userName}&code={code}
	//输出：^[/]*list\.do[\\?]?((userName=[\w_-]*[&]?)|(code=[\w_-]*[&]?)){2}(.*)$
	public static String parseReqUrlRegExpress(String reqUrl){
		if (StringUtils.isEmpty(reqUrl)) {
			return "";
		}
		
		StringBuilder regBuilder = new StringBuilder();
		//拆分请求url
		String[] urlArray = reqUrl.split("\\?");
		if (urlArray.length == 1) {
			String firstPart = urlArray[0];
			if (firstPart.contains("=") || firstPart.contains("&")) {//只包含请求参数
				regBuilder.append(buildReqParamReg(firstPart));
			}
			else{//只包含请求url
				regBuilder.append(buildReqUrlReg(firstPart));
			}
		}
		else if (urlArray.length == 2) {
			regBuilder.append(buildReqUrlReg(urlArray[0]));
			regBuilder.append(buildReqParamReg(urlArray[1]));
		}
		
		//拼接剩余匹配部分
		regBuilder.append("(.*)$");
		
		return regBuilder.toString();
	}
	
	//组装请求部分的正则表达式
	private static String buildReqUrlReg(String urlInfo){
		if (StringUtils.isEmpty(urlInfo)) {
			return "";
		}
		
		//去除请求开始部分的斜杠
		if (urlInfo.startsWith("/")) {
			urlInfo = urlInfo.replaceFirst("[//]+", "");
		}
		//替换特殊字符"."
		urlInfo = urlInfo.replace(".", "\\.");
		//替换url中的变量
		urlInfo = replaceVariable(urlInfo);
		
		//拼接请求url
		StringBuilder builder = new StringBuilder();
		builder.append("^[/]*")
				  .append(urlInfo)
				  .append("[\\\\?]?");
		
		return builder.toString();
	}
	
	//组装参数部分的正则表达式
	private static String buildReqParamReg(String paramInfo){
		if (StringUtils.isEmpty(paramInfo)) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		String paramBuffer = replaceVariable(paramInfo); 
	    
	    //参数分开匹配
	    String[] paramArray = paramBuffer.split("&");
	    int paramSize = paramArray.length;
	    if (paramSize > 0) {
	    	builder.append("(");
			for (int i = 0; i < paramSize; i++) {
				builder.append("(")
				       .append(paramArray[i])
				       .append("[&]?)");
				if (i != paramSize - 1) {
					builder.append("|");
				}
			}
			builder.append("){")
				   .append(paramSize)
				   .append("}");
		}
	    
	    return builder.toString();
	}
	
	//替换变量
	private static String replaceVariable(String value){
		//参数格式:name={name}&id={id}
		StringBuffer result = new StringBuffer();
		Pattern pattern = Pattern.compile("\\{(.*?)\\}"); 
	    Matcher matcher = pattern.matcher(value);
	    while(matcher.find()) { 
	    	matcher.appendReplacement(result, "[\\\\w_-]*"); 
	    } 
	    matcher.appendTail(result); 
	    
	    return result.toString();
	}
}