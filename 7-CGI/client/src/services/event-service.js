(function() {
	'use strict';
	
	var eventModule = angular.module('cgiAdpq.event');
	
	eventModule.factory('eventService', ['$http', '$q', 'session',
							  function ($http,   $q,   session) {
		var service = {
			getEvents: function(userId) {
				var data = [
					{
						id: 1,
						title: 'Visitation',
						date: '2016-05-22',
						completed: false,
					},
					{
						id: 2,
						title: 'Complete Evaluation',
						date: '2016-04-22',						
						completed: true,
					},
					{
						id: 3,
						title: 'Home Checklist',
						date: '2016-05-24',						
						completed: false,
					},
					{
						id: 4,
						title: 'Group Course',
						date: '2016-05-27',
						completed: false,
					}
				];
				
				var deferred = $q.defer();
				
				deferred.resolve(data);
				
				return deferred.promise;
			}
		};
		
		
		return service;
	}]);
})();