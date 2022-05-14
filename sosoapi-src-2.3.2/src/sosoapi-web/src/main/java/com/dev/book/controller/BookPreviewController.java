package com.dev.book.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.book.entity.Book;
import com.dev.book.service.BookChapterService;
import com.dev.book.service.BookContentService;
import com.dev.book.service.BookService;

/**
 * 
		* <p>Title: 书籍预览相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/book/preview")
public class BookPreviewController extends BaseController{
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookContentService bookContentService;
	
	@Autowired
	private BookChapterService bookChapterService;
	
	/**
	 * 
			*@name 进入预览首页
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/index.htm")
	public String index(HttpServletRequest request,Model model,Long bookId){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		Book book = bookService.getById(bookId);
		model.addAttribute("bookInfo",book);
		
		return "book/bookPreview";
	}
	
	/**
	 * 
			*@name 获取目录树结构
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/load.htm")
	public @ResponseBody List<TreeNodeInfo> loadTreeData(HttpServletRequest request,Long bookId,Long parentId,String condition){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		if (parentId == null) {
			parentId = TreeNodeInfo.ROOT_DATA_ID;
		}
		return bookChapterService.listTree(bookId,parentId,condition);
	}
	
	/**
	 * 
			*@name 获取章节详情
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/getContent.htm")
	public @ResponseBody Map getContent(HttpServletRequest request,Long bookId,Long contentId){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		ValidateUtils.notNull(contentId, ErrorCode.SYS_001,"内容id不能为空");
		
		return JsonUtils.createSuccess(bookContentService.getByBookId(bookId, contentId));
	}
}
