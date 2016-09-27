(function() {
  'use strict';
  var module = angular.module("chhsDemo");

  function userLicenseController($cookieStore, toaster, userService) {
    var model = this;
    model.licenceDetails = {};

    model.haveLicense = 'y';
    var _personalProfile = {};
    var _personalDetails = {};
    var _spouseDetails = {};
    var _familyDetails = {};
    var _userName = '';

    var context = {};
    if ($cookieStore.get('userContext')) {
        var _context = $cookieStore.get('userContext');
        context = _context.userContext;
        _userName = context.userName;
        //debugger;
    };
    if ($cookieStore.get('personalProfile')) {
        _personalProfile = $cookieStore.get('personalProfile');
    };
    if ($cookieStore.get('personalDetails')) {
        _personalDetails = $cookieStore.get('personalDetails');
    };

    if ($cookieStore.get('familyDetails')) {
        _familyDetails = $cookieStore.get('familyDetails');
    };

    var userInfo = {};
    var userData = JSON.stringify({ userName: _userName });
    userService.GetAccountDetails(userData).then(function (userDataResponse) {
        userInfo = userDataResponse;
        if (userInfo.user != null) {
            model.licenceDetails = userInfo.user.licenceDetails;
            _personalProfile = userInfo.user.personalProfile;
            _personalDetails = userInfo.user.personalDetails;
            _familyDetails = userInfo.user.familyDetails;

            _personalProfile.password = 'none';
            if (_personalDetails.dob) {
                var newDate = model.formatDate(_personalDetails.dob);
                _personalDetails.dob = newDate;
            }

            if (_personalDetails.spouseDetails.dob) {
                var newDate = model.formatDate(_personalDetails.spouseDetails.dob);
                _personalDetails.spouseDetails.dob = newDate;
            }

            if(model.licenceDetails.licenceNo && model.licenceDetails.licenceNo !== "")
            {
                model.haveLicense = 'y';
            }
            //debugger;
            if (model.licenceDetails.dateOfIssue) {
                var newDate = model.formatDate(model.licenceDetails.dateOfIssue);
                model.licenceDetails.dateOfIssue = newDate;
            }

        }
    }, function (error) {
        console.log("User Info response: ", error);
        toaster.pop("error", "Unable to fetch user info", "Error in profile registration.");
    });

    model.formatDate = function (input) {
        //debugger;
        var datePart = input.match(/\d+/g),
        year = datePart[0], // get only two digits
        month = datePart[1], day = datePart[2];

        return month + '/' + day + '/' + year;
    }

    model.updateUserLicense = function () {

        var userProfileData = JSON.stringify({ userContext: context, personalProfile: _personalProfile, personalDetails: _personalDetails, familyDetails: _familyDetails, licenceDetails: model.licenceDetails, registrationStage: 4 });
       // var _userProfileData = JSON.stringify({ licenceDetails: model.licenceDetails });
       //console.log(_userProfileData);
        //debugger;
        userService.UpdateAccountDetails(userProfileData).then(function (userAccountDetailsResponse) {
            if (userAccountDetailsResponse.status == "0") {
                console.log("User License response: ", userAccountDetailsResponse);
                toaster.pop("success", "License Details", userAccountDetailsResponse.message);
                $cookieStore.put("licenceDetails", model.licenceDetails);
                //model.$router.navigate(["UserAccountDetails"])
            } else {
                console.log("User Account Details response: ", userAccountDetailsResponse.Error);
                toaster.pop("error", "License Details", userAccountDetailsResponse.message);
            }

        }, function (error) {
            console.log("User License response: ", error);
            toaster.pop("error", "Register Profile", "Error in fetching user profile.");
        });
    }
  }

  module.component("userLicense", {
      templateUrl: './app/templates/userLicense.html',
    controllerAs: "model",
    controller: ["$cookieStore", "toaster", "userService", userLicenseController],
    bindings: {
      $router: '<'
    }
  })

}());
