(function() {
    'use strict';
angular.module('cws').factory('FosterAgency', [ '$resource', 'API_LINKS', function($resource, API_LINKS) {
    return $resource(API_LINKS.baseurl+'agencies/:id');
}]);
}());