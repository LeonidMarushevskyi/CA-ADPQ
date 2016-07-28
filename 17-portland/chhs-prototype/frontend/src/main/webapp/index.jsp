<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CHHS</title>

    <link rel="stylesheet" href="/app/css/styles.css" media="screen"/>

  </head>
  <body ng-app="chhs">

    <div class="site-header"></div>
    <div class="ng-view"></div>
    <div class="site-footer"></div>

    <!-- Third-party libraries -->
    <script src='//maps.googleapis.com/maps/api/js?sensor=false'></script>

    <!-- build:externalJs lib/lib-all.js -->
    <script src='bower_components/lodash/dist/lodash.js'></script>
    <script src="bower_components/jquery/dist/jquery.js"></script>
    <script src="bower_components/angular/angular.js"></script>
    <script src="bower_components/angular-cookies/angular-cookies.js"></script>
    <script src="bower_components/angular-route/angular-route.js"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src='bower_components/angular-simple-logger/dist/angular-simple-logger.js'></script>
    <script src='bower_components/angular-google-maps/dist/angular-google-maps.js'></script>
    <!-- endbuild -->

    <!-- build:appJs app/app-all.js -->
    <script src="app/application.js"></script>
    <script src="app/routes.js"></script>
    <script src="app/factories/auth.js"></script>
    <script src="app/factories/account.js"></script>
    <script src="app/factories/fosterHomes.js"></script>
    <script src="app/factories/messages.js"></script>
    <script src="app/pages/account/manage.js"></script>
    <script src="app/pages/home/index.js"></script>
    <script src="app/pages/login/index.js"></script>
    <script src="app/pages/register/directives/registrationEmailValidator.js"></script>
    <script src="app/pages/register/index.js"></script>
    <script src="app/pages/dashboard/index.js"></script>
    <script src="app/pages/support/index.js"></script>
    <script src="app/directives/site-header/index.js"></script>
    <script src="app/directives/site-footer/index.js"></script>
    <script src="app/directives/foster-home-locator/index.js"></script>
    <script src="app/directives/case-worker-messenger/index.js"></script>
    <script src="app/directives/site-banner/index.js"></script>
    <script src="app/directives/site-account-cta/index.js"></script>
    <script src="app/directives/terms-conditions/index.js"></script>
    <!-- endbuild -->

  </body>
</html>
