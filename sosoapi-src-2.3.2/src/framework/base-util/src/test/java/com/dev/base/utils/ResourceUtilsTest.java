package com.dev.base.utils;

import org.junit.Test;
import org.springframework.core.io.Resource;

public class ResourceUtilsTest {

	@Test
	public void testLoad() throws Exception{
		Resource[] resources = ResourceUtils.load("file:F:/DevWorkspace/api/framework/base-util/src/main/java/com/dev/base/utils/*.java");
		for(Resource r : resources){
	        System.out.println("URL:" + r.getURL());
	    }
	}

	@Test
	public void testLoadSingle() throws Exception {
		Resource resource = ResourceUtils.loadSingle("classpath:SensitiveWord.txt");
		if (resource != null) {
			System.out.println(resource.getURL());
		}
		else{
			System.out.println("not exist");
		}
	}
}
