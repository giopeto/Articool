package com.articool.user.service;

import java.util.List;

import com.articool.user.domain.User;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;

public interface UserService {

	public void set(JsonObject reqUser);
	public String get();
	public User getOneUserById(Long id);
	public String getOneUserByVendorCode(String code);
	
	/*public void delete(Long id);
	public void update(JSONObject reqGroup);
	*/
}
