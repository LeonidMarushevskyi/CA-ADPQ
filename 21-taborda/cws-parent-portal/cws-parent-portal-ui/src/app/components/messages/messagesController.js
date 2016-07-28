(function() {
    'use strict';
angular.module('cws').controller('messagesController', ['$scope','User','$timeout',function($scope,User,$timeout){

    User.data.$promise.then(function(data){
        $scope.user =  data.user;

    });


   



    $timeout(function() {
        $('[data-toggle="popover"]').popover();
    }, 2000);


    $(".sidebar").click(function(){
        $(".sidebar").removeClass('active');
    })
}]);
}());
