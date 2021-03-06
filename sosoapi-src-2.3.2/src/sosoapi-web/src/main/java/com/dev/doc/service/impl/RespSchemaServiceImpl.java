package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.base.enums.SchemaType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.MapUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.dao.RespSchemaDao;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.vo.RespSchemaInfo;

/**
 * 
		* <p>Title: 接口公共响应相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:18</p>
 */
@Service
public class RespSchemaServiceImpl extends BaseMybatisServiceImpl<RespSchema,Long,RespSchemaDao>
										implements RespSchemaService{

	@Override
	public List<RespSchemaInfo> listByDocId(Long docId, Long moduleId,String code,
										String name,String description,Pager pager) {
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		description = getLikeExpr(description);
		List<Map> infoList = getMybatisDao().listByDocId(docId, moduleId,code,name,description,pager);
		List<RespSchemaInfo> result = new ArrayList<RespSchemaInfo>();
		RespSchemaInfo schemaInfo = null;
		for (Map info : infoList) {
			schemaInfo = new RespSchemaInfo();
			schemaInfo.setId((Long)info.get("id"));
			schemaInfo.setDocId((Long)info.get("docId"));
			schemaInfo.setModuleId((Long)info.get("moduleId"));
			schemaInfo.setCode((String)info.get("code"));
			schemaInfo.setName((String)info.get("name"));
			schemaInfo.setDescription((String)info.get("description"));
			schemaInfo.setType(SchemaType.valueOf((String)info.get("type")));
			schemaInfo.setCreateDate((Date)info.get("createDate"));
			schemaInfo.setModuleName((String)info.get("moduleName"));
			result.add(schemaInfo);
		}
		
		return result;
	}

	@Override
	public List<RespSchema> listAllByDocId(Long docId) {
		return getMybatisDao().listAllByDocId(docId);
	}

	@Override
	public int countByDocId(Long docId, Long moduleId,String code,String name,String description) {
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		description = getLikeExpr(description);
		
		return getMybatisDao().countByDocId(docId, moduleId,code,name,description);
	}

	@Override
	public Map<Long, String> getAllByDocId(Long docId) {
		Map<Long, String> result = MapUtils.newMap();
		
		List<RespSchema> list = listAllByDocId(docId);
		for (RespSchema respSchema : list) {
			result.put(respSchema.getId(), respSchema.getCode());
		}
		
		return result;
	}

	@Override
	public String getCodeById(Long refSchemaId) {
		RespSchema schema = getById(refSchemaId);
		
		return schema == null ? null : schema.getCode();
	}

	@Override
	public void updateByDocId(RespSchema schema) {
		getMybatisDao().updateByDocId(schema);
	}

	@Override
	public void deleteByDocId(Long docId, Long schemaId) {
		getMybatisDao().deleteByDocId(docId, schemaId);
	}

	@Override
	public RespSchema getByDocId(Long docId, Long schemaId) {
		return getMybatisDao().getByDocId(docId, schemaId);
	}

	@Override
	public List<SelectInfo> listRespSchema(Long docId) {
		List<RespSchema> list = listAllByDocId(docId);
		
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		String name = "";
		for (RespSchema schema : list) {
			name = schema.getCode();
			if (!StringUtils.isEmpty(schema.getDescription())) {
				name += " -- " + schema.getDescription();
			}
			result.add(new SelectInfo(name, schema.getId().toString()));
		}
		
		return result;
	}
}
