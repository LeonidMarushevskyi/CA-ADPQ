(function(){
  'use strict';

  var module = angular.module('searchModule', ['ngStorage']);

  module.controller('SearchController', ['$scope', '$location',
    'facilityFactory', '$localStorage', function($scope, $location, facilityFactory, $localStorage){
    $scope.data = "Search";
    $scope.storage = $localStorage;

    $scope.searchZipCodes = function () {
      facilityFactory.getZipCode = $scope.zipcodes;
      $scope.storage.zipcode = facilityFactory.getZipCode;
      $location.path('/facilities');
    }
  }]);

  module.factory('facilityFactory', function () {
    return {
      getZipCode: ""
    };
  });
})();
