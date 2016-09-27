describe('Edit Profile Controller', function() {
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
	deferred,
	$stateParams;
	
	var currentState = 'login';
	var otherState = 'contact';
	
	beforeEach(inject(function (_$rootScope_, $controller, _$state_, _gettextCatalog_, _$localStorage_, _LOCALES_, _session_, $httpBackend, _AUTH_EVENTS_, _$q_, _messageService_, _eventService_, _familyService_, _$stateParams_) {
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
		$stateParams = _$stateParams_;
		deferred = _$q_.defer();
    
	    spyOn(familyService, 'getUser').and.returnValue(deferred.promise);
		
		
		$httpBackend.whenGET('main/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');
		
		scope.$parent = $rootScope.$new();
		scope.$parent.userData = {id:1};
		
		$rootScope.$broadcast(AUTH_EVENTS.userInfo, {id:1});
		
		$rootScope.$digest();
		
		controller = $controller('EditProfileController', {
			$scope: scope,
			//postman: postman,
			$state: {current: {name: currentState}, go: function() {}},
			gettextCatalog: _gettextCatalog_,
			$localStorage: _$localStorage_,
			LOCALES: LOCALES,
			messageService: messageService
		});
		
	}));
	
	var user = {id: 1, name:'User 1'};
	var member = {id: 2, name:'User 2'};
	
	it('gets the logged in user to edit', function() {
		deferred.resolve(user);
    
	    scope.$apply();
	    
	    expect(scope.user).toEqual(user);
	});	
	
	it('gets a family member to edit', function() {
		$stateParams.id = 2
		deferred.resolve(member);
    
	    scope.$apply();
	    
	    expect(scope.user).toEqual(member);
	});	
	
	
	
	
	
	it('clears a facility', function() {
		scope.input.zipcode = '97209';
		scope.facilties = [1,2,3];
		scope.resultsReturned = true;
		
		scope.cancelFacilities();
		
		expect(scope.input.zipcode).toEqual('');
		expect(scope.facilities).toEqual([]);
		expect(scope.resultsReturned).toEqual(false);
	});
	
	it('selects a facility', function() {
		var facility = {name: 'Facility 1', zipcode: '97209'};
		
		scope.input.zipcode = '97209';
		scope.facilties = [1,2,3];
		scope.resultsReturned = true;

		
		scope.selectFacility(facility);

		expect(scope.user.facility).toEqual(facility.name);
		
		expect(scope.input.zipcode).toEqual('');
		expect(scope.facilities).toEqual([]);
		expect(scope.resultsReturned).toEqual(false);

	});
	
	it('sets the user location', function() {
		scope.user = {location:'test'};
		
		expect(scope.showFacility).toEqual(false);
		
		scope.user.location = '';
		scope.$apply();
		
		scope.user.location = 'In Placement';
		scope.$apply();
		
		//expect(scope.showFacility).toEqual(true);
	});

	

});
