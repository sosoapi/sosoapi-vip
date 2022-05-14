package com.dev.base.util;

import java.io.File;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class MockUtilsTest {

	@Test
	public void invokeFunction() throws ScriptException, NoSuchMethodException {    
        ScriptEngine engine = MockUtils.getJsEngine();    
        String scriptText = "function greet(name) { println('Hello, ' + name); } ";    
        engine.eval(scriptText);    
        Invocable invocable = (Invocable) engine;    
        invocable.invokeFunction("greet", "Alex");    
    } 
    
	@Test
    public void invokeMethod() throws ScriptException, NoSuchMethodException {    
        ScriptEngine engine = MockUtils.getJsEngine();    
        String scriptText = "var obj = { getGreeting : function(name) { return 'Hello, ' + name; } }; ";    
        engine.eval(scriptText);    
        Invocable invocable = (Invocable) engine;    
        Object scope = engine.get("obj");    
        Object result = invocable.invokeMethod(scope, "getGreeting", "Alex");   //第一个参数为方法所属对象    
        System.out.println(result);    
    } 
    
	@Test
    public void invokeMockJs() throws Exception {
		String mockJsPath = "F:\\DevWorkspace\\api\\trunk\\sosoapi-web\\src\\main\\webapp\\plugin\\mock\\ext\\js\\mock-java.js";
		MockUtils.init(mockJsPath);
		
		String mockRulePath = "F:\\DevWorkspace\\api\\trunk\\sosoapi-web\\src\\test\\resources\\mockRule.json";
		String mockRule = FileUtils.readFileToString(new File(mockRulePath));
		
		for (int i = 0; i < 1; i++) {
			System.out.println(MockUtils.getMockData(mockRule));
		}
	}
}
