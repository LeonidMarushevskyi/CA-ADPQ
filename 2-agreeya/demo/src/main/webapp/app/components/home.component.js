(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");

  function homePageController($cookieStore) {
    var model = this;

    model.GoToFamilyRegistration = function() {
      model.$router.navigate(['FamilyRegistration', 'FamilyRegistrationEligibility']);
    }

    if($cookieStore.get('userContext') && $cookieStore.get("fromLogin")){
      
      //model.$router.navigate(['UserHomePage']);      
       model.$router.navigate(['UserHomeRoot', 'UserStatus']);
    };
  }

  module.component("homePage", {
    templateUrl: "./app/templates/home.html",
    controllerAs: "model",
    controller: ['$cookieStore', homePageController],
    bindings: {
      $router: '<'
    }
  });
}(window.angular));
