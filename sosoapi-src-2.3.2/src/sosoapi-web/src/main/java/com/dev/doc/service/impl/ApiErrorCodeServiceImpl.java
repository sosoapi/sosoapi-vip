package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.dao.ApiErrorCodeDao;
import com.dev.doc.entity.ApiErrorCode;
import com.dev.doc.service.ApiErrorCodeService;
import com.dev.doc.vo.ApiErrorCodeInfo;

/**
 * 
		* <p>Title: api错误码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月16日上午8:52:36</p>
 */
@Service
public class ApiErrorCodeServiceImpl extends BaseMybatisServiceImpl<ApiErrorCode,Long,ApiErrorCodeDao>
										implements ApiErrorCodeService{

	@Override
	public ApiErrorCode add(ApiErrorCode param) {
		param.setSortWeight(getMaxSortWeight(param.getDocId(),param.getModuleId()) + 1);
		getMybatisDao().add(param);
		
		return param;
	}
	
	@Override
	public List<ApiErrorCode> listByDocId(Long docId, Long moduleId,String condition) {
		condition = getLikeExpr(condition);
		return getMybatisDao().listByDocId(docId, moduleId, condition);
	}

	@Override
	public void updateByDocId(ApiErrorCode apiErrorCode) {
		getMybatisDao().updateByDocId(apiErrorCode);
	}

	@Override
	public ApiErrorCode getByDocId(Long docId, Long codeId) {
		return getMybatisDao().getByDocId(docId, codeId);
	}

	@Override
	public void deleteByDocId(Long docId, Long codeId) {
		getMybatisDao().deleteByDocId(docId, codeId);
	}

	@Override
	public void buildSortWeight(Long docId, Long moduleId, int minSortWeight,
			int maxSortWeight, int step) {
		getMybatisDao().buildSortWeight(docId, moduleId, minSortWeight, maxSortWeight, step);
	}

	@Override
	public int getMaxSortWeight(Long docId, Long moduleId) {
		return getMybatisDao().getMaxSortWeight(docId, moduleId);
	}

	@Override
	public void updateSortWeight(Long docId, Long codeId, int sortWeight) {
		getMybatisDao().updateSortWeight(docId, codeId, sortWeight);
	}

	@Override
	public List<ApiErrorCodeInfo> listInfoByDocId(Long docId, Long moduleId,String condition) {
		List<ApiErrorCode> list = listByDocId(docId, moduleId, condition);
		ApiErrorCodeInfo info = null;
		List<ApiErrorCodeInfo> result = new ArrayList<ApiErrorCodeInfo>();
		for (ApiErrorCode errorCode : list) {
			info = new ApiErrorCodeInfo();
			info.setCode(errorCode.getCode());
			info.setMsg(errorCode.getMsg());
			info.setDescription(errorCode.getDescription());
			
			result.add(info);
		}
		
		return result;
	}

	@Override
	public List<ApiErrorCodeInfo> listInfoByViewId(Long viewId) {
		List<Map> codeList = getMybatisDao().listInfoByViewId(viewId);
		ApiErrorCodeInfo info = null;
		List<ApiErrorCodeInfo> result = new ArrayList<ApiErrorCodeInfo>();
		for (Map code : codeList) {
			info = new ApiErrorCodeInfo();
			info.setId((Long)code.get("id"));
			info.setCode((String)code.get("code"));
			info.setMsg((String)code.get("msg"));
			info.setDescription((String)code.get("description"));
			
			result.add(info);
		}
		return result;
	}

	@Override
	public Map<Long, Integer> countByModule(Long docId) {
		List<Map> list = getMybatisDao().countByModule(docId);
		Map<Long, Integer> result = MapUtils.newMap();
		for (Map map : list) {
			result.put(FormatUtils.parseLong(map.get("moduleId")), FormatUtils.parseInt(map.get("totalCount")));
		}
		
		return result;
	}

	@Override
	public int countByModuleId(Long docId, Long moduleId) {
		return getMybatisDao().countByModuleId(docId, moduleId);
	}

	@Override
	public List<ApiErrorCode> listByModuleId(Long docId, Long moduleId, Pager pager) {
		return getMybatisDao().listByModuleId(docId, moduleId, pager);
	}
}
