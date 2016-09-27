describe('Home Controller', function() {
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
    
	    spyOn(messageService, 'getMessages').and.returnValue(deferred.promise);
	    spyOn(familyService, 'getPlans').and.returnValue(deferred.promise);
	    spyOn(eventService, 'getEvents').and.returnValue(deferred.promise);
		
		scope.$parent = $rootScope.$new();
		scope.$parent.userData = {id:1};
		
		$httpBackend.whenGET('main/home.html').respond('home view');
		//$httpBackend.expectGET('staticPages/home.html');
		
		$httpBackend.whenGET('user/login.html').respond('login page');
		//$httpBackend.expectGET('user/login.html');
		
		$httpBackend.whenGET(ENDPOINTS.profile + 'view/1').respond('get user');
		
		$rootScope.$broadcast(AUTH_EVENTS.userInfo, {id:1});
		
		$rootScope.$digest();
		
		controller = $controller('HomeController', {
			$scope: scope,
			//postman: postman,
			$state: {current: {name: currentState}, go: function() {}},
			gettextCatalog: _gettextCatalog_,
			$localStorage: _$localStorage_,
			LOCALES: LOCALES,
			messageService: messageService
		});
	}));
	
	it('gets messages', function() {
		expect(scope.unreadCount).toBe(0);
		
		var messages = [{ read: true }, { read: false }];
		
		deferred.resolve([{ read: true }, { read: false }]);
    
	    scope.$apply();
	    
	    //expect(scope.unreadCount).toBe(1);
	    
	    //expect(messages).toEqual(messages);
	});	
	
	it('gets plans', function() {
		var plans = [
			{
				firstName: 'Walter',
				lastName: 'Woolf',
				profilePicture: 'https://dl.dropboxusercontent.com/s/6fcdjqaufim9njo/u298.png',
				location: 'White Plains Group Home',
				id: 1,
				hasPlan: true,
				relationship: 'son',
				gender: 'Male',
				dob: '2005-01-15',
				group: true
			},
			{
				firstName: 'Lilly',
				lastName: 'Woolf',
				profilePicture: 'https://dl.dropboxusercontent.com/s/9f3kny5kq0saalh/u322.png',
				location: 'Palo Alto Foster Services',
				id: 2,
				hasPlan: true,
				relationship: 'daughter',
				gender: 'Female',
				dob: '2010-03-22',
				group: true,
				home: true
			},
			{
				firstName: 'Leonard',
				lastName: 'Woolf',
				profilePicture: 'https://dl.dropboxusercontent.com/s/5bp3skd2joxc5w8/lwoolf.jpeg',
				location: 'Palo Alto Foster Services',
				id: 3,
				hasPlan: false,
				relationship: 'husband',
				gender: 'Male',
				dob: '1972-07-14'
			}
		];	
		
		deferred.resolve(plans);
    
	    scope.$apply();
	    
	    //expect(scope.plans.length).toBe(plans.length);
	});	
	
	
	
	it('gets events', function() {
		var data = [
			{
				id: 1,
				title: 'Visitation',
				date: '2016-05-22',
				completed: false,
				eventFor: 1 
			},
			{
				id: 2,
				title: 'Complete Evaluation',
				date: '2016-04-22',						
				completed: true,
				eventFor: 1
			},
			{
				id: 3,
				title: 'Home Checklist',
				date: '2016-05-24',						
				completed: false,
				eventFor: 2
			},
			{
				id: 4,
				title: 'Group Course',
				date: '2016-05-27',
				completed: false,
				eventFor: 2
			}
		];
		
		deferred.resolve(data);
		
		scope.$apply();
		
		//expect(scope.events.length).toBe(4);
	});

});
