(function() {
    'use strict';
angular.module('cws').controller('createProfileController', ['$scope','User','AuthService','toastr',function($scope, User,AuthService,toastr){



    $scope.user = new User.resource();
    $scope.formSubmitted = false;
    $scope.formValid = false;

    var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");

    $scope.strongRegex = strongRegex;



    $scope.register = function(){
        User.resource.save($scope.user, function(data){
            console.log(data);
            if(data.success){
                AuthService.storeUserCredentials(data);
                window.location = '/';
            }else if(!data.success){
                toastr.error(data.message, 'Error');
            }
        },function (err) {
            toastr.error(err, 'Error');
            console.log(err);
        });


    }

}]);
}());