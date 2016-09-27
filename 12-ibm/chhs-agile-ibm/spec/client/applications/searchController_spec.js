'use strict';

describe('Search Module', function () {
  var $controller;
  var $scope;
  var element;
  beforeEach(module('searchModule'));

  beforeEach(function() {
    $scope = {};

    inject(function ($controller, $rootScope, $compile) {
      var $controller = $controller('SearchController', {$scope: $scope});
    });
  });

  describe('Search Controller', function () {

    it('has a search controller', function () {
      expect($scope.data).toEqual('Search');
    });
  });
});