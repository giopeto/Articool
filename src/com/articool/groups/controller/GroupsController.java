package com.articool.groups.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.*;

import com.articool.groups.domain.Group;
import com.articool.groups.service.GroupServiceImpl;
import com.articool.utility.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class GroupsController extends HttpServlet {

	private GroupServiceImpl groupService = new GroupServiceImpl();
	private Gson gson = new Gson();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.setCharacterEncoding("UTF-8");
		
		//JsonObject jsonResp = new JsonObject();
		JsonObject jsonReq =  (JsonObject) JsonUtil.makeJson(req).get("data");
		Group group = new Group( jsonReq);
		groupService.set(group);
		
		/*resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(jsonResp);*/
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		JsonObject jsonReq = (JsonObject) JsonUtil.makeJson(req).get("data");
		
		
		
			Group group = new Group(jsonReq.get("id").getAsLong(), jsonReq);
			groupService.update(group);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String returnGroup = null;
		String reqId = req.getParameter("id");
		if (reqId != null && !reqId.isEmpty()) {
			long id = Long.parseLong(reqId);
			System.out.println("Id: "+ reqId);
			Group  group = groupService.getOneGroupById(id);
			returnGroup = gson.toJson(group);
		} else {
			List<Group> groups = groupService.get();
			Type token = new TypeToken<List<Group>>(){}.getType();
			returnGroup = new Gson().toJson(groups, token);
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		resp.getWriter().println(returnGroup);
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		long id = Long.parseLong(req.getParameter("id"));
		groupService.delete(id);
		
	}
	
}
