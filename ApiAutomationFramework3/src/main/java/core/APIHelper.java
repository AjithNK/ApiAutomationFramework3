package core;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import test.TestPost;

public class APIHelper {
	
	//Method for hitting different APIs using various RestAssured methods
	public Response hitAPI(APIRequest apiRequest) {
		
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification request = RestAssured.given();
		Header header =null;
		Response response = null;
		HashMap<String, String> headers = null;
		
		//TestPost t1= new TestPost();
		
		
		if(apiRequest.getHeaders()!=null) {
		
			header = new Header("Content-Type",apiRequest.getHeaders().get("Content-Type"));
			
			headers = new HashMap<String, String>();
			
			
			
			//String var1 =headers.get("Content-Type");
			//headers.get("Accept");
		//	headers.get("Connection");
			//headers.put("Content-Type","application/json");
			//headers.put("Accept","*/*");
			//headers.put("Connection","keep-alive");
		}
		

		//request.header(header);
		//Performing the Rest Assured method calls depending on the Request Type obtained
		if(apiRequest.getRequestType().equalsIgnoreCase("get")) {
			 response = request.get(apiRequest.getApiPath());
		}
		
		else if(apiRequest.getRequestType().equalsIgnoreCase("post")) {
		
			request.body(apiRequest.getRequestBody());
			response = request.post(apiRequest.getApiPath());
			System.out.println("Headers in hitAPI: "+ headers);
			
		}
		
		else if(apiRequest.getRequestType().equalsIgnoreCase("put")) {
			request.body(apiRequest.getRequestBody());
			response= request.put(apiRequest.getApiPath());
		}
		
		else if(apiRequest.getRequestType().equalsIgnoreCase("patch")) {
			
			request.body(apiRequest.getRequestBody());
			response = request.patch(apiRequest.getApiPath());
			
		}
		
		else if(apiRequest.getRequestType().equalsIgnoreCase("delete")) {
			response = request.delete(apiRequest.getApiPath());
			
		} 
		
		return response;
	}

}
