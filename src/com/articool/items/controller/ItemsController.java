package com.articool.items.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import com.articool.groups.domain.Group;
import com.articool.items.domain.Item;
import com.articool.items.service.ItemServiceImpl;
import com.articool.utility.JsonUtil;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class ItemsController extends HttpServlet {

	private ItemServiceImpl itemsService = new ItemServiceImpl();
	private Gson gson = new Gson();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//req.setCharacterEncoding("UTF-8");
		
		JsonObject jsonReq = (JsonObject) JsonUtil.makeJson(req).get("data");
		Item item = new Item(jsonReq);
		itemsService.set(item);
		//Item jsonResp = itemsService.set(item);
		
		/*resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(jsonResp);*/
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		JsonObject jsonReq = (JsonObject) JsonUtil.makeJson(req).get("data");
		Item item = new Item(jsonReq.get("id").getAsLong(), jsonReq);
		itemsService.update(item);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String returnItem = null;
		
		String reqId = req.getParameter("id");
		String reqGroupId = req.getParameter("groupId");
		
		if (reqGroupId != null && !reqGroupId.equals("undefined") && !reqGroupId.isEmpty()){
			reqGroupId = reqGroupId.replace(":","");
			long groupId = (Long) Long.valueOf(reqGroupId).longValue();
			
			
			
			List<Item> allGroupItems = itemsService.getItemsByGroupId(groupId);
			Type token = new TypeToken<List<Item>>(){}.getType();
			returnItem = new Gson().toJson(allGroupItems, token);
			
			
			
		} else if (reqId != null && !reqId.isEmpty()) {
			long id = Long.parseLong(reqId);
			Item  thisItem = itemsService.getOneItemById(id);
			returnItem = gson.toJson(thisItem).toString();
			
		} else {
			List<Item> allItems = itemsService.get();
			returnItem = gson.toJson(allItems);
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(returnItem);
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		long id = Long.parseLong(req.getParameter("id"));
		itemsService.delete(id);
	}
	
}
