package com.articool.user.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.articool.items.domain.Item;
import com.articool.user.domain.User;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.VoidWork;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Work;
public class UserServiceImpl implements UserService {

	@Override
	public void set(JsonObject oathUserResponse) {
		
		System.out.println("in set service");
		
		final User user = new User(oathUserResponse);
		
		/*System.out.println(user.getName());
		
		ofy().save().entity(user).now();
		
		System.out.println("EKKKKKKKKKKKKKKKKKK");
		assert user.id != null;
		System.out.println("EKKKKKKKKKKKKKKKKKK");*/
		
		ofy().save().entity(user).now();
		User checkUser = ofy().load().type(User.class).id(user.id).get();
		
		/*ofy().transact(new VoidWork() {
		    public void vrun() {
		    	// ofy().save().entity(user).now();
		    	
		    	Result<Key<User>> result = ofy().save().entity(user);
				assert user.id == null;    // It has not been generated yet!
				result.now();
				assert user.id != null;    // Now the id is available
		    	
		    }
		});*/
		
		

		/*// If you don't need to return a value, you can use VoidWork
		User th = ofy().transact(new Work<User>() {
		    public User run() {
		    	
		        ofy().save().entity(user);
		        return thing;
		    }
		});
		
		

		Result<Key<User>> result = ofy().save().entity(user);*/
		/*assert user.id == null;    // It has not been generated yet!
		result.now();
		assert user.id != null;    // Now the id is available
*/		
		
		
		//return oathUserResponse;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getOneUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOneUserByVendorCode(String code) {
		System.out.println("vendor code is " + code);
		
		
		List<User> listUser = ofy().load().type(User.class).filter("vendorCode", code).list();
		String user = new Gson().toJson(listUser);
		
		System.out.println(user);
		
		return user;
	}

	

}
