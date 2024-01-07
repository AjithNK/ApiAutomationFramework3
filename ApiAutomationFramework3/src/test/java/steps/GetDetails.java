package steps;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.listResources.ListResources;
import pojo.listUsers.ListUsers;
import pojo.singleResource.SingleResource;
import pojo.singleUser.SingleUser;

public class GetDetails {
	
	//Instance variables
	public APIRequest apiRequest;
	public Response response;
	public String filePath;
	public JSONObject jsonObject;
	
	
	//Scenario 1
	//Step 1
	@Given("The users whose details need to be retrieved")
	public void the_users_whose_details_need_to_be_retrieved() {
		
		//Creating the Request object
		apiRequest = new APIRequest("get", "/api/users?page=2");
		
	}

	//Step 2
	@When("The admin user submits the users whose details need to be retrieved")
	public void the_admin_user_submits_the_users_whose_details_need_to_be_retrieved() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	//Step 3
	@Then("The users details should be retrieved successfully")
	public void the_users_details_should_be_retrieved_successfully() {

		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
		//Validating the Response body
		
		ListUsers listUsers = null;
		ObjectMapper objectMapper = new ObjectMapper();
				
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
					
			listUsers = objectMapper.readValue(jsonObj.toString(), ListUsers.class);
					
		} 
				
		catch (Exception e) {
			e.printStackTrace();
		}

		//Asserting various components of response body
				
		Assert.assertEquals(listUsers.getPage(), 2);
		Assert.assertEquals(listUsers.getPer_page(), 6);
		Assert.assertEquals(listUsers.getTotal(),12);
		Assert.assertEquals(listUsers.getTotal_pages(),2);
				
		Assert.assertEquals(listUsers.getData().get(0).getFirst_name(),"Michael");
		Assert.assertEquals(listUsers.getData().get(5).getEmail(),"rachel.howell@reqres.in");
				
