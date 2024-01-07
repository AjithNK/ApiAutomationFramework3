package steps;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;
import requestPojo.RequestPojoPut;

@SuppressWarnings("unchecked")
public class UpdateUserDetails {
	
	//Instance variables
	public HashMap<String, String> headers;
	public JSONObject requestBody;
	public Response response;
	public String filePath;
	public HashMap<String, String> metaInfo;
	public JSONObject jsonObj;
	public JSONObject requestJSONObject;
	
	
	//Scenario 1
	//Step 1
	@Given("The user details that need to be updated")
	public void the_user_details_that_need_to_be_updated() {
		
		//Header
		HashMap<String, String>  headers = new HashMap<String,String>();
		headers.put("Content-Type","application/json");
				
		//Request body
		requestBody = new JSONObject();
		requestBody.put("name", "Rajesh");
		requestBody.put("job", "Automation Engineer");
		
	}

	//Step 2
	@When("The admin user submits the user details")
	public void the_admin_user_submits_the_user_details() {
		
		//Creating the Request object
		APIRequest apiRequestPut = new APIRequest("put","https://reqres.in/api/users/2",headers,requestBody);
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		System.out.println(apiHelper.hitAPI(apiRequestPut));
		response = apiHelper.hitAPI(apiRequestPut);  
	}

	//Step 3
	@Then("The user details should get updated successfully")
	public void the_user_details_should_get_updated_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
	  
	}
	
	
	//Scenario 2
	//Step 1
	@Given("The user details to be updated are passed from external file")
	public void the_user_details_to_be_updated_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="UpdateUserDetails.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
	}

	//Step 2
	@When("The admin user submits the user details given in	external file")
	public void the_admin_user_submits_the_user_details_given_in_external_file() {
	    
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath);
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
	}

	//Step 3
	@Then("The user details obtained from external file should get updated successfully")
	public void the_user_details_obtained_from_external_file_should_get_updated_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
	}
	
	
	//Scenario 3
	//Step 1
	@Given("The user details to be updated are passed from external file & request body is dynamic")
	public void the_user_details_to_be_updated_are_passed_from_external_file_request_body_is_dynamic() {
		
		//Creating the JSON file path
		String jsonFileName="UpdateUserDetails3.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
					
		//Request body as hashMap
		metaInfo = new HashMap<String, String>();
		metaInfo.put("name", "John");
		metaInfo.put("job", "admin");
	
	}

	//Step 2
	@When("The admin user submits the dynamic request body & user details given in external file")
	public void the_admin_user_submits_the_dynamic_request_body_user_details_given_in_external_file() {
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath, metaInfo);
					
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
	
	}

	//Step 3
	@Then("The request should be sent & user details obtained from external file should get updated successfully")
	public void the_request_should_be_sent_user_details_obtained_from_external_file_should_get_updated_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
					
		//Asserting the response details		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
	
	}
	
	
	//Scenario 4
	//Step 1
	@Given("The user details to be updated are passed from external file & request body as java object")
	public void the_user_details_to_be_updated_are_passed_from_external_file_request_body_as_java_object() {

		//Creating the JSON file path
		String jsonFileName="UpdateUserDetails4.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
				
		//Reading the data from JSON file and obtaining the jsonObject 
		jsonObj = JsonProcessor.readFromJsonFile(filePath);
				
		//Headers
		HashMap<String,String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "json");
				
		//Creating object of request pojo
		RequestPojoPut requestPojoPut = new RequestPojoPut();
		requestPojoPut.setName("John");
		requestPojoPut.setJob("Team Lead");
				
		//Obtaining the request as JSON Object
		requestJSONObject = JsonProcessor.stringToJsonObject(requestPojoPut.toString());
		
	}

	//Step 2
	@When("The admin user submits the request with user details given in external file & request body as java object")
	public void the_admin_user_submits_the_request_with_user_details_given_in_external_file_request_body_as_java_object() {

		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObj.get("requestType").toString(),
				jsonObj.get("requestApiPath").toString(),headers, requestJSONObject);
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
			
	}

	//Step 3
	@Then("The request should be sent & user details obtained from external file, java object should get updated successfully")
	public void the_request_should_be_sent_user_details_obtained_from_external_file_java_object_should_get_updated_successfully() {

		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details	
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}

	
	
}

