'use strict';

angular.module('chhs').directive('caseWorkerMessenger', function (messagesFactory, Account) {
  return {
    restrict: 'C',
    templateUrl: 'app/directives/case-worker-messenger/index.jsp',
    scope: {},
    controller: function ($scope) {
      var account;

      $scope.messageRetrieveFailed = false;
      $scope.messageSendingFailed = false;
      $scope.messageDeleteFailed = false;
      $scope.messages = [];
      $scope.userMessage = null;
      $scope.viewedMessage = null;
      $scope.repliedMessage = null;

      function hideComposeWindow(){
        // Because data-dismiss overrides handlers and prevents Angular clicks.
        $('#case-worker-messenger__composer').modal('hide');
      }
      
      function setToField(){
        Account.getCaseWorker().then(function (caseWorker) {
          $scope.caseWorker = caseWorker;
          $scope.userMessage.toEmail = caseWorker.email;
        });
      }

      $scope.loadMessages = function () {
        $scope.messageRetrieveFailed = false;
        messagesFactory.getMessages().then(function (messages) {
          $scope.messages = messages;
        }, function(){
          $scope.messageRetrieveFailed = true;
        });
      };

      $scope.sendMessage = function (isFormValid) {

        if(!isFormValid){
          return;
        }

        $scope.messageSendingFailed = false;
        messagesFactory.sendMessage($scope.userMessage).then(function () {
          hideComposeWindow();
          $scope.userMessage = null;
          $scope.caseWorkerMessageForm.$setPristine();
          $scope.loadMessages();
        }, function(){
          $scope.messageSendingFailed = true;
        });
      };

      $scope.composeMessage = function(){
        $scope.userMessage = messagesFactory.createMessage();
        setToField();
      };

      $scope.replyToMessage = function (message) {
        $scope.userMessage = messagesFactory.createMessage();
        setToField();
        $scope.userMessage.inReplyToId = message.id;
        $scope.userMessage.toEmail = message.fromEmail;
        $scope.userMessage.subject = 'RE: ' + message.subject;
      };

      $scope.deleteMessage = function (id) {
        $scope.messageDeleteFailed = false;
        messagesFactory.deleteMessage(id).then(function(){
          $scope.viewedMessage = null;
          $scope.loadMessages();
        }, function(){
          $scope.messageDeleteFailed = true;
        });
      };

      $scope.cancelCompose = function () {
        $scope.userMessage = null;
        $scope.messageSendingFailed = false;
        hideComposeWindow();
      };

      $scope.viewMessage = function(message){
        $scope.viewedMessage = message;
      };

      Account.getUserAccount().then(function (currentAccount) {
        account = currentAccount;
      });

      $scope.loadMessages();
    }
  };
});
