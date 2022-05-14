package com.dev.base.qiniu.demo;

import java.io.File;

import com.dev.base.json.JsonUtils;
import com.dev.base.qiniu.common.QiniuCfg;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class SimpleTest {
	private static Auth auth = Auth.create(QiniuCfg.getAccessKey(),QiniuCfg.getSecretKey());
	
	private static UploadManager uploadManager = new UploadManager(QiniuCfg.getConfiguration());
	
	private static BucketManager bucketManager = new BucketManager(auth,QiniuCfg.getConfiguration());
	
	public static void main(String[] args) throws Exception {
		String bucket = "demo";
		String key = "/img/002/head/pig.jpg";
		String filePath = "E:/qq图片/pig.jpg";
		
		upload(bucket,key,filePath);
//		System.out.println(getDownUrl(baseUrl,key));
//		System.out.println(auth.uploadToken(bucket));
		
//		printBucket();
	}

	public static void printBucket() throws Exception{
		String[] buckets = bucketManager.buckets();
		for (String bucket : buckets) {
			System.out.println(bucket);
		}
	}
	
	public static void upload(String bucket,String key,String filePath) throws Exception{
		//默认上传已存在文件名报错
//		String uploadToken = auth.uploadToken(bucket);
		
		//指定资源覆盖上传
		StringMap stringMap = new StringMap();
		stringMap.put("scope", bucket + ":" + key);
		String uploadToken = auth.uploadToken(bucket, key, 3600, stringMap);
		
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
		return baseUrl + key;
	}
}
