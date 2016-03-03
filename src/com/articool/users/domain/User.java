package com.articool.users.domain;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {

	@Id
	public Long id;
	@Index
	public String vendorId;
	public String name;
	public String email;
	public String given_name;
	public String family_name;
	public String picture;
	public String gender;
	public String locale;
	public String vendor;

	public User() {
	}

	/* Json constructor */
	@SuppressWarnings("unchecked")
	public User(JsonObject jsonUsers) {
		super();
		this.vendorId = jsonUsers.get("vendorId").getAsString();
		this.name = jsonUsers.get("name").getAsString();
		this.email = jsonUsers.get("email").getAsString();
		this.given_name = jsonUsers.get("given_name").getAsString();
		this.family_name = jsonUsers.get("family_name").getAsString();
		this.picture = jsonUsers.get("picture").getAsString();
		this.gender = jsonUsers.get("gender").getAsString();
		this.locale = jsonUsers.get("locale").getAsString();
		this.vendor = jsonUsers.get("vendor").getAsString();
	}

	public User(Long id, JsonObject jsonUsers) {
		super();

		this.id = id;
		this.vendorId = jsonUsers.get("vendorId").getAsString();
		this.name = jsonUsers.get("name").getAsString();
		this.email = jsonUsers.get("email").getAsString();
		this.given_name = jsonUsers.get("given_name").getAsString();
		this.family_name = jsonUsers.get("family_name").getAsString();
		this.picture = jsonUsers.get("picture").getAsString();
		this.gender = jsonUsers.get("gender").getAsString();
		this.locale = jsonUsers.get("locale").getAsString();
		this.vendor = jsonUsers.get("vendor").getAsString();
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

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
