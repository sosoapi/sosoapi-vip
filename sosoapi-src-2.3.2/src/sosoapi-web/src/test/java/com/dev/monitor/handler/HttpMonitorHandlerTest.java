package com.dev.monitor.handler;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.test.BaseTest;

public class HttpMonitorHandlerTest extends BaseTest{
	@Autowired
	private HttpMonitorHandler httpMonitorHandler;
	
	@Test
	public void doMonitorByRate() {
		httpMonitorHandler.doMonitorByRate(10);
	}
}
