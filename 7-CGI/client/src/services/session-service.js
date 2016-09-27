(function() {
	'use strict';
	
	var userModule = angular.module('cgiAdpq.user');
	
	userModule.service('session', ['$localStorage', function ($localStorage) {
		this.data = {};
		this.create = function (data) {
			var expiration = new Date();
			expiration.setDate(expiration.getDate() + 7);
			
			this.data = {
				sessionId: data.sessionId,
				userId: data.userId,
				userRole: data.userRole,
				expiration: expiration.getTime()
			};
			
			$localStorage.sessionData = this.data;
		};
		
		this.destroy = function () {
			this.data = {};

			delete $localStorage.sessionData;
		};
	}]);
})();