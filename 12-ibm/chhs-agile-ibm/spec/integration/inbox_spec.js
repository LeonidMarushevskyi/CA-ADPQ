
describe('Inbox', function() {
  beforeEach(function() {
    browser.get(browser.baseUrl + '/#/inbox');
  });

  it('should load Inbox', function() {
    expect(element(by.css('.md-toolbar-tools')).getText()).toContain('Inbox');
  });

  it('should render 2 messages', function() {
    var inbox_list = element.all(by.repeater('inbox in inboxData'));
    expect(inbox_list.count()).toBe(2);
  });

  it('should render a message with title', function() {
    var inbox_list = element.all(by.repeater('inbox in inboxData'));
    expect(inbox_list.get(0).getText()).toContain('Welcome\nFrom: Francis');
  });

  describe('shows body', function() {
    it('should render list of message body', function() {
      var inbox_list = element.all(by.repeater('inbox in inboxData'));
      expect(inbox_list.get(0).getText()).toContain('From: Francis');
    });
  });
});
