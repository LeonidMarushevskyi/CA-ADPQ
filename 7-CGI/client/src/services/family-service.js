(function() {
	'use strict';
	
	var userModule = angular.module('cgiAdpq.user');
	
	userModule.factory('familyService', ['$http', '$q', '$rootScope', '$localStorage', 'gettextCatalog', 'session', 'AUTH_EVENTS', 'ENDPOINTS',
								function ($http,   $q,   $rootScope,   $localStorage,   gettextCatalog,   session,   AUTH_EVENTS,   ENDPOINTS) {	
		var caseworker = {
			name: 'Ann Trason',
			email: 'atrason@foster.ca.gov',
			phone:  '(408) 299-5437'
		};
		
		var items = [
			{
				id: 1,
				serviceType: 'Service 1',
				explanation: 'Explanation',
				provider: 'Providence',
				frequency: 'Weekly'
			},
			{
				id: 2,
				serviceType: 'Service 2',
				explanation: 'Explanation',
				provider: 'Moda',
				frequency: '5/1/2015 - 11/23/16'
			}
		];

		
		var comments = [
			{
				from: 'Amy Treyvan',
				date: '2016-03-22',
				body: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et sem eu est tristique condimentum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras porta massa sit amet lectus mollis, aliquam placerat risus porttitor. Vivamus pulvinar ligula ac magna volutpat, at laoreet velit consectetur. Morbi eleifend et mauris ac tempus. Ut nec lacus tellus. In mollis nisi pulvinar, congue diam et, volutpat sem. In hac'
			},
			{
				from: 'John Treyvan',
				date: '2016-03-23',
				body: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et sem eu est tristique condimentum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras porta massa sit amet lectus mollis, aliquam placerat risus porttitor. Vivamus pulvinar ligula ac magna volutpat, at laoreet velit consectetur. Morbi eleifend et mauris ac tempus. Ut nec lacus tellus. In mollis nisi pulvinar, congue diam et, volutpat sem. In hac'
			},
			{
				from: 'Lori Smith',
				date: '2016-04-22',
				body: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et sem eu est tristique condimentum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras porta massa sit amet lectus mollis, aliquam placerat risus porttitor. Vivamus pulvinar ligula ac magna volutpat, at laoreet velit consectetur. Morbi eleifend et mauris ac tempus. Ut nec lacus tellus. In mollis nisi pulvinar, congue diam et, volutpat sem. In hac'
			}
		];
		
		var emailLookup = {
			'janeausten@lighthouse.com': 1,
			'maria@emailaddress.com': 2,
			'lewissmith@emailaddress.com': 3,
			'atrason@foster.ca.gov': 10,
			'ashley@email.com': 14,
			'mike@email.com': 15,
			'tim@email.com': 16
		};
		
		function getProfilePicture() {
			var pictures = [
				'https://dl.dropboxusercontent.com/s/9f3kny5kq0saalh/u322.png',
				'https://dl.dropboxusercontent.com/s/xifwhewttibrweq/u480.png',
				'https://dl.dropboxusercontent.com/s/6fcdjqaufim9njo/u298.png',
				'https://dl.dropboxusercontent.com/s/nou3oes1k6snx8a/u342.png'
			];
			
			
			var picture = pictures[Math.floor(Math.random() * pictures.length)];
			
			return picture;
		}

		
		var service = {
			getFamily: function(userId) {
				return $http.get(ENDPOINTS.profile + 'getFamily/' + userId).then(function(response) {
					return response.data;
				});
			},
			
			getPlans: function(userId) {
				return this.getFamily(userId).then(function(data) {
					var plans =  data.filter(function(ele) {
						return ele.hasOwnProperty('hasPlan') && ele.hasPlan === true;
					});
					
					return plans;
				});			
			},
				
			getUserId: function(email) {
				if (emailLookup.hasOwnProperty(email.toLowerCase())) {
					return emailLookup[email.toLowerCase()];
				} else {
					return null;
				}
			},
					
			getUser: function(userId) {
				userId = userId || 1;
				
				var userPromise = $http.get(ENDPOINTS.profile + 'view/' + userId);
				
				return userPromise.then(function(response) {
					var user = response.data;
					
					if (user.id !== session.data.userId) {
						user.items = angular.copy(items);
						
						user.caseworker = caseworker;
						
						if (user.hasOwnProperty('items')) {
							for (var i = 0; i < user.items.length; i++) {
								var key = 'item-comments-' + session.data.userId + '-' + user.id + '-' + user.items[i].id;
								
								if ($localStorage.hasOwnProperty(key)) {
									user.items[i].comments = $localStorage[key];
								} else {
									user.items[i].comments = angular.copy(comments);
								}
							}
						}
					}
					
					return user;
				});
			},
			
			saveUser: function(user) {
				if (session.data.userId != user.id) {
					user.parentId = session.data.userId;
				}
				
				if (typeof(user.parentId) === 'undefined' || user.parentId === null || user.parentId === '') {
					user.parentId = 0;
				}
				
				//extraneous leftover field
				user.group = 1;
				user.address = user.address1;
	
				if (!user.hasOwnProperty('profilePicture') || user.profilePicture === null || user.profilePicture === '') {
					user.profilePicture = getProfilePicture();
				}
				
				if (!user.hasOwnProperty('hasPlan')) {
					user.hasPlan = (!!Math.floor(Math.random() * 2)).toString();
					
					//temp
					user.hasPlan = true;
				}
				
				if (!user.hasOwnProperty('goal')) {
					user.goal = gettextCatalog.getString('Reunification');
				}
				
				var keys = Object.keys(user);
				
				
				//set unset fields to ""
				for (var i = 0; i < keys.length; i++) {
					var key = keys[i];
					
					if (user.hasOwnProperty(key) && user[key] === null) {
						user[key] = '';
					}
				}
				
				var method = 'addProfile';
				if (user.hasOwnProperty('id')) {
					method = 'updateProfile';
				}
				
				var userToSave = angular.copy(user);
				delete userToSave.items;
				delete userToSave.caseworker;
				
				return $http.post(ENDPOINTS.profile + method, userToSave).then(function(response) {
					if (response.data.success) {
						if (session.data.userId == user.id) {
							$rootScope.$broadcast(AUTH_EVENTS.userInfo, userToSave);
						}
						
						return 'success';
					} else {
						return 'error';
					}
				});
			}
		};
		
		
		return service;
	}]);
})();