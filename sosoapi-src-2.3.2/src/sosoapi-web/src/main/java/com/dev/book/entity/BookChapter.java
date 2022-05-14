package com.dev.book.entity;

import com.dev.base.enums.BookChapterType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 电子书章节相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:23:05</p>
 */
public class BookChapter extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 书籍id */
	private Long bookId;		
	
	/** 标题 */
	private String title;		
	
	/** 排序权重 */
	private int sortWeight;		
	
	/** 类型 */
	private BookChapterType type;		
	
	/** 上级id */
	private Long parentId;		
	
	/** 内容id */
	private Long contentId;		
	
	/** 内容链接 */
	private String contentLink;		
	

	public void setBookId(Long bookId){
		this.bookId = bookId;
	}
	
	public Long getBookId(){
		return bookId;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setSortWeight(int sortWeight){
		this.sortWeight = sortWeight;
	}
	
	public int getSortWeight(){
		return sortWeight;
	}

	public BookChapterType getType() {
		return type;
	}

	public void setType(BookChapterType type) {
		this.type = type;
	}

	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return parentId;
	}
	
	public void setContentId(Long contentId){
		this.contentId = contentId;
	}
	
	public Long getContentId(){
		return contentId;
	}
	
	public void setContentLink(String contentLink){
		this.contentLink = contentLink;
	}
	
	public String getContentLink(){
		return contentLink;
	}
	
}
