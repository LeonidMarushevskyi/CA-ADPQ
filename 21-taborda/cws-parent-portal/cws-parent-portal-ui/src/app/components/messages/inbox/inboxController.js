/**
 * Created by Husamui on 6/1/16.
 */
(function() {
    'use strict';
angular.module('cws').controller('inboxController', ['$scope','Conversation',function($scope,Conversation){



    Conversation.resource.get({select:'receiver'},function(data){
        $scope.conversations = data.conversations;
    });



    $('[data-toggle="popover"]').popover();


    $scope.getSender = function(conversion){
        return conversion.sender.name.first_name + " " + conversion.sender.name.last_name;
        // console.log(conversion);
    };

  


}]);
}());
