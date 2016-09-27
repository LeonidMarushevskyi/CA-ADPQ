(function() {
	'use strict';
	
	var userModule = angular.module('cgiAdpq.user');
	
		userModule.controller('EditProfileController', ['$scope', '$rootScope', 'userService', 'postman', '$state', '$stateParams', 'gettextCatalog', 'authService', 'AUTH_EVENTS', 'STATES', 'familyService', 'facilityService',
									    		function($scope,   $rootScope,   userService,   postman,   $state,   $stateParams,   gettextCatalog,   authService,   AUTH_EVENTS,   STATES,   familyService,   facilityService) {	
			$scope.states = STATES;

			$scope.showLocation = false;
			$scope.showFacility = false;
			$scope.showOther = false;
			$scope.resultsReturned = false;
			
			$scope.user = {};			
			function getProfile(userId) {
				familyService.getUser(userId).then(function(user) {
					$scope.user = angular.copy(user);

					if (authService.getUserId() != $scope.user.id) {
						$scope.showLocation = true;
						setupFacilities();
					}
				});
			}
			
			$scope.showRelation = false;
			if ($stateParams.id === 'new') {
				$scope.showLocation = true;
				setupFacilities();
			} else {
				getProfile($stateParams.id);
			}
			
			$scope.save = function(user) {
				
				familyService.saveUser(user).then(function(result) {
					if (result === 'success') {
						postman.success('', gettextCatalog.getString('User updated'));					
					} else {
						postman.error('', gettextCatalog.getString('User could not be updated'));
						return;
					}

					if (authService.getUserId() != $stateParams.id && $stateParams.id != 'new') {
						$state.go('plan', {id: $stateParams.id});
					} else {
						$state.go('profile');
					}
				}, function() {
					postman.error('', gettextCatalog.getString('User could not be updated'));
				});	
			};
			
			$scope.facilities = [];
			$scope.input = { zipcode: '' };
			$scope.getFacilities = function(zipcode) {
				facilityService.getFacilities(zipcode).then(function(facilities) {
					$scope.facilities = facilities;
				
					$scope.resultsReturned = true;
				});
			};
			
			var firstRun = true;
			function setupFacilities() {
				
				$scope.$watch('user.location', function(val, oldVal) {
					if (val == oldVal && !firstRun) {	
						return;	
					} 
					
					firstRun = false;
					
					$scope.showFacility = false;
					$scope.showOther = false;
					
					if (firstRun) {
						//$scope.user.facility = null;
					} 
					
					if (val == 'In Placement') { 
						if ($scope.user.facility !== null && $scope.user.facility !== '') {
							$scope.showOther = true;
						}
						
						if (val != oldVal) {
							$scope.user.facility = '';
						}
						
						$scope.showFacility = true;
					} else if (val == 'Other') {
						$scope.user.facility = '';
						$scope.showOther = true;
					} else if (val == 'In Home') {
						var user = $scope.$parent.userData;
						$scope.showOther = true;
						$scope.user.facility = 'Home ' + $scope.$parent.userData.address1 + ' ' + $scope.$parent.userData.city + ', ' + $scope.$parent.userData.state + ' ' + $scope.$parent.userData.zip;
					}
				});

				$scope.$watch('input.zipcode', function() {
					if ($scope.input.zipcode.length === 5) {
						$scope.getFacilities($scope.input.zipcode);
					}
				});
			}
			
			$scope.selectFacility = function(facility) {
				$scope.user.facility = facility.name;
				$scope.cancelFacilities();	
			};
			
			$scope.cancelFacilities = function() {
				$scope.input.zipcode = '';
				$scope.facilities = [];
				$scope.resultsReturned = false;
			};
	}]);
})();