//Define an angular module for our app
var gifts = angular.module('gifts', ['ngRoute', 'ngResource']);

gifts.controller('appCtrl', function($scope,  $http) {
	$http.get('groupsController', {
		data: {}
	}).success(function(data) {
		$scope.groups = data;
	}).error(function(error) {
		$log.log("ERROR: "+error);
	});
	
});

gifts.config(function ($controllerProvider, $compileProvider, $filterProvider, $provide, $routeProvider, $httpProvider, $locationProvider) {
	
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$locationProvider.html5Mode = true;
	gifts.lazy = {
        controller: $controllerProvider.register,
       	directive: $compileProvider.directive,
        filter: $filterProvider.register,
        factory: $provide.factory,
        service: $provide.service
    };
   
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
	}).otherwise({
		redirectTo: '/items_list',
	});

    
    
    


});