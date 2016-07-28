(function() {
    'use strict';
angular.module('cws').controller('mainController', ['$scope','$rootScope','AuthService','User','ERRORS',function($scope,$rootScope,AuthService,User,ERRORS){


    User.data.$promise.then(function(data){
        if(data.success){
            $scope.user = data.user;
        }else{
            $rootScope.$broadcast(ERRORS.server);
        }
    });


}]);

}());










