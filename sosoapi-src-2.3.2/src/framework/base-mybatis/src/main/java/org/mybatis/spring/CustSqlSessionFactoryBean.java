package org.mybatis.spring;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mybatis.spring.typealias.ClassPathBeanScanner;
import org.mybatis.spring.typealias.SkipTypeAlias;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: 自定义SqlSessionFactoryBean</p>
		* <p>Description: 自动扫描并注册别名，注意当前实现将会重置typeAliases属性</p>
		* <p>CreateDate: 2017年12月12日下午2:56:27</p>
 */
public class CustSqlSessionFactoryBean extends SqlSessionFactoryBean {
	/** 是否自动注册别名，默认为true*/
	private boolean autoRegTypeAlias = false;
	
	/** 扫描基础包*/
	private String typeAliasBasePackage;
	
	/** 包含指定注解*/
	private Class<? extends Annotation> typeAliasAnnotationClass;

	/** 指定类或父类类型*/
	private Class<?> typeAliasTargetType;
	
	/** 类名符合指定正则表达式*/
	private String typeAliasClassPattern;

	public void afterPropertiesSet() throws Exception {
		/** 初始化别名数组*/
		initTypeAliases();
		super.afterPropertiesSet();
	}

	/** 初始化别名数组*/
	private void initTypeAliases(){
		if (!autoRegTypeAlias) {
			return ;
		}
		
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		ClassPathBeanScanner scanner = new ClassPathBeanScanner(factory);
		scanner.setAnnotationClass(this.typeAliasAnnotationClass);
		scanner.setTargetType(this.typeAliasTargetType);
		scanner.setClassPattern(this.typeAliasClassPattern);
		scanner.setExcludeAnnotationClass(SkipTypeAlias.class);
	    scanner.registerFilters();
	    Set<BeanDefinitionHolder> beanDefinitions = scanner.doScan(StringUtils.tokenizeToStringArray(this.typeAliasBasePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	    List<Class<?>> typeAliasList = new ArrayList<Class<?>>();
	    GenericBeanDefinition definition = null;
	    try{
	    	for (BeanDefinitionHolder holder : beanDefinitions) {
				definition = (GenericBeanDefinition) holder.getBeanDefinition();
				typeAliasList.add(Class.forName(definition.getBeanClassName()));
			}
	    }
		catch (Exception e){
			e.printStackTrace();
		}
		
		if (!typeAliasList.isEmpty()) {
			setTypeAliases(typeAliasList.toArray(new Class<?>[0]));
		}
	}
	
	public String getTypeAliasBasePackage() {
		return typeAliasBasePackage;
	}

	public void setTypeAliasBasePackage(String typeAliasBasePackage) {
		this.typeAliasBasePackage = typeAliasBasePackage;
	}

	public Class<? extends Annotation> getTypeAliasAnnotationClass() {
		return typeAliasAnnotationClass;
	}

	public void setTypeAliasAnnotationClass(Class<? extends Annotation> typeAliasAnnotationClass) {
		this.typeAliasAnnotationClass = typeAliasAnnotationClass;
	}

	public Class<?> getTypeAliasTargetType() {
		return typeAliasTargetType;
	}

	public void setTypeAliasTargetType(Class<?> typeAliasTargetType) {
		this.typeAliasTargetType = typeAliasTargetType;
	}

	public String getTypeAliasClassPattern() {
		return typeAliasClassPattern;
	}

	public void setTypeAliasClassPattern(String typeAliasClassPattern) {
		this.typeAliasClassPattern = typeAliasClassPattern;
	}

	public boolean isAutoRegTypeAlias() {
		return autoRegTypeAlias;
	}

	public void setAutoRegTypeAlias(boolean autoRegTypeAlias) {
		this.autoRegTypeAlias = autoRegTypeAlias;
	}
}
