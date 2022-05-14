package org.mybatis.spring.typealias;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

public class ClassPathTypeAliasScannerTest {

	@Test
	public void testDoScan() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		ClassPathBeanScanner scanner = new ClassPathBeanScanner(factory);
//		scanner.setAnnotationClass(Service.class);
//		scanner.setTargetType(BaseMybatisEntity.class);
//		scanner.setClassPattern("com\\.dev\\.demo\\.dao\\.DemoDao");
	    scanner.registerFilters();
	    scanner.scan(StringUtils.tokenizeToStringArray("com.dev", ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

}
