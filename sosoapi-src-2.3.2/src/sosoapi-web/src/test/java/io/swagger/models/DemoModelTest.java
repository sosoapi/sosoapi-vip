package io.swagger.models;

import io.swagger.models.parameters.FormParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:app-context.xml")
public class DemoModelTest {
	
	@Test
	public void testCombineModel(){
		System.out.println(JsonUtils.toFormatJson(createSwagger()));
	}
	
	private Swagger createSwagger(){
		Swagger swagger = new Swagger();
		swagger.setHost("192.168.1.131:9200");
		swagger.setBasePath("/peng/rest");
		swagger.setSchemes(createScheme());
		swagger.setConsumes(createConsume());
		swagger.setProduces(createProduce());
		swagger.setInfo(createInfo());
		swagger.setDefinitions(createDefinition());
		swagger.setPaths(createPath());
		
		return swagger;
	}
	
	private List<Scheme> createScheme(){
		List<Scheme> schemeList = new ArrayList<Scheme>();
		schemeList.add(Scheme.HTTP);
		schemeList.add(Scheme.HTTPS);
		
		return schemeList;
	}
	
	private List<String> createConsume(){
		List<String> consumeList = new ArrayList<String>();
		consumeList.add("application/json");
		consumeList.add("application/xml");
		
		return consumeList;
	}
	
	private List<String> createProduce(){
		List<String> produceList = new ArrayList<String>();
		produceList.add("application/json");
		produceList.add("application/xml");
		
		return produceList;
	}
	
	private Info createInfo(){
		String desc = "调用说明:<br/><br/> 1.api接口协议前缀有'/auth/'的必须在head中添加token，其中key为'token',value为token值<br/><br/> 2.开发环境:[http://192.168.1.131:9200/peng]<br/><br/> 3.测试环境:[http://192.168.1.131:9300/peng]<br/><br/>";
		Info info = new Info();
		info.setDescription(desc);
		info.setTitle("Peng");
		info.setVersion("1.0.0");
		
		return info;
	}
	
	private Map<String, Model> createDefinition(){
		StringProperty errorCodeProperty = new StringProperty();
		errorCodeProperty.setDescription("错误码");
		errorCodeProperty.setRequired(true);
		
		StringProperty errorMsgProperty = new StringProperty();
		errorMsgProperty.setDescription("错误提示信息");
		errorCodeProperty.setRequired(false);
		
		Map<String, Property> properties = MapUtils.newMap();
		properties.put("errorCode", errorCodeProperty);
		properties.put("errorMsg", errorMsgProperty);
		
		
		ModelImpl errorModel = new ModelImpl();
		errorModel.setProperties(properties);

		Map<String,Model> modelMap = MapUtils.newMap();
		modelMap.put("ErrorModel", errorModel);
		return modelMap;
	}
	
	private Map<String,Path> createPath(){
		Path registPath = new Path();
		registPath.setPost(createRegistOper());
		
		Path loginPath = new Path();
		loginPath.setPost(createLoginOper());
		
		Map<String,Path> pathMap = MapUtils.newMap();
		pathMap.put("/pass/regist/JH_reg_phone", registPath);
//		pathMap.put("/pass/login/JH_user_login", loginPath);
		
		return pathMap;
	}
	
	private Operation createRegistOper(){
		Operation operation = new Operation();
		operation.setSummary("手机号注册");
		operation.setDescription("注册");
		
		List<String> tags = new ArrayList<String>();
		tags.add("登陆注册");
		operation.setTags(tags);
		
		List<Parameter> parameterList = new ArrayList<Parameter>();
		FormParameter phoneNumberParameter = new FormParameter();
		phoneNumberParameter.setName("phoneNumber");
		phoneNumberParameter.setDescription("手机号");
		phoneNumberParameter.setRequired(true);
		phoneNumberParameter.setType("string");
		parameterList.add(phoneNumberParameter);
		
		FormParameter nickNameParameter = new FormParameter();
		nickNameParameter.setName("nickName");
		nickNameParameter.setDescription("昵称");
		nickNameParameter.setRequired(false);
		nickNameParameter.setType("string");
		parameterList.add(nickNameParameter);
		
		FormParameter authCodeParameter = new FormParameter();
		authCodeParameter.setName("authCode");
		authCodeParameter.setDescription("验证码");
		authCodeParameter.setRequired(true);
		authCodeParameter.setType("string");
		parameterList.add(authCodeParameter);
		
		FormParameter deviceIdParameter = new FormParameter();
		deviceIdParameter.setName("deviceId");
		deviceIdParameter.setDescription("推送设备id");
		deviceIdParameter.setRequired(true);
		deviceIdParameter.setType("string");
		parameterList.add(deviceIdParameter);
		
		FormParameter deviceTypeParameter = new FormParameter();
		deviceTypeParameter.setName("deviceType");
		deviceTypeParameter.setDescription("推送设备类型");
		deviceTypeParameter.setRequired(true);
		deviceTypeParameter.setType("string");
		List<String> enumList = new ArrayList<String>();
		enumList.add("0");
		enumList.add("1");
		deviceTypeParameter.setEnum(enumList);
		parameterList.add(deviceTypeParameter);
		
		operation.setParameters(parameterList);

		Map<String,Response> responseMap = MapUtils.newMap();
		Response code51007 = new Response();
		code51007.setDescription("用户已注册");
		responseMap.put("51007", code51007);
		
		Response code51011 = new Response();
		code51011.setDescription("验证码错误");
		responseMap.put("51011", code51011);
		
		RefProperty schema = new RefProperty();
		schema.set$ref("ErrorModel");
		
		Response codeDef = new Response();
		codeDef.setDescription("成功");
		codeDef.setSchema(schema);
		responseMap.put("default", codeDef);
		
		operation.setResponses(responseMap);
		operation.setConsumes(createConsume());
		operation.setProduces(createProduce());
		
		return operation;
	}
	
	private Operation createLoginOper(){
		Operation operation = new Operation();
		
		return operation;
	}
}
