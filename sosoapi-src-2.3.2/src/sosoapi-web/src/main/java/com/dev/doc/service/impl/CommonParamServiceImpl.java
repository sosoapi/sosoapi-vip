package com.dev.doc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.doc.dao.CommonParamDao;
import com.dev.doc.entity.InterParam;
import com.dev.doc.service.CommonParamService;

/**
 * 
		* <p>Title: 接口公共参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:02</p>
 */
@Service
public class CommonParamServiceImpl extends BaseMybatisServiceImpl<InterParam,Long,CommonParamDao>
										implements CommonParamService{

	@Override
	public List<InterParam> listByDocId(Long docId) {
		return getMybatisDao().listByDocId(docId);
	}

	@Override
	public void batchDelByDocId(Long docId) {
		getMybatisDao().batchDelByDocId(docId);
	}

	@Override
	@Transactional
	public void batchAdd(Long docId, List<InterParam> list) {
		batchDelByDocId(docId);
		batchAdd(list);
	}

	@Override
	public void cloneByDocId(Long srcDocId, Long cloneDocId) {
		getMybatisDao().cloneByDocId(srcDocId, cloneDocId);
	}
}
