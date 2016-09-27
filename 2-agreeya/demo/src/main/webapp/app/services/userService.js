(function(angular) {
    'use strict';
  var module = angular.module("chhsDemo");
  module.factory("userService", ["dataService", "$q", "$cookieStore", function(dataService, $q, $cookieStore){
    var userService = {};

    //Validate user using API
    userService.validateUser = function(userName, password) {
      var deferred = $q.defer();
      dataService.validateUser(userName, password).then(function(userResponse){
        console.log("User response data: ", userResponse);
          deferred.resolve(userResponse);
      }, function(errors){
        deferred.reject();
      });
      return deferred.promise;
    }

    ///Logout call for current userName
    userService.logoutUser = function(){
      var deferred = $q.defer();
      var context = $cookieStore.get('userContext');
      if(context){
        dataService.logoutUser(context.userContext).then(function(userResponse){
          console.log("User response data: ", userResponse);
            deferred.resolve(userResponse);
        }, function(errors){
          deferred.reject();
        });
      } else {
        deferred.reject();
      }

      return deferred.promise;
    };

    /* Check whether user is logged in or not? */
    userService.IsUserLoggedIn = function(){
      var loggedIn = false;
      if($cookieStore.get('userContext')){
        var context = $cookieStore.get('userContext');
        if(context.userContext)
        {
            loggedIn = true;
        }
        return loggedIn;
      };
    }; //IsUserLoggedIn ends

    //Get the userContext. It will be useful where we need to send userContext to API
    userService.getUserContext = function() {
      var userContext = {};
      if($cookieStore.get('userContext')) {
        userContext = $cookieStore.get('userContext');
      }
      return userContext;
    }; //getUserContext function ends

    //Get the user's full name from context
    userService.getCurrentUserFullname = function() {
      var fullName = "";
      if($cookieStore.get('userContext')){
        var context = $cookieStore.get('userContext');
        if(context.userContext)
        {

          if(context.userContext.firstName != ""){
            fullName = context.userContext.firstName + ' ' + context.userContext.lastName;
          } else {
            fullName = context.userContext.userName;
          }

        }
        return fullName;
      }; //getCurrentUserFullname function ends
    };

    //Save user registration data in database and return userContext in response.
    userService.SaveRegistrationProfileData = function(registrationData){
      var deferred = $q.defer();
      dataService.SaveRegistrationProfileData(registrationData).then(function(userRegistrationResponse){
        console.log("User registration response data: ", userRegistrationResponse);
          deferred.resolve(userRegistrationResponse);
      }, function(errors){
        deferred.reject();
      });
      return deferred.promise;
    };

    //Save user registration data in database and return userContext in response.
    userService.getuserInboxData = function(){
      var deferred = $q.defer();
      var context = userService.getUserContext();
      if(context) {
        dataService.getUesrInboxData(context).then(function(response){
          console.log("User registration response data: ", response);
            deferred.resolve(response);
        }, function(errors){
          deferred.reject();
        });
      } else {
        deferred.reject();
      }

      return deferred.promise;
    };

     
        //Update user account details data in database and return userContext in response.
    userService.UpdateAccountDetails = function (accountDetailsData) {
        var deferred = $q.defer();
        dataService.UpdateAccountDetails(accountDetailsData).then(function (accountDetailsDataResponse) {
            console.log("User registration response data: ", accountDetailsDataResponse);
            deferred.resolve(accountDetailsDataResponse);
        }, function (errors) {
            deferred.reject();
        });
        return deferred.promise;
    };


    userService.GetAccountDetails = function (accountDetailsData) {
        var deferred = $q.defer();
        dataService.GetAccountDetails(accountDetailsData).then(function (accountDetailsDataResponse) {
            console.log("User registratAccount Details response data: ", accountDetailsDataResponse);
            deferred.resolve(accountDetailsDataResponse);
        }, function (errors) {
            deferred.reject();
        });
        return deferred.promise;
    };


    return userService;
  }]);
}(window.angular));
