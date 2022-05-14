package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.MapUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.dao.ModuleDao;
import com.dev.doc.entity.Module;
import com.dev.doc.service.ModuleService;

/**
 * 
		* <p>Title: 模块相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class ModuleServiceImpl extends BaseMybatisServiceImpl<Module,Long,ModuleDao>
										implements ModuleService{
	@Override
	public List<Module> listByDocId(Long docId,String code,String name,String description,Pager pager) {
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		description = getLikeExpr(description);
		
		return getMybatisDao().listByDocId(docId,code,name,description,pager);
	}

	@Override
	public int countByDocId(Long docId,String code,String name,String description) {
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		return getMybatisDao().countByDocId(docId,code,name,description);
	}

	@Override
	public void updateByDocId(Module module) {
		getMybatisDao().updateByDocId(module);
	}

	@Override
	public void deleteByDocId(Long docId, Long moduleId) {
		getMybatisDao().deleteByDocId(docId, moduleId);
	}

	@Override
	public List<Module> listAllByDocId(Long docId) {
		return listByDocId(docId, null, null, null, null);
	}

	@Override
	public void buildSortWeight(Long docId, int minSortWeight, int maxSortWeight, int step) {
		getMybatisDao().buildSortWeight(docId, minSortWeight, maxSortWeight, step);
	}

	@Override
	public Module getByDocId(Long docId, Long moduleId) {
		return getMybatisDao().getByDocId(docId, moduleId);
	}

	@Override
	public void updateName(Long docId, Long moduleId, String name) {
		getMybatisDao().updateName(docId, moduleId, name);
	}

	@Override
	public List<Module> listByIdSet(Long docId, Set<Long> idSet) {
		if (CollectionUtils.isEmpty(idSet)) {
			return new ArrayList<Module>();
		}
		
		return getMybatisDao().listByIdSet(docId, idSet);
	}

	@Override
	public int getMaxSortWeight(Long docId) {
		return getMybatisDao().getMaxSortWeight(docId);
	}

	@Override
	public Module add(Module param) {
		param.setSortWeight(getMaxSortWeight(param.getDocId()) + 1);
		getMybatisDao().add(param);
		
		return param;
	}

	@Override
	public void updateSortWeight(Long docId, Long moduleId, int sortWeight) {
		getMybatisDao().updateSortWeight(docId, moduleId, sortWeight);
	}

	@Override
	public Map<Long, String> getByDocId(Long docId) {
		List<Module> list = listAllByDocId(docId);
		Map<Long, String> result = MapUtils.newMap();
		for (Module module : list) {
			result.put(module.getId(), module.getName());
		}
		
		return result;
	}

	@Override
	public List<SelectInfo> listModule(Long docId) {
		List<Module> list = listAllByDocId(docId);
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		SelectInfo info = null;
		for (Module module : list) {
			info = new SelectInfo();
			info.setCode("" + module.getId());
			info.setName(module.getName());
			result.add(info);
		}
		return result;
	}

	@Override
	public List<Module> listByViewId(Long viewId) {
		return getMybatisDao().listByViewId(viewId);
	}
}
