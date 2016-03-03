'use strict';

/* Groups Controller */

gifts.lazy.controller('loginCtrl', function($scope, $log, $http, $auth) {

	var loginScope = this;
	loginScope.picture = '';
	loginScope.accessToken = '';
	loginScope.authenticate = function (provider) {
		
		$auth.authenticate(provider)
		  .then(function(response) {
			  $log.log ($auth.isAuthenticated());
			 // exchangeCodeForToken(response.config.data);
			  
			  $log.log (response);
			  
			  $log.log("Save also token to user and return here yhen to auth.setToken or something similar.");
			  
			  $http.get('usersController?vendorId='+response.data.vendorId+"&provider="+provider, {
				}).success(function(data) {
					  $log.log ("User: ",data);
					  
					  loginScope.picture = data.picture;
					  loginScope.name = data.name;
					  
					  
				}).error(function(error) {
					$log.log("ERROR: "+error);
				});
			  
		  })
		  .catch(function(response) {
		    // Something went wrong.
		  });
		
	};
	

	loginScope.logout = function () {
		
		var logOutUrl = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="+loginScope.accessToken;
		$http.post(logOutUrl, {	
		}).success(function(response) {
		}).error(function(error) {
			$log.log("ERROR: ", error);
		});
		
		
		
	}
	
	
	
});