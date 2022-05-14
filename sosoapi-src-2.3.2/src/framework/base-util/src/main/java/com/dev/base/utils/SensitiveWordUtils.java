package com.dev.base.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: 敏感词工具类</p>
		* <p>Description: 摘自：http://cmsblogs.com/?p=1031</p>
		* <p>CreateDate: 2016年7月29日下午2:39:16</p>
 */
public class SensitiveWordUtils {
	/** 静态敏感词库*/
	private static Map SENS_WORD_MAP = null;
	/** 默认替换字符*/
	private static String defReplaceChar = "*";
	
	/**
	 * 
			*@name 加载敏感词库
			*@Description  
			*@CreateDate 2016年7月29日下午4:32:23
	 */
	public static void load(List<String> wordList){
		SENS_WORD_MAP = buildSensitiveWordMap(wordList);
	}
	
	/**
	 * 
			*@name 加载敏感词库
			*@Description  
			*@CreateDate 2016年7月29日下午4:32:23
	 */
	public static void load(String filePath,String encoding){
		SENS_WORD_MAP = buildSensitiveWordMap(filePath,encoding);
	}
	
	/**
	 * 
			*@name 判断文字是否包含敏感字符
			*@Description 
			*@return 若包含返回true，否则返回false 
			*@CreateDate 2016年7月29日下午3:18:39
	 */
	public static boolean isContain(String content){
		return isContain(SENS_WORD_MAP, content, true);
	}
	
	/**
	 * 
			*@name 判断文字是否包含敏感字符
			*@Description 
			*@return 若包含返回true，否则返回false 
			*@CreateDate 2016年7月29日下午3:18:39
	 */
	public static boolean isContain(Map sensitiveWordMap,String content,boolean minMatch){
		boolean flag = false;
		if (StringUtils.isEmpty(content) || CollectionUtils.isEmpty(sensitiveWordMap)) {
			return flag;
		}
		
		int size = content.length();
		for(int i = 0 ; i < size ; i ++){
			if (check(sensitiveWordMap,content, i, minMatch) > 0) {//判断是否包含敏感字符
				flag = true;
				break ;
			}
		}
		return flag;
	}
	
	/**
	 * 
			*@name 获取文字中的敏感词
			*@Description  
			*@CreateDate 2016年7月29日下午3:21:38
	 */
	public static Set<String> getSensitiveWord(String content){
		return getSensitiveWord(SENS_WORD_MAP, content, true);
	}
	
