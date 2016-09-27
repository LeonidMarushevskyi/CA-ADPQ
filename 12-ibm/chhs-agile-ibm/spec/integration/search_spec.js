
describe('Search', function() {
  beforeEach(function() {
    browser.get(browser.baseUrl + '/#/search');
  });

  it('Zip Code', function() {
    expect(browser.getTitle()).toEqual('CHHS');
  });

  it('has a text box for the zip code', function() {
    expect(element(by.css('.zipcodes')).isPresent()).toBe(true);
  });

});
