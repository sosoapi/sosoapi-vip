package com.dev.doc.service.impl;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.test.BaseTest;
import com.dev.base.utils.RegexUtil;
import com.dev.doc.dao.InterDao;
import com.dev.doc.entity.Inter;

public class InterServiceImplTest extends BaseTest{
	@Autowired
	private InterDao interDao;
	
	@Test
	public void initPathRegex() {
		List<Inter> interList = Collections.EMPTY_LIST;
		do {
			for (Inter inter : interList) {
				interDao.updateForPathRegex(inter.getId(), RegexUtil.parseReqUrlRegExpress(inter.getPath()));
			}
			interList = interDao.listForPathRegex(1000);
		} 
		while (!interList.isEmpty());
	}
}
