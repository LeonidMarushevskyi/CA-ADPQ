(function() {
    'use strict';
var app = angular.module('cws', ['ui.router','ngResource','toastr']);

    app.controller('cwsMainCtrl', ['$scope','ERRORS','toastr','AuthService', function ($scope, ERRORS, toastr,AuthService) {

        $scope.$on(ERRORS.badRequest, function() {
            toastr.error('Invalid request', 'Error');
        });

        $scope.$on(ERRORS.notFound, function() {
            toastr.error('Resource not found', 'Error');
        });

        $scope.$on(ERRORS.notAuthenticated, function() {
            toastr.error('Unauthorized request', 'Error');
            AuthService.logout();

        });

        $scope.$on(ERRORS.server, function() {
            toastr.error('Unexpected error', 'Error');
        });

    }])

    .run(['$rootScope','$state','AuthService','ERRORS',function ($rootScope, $state, AuthService,ERRORS) {
        $rootScope.$on('$stateChangeStart', function (event, next) {


            $rootScope.messagestab = next.name.indexOf('messages') != -1;
            $('[data-toggle="popover"]').popover('hide');


            
            if (next.authenticate && !AuthService.isAuthenticated()){
                // User isn’t authenticated
                event.preventDefault();
                $state.go('authentication.login', {}, {reload: true});
                // $rootScope.$broadcast(ERRORS.notAuthenticated);
            }else if(!next.authenticate && AuthService.isAuthenticated()){
                event.preventDefault();
                $state.go('messages.inbox', {}, {reload: true});
            }

        });
    }])

    .factory('AuthInterceptor', ['$rootScope','$q','ERRORS',function ($rootScope, $q, ERRORS) {
        return {
            request: function(config) {
                return config;
            },

            requestError: function(config) {
                return config;
            },

            response: function(res) {
                switch (res.status){
                    case 400 :
                        $rootScope.$broadcast(ERRORS.badRequest);
                        break;
                    case 404 :
                        $rootScope.$broadcast(ERRORS.notFound);
                        break;
                    case 401 :
                    case 403 :
                        $rootScope.$broadcast(ERRORS.notAuthenticated);
                        break;
                    case 500 :
                        $rootScope.$broadcast(ERRORS.server);
                        break;
                }
                return res;
            },
            responseError: function(res) {
                console.log("Response error is: ",res);
                return res;
            }
        };
    }])
    .config(['$httpProvider',function($httpProvider) {
        $httpProvider.interceptors.push('AuthInterceptor');
    }]);
}());