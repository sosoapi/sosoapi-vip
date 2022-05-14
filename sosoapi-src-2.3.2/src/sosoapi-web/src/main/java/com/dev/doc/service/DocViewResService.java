package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.doc.entity.DocViewRes;

/**
 * 
		* <p>Title: api文档视图资源</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:23:10</p>
 */
public interface DocViewResService extends BaseMybatisService<DocViewRes, Long>{
	/**
	 * 
			*@name 加载资源树信息
			*@Description  
			*@CreateDate 2017年7月21日下午4:05:31
	 */
	List<TreeNodeInfo> loadTreeData(Long docId,Long viewId);
	
	/**
	 * 
			*@name 删除全部资源
			*@Description  
			*@CreateDate 2017年7月3日下午2:42:07
	 */
	void batchDel(Long viewId,Long docId);
	
	/**
	 * 
			*@name 更新视图资源
			*@Description  
			*@CreateDate 2017年7月24日下午2:25:10
	 */
	void updateViewRes(Long viewId,Long docId,List<DocViewRes> resList);
	
	/**
	 * 
			*@name 查询视图相关所有资源
			*@Description  
			*@CreateDate 2017年7月24日下午3:20:37
	 */
	List<DocViewRes> listAll(Long viewId,Long docId);
}
