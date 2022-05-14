package com.dev.base.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.dev.base.json.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtilsTest {
	
	public static String serializeAllExcept(Object obj, String... filterFields) 
    { 
        try 
        { 
            ObjectMapper mapper = new ObjectMapper(); 
            mapper.addMixInAnnotations(UserTemp.class,MyFilterMixIn.class);  

            return mapper.writeValueAsString(obj); 
        } 
        catch (Exception e) 
        { 
            throw new RuntimeException("Json.format error:" + obj, e); 
        } 
    }
	
	@Test
	public void testFilter() {
		UserTemp userTemp = new UserTemp();
		userTemp.setName("user 1");
		userTemp.setAge(1);
		
		AddressTemp addressTemp = new AddressTemp();
		addressTemp.setAddress("address 1");
		addressTemp.setCity("city 1");
		userTemp.setAddressTemp(addressTemp);
		System.out.println(JsonUtils.toFormatJson(userTemp));
		
		System.out.println(serializeAllExcept(userTemp,"name"));
	}
	
	@Test
	public void testJson() throws Exception{
		String filePath = "src\\test\\resources\\json.txt";
		String content = FileUtils.readFileToString(new File(filePath));
		System.out.println(content);
		content = content.trim().replace("\r\n", "<br/>").replace(" ", "&nbsp;");
		System.out.println(content);
		
		//System.out.println(content.trim().startsWith("["));
		//String jsonFormat = JsonUtils.formatJson(content);
		//System.out.println(jsonFormat);
		
//		Map<String, Object> json = JsonUtils.toObject(content, Map.class);
//		System.out.println(JsonUtils.toJson(json));
	}
	
	@Test
	public void testByte(){
		List<String> result = new ArrayList<String>();
		List<String> other = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			result.add("" + i);
		}
		
		/*for (int j = 10; j < 20; j++) {
			other.add("" + j);
		}*/
		
		result.addAll(0, other);
		System.out.println(JsonUtils.toFormatJson(result));
	}
	
	@Test
	public void testClone() throws Exception{
		UserTemp userTemp = new UserTemp();
		userTemp.setName("user 1");
		userTemp.setAge(1);
		
		AddressTemp addressTemp = new AddressTemp();
		addressTemp.setAddress("address 1");
		addressTemp.setCity("city 1");
		userTemp.setAddressTemp(addressTemp);
		System.out.println(JsonUtils.toFormatJson(userTemp));
		
		UserTemp userTemp2 = new UserTemp();
		BeanUtils.copyProperties(userTemp, userTemp2);
		System.out.println(JsonUtils.toFormatJson(userTemp2));
	}
}

//@JsonFilter("UserTemp")
class UserTemp{
	private String name;
	private int age;
	private AddressTemp addressTemp;
	
	public UserTemp(){
		
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

	public AddressTemp getAddressTemp() {
		return addressTemp;
	}

	public void setAddressTemp(AddressTemp addressTemp) {
		this.addressTemp = addressTemp;
	}
}

class AddressTemp{
	private String city;
	private String address;
	
	public AddressTemp(){
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

@JsonIgnoreProperties(value={"name"}) 
interface MyFilterMixIn{  
}  