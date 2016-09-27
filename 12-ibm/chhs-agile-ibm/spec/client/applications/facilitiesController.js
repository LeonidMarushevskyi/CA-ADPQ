'use strict';

describe('Facilities Module', function () {
  var $controller;
  var $scope;
  var element;
  var facilityFactory;
  beforeEach(module('facilitiesModule'));
  beforeEach(module('facilityFactory'));

  beforeEach(function() {
    $scope = {};

    inject(function ($controller, $rootScope, $compile, facilityFactory) {
      var $controller = $controller('FacilitiesController',
        {$scope: $scope, facilityFactory: facilityFactory});

    });
  });

  describe('Facilities Controller', function () {

    xit('has a Facilities controller', function () {
      expect($scope.data).toBeDefined();
    });
    
  });
});