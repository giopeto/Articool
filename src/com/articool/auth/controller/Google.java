package com.articool.auth.controller;


import java.io.IOException;







//import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.*;

import com.articool.auth.service.AuthGoogleService;
//import com.articool.auth.service.AuthGoogleService;
import com.articool.utility.JsonUtil;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
public class Google extends HttpServlet {

	AuthGoogleService googleService = new AuthGoogleService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		JsonObject thisUser = null;
		
		
		JsonObject jsonReq = JsonUtil.makeJson(req);
		String code = jsonReq.get("code").getAsString();
		String clientId = jsonReq.get("clientId").getAsString();
		String redirectUri = jsonReq.get("redirectUri").getAsString();
		
		if (code != null) {
			thisUser = googleService.getGoogleUser(code, clientId, redirectUri);
		} else {
			System.out.println("No code");
		}
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(thisUser);
		
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

	}

}