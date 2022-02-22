package com.api.platform.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class ResponseCreator {
	
	
	private static Gson gson = new Gson();
	
	public static <T> JSONObject JSONResponse(T thing) {
		String jsonString = gson.toJson(thing);
	    JSONParser parser = new JSONParser();
	    try {
			JSONObject json = (JSONObject) parser.parse(jsonString);
			return json;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static  <T> List<JSONObject> JSONResponse(Collection<T> things) {
		ArrayList<JSONObject>response = new ArrayList<>();
		things.forEach(thing->{
			JSONObject json = JSONResponse(thing);
			response.add(json);
		});
		return response;
	}
	
	public static JSONObject ErrorResponse(String err) {
		JSONObject json = new JSONObject();
		json.put("err", err);
		return json;
	}

}
