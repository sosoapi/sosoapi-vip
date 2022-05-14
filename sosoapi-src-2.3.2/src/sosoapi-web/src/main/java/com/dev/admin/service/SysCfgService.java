package com.dev.admin.service;

import java.util.List;

import com.dev.admin.entity.SysCfg;
import com.dev.base.enums.SysCfgType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: 系统参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月17日上午10:30:27</p>
 */
public interface SysCfgService extends BaseMybatisService<SysCfg, Long>{
	/**
	 * 
			*@name 查询指定的编码是否存在
			*@Description  
			*@CreateDate 2016年5月21日下午10:12:19
	 */
	boolean isCodeExist(String code,Long exceptId);
	
	/**
	 * 
			*@name 查询树结构数据
			*@Description  
			*@CreateDate 2017年3月15日下午6:01:41
	 */
	List<TreeNodeInfo> loadTreeData(Long parentDataId,String condition);
	
	/**
	 * 
			*@name 查询树结构数据
			*@Description  
			*@CreateDate 2017年3月15日下午6:01:41
	 */
	List<TreeNodeInfo> search(String condition);
	
	/**
	 * 
			*@name 创建树节点
			*@Description  
			*@CreateDate 2017年7月17日下午2:33:58
	 */
	TreeNodeInfo buildTreeNodeInfo(SysCfg sysCfg);
	
	/**
	 * 
			*@name 获取分组下拉框列表
			*@Description  
			*@CreateDate 2017年5月23日上午10:18:54
	 */
	List<SelectInfo> listCate();
	
	/**
	 * 
			*@name 查询配置信息列表
			*@Description  
			*@CreateDate 2017年7月17日上午11:47:55
	 */
	List<SysCfg> listAll(SysCfgType type,Long parentId,String condition);
}
