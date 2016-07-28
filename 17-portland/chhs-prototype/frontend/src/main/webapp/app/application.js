'use strict';

var app = angular.module('chhs', ['ngCookies', 'ngRoute', 'uiGmapgoogle-maps'])
  .run(function ($cookies, $http, $log) {
    if ($cookies.get('Token')) {
      $log.debug('Auth token found, adding to all requests.');
      $http.defaults.headers.common.Token = $cookies.get('Token');
    }

  });
  
  app.constant('STATE_LIST', ['AK', 'AL', 'AR', 'AZ', 'CA', 'CO', 'CT', 'DC', 'DE', 'FL', 'GA', 'GU', 'HI', 'IA', 'ID', 'IL', 'IN', 'KS', 'KY', 'LA', 'MA', 'MD', 'ME', 'MH', 'MI', 'MN', 'MO', 'MS', 'MT', 'NC', 'ND', 'NE', 'NH', 'NJ', 'NM', 'NV', 'NY', 'OH', 'OK', 'OR', 'PA', 'PR', 'PW', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VA', 'VI', 'VT', 'WA', 'WI', 'WV', 'WY']);
