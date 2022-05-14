package org.mybatis.spring.typealias;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: 扫描指定类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年12月12日下午1:30:04</p>
 */
public class ClassPathBeanScanner extends ClassPathBeanDefinitionScanner {
	/** 扫描基础包*/
	private String basePackage;
	
	/** 包含指定注解*/
	private Class<? extends Annotation> annotationClass;

	/** 指定类或父类类型*/
	private Class<?> targetType;
	
	/** 类名符合指定正则表达式*/
	private String classPattern;
	
	/** 排除指定注解*/
	private Class<? extends Annotation> excludeAnnotationClass;

	public ClassPathBeanScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	/**
	 * 
			*@name 添加扫描过滤器
			*@Description  
			*@CreateDate 2017年12月12日下午1:33:02
	 */
	public void registerFilters() {
		boolean acceptAllInterfaces = true;

		//别名过滤
		if (this.annotationClass != null) {
			addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
			acceptAllInterfaces = false;
		}

		//指定类型或父类过滤
		if (this.targetType != null) {
			addIncludeFilter(new AssignableTypeFilter(this.targetType) {
				@Override
				protected boolean matchClassName(String className) {
					return false;
				}
			});
			acceptAllInterfaces = false;
		}

		//指定正则表达式过滤
		if (!StringUtils.isEmpty(this.classPattern)) {
			addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(this.classPattern)));
			acceptAllInterfaces = false;
		}
				
		//不做过滤，所有类都满足
		if (acceptAllInterfaces) {
			addIncludeFilter(new TypeFilter() {
				public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
						throws IOException {
					return true;
				}
			});
		}

		//排除package-info.java
		addExcludeFilter(new TypeFilter() {
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				String className = metadataReader.getClassMetadata().getClassName();
				return className.endsWith("package-info");
			}
		});
		
		//排除
		if (this.excludeAnnotationClass != null) {
			addExcludeFilter(new AnnotationTypeFilter(this.excludeAnnotationClass));
		}
	}

	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		if (ObjectUtils.isEmpty(basePackages) && StringUtils.isEmpty(basePackage)) {
			basePackages = StringUtils.tokenizeToStringArray(basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		}
		
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		if (beanDefinitions.isEmpty()) {
			logger.warn("No bean found in '" + Arrays.toString(basePackages)
			+ "' package. Please check your configuration.");
		}
		
		GenericBeanDefinition definition = null;
		for (BeanDefinitionHolder holder : beanDefinitions) {
			definition = (GenericBeanDefinition) holder.getBeanDefinition();
			logger.debug(holder.getBeanName() + ":" + definition.getBeanClassName()); 
			//System.out.println(holder.getBeanName() + ":" + definition.getBeanClassName());
		}

		return beanDefinitions;
	}

	/**
	 * 判断是否满足指定匹配规则
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return true;
	}

	/**
	 * 判断是否有重复
	 */
	@Override
	protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
		return super.checkCandidate(beanName, beanDefinition);
	}
	
	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public void setTargetType(Class<?> targetType) {
		this.targetType = targetType;
	}

	public void setClassPattern(String classPattern) {
		this.classPattern = classPattern;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public void setExcludeAnnotationClass(Class<? extends Annotation> excludeAnnotationClass) {
		this.excludeAnnotationClass = excludeAnnotationClass;
	}
}
