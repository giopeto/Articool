package com.articool.items.service;

import com.articool.items.domain.Item;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public interface ItemService {

	public JSONObject set(JSONObject reqItem);
	public String get();
	public void delete(Long id);
	public void deleteImageToItem(Long id);
	public void update(JSONObject reqItem);
	public Item getOneItemById(Long id);
	public String getItemsByGroupId(Long id);
}
