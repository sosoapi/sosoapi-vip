package com.dev.book.service;

import java.util.List;

import com.dev.base.enums.BookChapterType;
import com.dev.base.enums.NodeMoveType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.book.entity.BookChapter;

/**
 * 
		* <p>Title: 电子书章节相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:23:05</p>
 */
public interface BookChapterService extends BaseMybatisService<BookChapter, Long>{
	/**
	 * 
			*@name 查询指定书籍所有目录
			*@Description  
			*@CreateDate 2017年11月23日下午5:38:35
	 */
	List<BookChapter> listByBookId(Long bookId,Long parentId,String condition);
	
	/**
	 * 
			*@name 查询目录树
			*@Description  
			*@CreateDate 2017年11月23日下午5:41:11
	 */
	List<TreeNodeInfo> listTree(Long bookId,Long parentId,String condition);
	
	/**
	 * 
			*@name 指定权重之间全部加上指定步长
			*@Description  
			*@CreateDate 2017年3月23日下午5:09:54
	 */
	void buildSortWeight(Long bookId,Long parentId,int minSortWeight,int maxSortWeight,int step);
	
	/**
	 * 
			*@name 获取最大的排序权重
			*@Description  
			*@CreateDate 2017年3月30日下午3:35:26
	 */
	int getMaxSortWeight(Long bookId,Long parentId);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(Long bookId,Long chapterId,int sortWeight);
	
	/**
	 * 
			*@name 拖拽排序
			*@Description  
			*@CreateDate 2017年11月24日上午10:58:05
	 */
	void sortTreeData(Long bookId,TreeNodeInfo srcNode,TreeNodeInfo targetNode,NodeMoveType moveType);
	
	/**
	 * 
			*@name 获取章节信息
			*@Description  
			*@CreateDate 2017年11月24日上午11:03:35
	 */
	BookChapter getByBookId(Long bookId,Long chapterId);
	
	/**
	 * 
			*@name 重置父章节
			*@Description  
			*@CreateDate 2017年11月24日下午5:06:18
	 */
	void updateParentId(Long bookId,Long oldParentId,Long newParentId);
	
	/**
	 * 
			*@name 删除章节
			*@Description  
			*@CreateDate 2017年11月24日下午5:02:18
	 */
	void delByBookId(Long bookId,Long chapterId);
	
	/**
	 * 
			*@name 重命名章节标题
			*@Description  
			*@CreateDate 2017年11月24日下午5:02:18
	 */
	void renameByBookId(Long bookId,Long chapterId,String title);
	
	
	/**
	 * 
			*@name 创建章节节点
			*@Description  
			*@CreateDate 2017年11月25日上午10:10:35
	 */
	TreeNodeInfo addChapterNode(Long bookId,Long parentId,BookChapterType type,String title);
}
