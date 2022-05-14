package com.dev.book.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.book.dao.BookContentDao;
import com.dev.book.entity.BookContent;
import com.dev.book.service.BookContentService;

/**
 * 
		* <p>Title: 电子书章节内容相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:23:05</p>
 */
@Service
public class BookContentServiceImpl extends BaseMybatisServiceImpl<BookContent,Long,BookContentDao>
										implements BookContentService{

	@Override
	public BookContent getByBookId(Long bookId, Long contentId) {
		return getMybatisDao().getByBookId(bookId, contentId);
	}

	@Override
	public void updateByBookId(BookContent content) {
		getMybatisDao().updateByBookId(content);
	}

	@Override
	public String getHtmlContent(Long bookId, Long contentId) {
		return getMybatisDao().getHtmlContent(bookId, contentId);
	}
}
