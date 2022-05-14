package com.dev.book.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.book.entity.Book;
import com.dev.book.vo.BookInfo;

/**
 * 
		* <p>Title: 电子书相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:23:05</p>
 */
public interface BookService extends BaseMybatisService<Book, Long>{
	/**
	 * 
			*@name 获取项目相关书籍
			*@Description  
			*@CreateDate 2017年12月5日下午4:24:17
	 */
	List<Book> listByProjId(Long projId);
	
	/**
	 * 
			*@name 查询用户有权限的书籍列表
			*@Description  
			*@CreateDate 2017年12月6日下午1:52:00
	 */
	List<BookInfo> listAuth(Long userId);
}
