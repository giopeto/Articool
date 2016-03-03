package com.articool.auth.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

import com.articool.users.service.UserServiceImpl;
import com.articool.utility.HttpUtillity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AuthFacebookService {

	
private UserServiceImpl userService = new UserServiceImpl();
	
	public JsonObject getFacebookUser(String code, String clientId, String redirectUri) throws FileNotFoundException, IOException {
		
		String ACCESS_TOKEN = null;
		String EXCHANGE_CODE_FOR_TOKEN_URL = "https://graph.facebook.com/v2.3/oauth/access_token";
		String EXCHANGE_TOKEN_FOR_USER_URL  = "https://graph.facebook.com/me";
		
		String urlParameters = "?code="
				+ code
				+ "&client_id="
				+ clientId
				+ "&client_secret=441eebbbcc45d845247e85a2b022171c"
				+ "&redirect_uri="
				+ redirectUri;
		
		JsonObject oathTokenResponse = HttpUtillity.getHttpResponseAsJsonObject(EXCHANGE_CODE_FOR_TOKEN_URL + urlParameters, "GET");
		if(oathTokenResponse.get("access_token") != null) {
			ACCESS_TOKEN = oathTokenResponse.get("access_token").toString().replaceAll("\"", "");
		}
		urlParameters = null;
		urlParameters = "?access_token="
				+ ACCESS_TOKEN
				+ "&fields="
				+ "id,email,name,first_name,last_name,picture,gender,languages,locale";
		
		JsonObject oathUserResponse = HttpUtillity.getHttpResponseAsJsonObject(EXCHANGE_TOKEN_FOR_USER_URL + urlParameters, "GET");
		
		oathUserResponse.addProperty("vendor", "facebook");
		oathUserResponse.addProperty("vendorCode", code);
		oathUserResponse.addProperty("vendorId", oathUserResponse.get("id").getAsString());
		oathUserResponse.addProperty("given_name", oathUserResponse.get("first_name").getAsString());
		oathUserResponse.addProperty("family_name", oathUserResponse.get("last_name").getAsString());
		oathUserResponse.addProperty("picture", oathUserResponse.get("picture").getAsJsonObject().get("data").getAsJsonObject().get("url").getAsString());
		
		oathUserResponse.remove("id");
		
		//System.out.println("Save: " + oathUserResponse);
		
		userService.set(oathUserResponse);
		
		return oathUserResponse;
	    
		//System.out.println("Save 2: " + oathUserResponse);
	}
	
}
