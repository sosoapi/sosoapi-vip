package com.google.code.kaptcha.text.impl;

import java.util.Random;

import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.util.Config;
import com.google.code.kaptcha.util.Configurable;

/**
 * 
		* <p>Title: 生成随机个数验证码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月18日上午11:44:09</p>
 */
public class RandomTextCreator extends Configurable implements TextProducer{

	public RandomTextCreator() {

	}
	
	public RandomTextCreator(Config config){
		setConfig(config);
	}
	
	@Override
	public String getText() {
		int length = getConfig().getTextProducerCharLength();
		return getText(length);
	}
	
	/**
	 * 
			*@name 生成指定范围内随机个数验证码
			*@Description  
			*@CreateDate 2017年9月18日上午11:45:04
	 */
	public String getText(int min,int max) {
		int length = new Random().nextInt(max - min + 1) + min;
		return getText(length);
	}
	
	/**
	 * 
			*@name 生成指定长度验证码
			*@Description  
			*@CreateDate 2017年9月18日上午11:48:25
	 */
	public String getText(int length){
		char[] chars = getConfig().getTextProducerCharString();
		Random rand = new Random();
		StringBuffer text = new StringBuffer();
		for (int i = 0; i < length; i++){
			text.append(chars[rand.nextInt(chars.length)]);
		}

		return text.toString();
	}
}
