package com.dev.base.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.util.StringUtils;

import com.dev.base.exception.errorcode.SysErrorCode;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;

/**
 * 
		* <p>Title: oval验证工具类</p>
		* <p>Description: 用户手册 http://oval.sourceforge.net/userguide.html</p>
		* <p>CreateDate: 2017年6月29日下午3:15:34</p>
 */
public class OvalValidateUtils {
	private static Validator validator = new Validator(); 
	
	static {
        ResourceBundleMessageResolver resolver = new ResourceBundleMessageResolver();
        //classes根目录
        //ResourceBundle bundle = ResourceBundle.getBundle("oval_",Locale.SIMPLIFIED_CHINESE);
        //指定包下，包名com.dev.base.utils
        //ResourceBundle bundle = ResourceBundle.getBundle("com.dev.base.utils.oval_",Locale.SIMPLIFIED_CHINESE);
        //classes指定目录
        ResourceBundle bundle = ResourceBundle.getBundle("i18n/oval_",Locale.SIMPLIFIED_CHINESE);
        resolver.addMessageBundle(bundle);
        Validator.setMessageResolver(resolver);
	}
	
	/**
	 * 
			*@name 验证指定对象
			*@Description  
			*@CreateDate 2017年6月29日下午4:17:52
	 */
	public static void validate(final Object validatedObject){
		List<ConstraintViolation> result = validator.validate(validatedObject);
		dealResult(result);
	}
	
	/**
	 * 
			*@name 验证指定对象
			*@Description  
			*@CreateDate 2017年6月29日下午4:17:52
	 */
	public static void validate(final Object validatedObject, final String... profiles){
		List<ConstraintViolation> result = validator.validate(validatedObject,profiles);
		dealResult(result);
	}
	
	/**
	 * 
			*@name 验证指定属性
			*@Description  
			*@CreateDate 2017年6月29日下午5:02:56
	 */
	public static void validateFieldValue(final Object validatedObject, final Field validatedField, 
						final Object fieldValueToValidate){
		List<ConstraintViolation> result = validator.validateFieldValue(validatedObject, validatedField, fieldValueToValidate);
		dealResult(result);
	}
	
	/**
	 * 
			*@name 验证指定属性
			*@Description  
			*@CreateDate 2017年6月29日下午5:02:56
	 */
	public static void validateFieldValue(final Object validatedObject, final String validatedFieldName, 
						final Object fieldValueToValidate){
		Field field = null;
		try {
			field = validatedObject.getClass().getDeclaredField(validatedFieldName);
		} 
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		} 
		catch (SecurityException e) {
			e.printStackTrace();
		}
		
		validateFieldValue(validatedObject, field, fieldValueToValidate);
	}
	
	//处理验证结果
	private static void dealResult(List<ConstraintViolation> result){
		if (result.size() == 0) {
			return ;
		}
		
		StringBuilder errorMsgBuilder = new StringBuilder();
		for (ConstraintViolation constraintViolation : result) {
			errorMsgBuilder.append(constraintViolation.getMessage())
						   .append("\r\n");
		}
		
		ValidateUtils.isTrue(StringUtils.isEmpty(errorMsgBuilder), SysErrorCode.SYS_001,errorMsgBuilder.toString());
	}
}
