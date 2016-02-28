package com.articool.users.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import com.articool.groups.domain.Group;
import com.articool.groups.service.GroupService;
import com.articool.groups.service.GroupServiceImpl;
import com.articool.items.domain.Item;
import com.articool.items.service.ItemServiceImpl;
import com.articool.user.domain.User;
import com.articool.user.service.UserServiceImpl;
import com.articool.utility.JsonUtil;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;





@SuppressWarnings("serial")
public class UsersController extends HttpServlet {

	private UserServiceImpl userService = new UserServiceImpl();
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String thisUser = null;
		String reqCode = req.getParameter("code");
		
		
		
		
		System.out.println("цодеее"+ reqCode);
		
		if (reqCode != null && !reqCode.isEmpty()) {
			Gson gson = new Gson();
			
			System.out.println("Id: "+ reqCode);
			thisUser  = userService.getOneUserByVendorCode(reqCode);
			System.out.println(gson.toJson(thisUser));
			//thisUser = gson.toJson(user).toString();
			
		} 
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(thisUser);
	}
	
	
	
}

