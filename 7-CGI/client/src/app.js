(function() {
	'use strict';
	
	angular.module('cgiAdpq', ['ui.router', 'ui.bootstrap', 'Postman', 'ngStorage', 'gettext', 'cgiAdpq.main', 'cgiAdpq.nav', 'cgiAdpq.user', 'cgiAdpq.message', 'cgiAdpq.event', 'cgiAdpq.plan', 'cgiAdpq.facility'])
		.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', 
		 function($stateProvider,   $urlRouterProvider,   $locationProvider) {  
			$locationProvider.html5Mode(true);
						 
			$stateProvider
				.state('home', {
					url: "/home",
					templateUrl: "main/home.html",
					data: {auth:true}
				})
				.state('login', {
					url: "/login",
					templateUrl: "user/login.html"
				})
				.state('register', {
					url: "/register",
					templateUrl: "user/register.html"
				})
				.state('about', {
					url: "/about",
					templateUrl: "staticPages/about.html"
				})
				.state('contact', {
					url: "/contact",
					templateUrl: "staticPages/contact.html"
				})
				.state('profile', {
					url: "/profile",
					templateUrl: "user/profile.html",
					data: {auth:true}
				})
				.state('messages', {
					url: "/messages/:id",
					templateUrl: "messaging/messages.html",
					data: {auth:true}
				})
				.state('facilities', {
					url: "/facilities",
					templateUrl: "facility/facilities.html",
					data: {auth:true}
				})
				.state('plan', {
					url: "/plan/:id",
					templateUrl: "plans/plan.html",
					data: {auth:true}
				})
				.state('event', {
					url: "/event/:id",
					templateUrl: "staticPages/events.html",
					data: {auth:true}
				})
				.state('editProfile', {
					url: "/edit-profile/:id",
					templateUrl: "user/edit-profile.html",
					data: {auth:true}
				})
				.state('placeholder', {
					url: "/placeholder/:page",
					templateUrl: "staticPages/placeholder.html"
				})
				
			;

			$urlRouterProvider.otherwise("/login");
		}])
		.run(['$rootScope', 'gettextCatalog', '$localStorage', 'LOCALES', 'AUTH_EVENTS', 'authService', 'session', '$state', 'postman',
	 function ($rootScope,   gettextCatalog,   $localStorage,   LOCALES,   AUTH_EVENTS,   authService,   session,   $state,   postman) {
			//set the current locale/language
			var currentLocale = $localStorage.currentLocale || LOCALES.English;
            gettextCatalog.setCurrentLanguage(currentLocale.language);
            
            //check for existing session data
            if ($localStorage.hasOwnProperty('sessionData') && $localStorage.sessionData.expiration > new Date().getTime()) {
	            session.create($localStorage.sessionData);
            }
            
            //check for authentication (logged in)
			$rootScope.$on('$stateChangeStart', function (event, next) {
				if (next.hasOwnProperty('data') && next.data.hasOwnProperty('auth') && !authService.isAuthenticated()) {	
					event.preventDefault();
					
					$rootScope.$broadcast(AUTH_EVENTS.notAuthenticated, next);
				}
			});
			
			//listen for not being authenticated
			$rootScope.$on(AUTH_EVENTS.notAuthenticated, function(event, nextState) {
				$localStorage.nextState = nextState;
				
				postman.warn(gettextCatalog.getString('You must log in first'));
				
				$state.go('login');
			});
        }]);

	var mainModule = angular.module('cgiAdpq.main', []);	
	var navModule = angular.module('cgiAdpq.nav', []);	
	var userModule = angular.module('cgiAdpq.user', []);
	var messageModule = angular.module('cgiAdpq.message', []);
	var eventModule = angular.module('cgiAdpq.event', []);
	var planModule = angular.module('cgiAdpq.plan', []);
	var facilityModule = angular.module('cgiAdpq.facility', []);
})();