package com.dev.book.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.book.entity.Book;

/**
 * 
		* <p>Title: 书籍设置信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:54:58</p>
 */
public interface BookDao extends BaseMybatisDao<Book,Long> {
	/**
	 * 
			*@name 获取项目相关书籍
			*@Description  
			*@CreateDate 2017年12月5日下午4:24:17
	 */
	List<Book> listByProjId(@Param("projId")Long projId);
	
	/**
	 * 
			*@name 查询用户有权限的书籍列表
			*@Description  
			*@CreateDate 2017年12月6日下午1:52:00
	 */
	List<Map> listAuth(@Param("userId")Long userId);
}
