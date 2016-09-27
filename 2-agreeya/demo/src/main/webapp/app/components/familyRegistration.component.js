(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");

  function familyRegistrationController() {
    var model = this;

  }

  module.component("familyRegistration", {
    templateUrl: "./app/templates/familyRegistrationMain.html",
    $routeConfig: [
      {path: "/familyRegistrationEligibility", component: "familyRegistrationEligibility", name: "FamilyRegistrationEligibility", useAsDefault: true},
      {path: "/familyRegistrationProfile", component: "familyRegistrationProfile", name: "FamilyRegistrationProfile"},
        {path: "/familyRegistrationProfileStep2", component: "familyRegistrationProfileStep2", name: "FamilyRegistrationProfileStep2"},
        {path: "/familyRegistrationProfileStep3", component: "familyRegistrationProfileStep3", name: "FamilyRegistrationProfileStep3"},
        {path: "/familyRegistrationProfileStep4", component: "familyRegistrationProfileStep4", name: "FamilyRegistrationProfileStep4"},
        {path: "/registrationThankYou", component: "familyRegistrationProfileThankYou", name: "RegistrationThankYou"}
    ],
    controllerAs: "model",
    controller: [familyRegistrationController]
  });

}(window.angular));
