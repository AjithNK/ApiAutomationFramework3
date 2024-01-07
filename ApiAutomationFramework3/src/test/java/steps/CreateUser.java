package steps;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.Assert;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import requestPojo.RequestPojoPost;
@SuppressWarnings("unchecked")
public class CreateUser {
	
	//Instance variables
	public HashMap<String, String> headers;
	public JSONObject requestObject;
	public APIRequest apiRequestPost;
	public Response response;
	public String filePath;
	public HashMap<String,String> metaInfo;
	public JSONObject requestBody;
	APIRequest apiRequest;
	JSONObject jsonObj;
	JSONObject requestJSONObject;
	
	
	//Scenario 1
	//Step 1
	@Given("The user details which needs to be created")
	public void the_user_details_which_needs_to_be_created() {
		
		//Request headers	
		headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
			
		//Request body
		requestObject = new JSONObject();
		requestObject.put( "name","morpheus");
		requestObject.put(  "job", "leader");
			 
		//Creating the Request object
		apiRequestPost = new APIRequest("post","/api/users",headers,requestObject);
	}

	//Step 2
	@When("The admin user submits the user details which will be created")
	public void the_admin_user_submits_the_user_details_which_will_be_created() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response	
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequestPost);
		
	}

	//Step 3
	@Then("The user should be created successfully")
	public void the_user_should_be_created_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
					
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
				
	}
	
	
	//Scenario 2
	//Step 1
	@Given("The user details which needs to be created are passed from external file")
	public void the_user_details_which_needs_to_be_created_are_passed_from_external_file() {

		//Creating the JSON file path
		String jsonFileName="CreateUser2.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
			
		//Request headers	
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
				
		//Creating the Request object
		apiRequestPost=new APIRequest(filePath);
		
	}
	
	//Step 2
	@When("The admin user submits the user details as a request & user will be created")
	public void the_admin_user_submits_the_user_details_as_a_request_user_will_be_created() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response=apiHelper.hitAPI(apiRequestPost);
				
	}
	
	//Step 3
	@Then("The user should be created successfully as per details obtained from external file")
	public void the_user_should_be_created_successfully_as_per_details_obtained_from_external_file() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
				  
	}
	
	
	//Scenario 3
	//Step 1
	@Given("The user details which needs to be created are passed from external file & request body is dynamic")
	public void the_user_details_which_needs_to_be_created_are_passed_from_external_file_request_body_is_dynamic() {

		//Creating the JSON file path
		String jsonFileName="CreateUser3.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
					
		//Request headers	
		headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
				
		//Creating Request body as a hashMap for dynamically replacing the existing request body 
		metaInfo = new HashMap<String,String>();
		//Solving the data type issue
		metaInfo.put("name", "Ram");
		metaInfo.put("job", "QA");
			
		//Creating the Request object
		apiRequestPost=new APIRequest(filePath,metaInfo);
				
	}

	//Step 2
	@When("The admin user submits the user details\\(passed from external file) as a request & user will be created")
	public void the_admin_user_submits_the_user_details_passed_from_external_file_as_a_request_user_will_be_created() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response=apiHelper.hitAPI(apiRequestPost);
		
	}

	//Step 3
	@Then("The user should be created successfully as per details obtained from external file & the request body")
	public void the_user_should_be_created_successfully_as_per_details_obtained_from_external_file_the_request_body() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
				  
	}
	
	
	//Scenario 4
	//Step 1
	@Given("The user details which needs to be created are passed from external file & request body as java object")
	public void the_user_details_which_needs_to_be_created_are_passed_from_external_file_request_body_as_java_object() {

		//Creating the JSON file path
		String jsonFileName="CreateUser4.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Reading the data from JSON file and obtaining the jsonObject 
		jsonObj = JsonProcessor.readFromJsonFile(filePath);
		
		//Request headers	
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive"); 
		
		//Creating object of request pojo
		RequestPojoPost requestPojoPost = new RequestPojoPost();
		requestPojoPost.setName("Raj");
		requestPojoPost.setJob("leader");
				
		//Obtaining the request as JSON Object
		requestJSONObject = JsonProcessor.stringToJsonObject(requestPojoPost.toString());
		
	}

	//Step 2
	@When("The admin user submits the user details\\(passed from external file and as java object) as a request & user will be created")
	public void the_admin_user_submits_the_user_details_passed_from_external_file_and_as_java_object_as_a_request_user_will_be_created() {

		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObj.get("requestType").toString(),
				jsonObj.get("requestApiPath").toString(),headers, requestJSONObject);
						
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
						
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
		
	}

	//Step 3
	@Then("The user should be created successfully as per details obtained from external file & the request body as java object")
	public void the_user_should_be_created_successfully_as_per_details_obtained_from_external_file_the_request_body_as_java_object() {

		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
						
		//Asserting the response details	
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}
	
	
	//Scenario 5
	//Step 1
	@Given("The user to be registered & request details are passed from external file")
	public void the_user_to_be_registered_request_details_are_passed_from_external_file() {

		//Request headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
			
		//Creating the JSON file path
		String jsonFileName="UserRegistrationSuccessful.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
		//Request body
		requestBody = new JSONObject();
		requestBody.put("username", "cerulean");
		requestBody.put("email", "eve.holt@reqres.in");
		requestBody.put("password", "pistol@123");
		
		//Creating the Request object
		apiRequest = new APIRequest(filePath);	
		
	}

	//Step 2
	@When("The admin user submits the user details as a request & user will be registered")
	public void the_admin_user_submits_the_user_details_as_a_request_user_will_be_registered() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	//Step 3
	@Then("The user should be registered successfully")
	public void the_user_should_be_registered_successfully() {

		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}
	
	
	//Scenario 6
	//Step 1
	@Given("The user to be registered & the request details are passed from external file")
	public void the_user_to_be_registered_the_request_details_are_passed_from_external_file() {

		//Request headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");	
				
		//Creating the JSON file path
		String jsonFileName="UserRegistrationUnSuccessful.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
		//Creating Request body as a hashMap for dynamically replacing the existing request body 
		HashMap<String, String> metaInfo = new HashMap<String, String>();
		metaInfo.put("email", "sydney@fife");
				
		//Creating the Request object
		apiRequest = new APIRequest(filePath, metaInfo);
				
	}

	//Step 2
	@When("The admin user submits the user details as a request & user will not be registered")
	public void the_admin_user_submits_the_user_details_as_a_request_user_will_not_be_registered() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	@Then("The user should not be registered")
	public void the_user_should_not_be_registered() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}
	
	
	//Scenario 7
	//Step 1
	@Given("The user to be logged in & the request details are passed from external file")
	public void the_user_to_be_logged_in_the_request_details_are_passed_from_external_file() {
		
		//Request headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
				
		//Request body
		requestBody = new JSONObject();
		requestBody.put("email","peter@klaven");
					
		//Creating the Request object
		apiRequest = new APIRequest("post","https://reqres.in/api/login",headers,requestBody);
	}

	//Step 2
	@When("The user submits the details as a request & user will not be able to login")
	public void the_user_submits_the_details_as_a_request_user_will_not_be_able_to_login() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
				
	}

	//Step 3
	@Then("The user should not be able to login")
	public void the_user_should_not_be_able_to_login() {

		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
					
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
					
	}
	
	
	
}



