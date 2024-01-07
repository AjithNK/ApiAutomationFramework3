package steps;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import core.APIHelper;
import core.APIRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class DeleteUserDetails {
	
	//Instance variables
	public Response response ;
	public APIRequest apiRequest;
	public String filePath;
	
	
	//Scenario 1
	//Step 1
	@Given("The user details that need to be deleted")
	public void the_user_details_that_need_to_be_deleted() {
	
		//Creating Request object
		apiRequest = new APIRequest("delete","https://reqres.in/api/users/10");
	
	}
	
	//Step 2
	@When("The admin user submits the user details to delete")
	public void the_admin_user_submits_the_user_details_to_delete() {
		
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
	
	}

	//Step 3
	@Then("The user details should get deleted successfully")
	public void the_user_details_should_get_deleted_successfully() {
	
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());

		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(), 204);
		Assert.assertEquals(response.getContentType(), "");
		Assert.assertEquals(response.getBody().asPrettyString(),"");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 204 No Content");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);  
				
	
	}

	
	//Scenario 2
	//Step 1
	@Given("The user details to be deleted are passed from external file")
	public void the_user_details_to_be_deleted_are_passed_from_external_file() {
		
		//Creating the JSON file path
		String jsonFileName="DeleteUser.json";
		filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
	}

	//Step 2
	@When("The admin user submits the user details to delete given in	external file")
	public void the_admin_user_submits_the_user_details_to_delete_given_in_external_file() {
		
		//Creating Request object
		APIRequest apiRequest = new APIRequest(filePath);
				
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		response = apiHelper.hitAPI(apiRequest);
	}

	//Step 3
	@Then("The user obtained from external file should get deleted successfully")
	public void the_user_obtained_from_external_file_should_get_deleted_successfully() {
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(),204);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 204 No Content");
		Assert.assertEquals(response.getContentType(), "");
		Assert.assertEquals(response.getBody().asPrettyString(),"");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<5000);
		
	}

}

