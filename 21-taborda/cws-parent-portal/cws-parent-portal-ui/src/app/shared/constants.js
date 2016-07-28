/**
 * Created by Husamui on 5/29/16.
 */
(function() {
    'use strict';
angular.module('cws')
    .constant('API_LINKS',window.__env)

    .constant('ERRORS', {
        notAuthenticated: 'auth-not-authenticated',
        notFound: 'resource-not-found',
        badRequest: 'bad-request',
        server: 'server-internal-error'
    });
}());