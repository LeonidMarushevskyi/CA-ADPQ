'use strict';

describe('Inbox Module', function () {
  var $controller;
  var $scope;

  beforeEach(module('inboxModule'));
  beforeEach(module('ngMaterial'));

  beforeEach(function() {
    $scope = {};

    inject(function ($controller, $mdDialog) {
      var $controller = $controller('InboxController', {$scope: $scope});
    })
  });

  describe('Inbox Controller', function () {

    describe('Messages List', function () {

      it('is defined', function () {
        expect($scope.inboxData).toBeDefined();
      });

      it('has a title', function () {
        expect($scope.inboxData[0].title).toContain('Welcome');
      });

      it('has a from', function () {
        expect($scope.inboxData[0].from).toContain('From: Francis');
      });

      it('has 2 messages', function () {
        expect($scope.inboxData.length).toEqual(2);
      });
    });

    describe('Sent Message', function(){

      it('has message text', function(){
        expect($scope.sent_message).toBeDefined();
      });

      it('has a mdDialog', inject(function($mdDialog) {
        expect($mdDialog).not.toBe(null);
      }));

      it('has a dialog function', function() {
        expect($scope.showMessageDialog).toBeDefined();
      });

      it('mdDialog has a title', function() {
        expect($scope.sent_message[0].title).toContain('Can you help?');
      });

      it('mdDialog has a textContent', function() {
        expect($scope.sent_message[0].body).toContain('Hi Francis, I am struggling to balance everything');
      });
    });

    describe('View Message', function() {

      it('has message body', function() {
        expect($scope.inboxData[0].body).toContain('Welcome to your Child');
      });

      it('has a showMessage function', function() {
        expect($scope.showMessage).toBeDefined();
      });

    });

  });
});
