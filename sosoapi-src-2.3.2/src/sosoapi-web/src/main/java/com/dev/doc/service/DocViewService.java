package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.doc.entity.DocView;
import com.dev.doc.vo.DocViewInfo;

/**
 * 
		* <p>Title: api文档视图</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:19:12</p>
 */
public interface DocViewService extends BaseMybatisService<DocView, Long>{
	/**
	 * 
			*@name 查询文档视图信息
			*@Description  
			*@CreateDate 2017年7月20日下午5:19:10
	 */
	DocView getByDocId(Long viewId,Long docId);
	
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
	void delByDocId(Long viewId,Long docId);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档视图列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<DocViewInfo> listByDocId(Long docId,String title,
							String description,Pager pager);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档视图总数
			*@Description  
			*@CreateDate 2015年8月19日下午3:26:29
	 */
	int countByDocId(Long docId,String title,String description);
	
	/**
	 * 
			*@name 加密分享信息
			*@Description  
			*@CreateDate 2017年7月21日下午2:45:08
	 */
	String encryShareInfo(Long viewId,Long docId);
	
	/**
	 * 
			*@name 解密分享信息
			*@Description  
			*@CreateDate 2017年7月24日下午4:42:45
	 */
	DocView decryptShareInfo(String shareKey);
}
