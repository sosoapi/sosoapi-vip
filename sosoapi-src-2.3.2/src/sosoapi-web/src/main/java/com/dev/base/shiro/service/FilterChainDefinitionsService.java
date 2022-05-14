package com.dev.base.shiro.service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
		* <p>Title: 加载权限规则链服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午5:02:15</p>
 */
public interface FilterChainDefinitionsService {
    /**
     * 
    		*@name 初始化权限规则链
    		*@Description  
    		*@CreateDate 2017年6月21日下午5:04:17
     */
    void initFilterChain();  
  
    /**
     * 
    		*@name 重新加载权限规则链 (强制线程同步)
    		*@Description  
    		*@CreateDate 2017年6月21日下午5:04:25
     */
    void reloadFilterChain();  
  
    /**
     * 
    		*@name 加载第三方权限资源配置
    		*@Description  
    		*@CreateDate 2017年6月21日下午5:04:42
     */
    LinkedHashMap<String, String> loadOtherFilterChain();  
    
    /**
     * 
    		*@name 获取当前权限资源配置
    		*@Description  
    		*@CreateDate 2017年6月21日下午6:00:22
     */
    Map<String, String> getFilterChainDefinitionMap();
}
