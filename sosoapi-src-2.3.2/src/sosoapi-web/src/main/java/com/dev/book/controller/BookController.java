package com.dev.book.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.book.entity.Book;
import com.dev.book.entity.BookContent;
import com.dev.book.service.BookChapterService;
import com.dev.book.service.BookContentService;
import com.dev.book.service.BookService;

/**
 * 
		* <p>Title: 书籍相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/book/")
public class BookController extends BaseController{
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookContentService bookContentService;
	
	@Autowired
	private BookChapterService bookChapterService;
	
	/**
	 * 
			*@name 获取设置信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/setting/json/getInfo.htm")
	public @ResponseBody Map getSettingInfo(HttpServletRequest request,Long bookId){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		
		return JsonUtils.createSuccess(bookService.getById(bookId));
	}
	
	/**
	 * 
			*@name 更新设置信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/setting/json/update.htm")
	public @ResponseBody Map updateSettingInfo(HttpServletRequest request,Book book,Long bookId){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		book.setId(bookId);
		bookService.update(book);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取章节内容信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/content/json/getInfo.htm")
	public @ResponseBody Map getContentInfo(HttpServletRequest request,Long bookId,Long contentId){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"内容id不能为空");
		
		return JsonUtils.createSuccess(bookContentService.getByBookId(bookId, contentId));
	}
	
	/**
	 * 
			*@name 更新章节内容信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/content/json/update.htm")
	public @ResponseBody Map updateContentInfo(HttpServletRequest request,BookContent content,
					Long bookId,Long chapterId,Long contentId,String title){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		ValidateUtils.notNull(contentId, ErrorCode.SYS_001,"章节id不能为空");
		ValidateUtils.notNull(title, ErrorCode.SYS_001,"章节标题不能为空");
		
		content.setBookId(bookId);
		content.setId(contentId);
		bookContentService.updateByBookId(content);
		
		//更新章节标题
		bookChapterService.renameByBookId(bookId, chapterId, title);
		
		return JsonUtils.createSuccess();
	}
}
