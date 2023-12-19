package test;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import requestPojo.RequestPojoPost;

public class Test1Post {
	
	//POST REQUEST 1 - Create User    
	//(pass data as hard-coded values)
		
	@Test(enabled=false)

	public void testPost()
	{
	 HashMap<String, String> headers = new HashMap<String, String>();
	 headers.put("Content-Type","application/json");
	
	 JSONObject requestObject = new JSONObject();
	 requestObject.put( "name","morpheus");
	 requestObject.put(  "job", "leader");
	 
	 APIRequest apiRequestPost = new APIRequest("post","/api/users",headers,requestObject);
	 
	 APIHelper apiHelper = new APIHelper();
	 Response response = apiHelper.hitAPI(apiRequestPost);
	
	 Assert.assertEquals(201, response.getStatusCode());
		
	}
	
	
	
	//POST REQUEST 2 - Create User    
	//(pass data from json file)
	 @Test (enabled=false)
	  public void testPost2() {
		 
		 APIHelper apiHelper = new APIHelper();
		  
		  /*HashMap<String,String> headers=new HashMap<String,String>();
		  headers.put("Content-Type", "text/plain");
		  JSONObject requestObject=new JSONObject();
		  requestObject.put("name","morpheus");
		  requestObject.put("job","leader");*/
		 
		  APIRequest apiRequestPost=new APIRequest("D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\api2.json");
		  Response response=apiHelper.hitAPI(apiRequestPost);
		  System.out.println(response.getStatusCode());
		  Assert.assertEquals(201,response.getStatusCode());
		  
		 
	  }
	 
	 
	 
	 //POST REQUEST 3 - Create User    
	 //(pass data from json file and request is dynamic)
	 
	 @Test(enabled=false)

	  public void testPostOrchestration() {
		 
		 APIHelper apiHelper = new APIHelper();
		  
		  /*HashMap<String,String> headers=new HashMap<String,String>();
		  headers.put("Content-Type", "text/plain");
		  JSONObject requestObject=new JSONObject();
		  requestObject.put("name","morpheus");
		  requestObject.put("job","leader");*/
		 HashMap<String,String> metaInfo = new HashMap<String,String>();
	/*	 metaInfo.put("name", "Ram");
		 metaInfo.put("job", "IT Professional");  */
		 
		 //Solving the data type issue
		 metaInfo.put("name", "Ram");
		 metaInfo.put("job", "QA");
		
		  APIRequest apiRequestPost=new APIRequest("D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\api2.json",metaInfo);
		  Response response=apiHelper.hitAPI(apiRequestPost);
		  System.out.println(response.getStatusCode());
		  Assert.assertEquals(201,response.getStatusCode());
		  
		 
	  }
	 
	 
	 
	//POST REQUEST 4 - Create User  
	//pass data from json file and request obtained using RequestPojo)
	 @Test(enabled=true)

	  public void testPostOrchestrationUsingRequestPojo() {
		 
		 
		RequestPojoPost objRequest = new RequestPojoPost();
		
		objRequest.setName("Raj");
		objRequest.setJob("leader");
		
		System.out.println(objRequest);
		HashMap<String,String> headers = new HashMap<String, String>();
		
		JSONObject requestObject=JsonProcessor.stringToJsonObject(objRequest.toString());
		
		JSONObject jsonObject = JsonProcessor.readFromJsonFile(
				"D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\ap3.json");
		
		APIRequest apiRequest= new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString(),headers,requestObject) ;
		
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		System.out.println(response.getStatusCode());
		
		
		 
	 } 
	 
	 
}


