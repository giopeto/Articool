package com.articool.auth.controller;

import java.io.IOException;

import javax.servlet.http.*;

import com.articool.auth.service.AuthFacebookService;
import com.articool.utility.JsonUtil;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
public class Facebook extends HttpServlet {

	AuthFacebookService facebookService = new AuthFacebookService();
	// CREATE FOLDER auth.controller and check user/users folder
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		JsonObject thisUser = null;
		JsonObject jsonReq = JsonUtil.makeJson(req);
		String code = jsonReq.get("code").getAsString();
		String clientId = jsonReq.get("clientId").getAsString();
		String redirectUri = jsonReq.get("redirectUri").getAsString();
	
		if (code != null) {
			thisUser = facebookService.getFacebookUser(code, clientId, redirectUri);
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