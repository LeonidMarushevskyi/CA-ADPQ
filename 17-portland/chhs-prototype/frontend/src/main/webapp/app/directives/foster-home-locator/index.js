'use strict';

angular.module('chhs').directive('fosterHomeLocator', function (fosterHomesFactory, uiGmapIsReady) {
  return {
    restrict: 'C',
    templateUrl: 'app/directives/foster-home-locator/index.jsp',
    controller: function ($scope) {

      var defaultSearchState = {
        zip: '',
        proximity: ''
      };

      var defaultMapState = {
        center: {
          latitude: 37.1870327,
          longitude: -119.7638647
        },
        zoom: 5
      };

      var gMap;

      var zoom = function (locations) {
        if (gMap && locations && locations.length > 0) {
          var bounds = new google.maps.LatLngBounds();
          _.each(locations, function (loc) {
            bounds.extend(new google.maps.LatLng({lat: loc.location.latitude, lng: loc.location.longitude}));
          });
          gMap.fitBounds(bounds);
        }
      };

      // Initialize data
      $scope.results = [];
      $scope.search = angular.copy(defaultSearchState);
      $scope.map = angular.copy(defaultMapState);

      uiGmapIsReady.promise(1).then(function (instances) {
        gMap = instances[0].map;
        zoom($scope.results);
      });

      // Initialize async data
      fosterHomesFactory.getFosterHomes().then(function (fosterHomes) {
        $scope.selectedFacility = null;
        $scope.results = fosterHomes;
        zoom($scope.results);
      });

      // Exposed functions
      $scope.searchFosterHomes = function (zip, proximity) {
        fosterHomesFactory.searchFosterHomes(zip, proximity).then(function (fosterHomes) {
          $scope.selectedFacility = null;
          $scope.results = fosterHomes;
          zoom($scope.results);
        });
      };

      $scope.resetFosterHomes = function () {
        fosterHomesFactory.getFosterHomes().then(function (fosterHomes) {
          $scope.selectedFacility = null;
          $scope.search = angular.copy(defaultSearchState);
          $scope.results = fosterHomes;
          zoom($scope.results);
        });
      };

      $scope.mapClick = function(marker, eventName, model) {
        $scope.selectedFacility = marker.key;
        gMap.setZoom(16);
        gMap.setCenter(marker.getPosition());

        var top = $('#' + marker.key).position().top;
        $('.foster-home-locator__search-results').scrollTop(top);
      };

      $scope.map.events = {
        click: $scope.mapClick
      };

      $scope.selectFacility = function(location){
        $scope.selectedFacility = location.facility_number;
        zoom([location]);
        gMap.setZoom(16);
      };


    }
  };
});
