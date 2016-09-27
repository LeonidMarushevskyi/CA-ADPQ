(function(angular){
  'use strict';

angular.module('myApp', ['ngRoute', 'profileModule',
    'inboxModule', 'searchModule', 'facilitiesModule', 'ngMaterial', 'ngMessages'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
          title: 'Profile',
          templateUrl: 'client/views/applications/profile.html',
          controller: 'ProfileController'
        })
        .when('/profile', {
          title: 'Profile',
          templateUrl: 'client/views/applications/profile.html',
          controller: 'ProfileController'
        })
        .when('/inbox', {
          title: 'Inbox',
          templateUrl: 'client/views/applications/inbox.html',
          controller: 'InboxController'
        })
        .when('/search', {
          title: 'Search',
          templateUrl: 'client/views/applications/search.html',
          controller: 'SearchController'
        })
        .when('/facilities', {
          title: 'Search',
          templateUrl: 'client/views/applications/facilities.html',
          controller: 'FacilitiesController'
        })
        .when('/mobile_profile', {
          templateUrl: 'client/views/applications/mobile_profile.html',
          controller: 'ProfileController'
        })
        .when('/desktop_profile', {
            templateUrl: 'client/views/applications/desktop_profile.html',
            controller: 'ProfileController'
        });
  }])
  .run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
      $rootScope.title = current.$$route.title;
      $rootScope.changeColor = function(val) {
        if(val === 'profile') {
          $rootScope.profileColor = "#083d66";
          $rootScope.searchColor = '';
          $rootScope.inboxColor = '';
        } else if(val === 'search') {
          $rootScope.profileColor = '';
          $rootScope.searchColor = '#083d66';
          $rootScope.inboxColor = '';
        } else if(val === 'inbox') {
          $rootScope.profileColor = '';
          $rootScope.searchColor = '';
          $rootScope.inboxColor = '#083d66';
        }
      };
    });
  }]);
})(window.angular);
