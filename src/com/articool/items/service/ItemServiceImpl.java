package com.articool.items.service;

import java.util.List;
import java.util.Map;

import com.articool.items.domain.Item;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ItemServiceImpl implements ItemService {
	
	@Override
	public JSONObject set(JSONObject reqItem) {
		
		Item item = new Item(reqItem);
		ofy().save().entity(item).now();
		
		return reqItem;
	}

	@Override
	public void update(JSONObject reqItem) {

		Item item = null;
		try {
			item = new Item((Long) reqItem.get("id"), reqItem);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		//System.out.println(item.toString());
		
		ofy().save().entity(item).now();
	}

	@Override
	public String get() {
		List<Item> items = ofy().load().type(Item.class).list();
		
		String allItems = new Gson().toJson(items);
		return allItems;
	}
	
	@Override
	public void delete(Long id) {
		deleteImageToItem(id);
		ofy().delete().type(Item.class).id(id).now();
	}
	
	@Override
	public void deleteImageToItem(Long id) {
		
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		
		Item item = getOneItemById(id);
		Map<String, String> fileIds = item.getFileIds();
		
		for (Map.Entry<String, String> entry : fileIds.entrySet()) {
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
		    BlobKey blobKey = new BlobKey(entry.getKey());
		    blobstoreService.delete(blobKey);
		}
	}
	
	@Override
	public Item getOneItemById(Long id) {
		Item item = ofy().load().type(Item.class).id(id).get();
		return item;
	}

	@Override
	public String getItemsByGroupId(Long id) {
		
		System.out.println("getItemsByGroupId id is " + id);
		
		
		List<Item> items = ofy().load().type(Item.class).filter("groupId", id).list();
		String allItems = new Gson().toJson(items);
		return allItems;
	}

}
