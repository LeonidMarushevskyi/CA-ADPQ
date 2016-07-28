describe('edit a profile', function(){
    /* only for testing
    //log in for now
    beforeAll(function(){
        browser.get('#/login');

        element(by.id('email')).sendKeys('joey.doe@example.com');
        element(by.id('password')).sendKeys('xyz');
        element(by.xpath("//input[@value='Log in']")).click();

        // Login takes some time, so wait until it's done.
        // For the test app's login, we know it's done when it redirects to
        // index.html.
        return browser.wait(function() {
            return browser.getCurrentUrl().then(function(url) {
                return /messages/.test(url);
            });
        }, 10000);
    });*/

    beforeEach(function() {
        browser.get('#/profile');
    });

    it('should validate existence of first name', function() {
        element(by.id('fname')).clear();
        expect(element(by.id('fname')).getAttribute('class')).toMatch('ng-invalid-required');

        element(by.id('fname')).sendKeys('Test');
        expect(element(by.id('fname')).getAttribute('class')).toMatch('ng-valid-required');
    });

    it('should validate existence of last name', function() {
        element(by.id('lname')).clear();
        expect(element(by.id('lname')).getAttribute('class')).toMatch('ng-invalid-required');

        element(by.id('lname')).sendKeys("User");
        expect(element(by.id('lname')).getAttribute('class')).toMatch('ng-valid-required');
    });

    it('should validate existence of zip', function() {
        element(by.id('zip')).clear();
        expect(element(by.id('zip')).getAttribute('class')).toMatch('ng-invalid-required');

        element(by.id('zip')).sendKeys("95642");
        expect(element(by.id('zip')).getAttribute('class')).toMatch('ng-valid-required');
    });

    it('should validate existence of email', function() {
        element(by.id('email')).clear();
        expect(element(by.id('email')).getAttribute('class')).toMatch('ng-invalid-required');

        element(by.id('email')).sendKeys("joeina.doe@example.com");
        expect(element(by.id('email')).getAttribute('class')).toMatch('ng-valid-required');
    });

    //We don't have a db cleaner in place
    /*
    it('should edit a profile if proper fields are entered', function(){
        element(by.id('fname')).sendKeys("Test");
        element(by.id('lname')).sendKeys("User");
        element(by.id('zip')).sendKeys("95642");
        element(by.id('email')).sendKeys("joeina.doe@example.com");
        element(by.id('password')).sendKeys("asdf1234A!");
        element(by.id('cpassword')).sendKeys("asdf1234A!");

        element(by.buttonText("Update")).click();

        expect(browser.getCurrentUrl()).toContain('#/messages/inbox');
    }); */
});