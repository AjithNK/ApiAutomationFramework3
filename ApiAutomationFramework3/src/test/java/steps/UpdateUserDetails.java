package steps;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;

import core.APIHelper;
import core.APIRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

@SuppressWarnings("unchecked")
public class UpdateUserDetails {
	
	public HashMap<String, String> headers;
	public JSONObject requestBody;
	public Response response;
	
	
	
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

	@When("The admin user submits the user details")
	public void the_admin_user_submits_the_user_details() {
		
		//Creating the Request object
		APIRequest apiRequestPut = new APIRequest("put","https://reqres.in/api/users/2",headers,requestBody);
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		System.out.println(apiHelper.hitAPI(apiRequestPut));
		response = apiHelper.hitAPI(apiRequestPut);  
	}

	@Then("The user details should get updated successfully")
	public void the_user_details_should_get_updated_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
	  
	}
	

}
