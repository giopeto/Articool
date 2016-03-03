package com.articool.groups.service;

import java.util.List;

import com.articool.groups.domain.Group;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public interface GroupService {

	public Group set(Group reqGroup);
	public List<Group> get();
	public void delete(Long id);
	public void update(Group reqGroup);
	public Group getOneGroupById(Long id);
}
