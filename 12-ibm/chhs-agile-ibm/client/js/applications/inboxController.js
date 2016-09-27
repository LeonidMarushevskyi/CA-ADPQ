(function(){
  'use strict';

  var module = angular.module('inboxModule', []);

  module.controller('InboxController', ['$scope', '$mdDialog', '$mdToast', function($scope, $mdDialog, $mdToast){

     $scope.inboxData = [
       {title: 'Welcome', from: 'From: Francis', body: 'Hello Rachel,\nWelcome to your Child Healthcare Services online profile.\nLet me know if I can help you in any way.\nSincerely, Francis'},
       {title: 'New data you\'ll find useful', from: 'From: Francis', body: 'Hello Rachel, We found a few Facilities in your zipcode. Click on the search button at the top right to explore and try searching for facilities in your zip code.  Let me know if you need any help or have any questions.  Sincerely, Francis'}
     ];

     $scope.sent_message = [
       {title: 'Can you help?', body:
       'Hi Francis, I am struggling to balance everything right now and need assistance.' + '\n' +'I am planning to search through the FFAs to see if there is one in my neighborhood that is appropriate for Molly.'}
     ];

     $scope.showMessage = function(ev, title, from, body) {
        $scope.inboxBody = body;
        $mdDialog.show(
          $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title(title)
            .textContent(body)
            .ariaLabel('Viewing a message')
            .ok('OK')
            .targetEvent(ev)
        );
     };

     $scope.showMessageDialog = function(ev) {
       var confirm = $mdDialog.confirm()
           .title($scope.sent_message[0].title)
           .textContent($scope.sent_message[0].body)
           .ariaLabel('Message saying Rachel asks for help')
           .targetEvent(ev)
           .ok('SEND')
           .cancel('CANCEL');

           $mdDialog.show(confirm).then(function() {
             $mdToast.show(
                     $mdToast.simple()
                        .textContent('Message sent')
                        .position('bottom center')
                        .hideDelay(3000)
                  );
          }, function() {
            $scope.status = 'You decided to keep your debt.';
          });
     }


  }]);
})();
