'use strict';

angular.module('chhs').factory('Account', function ($http, $log, $q) {

  function createAccount(accountInfo) {
    return $http.post('/api/account', accountInfo)
      .then(function (response) {
        return response.data;
      }, function (e) {
        $log.error('Error creating user account.');
        return $q.reject('Error creating account.');
      });
  }

  function getUserAccount() {
    return $http.get('/api/account')
      .then(function (response) {
        return response.data;
      }, function (e) {
        $log.error('Error getting user account.');
        return $q.reject('Error getting user account.');
      });
  }

  function getCaseWorker() {
    return $http.get('/api/account/caseworker').then(function (response) {
      return response.data;
    }, function () {
      $log.error('Error getting caseworker information.');
      return $q.reject('Error case worker info account.');
    });
  }

  function update(account) {
    return $http.put('/api/account/', account).then(function () {
      return true;
    }, function () {
      $log.error('Error updating account information.');
      return $q.reject('Error updating account information.');
    });
  }

  return {
    createAccount: createAccount,
    getUserAccount: getUserAccount,
    getCaseWorker: getCaseWorker,
    update: update
  };
});
