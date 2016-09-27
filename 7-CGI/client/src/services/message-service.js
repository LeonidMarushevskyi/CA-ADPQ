(function() {
	'use strict';
	
	var messageModule = angular.module('cgiAdpq.message');
	
	messageModule.factory('messageService', ['$http', '$q', 'session', 'ENDPOINTS',
								    function ($http,   $q,   session,   ENDPOINTS) {
		var service = {
			getMessages: function(userId) {
				var senderLookup = {
					'10': 'Ann Trason',
					'11': 'CA CareMail'
					
				};
				
				return $http.get(ENDPOINTS.messaging + 'get/' + userId).then(function(response) {
					var messages = response.data;
					
					for (var i = 0; i < messages.length; i++) {
						var senderKey = messages[i].fromId == session.data.userId ? messages[i].toId : messages[i].fromId;
						//messages[i].id = i + 1;
						messages[i].from = senderLookup[senderKey];
						//messages[i].date = getDate();
					}
					
					return messages;
				});
				
				
				function getDate() {
					var dates = [
						'2016-05-22',
						'2016-04-22',						
						'2016-05-24',						
						'2016-05-27',
					];
					
					
					var date = dates[Math.floor(Math.random() * dates.length)];
					
					return date;
				}
			},
			
			markRead: function(messageId) {
				return $http.get(ENDPOINTS.messaging + 'markRead/' + messageId).then(function(response) {
					
				});
			},
			
			sendMessage: function(message) {
				var messageToSend = angular.copy(message);
				
				messageToSend.from =  messageToSend.fromId;
				
				delete messageToSend.fromId;
				
				return $http.post(ENDPOINTS.messaging + 'send', messageToSend).then(function(response) {
					console.log(response);
				});
			}
		};
		
		
		return service;
	}]);
})();