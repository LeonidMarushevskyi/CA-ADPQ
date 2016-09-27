describe('App', function() {
	function mockTemplate(templateRoute, tmpl) {
		//console.log(templateRoute, tmpl);
		
		//$templateCache.put(templateRoute, tmpl || templateRoute);
	}
	
	function goTo(url) {
		$location.url(url);
		$rootScope.$apply();
	}
	
	function goFrom(url) {
		return {toState: function (state, params) {
			$location.replace().url(url); //Don't actually trigger a reload
			$state.go(state, params);
			$rootScope.$apply();
		}};
	}

	beforeEach(module('cgiAdpq'));
	
	var $rootScope,
	$httpBackend,
	authService,
	$localStorage,
	$state,
	$templateCache;
	
	beforeEach(inject(function (_$rootScope_, $controller, _$q_, _$rootScope_, _$httpBackend_, _authService_, _$localStorage_, _$state_, _$templateCache_, _$location_) {
		$rootScope = _$rootScope_;
		$httpBackend = _$httpBackend_;
		authService = _authService_;
		$localStorage = _$localStorage_;
		$state = _$state_;
		$templateCache = _$templateCache_;
		$location = _$location_;
		
		$httpBackend.whenGET('staticPages/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');
		
	}));
	
//	beforeEach(mockTemplate('', 'staticPages/login.html'));
//	beforeEach(mockTemplate.bind('home', 'staticPages/login.html'));

	it('remembers the last route prior to login', function () {	
		$templateCache.put('login', 'user/login.html');
			
		var nextState = {name: 'home', params: {}};
		$localStorage.nextState = nextState;
		
		goTo('/login');
		
		//console.log($state);
		
		//expect($localStorage.nextState).toBe(undefined);
		
	});



});
