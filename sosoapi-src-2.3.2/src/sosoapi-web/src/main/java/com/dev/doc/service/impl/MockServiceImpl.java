package com.dev.doc.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.MockType;
import com.dev.base.enums.ReqMethod;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.util.MockUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterResp;
import com.dev.doc.service.InterRespService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.MockService;

/**
 * 
		* <p>Title: mock相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年10月25日下午3:58:06</p>
 */
@Service
public class MockServiceImpl implements MockService{
	@Autowired
	private InterService interService;

	@Autowired
	private InterRespService interRespService;
	
	@Override
	public String getMockData(Long docId, Long interId, String path, 
								ReqMethod method, String respCode,MockType mockType) {
		Long matchedInterId = getMatchedInterId(docId,interId, path, method);
		ValidateUtils.notNull(matchedInterId, ErrorCode.DOC_004);
		InterResp interResp = interRespService.getByInterIdAndCode(docId, matchedInterId, respCode);
		if (interResp == null) {
			return "";
		}
		
		String mockData = "";
		if (mockType != null) {//指定类型
			switch (mockType) {
				case data:
					mockData = interResp.getMockData();
					break;
					
				case rule:
					mockData = StringUtils.isEmpty(interResp.getMockRule()) ? "" : MockUtils.getMockData(interResp.getMockRule());
					break;
					
				default:
					break;
			}
		}
		else{
			mockData = StringUtils.isEmpty(interResp.getMockRule()) ? "" : MockUtils.getMockData(interResp.getMockRule());
			if (StringUtils.isEmpty(mockData)) {
				mockData = interResp.getMockData();
			}
		}
		
		return mockData;
	}
	
	@Override
	public String getStaticMockData(Long docId,Long interId, String path, ReqMethod method, String respCode) {
		return getMockData(docId, interId, path, method, respCode, MockType.data);
	}

	@Override
	public String getDynamicMockData(Long docId,Long interId,String path, ReqMethod method, String respCode) {
		return getMockData(docId, interId, path, method, respCode, MockType.rule);
	}
	
	//匹配对应的接口id
	private Long getMatchedInterId(Long docId,Long interId,String path,ReqMethod method){
		if (interId != null) {
			return interId;
		}
		
		List<Inter> interList = interService.listForMockByDocId(docId, method);
		Long matchInterId = null;
		for (Inter inter : interList) {
			if (!StringUtils.isEmpty(inter.getPathRegex()) 
					&& Pattern.matches(inter.getPathRegex(), path)) {
				matchInterId = inter.getId();
				break;
			}
		}
		
		return matchInterId;
	}

	@Override
	public String getMockUrl(Long docId, MockType mockType) {
		StringBuilder mockUrlBuilder = new StringBuilder("");
		if (docId == null) {
			return mockUrlBuilder.toString();
		}
		
		mockUrlBuilder.append(CfgConstants.WEB_BASE_URL);
		switch (mockType) {
			case auto:
				mockUrlBuilder.append("pass/mock/");
				break;
			
			case data:
				mockUrlBuilder.append("pass/mock/static/");
				break;
			
			case rule:
				mockUrlBuilder.append("pass/mock/dynamic/");
				break;
	
			default:
				break;
		}
		mockUrlBuilder.append(docId)
					  .append("/");
		
		return mockUrlBuilder.toString();
	}
}
