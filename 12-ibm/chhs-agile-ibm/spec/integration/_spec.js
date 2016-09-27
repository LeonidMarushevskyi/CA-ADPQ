// Workaround for Protractor problem in Build and Deploy Pipeline.
// In first test, allow GET to fail, then try again.

describe('Preload', function() {
  it('ensures Angular settles', function() {
    var url = browser.baseUrl;
    browser.get(url).then(undefined, function() { browser.get(url) });
  });
});
