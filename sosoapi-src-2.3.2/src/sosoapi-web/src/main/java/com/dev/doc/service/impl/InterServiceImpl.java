package com.dev.doc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dev.base.enums.DevStatus;
import com.dev.base.enums.ReqMethod;
import com.dev.base.enums.ReqScheme;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.dao.InterDao;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.service.InterParamService;
import com.dev.doc.service.InterRespService;
import com.dev.doc.service.InterService;
import com.dev.doc.vo.InterInfo;

/**
 * 
		* <p>Title: 接口相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class InterServiceImpl extends BaseMybatisServiceImpl<Inter,Long,InterDao>
										implements InterService{
	@Autowired
	private InterParamService interParamService;
	
	@Autowired
	private InterRespService interRespService;
	
	@Override
	public List<Inter> listByDocId(Long docId, Long moduleId,String name,String path,String description,Pager pager) {
		name = getLikeExpr(name);
		path = getLikeExpr(path);
		description = getLikeExpr(description);
		
		return getMybatisDao().listByDocId(docId, moduleId,name,path,description, pager);
	}

	@Override
	public int countByDocId(Long docId, Long moduleId,String name,String path,String description) {
		name = getLikeExpr(name);
		path = getLikeExpr(path);
		description = getLikeExpr(description);
		
		return getMybatisDao().countByDocId(docId, moduleId,name,path,description);
	}

	@Override
	public InterInfo getInfoByDocId(Long docId,Long interId) {
		Map detailMap = getMybatisDao().getInfoByDocId(docId,interId);
		
		InterInfo result = new InterInfo();
		if (CollectionUtils.isEmpty(detailMap)) {
			return result;
		}
		
		result.setConsume((String)detailMap.get("consume"));
		result.setDeprecated((Integer)detailMap.get("deprecated") == 1);
		result.setSkipCommonParam((Integer)detailMap.get("skipCommonParam") == 1);
		result.setDescription((String)detailMap.get("description"));
		result.setInterId((Long)detailMap.get("id"));
		result.setMethod(ReqMethod.valueOf((String)detailMap.get("method")));
		result.setModuleId((Long)detailMap.get("moduleId"));
		result.setModuleName((String)detailMap.get("moduleName"));
		result.setName((String)detailMap.get("name"));
		result.setPath((String)detailMap.get("path"));
		result.setProduce((String)detailMap.get("produce"));
		result.setScheme(ReqScheme.valueOf((String)detailMap.get("scheme")));
		result.setSummary((String)detailMap.get("summary"));
		result.setSortWeight(((Number)detailMap.get("sortWeight")).intValue());
		result.setDeveloper((String)detailMap.get("developer"));
		result.setDevStatus(DevStatus.valueOf((String)detailMap.get("devStatus")));
		result.setLabel((String)detailMap.get("label"));
		result.setOperationId((String)detailMap.get("operationId"));
		
		return result;
	}

	@Override
	public List<Inter> listAllByDocId(Long docId,Long moduleId,Boolean deprecated,String condition) {
		condition = getLikeExpr(condition);
		return getMybatisDao().listAllByDocId(docId,moduleId,deprecated,condition);
	}

	@Override
	public Inter add(Inter inter){
		validPathAndMethod(inter,true);

		inter.setDelFlag(false);
		inter.setSortWeight(getMaxSortWeight(inter.getDocId(), inter.getModuleId()) + 1);
		inter.setPathRegex(RegexUtil.parseReqUrlRegExpress(inter.getPath()));
		getMybatisDao().add(inter);
		return inter;
	}
	
	@Override
	public void updateByDocId(Inter inter) {
		validPathAndMethod(inter,false);
		
		inter.setPathRegex(RegexUtil.parseReqUrlRegExpress(inter.getPath()));
		getMybatisDao().updateByDocId(inter);
	}

	@Override
	public Inter update(Inter inter){
		validPathAndMethod(inter,false);
		
		inter.setPathRegex(RegexUtil.parseReqUrlRegExpress(inter.getPath()));
		getMybatisDao().update(inter);
		return inter;
	}
	
	@Override
	public void deleteByDocId(Long docId, Long interId) {
		getMybatisDao().deleteByDocId(docId, interId);
		
		//删除关联的请求参数
		interParamService.deleteByDocIdAndInterId(docId, interId);
		
		//删除关联的请求响应
		interRespService.deleteByDocIdAndInterId(docId, interId);
	}

	//同一个文档下的请求url和请求方式两者唯一确定一个接口
	private void validPathAndMethod(Inter inter,boolean isAdd){
		Inter temp = getMybatisDao().getByPathAndMethod(inter.getDocId(), inter.getPath(), inter.getMethod());
		if (isAdd) {//新增
			ValidateUtils.isTrue(temp == null, ErrorCode.DOC_001);
		}
		else if(temp != null){//编辑
			ValidateUtils.isTrue(FormatUtils.isEqual(inter.getId(), temp.getId()), ErrorCode.DOC_001);
		}
	}

	@Override
	public Inter getByDocId(Long docId, Long interId) {
		return getMybatisDao().getByDocId(docId, interId);
	}

	@Override
	public Inter getByPathAndMethod(Long docId, String path, ReqMethod method) {
		return getMybatisDao().getByPathAndMethod(docId, path, method);
	}

	@Override
	public List<Inter> listForMockByDocId(Long docId,ReqMethod method) {
		return getMybatisDao().listForMockByDocId(docId,method);
	}

	@Override
	public void buildSortWeight(Long docId, Long moduleId, int minSortWeight, int maxSortWeight, int step) {
		getMybatisDao().buildSortWeight(docId, moduleId, minSortWeight, maxSortWeight, step);
	}

	@Override
	public int getMaxSortWeight(Long docId, Long moduleId) {
		return getMybatisDao().getMaxSortWeight(docId, moduleId);
	}

	@Override
	public void updateName(Long docId, Long interId, String name) {
		getMybatisDao().updateName(docId, interId, name);
	}

	@Override
	public void updateForDelModule(Long docId, Long moduleId) {
		getMybatisDao().updateForDelModule(docId, moduleId);
	}

	@Transactional
	@Override
	public Inter addDetailByDocId(Long docId, InterInfo interInfo) {
		//处理接口信息
		Inter inter = parseInter(docId, null, interInfo);
		add(inter);
		
		Long interId = inter.getId();
		
		//处理接口请求
		List<InterParam> reqParamList = interParamService.buildParamList(docId, interId, interInfo.getReqParam());
		interParamService.batchAdd(docId,interId, reqParamList);
		
		//处理接口响应
		List<InterResp> reqRespList = interRespService.buildRespList(docId, interId, interInfo.getReqResp());
		interRespService.batchAdd(reqRespList);
		
		return inter;
	}
	
	//组装接口信息
	private Inter parseInter(Long docId,Long interId,InterInfo interInfo){
		Inter inter = new Inter();
		inter.setDocId(docId);
		inter.setId(interId);
		inter.setModuleId(interInfo.getModuleId());
		inter.setName(interInfo.getName());
		inter.setPath(interInfo.getPath());
		inter.setMethod(interInfo.getMethod());
		inter.setScheme(interInfo.getScheme());
		inter.setSummary(interInfo.getSummary());
		inter.setDescription(interInfo.getDescription());
		inter.setConsume(interInfo.getConsume());
		inter.setProduce(interInfo.getProduce());
		inter.setDeprecated(interInfo.isDeprecated());
		inter.setDeveloper(interInfo.getDeveloper());
		inter.setDevStatus(interInfo.getDevStatus());
		inter.setLabel(interInfo.getLabel());
		inter.setOperationId(interInfo.getOperationId());
		inter.setSkipCommonParam(interInfo.isSkipCommonParam());
		
		return inter;
	}

	@Transactional
	@Override
	public void updateDetailByDocId(Long docId, Long interId, InterInfo interInfo) {
		Inter old = getByDocId(docId, interId);
		ValidateUtils.notNull(old, ErrorCode.SYS_001,"接口信息不存在");
		
		//处理接口信息
		Inter inter = parseInter(docId, interId, interInfo);
		inter.setSortWeight(old.getSortWeight());
		updateByDocId(inter);
		
		//处理接口请求
		List<InterParam> reqParamList = interParamService.buildParamList(docId, interId, interInfo.getReqParam());
		interParamService.batchAdd(docId,interId, reqParamList);
		
		//处理接口响应
		List<InterResp> reqRespList = interRespService.buildRespList(docId, interId, interInfo.getReqResp());
		interRespService.batchAdd(docId, interId, reqRespList);
	}

	@Override
	public void updateSortWeight(Long docId, Long interId, int sortWeight) {
		getMybatisDao().updateSortWeight(docId, interId, sortWeight);
	}

	@Override
	public Map<Long, Integer> countByModule(Long docId) {
		List<Map> list = getMybatisDao().countByModule(docId);
		Map<Long, Integer> result = MapUtils.newMap();
		for (Map map : list) {
			result.put(FormatUtils.parseLong(map.get("moduleId")), FormatUtils.parseInt(map.get("totalCount")));
		}
		
		return result;
	}

	@Override
	public List<Inter> listByModuleId(Long docId, Long moduleId, Pager pager) {
		return getMybatisDao().listByModuleId(docId, moduleId, pager);
	}

	@Override
	public int countByModuleId(Long docId, Long moduleId) {
		return getMybatisDao().countByModuleId(docId, moduleId);
	}

	@Override
	public List<Inter> listByViewId(Long viewId,String condition) {
		condition = getLikeExpr(condition);
		return getMybatisDao().listByViewId(viewId,condition);
	}
}
