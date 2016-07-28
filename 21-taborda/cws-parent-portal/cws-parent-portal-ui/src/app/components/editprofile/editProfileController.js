/**
 * Created by Husamui on 5/31/16.
 */
(function() {
    'use strict';
angular.module('cws').controller('editProfileController', ['$scope','$rootScope','User','USStates','toastr','ERRORS',function($scope, $rootScope, User, USStates, toastr, ERRORS){

    $scope.USStates = USStates;

    var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");

    $scope.strongRegex = strongRegex;

    User.data.$promise.then(function(data){
        if(data.success){
            $scope.user = data.user;
            $scope.user.address.zip = Number(data.user.address.zip);
        }else{
            $rootScope.$broadcast(ERRORS.server);
        }
    });


    $scope.update = function () {
        var update = User.resource.update({ id:'me' }, $scope.user, function(){
            if(update.success){
                toastr.success('Your profile updated!', 'Success');
                $scope.user = update.user;
                $scope.user.address.zip = Number(update.user.address.zip);
            }else{
                toastr.error(update.message, 'Error');
            }
        });
    };

    

}]);
}());