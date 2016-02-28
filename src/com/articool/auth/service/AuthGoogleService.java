package com.articool.auth.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.articool.groups.service.GroupServiceImpl;
import com.articool.user.service.UserServiceImpl;
import com.articool.utility.HttpUtillity;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;

public class AuthGoogleService {
	
	private UserServiceImpl userService = new UserServiceImpl();
	
	public void getGoogleUser(String code, String clientId, String redirectUri) throws FileNotFoundException, IOException {
		
		String ACCESS_TOKEN = null;
		String EXCHANGE_CODE_FOR_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";
		String EXCHANGE_TOKEN_FOR_USER_URL  = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=";
		
		String urlParameters = "?code="
				+ code
				+ "&client_id="
				+ clientId
				+ "&client_secret=Dw49ctpQtkclU5dxdERIqvnI&grant_type=authorization_code&redirect_uri="
				+ redirectUri;
	
		
		JsonObject oathTokenResponse = HttpUtillity.getHttpResponseAsJsonObject(EXCHANGE_CODE_FOR_TOKEN_URL + urlParameters, "POST");
		if(oathTokenResponse.get("access_token") != null) {
			ACCESS_TOKEN = oathTokenResponse.get("access_token").toString().replaceAll("\"", "");
		}
		
		JsonObject oathUserResponse = HttpUtillity.getHttpResponseAsJsonObject(EXCHANGE_TOKEN_FOR_USER_URL + ACCESS_TOKEN, "GET");
		oathUserResponse.addProperty("vendor", "google");
		oathUserResponse.addProperty("vendorCode", code);
		
		System.out.println("Save: " + oathUserResponse);
		
		userService.set(oathUserResponse);
		
	    
		System.out.println("Save 2: " + oathUserResponse);
	  
	
	
	
	
	
	
	
	
	
	
	// Exchange auth code for access token
	
	/*String userId = payload.getSubject();  // Use this value as a key to identify a user.
	String email = payload.getEmail();
	
	System.out.println("MAil: "+email);
	
	boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
	String name = (String) payload.get("name");
	String pictureUrl = (String) payload.get("picture");
	String locale = (String) payload.get("locale");
	String familyName = (String) payload.get("family_name");
	String givenName = (String) payload.get("given_name");*/
	
	}
}
