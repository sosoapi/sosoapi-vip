package com.dev.base.util;

import java.util.Map;

import javax.crypto.EncryptedPrivateKeyInfo;

import org.junit.Test;

import com.dev.base.constant.CfgConstants;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.MapUtils;

public class CryptUtilTest {

	@Test
	public void encrypt(){
		Map<String, Object> content = MapUtils.newMap();
		content.put("userId", 14L);
		content.put("docId", 1L);
		content.put("viewId", 8L);
		
		System.out.println(CryptUtil.encryptAES(JsonUtils.toJson(content), CfgConstants.SECURITY_SECRET_KEY));
	}

}
