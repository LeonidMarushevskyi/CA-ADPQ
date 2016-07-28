'use strict';

angular.module('chhs').factory('Auth', function ($http, $log, $cookies) {

  function login(email, password) {
    var authRequest = {
      email: email,
      password: password
    };

    $log.info('Attempting login request...');

    return $http.post('/login', authRequest)
      .then(function (response) {
        return response.data;
      }, function (e) {
        $log.error('Error during login.');
        throw e;
      });

  }

  function loggedIn() {
    return !!$cookies.get('Token');
  }

  return {
    login: login,
    loggedIn: loggedIn
  };
});
