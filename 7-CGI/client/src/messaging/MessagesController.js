(function() {
	'use strict';
	
	var messageModule = angular.module('cgiAdpq.message');
	
		messageModule.controller('MessagesController', ['$scope', '$rootScope', 'userService', 'postman', '$state', '$stateParams', 'AUTH_EVENTS', 'authService', 'familyService', 'messageService', 'gettextCatalog',
									    		function($scope,   $rootScope,   userService,   postman,   $state,   $stateParams,   AUTH_EVENTS,   authService,   familyService,   messageService,   gettextCatalog) {	
			
			$scope.messageId = $stateParams.id;
			$scope.message = null;
			
			$scope.messageType = 'received';
										    	
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

				    if (typeof $scope.messageId !== 'undefined' && $scope.messageId !== '') {
					    $scope.message = _(messages).find(function(ele) {
						    return ele.id == $scope.messageId;
					    });
					    
					    if ($scope.message !== null) {
						    $scope.message.isRead = true;
						    messageService.markRead($scope.message.id);
					    }
				    }
			    });
			};
			
			$scope.newMessage = null;
			$scope.createNewMessage = function(message) {
				$scope.newMessage = {
					from: $scope.user.firstName + ' ' + $scope.user.lastName,
					subject: '',
					body: ''	
				};
				
				$scope.messageDate = new Date();
				
				if (typeof message !== 'undefined') {
					var toId = authService.getUserId !== message.fromId ? message.fromId : message.toId;
					var fromId = authService.getUserId === message.fromId ? message.fromId : message.toId;
				
					$scope.newMessage.to = toId;
					$scope.newMessage.fromId = fromId;
				} else {
					$scope.newMessage.to = 3;
					$scope.newMessage.fromId = authService.getUserId();
				}
				
				if (typeof message !== 'undefined') {
					var prefix = message.subject.indexOf('Re') < 0 ? 'Re: ' : '';
					$scope.newMessage.subject =  prefix + message.subject;
				}
				
				console.log(message, $scope.newMessage);
			};
			
			$scope.sendMessage = function(message) {
				if (message.subject === '') {
					postman.warn(gettextCatalog.getString('Subject required'));
				} else if (message.body === '') {
					postman.warn(gettextCatalog.getString('Body required'));
				} else {
				
					messageService.sendMessage(message).then(function() {
						postman.success(gettextCatalog.getString('Message sent'));
						$scope.newMessage = null;						
						$state.go('messages', {id:null});

						$scope.getMessages($scope.$parent.userData.id);
						
					}, function() {
						postman.error(gettextCatalog.getString('Your message could not be sent'));
					});
				}			
			};
			
			$scope.user = {};
			familyService.getUser(authService.getUserId()).then(function(data) {
				$scope.user = data;
				$scope.getMessages($scope.user.id);
			});
		}]);
})();