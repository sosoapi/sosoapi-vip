package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.dao.DocViewDao;
import com.dev.doc.entity.DocView;
import com.dev.doc.service.DocViewResService;
import com.dev.doc.service.DocViewService;
import com.dev.doc.vo.DocViewInfo;

/**
 * 
		* <p>Title: api文档视图</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:19:12</p>
 */
@Service
public class DocViewServiceImpl extends BaseMybatisServiceImpl<DocView,Long,DocViewDao>
										implements DocViewService{
	@Autowired
	private DocViewResService docViewResService;
	
	@Override
	public DocView add(DocView docView) {
		getMybatisDao().add(docView);
		
		//设置分享url
		Long viewId = docView.getId();
		docView.setShareUrl(encryShareInfo(viewId, docView.getDocId()));
		update(docView);
		return docView;
	}

	@Override
	public DocView getByDocId(Long viewId, Long docId) {
		return getMybatisDao().getByDocId(viewId, docId);
	}

	@Override
	public void updateByDocId(DocView docView) {
		getMybatisDao().updateByDocId(docView);
	}

	@Transactional
	@Override
	public void delByDocId(Long viewId, Long docId) {
		getMybatisDao().delByDocId(viewId, docId);
		docViewResService.batchDel(viewId, docId);
	}

	@Override
	public List<DocViewInfo> listByDocId(Long docId, String title, String description, Pager pager) {
		title = getLikeExpr(title);
		description = getLikeExpr(description);
		List<Map> infoList = getMybatisDao().listByDocId(docId, title, description, pager);
		List<DocViewInfo> result = new ArrayList<DocViewInfo>();
		DocViewInfo viewInfo = null;
		for (Map info : infoList) {
			viewInfo = new DocViewInfo();
			viewInfo.setId((Long)info.get("id"));
			viewInfo.setProjId((Long)info.get("projId"));
			viewInfo.setProjName((String)info.get("projName"));
			viewInfo.setProjCode((String)info.get("projCode"));
			viewInfo.setDocId((Long)info.get("docId"));
			viewInfo.setTitle((String)info.get("title"));
			viewInfo.setCreateDate((Date)info.get("createDate"));
			viewInfo.setShare((Integer)info.get("share") == 1);
			
			result.add(viewInfo);
		}
		
		return result;
	}

	@Override
	public int countByDocId(Long docId, String title, String description) {
		title = getLikeExpr(title);
		description = getLikeExpr(description);
		
		return getMybatisDao().countByDocId(docId, title, description);
	}
	
	@Override
	public String encryShareInfo(Long viewId,Long docId) {
		if (docId == null || viewId == null) {
			return null;
		}
		
		Map<String, Object> infoMap = MapUtils.newMap();
		infoMap.put("docId", docId);
		infoMap.put("viewId", viewId);
		return CryptUtil.encryptAES(JsonUtils.toJson(infoMap),CfgConstants.SECURITY_SECRET_KEY);
	}

	@Override
	public DocView decryptShareInfo(String shareKey) {
		if (StringUtils.isEmpty(shareKey)) {
			return null;
		}
		
		Map<String, Object> infoMap = JsonUtils.toObject(CryptUtil.decryptAES(shareKey,CfgConstants.SECURITY_SECRET_KEY), HashMap.class);
		DocView docView = new DocView();
		docView.setDocId(FormatUtils.parseLong(infoMap.get("docId")));
		docView.setId(FormatUtils.parseLong(infoMap.get("viewId")));
		return docView;
	}
}
