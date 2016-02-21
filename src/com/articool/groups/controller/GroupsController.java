package com.articool.groups.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import com.articool.groups.domain.Group;
import com.articool.groups.service.GroupService;
import com.articool.groups.service.GroupServiceImpl;
import com.articool.items.domain.Item;
import com.articool.items.service.ItemServiceImpl;
import com.articool.utility.JsonUtil;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class GroupsController extends HttpServlet {

	private GroupServiceImpl groupService = new GroupServiceImpl();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.setCharacterEncoding("UTF-8");
		JSONObject jsonReq = null;
		
		//System.out.println("asdasd" + JsonUtil.makeJson(req).toString());
		
		JSONObject jsonResp = new JSONObject();
		
		try {
			jsonReq = (JSONObject) JsonUtil.makeJson(req).get("data");
			System.out.println(jsonReq);
			groupService.set(jsonReq);
			//jsonResp = groupService.set(jsonReq);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(jsonResp);
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		try {
			JSONObject jsonReq = (JSONObject) JsonUtil.makeJson(req).get("data");
			groupService.update(jsonReq);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String allGroups = null;
		String reqId = req.getParameter("id");
		if (reqId != null && !reqId.isEmpty()) {
			Gson gson = new Gson();
			long id = Long.parseLong(reqId);
			System.out.println("Id: "+ reqId);
			Group  thisItem = groupService.getOneGroupById(id);
			System.out.println(gson.toJson(thisItem));
			allGroups = gson.toJson(thisItem).toString();
			
		} else {
			allGroups = groupService.get();
			
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(allGroups);
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String reqId = req.getParameter("id");
		long id = Long.parseLong(reqId);
		groupService.delete(id);
		/*
		try {
			Long id = (Long) JsonUtil.makeJson(req).get("id");
			itemsService.delete(id);
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
		
	}
	
}
