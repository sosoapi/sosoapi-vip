package com.dev.book.entity;

import com.dev.base.enums.BookContentType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 电子书章节内容相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:23:05</p>
 */
public class BookContent extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 书籍id */
	private Long bookId;		
	
	/** 类型 */
	private BookContentType type;		
	
	/** 富文本内容 */
	private String richTextContent;		
	
	/** markdown内容 */
	private String markdownContent;		
	
	/** html内容 */
	private String htmlContent;		
	

	public void setBookId(Long bookId){
		this.bookId = bookId;
	}
	
	public Long getBookId(){
		return bookId;
	}
	
	public BookContentType getType() {
		return type;
	}

	public void setType(BookContentType type) {
		this.type = type;
	}

	public void setRichTextContent(String richTextContent){
		this.richTextContent = richTextContent;
	}
	
	public String getRichTextContent(){
		return richTextContent;
	}
	
	public void setMarkdownContent(String markdownContent){
		this.markdownContent = markdownContent;
	}
	
	public String getMarkdownContent(){
		return markdownContent;
	}
	
	public void setHtmlContent(String htmlContent){
		this.htmlContent = htmlContent;
	}
	
	public String getHtmlContent(){
		return htmlContent;
	}
	
}
