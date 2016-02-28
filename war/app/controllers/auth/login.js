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
			  
			  $log.log (response.config.data.code);
			  
			  $log.log("Save also token to user and return here yhen to auth.setToken or something similar.");
			  
			 
			  
			  $http.get('usersController?code='+response.config.data.code, {
				}).success(function(data) {
					  $log.log (data);
					  
					  loginScope.picture = data[0].picture;
					  loginScope.name = data[0].name;
					  
					  
				}).error(function(error) {
					$log.log("ERROR: "+error);
					groupsScope.isLoading = false;
				});
			  
			  
			 /*$http.post('auth/google?', {	
				  data: response.config.data
			  }).success(function(response) {
					 $log.log (response);
					 loginScope.picture = response.picture;
					
					 $auth.authenticate('google', [response]);
					 
					 $log.log ($auth.isAuthenticated());
					 $auth.logout();
					 $log.log ($auth.isAuthenticated());
				}).error(function(error) {
					$log.log("ERROR: ", error);
					
				});*/
			  
			  
		  })
		  .catch(function(response) {
		    // Something went wrong.
		  });
		
	};
	
	var exchangeCodeForToken = function (args) {
		
		var exchangeCodeForTokenUrl = "https://www.googleapis.com/oauth2/v4/token";
		var exchangeCodeForTokenQueryStringParams = "?code="+args.code+"&client_id="+args.clientId+"&client_secret=Dw49ctpQtkclU5dxdERIqvnI&grant_type=authorization_code&redirect_uri="+args.redirectUri;
		
		$log.log(exchangeCodeForTokenUrl+exchangeCodeForTokenQueryStringParams);
		
		$http.post(exchangeCodeForTokenUrl+exchangeCodeForTokenQueryStringParams, {	
		}).success(function(response) {
			loginScope.accessToken = response.access_token;
			exchangeTokenForUser(response);
		}).error(function(error) {
			$log.log("ERROR: ", error);
			
		});
	
	};
	
	var exchangeTokenForUser = function (args) {
		
		var exchangeTokenForUserUrl = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="+args.access_token;
		
		$http.get(exchangeTokenForUserUrl, {	
		}).success(function(response) {
			 $log.log (response);
			 loginScope.picture = response.picture;
			
			 $auth.authenticate('google', [response]);
			 
			 $log.log ($auth.isAuthenticated());
			 $auth.logout();
			 $log.log ($auth.isAuthenticated());
		}).error(function(error) {
			$log.log("ERROR: ", error);
			
		});
		
	}
	
	
	loginScope.logout = function () {
		
		var logOutUrl = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="+loginScope.accessToken;
		$http.post(logOutUrl, {	
		}).success(function(response) {
		}).error(function(error) {
			$log.log("ERROR: ", error);
		});
		
		
		
	}
	
	
	
});