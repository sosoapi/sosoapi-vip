package com.dev.admin.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.admin.service.PrivilegeService;
import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.json.JsonUtils;
import com.dev.base.test.BaseTest;

public class PrivilegeServiceImplTest extends BaseTest{
	@Autowired
	private PrivilegeService privilegeService;
	
	@Test
	public void testBuildMenuTree() {
		List<PrivilegeInfo> privList = privilegeService.buildMenuTree(1L);
		System.out.println(JsonUtils.toFormatJson(privList));
	}

}
