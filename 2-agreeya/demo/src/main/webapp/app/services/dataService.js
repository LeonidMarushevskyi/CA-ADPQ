(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");
  //Change this if API host address is changing
  //var APIHostAddress = "http://209.118.218.61:8080/";

  module.factory("dataService", ["$http", "$resource", "$q", "configVals", function ($http, $resource, $q, configVals) {
    var dataService = {};
    var APIHostAddress = configVals.apiHostUrl;
    dataService.fetchMovies = function() {
            return $http.get("./app/data/movies.json").then(function (response) {
                return response.data;
            })
        }
    dataService.getUser = function() {
      return $http.get(APIHostAddress + "chhs/rest/contextinit/createusercontext").then(function (response) {
          console.log("User data");
          console.log(response.data);
          return response.data;
      })
    }
    dataService.validateUser = function(username, password) {
      var deferred = $q.defer();
      var userData = JSON.stringify({userName: username, password: password});
      $.post( APIHostAddress + "chhs/rest/contextinit/createusercontext", userData, function( data ) {
        var userResponse = JSON.parse(data);
        console.log(userResponse);
        deferred.resolve(userResponse);
      })
      .fail(function(error) {
        console.log(error);
        deferred.reject();
      });

      return deferred.promise;
       
  };

  dataService.logoutUser = function(userContext) {
    var deferred = $q.defer();
    var userData = JSON.stringify({userContext: userContext});
    
    $.post( APIHostAddress + "chhs/rest/contextinit/userlogout", userData, function( data ) {
      var userResponse = JSON.parse(data);
      console.log(userResponse);
      deferred.resolve(userResponse);
    })
    .fail(function(error) {
      console.log(error);
      deferred.reject();
    });

    return deferred.promise;
  }
    dataService.getResponse = function(){
      return $http.get("http://rest-service.guides.spring.io/greeting").then(function (response) {
        console.log("CORS CAll: ", response.data);
        return response.data;
      });
    }

    dataService.fetchFacilities = function() {
        return $http.get("./app/data/facilities.json").then(function (response) {
          console.log("Facilities data: ", response)
            return response.data;
        }, function(error){
          console.log("Error in getting facilities");
        });
    };

    //Save user registration data in database.
    dataService.SaveRegistrationProfileData = function(registrationData) {
      var deferred = $q.defer();
      $.post( APIHostAddress + "chhs/rest/member/register", registrationData, function( registrationResponse ) {
        var userRegistrationResponse = JSON.parse(registrationResponse);
        console.log(userRegistrationResponse);
        deferred.resolve(userRegistrationResponse);
      })
      .fail(function(error) {
        console.log(error);
        deferred.reject();
      });

      return deferred.promise;
    }; //SaveRegistrationProfileData function ends here

    //Get child facilities data based on zipcode.
    dataService.getChildResidentialFacilities = function(zipCode) {
      var deferred = $q.defer();
      $.get( APIHostAddress + "chhs/rest/facilities/zipcode/" + zipCode, function( response ) {
        //var searchResponse = JSON.parse(response);
        console.log(response);
        deferred.resolve(response);
      })
      .fail(function(error) {
        console.log(error);
        deferred.reject();
      });

      return deferred.promise;
    }; //getChildResidentialFacilities function ends here

    //Get child facilities data based on zipcode.
    dataService.getFosterCareAgencies = function(locationData) {
      var deferred = $q.defer();
      var location = JSON.stringify(locationData);
      $.post(APIHostAddress + "chhs/rest/facilities/agencynearby", locationData,  function( response ) {
        //var searchResponse = JSON.parse(response);
        console.log(response);
        deferred.resolve(response);
      })
      .fail(function(error) {
        console.log(error);
        deferred.reject();
      });

      return deferred.promise;
    }; //getFosterCareAgencies function ends here


    dataService.getUserLocation = function(zipCode) {
        var deferred = $q.defer();
      $.get( "https://maps.googleapis.com/maps/api/geocode/json?address=" + zipCode,  function( response ) {
        //var searchResponse = JSON.parse(response);
        console.log(response);
        deferred.resolve(response.results[0]);
      })
      .fail(function(error) {
        console.log(error);
        deferred.reject();
      });
      return deferred.promise;
    }

    //get user inbox data from database.
    dataService.getUesrInboxData = function(userContext) {
      var deferred = $q.defer();
      var requestData = JSON.stringify({userContext: userContext.userContext});
      $.post( APIHostAddress + "chhs/rest/member/inbox", requestData, function( response ) {
        var jsonInbox = JSON.parse(response);
        console.log(jsonInbox);
        deferred.resolve(jsonInbox);
      })
      .fail(function(error) {
        console.log(error);
        deferred.reject();
      });

      return deferred.promise;
    }; //getUesrInboxData function ends here


    
      //Update user account details data in database.
    dataService.UpdateAccountDetails = function (accountDetailsData) {
        var deferred = $q.defer();
        $.post(APIHostAddress + "chhs/rest/member/save", accountDetailsData, function (accountDetailsResponse) {
            var userAccountDetaisUpdateResponse = JSON.parse(accountDetailsResponse);
            console.log(userAccountDetaisUpdateResponse);
            deferred.resolve(userAccountDetaisUpdateResponse);
        })
        .fail(function (error) {
            console.log(error);
            deferred.reject();
        });

        return deferred.promise;
    }; //UpdateAccountDetails function ends here

      //Get user account details data in database.
    dataService.GetAccountDetails = function (accountDetailsData) {
        var deferred = $q.defer();
        $.post(APIHostAddress + "chhs/rest/contextinit/checkuserdetailexist", accountDetailsData, function (accountDetailsResponse) {
            var userAccountDetaisUpdateResponse = JSON.parse(accountDetailsResponse);
            console.log(userAccountDetaisUpdateResponse);
            deferred.resolve(userAccountDetaisUpdateResponse);
        })
        .fail(function (error) {
            console.log(error);
            deferred.reject();
        });

        return deferred.promise;
    }; //GetAccountDetails function ends here


    return dataService;
  }]);
}(window.angular));
