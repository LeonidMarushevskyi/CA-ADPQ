(function() {
  'use strict';
  var module = angular.module("chhsDemo");

  module.factory("searchService", ["$q", "dataService", function($q, dataService){
    var searchService = {};

    searchService.getChildResidentialFacilities = function(zipCode) {
        var deferred = $q.defer();
        dataService.getChildResidentialFacilities(zipCode).then(function(res){
          console.log("Response data: ", res);
            deferred.resolve(res);
        }, function(errors){
          deferred.reject();
        });
        return deferred.promise;
    } //getChildResidentialFacilities ends here

    searchService.getFosterCareAgencies = function(locationData) {
        var deferred = $q.defer();
        dataService.getFosterCareAgencies(locationData).then(function(res){
          console.log("Response data: ", res);
            deferred.resolve(res);
        }, function(errors){
          deferred.reject();
        });
        return deferred.promise;
    } //getFosterCareAgencies ends here

    searchService.getUserLocation = function(zipCode) {
      var deferred = $q.defer();
      dataService.getUserLocation(zipCode).then(function(res){
        console.log("Response data: ", res);
          deferred.resolve(res);
      }, function(errors){
        deferred.reject();
      });
      return deferred.promise;
    }

    return searchService;
  }]);
}());
