package com.articool.items.domain;

/*import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;*/
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
/*import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;*/


import com.googlecode.objectify.annotation.Index;

import java.lang.String;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Item {

	@Id public Long id;
	public String name;
	@Index public Long groupId;
	public String shortDescription;
	public String description;
	public Double price;
	public boolean archive;
	@Index public Date date;
	
	public  Map<String, String> fileIds = new HashMap<>();
	
	public Item(Long id, Long groupId, String name, Date date) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.name = name;
		this.date = date;
	}
	
	public Item() {}
	
	/*Json constructor*/
	@SuppressWarnings("unchecked")
	public Item(JsonObject jsonItems) {
		super();
		
		Type collectionType = new TypeToken<Map<String,String>>(){}.getType();
		
		this.name = jsonItems.get("name").getAsString();
		this.groupId = jsonItems.get("groupId").getAsLong();
		this.price = jsonItems.get("price").getAsDouble();
		this.shortDescription = jsonItems.get("groupId").getAsString();
		this.description = jsonItems.get("groupId").getAsString();
		this.fileIds = new Gson().fromJson(jsonItems.get("fileIds"), collectionType);
		this.date = new Date();
		
		
		
		/*try {
			//this.name = (String) jsonItems.get("name");
			//this.groupId = (Long) Long.valueOf((String) jsonItems.get("groupId")).longValue();
			
			setJsonGroupId(jsonItems);
			
			this.price = setJsonPrice(jsonItems);
			this.shortDescription = (String) jsonItems.get("shortDescription");
			this.description = (String) jsonItems.get("description");
			this.fileIds = setJsonFileIds(jsonItems);
			this.date = new Date();
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
			
	}
	
	public Item(Long id, JsonObject jsonItems) {
		super();
		
		System.out.println(jsonItems);
		
		
			this.id = id;
			Type collectionType = new TypeToken<Map<String,String>>(){}.getType();
			
			this.name = jsonItems.get("name").getAsString();
			this.groupId = jsonItems.get("groupId").getAsLong();
			this.price = jsonItems.get("price").getAsDouble();
			this.shortDescription = jsonItems.get("shortDescription").getAsString();
			this.description = jsonItems.get("description").getAsString();
			this.fileIds = new Gson().fromJson(jsonItems.get("fileIds"), collectionType);
			this.date = new Date();
	}
	
	
	
	/*private long setJsonGroupId(JSONObject jsonItems) {
		
		Long thisGroupId = null;
		
		try {
			if (jsonItems.get("groupId") instanceof String) {
				thisGroupId= (Long) Long.valueOf((String) jsonItems.get("groupId")).longValue();
			}
			if (jsonItems.get("groupId") instanceof Integer) {
				Integer intGropId = (Integer) jsonItems.get("groupId");
				thisGroupId = intGropId.longValue();
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return thisGroupId;
	}

	private Double setJsonPrice(JSONObject jsonItems) {
		
		Double thisPrice = 0.00;
		
		try {
			if (jsonItems.get("price") instanceof String) {
				thisPrice= (Double) Double.parseDouble((String) jsonItems.get("price"));
			}
			if (jsonItems.get("price") instanceof Integer) {
				Integer intPrice = (Integer) jsonItems.get("price");
				thisPrice = intPrice.doubleValue();
			}
			if (jsonItems.get("price") instanceof Double) {
				thisPrice = (Double) jsonItems.get("price");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return thisPrice;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getFileIds() {
		return fileIds;
	}

	public void setFileIds(Map<String, String> fileIds) {
		this.fileIds = fileIds;
	}
	
	/*public Map<String, String> setJsonFileIds(JSONObject jsonItems) {
		Gson gson=new Gson(); 
		String json = null;
		try {
			json = (String) jsonItems.get("fileIds").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> mapFileIds=new HashMap<String,String>();
		mapFileIds=(Map<String,String>) gson.fromJson(json, mapFileIds.getClass());
		
		return this.fileIds = (Map<String, String>) mapFileIds;
	}*/
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", groupId=" + groupId
				+ ", shortDescription=" + shortDescription + ", description="
				+ description + ", price=" + price + ", archive=" + archive
				+ ", date=" + date + ", fileIds=" + fileIds + "]";
	}

	
	
}










/*public Item(Long id, String name, String shortDescription, String description,  Map<String, String>fileIds) {
super();
this.id = id;
this.name = name;
this.shortDescription = shortDescription;
this.description = description;
this.fileIds = fileIds;
this.date = new Date();
}

public Item(String name, String shortDescription, String description,  Map<String, String> fileIds) {
super();
this.name = name;
this.shortDescription = shortDescription;
this.description = description;
this.fileIds = fileIds;
this.date = new Date();
}*/



