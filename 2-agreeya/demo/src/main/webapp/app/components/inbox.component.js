(function() {
  'use strict';
  var module = angular.module("chhsDemo");

  //controller function
  function userInboxController(userService) {
    var model = this;
    model.isMails = 'default';
    model.inboxData = [];
    model.$onInit = function() {
      userService.getuserInboxData().then(function(response){
        model.inboxData = response.emails;
        console.log(model.inboxData);
        model.isMails = 'yes';
      }, function(error){
        console.log("Error in loading emails");
        model.isMails = 'no';
      });
    }

  } //controller function ends here

  //component
  module.component("userInbox", {
    templateUrl: './app/templates/inbox.html',
    controllerAs: 'model',
    controller: ['userService', userInboxController]
  });
}());
