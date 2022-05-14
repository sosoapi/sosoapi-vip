package com.dev.base.qiniu.market.service.impl;

import org.junit.Test;

import com.dev.base.json.JsonUtils;
import com.dev.base.qiniu.market.resp.NropResp;

public class NropServiceImplTest {

	@Test
	public void testNrop() {
		NropServiceImpl serviceImpl = new NropServiceImpl();
		NropResp result = serviceImpl.nrop("http://head.png");
		System.out.println(JsonUtils.toFormatJson(result));
	}

}
