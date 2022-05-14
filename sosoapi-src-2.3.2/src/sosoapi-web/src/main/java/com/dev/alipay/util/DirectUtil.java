package com.dev.alipay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.alipay.constant.DirectCfg;
import com.dev.base.utils.MD5Utils;
import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 即时到账相关工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月21日下午3:35:57</p>
 */
public class DirectUtil {
	/**
	 * 
			*@name 组装请求参数
			*@Description  
			*@CreateDate 2017年1月21日下午3:36:54
	 */
	public static Map<String, String> buildReqParam(String tradeNo,String subName,String subDesc,String totalFee){
		//把请求参数打包成数组
		Map<String, String> paramMap = MapUtils.newMap();
		paramMap.put("service", DirectCfg.SERVICE);
		paramMap.put("partner", DirectCfg.PARTNER);
		paramMap.put("seller_id", DirectCfg.SELLER_ID);
		paramMap.put("_input_charset", DirectCfg.CHARSET);
		paramMap.put("payment_type", DirectCfg.PAYMENT_TYPE);
		paramMap.put("notify_url", DirectCfg.NOTIFY_URL);
		paramMap.put("return_url", DirectCfg.RETURN_URL);
		paramMap.put("anti_phishing_key", null);
		paramMap.put("exter_invoke_ip", null);
		paramMap.put("out_trade_no", tradeNo);
		paramMap.put("subject", subName);
		paramMap.put("body", subDesc);
		paramMap.put("total_fee", totalFee);
		
		//过滤空值和签名参数
		paramMap = filterParam(paramMap);
		
		//签名结果与签名方式加入请求提交参数组中
		String sign = signParam(paramMap);
		paramMap.put("sign", sign);
		paramMap.put("sign_type", DirectCfg.SIGN_TYPE);
		
		return paramMap;
	}
	
	/** 
     * 除去数组中的空值和签名参数
     */
    public static Map<String, String> filterParam(Map<String, String> paramMap) {
        Map<String, String> result = MapUtils.newMap();
        if (CollectionUtils.isEmpty(paramMap)) {
			return result;
		}

        Set<String> keySet = paramMap.keySet();
        String value = "";
        for (String key : keySet) {
			value = paramMap.get(key);
			if (StringUtils.isEmpty(value) 
					|| key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			
			result.put(key, value);
		}
        
        return result;
    }
    
    /**
     * 
    		*@name 参数签名
    		*@Description  
    		*@CreateDate 2017年1月21日下午3:37:17
     */
    public static String signParam(Map<String, String> paramMap){
    	String sign = "";
        String paramForSign = buildParamForSign(paramMap);
        if(DirectCfg.SIGN_TYPE.equals("MD5") ) {
        	sign = MD5Utils.sign(paramForSign, DirectCfg.KEY);
        }
        
        return sign;
    }
    
    /**
     * 
    		*@name 组装待签名参数信息
    		*@Description  
    		*@CreateDate 2017年1月21日下午3:47:58
     */
    public static String buildParamForSign(Map<String, String> paramMap){
    	List<String> keyList = new ArrayList<String>(paramMap.keySet());
    	//参数名称排序
        Collections.sort(keyList);

        StringBuilder paramBuilder = new StringBuilder();
        int size = keyList.size();
        String key = "";
        String value = "";
        for (int i = 0; i < size; i++) {
        	key = keyList.get(i);
			value = paramMap.get(key);
			
			paramBuilder.append(key)
			   		   .append("=")
			           .append(value);
			
			if (i != size - 1) {
				paramBuilder.append("&");
			}
		}
        
        return paramBuilder.toString();
    }
    
    /**
     * 
    		*@name 验证消息是否是支付宝发出的合法消息
    		*@param paramMap 通知返回来的参数数组
    		*@Description  
    		*@CreateDate 2017年1月21日下午3:39:35
     */
    public static boolean verify(Map<String, String> paramMap) {
    	if (CollectionUtils.isEmpty(paramMap)) {
			return false;
		}
    	
        //判断responsetTxt是否为true，isSign是否为true
        //responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
    	String notifyId = paramMap.get("notify_id");
    	String responseTxt = StringUtils.isEmpty(notifyId) ? "false" : verifyResponse(notifyId);
		
	    boolean isSign = getSignVeryfy(paramMap, paramMap.get("sign"));

        return isSign && responseTxt.equals("true");
    }

    /**
     * 
    		*@name 根据反馈回来的信息，生成签名结果
    		*@param paramMap 通知返回来的参数数组
    		*@param sign 比对的签名结果
    		*@Description  
    		*@CreateDate 2017年1月21日下午3:43:10
     */
	private static boolean getSignVeryfy(Map<String, String> paramMap, String sign) {
		if (StringUtils.isEmpty(sign)) {
			return false;
		}
		
		//过滤空值、sign与sign_type参数
    	Map<String, String> paramMapTemp = filterParam(paramMap);
        //获取待签名字符串
        String paramForSign = buildParamForSign(paramMapTemp);
        
        //获得签名验证结果
        boolean isSign = false;
        if(DirectCfg.SIGN_TYPE.equals("MD5") ) {
        	isSign = MD5Utils.verify(sign, paramForSign, DirectCfg.KEY);
        }
        
        return isSign;
    }

	/**
	 * 
			*@name 获取远程服务器ATN结果,验证返回URL
			*@Description  
			*验证结果集：
			* invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
			* true 返回正确信息
			* false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
			*@CreateDate 2017年1月21日下午4:03:14
	 */
    private static String verifyResponse(String notifyId) {
        //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
        String partner = DirectCfg.PARTNER;
        String veryfyUrl = DirectCfg.HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notifyId;
        return checkUrl(veryfyUrl);
    }

    /**
     * 
    		*@name 获取远程服务器ATN结果
    		*@Description  
    		*验证结果集：
		    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
		    * true 返回正确信息
		    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    		*@CreateDate 2017年1月21日下午4:04:11
     */
    private static String checkUrl(String urlValue) {
        String inputLine = "";
        try {
            URL url = new URL(urlValue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = reader.readLine().toString();
        } 
        catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
    
    /**
     * 
    		*@name 解析通知请求参数
    		*@Description  
    		*@CreateDate 2017年1月21日下午4:20:05
     */
    public static Map<String, String> parseNoticeParam(Map paramMap){
    	Map<String,String> result = MapUtils.newMap();
    	Iterator keyIterator = paramMap.keySet().iterator();
    	String key = "";
    	String[] valueArray = null;
    	String value = "";
    	while (keyIterator.hasNext()) {
			key = (String)keyIterator.next();
			valueArray = (String[])paramMap.get(key);
			
			value = "";
			for (int i = 0; i < valueArray.length; i++) {
				value += (i == valueArray.length - 1) ? valueArray[i] : valueArray[i] + ",";
			}
			
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//result.put(key, new String(value.getBytes("ISO-8859-1"), "UTF-8"));
			result.put(key, value);
		}
    	
    	return result;
    }
    
    /**
     * 
    		*@name 直接输出
    		*@Description  
    		*@CreateDate 2017年1月21日下午4:34:48
     */
    public static void print(HttpServletResponse response,String content){
    	try {
    		response.setContentType("text/html;charset=utf-8"); 
    		response.setCharacterEncoding("UTF-8"); 
    		PrintWriter out = response.getWriter(); //在设置完编码以后在获取输出流就好了。 
    		out.print(content); 
    		out.flush(); 
    		out.close();
		} 
        catch (IOException e) {
			e.printStackTrace();
		}
    }
}
