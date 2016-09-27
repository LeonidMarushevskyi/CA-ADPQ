(function() {
	'use strict';
	
	var facilityModule = angular.module('cgiAdpq.facility');
	
		facilityModule.controller('FacilityController', ['$scope', '$rootScope', 'userService', 'postman', '$state', 'AUTH_EVENTS', 'facilityService',
									    	 	 function($scope,   $rootScope,   userService,   postman,   $state,   AUTH_EVENTS,   facilityService) {	
			$scope.user = {};
			
			if ($scope.$parent.userData !== null) {
				$scope.user = angular.copy($scope.$parent.userData);
			} else {
				$rootScope.$on(AUTH_EVENTS.userInfo, function(event, data){					
					$scope.user = angular.copy(data);
				});
			}
			
			$scope.facilities = [];
			$scope.getFacilities = function(zipcode) {
				facilityService.getFacilities(zipcode).then(function(facilities) {
					$scope.facilities = facilities;
				});
			};
			
			$scope.input = { zipcode: '' };
			$scope.$watch('input.zipcode', function() {
				if ($scope.input.zipcode.length === 5) {
					$scope.getFacilities($scope.input.zipcode);
				}
			});
	}]);
})();