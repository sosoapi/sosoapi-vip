package com.dev.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.dev.base.utils.RegexUtil;

public class RegUtilsTest {
	private String[] pathArray = null;
	
	private String[] exampleArray = null;
	
	@Before
	public void init(){
		pathArray = new String[]{	"/pets",
									"/pets/{id}",
									"pets/{id}",
									"wacc-sso-web/android/mobileLogin",
									"m=Home&c=Index&a=createVcode",
									"http://api.fangxuehai.com/index.php/index/index?t=1441875091&sig=1b88c7c60607fc91e990a59701b667a6",
									"testserver.ibaobeimao.com/main.php?action=bbmserver",
									"GetCouponList?username={username}&parkcode={parkcode}",
									"/?s=/Home/Pay/getPayTypeList"};
		
		/*exampleArray = new String[]{"/pets",
									"/pets/123",
									"pets/456",
									"wacc-sso-web/android/mobileLogin",
									"m=Home&c=Index&a=createVcode",
									"http://api.fangxuehai.com/index.php/index/index?t=1441875091&sig=1b88c7c60607fc91e990a59701b667a6",
									"testserver.ibaobeimao.com/main.php?action=bbmserver",
									"GetCouponList?username=234&parkcode=789",
									"/?s=/Home/Pay/getPayTypeList"};*/
		
		exampleArray = new String[]{"/pets",
									"/pets/123",
									"pets/456",
									"wacc-sso-web/android/mobileLogin?",
									"m=Home&a=createVcode&c=Index&test=1",
									"http://api.fangxuehai.com/index.php/index/index?t=1441875091&sig=1b88c7c60607fc91e990a59701b667a9",
									"testserver.ibaobeimao.com/main.php?action=bbmserver",
									"GetCouponList?parkcode=789&username=234",
									"/?s=/Home/Pay/getPayTypeList"};
		
	}
	
	@Test
	public void testReg(){
		String reqExpress = "";
		for (int i = 0; i < pathArray.length; i++) {
			reqExpress = RegexUtil.parseReqUrlRegExpress(pathArray[i]);
			System.out.println("path:" + pathArray[i]);
			System.out.println("reg:" + reqExpress);
			System.out.println("example:" + exampleArray[i]);
			System.out.println("match:" + Pattern.matches(reqExpress, exampleArray[i]));
			System.out.println("");
		}
	}
	
	@Test
	public void testRegSimple(){
		String content = "/user/complex/info.htm";
		String test = "GetCouponList.do?parkcode=12&username=123&nick1=12";
		String reg = RegexUtil.parseReqUrlRegExpress(content);
		System.out.println(reg);
		System.out.println(Pattern.matches(reg, test));
	}
}
