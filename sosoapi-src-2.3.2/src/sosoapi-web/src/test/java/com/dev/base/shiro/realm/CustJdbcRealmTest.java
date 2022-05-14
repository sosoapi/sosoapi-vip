package com.dev.base.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.dev.base.test.BaseTest;

public class CustJdbcRealmTest extends BaseTest{

	@Test
	public void testLogin() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("demo@sosoapi.com", "880814");
		subject.login(token);
		System.out.println("login:" + subject.isAuthenticated());
	}

}
