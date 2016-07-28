/**
 * Created by Husamui on 6/1/16.
 */
(function() {
    'use strict';
angular.module('cws').controller('singleMessageController', ['$scope','$stateParams','Conversation','Message','User',function($scope, $stateParams,Conversation,Message,User){

    var messagesWrapper = $(".messages-wrapper");

    function disableReplyingFomr(){
        // So the user can't reply on their messages.
        var textarea = $("#replyMsgInput");
        textarea.attr('disabled','disabled');
        textarea.attr('placeholder','Your message has been sent.');
        $(".reply-btns input").attr('disabled','disabled');
    }

        Conversation.resource.get({id: $stateParams.id}, function(data){
            $scope.conversation = data.conversation;
            // console.log($scope.conversation.receiver.id);
            $scope.replymessage.receiver = $scope.conversation.receiver.id;

            if(User.data.user.id == $scope.conversation.sender.id){
                disableReplyingFomr();
            }

        });


    function scrollToBottom(){
        messagesWrapper.animate({ scrollTop: messagesWrapper.height() }, "slow");

    }
    scrollToBottom();

    $('[data-toggle="popover"]').popover();


    $scope.replymessage = new Message.resource();
    $scope.replymessage.conversation_id = $stateParams.id;



    $scope.reply = function(){
        Message.resource.save($scope.replymessage, function(res){
           if(res.success){
               res.message.sender = User.data.user;
               $scope.conversation.messages.push(res.message);
               disableReplyingFomr();
               scrollToBottom();
               $scope.replymessage.content = "";
           }
        });
    };


    $scope.print = function(){
        window.print();
    }


    

}]);
}());