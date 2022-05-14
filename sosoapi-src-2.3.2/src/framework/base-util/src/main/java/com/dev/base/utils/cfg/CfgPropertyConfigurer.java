package com.dev.base.utils.cfg;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

/**
 * 
		* <p>Title: properties参数配置</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年12月20日下午6:46:02</p>
 */
public class CfgPropertyConfigurer implements InitializingBean {
	private Resource[] locations;

	public CfgPropertyConfigurer() {
		
	}
	
	public CfgPropertyConfigurer(Resource[] locations) {
		this.locations = locations;
	}

	public Resource[] getLocations() {
		return locations;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (CollectionUtils.isEmpty(CfgPropertyUtils.getProperties())) {
			CfgPropertyUtils.loadProperties(this.locations);
		}
	}
}
