package com.dev.base.qiniu.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.base.qiniu.common.QiniuCfg;
import com.dev.base.qiniu.service.QiniuService;
import com.dev.base.utils.FileUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

/**
 * 
		* <p>Title: 七牛云相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午5:04:50</p>
 */
@Service
public class QiniuServiceImpl implements QiniuService{
	private static Logger logger = LogManager.getLogger(QiniuServiceImpl.class);
	
	private static Auth auth;
	
	private static UploadManager uploadManager;
	
	private static BucketManager bucketManager;
	
	//创建auth信息
	private Auth getAuth(){
		if (auth == null) {
			auth = Auth.create(QiniuCfg.getAccessKey(),QiniuCfg.getSecretKey());
		}
		
		return auth;
	}
	
	//创建UploadManager
	private UploadManager getUploadManager(){
		if (uploadManager == null) {
			uploadManager = new UploadManager(QiniuCfg.getConfiguration());
		}
		
		return uploadManager;
	}
	
	//创建BucketManager
	private BucketManager getBucketManager(){
		if (bucketManager == null) {
			bucketManager = new BucketManager(getAuth(),QiniuCfg.getConfiguration());
		}
		
		return bucketManager;
	}
	
	@Override
	public void upload(String filePath,String filterArrayStr,String bucket,String prefix){
		List<File> fileList = FileUtils.listFile(filePath, filterArrayStr);
		if (fileList.isEmpty()) {
			return ;
		}
		
		String uploadToken = getUploadToken(bucket);
		Response response = null;
		for (File file : fileList) {
			try {
				response = getUploadManager().put(file, prefix + file.getName(), uploadToken);
				if (!response.isOK()) {
					logger.error("fail to upload file [" + file.getName() + "]");
					logger.error(response.bodyString());
				}
			} 
			catch (QiniuException e) {
				logger.error("fail to upload file [" + file.getName() + "]");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void batchDel(String bucket,String prefix){
		List<String> keyList = list(bucket, prefix);
		for (String key : keyList) {
			try {
				getBucketManager().delete(bucket, key);
			} 
			catch (QiniuException e) {
				logger.error("fail to delete file [" + key + "]");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<String> list(String bucket,String prefix){
		List<String> result = new ArrayList<String>();
		try {
			FileListing fileListing = getBucketManager().listFiles(bucket, prefix, null, 100, null);
			while (fileListing != null && fileListing.items.length > 0) {
				for (FileInfo fileInfo : fileListing.items) {
					result.add(fileInfo.key);
				}
				
				if (StringUtils.isEmpty(fileListing.marker)) {
					fileListing = null;
				}
				else{
					fileListing = getBucketManager().listFiles(bucket, prefix, fileListing.marker, 100, null);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public void upload(String bucket, String key, File file) {
		upload(bucket, getUploadToken(bucket), key, file);
	}

	@Override
	public void upload(String bucket,byte[] data, String key) {
		try {
			getUploadManager().put(data, key, getUploadToken(bucket));
		}
		catch (QiniuException e) {
			logger.error("fail to upload file [" + key + "]");
			e.printStackTrace();
		}
	}

	@Override
	public void upload(String bucket,InputStream stream, String key) {
		try {
			getUploadManager().put(stream, key, getUploadToken(bucket), null, null);
		} 
		catch (QiniuException e) {
			logger.error("fail to upload file [" + key + "]");
			e.printStackTrace();
		}
	}
	
	@Override
	public String getUploadToken(String bucket) {
		return getAuth().uploadToken(bucket);
	}

	@Override
	public void upload(String bucket, String uploadToken, String key, File file) {
		Response response = null;
		try {
			response = getUploadManager().put(file, key, uploadToken);
			if (!response.isOK()) {
				logger.error("fail to upload file [" + file.getName() + "]");
				logger.error(response.bodyString());
			}
		} 
		catch (QiniuException e) {
			logger.error("fail to upload file [" + file.getName() + "]");
			e.printStackTrace();
		}
	}

	@Override
	public String getPrivDownUrl(String pubDownUrl, long expireInSeconds) {
		return getAuth().privateDownloadUrl(pubDownUrl,expireInSeconds);
	}

	@Override
	public void del(String bucket, String key) {
		try {
			getBucketManager().delete(bucket, key);
		} 
		catch (QiniuException e) {
			logger.error("fail to delete file [" + key + "]");
			e.printStackTrace();
		}
	}
}
