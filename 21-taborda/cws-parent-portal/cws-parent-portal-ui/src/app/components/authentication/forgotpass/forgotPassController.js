(function() {
    'use strict';
angular.module('cws').controller('forgotPassController', ['$scope','$rootScope',function($scope,$rootScope){

    $('[data-toggle="popover"]').popover();

    $(".fpasswordForm").hover(function(){
        $('[data-toggle="popover"]').popover('show');

    });



}]);
}());