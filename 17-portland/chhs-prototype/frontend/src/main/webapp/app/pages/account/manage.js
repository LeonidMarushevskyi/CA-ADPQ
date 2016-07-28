'use strict';

angular.module('chhs').controller('manageAccountCtrl', function (Account, account, STATE_LIST) {
  var self = this;
  self.account = account;
  self.states = STATE_LIST;

  this.update = function () {
    self.updating = true;
    Account.update(self.account).then(function () {
      self.updating = false;
      self.updated = true;
    }, function () {
      self.updating = false;
      self.error = true;
    });
  };

  this.cancel = function(){
    Account.getUserAccount().then(function(freshAccount){
      self.account = freshAccount;
    });
  };
});
