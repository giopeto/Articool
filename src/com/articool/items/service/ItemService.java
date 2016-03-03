package com.articool.items.service;

import java.util.List;

import com.articool.groups.domain.Group;
import com.articool.items.domain.Item;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public interface ItemService {

	public Item set(Item reqItem);
	public List<Item> get();
	public void delete(Long id);
	public void deleteImageToItem(Long id);
	public void update(Item reqItem);
	public Item getOneItemById(Long id);
	public List<Item> getItemsByGroupId(Long id);
}