		Assert.assertEquals(listUsers.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
		Assert.assertEquals(listUsers.getSupport().getUrl(), "https://reqres.in/#support-heading");
				
				
	}
		
	
	//Scenario 2
	//Step 1
	@Given("The user\\(single user) whose details need to be retrieved are passed from external file")
	public void the_user_single_user_whose_details_need_to_be_retrieved_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="SingleUser.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Creating the Request object
		apiRequest = new APIRequest(filePath);
		
	}

	//Step 2
	@When("The admin user submits the user whose details need to be retrieved given in external file")
	public void the_admin_user_submits_the_user_whose_details_need_to_be_retrieved_given_in_external_file() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
				
	}

	//Step 3
	@Then("The user details\\(single user) obtained from external file should be retrieved successfully")
	public void the_user_details_single_user_obtained_from_external_file_should_be_retrieved_successfully() {

		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
						
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
		//Validating the Response body
		
		SingleUser singleUser = null;
		ObjectMapper objectMapper = new ObjectMapper();
						
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
							
			singleUser = objectMapper.readValue(jsonObj.toString(), SingleUser.class);
							
		} 
						
		catch (Exception e) {
			e.printStackTrace();
		}

		//Asserting various components of response body
				
		Assert.assertEquals(singleUser.getData().getId(),2);
		Assert.assertEquals(singleUser.getData().getFirst_name(),"Janet");
		Assert.assertEquals(singleUser.getData().getLast_name(),"Weaver");
		Assert.assertEquals(singleUser.getData().getEmail(),"janet.weaver@reqres.in");
		Assert.assertEquals(singleUser.getData().getAvatar(),"https://reqres.in/img/faces/2-image.jpg");
				
		Assert.assertEquals(singleUser.getSupport().getUrl(),"https://reqres.in/#support-heading");
		Assert.assertEquals(singleUser.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
	
		
	}
	
	
	//Scenario 3
	//Step 1
	@Given("The users whose details need to be retrieved are passed from external file")
	public void the_users_whose_details_need_to_be_retrieved_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="ListUsers.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
		//Obtaining the values into jsonObject from the json file
		jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
	}

	//Step 2
	@When("The admin user submits the users whose details need to be retrieved given in external file")
	public void the_admin_user_submits_the_users_whose_details_need_to_be_retrieved_given_in_external_file() {
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	//Step 3
	@Then("The users details\\(all users) obtained from external file should be retrieved successfully")
	public void the_users_details_all_users_obtained_from_external_file_should_be_retrieved_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
						
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
				  
		//Validating the Response body
				
		ListUsers listUsers = null;
		ObjectMapper objectMapper = new ObjectMapper();
								
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
									
			listUsers = objectMapper.readValue(jsonObj.toString(), ListUsers.class);
									
		} 
								
		catch (Exception e) {
			e.printStackTrace();
		}
				
		//Asserting various components of response body
				
		Assert.assertEquals(listUsers.getPage(), 2);
		Assert.assertEquals(listUsers.getPer_page(), 6);
		Assert.assertEquals(listUsers.getTotal(),12);
		Assert.assertEquals(listUsers.getTotal_pages(),2);
						
		Assert.assertEquals(listUsers.getData().get(0).getFirst_name(),"Michael");
		Assert.assertEquals(listUsers.getData().get(5).getEmail(),"rachel.howell@reqres.in");
				
		Assert.assertEquals(listUsers.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
		Assert.assertEquals(listUsers.getSupport().getUrl(), "https://reqres.in/#support-heading");

	}
	
	
	//Scenario 4
	//Step 1
	@Given("The user to be searched & request details are passed from external file")
	public void the_user_to_be_searched_request_details_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="SingleUserNotFound.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
		//Obtaining the values into jsonObject from the json file
		jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
	}

	//Step 2
	@When("The admin user submits the user details\\(who is not present) given in external file")
	public void the_admin_user_submits_the_user_details_who_is_not_present_given_in_external_file() {
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	//Step 3
	@Then("The user details should not be shown as the searched user is not present")
	public void the_user_details_should_not_be_shown_as_the_searched_user_is_not_present() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),404);
		Assert.assertEquals(response.body().asString(),"{}");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}
	
	
	//Scenario 5
	//Step 1
	@Given("The resources url whose details need to be retrieved & request details are passed from external file")
	public void the_resources_url_whose_details_need_to_be_retrieved_request_details_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="ListResources.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
		//Obtaining the values into jsonObject from the json file
		jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
	}

	//Step 2
	@When("The admin user submits the resources url & request details to be retrieved given in external file")
	public void the_admin_user_submits_the_resources_url_request_details_to_be_retrieved_given_in_external_file() {
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	//Step 3
	@Then("The resource details\\(list resource) obtained from external file should be retrieved successfully")
	public void the_resource_details_list_resource_obtained_from_external_file_should_be_retrieved_successfully() {

		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
						
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
				
		//Validating the Response body
				
		ListResources listResources = null;
		ObjectMapper objectMapper = new ObjectMapper();
								
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
									
			listResources = objectMapper.readValue(jsonObj.toString(), ListResources.class);
									
		} 
								
		catch (Exception e) {
			e.printStackTrace();
		}
				
		//Asserting various components of response body
				
		Assert.assertEquals(listResources.getPage(), 1);
		Assert.assertEquals(listResources.getPer_page(),6);
		Assert.assertEquals(listResources.getTotal(),12);
		Assert.assertEquals(listResources.getTotal_pages(),2);
				
		Assert.assertEquals(listResources.getData().get(3).getName(),"aqua sky");
		Assert.assertEquals(listResources.getData().get(0).getColor(),"#98B2D1");
				
		Assert.assertEquals(listResources.getSupport().getUrl(),"https://reqres.in/#support-heading");
		Assert.assertEquals(listResources.getSupport().getText(), 
				"To keep ReqRes free, contributions towards server costs are appreciated!");
				
				
	}
	
	
	//Scenario 6
	//Step 1
	@Given("The resource url whose details need to be retrieved & request details are passed from external file")
	public void the_resource_url_whose_details_need_to_be_retrieved_request_details_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="SingleResource.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
		//Obtaining the values into jsonObject from the json file
		jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
	}
	
	//Step 2
	@When("The admin user submits the resource url & request details to be retrieved given in external file")
	public void the_admin_user_submits_the_resource_url_request_details_to_be_retrieved_given_in_external_file() {
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
						
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	//Step 3
	@Then("The resource details\\(single resource) obtained from external file should be retrieved successfully")
	public void the_resource_details_single_resource_obtained_from_external_file_should_be_retrieved_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);	
				
		//Validating the Response body
				
		SingleResource singleResource = null;
		ObjectMapper objectMapper = new ObjectMapper();
								
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
			singleResource = objectMapper.readValue(jsonObj.toString(), SingleResource.class);
									
		} 
								
		catch (Exception e) {
			e.printStackTrace();
		}

		//Asserting various components of response body
				
		Assert.assertEquals(singleResource.getData().getName(),"fuchsia rose");
		Assert.assertEquals(singleResource.getData().getYear(),2001);
		Assert.assertEquals(singleResource.getData().getId(),2);
		Assert.assertEquals(singleResource.getData().getPantone_value(),"17-2031");
				
		Assert.assertEquals(singleResource.getSupport().getUrl(), "https://reqres.in/#support-heading");
		Assert.assertEquals(singleResource.getSupport().getText(),
				"To keep ReqRes free, contributions towards server costs are appreciated!");
				
				
	}
	
	
	//Scenario 7
	//Step 1
	@Given("The resource url which need to be retrieved & request details are passed from external file")
	public void the_resource_url_which_need_to_be_retrieved_request_details_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="GetSingleResourceNotFound.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
						
		//Obtaining the values into jsonObject from the json file
		jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
	}

	//Step 2
	@When("The admin user submits the resource url\\(of the resource which is not present) & request details given in external file")
	public void the_admin_user_submits_the_resource_url_of_the_resource_which_is_not_present_request_details_given_in_external_file() {

		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
						jsonObject.get("requestApiPath").toString());
						
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
				
	}

	//Step 3
	@Then("The resource details should not be shown as the searched resource is not present")
	public void the_resource_details_should_not_be_shown_as_the_searched_resource_is_not_present() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
						
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),404);
		Assert.assertEquals(response.body().asString(),"{}");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}

	
	//Scenario 8
	//Step 1
	@Given("The url for users whose details need to be retrieved & request details are passed from external file")
	public void the_url_for_users_whose_details_need_to_be_retrieved_request_details_are_passed_from_external_file() {

		//Creating the JSON file path
		String jsonFileName="DelayedResponse.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
					
		//Creating the Request object
		apiRequest = new APIRequest(filePath);
				
	}

	//Step 2
	@When("The admin user submits the users url whose details need to be retrieved as a request")
	public void the_admin_user_submits_the_users_url_whose_details_need_to_be_retrieved_as_a_request() {
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
		
	}

	//Step 3
	@Then("The users details should be retrieved with a delay in getting the response")
	public void the_users_details_should_be_retrieved_with_a_delay_in_getting_the_response() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
					
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)>3000);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		
	}




}
