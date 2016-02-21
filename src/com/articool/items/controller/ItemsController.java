package com.articool.items.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import com.articool.items.domain.Item;
import com.articool.items.service.ItemServiceImpl;
import com.articool.utility.JsonUtil;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ItemsController extends HttpServlet {

	private ItemServiceImpl itemsService = new ItemServiceImpl();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.setCharacterEncoding("UTF-8");
		JSONObject jsonReq = null;
		JSONObject jsonResp = new JSONObject();
		try {
			jsonReq = (JSONObject) JsonUtil.makeJson(req).get("data");
			jsonResp = itemsService.set(jsonReq);
		} catch (JSONException e) {
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
			itemsService.update(jsonReq);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String allItems = null;
		
		
		
		
		
		String reqId = req.getParameter("id");
		
		
		String reqGroupId = req.getParameter("groupId");
		
		System.out.println(reqGroupId);
		if (reqGroupId != null && !reqGroupId.equals("undefined") && !reqGroupId.isEmpty()){
			System.out.println(reqGroupId);
			
			reqGroupId = reqGroupId.replace(":","");
			Gson gson = new Gson();
			long groupId = (Long) Long.valueOf(reqGroupId).longValue();
			
			System.out.println("gb g: "+groupId);
			
			allItems = itemsService.getItemsByGroupId(groupId);
			
			
			
		} else if (reqId != null && !reqId.isEmpty()) {
			Gson gson = new Gson();
			long id = Long.parseLong(reqId);
			Item  thisItem = itemsService.getOneItemById(id);
			System.out.println(gson.toJson(thisItem));
			allItems = gson.toJson(thisItem).toString();
			
		} else {
			allItems = itemsService.get();
			
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(allItems);
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String reqId = req.getParameter("id");
		long id = Long.parseLong(reqId);
		itemsService.delete(id);
		/*
		try {
			Long id = (Long) JsonUtil.makeJson(req).get("id");
			itemsService.delete(id);
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
		
	}
	
}
