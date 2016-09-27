describe('Profile Controller', function() {
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
	AUTH_EVENTS,
	$q,
	messageService,
	eventService,
	familyService,
	deferred;
	
	var currentState = 'login';
	var otherState = 'contact';
	
	beforeEach(inject(function (_$rootScope_, $controller, _$state_, _gettextCatalog_, _$localStorage_, _LOCALES_, _session_, $httpBackend, _AUTH_EVENTS_, _$q_, _messageService_, _eventService_, _familyService_, ENDPOINTS) {
		$rootScope = _$rootScope_;
		scope = $rootScope.$new();
		$localStorage = _$localStorage_;
		LOCALES = _LOCALES_;
		session = _session_;
		AUTH_EVENTS = _AUTH_EVENTS_;
		$q = _$q_;
		messageService = _messageService_;
		eventService = _eventService_;
		familyService = _familyService_;
		
		deferred = _$q_.defer();
    
	    spyOn(familyService, 'getFamily').and.returnValue(deferred.promise);
		
		
		$httpBackend.whenGET('main/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');
		
		$httpBackend.whenGET(ENDPOINTS.profile + 'view/1').respond('get user');
		
		scope.$parent = $rootScope.$new();
		scope.$parent.userData = {id:1};
		
		$rootScope.$broadcast(AUTH_EVENTS.userInfo, {id:1});
		
		$rootScope.$digest();
		
		controller = $controller('ProfileController', {
			$scope: scope,
			//postman: postman,
			$state: {current: {name: currentState}, go: function() {}},
			gettextCatalog: _gettextCatalog_,
			$localStorage: _$localStorage_,
			LOCALES: LOCALES,
			messageService: messageService
		});
		
	}));
	
	var data = [
		{name:'User 1'}, {name: 'User 2'}
	];
	
	it('gets family', function() {
		deferred.resolve(data);
    
	    scope.$apply();
	    
	    //expect(scope.family).toBe(data);
	});	
	

});
