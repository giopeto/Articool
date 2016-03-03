//Define an angular module for our app
var gifts = angular.module('gifts', ['ngRoute', 'ngResource', 'satellizer']);

gifts.controller('appCtrl', function($scope,  $http) {
	$http.get('groupsController', {
		data: {}
	}).success(function(data) {
		$scope.groups = data;
	}).error(function(error) {
		$log.log("ERROR: "+error);
	});
	
});


/*gifts.controller('AuthCtrl', ['$scope', 'GooglePlus', function ($scope, GooglePlus) {
       console.log (GooglePlus);


      $scope.onSignIn = function(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log("Name: " + profile.getName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());
        $scope.imgUrl =  profile.getImageUrl();

        
        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
      };

        $scope.login = function () {
          GooglePlus.login().then(function (authResult) {
              console.log(authResult);
  
              GooglePlus.getUser().then(function (user) {
                  console.log(user);
              });

              GooglePlus.logout().then(function (user) {
                  console.log(user);
              });

              GooglePlus.getUser().then(function (user) {
                  console.log(user);
              });

          }, function (err) {
              console.log(err);
          });
        };
    }])
*/

gifts.config(function ($controllerProvider, $compileProvider, $filterProvider, $provide, $routeProvider, $httpProvider, $locationProvider, $authProvider) {
	
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$locationProvider.html5Mode = true;
	gifts.lazy = {
        controller: $controllerProvider.register,
       	directive: $compileProvider.directive,
        filter: $filterProvider.register,
        factory: $provide.factory,
        service: $provide.service
    };

  


    $authProvider.google({
		 clientId:"1076037111884-5gn09vbr8k5vpvhd3uu2ig7a19g5jm97.apps.googleusercontent.com",  
		 url: '/auth/google',
		  authorizationEndpoint: 'https://accounts.google.com/o/oauth2/auth',
		  
		  redirectUri: window.location.origin,
		  requiredUrlParams: ['scope'],
		  optionalUrlParams: ['display'],
		  scope: ['profile', 'email'],
		  scopePrefix: 'openid',
		  scopeDelimiter: ' ',
		  display: 'popup',
		  type: '2.0',
		  popupOptions: { width: 452, height: 633 }
		});
	
    $authProvider.facebook({
        clientId: '498729053585609'
      });


    $routeProvider.when('/items_add_edit', {
		templateUrl: 'app/templates/items/items_add_edit.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					/*'app/directives/file_model.js',*/
					'app/controllers/items/items_add_edit.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/items_list', {
		templateUrl: 'app/templates/items/items_list.html',
		
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/items/items_list.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/items_list/:groupId', {
		templateUrl: 'app/templates/items/items_list.html',
		
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/items/items_list.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/files_add_edit/:itemId', {
		templateUrl: 'app/templates/files/files_add_edit.jsp',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/files/files_add_edit.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/groups_add_edit', {
		templateUrl: 'app/templates/groups/groups_add_edit.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/groups/groups_add_edit.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}

		}).when('/login', {
		templateUrl: 'app/templates/auth/login.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/auth/login.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}

	}).otherwise({
		redirectTo: '/items_list',
	});

    
    
    


});