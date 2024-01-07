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
import requestPojo.RequestPojoPatch;

@SuppressWarnings("unchecked")
public class PartialUpdateUserDetails {
	
	//Instance variables
	public Response response;
	public HashMap<String,String> headers;
	public JSONObject requestBody;
	public String filePath;
	public HashMap<String, String> metaInfo;
	public JSONObject jsonObj;
	public JSONObject requestJSONObject;
	
	
	//Scenario 1
	//Step 1
	@Given("The user details that need to be partially updated")
	public void the_user_details_that_need_to_be_partially_updated() {
		
		//Headers
		headers = new HashMap<String,String>();
				
		//RequestBody for updating the email id
		requestBody = new JSONObject();
		requestBody.put("email", "charles.david@reqres.in");
		
	}

	//Step 2
	@When("The admin user submits only the required details which needs to be updated")
	public void the_admin_user_submits_only_the_required_details_which_needs_to_be_updated() {
		
		//Creating Request object
		APIRequest requestPatch= new APIRequest("patch","https://reqres.in/api/users/5",headers,requestBody);
				
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper= new APIHelper();
		response = apiHelper.hitAPI(requestPatch);
		
	}

	//Step 3
	@Then("only the required user details should get updated successfully")
	public void only_the_required_user_details_should_get_updated_successfully() {

		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK"); 
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	} 	
	
	
	//Scenario 2
	//Step 1
	@Given("The user details that need to be partially updated are passed from external file")
	public void the_user_details_that_need_to_be_partially_updated_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="PartialUpdateUserDetails2.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
	}
	
	//Step 2
	@When("The admin user submits only the required details given in external file to be updated")
	public void the_admin_user_submits_only_the_required_details_given_in_external_file_to_be_updated() {
		
		//Creating Request object
		APIRequest apiRequest = new APIRequest(filePath);
			
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apihelper = new APIHelper();
		response = apihelper.hitAPI(apiRequest);
		
	}
	
	//Step 3
	@Then("only the required user details obtained from external file should get updated successfully")
	public void only_the_required_user_details_obtained_from_external_file_should_get_updated_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.asPrettyString());
		System.out.println(response.getHeaders());
			
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}
	

	//Scenario 3
	//Step 1
	@Given("The user details that need to be partially updated are passed from external file & request body is dynamic")
	public void the_user_details_that_need_to_be_partially_updated_are_passed_from_external_file_request_body_is_dynamic() {

		//Creating the JSON file path
		String jsonFileName="PartialUpdateUserDetails3.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
						
		metaInfo = new HashMap<String, String>();
		metaInfo.put("id", "5");
					
	}

	//Step 2
	@When("The admin user submits the dynamic request body & only the required details given in	external file")
	public void the_admin_user_submits_the_dynamic_request_body_only_the_required_details_given_in_external_file() {

		//Creating Request object
		APIRequest apiRequest = new APIRequest(filePath, metaInfo);
				
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
					
	}

	//Step 3
	@Then("The request should be sent & only the required user details obtained from external file should get updated successfully")
	public void the_request_should_be_sent_only_the_required_user_details_obtained_from_external_file_should_get_updated_successfully() {

		//Console output of the response body and headers
		System.out.println(response.asPrettyString());
		System.out.println(response.getHeaders());
					
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
					
	}
	
	
	//Scenario 4
	//Step 1
	@Given("The user details that need to be partially updated are passed from external file & request body as java object")
	public void the_user_details_that_need_to_be_partially_updated_are_passed_from_external_file_request_body_as_java_object() {
		
		//Creating the JSON file path
		String jsonFileName="PartialUpdateUserDetails4.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
								
		//Reading the data from JSON file and obtaining the jsonObject 
		jsonObj = JsonProcessor.readFromJsonFile(filePath);
						
		//Headers
		HashMap<String,String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "json");
						
		//Creating object of request pojo
		RequestPojoPatch requestPojoPatch = new RequestPojoPatch();
		requestPojoPatch.setJob("QA Manager");
						
		//Obtaining the request as JSON Object
		requestJSONObject = JsonProcessor.stringToJsonObject(requestPojoPatch.toString());
	
		
	}

	//Step 2
	@When("The admin user submits the dynamic request body & only the required details given in	external file & request body as java object")
	public void the_admin_user_submits_the_dynamic_request_body_only_the_required_details_given_in_external_file_request_body_as_java_object() {

		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObj.get("requestType").toString(),
				jsonObj.get("requestApiPath").toString(),headers, requestJSONObject);
						
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
				
	
	}

	//Step 3
	@Then("The request should be sent & only the required user details obtained from external file,java object should get updated successfully")
	public void the_request_should_be_sent_only_the_required_user_details_obtained_from_external_file_java_object_should_get_updated_successfully() {

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



