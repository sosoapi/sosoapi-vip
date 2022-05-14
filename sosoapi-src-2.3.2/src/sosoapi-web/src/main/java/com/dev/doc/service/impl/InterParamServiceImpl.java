package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dev.base.enums.ParamPosition;
import com.dev.base.enums.SchemaType;
import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.doc.dao.InterParamDao;
import com.dev.doc.entity.InterParam;
import com.dev.doc.service.InterParamService;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: 接口参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:02</p>
 */
@Service
public class InterParamServiceImpl extends BaseMybatisServiceImpl<InterParam,Long,InterParamDao>
										implements InterParamService{

	@Override
	public List<InterParam> listAllByInterId(Long docId,Long interId) {
		return getMybatisDao().listAllByInterId(docId,interId);
	}

	@Override
	public void deleteByDocIdAndInterId(Long docId,Long interId) {
		getMybatisDao().deleteByDocIdAndInterId(docId,interId);
	}
	
	@Override
	@Transactional
	public void batchAdd(Long docId,Long interId,List<InterParam> list){
		deleteByDocIdAndInterId(docId,interId);
		batchAdd(list);
	}

	@Override
	public List<InterParam> listAllByDocId(Long docId) {
		return getMybatisDao().listAllByDocId(docId);
	}

	@Override
	public List<InterParam> buildParamList(Long docId, Long interId, String reqParam) {
		List<InterParam> result = new ArrayList<InterParam>();
		if (StringUtils.isEmpty(reqParam)) {
			return result;
		}
		
		List<Map<String,String>> paramList = JsonUtils.toObject(reqParam, new TypeReference<List<Map<String,String>>>(){});
		String code;
		String refSchemaIdStr;
		InterParam interParam = null;
		int sortWeight = 1;
		for (Map<String,String> paramMap : paramList) {
			code = (String)paramMap.get("code");
			
			if (StringUtils.isEmpty(code)) {//参数编码非空
				continue ;
			}
			
			interParam = new InterParam();
			interParam.setSortWeight(sortWeight ++);
			interParam.setCode(code);
			interParam.setName(paramMap.get("name"));
			interParam.setDefValue(paramMap.get("defValue"));
			interParam.setDescription(paramMap.get("description"));
			interParam.setInterId(interId);
			interParam.setDocId(docId);
			interParam.setPosition(ParamPosition.valueOf(paramMap.get("position")));
			interParam.setRequired(Boolean.parseBoolean(paramMap.get("required")));
			interParam.setType(SchemaType.valueOf(paramMap.get("type")));
			interParam.setExtSchema(paramMap.get("extSchema"));
			
			refSchemaIdStr = paramMap.get("refSchemaId");
			if (!StringUtils.isEmpty(refSchemaIdStr)) {
				interParam.setRefSchemaId(Long.parseLong(refSchemaIdStr));
			}
			
			result.add(interParam);
		}
		
		return result;
	}

	@Override
	public void updateSortWeight(Long docId, Long paramId, int sortWeight) {
		getMybatisDao().updateSortWeight(docId, paramId, sortWeight);
	}
}
