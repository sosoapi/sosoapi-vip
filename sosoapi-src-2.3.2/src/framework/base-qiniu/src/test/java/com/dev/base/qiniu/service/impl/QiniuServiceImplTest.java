package com.dev.base.qiniu.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;

public class QiniuServiceImplTest {
	private QiniuServiceImpl qiniuServiceImpl = new QiniuServiceImpl();
	
	@Test
	public void testUploadByte() throws Exception {
		qiniuServiceImpl.upload("dev", "this is demo".getBytes("UTF-8"), "123.txt");
		
	}
	
	@Test
	public void testUploadStream() throws Exception {
		qiniuServiceImpl.upload("dev", new FileInputStream("d:/test.png"), "img.png");
	}
	
	
	@Test
	public void testUploadSingle() {
		qiniuServiceImpl.upload("priv", "img/test.png", new File("d:/test.png"));
	}
	
	@Test
	public void testDel(){
		qiniuServiceImpl.del("priv-test", "img/test.png");
	}
	
	@Test
	public void testGetPrivDownUrl() {
		String imgUrl = "http://test.png";
		System.out.println(qiniuServiceImpl.getPrivDownUrl(imgUrl, 120));
	}
	
	@Test
	public void testUpload() {
		qiniuServiceImpl.upload("D:\\sysimg", "jpg,png", "dev", "sys_head_");
		System.out.println("upload success");
	}

	@Test
	public void testBatchDel() {
		qiniuServiceImpl.batchDel("test", "sysimg/");
		System.out.println("batchDel success");
	}

	@Test
	public void testList() {
		List<String> keyList = qiniuServiceImpl.list("test", "sysimg");
		if (keyList.isEmpty()) {
			System.out.println("no data");
		}
		
		System.out.println("total:" + keyList.size());
		for (String key : keyList) {
			System.out.println(key);
		}
	}

}
