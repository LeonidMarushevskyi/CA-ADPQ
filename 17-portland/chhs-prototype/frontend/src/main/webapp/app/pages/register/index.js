'use strict';

angular.module('chhs').controller('registerCtrl', function ($location, Account, STATE_LIST) {

  var register = this;
  register.data = {
    address: {
      state: 'CA'
    }
  };
  register.accountCreated = false;
  register.accountError = false;

  register.states = STATE_LIST;

  register.cancel = function () {
    $location.path('home');
  };

  register.createAccount = function (isValid) {
    register.submitted = false;
    register.accountError = false;
    if (isValid && register.customValidation()) {
      var accountData = angular.copy(register.data);
      accountData.address.country = 'USA';

      Account.createAccount(accountData)
        .then(function (response) {
          register.accountCreated = true;
        }, function (e) {
          register.accountError = true;
        });

    } else {
      register.submitted = true;
    }
  };

  register.postalCodeValid = function () {
    var reg = /^[0-9]{5}(?:-[0-9]{4})?$/;
    if (register.data.address !== undefined) {
      return reg.test(register.data.address.postalCode);
    } else {
      return false;
    }
  };

  register.emailsMatch = function () {
    return register.data.email === register.data.emailre;
  };

  register.passwordsMatch = function () {
    return register.data.newPassword === register.data.newPasswordConfirmation;
  };

  register.passwordValid = function () {
    var reg = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{10,}/;
    return reg.test(register.data.newPassword);
  };

  register.customValidation = function () {
    return register.postalCodeValid() && register.emailsMatch() && register.passwordValid() && register.passwordsMatch();
  };

});
