(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");

  function familyRegistrationEligibilityController(toaster, $cookieStore) {
    var model = this;
    model.homeStudy = {
      value: "no"
    }
    model.priorTraining = {
      value: "no"
    }
    model.GoTo = function(){
      if(model.homeStudy.value.toLowerCase() === "yes" && model.priorTraining.value.toLowerCase() === "yes"){
        model.$router.navigate(['FamilyRegistrationProfile']);
      } else {
        toaster.pop('error', 'Eligibility criteria', "For now, you do not meet the eligibility criteria to become a foster parent. Please Contact Us, we would be happy to assist you.");
        console.log("Selected criteria does not match");
        setTimeout(function() {
          location.href="index.html";
        }, 2000);

      }
    }

    model.$routerOnActivate = function() {
      if($cookieStore.get("userContext") && $cookieStore.get("fromLogin")){
         location.href="index.html";
      }
    };
  }

  module.component("familyRegistrationEligibility", {
    templateUrl: './app/templates/familyRegistrationEligibility.html',
    controllerAs: "model",
    controller: ["toaster", '$cookieStore', familyRegistrationEligibilityController],
    bindings: {
      $router: '<'
    }
  })

}(window.angular));
