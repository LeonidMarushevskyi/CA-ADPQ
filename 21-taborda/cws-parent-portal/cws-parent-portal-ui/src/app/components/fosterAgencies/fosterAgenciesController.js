(function() {
    'use strict';
angular.module('cws').controller('fosterAgenciesController', ['$scope', 'FosterAgency', 'User', function($scope, FosterAgency, User){
    User.data.$promise.then(function(data) {
        $scope.fosterAgencies = FosterAgency.query({zipcode: data.user.address.zip});
    });

    $scope.print = function(){
        window.print();
    }
    
}]);
}());
