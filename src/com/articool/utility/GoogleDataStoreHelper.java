package com.articool.utility;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GoogleDataStoreHelper {
	
	public void indexDataStore(Class entityClass, Long id) {
		ofy().load().type(entityClass).filter("id", id).first().get();
	}
	
}
