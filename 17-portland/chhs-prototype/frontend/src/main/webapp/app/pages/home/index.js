'use strict';

angular.module('chhs').controller('homeCtrl', function ($log, Account) {

  var home = this;

  Account.getUserAccount()
    .then(function (data) {
      home.account = data;
    });
});
