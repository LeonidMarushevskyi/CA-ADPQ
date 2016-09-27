(function(){
  'use strict';

  var module = angular.module('facilitiesModule', ['ngStorage']);

  module.controller('FacilitiesController', ['$scope', 'facilityFactory', '$http', '$localStorage',
    function($scope, facilityFactory, $http, $localStorage){
    $scope.data = "Facilities";
      $scope.storage = $localStorage;

    var url = "https://chhs.data.ca.gov/resource/mffa-c6z5.json?location_zip="
            + $scope.storage.zipcode;
    $http.get(url)
    .success(function(data) {
        if(data == '') {
          $scope.noFacilities = 'Your search found no facilities. ' +
            ' Please try another nearby zip code.';
        } else {
          $scope.showFacilities = data;
        }
    })
    .error(function(err) {
      console.log('err: '+ err);
    });

  }]);
})();
