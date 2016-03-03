package com.articool.groups.domain;

/*import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;*/
import com.google.gson.JsonObject;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
/*import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;*/


@Entity
public class Group {

	@Id public Long id;
	public String name;
	
	public Group(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Group() {}
	
	/*Json constructor*/
	public Group(JsonObject jsonGroups) {
		super();
		this.name = jsonGroups.get("name").getAsString();
	}
	
	public Group(Long id, JsonObject jsonGroups) {
		super();
		this.id = id;
		this.name = jsonGroups.get("name").getAsString();
	}
	
	
	public Group(String name) {
		super();
		this.name = name;
	}

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


	

	

	
}