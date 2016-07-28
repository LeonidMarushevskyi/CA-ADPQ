'use strict';

angular.module('chhs').factory('fosterHomesFactory', function ($http, $log, $q) {

  function getFosterHomes() {
    return $http.get('/api/adoption-centers').then(function (response) {
      return response.data;
    });
  }

  function searchFosterHomes(zip, proximity) {
    return $http.get('/api/adoption-centers/' + zip + '/' + proximity).then(function (response) {
      return response.data;
    });
  }

  return {
    getFosterHomes: getFosterHomes,
    searchFosterHomes: searchFosterHomes
  };
});
