package com.articool.utility;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.labs.repackaged.org.json.JSONException;
/*import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;*/
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
	public static JsonObject makeJson(HttpServletRequest request) throws IOException {
		JsonParser parser = new JsonParser();
		StringBuilder sb = new StringBuilder();
	    BufferedReader br = request.getReader();
	    String str;
	    while( (str = br.readLine()) != null ){
	        sb.append(str);
	    }
	    JsonObject returnJsonObject = parser.parse(sb.toString()).getAsJsonObject();
	  
	    return returnJsonObject;
	}

}
