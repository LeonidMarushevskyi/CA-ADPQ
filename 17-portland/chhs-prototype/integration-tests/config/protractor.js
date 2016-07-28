exports.config = {
	//////////////////////
	// TESTS
	/////////////////////
	specs: ['setup.feature','../features/**/*.feature'],
	//////////////////////
	// USERS
	/////////////////////
	// baseUrl: 'http://localhost:8080/#/',
	baseUrl: 'https://chhs-prototype.portlandwebworks.com/#/',
	params: {
  	},
		allScriptsTimeout: 30000,
	//////////////////////
	// SELENIUM / WEB DRIVERS
	/////////////////////
	/* Option 1: Use Provided Standalone Server */
	// seleniumServerJar: '../node_modules/selenium-server/lib/runner/selenium-server-standalone-2.38.0.jar',
	// seleniumPort: 4444,
	/* Option 2: Use Remote Selenium Server */
	seleniumAddress: 'http://localhost:4444/wd/hub',

	chromeDriver: '../node_modules/chromedriver/bin/chromedriver',

	// Browsers to Test Against
	multiCapabilities: [
		{ 'browserName': 'chrome' },
		//{ 'browserName': 'firefox' },
		//{ 'browserName': 'phantomjs' }
	],


	framework: 'cucumber',
	cucumberOpts: {
		tags: '@prod',
		// define your step definitions in this file
		require: '../features/step_definitions/base_steps.js',
		format: 'pretty'
	}

	//////////////////////
	// JASMINE
	/////////////////////
	/*
	onPrepare: function() {
		require('jasmine-reporters');
		jasmine.getEnv().addReporter(new jasmine.JUnitXmlReporter('results', true, true));
	},
	jasmineNodeOpts: {
		showColors: false,
		defaultTimeoutInterval: 360000
	}*/

};
