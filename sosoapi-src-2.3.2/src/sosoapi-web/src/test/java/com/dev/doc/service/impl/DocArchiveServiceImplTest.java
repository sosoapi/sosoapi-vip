package com.dev.doc.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.DocArchiveType;
import com.dev.base.qiniu.service.QiniuService;
import com.dev.base.test.BaseTest;
import com.dev.doc.entity.DocArchive;
import com.dev.doc.service.DocArchiveService;

public class DocArchiveServiceImplTest extends BaseTest{
	@Autowired
	private DocArchiveService docArchiveService;
	
	@Autowired
	private QiniuService qiniuService;
	
	@Test
	public void testAddDocArchive() {
		DocArchive docArchive = new DocArchive();
		docArchive.setDocId(1L);
		docArchive.setProjId(1L);
		docArchive.setTitle("test");
		docArchive.setUserId(14L);
		
		docArchiveService.add(docArchive);
	}

	@Test
	public void testBuildArchiveLocal() {
		String url = docArchiveService.buildArchive(1L, "归档测试", DocArchiveType.local);
		if (!StringUtils.isEmpty(url)) {
			System.out.println(url);
		}
		else {
			System.out.println("error");
		}
	}
	
	@Test
	public void testBuildArchiveRemote() {
		String url = docArchiveService.buildArchive(1L, "归档测试1", DocArchiveType.remote);
		if (!StringUtils.isEmpty(url)) {
			System.out.println(url);
			String remoteUrl = CfgConstants.DOC_ARCHIVE_REMOTE_BASE_URL + url;
			System.out.println(remoteUrl);
			String avaliableUrl = qiniuService.getPrivDownUrl(remoteUrl, 60);
			System.out.println(avaliableUrl);
		}
		else {
			System.out.println("error");
		}
	}
	
	@Test
	public void testLoadArchive(){
		String content = docArchiveService.loadArchive(1L, 7L);
		System.out.println(content);
	}
}
