package com.dev.monitor.timer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.monitor.handler.HttpMonitorHandler;

/**
 * 
		* <p>Title: http监控执行定时器</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月15日下午3:35:52</p>
 */
@Service
public class HttpMonitorTimer {
	private static Logger logger = LogManager.getLogger(HttpMonitorTimer.class);
	
	@Autowired
	private HttpMonitorHandler httpMonitorHandler;
	
	
	/**
	 * 
			*@name 执行监控任务
			*@Description  
			*@CreateDate 2017年5月15日下午5:53:16
	 */
	public void doMonitorByRate10Min(){
		httpMonitorHandler.doMonitorByRate(10);
	}
	
	/**
	 * 
			*@name 执行监控任务
			*@Description  
			*@CreateDate 2017年5月15日下午5:53:16
	 */
	public void doMonitorByRate20Min(){
		httpMonitorHandler.doMonitorByRate(20);
	}
	
	/**
	 * 
			*@name 执行监控任务
			*@Description  
			*@CreateDate 2017年5月15日下午5:53:16
	 */
	public void doMonitorByRate30Min(){
		httpMonitorHandler.doMonitorByRate(30);
	}
}
