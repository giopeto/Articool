package com.articool.auth;


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
		
		JSONObject jsonReq = (JSONObject) JsonUtil.makeJson(req);
		
		
		
		//System.out.println("asdasd" + JsonUtil.makeJson(req).toString());
		
		String code = null;
		String clientId = null;
		String redirectUri = null;
		try {
			code = (String) jsonReq.get("code");
			clientId = (String) jsonReq.get("clientId");
			redirectUri = (String) jsonReq.get("redirectUri");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (code != null) {
			googleService.getGoogleUser(code, clientId, redirectUri);
		} else {
			System.out.println("No code");
		}
		
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

	}

}