package com.dev.sys.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.test.BaseTest;
import com.dev.sys.service.StaticDataService;

public class StaticDataServiceImplTest extends BaseTest{
	@Autowired
	private StaticDataService staticDataService;
	
	@Test
	public void testLoadPrivList() {
		staticDataService.loadPrivList(true);
	}
}
