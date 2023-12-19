package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

public class APIRequest {

	public String requestType;  			 //GET OR POST OR PUT OR PATCH
	public String apiPath; 					 //ENDPOINT
	public HashMap<String, String> headers;  //HEADERS
	public JSONObject requestBody;  	     //REQUEST BDODY 

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public JSONObject getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(JSONObject requestBody) {
		this.requestBody = requestBody;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	
	//Constructor called for get request
	public APIRequest(String requestType, String apiPath) {
		super();
		this.requestType = requestType;
		this.apiPath = apiPath;
	}
	
	
	
	//Constructor called for post request
	public APIRequest(String requestType, String apiPath, HashMap<String, String> headers, JSONObject requestBody) {
		super();
		this.requestType = requestType;
		this.apiPath = apiPath;
		this.headers = headers;
		this.requestBody = requestBody;
	}
	
	
	//Constructor called for any request for which the details are passed from external json file
	public APIRequest(String filepath) {
		  JsonProcessor obj=new JsonProcessor();
		  JSONObject jsonObject=obj.readFromJsonFile(filepath);
		  
		  this.requestType=(String) jsonObject.get("requestType");
		  this.apiPath=(String) jsonObject.get("requestApiPath");
		  
		  if(requestType.equalsIgnoreCase("post")) {
			  this.requestBody = (JSONObject) jsonObject.get("requestBody");
		  }
		  
		  if(jsonObject.containsKey("headers")) {
			 this.headers =(HashMap<String, String>) jsonObject.get("headers");
		  }
		  	 
	}
	
	//Constructor called for post request for which the details are passed from external json file 
	// & request body passed as a hashmap
	public APIRequest(String filepath,HashMap<String,String>metaInfo) {
		  JsonProcessor obj=new JsonProcessor();
		  JSONObject jsonObject=obj.readFromJsonFile(filepath);
		  
		  this.requestType=(String) jsonObject.get("requestType");
		  this.apiPath=(String) jsonObject.get("requestApiPath");
		  
		  if(requestType.equalsIgnoreCase("post")) {
			  this.requestBody = (JSONObject) jsonObject.get("requestBody");
			  this.requestBody=orchestrateRequest(requestBody,metaInfo);
			  System.out.println(requestBody.toString());
		  }
		  
		  if(jsonObject.containsKey("headers")) {
			 this.headers =(HashMap<String, String>) jsonObject.get("headers");
		  }
		  	 
	}

	
	//orchestrateRequest() method 
	public JSONObject orchestrateRequest(JSONObject requestBody,HashMap<String, String> metaInfo){
		
		 HashMap<String,Object> reqMap = JsonProcessor.getJsonAsObjectMap(requestBody.toJSONString());
		   for (Map.Entry<String, Object> it : reqMap.entrySet()) {

		       // Verify Key starts with "#" if yes ,need to orchestrate
		       if(it.getKey().startsWith("#")){
		           //get metaInfo map key name by reading Json value
		           if(it.getValue().toString().contains("%s")){
		               String metaKey = it.getValue().toString().split(",")[1];
		               
		               /*     if(this.metaInfo.containsKey(metaKey)) {
		                  String toBeReplaced = metaInfo.get(metaKey);
		                   requestBody.put(it.getKey().split("#")[1],toBeReplaced);
		                   requestBody.remove(it.getKey());
		               }*/
		           }
		           if(it.getValue().toString().contains("%d")){
		               String metaKey = it.getValue().toString().split(",")[1];
		     /*          if(this.metaInfo.containsKey(metaKey)) {
		                   int toBeReplaced = Integer.parseInt(metaInfo.get(metaKey));
		                   requestBody.put(it.getKey().split("#")[1],toBeReplaced);
		                   requestBody.remove(it.getKey());
		               }*/
		           } 
		           
		           /*
		            * Need to orchestrate for Int, json, decimal etc
		            * Also for multiple replacer for single json attribute
		            */
		       }


		   }
		   return requestBody;
		}
	


}
