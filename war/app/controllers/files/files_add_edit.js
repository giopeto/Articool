'use strict';

/* Files Controller */

gifts.lazy.controller('filesCtrl', function($scope, $log, $routeParams, $http) {

	var filesScope = this;
	filesScope.item = {};
	
	$http.get('itemsController?id='+ $routeParams.itemId, {
		data: {id: $routeParams.itemId},
		headers: {
			   'Content-Type': 'application/json'
			 },
	}).success(function(data) {
		angular.copy(data, filesScope.item);
	}).error(function(error) {
		$log.log("ERROR: "+error);
	});
	
	
	
});