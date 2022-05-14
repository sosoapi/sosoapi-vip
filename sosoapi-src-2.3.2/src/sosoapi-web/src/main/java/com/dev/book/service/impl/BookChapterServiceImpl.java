package com.dev.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.enums.BookChapterType;
import com.dev.base.enums.BookContentType;
import com.dev.base.enums.NodeMoveType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.utils.FormatUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.book.dao.BookChapterDao;
import com.dev.book.entity.BookChapter;
import com.dev.book.entity.BookContent;
import com.dev.book.service.BookChapterService;
import com.dev.book.service.BookContentService;

/**
 * 
		* <p>Title: 电子书章节相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:23:05</p>
 */
@Service
public class BookChapterServiceImpl extends BaseMybatisServiceImpl<BookChapter,Long,BookChapterDao>
										implements BookChapterService{
	@Autowired
	private BookContentService bookContentService;
	
	@Override
	public List<BookChapter> listByBookId(Long bookId,Long parentId,String condition) {
		return getMybatisDao().listByBookId(bookId,parentId,condition);
	}

	@Override
	public List<TreeNodeInfo> listTree(Long bookId,Long parentId,String condition) {
		List<BookChapter> chapterList = listByBookId(bookId,parentId,condition);
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		for (BookChapter chapter : chapterList) {
			result.add(buildChapterNode(chapter));
		}
		
		return result;
	}
	
	//组装章节节点
	private TreeNodeInfo buildChapterNode(BookChapter chapter){
		boolean isFolder = chapter.getType() == BookChapterType.bookFolder;
		TreeNodeInfo nodeInfo = new TreeNodeInfo();
		nodeInfo.setId(buildNodeId(chapter.getId()));
		nodeInfo.setDataId(chapter.getId());
		if (chapter.getParentId() == null) {
			nodeInfo.setParentId(buildNodeId(TreeNodeInfo.ROOT_DATA_ID));
			nodeInfo.setParentDataId(TreeNodeInfo.ROOT_DATA_ID);
			nodeInfo.setParentType(BookChapterType.bookFolder.name());
		}
		else{
			nodeInfo.setParentId(buildNodeId(chapter.getParentId()));
			nodeInfo.setParentDataId(chapter.getParentId());
			nodeInfo.setParentType(BookChapterType.bookFolder.name());
		}
		nodeInfo.setName(chapter.getTitle());
		nodeInfo.setTitle(chapter.getTitle());
		nodeInfo.setOpen(false);
		nodeInfo.setIsParent(isFolder);
		nodeInfo.setDrag(true);
		if (!isFolder) {
			nodeInfo.setDropInner(false);
		}
		nodeInfo.setEnableAdd(isFolder);
		nodeInfo.setEnableDel(true);
		nodeInfo.setEnableEdit(true);
		nodeInfo.setType(chapter.getType().name());
		nodeInfo.setOtherData(chapter.getContentId());
		
		return nodeInfo;
	}
	
	//组装节点id
	private String buildNodeId(Long dataId){
		return dataId == null ? null : "" + dataId;
	}

	@Override
	public void buildSortWeight(Long bookId,Long parentId, int minSortWeight, int maxSortWeight, int step) {
		getMybatisDao().buildSortWeight(bookId,parentId, minSortWeight, maxSortWeight, step);
	}

	@Override
	public int getMaxSortWeight(Long bookId,Long parentId) {
		return getMybatisDao().getMaxSortWeight(bookId,parentId);
	}

	@Override
	public void updateSortWeight(Long bookId, Long chapterId, int sortWeight) {
		getMybatisDao().updateSortWeight(bookId, chapterId, sortWeight);
	}

	@Override
	public void sortTreeData(Long bookId, TreeNodeInfo srcNode, TreeNodeInfo targetNode, NodeMoveType moveType) {
		BookChapter srcChapter = getByBookId(bookId, srcNode.getDataId());
		BookChapter targetChapter = getByBookId(bookId,targetNode.getDataId());
		//源节点为空或目标节点为文件，则不允许移动
		if (srcChapter == null || 
				(srcChapter.getType() == BookChapterType.bookFolder 
					&& (targetChapter != null && targetChapter.getType() == BookChapterType.bookFile))) {
			return ;
		}
		
		switch (moveType){
			case inner:
				moveInner(bookId, srcChapter, targetChapter);
				break;
			
			case prev:
				movePrev(bookId, srcChapter, targetChapter);
				break;
				
			case next:
				moveNext(bookId, srcChapter, targetChapter);
				break;
			
			default:
				break;
		}
	}
	
	//移动到目标节点内部
	private void moveInner(Long bookId, BookChapter srcChapter, BookChapter targetChapter){
		Long targetId = targetChapter.getId();
		Long srcOldParentId = srcChapter.getParentId();
		int srcChapterOldSortWeight = srcChapter.getSortWeight();
		
		//更新源节点信息
		int srcSortWeight = getMaxSortWeight(bookId, targetId) + 1;
		srcChapter.setSortWeight(srcSortWeight);
		srcChapter.setParentId(targetId);
		update(srcChapter);
		
		//更新源节点原位置之后的节点排序权重全部-1
		buildSortWeight(bookId, srcOldParentId, srcChapterOldSortWeight + 1, Integer.MAX_VALUE, -1);
	}
	
	//移动到节点前面
	private void movePrev(Long bookId, BookChapter srcChapter, BookChapter targetChapter){
		Long srcParentId = srcChapter.getParentId();
		Long targetParentId = targetChapter.getParentId();
		int targetSortWeight = targetChapter.getSortWeight();
		if (FormatUtils.isEqual(srcParentId, targetParentId)) {//同一父节点下移动
			//目标节点及之后的节点权重全部+1
			buildSortWeight(bookId, targetParentId, targetSortWeight, Integer.MAX_VALUE, 1);
			//当前节点权重为目标节点权重
			updateSortWeight(bookId, srcChapter.getId(), targetSortWeight);
		}
		else {//不同父节点下移动
			Long srcOldParentId = srcChapter.getParentId();
			int srcChapterOldSortWeight = srcChapter.getSortWeight();
			
			//更新目标节点及之后的排序权重全部+1
			buildSortWeight(bookId, targetParentId, targetSortWeight, Integer.MAX_VALUE, 1);
			
			//更新源节点信息，源节点权重为目标节点权重，目标及之后节点权重全部+1
			srcChapter.setSortWeight(targetSortWeight);
			srcChapter.setParentId(targetParentId);
			update(srcChapter);
			
			//更新源节点原位置之后的节点排序权重全部-1
			buildSortWeight(bookId, srcOldParentId, srcChapterOldSortWeight + 1, Integer.MAX_VALUE, -1);
		}
	}
	
	//移动到节点后面
	private void moveNext(Long bookId, BookChapter srcChapter, BookChapter targetChapter){
		Long srcParentId = srcChapter.getParentId();
		Long targetParentId = targetChapter.getParentId();
		int targetSortWeight = targetChapter.getSortWeight();
		if (FormatUtils.isEqual(srcParentId, targetParentId)) {//同一父节点下移动
			//目标节点之后的节点权重全部+1
			buildSortWeight(bookId, targetParentId, targetSortWeight + 1, Integer.MAX_VALUE, 1);
			
			//当前节点权重为目标节点权重，目标节点之后的节点权重全部+1
			updateSortWeight(bookId, srcChapter.getId(), targetSortWeight + 1);
		}
		else {//不同父节点下移动
			Long srcOldParentId = srcChapter.getParentId();
			int srcChapterOldSortWeight = srcChapter.getSortWeight();
			
			//更新目标节点之后的排序权重全部+1
			buildSortWeight(bookId, targetParentId, targetSortWeight + 1, Integer.MAX_VALUE, 1);
			
			//更新源节点信息，源节点权重为目标节点权重，目标及之后节点权重全部+1
			srcChapter.setSortWeight(targetSortWeight + 1);
			srcChapter.setParentId(targetParentId);
			update(srcChapter);
			
			//更新源节点原位置之后的节点排序权重全部-1
			buildSortWeight(bookId, srcOldParentId, srcChapterOldSortWeight + 1, Integer.MAX_VALUE, -1);
		}
	}

	@Override
	public BookChapter getByBookId(Long bookId, Long chapterId) {
		return getMybatisDao().getByBookId(bookId, chapterId);
	}

	@Override
	public void updateParentId(Long bookId, Long oldParentId, Long newParentId) {
		getMybatisDao().updateParentId(bookId, oldParentId, newParentId);
	}

	@Override
	public void delByBookId(Long bookId, Long chapterId) {
		BookChapter chapter = getByBookId(bookId, chapterId);
		if (chapter == null) {
			return ;
		}
		
		updateParentId(bookId, chapter.getId(), 0-chapter.getId());
		getMybatisDao().delByBookId(bookId, chapterId);
	}

	@Override
	public void renameByBookId(Long bookId, Long chapterId, String title) {
		getMybatisDao().renameByBookId(bookId, chapterId, title);
	}

	@Transactional
	@Override
	public TreeNodeInfo addChapterNode(Long bookId, Long parentId, BookChapterType type, String title) {
		Long contentId = null;
		if (type == BookChapterType.bookFile) {
			BookContent bookContent = new BookContent();
			bookContent.setBookId(bookId);
			bookContent.setType(BookContentType.html);
			bookContentService.add(bookContent);
			contentId = bookContent.getId();
		}
		
		BookChapter bookChapter = new BookChapter();
		bookChapter.setBookId(bookId);
		bookChapter.setContentId(contentId);
		bookChapter.setParentId(parentId);
		bookChapter.setSortWeight(getMaxSortWeight(bookId, parentId) + 1);
		bookChapter.setTitle(title);
		bookChapter.setType(type);
		add(bookChapter);
		
		return buildChapterNode(bookChapter);
	}
}
