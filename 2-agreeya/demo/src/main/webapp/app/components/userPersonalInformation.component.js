(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");

  function userPersonalInformationController(toaster, $cookieStore, userService) {
    var model = this;
    model.showSpouseDetails = true;
    model.spouseClass = "glyphicon glyphicon-minus";

    model.onChangeSpouseDetails = function(){
        if(model.showSpouseDetails === true) {
            model.showSpouseDetails = false;
            model.spouseClass = "glyphicon glyphicon-plus";
        } else {
            model.showSpouseDetails = true;
            model.spouseClass = "glyphicon glyphicon-minus";
        }
    }
    model.personalDetails = {};
    model.personalDetails.spouseDetails = {};

    var context = {};
    var _personalProfile = {};
    var _userName = '';
    if ($cookieStore.get('userContext')) {
        var _context = $cookieStore.get('userContext');
        context = _context.userContext;
        _userName = context.userName;
    };
    if ($cookieStore.get('personalProfile')) {
       _personalProfile = $cookieStore.get('personalProfile');
    };

    model.races = [
     { value: "", text: "Select Race" },
     { value: "White", text: "White" },
     { value: "Black or African American", text: "Black or African American" },
     { value: "American Indian and Alaska Native", text: "American Indian and Alaska Native" },
     { value: "Native Hawaiian and Other Pacific Islander", text: "Native Hawaiian and Other Pacific Islander" },
     { value: "Asian", text: "Asian" },
     { value: "Some other race", text: "Some other race" }
    ];

    model.martialStatus = [
     { value: "", text: "Select Martial Status" },
     { value: "Married", text: "Married" },
     { value: "Single", text: "Single" }
    ];
    model.personalDetails.maritalStatus = model.martialStatus[0].value;



    model.genders = [
      { value: "", text: "Select Gender" },
      { value: "M", text: "Male" },
      { value: "F", text: "Female" }
    ];
    if (!model.personalDetails.gender) {
        model.personalDetails.gender = model.genders[0].value;
    }
    if (!model.personalDetails.spouseDetails.gender) {
        model.personalDetails.spouseDetails.gender = model.genders[0].value;
    }

    model.formatDate = function (input) {
        //debugger;
        var datePart = input.match(/\d+/g),
        year = datePart[0], // get only two digits
        month = datePart[1], day = datePart[2];

        return month + '/' + day + '/' + year;
    }

    var userInfo = {};
    var userData = JSON.stringify({ userName: _userName });
    userService.GetAccountDetails(userData).then(function (userDataResponse) {
        userInfo = userDataResponse;
        if (userInfo.user != null) {
            _personalProfile = userInfo.user.personalProfile;
            model.personalDetails = userInfo.user.personalDetails;
            //debugger;

            _personalProfile.password = 'none';
            if (model.personalDetails.dob) {
                var newDate = model.formatDate(model.personalDetails.dob);
                model.personalDetails.dob = newDate;
            }

            if (model.personalDetails.spouseDetails.dob) {
                var newDate = model.formatDate(model.personalDetails.spouseDetails.dob);
                model.personalDetails.spouseDetails.dob = newDate;
            }

            //debugger;
        }
    }, function (error) {
        console.log("User Info response: ", error);
        toaster.pop("error", "Unable to fetch user info", "Error in fetching user profile.");
    });



    if (model.personalDetails.maritalStatus == "" || !model.personalDetails.maritalStatus) {
        model.personalDetails.maritalStatus = model.martialStatus[0].value;
    }
    console.log(model.personalDetails.maritalStatus);
    //debugger;

    model.religions = [
         { value: "", text: "Select Religion" },
         { value: "Christian", text: "Christian" },
         { value: "Muslim", text: "Muslim" },
         { value: "Jew", text: "Jew" },
         { value: "Hindu", text: "Hindu" }
    ];
    if (!model.personalDetails.religion) {
        model.personalDetails.religion = model.religions[0].value;
    }
    if (!model.personalDetails.spouseDetails.religion) {
        model.personalDetails.spouseDetails.religion = model.religions[0].value;
    }



    if (!model.personalDetails.race) {
        model.personalDetails.race = model.races[0].value;
    }
    if (!model.personalDetails.spouseDetails.race) {
        model.personalDetails.spouseDetails.race = model.races[0].value;
    }

    model.preferences = [
        { value: "", text: "Select Preference" },
        { value: "prefA", text: "prefA" },
        { value: "prefB", text: "prefB" },
        { value: "prefC", text: "prefC" },
        { value: "prefD", text: "prefD" },
        { value: "prefE", text: "prefE" }
    ];
    if (!model.personalDetails.preference) {
        model.personalDetails.preference = model.preferences[0].value;
    }
    if (!model.personalDetails.spouseDetails.preference) {
        model.personalDetails.spouseDetails.preference = model.preferences[0].value;
    }


    model.updatePersonalInformation = function () {



        var userProfileData = JSON.stringify({ userContext: context, personalProfile: _personalProfile, personalDetails: model.personalDetails, spouseDetails: model.personalDetails.spouseDetails, registrationStage: 2 });


        userService.UpdateAccountDetails(userProfileData).then(function (userAccountDetailsResponse) {
            if (userAccountDetailsResponse.status == "0") {
                console.log("User Personal Information response: ", userAccountDetailsResponse);
                toaster.pop("success", "Personal Information", userAccountDetailsResponse.message);
                $cookieStore.put("personalDetails", model.personalDetails);
                model.$router.navigate(["UserPreferences"])
            } else {
                console.log("User Personal Information response: ", userAccountDetailsResponse.Error);
                toaster.pop("error", "Personal Information", userAccountDetailsResponse.message);
            }

        }, function (error) {
            console.log("User Personal Information response: ", error);
            toaster.pop("error", "Personal Information", "Error in Personal Information.");
        });
    }

  }

  module.component("userPersonalInformation", {
      templateUrl: './app/templates/userPersonalInformation.html',
    controllerAs: "model",
    controller: ["toaster", "$cookieStore", "userService", userPersonalInformationController],
    bindings: {
      $router: '<'
    }
  })

}(window.angular));
