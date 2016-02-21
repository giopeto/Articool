'use strict';

/* Items Controller */

gifts.lazy.controller('listsCtrl', function($scope, $log, $http, $routeParams) {

	var listsScope = this;
	listsScope.showAllItem = true;
	listsScope.allItems = [];
	listsScope.item = {};
	listsScope.isLoading = true;
	var groupId;
	if ($routeParams.groupId) {
		groupId = $routeParams.groupId;
	}
	
	$http.get('itemsController?groupId='+groupId, {
		data: {}
	}).success(function(data) {
		var arr = [];
		data.forEach(function(value, key) {
			angular.forEach(value.fileIds, function (fileValue, fileKey){
				arr[parseInt(fileValue)] = fileKey;
			});
			value.aFileIds = arr.filter(function(n){ return n != undefined });
			arr = [];
		});
		listsScope.allItems = data;
		listsScope.isLoading = false;
	}).error(function(error) {
		$log.log("ERROR: "+error);
		listsScope.isLoading = false;
	});
	
	listsScope.showSingleItem = function (args) {
		listsScope.item = args.thisItem;
		listsScope.showAllItem = false;
	}
	
});