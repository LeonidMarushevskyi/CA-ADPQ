var app = angular.module('cws');
app.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.when('/messages', '/messages/inbox');

    $stateProvider
        .state('main',{
            templateUrl: 'app/shared/main/main.html',
            abstract:true,
            controller: 'mainController',
            controllerAs: 'mainvm'
        })
        .state('authentication',{
            templateUrl: 'app/components/authentication/authentication.html',
            abstract:true,
            controller: 'authenticationController'
        })
        .state('authentication.login',{
            templateUrl: 'app/components/authentication/login/login.html',
            url: '/login',
            parent:'authentication',
            controller: 'loginController',
            authenticate: false
        })
        .state('authentication.forgotpass',{
            templateUrl: 'app/components/authentication/forgotpass/forgotpass.html',
            url: '/forgot-password',
            parent:'authentication',
            controller: 'forgotPassController',
            authenticate: false

        })
        .state('createprofile',{
            templateUrl: 'app/components/createprofile/createProfile.html',
            url: '/create-profile',
            controller: 'createProfileController',
            authenticate: false
        })
        .state('messages',{
            parent:'main',
            url:'/messages',
            templateUrl:'app/components/messages/messagesView.html',
            controller:'messagesController',
            authenticate: true
        })
        .state('editprofile',{
            // parent:'main',
            url:'/profile',
            templateUrl:'app/components/editprofile/editProfile.html',
            controller:'editProfileController',
            authenticate: true
        })
        .state('messages.inbox',{
            parent:'messages',
            url:'/inbox',
            templateUrl:'app/components/messages/inbox/inbox.html',
            controller:'inboxController',
            authenticate: true
        })
        .state('messages.sent',{
            parent:'messages',
            url:'/sent',
            templateUrl:'app/components/messages/sent/sent.html',
            controller:'sentController',
            authenticate: true
        })
        .state('messages.newmessage',{
            parent:'messages',
            url:'/new-message',
            templateUrl:'app/components/messages/newMessage/newMessage.html',
            controller:'newMessageController',
            authenticate: true
        })
        .state('messages.single',{
            parent:'messages',
            url:'/:id',
            templateUrl:'app/components/messages/single/singleMessage.html',
            controller:'singleMessageController',
            authenticate: true
        })
        .state('fosterAgencies',{
            parent:'main',
            url:'/foster-agencies',
            templateUrl:'app/components/fosterAgencies/fosterAgencies.html',
            controller: 'fosterAgenciesController',
            authenticate: true
        })
        .state('fosterAgency.single',{
            parent:'fosterAgency',
            url:'/foster-agency/:id',
            templateUrl:'app/components/fosterAgency/singleAgency.html',
            controller: 'singleAgencyController',
            authenticate: true
        });

    $urlRouterProvider.otherwise(function($injector){
        var $state = $injector.get("$state");
        $state.go('messages.inbox');
    });
}]);