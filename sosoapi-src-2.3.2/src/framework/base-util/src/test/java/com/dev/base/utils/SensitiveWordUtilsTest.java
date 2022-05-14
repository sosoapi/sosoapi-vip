package com.dev.base.utils;

import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.dev.base.json.JsonUtils;

public class SensitiveWordUtilsTest {
	private Map sensitiveWordMap = null;
	private String content = "";
	private String encoding = "GBK";
	//D:\\SensitiveWord.txt
	private String filePath = "src\\test\\resources\\SensitiveWord.txt";
	
	@Before
	public void init(){
		content = "太多falun的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
				+ "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
				+ "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
		
		sensitiveWordMap = SensitiveWordUtils.buildSensitiveWordMap(filePath, encoding);
		
		System.out.println("待检测语句字数：" + content.length());
	}

	@Test
	public void getSensitiveWord() {
		long beginTime = System.currentTimeMillis();
		SensitiveWordUtils.load(filePath, encoding);
		Set<String> set = SensitiveWordUtils.getSensitiveWord(content);
		long endTime = System.currentTimeMillis();
		System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
		System.out.println("总共消耗时间为：" + (endTime - beginTime));
	}
	
	@Test
	public void testIsContain() {
		long beginTime = System.currentTimeMillis();
		boolean result = SensitiveWordUtils.isContain(sensitiveWordMap,content, true);
		long endTime = System.currentTimeMillis();
		System.out.println("语句中是否包含敏感词：" + result);
		System.out.println("总共消耗时间为：" + (endTime - beginTime));
	}

	@Test
	public void testGetSensitiveWord() {
		long beginTime = System.currentTimeMillis();
		Set<String> set = SensitiveWordUtils.getSensitiveWord(sensitiveWordMap,content, true);
		long endTime = System.currentTimeMillis();
		System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
		System.out.println("总共消耗时间为：" + (endTime - beginTime));
	}

	@Test
	public void testReplace() {
		long beginTime = System.currentTimeMillis();
		String info = SensitiveWordUtils.replace(sensitiveWordMap,content, true,"*");
		long endTime = System.currentTimeMillis();
		System.out.println(info);
		System.out.println("总共消耗时间为：" + (endTime - beginTime));
	}

	@Test
	public void testBuildSensitiveWordMap() {
		long beginTime = System.currentTimeMillis();
		Map map = SensitiveWordUtils.buildSensitiveWordMap("D:\\SensitiveWord.txt", encoding);
		long endTime = System.currentTimeMillis();
		System.out.println("总共消耗时间为：" + (endTime - beginTime));
		System.out.println(JsonUtils.toFormatJson(map));
	}
}
