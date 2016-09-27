(function (angular) {
    'use strict';
    // get ag-Grid to create an Angular module and register the ag-Grid directive
    agGrid.initialiseAgGridWithAngular1(angular);
    var module = angular.module("chhsDemo", ["toaster", "ngComponentRouter", "ngResource", "ngCookies", "angular-cache", "agGrid", 'ngSanitize','ui.bootstrap', 'dialogs.main', 'ngGeolocation']);

    module.config(function ($httpProvider, CacheFactoryProvider) {
      $httpProvider.defaults.useXDomain = true;
    //   delete $httpProvider.defaults.headers.common['X-Requested-With'];
      angular.extend(CacheFactoryProvider.defaults, { maxAge: 15 * 60 * 1000 });
    });
    module.value("$routerRootComponent", "mainApp");

    module.component("appAbout", {
       template: "This is a about page"
    });
}(window.angular))
