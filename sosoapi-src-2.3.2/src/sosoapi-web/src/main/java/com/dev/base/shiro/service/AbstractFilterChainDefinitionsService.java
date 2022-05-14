package com.dev.base.shiro.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.dev.base.json.JsonUtils;

/**
 * 
		* <p>Title: 加载权限规则链服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午5:05:46</p>
 */
public abstract class AbstractFilterChainDefinitionsService implements FilterChainDefinitionsService{
	private final static Logger logger = Logger.getLogger(AbstractFilterChainDefinitionsService.class);  
	
	/** 权限规则链信息，同ShiroFilterFactoryBean中的filterChainDefinitions*/
    private String filterChainDefinitions = "";  
  
    @Autowired  
    private ShiroFilterFactoryBean shiroFilterFactoryBean;  
    
    @PostConstruct  
    @Override
    public void initFilterChain() {  
    	//加载配置文件设置的权限资源
        shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChain());  
        logger.debug("init shiro filter chain success...");
        logger.debug("current filter chain is:\r\n" + JsonUtils.toFormatJson(getFilterChainDefinitionMap()));
    }  
  
    @Override
    public void reloadFilterChain() {  
        synchronized (shiroFilterFactoryBean) {  
            AbstractShiroFilter shiroFilter = null;  
            try {  
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();  
            } 
            catch (Exception e) {  
            	e.printStackTrace();
            }  
  
            //获取过滤管理器  
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();  
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();  
  
            //清空初始配置  
            manager.getFilterChains().clear();  
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();  
  
            //重新构建生成  
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChain());
            Map<String, String> filterChainMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();  
            String url = "";
            String chainDefinition = "";
            for (Map.Entry<String, String> filterChain : filterChainMap.entrySet()) {  
                url = filterChain.getKey();  
                chainDefinition = filterChain.getValue().trim().replace(" ", "");  
                manager.createChain(url, chainDefinition);  
            }  
  
            logger.debug("update shiro permission success...");  
        }  
    }  
  
    //读取默认配置规则
    private Section loadFilterChain() {  
        Ini ini = new Ini();  
        //加载资源文件节点串  
        ini.load(filterChainDefinitions); 
        
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        if (CollectionUtils.isEmpty(section)) {
			section = ini.addSection(Ini.DEFAULT_SECTION_NAME);
		}
        
        //第三方
        Map<String, String> otherFilterChain = loadOtherFilterChain();
        if (!CollectionUtils.isEmpty(otherFilterChain)) {
			section.putAll(otherFilterChain);
		}
        
        return section;
    }  
  
    @Override
    public Map<String, String> getFilterChainDefinitionMap() {
		return shiroFilterFactoryBean.getFilterChainDefinitionMap();
	}
    
	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}  
}
