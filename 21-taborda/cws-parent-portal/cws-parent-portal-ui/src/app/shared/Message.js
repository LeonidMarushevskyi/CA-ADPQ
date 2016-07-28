/**
 * Created by Husamui on 5/31/16.
 */
(function() {
    'use strict';
angular.module('cws')
    .factory('Message', ['API_LINKS','$resource', function(API_LINKS,$resource) {

        var message = {};
        message.resource = $resource(API_LINKS.baseurl+'message/:id', { id: '@id' }, {
            update: {
                method: 'PUT' // this method issues a PUT request
            }
        }, {
            stripTrailingSlashes: false
        });





        return message


        }]);
}());