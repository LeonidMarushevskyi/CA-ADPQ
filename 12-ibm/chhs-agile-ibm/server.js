var express = require('express');
var cfenv = require('cfenv');

// create a new express server
var app = express();

// serve up public files
app.use('/client',  express.static(__dirname + '/client'));
app.use('/node_modules',  express.static(__dirname + '/node_modules'));

app.get('/', function(req, res) {
  res.sendFile(__dirname + '/client/index.html');
});

// get the app environment from Cloud Foundry
var appEnv = cfenv.getAppEnv();

// start server on the specified port and binding host
app.listen(appEnv.port, '0.0.0.0', function() {

	// print a message when the server starts listening
  console.log("server starting on " + appEnv.url);
});

module.exports = app;
