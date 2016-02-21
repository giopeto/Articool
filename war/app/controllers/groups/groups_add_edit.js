'use strict';

/* Groups Controller */

gifts.lazy.controller('groupsCtrl', function($scope, $log, $http) {

	var groupsScope = this;
	groupsScope.showAll = true;
	groupsScope.group = {};
	groupsScope.isLoading = true;
	
	groupsScope.set = function() {
		
		var setMethod = "post";
		if (groupsScope.group.id > 0) {
			setMethod = "put";
		}
		groupsScope.isLoading = true;
		
		$http[setMethod]('groupsController', {
			data: groupsScope.group
		}).success(function(data) {
			groupsScope.showAll = true;
			groupsScope.isLoading = false;
		}).error(function(error) {
			$log.log("ERROR: "+error);
			groupsScope.isLoading = false;
		});
	};

	groupsScope.edit = function (args) {
		groupsScope.showAll = false;
		//angular.copy(emptygroup, groupsScope.group);
		angular.copy(args.thisGroup, groupsScope.group);
	};
	
	var get = function () {
		groupsScope.isLoading = true;
		$http.get('groupsController', {
			data: {}
		}).success(function(data) {
			groupsScope.all = data;
			groupsScope.isLoading = false;
		}).error(function(error) {
			$log.log("ERROR: "+error);
			groupsScope.isLoading = false;
		});
	};
	
	
	groupsScope.delete = function(args) {
		if(confirm("Are you sure ? ")) {
			groupsScope.isLoading = true;
			$http.delete('groupsController?id='+args.id, {
				//data: args
			}).success(function(data) {
				get();
				groupsScope.isLoading = false;
			}).error(function(error) {
				$log.log("ERROR: "+error);
				groupsScope.isLoading = false;
			});
		}
	};
	
	groupsScope.prepareSetUI = function () {
		angular.copy({}, groupsScope.group);
		groupsScope.showAll = false;
	};
	
	get();
});