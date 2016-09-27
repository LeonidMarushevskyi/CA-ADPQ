describe('Auth Service', function() {

	beforeEach(module('cgiAdpq'));
	
	var authService, session, deferred, $rootScope, $httpBackend;
	
	beforeEach(inject(function (_authService_, $q, _gettextCatalog_, _session_, _$rootScope_, _$httpBackend_) {
		authService = _authService_;
		session = _session_;
		$rootScope = _$rootScope_;
		$httpBackend = _$httpBackend_;
		
		//deferred = $q.defer();
		//spyOn(authService, 'login').and.returnValue(deferred.promise); 
		
		$httpBackend.whenGET('main/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');
	}));
	
	afterEach(function() {
		$httpBackend.flush();
	});
	
	it('successfully logs in', function () {
		var password = 'testing' + new Date().getDate();
		
		authService.login({email: 'testing@test.com', password: password}).then(function(data) {
			expect(data).toEqual('success');
		});
	});
	
	it('fails logging in', function () {
		var password = 'testing';
		
		authService.login({email: 'testing@test.com', password: password}).then(function(){}, function(data) {
			expect(data).toEqual('failure');
		});
	});
	
	it('checks existing authorization', function () {
		var expiration = new Date();
		expiration.setDate(expiration.getDate() + 7);
			
		session.create({sessionId: new Date().getTime(), userId: new Date().getTime(), expiration: expiration});
		
		expect(authService.isAuthenticated()).toBe(true);
	});
});
