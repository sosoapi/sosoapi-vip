package com.dev.base.utils;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 
		* <p>Title: 资源工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年10月9日下午2:58:40</p>
 */
public class ResourceUtils {
	/**
	 * 
			*@name 加载指定资源
			* <pre class="code">
			* /WEB-INF/*-context.xml
			* com/mycompany/**&#47;applicationContext.xml
			* file:C:/some/path/*-context.xml
			* classpath:com/mycompany/**&#47;applicationContext.xml</pre>
			*@Description  
			*@CreateDate 2016年10月9日下午2:59:03
	 */
	public static Resource[] load(String locationPattern){
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		try {
			return resourcePatternResolver.getResources(locationPattern);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
			*@name 加载指定单个资源
			* <pre class="code">
			* /WEB-INF/*-context.xml
			* com/mycompany/**&#47;applicationContext.xml
			* file:C:/some/path/*-context.xml
			* classpath:com/mycompany/**&#47;applicationContext.xml</pre>
			*@Description  
			*@CreateDate 2016年10月9日下午2:59:03
	 */
	public static Resource loadSingle(String locationPattern){
		Resource[] resources = load(locationPattern);
		Resource result = resources != null && resources.length > 0 ? resources[0] : null;
		return result;	
	}
}
