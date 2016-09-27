(function() {
	'use strict';
	
	var userModule = angular.module('cgiAdpq.user');
	
	userModule.controller('LoginController', ['$scope', '$rootScope', 'authService', 'postman', '$state', '$localStorage', 'gettextCatalog', 'familyService', 'AUTH_EVENTS',
									  function($scope,   $rootScope,   authService,   postman,   $state,   $localStorage,   gettextCatalog,   familyService,   AUTH_EVENTS) {	
		$scope.credentials = {
			email: '',
			password: ''
		};
		
		$scope.login = function() { 
			var userId = familyService.getUserId($scope.credentials.email);
			
			if (userId === null) {
				postman.info(gettextCatalog.getString('Use valid email address'));
				return;
			}
			
			if (!$scope.credentials.password) {
				postman.info(gettextCatalog.getString('Password is required'));
				return;
			}
			
			authService.login($scope.credentials).then(function(data) {
				$rootScope.loggedinEmail = $scope.credentials.email;
				
				postman.success(gettextCatalog.getString('Logged in!'));

				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);

				if ($localStorage.nextState) { 
					var nextState = angular.copy($localStorage.nextState);
					delete $localStorage.nextState;
					
					$state.go(nextState.name, nextState.params || {});
				} else {
					$state.go('home');
				}
				
			}, function(data) {
				postman.error('', gettextCatalog.getString('Could not login'));
			});	
		};
	}]);
})();