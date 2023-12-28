package core;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIHelper {
	
	public Response hitAPI(APIRequest apiRequest) {
		
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification request = RestAssured.given();
		Header header =null;
		
		
		if(apiRequest.getHeaders()!=null) {
		
			header = new Header("Content-Type",apiRequest.getHeaders().get("Content-Type"));
		}
		
		Response response = null;
		
		//request.header(header);
		if(apiRequest.getRequestType().equalsIgnoreCase("get")) {
			 response = request.get(apiRequest.getApiPath());
		}
		
		else if(apiRequest.getRequestType().equalsIgnoreCase("post")) {
		
			request.body(apiRequest.getRequestBody());
			response = request.post(apiRequest.getApiPath());
			
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
