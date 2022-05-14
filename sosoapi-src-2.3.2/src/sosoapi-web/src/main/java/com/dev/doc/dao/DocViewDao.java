package com.dev.doc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.DocView;

/**
 * 
		* <p>Title: api文档视图</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:23:10</p>
 */
public interface DocViewDao extends BaseMybatisDao<DocView,Long> {
	/**
	 * 
			*@name 查询文档视图信息
			*@Description  
			*@CreateDate 2017年7月20日下午5:19:10
	 */
	DocView getByDocId(@Param("viewId")Long viewId,@Param("docId")Long docId);
	
	/**
	 * 
			*@name 更新视图信息
			*@Description  
			*@CreateDate 2017年7月21日上午10:18:37
	 */
	void updateByDocId(DocView docView);
	
	/**
	 * 
			*@name 删除文档视图
			*@Description  
			*@CreateDate 2017年7月21日上午10:18:58
	 */
	void delByDocId(@Param("viewId")Long viewId,@Param("docId")Long docId);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档视图列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listByDocId(@Param("docId")Long docId,@Param("title")String title,
							@Param("description")String description,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档视图总数
			*@Description  
			*@CreateDate 2015年8月19日下午3:26:29
	 */
	int countByDocId(@Param("docId")Long docId,@Param("title")String title,
						@Param("description")String description);
}
