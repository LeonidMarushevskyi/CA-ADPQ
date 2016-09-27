'use strict';

describe('Profile Module', function () {
  var $controller;
  var $scope;
  var element;

  beforeEach(module('profileModule'));

  beforeEach(function() {
    $scope = {};

    inject(function ($controller, $rootScope, $compile, $localStorage) {
      var $controller = $controller('ProfileController', {$scope: $scope});
    });
  });
  
  describe('Profile Controller', function () {

    it('has a profile controller', function () {
      expect($scope.data).toEqual('Profile');
    });

    it('has parent address', function () {
      expect($scope.profileData).toBeDefined();
    });

    it('has a local storage', function(){
      expect($scope.storage).toBeDefined();
    });

    it('has an address in local storage', function () {
      expect(Object.keys($scope.storage)).toContain('parent_home_address');
    });

    describe('localStorage Service', function () {
      it('should contain a $localStorage service', inject(function(
        $localStorage
      ) {
        expect($localStorage).not.toBe(null);
      }));

      it('$localStorage should contain address', inject(function(
        $localStorage
      ) {
        expect($localStorage.parent_home_address)
          .toContain('4567 Cedar Avenue, Chula Vista 91910');
      }));

      it('$localStorage should contain parent home phone', inject(function(
        $localStorage
      ) {
        expect($localStorage.parent_home_phone)
          .toContain('626-514-1111');
      }));

      it('$localStorage should contain parent work number', inject(function(
        $localStorage
      ) {
        expect($localStorage.parent_work_number)
          .toContain('626-514-0000');
      }));
    });
  });

  xdescribe('Edit Profile', function () {

    // beforeEach(function(){
    //   element = angular.element('<div edit-my-profile/>');
    //
    //   inject(function ($controller, $rootScope, $compile) {
    //     var scope = $rootScope.$new();
    //     scope.name = name;
    //     $compile(element)(scope);
    //     scope.$digest();
    //   });
    // });

    beforeEach(function(){
      element = angular.element('<div edit-my-profile />');

      inject(function ($controller, $rootScope, $compile, $localStorage) {
        var $controller = $controller('ProfileController', {$scope: $scope});
        var scope = $rootScope.$new();
        $compile(element)(scope);
        scope.$digest();
      });
    });

    it('has a editmyProfile directive', function(){
      expect(true).toBe(true);
      expect(element.find('')).toContain('Edit');
    });
  });
  });