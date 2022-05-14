package com.dev.base.utils;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dev.base.utils.bean.OvalObj;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class OvalValidateUtilsTest {
	private OvalObj obj;
	
	@Before
	public void init(){
		obj = new OvalObj();
		obj.setName("");
		obj.setAge(-1);
	}
	
	@Test
	public void validAll(){
		OvalValidateUtils.validate(obj);
	}
	
	@Test
	public void validPart(){
		OvalValidateUtils.validate(obj, OvalObj.PROFILE_ADD_OPPER);
	}
	
	@Test
	public void validField() throws Exception {
		Field field = OvalObj.class.getDeclaredField("age");
		OvalValidateUtils.validateFieldValue(obj,field,-1);
	}
	
	@Test
	public void validFieldName() throws Exception {
		OvalValidateUtils.validateFieldValue(obj,"age",-1);
	}
}
