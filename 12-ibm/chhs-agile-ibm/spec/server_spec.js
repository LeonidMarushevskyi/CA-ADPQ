var app = require('../server.js');
var request = require('supertest');

describe('GET /', function() {
  it('should return an html page', function(done){
    request(app)
      .get('/')
      .expect('Content-Type', 'text/html; charset=UTF-8')
      .expect(200, done)
      .end(function(err, res) {
        if (err) {
          done.fail(err);
        } else {
          done();
        }
      });
  });
});
