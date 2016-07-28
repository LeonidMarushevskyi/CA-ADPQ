(function() {
    'use strict';

    function spinner() {
    return {
        request: function(config) {
            $(".loading").addClass('active');
            return config;
        },

        requestError: function(config) {
            $(".loading").removeClass('active');
            return config;
        },

        response: function(res) {
            $(".loading").removeClass('active');
            return res;
        },

        responseError: function(res) {
            $(".loading").removeClass('active');
            return res;
        }
    }
}

angular.module('cws')
    .factory('spinner', spinner)
    .config(['$httpProvider',function($httpProvider) {
        $httpProvider.interceptors.push('spinner');
    }]);
}());

