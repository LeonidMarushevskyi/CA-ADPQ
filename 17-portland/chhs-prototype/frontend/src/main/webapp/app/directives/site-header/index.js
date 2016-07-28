'use strict';

angular.module('chhs').directive('siteHeader', function () {
  return {
    restrict: 'C',
    templateUrl: 'app/directives/site-header/index.jsp',
    controller: function($scope, messagesFactory, Account){
      $scope.messageCount = null;
      $scope.user = null;

      messagesFactory.getMessages().then(function(messages){
        $scope.messageCount = messages.length;
      });

      Account.getUserAccount().then(function(account){
        $scope.user = account.firstName;
      });

    }
  };
});
