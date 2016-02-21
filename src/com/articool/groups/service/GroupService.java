package com.articool.groups.service;

import com.articool.groups.domain.Group;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public interface GroupService {

	public JSONObject set(JSONObject reqGroup);
	public String get();
	public void delete(Long id);
	public void update(JSONObject reqGroup);
	public Group getOneGroupById(Long id);
}
