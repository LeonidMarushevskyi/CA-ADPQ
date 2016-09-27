(function() {
	'use strict';
	
	var userModule = angular.module('cgiAdpq.user');
	
		userModule.controller('ProfileController', ['$scope', '$rootScope', 'userService', 'facilityService', 'postman', '$state', 'AUTH_EVENTS', 'authService', 'familyService',
									    	function($scope,   $rootScope,   userService,   facilityService,   postman,   $state,   AUTH_EVENTS,   authService,   familyService) {	
			$scope.user = {};
						
			$scope.family = [];
			$scope.getFamily = function(userId) {
				familyService.getFamily(userId).then(function(family) {
					$scope.family = family;
				});
			};
			
			
			$rootScope.$on(AUTH_EVENTS.logoutSuccess, function() {
				$scope.user = {};
			});
			
			familyService.getUser(authService.getUserId()).then(function(data) {
				$scope.user = data;
				$scope.getFamily($scope.user.id);
			});
			
			
			$scope.facilities = [];
			$scope.input = { zipcode: '' };
			$scope.getFacilities = function(zipcode) {
				facilityService.getFacilities(zipcode).then(function(facilities) {
					$scope.facilities = facilities;
				
					$scope.resultsReturned = true;
				});
			};
			
			$scope.$watch('input.zipcode', function() {
				if ($scope.input.zipcode.length === 5) {
					$scope.getFacilities($scope.input.zipcode);
				}
			});

	}]);
})();