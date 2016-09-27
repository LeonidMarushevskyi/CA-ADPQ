'use strict';

describe('App Routes', function() {

  var $route;

  beforeEach(module('myApp'));

  beforeEach(inject(
    function(_$route_){
    // The injector unwraps the underscores (_) from around the parameter names when matching
    $route = _$route_;
  }));

  describe('New Profile Route', function(){
    it('Uses the correct Route and Controller', function(){
      expect($route.routes['/'].controller).toBe('ProfileController');
      expect($route.routes['/'].templateUrl).toBe('client/views/applications/profile.html');
    });
  });

  describe('New Inbox Route', function(){
    it('Uses the correct Route and Controller', function(){
      expect($route.routes['/inbox'].controller).toBe('InboxController');
      expect($route.routes['/inbox'].templateUrl).toBe('client/views/applications/inbox.html');
    });
  });

  describe('New Search Route', function(){
    it('Uses the correct Route and Controller', function(){
      expect($route.routes['/search'].controller).toBe('SearchController');
      expect($route.routes['/search'].templateUrl).toBe('client/views/applications/search.html');
    });
  });

  describe('New Facilities Route', function(){
    it('Uses the correct Route and Controller', function(){
      expect($route.routes['/facilities'].controller).toBe('FacilitiesController');
      expect($route.routes['/facilities'].templateUrl).toBe('client/views/applications/facilities.html');
    });
  });

  describe('Mobile Profile Route', function(){
    it('Uses the correct Route and Controller', function(){
      expect($route.routes['/mobile_profile'].templateUrl).toBe('client/views/applications/mobile_profile.html');
    });
  });

  describe('Desktop Profile Route', function(){
    it('Uses the correct Route and Controller', function(){
      expect($route.routes['/desktop_profile'].templateUrl).toBe('client/views/applications/desktop_profile.html');
    });
  });
});
