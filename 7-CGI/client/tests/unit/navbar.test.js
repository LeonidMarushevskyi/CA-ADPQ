describe('Navbar Controller', function() {
	beforeEach(module('ui.router'));
	beforeEach(module('cgiAdpq.user'));
	beforeEach(module('cgiAdpq.nav'));
	
	var NavbarController,
	scope,
	$state;
	
	var currentState = 'login';
	var otherState = 'contact';
	
	beforeEach(inject(function ($rootScope, $controller, _$state_, _userService_, _$injector_, $templateCache) {
		scope = $rootScope.$new();
		
		NavbarController = $controller('NavBarController', {
			$scope: scope,
			$rootScope: $rootScope,
			$state: {current: {name: currentState}},
			user: _userService_
		});
	}));
	
	it('has only the current state as active', function () {
		var isActive = scope.isActive(currentState);
		var notActive = scope.isActive(otherState);

		expect(isActive).toBe('active');
		expect(notActive).toBe('');
	});
	
	it('collapses the navbar', function () {
		scope.navCollapsed = false;
		expect(scope.navCollapsed).toBe(false);
		
		scope.collapse()
		expect(scope.navCollapsed).toBe(true);
	});

});
