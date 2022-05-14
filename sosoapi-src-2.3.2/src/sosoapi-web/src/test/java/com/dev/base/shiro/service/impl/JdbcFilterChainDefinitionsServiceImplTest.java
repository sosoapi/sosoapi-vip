package com.dev.base.shiro.service.impl;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.json.JsonUtils;
import com.dev.base.shiro.service.FilterChainDefinitionsService;
import com.dev.base.test.BaseTest;

public class JdbcFilterChainDefinitionsServiceImplTest extends BaseTest{
	@Autowired
	private FilterChainDefinitionsService filterService;
	
	@Test
	public void testLoadOtherFilterChain() {
	}

	@Test
	public void testReloadFilterChain() {
		for (int i = 0; i < 5; i++) {
			System.out.println("reload time:" + i);
			filterService.reloadFilterChain();
			System.out.println(JsonUtils.toFormatJson(filterService.getFilterChainDefinitionMap()));
		}
	}

	@Test
	public void testGetFilterChainDefinitionMap() {
		Map<String, String> filterChainMap = filterService.getFilterChainDefinitionMap();
		System.out.println(JsonUtils.toFormatJson(filterChainMap));
	}

}
