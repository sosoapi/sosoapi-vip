package com.dev.base.utils.bean;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

public class OvalObj {
	public final static String PROFILE_ADD_OPPER = "addOper";
	public final static String PROFILE_UPDATE_OPPER = "updateOper";
	
	/** notnull必须放第一个*/
	@NotNull
	//@NotBlank(profiles = {PROFILE_ADD_OPPER},errorCode = "50001",message = "cust.not.empty")
	@NotBlank(profiles = {PROFILE_ADD_OPPER},errorCode = "50001")
	private String name;
	
	@Min(value = 1)
	private int age;
	
	
	@Length(min = 10,profiles = {PROFILE_ADD_OPPER,PROFILE_UPDATE_OPPER})
	private String version;
	
	public OvalObj() {
		
	}

	public OvalObj(String name,int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
