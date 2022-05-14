package com.dev.doc.service.impl;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.DocArchiveType;
import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.qiniu.common.QiniuCfg;
import com.dev.base.qiniu.service.QiniuService;
import com.dev.base.util.Pager;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.FileUtils;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.dao.DocArchiveDao;
import com.dev.doc.entity.DocArchive;
import com.dev.doc.exports.service.BaseDocExportService;
import com.dev.doc.service.DocArchiveService;

import io.swagger.models.Swagger;

/**
 * 
		* <p>Title: 文档归档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月26日上午10:46:26</p>
 */
@Service
public class DocArchiveServiceImpl extends BaseMybatisServiceImpl<DocArchive,Long,DocArchiveDao>
										implements DocArchiveService{
	@Resource(name = "swaggerJsonDocExportServiceImpl")
	private BaseDocExportService<Swagger> swaggerJsonDocExportService;
	
	@Autowired
	private QiniuService qiniuService;
	
	@Override
	public DocArchive add(DocArchive docArchive) {
		//创建归档文件并返回url
		String archiveUrl = buildArchive(docArchive.getDocId(), docArchive.getTitle(), CfgConstants.DOC_ARCHIVE_TYPE);
		docArchive.setArchiveUrl(archiveUrl);
		docArchive.setType(CfgConstants.DOC_ARCHIVE_TYPE);
		getMybatisDao().add(docArchive);
		
		//设置分享url
		Long archiveId = docArchive.getId();
		docArchive.setShareUrl(encryShareInfo(archiveId, docArchive.getDocId()));
		update(docArchive);
		
		return docArchive;
	}

	@Override
	public DocArchive getByDocId(Long archiveId, Long docId) {
		return getMybatisDao().getByDocId(archiveId, docId);
	}

	@Override
	public void updateByDocId(DocArchive docArchive) {
		getMybatisDao().updateByDocId(docArchive);
	}

	@Override
	public void delByDocId(Long archiveId, Long docId) {
		getMybatisDao().delByDocId(archiveId, docId);
	}

	@Override
	public List<DocArchive> listByDocId(Long docId, String title, String description, String label, Pager pager) {
		title = getLikeExpr(title);
		description = getLikeExpr(description);
		label = getLikeExpr(label);
		
		return getMybatisDao().listByDocId(docId, title, description, label, pager);
	}

	@Override
	public int countByDocId(Long docId, String title, String description, String label) {
		title = getLikeExpr(title);
		description = getLikeExpr(description);
		label = getLikeExpr(label);
		
		return getMybatisDao().countByDocId(docId, title, description, label);
	}

	@Override
	public String encryShareInfo(Long archiveId, Long docId) {
		if (docId == null || archiveId == null) {
			return null;
		}
		
		Map<String, Object> infoMap = MapUtils.newMap();
		infoMap.put("docId", docId);
		infoMap.put("archiveId", archiveId);
		return CryptUtil.encryptAES(JsonUtils.toJson(infoMap),CfgConstants.SECURITY_SECRET_KEY);
	}

	@Override
	public DocArchive decryptShareInfo(String shareKey) {
		if (StringUtils.isEmpty(shareKey)) {
			return null;
		}
		
		Map<String, Object> infoMap = JsonUtils.toObject(CryptUtil.decryptAES(shareKey,CfgConstants.SECURITY_SECRET_KEY), HashMap.class);
		DocArchive docArchive = new DocArchive();
		docArchive.setDocId(FormatUtils.parseLong(infoMap.get("docId")));
		docArchive.setId(FormatUtils.parseLong(infoMap.get("archiveId")));
		return docArchive;
	}

	@Override
	public String buildArchive(Long docId,String title, DocArchiveType type) {
		Swagger swagger = swaggerJsonDocExportService.exportDoc(null, docId, null, null);
		String url = buildArchiveUrl(docId, title);
		boolean result = false;
		switch (type) {
			case local:
				result = buildLocalArchive(swagger, url);
				break;
				
			case remote:
				result = buildRemoteArchive(swagger, url);
				break;
				
			default:
				break;
		}
		
		return result ? url : null;
	}
	
	//创建本地归档
	private boolean buildLocalArchive(Swagger swagger,String url){
		boolean result = false;
		String filePath = CfgConstants.DOC_ARCHIVE_LOCAL_BASE_URL + url;
		boolean isSuccess = FileUtils.makeDirsByPath(filePath);
		if (!isSuccess) {
			return result;
		}
		
		try {
			IOUtils.write(JsonUtils.toFormatJson(swagger), new FileOutputStream(filePath));
			result = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	//创建远程归档
	private boolean buildRemoteArchive(Swagger swagger,String url){
		boolean result = false;
		try {
			qiniuService.upload(QiniuCfg.BUCKET_PRIV_NAME, JsonUtils.toFormatJson(swagger).getBytes(AppConstants.DEF_CHARSET), url);
			result = true;
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//创建归档路径，无论是本地的还是远程的都一样
	private String buildArchiveUrl(Long docId,String title){
		String createDate = DateUtil.format("yyyyMMddHHmmss", DateUtil.getNow());
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(CfgConstants.DOC_ARCHIVE_URL_PREFIX)
				  .append(docId)
				  .append("/")
				  .append(title)
				  .append("_")
				  .append(createDate)
				  .append(".json");
		return urlBuilder.toString();
	}

	@Override
	public String loadArchive(Long docId, Long archiveId) {
		DocArchive docArchive = getByDocId(archiveId, docId);
		String result = "";
		if (docArchive == null) {
			return result;
		}

		try {
			org.springframework.core.io.Resource resource = null;
			switch (docArchive.getType()) {
				case remote:
					String remoteUrl = CfgConstants.DOC_ARCHIVE_REMOTE_BASE_URL + docArchive.getArchiveUrl();
					resource = new UrlResource(qiniuService.getPrivDownUrl(remoteUrl, QiniuCfg.PRIV_URL_EXPIRE_SECONDS));
					break;
					
				case local:
					resource = new FileSystemResource(CfgConstants.DOC_ARCHIVE_LOCAL_BASE_URL + docArchive.getArchiveUrl());
					break;
		
				default:
					break;
			}
					
			if (resource != null) {
				result = IOUtils.toString(resource.getInputStream(), AppConstants.DEF_CHARSET);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
