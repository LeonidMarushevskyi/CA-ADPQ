/**
 * Created by Husamui on 6/1/16.
 */
(function() {
    'use strict';
angular.module('cws').controller('newMessageController', ['$scope','User','Conversation','$state','toastr',function($scope,User,Conversation,$state,toastr){
    $scope.conversation = new Conversation.resource();





    $scope.newConversation = function(){
        console.debug($scope.conversation);
        console.log($scope.conversation.receiver);
        if($scope.conversation.receiver == undefined){
            // alert("this field it's required");
            toastr.error('Please select a receiver', 'Error');

            $(".loading").removeClass('active');
            return false;
        }else{
            Conversation.resource.save($scope.conversation, function(data){
                if(data.success){
                    $scope.conversation = new Conversation.resource();
                    $state.go('messages.inbox', {}, {reload: true});
                }

            });
        }
    }




}]);
}());