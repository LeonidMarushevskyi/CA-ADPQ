(function(angular) {
   'use strict';

   var module = angular.module("chhsDemo");
   function mainController(dataService, userService, toaster, ngModel) {
     var model = this;

     /*dataService.getResponse().then(function(data){
       console.log(data);
     });*/


   }

   module.component("mainApp", {
      templateUrl: "./app/templates/main.html",
      $routeConfig: [
          {path: "/", component: "homePage", name: "Home", useAsDefault: true},
          {path: "/home", component: "homePage", name: "HomePage"},
          {path: "/familyRegistration/...", component: "familyRegistration", name: "FamilyRegistration"},
          {path: "/list", component: "movieList", name: "List"},
          {path: "/about", component: "appAbout", name: "About"},
          {path: "/userHome", component: "userHomeComponent", name: "UserHomePage"},
          {path: "/userHomeRoot/...", component: "userHomeRoot", name: "UserHomeRoot" },
          {path: "/**", redirectTo: ["Home"]}
      ],
      controllerAs: "model",
      controller: ["dataService", "userService", "toaster", mainController]
   });

}(window.angular));
