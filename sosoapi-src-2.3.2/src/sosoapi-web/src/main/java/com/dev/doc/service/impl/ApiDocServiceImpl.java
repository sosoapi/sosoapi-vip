package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.dao.ApiDocDao;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.vo.ApiDocInfo;

/**
 * 
		* <p>Title: Api文档相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:37:33</p>
 */
@Service
public class ApiDocServiceImpl extends BaseMybatisServiceImpl<ApiDoc,Long,ApiDocDao>
										implements ApiDocService{
	@Override
	public ApiDoc getByProjId(Long projId) {
		return getMybatisDao().getByProjId(projId);
	}

	@Override
	public void delByProjId(Long projId) {
		getMybatisDao().delByProjId(projId);
	}
	
	@Override
	public List<ApiDocInfo> listByUserId(Long userId, String title, Pager pager) {
		List<Map> list = getMybatisDao().listByUserId(userId, title, pager);
		List<ApiDocInfo> result = new ArrayList<ApiDocInfo>();
		ApiDocInfo apiDocInfo = null;
		for (Map info : list) {
			apiDocInfo = new ApiDocInfo();
			apiDocInfo.setProjId((Long)info.get("projId"));
			apiDocInfo.setProjCode((String)info.get("code"));
			apiDocInfo.setProjName((String)info.get("name"));
			apiDocInfo.setDocId((Long)info.get("docId"));
			apiDocInfo.setDocTitle((String)info.get("title"));
			apiDocInfo.setVersion((String)info.get("version"));
			apiDocInfo.setCreateDate((Date)info.get("createDate"));
			
			result.add(apiDocInfo);
		}
		
		return result;
	}

	@Override
	public int countByUserId(Long userId, String title) {
		return getMybatisDao().countByUserId(userId, title);
	}

	@Override
	public void updateShareInfo(ApiDoc apiDoc) {
		apiDoc.setShareUrl(encryDocId(apiDoc.getId()));
		getMybatisDao().updateShareInfo(apiDoc);
	}

	@Override
	public ApiDoc getShareInfo(Long docId) {
		ApiDoc apiDoc = getById(docId);
		if (StringUtils.isEmpty(apiDoc.getShareUrl())) {
			apiDoc.setShareUrl(encryDocId(docId));
		}
		
		return apiDoc;
	}
	
	@Override
	public String encryDocId(Long docId) {
		if (docId == null) {
			return null;
		}
		
		Map<String, Object> infoMap = MapUtils.newMap();
		infoMap.put("docId", docId);
		return CryptUtil.encryptAES(JsonUtils.toJson(infoMap),CfgConstants.SECURITY_SECRET_KEY);
	}

	@Override
	public Long decryptDocId(String idInfo) {
		if (StringUtils.isEmpty(idInfo)) {
			return null;
		}
		
		Map<String, Object> shareInfo = JsonUtils.toObject(CryptUtil.decryptAES(idInfo,CfgConstants.SECURITY_SECRET_KEY), HashMap.class);
		return FormatUtils.parseLong(shareInfo.get("docId"));
	}
}
