package com.dev.book.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.BookChapterType;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.NodeMoveType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.book.entity.Book;
import com.dev.book.service.BookChapterService;
import com.dev.book.service.BookService;

/**
 * 
		* <p>Title: 书籍相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/book/tree")
public class BookTreeController extends BaseController{
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookChapterService bookChapterService;
	
	/**
	 * 
			*@name 目录列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,Long projId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		//TO-DO 临时方案，没关联文档时自动创建1个
		List<Book> bookList = bookService.listByProjId(projId);
		Book book = null;
		if (CollectionUtils.isEmpty(bookList)) {
			book = new Book();
			book.setUserId(getUserId(request));
			book.setProjId(projId);
			book.setPubStatus(EnableStatus.off);
			book.setSortWeight(1);
			bookService.add(book);
		}
		else{
			book = bookList.get(0);
		}
		model.addAttribute("bookInfo",book);
		
		return "book/bookTree";
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
			*@name 节点排序
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/sort.htm")
	public @ResponseBody Map sortTreeData(Long bookId,String srcNodeInfo,String targetNodeInfo,NodeMoveType moveType){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		ValidateUtils.isTrue(!StringUtils.isEmpty(srcNodeInfo), ErrorCode.SYS_001,"源节点信息不能为空");
		ValidateUtils.isTrue(!StringUtils.isEmpty(targetNodeInfo), ErrorCode.SYS_001,"目标节点信息不能为空");
		
		TreeNodeInfo srcNode = JsonUtils.toObject(srcNodeInfo, TreeNodeInfo.class);
		TreeNodeInfo targetNode = JsonUtils.toObject(targetNodeInfo, TreeNodeInfo.class);
		
		bookChapterService.sortTreeData(bookId,srcNode, targetNode, moveType);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除节点
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(Long bookId,Long chapterId){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		ValidateUtils.notNull(chapterId, ErrorCode.SYS_001,"节点id不能为空");
		bookChapterService.delByBookId(bookId, chapterId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 重命名
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/rename.htm")
	public @ResponseBody Map rename(Long bookId,Long chapterId,String title){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		ValidateUtils.notNull(chapterId, ErrorCode.SYS_001,"节点id不能为空");
		ValidateUtils.notNull(title, ErrorCode.SYS_001,"标题不能为空");
		
		bookChapterService.renameByBookId(bookId, chapterId, title);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增目录
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/addChapter.htm")
	public @ResponseBody Map addChapter(Long bookId,Long parentId,BookChapterType type,String title){
		ValidateUtils.notNull(bookId, ErrorCode.SYS_001,"书籍id不能为空");
		ValidateUtils.notNull(type, ErrorCode.SYS_001,"节点类型不能为空");
		ValidateUtils.notNull(title, ErrorCode.SYS_001,"标题不能为空");
		
		TreeNodeInfo nodeInfo = bookChapterService.addChapterNode(bookId, parentId, type, title);
		return JsonUtils.createSuccess(nodeInfo);
	}
}
