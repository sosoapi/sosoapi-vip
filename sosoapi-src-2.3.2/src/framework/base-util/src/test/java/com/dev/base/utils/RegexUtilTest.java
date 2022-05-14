package com.dev.base.utils;

import org.junit.Test;

public class RegexUtilTest {

	@Test
	public void testParseReqUrlRegExpress() {
		String url = "OrderController/complete/{id}";
		System.out.println(RegexUtil.parseReqUrlRegExpress(url));
	}

}
