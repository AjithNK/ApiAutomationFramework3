package steps;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;

import core.APIHelper;
import core.APIRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

//get, put,patch
//validate response body by converting to body

public class CreateUserSteps {
	 Response response=null;
	
	 @When("Admin user creates user: {string} , job: {string}")
	    public void add_user(String name, String job) {
	        
		 HashMap<String, String> headers = new HashMap<String, String>();
		 headers.put("Content-Type","application/json");
		
		 JSONObject requestObject = new JSONObject();
		 requestObject.put( "name",name);
		 requestObject.put(  "job", job);
		 
		 APIRequest apiRequestPost = new APIRequest("post","/api/users",headers,requestObject);
		 
		 APIHelper apiHelper = new APIHelper();
		 response = apiHelper.hitAPI(apiRequestPost);
		 
	    }
	    @Then("User should get created")
	    public void user_is_created() {
	    	 Assert.assertEquals(201, response.getStatusCode());
	    }


}
