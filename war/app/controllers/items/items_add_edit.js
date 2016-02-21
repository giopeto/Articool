'use strict';

/* Items Controller */

gifts.lazy.controller('itemsCtrl', function($scope, $log, $http) {

	var itemsScope = this;
	var emptyItem = {
		id: 0,
		groupId: 0,
		price: 0,
		name: "",
		shortDescription: "",
		description: "",
		archive: false,
		fileIds: {},
		aFileIds: [],
	};
	itemsScope.errorMsg = {
		groupErr: false,
	};
	itemsScope.showAllItems = true;
	itemsScope.showInTable = false;
	itemsScope.isLoading = true;
	itemsScope.allItems = [];
	itemsScope.item = {};
	itemsScope.groups = [];
	angular.copy(emptyItem, itemsScope.item);
	
	
	$http.get('groupsController', {
		data: {}
	}).success(function(data) {
		itemsScope.groups = data;
		
	}).error(function(error) {
		$log.log("ERROR: "+error);
	});
	
	itemsScope.set = function() {
		
		if (!itemsScope.item.groupId) {
			itemsScope.errorMsg.groupErr = true;
			return;
		}
		
		var setMethod = "post";
		if (itemsScope.item.id > 0) {
			setMethod = "put";
		}
		itemsScope.isLoading = true;
		itemsScope.item.fileIds = {};
		itemsScope.item.aFileIds.forEach(function(val, key) {
			itemsScope.item.fileIds[val] = key.toString();
		});
		
		$http[setMethod]('itemsController', {
			data: itemsScope.item
		}).success(function(data) {
			getAllItems();
			itemsScope.showAllItems = true;
			itemsScope.isLoading = false;
			itemsScope.errorMsg.groupErr = false;
		}).error(function(error) {
			$log.log("ERROR: "+error);
			itemsScope.isLoading = false;
		});
	};

	itemsScope.edit = function (args) {
		itemsScope.showAllItems = false;
		angular.copy(emptyItem, itemsScope.item);
		angular.copy(args.thisItem, itemsScope.item);
	};
	
	var getAllItems = function () {
		itemsScope.isLoading = true;
		$http.get('itemsController', {
			data: {}
		}).success(function(data) {
			var arr = [];
			data.forEach(function(value, key) {
				value.groupId = String(value.groupId);
				angular.forEach(value.fileIds, function (fileValue, fileKey){
					arr[parseInt(fileValue)] = fileKey;
				});
				value.aFileIds = arr.filter(function(n){ return n != undefined });
				arr = [];
			});
			itemsScope.allItems = data;
			itemsScope.isLoading = false;
		}).error(function(error) {
			$log.log("ERROR: "+error);
			itemsScope.isLoading = false;
		});
	}
	
	itemsScope.prepareSetUI = function () {
		angular.copy(emptyItem, itemsScope.item);
		itemsScope.showAllItems = false;
	};
	
	itemsScope.changeFilesOrder = function (args) {
		var index = args.index;
		var tmpArr = [];
		angular.copy(itemsScope.item.aFileIds, tmpArr);
		
		if (args.direction === 'up') {
			tmpArr[args.index] = itemsScope.item.aFileIds[args.index-1];
			tmpArr[args.index-1] = itemsScope.item.aFileIds[args.index];
		} else {
			tmpArr[args.index] = itemsScope.item.aFileIds[args.index+1];
			tmpArr[args.index+1] = itemsScope.item.aFileIds[args.index];
		}
		
		itemsScope.item.aFileIds = tmpArr;
	};
	
	itemsScope.removeFile = function (args) {
		itemsScope.item.aFileIds.splice(args.index, 1);
	};
	
	
	itemsScope.delete = function(args) {
		if(confirm("Are you sure ? ")) {
			itemsScope.isLoading = true;
			$http.delete('itemsController?id='+args.id, {
				//data: args
			}).success(function(data) {
				getAllItems();
				itemsScope.isLoading = false;
			}).error(function(error) {
				$log.log("ERROR: "+error);
				itemsScope.isLoading = false;
			});
		}
	};
	
	getAllItems();
});