
describe('Index', function() {
  beforeEach(function() {
    browser.get(browser.baseUrl + '/');
  });

  describe('footer', function() {
    it('is on the index page', function() {
      expect(element(by.tagName('footer')).isPresent()).toBe(true);
    });
  });
});
