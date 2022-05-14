package com.dev.base.qiniu.demo;

import java.io.File;

import com.dev.base.json.JsonUtils;
import com.dev.base.qiniu.common.QiniuCfg;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class AuthTest {
	private static Auth auth = Auth.create(QiniuCfg.getAccessKey(),QiniuCfg.getSecretKey());
	
	private static UploadManager uploadManager = new UploadManager(QiniuCfg.getConfiguration());
	
	public static void main(String[] args) throws Exception {
		String bucket = "demo-priv";
		String key = "/img/002/head/pig.jpg";
		String filePath = "E:/qq图片/pig.jpg";
		String baseUrl = "http://clouddn.com/";
		
		upload(bucket,key,filePath);
		System.out.println(getDownUrl(baseUrl,key));
	}

	public static void upload(String bucket,String key,String filePath) throws Exception{
		String uploadToken = auth.uploadToken(bucket);
		Response response = uploadManager.put(new File(filePath), key, uploadToken);
		if (response.isOK()) {
			System.out.println("upload success");
		}
		else {
			System.out.println(response.toString());
			System.out.println(response.bodyString());
		}
		System.out.println(JsonUtils.toJson(response));
	}
	
	public static String getDownUrl(String baseUrl,String key){
		return auth.privateDownloadUrl(baseUrl + key);
	}
}
