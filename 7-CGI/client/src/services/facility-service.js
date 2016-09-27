(function() {
	'use strict';
	
	var facilityModule = angular.module('cgiAdpq.facility');
	
	facilityModule.factory('facilityService', ['$http', '$q', '$rootScope', 'AUTH_EVENTS', 'ENDPOINTS',
									  function ($http,   $q,   $rootScope,   AUTH_EVENTS,   ENDPOINTS) {
		var service = {
			getFacilities: function(zipcode) {
				return $http.get(ENDPOINTS.facility + zipcode).then(function(response) {
					var data = angular.fromJson(response.data.message);
					
					var results = _.map(data, function(ele) {
						return { name: ele.facility_name, zipcode: ele.facility_zip };
					});
					
					return results;
				});
			}
		};
		
		
		return service;
	}]);
})();