package com.dev.base.util;

import java.io.File;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.dev.base.exception.ValidateException;
import com.dev.base.exception.code.ErrorCode;

/**
 * 
		* <p>Title: mock工具类</p>
		* <p>Description: 通过调用mock.js来实现动态mock</p>
		* <p>CreateDate: 2017年2月21日下午4:05:49</p>
 */
public class MockUtils {
	private static Logger logger = LogManager.getLogger(MockUtils.class);
	
	//mock.js文件目录
	private static String MOCK_JS_PATH = "plugin/mock/ext/js/mock-java.js";
	
	//js引擎
	private static ScriptEngine engine = null;
	
	//方法调用
	private static Invocable invocable = null;
	
	//mock对象
	private static Object mockObj = null;
	
	//初始化
	static{
		init(WebUtil.getWebRootPath(MOCK_JS_PATH));
	}
	
	/**
	 * 
			*@name 初始化
			*@Description  
			*@CreateDate 2017年2月21日下午6:41:16
	 */
	public static void init(String jsPath){
		String jsText = "";
		try {
			jsText = FileUtils.readFileToString(new File(jsPath));
		} 
		catch (IOException e) {
			logger.error("mock-java.js不存在，路径：" + jsPath);
		}
		
		engine = MockUtils.getJsEngine();
		if (engine != null && !StringUtils.isEmpty(jsText)) {
			try {
				engine.eval(jsText);
				invocable = (Invocable)engine;
				mockObj = engine.get("Mock");
			} 
			catch (ScriptException e) {
				logger.error("mock-java.js解析失败!");
			}
		}
	}
	
	/**
	 * 
			*@name 获取js引擎
			*@Description  
			*@CreateDate 2017年2月21日下午1:52:23
	 */
	public static ScriptEngine getJsEngine(){
		ScriptEngineManager manager = new ScriptEngineManager();    
		ScriptEngine engine = manager.getEngineByName("JavaScript");    
	    //ScriptEngine engine = manager.getEngineByExtension("js");    
	    //ScriptEngine engine = manager.getEngineByMimeType("text/javascript");    
		if (engine == null) {   
			logger.error("找不到js执行引擎。");
		}    
		
		return engine;
	}
	
	//转换mock规则为json对象
	private static Object getMockRule(String mockRuleJson) throws Exception {  
        /*String script = "function getMockRule() { return " + mockRuleJson + ";}";  
        engine.eval(script);  
        return invocable.invokeFunction("getMockRule",mockRuleJson); */
        
        /*String param = "var obj = " + mockRuleJson;
        engine.eval(param);
        return engine.get("obj");*/
		
		/*String script = "function getMockRule(param) { return eval('(' + param + ')');}";  
        engine.eval(script);  
        return invocable.invokeFunction("getMockRule",mockRuleJson);*/
		
		return engine.eval("(" + mockRuleJson + ")");
    }
	
	/**
	 * 
			*@name 获取mock数据，规则同mock.js(http://mockjs.com)
			*@Description 
			*@CreateDate 2017年2月21日下午4:10:48
	 */
	public static String getMockData(String mockRule){
		if (engine == null || invocable == null || mockObj == null) {
			logger.error("mock-java.js初始化失败或无法找到js引擎，无法动态mock数据！");
			return "";
		}
		
		String result = "";
		try {
			result = (String)invocable.invokeMethod(mockObj, "mock", getMockRule(mockRule));
		} 
		catch (Exception e) {
			throw new ValidateException(ErrorCode.SYS_001,"mock规则错误，请参考规则文档！");
		} 
		
		return result;
	}
}
