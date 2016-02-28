package com.articool.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpUtillity {

	public static JsonObject getHttpResponseAsJsonObject(String thisUrl,
			String httpMethod) throws IOException {

		URL url = new URL(thisUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8"); 
		connection.setDoOutput(true);
		connection.setRequestMethod(httpMethod);
		connection.setInstanceFollowRedirects(false);

		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ connection.getResponseCode());
		}

		
		
		//BufferedReader reader = new BufferedReader(new InputStreamReader(resultContentIS, "UTF-8"));

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(connection.getInputStream()) , "UTF-8"));

		String responseString = "";
		String output;

		while ((output = br.readLine()) != null) {
			responseString += output.trim();
			
		}
		//Charset.forName("UTF-8").encode(responseString);
		JsonParser parser = new JsonParser();
		JsonObject responseObject = parser.parse(responseString)
				.getAsJsonObject();
		
		connection.disconnect();
		
		
		
		
		
		return responseObject;

	}

}
