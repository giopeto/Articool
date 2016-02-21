package com.articool.groups.service;

import java.util.List;

import com.articool.groups.domain.Group;
import com.articool.items.domain.Item;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GroupServiceImpl implements GroupService {

	@Override
	public JSONObject set(JSONObject reqGroup) {
		System.out.println("from set");
		System.out.println(reqGroup);
		
		Group group = new Group(reqGroup);
		ofy().save().entity(group).now();
		
		return null;
		
	}

	@Override
	public String get() {
		List<Group> items = ofy().load().type(Group.class).list();
		
		String allGroups = new Gson().toJson(items);
		return allGroups;
	}

	@Override
	public void delete(Long id) {
		ofy().delete().type(Group.class).id(id).now();
		
	}

	@Override
	public void update(JSONObject reqGroup) {
		Group group = null;
		try {
			group = new Group((Long) reqGroup.get("id"), reqGroup);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		//System.out.println(item.toString());
		
		ofy().save().entity(group).now();
		
	}

	@Override
	public Group getOneGroupById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
