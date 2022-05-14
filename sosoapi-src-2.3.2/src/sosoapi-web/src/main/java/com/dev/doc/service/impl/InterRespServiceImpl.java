package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.doc.dao.InterRespDao;
import com.dev.doc.entity.InterResp;
import com.dev.doc.service.InterRespService;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: 接口响应相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class InterRespServiceImpl extends BaseMybatisServiceImpl<InterResp,Long,InterRespDao>
										implements InterRespService{

	@Override
	public List<InterResp> listAllByInterId(Long docId,Long interId) {
		return getMybatisDao().listAllByInterId(docId,interId);
	}

	@Override
	public void updateByDocId(InterResp interResp) {
		getMybatisDao().updateByDocId(interResp);
	}

	@Override
	public void deleteByDocId(Long docId, Long respId) {
		getMybatisDao().deleteByDocId(docId, respId);
	}

	@Override
	public List<InterResp> listAllByDocId(Long docId) {
		return getMybatisDao().listAllByDocId(docId);
	}

	@Override
	public InterResp getByDocId(Long docId, Long respId) {
		return getMybatisDao().getByDocId(docId, respId);
	}

	@Override
	public void deleteByDocIdAndInterId(Long docId, Long interId) {
		getMybatisDao().deleteByDocIdAndInterId(docId, interId);
	}

	@Override
	public InterResp getByInterIdAndCode(Long docId, Long interId, String code) {
		return getMybatisDao().getByInterIdAndCode(docId, interId, code);
	}

	@Override
	public void updateMockInfo(Long docId, Long respId, String mockData, String mockRule) {
		getMybatisDao().updateMockInfo(docId, respId, mockData, mockRule);
	}

	@Transactional
	@Override
	public void batchAdd(Long docId, Long interId, List<InterResp> list) {
		deleteByDocIdAndInterId(docId,interId);
		batchAdd(list);
	}

	@Override
	public List<InterResp> buildRespList(Long docId, Long interId, String reqResp) {
		if (StringUtils.isEmpty(reqResp)) {
			return new ArrayList<InterResp>();
		}
		
		List<InterResp> result = JsonUtils.toObject(reqResp, new TypeReference<List<InterResp>>(){});
		int sortWeight = 1;
		for (InterResp interResp : result) {
			interResp.setSortWeight(sortWeight ++ );
			interResp.setDocId(docId);
			interResp.setInterId(interId);
		}
		return result;
	}

	@Override
	public void updateSortWeight(Long docId, Long respId, int sortWeight) {
		getMybatisDao().updateSortWeight(docId, respId, sortWeight);
	}
}
