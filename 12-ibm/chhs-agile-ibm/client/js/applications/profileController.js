(function(){
  'use strict';

  var module = angular.module('profileModule', ['ngStorage']);

  module.controller('ProfileController', ['$scope', '$localStorage', function($scope, $localStorage){
    $scope.data = "Profile";
    $scope.storage = $localStorage;
    $scope.showProfile = true;
    $scope.editMyProfile = true;
    $scope.storage.parent_home_address;

    // Local Storage Initialization
    var jsonData = JSON.stringify($scope.storage);
    $scope.profileData = {
      parent_home_address: "4567 Cedar Avenue, Chula Vista 91910",
      parent_home_phone: "626-514-1111",
      parent_work_number: "626-514-0000"
    };

    //change condition to see if empty
    if(jsonData.indexOf('{}') >=0) {
      $scope.storage.parent_home_address = $scope.profileData.parent_home_address;
      $scope.storage.parent_home_phone = $scope.profileData.parent_home_phone;
      $scope.storage.parent_work_number = $scope.profileData.parent_work_number;
    } else {
      console.log('Storage Exists');
    }
    $scope.parentHomeAddress = $scope.storage.parent_home_address;
    $scope.parentHomePhone = $scope.storage.parent_home_phone;
  }]);

  module.directive('editMyProfile', function () {
    return {
      restrict: "A",
      templateUrl: 'client/views/applications/edit.html',
      link: function(scope, element, attrs, localStorage) {

        scope.editableValue = true;
        scope.viewAddress = scope.storage.parent_home_address;
        scope.viewHomePhone = scope.storage.parent_home_phone;
        scope.viewWorkPhone = scope.storage.parent_work_number;

        scope.editProfile = function(){
          scope.storage.parent_home_address = scope.viewAddress;
          scope.storage.parent_home_phone = scope.viewHomePhone;
          scope.storage.parent_work_number = scope.viewWorkPhone;

          if(scope.editableValue == true) {
            scope.editableValue = false;
            scope.showProfile = false;
            scope.editMyProfile = false;
          } else {
            scope.editableValue = true;
            scope.showProfile = true;
            scope.editMyProfile = true;
          }
        };
      }
    };
  });
})();
