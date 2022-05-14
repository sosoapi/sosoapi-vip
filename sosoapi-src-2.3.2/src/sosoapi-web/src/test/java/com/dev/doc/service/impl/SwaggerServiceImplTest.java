package com.dev.doc.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.dev.base.json.JsonUtils;
import com.dev.base.test.BaseTest;
import com.dev.base.util.FreeMarkerUtil;
import com.dev.doc.exports.json.filter.OperationFilter;
import com.dev.doc.exports.json.filter.ResponseFilter;
import com.dev.doc.exports.json.filter.SwaggerFilter;
import com.dev.doc.exports.json.filter.TagFilter;
import com.dev.doc.exports.service.BaseDocExportService;
import com.dev.doc.exports.vo.DocDataInfo;
import com.dev.doc.imports.service.BaseDocImportService;
import com.dev.doc.vo.PostmanCollection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.models.Operation;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

public class SwaggerServiceImplTest extends BaseTest{
	@Resource(name = "swaggerJsonDocImportServiceImpl")
	private BaseDocImportService<Map<String, Object>> swaggerJsonDocImportService;
	
	@Resource(name = "swaggerJsonDocExportServiceImpl")
	private BaseDocExportService<Swagger> swaggerJsonDocExportService;
	
	@Resource(name = "htmlDocExportServiceImpl")
	private BaseDocExportService<DocDataInfo> htmlDocExportService;
	
	@Resource(name = "postmanDocExportServiceImpl")
	private BaseDocExportService<PostmanCollection> postmanDocExportService;
	
	@Test
	public void testExportApiDoc() throws Exception {
		Swagger swagger = swaggerJsonDocExportService.exportDoc(14L, 366L,null,null);
		System.out.println(JsonUtils.toFormatJson(swagger));
//		FileUtils.writeStringToFile(new File("d://dev.json"), JsonUtils.toFormatJson(swagger));
	}

	@Test
	public void testExportStandard() throws Exception{
		Swagger swagger = swaggerJsonDocExportService.exportDoc(14L, 1L,null,null);
		
		//处理swagger官网格式
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//添加自定义属性过滤
		mapper.addMixInAnnotations(Swagger.class, SwaggerFilter.class);
		mapper.addMixInAnnotations(Operation.class, OperationFilter.class);
		mapper.addMixInAnnotations(Response.class, ResponseFilter.class);
		mapper.addMixInAnnotations(Tag.class, TagFilter.class);
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(swagger));
	}
	
	@Test
	public void testBuildDocTmplData(){
		DocDataInfo result = htmlDocExportService.exportDoc(14L, 1L, null,null);
		System.out.println(JsonUtils.toJson(result));
	}
	
	@Test
	public void testExportDoc() throws Exception{
		DocDataInfo result = htmlDocExportService.exportDoc(14L, 1L, null,null);
		System.out.println(JsonUtils.toJson(result));
		Writer writer = new FileWriter(new File("d:/demo.html"));
		FreeMarkerUtil.process("html.ftl", result, writer);
	}
	
	@Test
	public void testImportDoc() throws Exception{
		String fileName = "src\\main\\webapp\\swagger\\json\\bug.json";
		String content = FileUtils.readFileToString(new File(fileName));
		swaggerJsonDocImportService.importDoc(14L, JsonUtils.toObject(content, HashMap.class));
	}
}
