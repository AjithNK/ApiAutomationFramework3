package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;



public class JsonProcessor {
	
	
	
	//This method is used to convert json object into json string
	public static String jsonObjectToString(JSONObject jsonObj) {
		
		return jsonObj.toJSONString();
		
	}
	
	
	
	//This method is used to convert json string to json object
	public static JSONObject stringToJsonObject(String inputString) {
		
		JSONObject jsonObj= new JSONObject();	
		JSONParser parser = new JSONParser();
		
		
		try {
			
			 jsonObj = (JSONObject) parser.parse(inputString);    //parsing the json string using parse() method
			 System.out.println(jsonObj.getClass());
		} 
		
		
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		return jsonObj;
		
	}
	
	
	

	//This method is used to read a json file from a particular location and then parse it to json object
	public static JSONObject readFromJsonFile(String filePath) {
		
		JSONParser jsonParser = new JSONParser();
		FileReader fileReader=null;
		JSONObject jsonObject =null;
		
		
		try {
			
			fileReader = new FileReader(filePath);
			jsonObject = (JSONObject) jsonParser.parse(fileReader);
			
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		} 
		
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		catch (Exception e) {
			e.printStackTrace(); 
		}
		
	
		
		return jsonObject;
		
	}
	
	
	//This method is used to convert json string to ObjectMap
	public static HashMap<String, Object> getJsonAsObjectMap(String toJSONString) {
        HashMap<String,Object> objectMap = new Gson().fromJson(toJSONString, HashMap.class);
        return  objectMap;
    }

}
