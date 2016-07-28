/**
 * Created by Husamui on 5/31/16.
 */
(function() {
    'use strict';

angular.module('cws')
    .factory('User', ['AuthService','$q','$http','API_LINKS','$resource', function(AuthService, $q, $http, API_LINKS, $resource) {
        var user = {};


        user.resource = $resource(API_LINKS.baseurl+'users/:id', { id: '@id' }, {
            update: {
                method: 'PUT' // this method issues a PUT request
            }
        }, {
            stripTrailingSlashes: false
        });


        user.login = function(email, pw) {
            return $q(function(resolve, reject) {
                $http.post(API_LINKS.baseurl + 'session' ,{'email':email, 'password': pw})
                    .then(function(res, status) {
                            if (res.data.success == true){
                                AuthService.storeUserCredentials(res.data);
                                resolve('Login success.');
                                user.data = user.resource.get({ id: 'me'});
                                // user.data = user.resource.get({ id: res.data.userId});
                            }else{
                                reject('Login Failed.');
                            }
                        },
                        function(){
                            reject('Login Failed.');
                        });
            });
        };



        if(AuthService.isAuthenticated()){
            user.data = user.resource.get({ id: 'me'});
            // user.data = user.resource.get({ id: AuthService.getUserId()});
        }


        return user


        }]);
}());