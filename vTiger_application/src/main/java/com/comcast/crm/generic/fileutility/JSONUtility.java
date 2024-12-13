package com.comcast.crm.generic.fileutility;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONUtility {
	
	public String getDataFromJsonFile(String key) throws Throwable
	{
		FileReader file=new FileReader(IPathConstant.JSON_path);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(file);
		JSONObject jobj=(JSONObject)obj;
		String data = (String)jobj.get(key);
		
		return data;
	}

}
