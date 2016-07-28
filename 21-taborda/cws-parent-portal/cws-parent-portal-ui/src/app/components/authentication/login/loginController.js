(function() {
    'use strict';
angular.module('cws').controller('loginController', ['$scope','$state','User', function($scope,$state, User){
    


    $scope.data = {};
    


    $scope.login = function(data) {
        User.login(data.username, data.password).then(function(res) {
            $scope.data = {};
            $state.go('messages.inbox', {}, {reload: true});
        }, function(err) {
            // alert("Oops! That email / password combination is not valid.");
            $(".login-error-msg").removeClass("hide");

            // $('#myModal').modal(options)
        });
    };





}]);
}());