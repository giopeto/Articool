package com.articool.users.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.articool.items.domain.Item;
import com.articool.users.domain.User;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.VoidWork;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Work;
public class UserServiceImpl implements UserService {

	@Override
	public void set(JsonObject oathUserResponse) {
		
		System.out.println("******************  in set service ********************************");
		
		System.out.println(oathUserResponse);
		
		final User user = new User(oathUserResponse);
		
		System.out.println(oathUserResponse);
		String checkUser = getOneUserByVendorId(user.getVendorId());
		
		if (checkUser != null && !checkUser.isEmpty()) {
			
			
			JsonParser parser = new JsonParser();
			
			JsonObject o = parser.parse(checkUser).getAsJsonObject();
			
			final User checkedJvaUser = new User(o.get("id").getAsLong()  , o);
			
			System.out.println(checkedJvaUser.getVendorId() + ",new: " + user.getVendorId());
			
			if(checkedJvaUser.getVendorId().equals(user.getVendorId())) {
				System.out.println("equalll" + checkedJvaUser.getId());
				user.setId(checkedJvaUser.getId());
			}
		}
		
		
		ofy().transact(new VoidWork() {
		    public void vrun() {
		    	ofy().save().entity(user).now();
				ofy().load().type(User.class).id(user.id).get();
		    }
		    
		});
		 System.out.println("******************  in set service ********************************");
		

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
	public String getOneUserByVendorId(String vendorId) {
		
		System.out.println("getOneUserByVendorId"+vendorId);
		
		List<User> listUser = ofy().load().type(User.class).filter("vendorId", vendorId).list();
		
		//System.out.println(listUser.get(0));
		String user = null;
		if (!listUser.isEmpty()) {
			 user = new Gson().toJson(listUser.get(0));
		}
		
		System.out.println(user);
		
		return user;
	}

	/*@Override
	public String getOneUserByVendorCode(String code) {
		System.out.println("vendor code is " + code);
		NOT USED REMO
		
		System.out.println(": " + code);
		
		List<User> listUser = ofy().load().type(User.class).filter("vendorCode", code).list();
		String user = new Gson().toJson(listUser);
		
		System.out.println(user);
		
		return user;
	}*/

	

}
