'use strict';

angular.module('chhs').controller('loginCtrl', function ($http, $log, $location, $window, $cookies, Auth) {
  var login = this;


  this.login = function () {
    Auth.login(login.auth.email, login.auth.password)
      .then(function () {
        $location.path('/dashboard');

        // Force reload to re-initialize app with authenticated resources.
        $window.location.reload();

        login.error = false;
      }, function () {
        login.error = true;
      });
  };
});