	/**
	 * 
			*@name 获取文字中的敏感词
			*@Description  
			*@CreateDate 2016年7月29日下午3:21:38
	 */
	public static Set<String> getSensitiveWord(Map sensitiveWordMap,String content, boolean minMatch){
		Set<String> result = new HashSet<String>();
		if (StringUtils.isEmpty(content) || CollectionUtils.isEmpty(sensitiveWordMap)) {
			return result;
		}
		
		int size = content.length();
		int length = 0;
		for(int i = 0 ; i < size; i ++){
			//判断是否包含敏感字符
			length = check(sensitiveWordMap,content, i, minMatch);    
			if(length > 0){ //存在,加入list中
				result.add(content.substring(i, i+length));
				i = i + length - 1; //减1的原因，是因为for会自增
			}
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 替换敏感字字符
			*@Description  
			*@CreateDate 2016年7月29日下午3:21:55
	 */
	public static String replace(String content,String replaceChar){
		return replace(SENS_WORD_MAP, content, true, replaceChar);
	}
	
	/**
	 * 
			*@name 使用默认字符替换敏感字字符
			*@Description  
			*@CreateDate 2016年7月29日下午3:21:55
	 */
	public static String replaceByDef(String content){
		return replace(SENS_WORD_MAP, content, true, defReplaceChar);
	}
	
	/**
	 * 
			*@name 替换敏感字字符
			*@Description  
			*@CreateDate 2016年7月29日下午3:21:55
	 */
	public static String replace(Map sensitiveWordMap,String content,boolean minMatch,String replaceChar){
		if (StringUtils.isEmpty(content) || CollectionUtils.isEmpty(sensitiveWordMap)) {
			return content;
		}
		
		String result = content;
		Set<String> set = getSensitiveWord(sensitiveWordMap,content, minMatch);     //获取所有的敏感词
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			result = result.replaceAll(word, replaceString);
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 根据文件创建敏感词库
			*@Description  
			*@CreateDate 2016年7月29日下午3:06:08
	 */
	public static Map buildSensitiveWordMap(String filePath,String encoding){
		List<String> wordList = null;
		try {
			wordList = FileUtils.readLines(new File(filePath), encoding);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return buildSensitiveWordMap(wordList);
	}
	
	/**
	 *      *@name 创建敏感词库
	 *		*@Description 
	 *		*@CreateDate 2016年7月29日下午3:00:54
	 */
	public static Map buildSensitiveWordMap(List<String> wordList){
		wordList = filter(wordList);
		//初始化敏感词容器，减少扩容操作
		Map sensitiveWordMap = new HashMap(wordList.size());
		if (CollectionUtils.isEmpty(wordList)) {
			return sensitiveWordMap;
		}
		
		Map nowMap = null;
		Map newWordMap = null;
		char keyChar;
		Object wordMap = null;
		int wordLength = 0;
		for (String word : wordList) {
			nowMap = sensitiveWordMap;
			wordLength = word.length();
			for(int i = 0 ; i < wordLength; i ++){
				keyChar = word.charAt(i);       //转换成char型
				wordMap = nowMap.get(keyChar);//获取
				
				if(wordMap != null){ //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{ //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWordMap = MapUtils.newMap();
					newWordMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWordMap);
					nowMap = newWordMap;
				}
				
				if(i == word.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
		
		return sensitiveWordMap;
	}
	
	//过滤重复并处理头尾空格
	private static List<String> filter(List<String> srcList){
		List<String> distList = new ArrayList<String>();
		if (CollectionUtils.isEmpty(srcList)) {
			return distList;
		}
		
		for (String src : srcList) {
			src = src.trim();
			if (StringUtils.isEmpty(src) || distList.contains(src)) {
				continue ;
			}
			
			distList.add(src);
		}
		
		return distList;
	}
	
	//获取替换字符串
	private static String getReplaceChars(String replaceChar,int length){
		String resultReplace = replaceChar;
		for(int i = 1 ; i < length ; i ++){
			resultReplace += replaceChar;
		}
		
		return resultReplace;
	}
	
	//检查文字中是否包含敏感字符
	//如果存在，则返回敏感词字符的长度，不存在返回0
	//minMatch:是否最小化匹配
	private static int check(Map sensitiveWordMap,String content,int beginIndex,boolean minMatch){
		if (StringUtils.isEmpty(content) || CollectionUtils.isEmpty(sensitiveWordMap)) {
			return 0;
		}
		
		//敏感词结束标识位：用于敏感词只有1位的情况
		boolean flag = false; 
		//匹配标识数默认为0
		int matchFlag = 0;    
		char word = 0;
		Map nowMap = sensitiveWordMap;
		int size = content.length();
		for(int i = beginIndex; i < size; i++){
			word = content.charAt(i);
			//获取指定key
			nowMap = (Map)nowMap.get(word); 
			//存在，则判断是否为最后一个
			if(nowMap != null){ 
				//找到相应key，匹配标识+1 
				matchFlag ++;     				
				//如果为最后一个匹配规则,结束循环，返回匹配标识数
				if("1".equals(nowMap.get("isEnd"))){  
					//结束标志位为true
					flag = true;        
					//最小规则，直接返回,最大规则还需继续查找
					if(minMatch){    
						break;
					}
				}
			}
			else{ //不存在，直接返回
				break;
			}
		}
		
		if(matchFlag < 2 || !flag){ //长度必须大于等于1，为词 
			matchFlag = 0;
		}
		
		return matchFlag;
	}
}
