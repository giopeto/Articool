package com.articool.user.domain;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {
	
	@Id public Long id;
	public String vendorId;
	public String name;
	public String  email;
	public String given_name;
	public String family_name;
	public String link;
	public String picture;
	public String gender;
	public String locale;
	public String vendor;
	@Index public String vendorCode;
	
	
	/*Save: {"id":"111802934228760900008",
	"email":"georg3georgiev@gmail.com",
	"verified_email":true,
	"name":"Георги Георгиев",
	"given_name":"Георги",
	"family_name":"Георгиев",
	"link":"https://plus.google.com/111802934228760900008",
	"picture":"https://lh4.googleusercontent.com/-TcOP0UmM8uQ/AAAAAAAAAAI/AAAAAAAAAx4/WekMEQkHFqw/photo.jpg",
	"gender":"male",
	"locale":"bg"}
*/
	
	
	public User() {}
	
	/*Json constructor*/
	@SuppressWarnings("unchecked")
	public User(JsonObject jsonUsers) {
		super();
		
			System.out.println("type of : " + jsonUsers.get("id").getClass());
			
			
			
			
			this.vendorId = jsonUsers.get("id").getAsString();
			this.name = jsonUsers.get("name").getAsString();
			this.email = jsonUsers.get("email").getAsString();
			this.given_name = jsonUsers.get("given_name").getAsString();
			this.family_name = jsonUsers.get("family_name").getAsString();
			this.link = jsonUsers.get("link").getAsString();
			this.picture = jsonUsers.get("picture").getAsString();
			this.gender = jsonUsers.get("gender").getAsString();
			this.locale = jsonUsers.get("locale").getAsString();
			this.vendor = jsonUsers.get("vendor").getAsString();
			this.vendorCode = jsonUsers.get("vendorCode").getAsString();
			
			
			/*this.id = (Long) Long.valueOf((String) jsonUsers.get("id")).longValue();
			this.name = (String) jsonUsers.get("name");
			this.email = (String) jsonUsers.get("email");
			this.given_name = (String) jsonUsers.get("given_name");
			this.family_name = (String) jsonUsers.get("family_name");
			this.link = (String) jsonUsers.get("link");
			this.picture = (String) jsonUsers.get("picture");
			this.gender = (String) jsonUsers.get("gender");
			this.locale = (String) jsonUsers.get("locale");
			this.vendor = (String) jsonUsers.get("vendor");
			this.vendorCode = (String) jsonUsers.get("vendorCode");*/
		
			
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	
	
	
	
	
}
