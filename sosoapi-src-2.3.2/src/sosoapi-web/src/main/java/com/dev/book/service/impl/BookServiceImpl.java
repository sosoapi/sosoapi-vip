package com.dev.book.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.book.dao.BookDao;
import com.dev.book.entity.Book;
import com.dev.book.service.BookService;
import com.dev.book.vo.BookInfo;

/**
 * 
		* <p>Title: 电子书相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:23:05</p>
 */
@Service
public class BookServiceImpl extends BaseMybatisServiceImpl<Book,Long,BookDao>
										implements BookService{

	@Override
	public List<Book> listByProjId(Long projId) {
		return getMybatisDao().listByProjId(projId);
	}

	@Override
	public List<BookInfo> listAuth(Long userId) {
		List<Map> infoList = getMybatisDao().listAuth(userId);
		List<BookInfo> result = new ArrayList<BookInfo>();
		BookInfo bookInfo = null;
		for (Map info : infoList) {
			bookInfo = new BookInfo();
			bookInfo.setId((Long)info.get("bookId"));
			bookInfo.setProjId((Long)info.get("projId"));
			bookInfo.setRoleId((Long)info.get("projRoleId"));
			
			result.add(bookInfo);
		}
		
		return result;
	}
}
