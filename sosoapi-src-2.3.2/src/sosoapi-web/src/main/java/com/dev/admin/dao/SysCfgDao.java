package com.dev.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.admin.entity.SysCfg;
import com.dev.base.enums.SysCfgType;
import com.dev.base.mybatis.dao.BaseMybatisDao;

/**
 * 
		* <p>Title: 系统参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月17日上午10:30:27</p>
 */
public interface SysCfgDao extends BaseMybatisDao<SysCfg,Long> {
	/**
	 * 
			*@name 查询指定编码参数项
			*@Description  
			*@CreateDate 2017年7月17日上午11:01:27
	 */
	int countByCode(@Param("code")String code,@Param("exceptId")Long exceptId);
	
	/**
	 * 
			*@name 查询配置信息列表
			*@Description  
			*@CreateDate 2017年7月17日上午11:47:55
	 */
	List<SysCfg> listAll(@Param("type")SysCfgType type,@Param("parentId")Long parentId,@Param("condition")String condition);
	
	/**
	 * 
			*@name 更新父节点id
			*@Description  
			*@CreateDate 2017年7月17日下午5:06:28
	 */
	void updateParentId(@Param("oldId")Long oldId,@Param("newId")Long newId);
}
