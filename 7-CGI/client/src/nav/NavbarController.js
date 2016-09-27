(function() {
	'use strict';
	
	var navModule = angular.module('cgiAdpq.nav');
	
	navModule.controller('NavBarController', [
				'$scope', '$rootScope', '$state', 'userService',
		function($scope,   $rootScope,   $state,   userService) {
			$rootScope.$on('$stateChangeStart', function() {
				$scope.navCollapsed = true;
			});
			
			$rootScope.$on('user-info-returned', function(event, data) {

			});
			
			$scope.isActive = function(linkName) {
				return 	$state.current.name === linkName ? 'active' : ''; 
			};
			
			$scope.collapse = function() {
				$scope.navCollapsed = true;
			};
			
			$scope.gotoPage = function() {
				$state.go($scope.$parent.page, {id:null});
			};
		}
	]);
})();