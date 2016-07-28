
'use strict';

angular.module('chhs').directive('registrationEmailValidator', function ($http, $q, $log) {
  return {
    restrict: 'A',
    require: 'ngModel',
    link: function (scope, elem, attrs, ngModel) {
      $log.debug('Setting up email validation.');
      ngModel.$asyncValidators.uniqueEmail = function (modelValue, viewValue) {
        var value = modelValue || viewValue;
        $log.debug('Verifying email' + value + ' is available for use.');
        return $http.get('/api/account/available', {params: {email: value}}).then(function () {
          return true;
        }, function () {
          return $q.reject('not available');
        });
      };
    }
  };
});
