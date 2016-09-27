(function() {
	'use strict';
	
	var mainModule = angular.module('cgiAdpq.main');
	
	mainModule.controller('HomeController', ['$scope', '$rootScope', 'postman', '$state', 'gettextCatalog', '$localStorage', 'LOCALES', 'messageService', 'eventService', 'familyService', 'AUTH_EVENTS', 'authService',
									  function($scope,  $rootScope,   postman,   $state,   gettextCatalog,   $localStorage,   LOCALES,   messageService,   eventService,   familyService,   AUTH_EVENTS,   authService) {	
		
		function bootstrap() {
			$scope.getMessages($scope.user.id);
			$scope.getPlans($scope.user.id);
			$scope.getEvents($scope.user.id);
		}
				
		$scope.unreadCount = 0;
		$scope.messages = [];
		$scope.getMessages = function(id) {
			messageService.getMessages(id).then(function(messages) {
				$scope.messages = messages;

				var count = 0;
				angular.forEach($scope.messages, function(message){
			        count += message.isRead ? 0 : 1;
			    });			
			    $scope.unreadCount = count;
		    });
		};
		
		
		$scope.plans = [];
		$scope.getPlans = function(id) {
			familyService.getPlans($scope.user.id).then(function(plans) {
				$scope.plans = plans;
			});
		};	
		
		
		$scope.events = [];
		$scope.getEvents = function(id) {
			eventService.getEvents(id).then(function(data) {
				var events = {};
				data.forEach(function(ele) {
				  if (!events.hasOwnProperty(ele.eventFor)) {
				    events[ele.eventFor] = [];
				  }
				  
				  events[ele.eventFor].push(ele);
				});
				
				$scope.events = data;
			});
		};
		
		familyService.getUser(authService.getUserId()).then(function(data) {
			$scope.user = data;
			bootstrap();
		});		
	}]);
})();