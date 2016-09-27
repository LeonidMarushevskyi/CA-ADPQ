(function() {
  'use strict';
  var module = angular.module("chhsDemo");

  function userStatusController(toaster, $cookieStore) {
    var model = this;
    
    model.userStatus = "Pending";
    if($cookieStore.get("userContext")){
        var context = $cookieStore.get("userContext");
        model.userStatus = context.userContext.userStatus;
    }

  }

    module.component("userStatus", {
      templateUrl: './app/templates/userStatus.html',
    controllerAs: "model",
    controller: ["toaster", '$cookieStore', userStatusController],
    bindings: {
      $router: '<'
    }
  })

}());
