(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");

  function loginController(userService, toaster, $cookieStore) {
    var model = this;
    model.Username = "";
    model.Password = "";
    model.isLoggedIn = false;
    model.fullName = "";

    if(userService.IsUserLoggedIn()){
        model.isLoggedIn = true;
        model.fullName = userService.getCurrentUserFullname();
    };
    model.validateLogin = function(userName, psd) {

      var userContext;
      userService.validateUser(userName, psd).then(function(userResponse){
        if(userResponse) {

          if(userResponse.userContext){
            $cookieStore.put("userContext", userResponse);

              if(userResponse.userContext.firstName != ""){
                model.fullName = userResponse.userContext.firstName + ' ' + userResponse.userContext.lastName;
              } else {
                model.fullName = userResponse.userContext.userName;
              }
              model.isLoggedIn = true;
              //toaster.pop('success', "Login", "You are successfully logged in.");
               if (!$cookieStore.get("fromLogin")) {
                    $cookieStore.put("fromLogin", "yes");
                 }
              location.href="index.html";

          }
        } else {
         toaster.pop('error', "Login", "Wrong credentials! Please try again.");
         model.isLoggedIn = false;
         location.href="#/";
        }
      }, function(error){
        toaster.pop('error', "Login", "Wrong credentials! Please try again.");
        model.isLoggedIn = false;
        location.href="#/";
      });
    };

    model.logout = function() {
      userService.logoutUser().then(function(userResponse){
          if(userResponse){
            if(userResponse.status == "0"){
              $cookieStore.remove('userContext');
              model.isLoggedIn = false;
               if ($cookieStore.get("fromLogin")) {
                    $cookieStore.remove("fromLogin");
                 }
              if($cookieStore.get("selectedTab")){
                $cookieStore.remove("selectedTab");
              }
              location.href="index.html";
            } else {
              //toaster.pop('error', "Logout", userResponse.message);
              model.isLoggedIn = true;
            }
          }
      }, function(error){
        //toaster.pop('error', "Logout", "Error in logout");
        $cookieStore.remove('userContext');
        model.isLoggedIn = false;
        location.href="index.html";
      });

    }
  }

  module.component("loginControl", {
    templateUrl: "./app/templates/loginControl.html",
    controllerAs: "model",
    controller: ['userService', 'toaster', '$cookieStore', loginController]
  });
}(window.angular));
