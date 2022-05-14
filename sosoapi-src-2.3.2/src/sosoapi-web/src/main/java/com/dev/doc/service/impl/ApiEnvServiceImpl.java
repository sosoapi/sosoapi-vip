package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.base.enums.EnableStatus;
import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.doc.dao.ApiEnvDao;
import com.dev.doc.entity.ApiEnv;
import com.dev.doc.service.ApiEnvService;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: api接口环境</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月13日下午5:44:56</p>
 */
@Service
public class ApiEnvServiceImpl extends BaseMybatisServiceImpl<ApiEnv,Long,ApiEnvDao>
										implements ApiEnvService{

	@Override
	public List<ApiEnv> listByDocId(Long docId,EnableStatus status) {
		return getMybatisDao().listByDocId(docId,status);
	}

	@Override
	public void batchDelByDocId(Long docId) {
		getMybatisDao().batchDelByDocId(docId);
	}

	@Override
	public void batchAdd(Long docId, List<ApiEnv> envList) {
		batchDelByDocId(docId);
		batchAdd(envList);
	}

	@Override
	public List<ApiEnv> buildEnvList(Long docId, String envParam) {
		List<ApiEnv> result = new ArrayList<ApiEnv>();
		if (StringUtils.isEmpty(envParam)) {
			return result;
		}
		
		List<Map<String,String>> envList = JsonUtils.toObject(envParam, new TypeReference<List<Map<String,String>>>(){});
		ApiEnv env = null;
		int sort = 1;
		String name = "";
		for (Map<String, String> info : envList) {
			name = (String)info.get("name");
			if(StringUtils.isEmpty(name)){
				continue;
			}
			
			env = new ApiEnv();
			env.setDocId(docId);
			env.setName(name);
			env.setBaseUrl((String)info.get("baseUrl"));
			env.setStatus(EnableStatus.valueOf((String)info.get("status")));
			env.setDescription((String)info.get("description"));
			env.setSortWeight(sort ++);
			env.setDef(false);
			result.add(env);
		}
		
		return result;
	}

	@Override
	public void cloneByDocId(Long srcDocId, Long cloneDocId) {
		getMybatisDao().cloneByDocId(srcDocId, cloneDocId);
	}
}
