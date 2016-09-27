describe('Main Controller', function() {
	beforeEach(module('ui.router'));
	beforeEach(module('gettext'));
	beforeEach(module('ngStorage'));
	beforeEach(module('cgiAdpq'));
	beforeEach(module('cgiAdpq.main'));
	
	var controller,
	$rootScope,
	scope,
	$localStorage,
	LOCALES,
	sessioin,
	AUTH_EVENTS;
	
	var currentState = 'login';
	var otherState = 'contact';
	
	beforeEach(inject(function (_$rootScope_, $controller, _$state_, _gettextCatalog_, _$localStorage_, _LOCALES_, _session_, $httpBackend, _AUTH_EVENTS_, ENDPOINTS) {
		$rootScope = _$rootScope_;
		scope = $rootScope.$new();
		$localStorage = _$localStorage_;
		LOCALES = _LOCALES_;
		session = _session_;
		AUTH_EVENTS = _AUTH_EVENTS_;
		
		controller = $controller('MainController', {
			$scope: scope,
			//postman: postman,
			$state: {current: {name: currentState}, go: function() {}},
			gettextCatalog: _gettextCatalog_,
			$localStorage: _$localStorage_,
			LOCALES: LOCALES
		});
		
		$httpBackend.whenGET('main/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');

		$httpBackend.whenGET(ENDPOINTS.profile + 'view/1').respond('get user');
		
		//$httpBackend.whenGET('https://cgi-test.herokuapp.com/service/view/1').respond('');


	}));
	
	it('has a default current locale', function () {
		expect(scope.currentLocale).toEqual(LOCALES.English);
	});
	
	it('remembers the chosen locale', function () {
		var localeToSet = LOCALES.Spanish;
		
		scope.setLocale(localeToSet);
		
		expect(scope.currentLocale).toEqual(localeToSet);
		expect($localStorage.currentLocale).toEqual(localeToSet);
	});
	
	it('logs out', function() {
		scope.logout();
		
		expect(session.data).toEqual({});
	});
	
	it('sets the page name based on the route', function() {
		var pageName = 'login';
		$rootScope.$broadcast('$stateChangeStart', {name: pageName});
		$rootScope.$digest();
		
		expect(scope.page).toEqual(pageName);
	});
	
	it ('gets user data on auth event', function() {
		$rootScope.$broadcast(AUTH_EVENTS.userInfo, {firstName: 'tester'});
		$rootScope.$digest();
		
		expect(scope.userData).not.toEqual({});
		
	});
	

});
