module.exports = function(config) {
  config.set({
    basePath: '../../',
    frameworks: ['jasmine'],
    browsers: ['PhantomJS'],
    plugins : [
        'karma-phantomjs-launcher',
        'karma-jasmine',
        'karma-junit-reporter',
        'karma-coverage'
    ],
    files: [
		'../bower_components/angular/angular.js',
	    '../bower_components/angular-mocks/angular-mocks.js',
		'tmp/**/*.js',
	    'src/**/*.js',
    	'tests/unit/**/*.test.js'
	],
	//logLevel: config.LOG_DEBUG,
	included: false,
	singleRun: true,
	reporters: ['progress', 'coverage', 'dots', 'junit'],
	junitReporter: {
	  outputFile: 'test-results.xml'
	},

    preprocessors: {
		'src/**/*.js': ['coverage']
    },
    
    coverageReporter: {
		dir : 'tmp/coverage',
		reporters: [
			{ type: 'html', subdir: '.' },
			{ type: 'text', subdir: '.', file: 'text.txt' },
	        { type: 'text-summary', subdir: '.', file: 'text-summary.txt' }
		]
    }
  });
};
