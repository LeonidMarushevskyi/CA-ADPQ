// Config for protractor tests
//
// Runs against local server by default. To run against a staging server:
// $ export STAGING=http://angular-healthcare-dev.mybluemix.net

const cfenv = require('cfenv');
const appEnv = cfenv.getAppEnv();

var baseUrl = process.env.STAGING || cfenv.getAppEnv().url;
console.log('Testing server at ' + baseUrl);

exports.config = {
  baseUrl: baseUrl,
  framework: 'jasmine',
  specs: ['spec/integration/**/*_spec.js'],
  capabilities: {
    'browserName': 'phantomjs',
    'phantomjs.binary.path': require('phantomjs-prebuilt').path,
    'phantomjs.cli.args': ['--web-security=false', '--ignore-ssl-errors=true', '--webdriver-loglevel=DEBUG']
  },
  seleniumServerJar: 'spec/support/selenium-server-standalone-2.53.0.jar'
};
