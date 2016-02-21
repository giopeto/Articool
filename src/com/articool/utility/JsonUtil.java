package com.articool.utility;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class JsonUtil {
	public static JSONObject makeJson(HttpServletRequest request) throws IOException {
		JSONObject jsonObject = new JSONObject();
		StringBuilder sb = new StringBuilder();
	    BufferedReader br = request.getReader();
	    String str;
	    while( (str = br.readLine()) != null ){
	        sb.append(str);
	    }
	    
	    try {
			jsonObject = new JSONObject(sb.toString());
		} catch (JSONException e) {
			
		}
	    return jsonObject;
	}

}
