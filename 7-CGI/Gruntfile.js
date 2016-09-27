module.exports = function (grunt) {
    var libFiles = {
        //"jquery.js": "jquery/client/dist/jquery.js",
        //"lodash.min.js": "lodash/dist/lodash.min.js",
        //"moment.min.js": "moment/min/moment.min.js",
        "postman.min.js": "angular-postman/js/postman.min.js",
        "lodash.min.js": "lodash/dist/lodash.min.js",
        "ui-bootstrap.min.js": "angular-bootstrap/ui-bootstrap.min.js",
        "ui-bootstrap-tpls.min.js": "angular-bootstrap/ui-bootstrap-tpls.min.js",
        //"angular-mocks.js": "angular-mocks/angular-mocks.js",
        "angular-ui-router.min.js": "angular-ui-router/release/angular-ui-router.min.js",
        "ngStorage.min.js": "ngstorage/ngStorage.min.js",
        "angular-gettext.min.js": "angular-gettext/dist/angular-gettext.min.js"
    };

    grunt.initConfig({
    	watch: {
            css: {
                files: ['client/src/sass/*.scss', 'client/src/**/*.js', 'client/src/**/*.html'],
                tasks: ['dev'],
                options: {
                    spawn: false,
                },
            },
        },
        
        clean: {
	        dist: ['client/dist/*'],
	        test1: ['client/tmp/'],
	        test2: ['client/tmp/angular.min.js']
        },
        
		sass: {
	        options: {
	            sourceMap: true
	        },
	        dist: {
	            files: {
	                'client/dist/css/style.css': 'client/src/sass/main.scss'
	            }
	        }
        },
	        
        bowercopy: {
            options: {
                runBower: false,
                destPrefix: "client/dist/js/"
            },
            
            angular: {
	            files: {
		            "angular.js": "angular/angular.js"
	            }
            },
            angularmin: {
	            files: {
		            "angular.min.js": "angular/angular.min.js"
	            }
            },
            
            libs: {
                files: libFiles
            },  
            
            test: {
	            options: {
	                destPrefix: "client/tmp/"
	            },
	        	files: libFiles
            },
            
            css: {
                options: {
                    destPrefix: "client/src/sass"
                },
                files: {
                    "_bootstrap.scss": "bootstrap/dist/css/bootstrap.css",
                    "_postman.scss": "angular-postman/scss/postman.scss"
                }
            },
        },
        
        ngtemplates:  {
			cgiAdpq: {
				cwd: 'client/src/',
				src:      ['**/*.html', '!index.html'],
				dest:     'client/dist/js/templates.js'
			}
		},
        
        concat: {
		    options: {
		      separator: ';',
		    },
		    dist: {
		      src: ['client/src/app.js', 'client/src/**/*.js'],
		      dest: 'client/dist/js/app.js',
		    },
		},
        
        copy: {
		  index: {
		    src: 'client/src/index.html',
		    dest: 'client/dist/index.html',
		    options: {
		    },
		  },		  
 		},
		
        tags: {
		    buildScripts: {
		        options: {
			        scriptTemplate: '<script type="text/javascript" src="{{ path }}"></script>',
		            openTag: '<!-- start script template tags -->',
		            closeTag: '<!-- end script template tags -->'
		        },
		        src: [
			        'client/dist/js/angular.js',
			        'client/dist/js/angular.min.js',
			        'client/dist/js/toastr.min.js',
		            'client/dist/js/*.js'
		        ],
		        dest: 'client/dist/index.html'
		    },
		    buildLinks: {
		        options: {
					linkTemplate: '<link rel="stylesheet" type="text/css" href="{{ path }}" media="screen"/>',
		            openTag: '<!-- start css template tags -->',
		            closeTag: '<!-- end css template tags -->'
		        },
		        src: [
		            'client/dist/css/*.css'
		        ],
		        dest: 'client/dist/index.html'
		    }
		},
		
		jshint: {
		    all: ['Gruntfile.js', 'client/src/**/*.js', 'client/test/**/*.js']
		},
		
		uglify: {
		    prod: {
		      files: {
		        'client/dist/js/app.js': ['client/dist/js/app.js']
		      }
		    }
		},
		
		cssmin: {
		  options: {
		    shorthandCompacting: false,
		    roundingPrecision: -1
		  },
		  target: {
		    files: {
		      'client/dist/css/style.css': ['client/dist/css/style.css']
		    }
		  }
		},

		karma: {
		  unit: {
		    configFile: 'client/tests/unit/karma.conf.js'
		  }
		},
		
		nggettext_extract: {
            pot: {
                options: {
                    extensions: {
                        htm: 'html',
                        html: 'html',
                        php: 'html',
                        phtml: 'html',
                        tml: 'html',
                        js: 'js',
                        cshtml: 'html'
                    }
                },
                files: {
                    'client/src/translations/template.pot': ['client/src/**/*.html', 'client/src/app.js', 'client/src/**/*.js']
                }
            },
        },

        nggettext_compile: {
            all: {
                files: {
                    'client/src/translations.js': ['client/src/translations/*.po']
                }
            },
        },
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-bowercopy');
    grunt.loadNpmTasks('grunt-sass');
    grunt.loadNpmTasks('grunt-script-link-tags');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-angular-templates');
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-angular-gettext');    
    
    grunt.registerTask('test', ['clean:test1', 'jshint', 'bowercopy:test', 'clean:test2', 'karma']);
    
    grunt.registerTask('extract', ['nggettext_extract']);
    grunt.registerTask('compile', ['nggettext_compile', 'dev']);
    
    grunt.registerTask('dev', ['clean:dist', 'bowercopy:angular', 'bowercopy:libs', 'bowercopy:css', 'sass', 'ngtemplates', 'concat', 'copy:index', 'tags']);
    grunt.registerTask('build', ['jshint', 'test', 'clean:dist', 'bowercopy:angularmin', 'bowercopy:libs', 'bowercopy:css', 'sass', 'ngtemplates', 'concat', 'copy:index', 'uglify', 'cssmin', 'tags']);
};