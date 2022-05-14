package com.dev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.admin.dao.SysCfgDao;
import com.dev.admin.entity.SysCfg;
import com.dev.admin.service.SysCfgService;
import com.dev.base.constant.AppConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.SysCfgType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: 系统参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月17日上午10:30:27</p>
 */
@Service
public class SysCfgServiceImpl extends BaseMybatisServiceImpl<SysCfg,Long,SysCfgDao>
										implements SysCfgService{
	/** 默认分组id*/
	private static Long CATE_DEF_ID = -1L;
	
	/** 默认分组名称*/
	private static String CATE_DEF_NAME = "默认分组";
	
	@Override
	public boolean isCodeExist(String code, Long exceptId) {
		return getMybatisDao().countByCode(code, exceptId) > 0;
	}

	@Override
	public SysCfg add(SysCfg sysCfg) {
		sysCfg.setStatus(EnableStatus.on);
		getMybatisDao().add(sysCfg);
		
		return sysCfg;
	}

	@Override
	public List<TreeNodeInfo> loadTreeData(Long parentDataId,String condition) {
		if (parentDataId == null) {
			parentDataId = AppConstants.TREE_ROOT_PARENT_ID;
		}
		
		List<SysCfg> cfgList = getMybatisDao().listAll(null,parentDataId, condition);
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		
		//加载根节点
		if (parentDataId == null) {
			result.add(buildDefCate());
		}
		for (SysCfg sysCfg : cfgList) {
			result.add(buildTreeNodeInfo(sysCfg));
		}
		
		return result;
	}
	
	@Override
	public List<TreeNodeInfo> search(String condition) {
		condition = getLikeExpr(condition);
		List<SysCfg> cfgList = getMybatisDao().listAll(SysCfgType.item,null, condition);
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		TreeNodeInfo treeNodeInfo = null;
		for (SysCfg sysCfg : cfgList) {
			treeNodeInfo = buildTreeNodeInfo(sysCfg);
			treeNodeInfo.setParentId(null);
			
			result.add(treeNodeInfo);
		}
		
		return result;
	}
	
	private String buildNodeId(Long dataId){
		return "cfg_" + dataId;
	}

	//创建默认分组
	private TreeNodeInfo buildDefCate(){
		TreeNodeInfo def = new TreeNodeInfo();
		def.setEnableAdd(false);
		def.setEnableDel(false);
		def.setEnableEdit(false);
		def.setId(buildNodeId(CATE_DEF_ID));
		def.setDataId(CATE_DEF_ID);
		def.setIsParent(true);
		def.setOpen(false);
		def.setName(CATE_DEF_NAME);
		def.setTitle(CATE_DEF_NAME);
		def.setDrag(false);
		def.setType(SysCfgType.cate.name());
		
		return def;
	}
		
	@Override
	public TreeNodeInfo buildTreeNodeInfo(SysCfg sysCfg) {
		Long parentId = sysCfg.getParentId();
		boolean isCate = sysCfg.getType() == SysCfgType.cate;
		
		TreeNodeInfo nodeInfo = new TreeNodeInfo();
		nodeInfo.setId(buildNodeId(sysCfg.getId()));
		nodeInfo.setDataId(sysCfg.getId());
		nodeInfo.setName(sysCfg.getName());
		nodeInfo.setTitle(sysCfg.getName());
		if (parentId != null) {
			nodeInfo.setParentDataId(parentId);
			nodeInfo.setParentId(buildNodeId(parentId));
			nodeInfo.setParentType(SysCfgType.cate.name());
		}
		nodeInfo.setOpen(false);
		nodeInfo.setType(sysCfg.getType().name());
		nodeInfo.setDrag(false);
		nodeInfo.setEnableDel(true);
		nodeInfo.setEnableEdit(true);
		nodeInfo.setEnableAdd(isCate);
		nodeInfo.setIsParent(isCate);
		
		return nodeInfo;
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		getMybatisDao().deleteById(id);
		getMybatisDao().updateParentId(id, CATE_DEF_ID);
	}

	@Override
	public List<SelectInfo> listCate() {
		List<SysCfg> cfgList = getMybatisDao().listAll(SysCfgType.cate, null, null);
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		SelectInfo info = null;
		for (SysCfg cfg : cfgList) {
			info = new SelectInfo();
			info.setCode("" + cfg.getId());
			info.setName(cfg.getName());
			result.add(info);
		}
		
		return result;
	}

	@Override
	public List<SysCfg> listAll(SysCfgType type, Long parentId, String condition) {
		return getMybatisDao().listAll(type, parentId, condition);
	}
}
