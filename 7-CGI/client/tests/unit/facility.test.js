describe('Facility Controller', function() {
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
	
	beforeEach(inject(function (_$rootScope_, $controller, _$state_, _gettextCatalog_, _$localStorage_, _LOCALES_, _session_, $httpBackend, _AUTH_EVENTS_, _$q_, _messageService_, _eventService_, _facilityService_) {
		$rootScope = _$rootScope_;
		scope = $rootScope.$new();
		$localStorage = _$localStorage_;
		LOCALES = _LOCALES_;
		session = _session_;
		AUTH_EVENTS = _AUTH_EVENTS_;
		$q = _$q_;
		messageService = _messageService_;
		eventService = _eventService_;
		facilityService = _facilityService_;
		
		deferred = _$q_.defer();
    
	    spyOn(facilityService, 'getFacilities').and.returnValue(deferred.promise);
		
		scope.$parent = $rootScope.$new();
		scope.$parent.userData = {id:1};
		
		$httpBackend.whenGET('main/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');
		
		$rootScope.$broadcast(AUTH_EVENTS.userInfo, {id:1});
		
		$rootScope.$digest();
		
		controller = $controller('FacilityController', {
			$scope: scope,
			//postman: postman,
			$state: {current: {name: currentState}, go: function() {}},
			gettextCatalog: _gettextCatalog_,
			$localStorage: _$localStorage_,
			LOCALES: LOCALES,
			facilityService: facilityService
		});
		
		$httpBackend.whenGET('main/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');

	}));

	var zipcode = '97209';
	var facilities = [
		{
			name: 'White Oaks',
			zipcode: 97201
		},
		{
			name: 'Salmon Plains',
			zipcode: 97201
		},
		{
			name: 'Santa Monica Foster Services',
			zipcode: 97201
		},
		{
			name: 'Red Oaks',
			zipcode: 97202
		},
		{
			name: 'Trout Plains',
			zipcode: 97202
		},
		{
			name: 'Santa Cruz Foster Services',
			zipcode: 97202
		}

	];
	
	it('gets facilities', function() {
		scope.getFacilities(zipcode);
		
		deferred.resolve(facilities);
		scope.$digest();
		
		expect(scope.facilities).toEqual(facilities);
	});
	
	it('watches zipcode input', function() {
		scope.input.zipcode = zipcode;
		deferred.resolve(facilities);
		
		scope.$apply();
		
		expect(scope.facilities).toEqual(facilities);
	});
});
