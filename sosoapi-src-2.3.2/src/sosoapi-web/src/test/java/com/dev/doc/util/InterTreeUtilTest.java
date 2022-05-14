package com.dev.doc.util;

import org.junit.Test;

import com.dev.base.json.JsonUtils;

public class InterTreeUtilTest {

	@Test
	public void test() {
		System.out.println(JsonUtils.toFormatJson(InterTreeUtil.staticNodeMap));
	}

}
