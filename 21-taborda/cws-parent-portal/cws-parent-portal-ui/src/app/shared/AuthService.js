(function() {
    'use strict';
angular.module('cws')

    .service('AuthService', ['$rootScope','$q','$http', function($rootScope, $q, $http) {
        var LOCAL_TOKEN_KEY = 'CWS-Key';
        var LOCAL_USER_ID = "CWS-User";
        var isAuthenticated = false;
        var authToken;
        var userId;

        function loadUserCredentials() {
            var token = window.localStorage.getItem(LOCAL_TOKEN_KEY);
            userId = window.localStorage.getItem(LOCAL_USER_ID);

            if (token) {
                useCredentials(token);
            }
        }
        function storeUserCredentials(data) {
            window.localStorage.setItem(LOCAL_TOKEN_KEY, data.token);
            window.localStorage.setItem(LOCAL_USER_ID, data.userId);
            userId = data.userId;
            useCredentials(data.token);
        }

        function useCredentials(token) {
            isAuthenticated = true;
            authToken = token;
            // Set the token as header for requests!
            $http.defaults.headers.common['X-Auth-Token'] = token;
        }

        function destroyUserCredentials() {
            authToken = undefined;
            isAuthenticated = false;
            $http.defaults.headers.common['X-Auth-Token'] = undefined;
            window.localStorage.removeItem(LOCAL_TOKEN_KEY);
            window.localStorage.removeItem(LOCAL_USER_ID);
        }

        var logout = function() {
            destroyUserCredentials();

        };

        loadUserCredentials();

        return {
            storeUserCredentials: storeUserCredentials,
            logout: logout,
            isAuthenticated: function() {return isAuthenticated;},
            getUserId: function() {return userId;}
        };
    }]);
}());
    