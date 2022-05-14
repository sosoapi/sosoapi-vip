package com.dev.book.service;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.book.entity.BookContent;

/**
 * 
		* <p>Title: 书籍内容相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:50:35</p>
 */
public interface BookContentService extends BaseMybatisService<BookContent, Long>{
	/**
	 * 
			*@name 获取章节详情
			*@Description  
			*@CreateDate 2017年11月28日下午6:15:11
	 */
	BookContent getByBookId(Long bookId,Long contentId);
	
	/**
	 * 
			*@name 更新章节详情
			*@Description  
			*@CreateDate 2017年11月28日下午6:15:52
	 */
	void updateByBookId(BookContent content);
	
	/**
	 * 
			*@name 获取html内容
			*@Description  
			*@CreateDate 2017年12月5日上午11:29:37
	 */
	String getHtmlContent(Long bookId,Long contentId);
}
