package test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.restassured.response.Response;
import pojo.ListUser;

public class Test1Get {
	
	//GET REQUEST 1 - Get Users     
	//(pass data as hard-coded values)
	
	@Test(enabled=false)
	public void testGet() {
		
		APIRequest apiRequest = new APIRequest("get", "/api/users?page=2");

		APIHelper apiHelper = new APIHelper();
		
		Response response = apiHelper.hitAPI(apiRequest);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.body());
		System.out.println(response.asPrettyString());
		
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		// JsonPath - 1st method (suport.data)
		// use pojo classes -2nd method

		ListUser listUser = null;

		ObjectMapper om = new ObjectMapper();
		try {
			listUser = om.readValue(jsonObj.toString(), ListUser.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(listUser.getSupport().getUrl());

	}
	
	
	
	//GET REQUEST 2 - Get Single User     
	//(pass data from json file)
	
	@Test(enabled=true)
	public void testGetSingleUser() {

		  /*APIRequest apiRequest=new APIRequest("get","/api/users/2");
		  Response response=apiHelper.hitAPI(apiRequest);
		  System.out.println(response.getStatusCode());
		  System.out.println(response.getStatusLine());
		  System.out.println(response.body());
		  System.out.println(response.asPrettyString());*/
		
		  JsonProcessor obj=new JsonProcessor();
		  JSONObject jsonObject=obj.readFromJsonFile("D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\SingleUser.json");
		 
		  System.out.println(jsonObject.get("requestType"));
		  
		  
		
	}
	
	
	//GET REQUEST 3 - Get List Users    
	
	@Test(enabled = false)
	public void testGetListUsers() {
		JsonProcessor obj = new JsonProcessor();
		JSONObject jsonObject = obj.readFromJsonFile(
				"D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\ListUsers.json");
		System.out.println(jsonObject.get("requestType"));
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
		
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		Assert.assertEquals(200, response.getStatusCode());

		ListUser listUsers = null;
		ObjectMapper om = new ObjectMapper();
		try {
			listUsers = om.readValue(response.asString(), ListUser.class);
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listUsers.getPage());
	}
	
	
	//GET REQUEST 4 - Get Single User Not Found 
	@Test(enabled = false)
	public void testGetSingleUsersNotFound() {
	
		JsonProcessor obj = new JsonProcessor();
		JSONObject jsonObject = obj.readFromJsonFile(
				"D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\SingleUserNotFound.json");
		System.out.println(jsonObject.get("requestType"));
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
		
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		Assert.assertEquals(404, response.getStatusCode());
		
		Assert.assertEquals("{}", response.body().asString());
	
	}
	
	
	//GET REQUEST 5 - Get List Resources
	@Test(enabled = false)
	public void testGetListResourses() {
	
		JsonProcessor obj = new JsonProcessor();
		JSONObject jsonObject = obj.readFromJsonFile(
				"D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\ListResources.json");
		System.out.println(jsonObject.get("requestType"));
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
		
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		Assert.assertEquals(200, response.getStatusCode());
		//construct API Request object
		//APIHelper.hit method is called using object created in step 1
		//validate response
		
	
	}

}
