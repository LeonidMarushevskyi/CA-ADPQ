describe("login", function () {

    // Once cleaner is implemented, create profile will login and we will
    // need to logout to test this suite
    /*
    beforeAll(function() {
        browser.get('#/');
        element(by.id('dropProfile')).click();
        element(by.css('[ng-click="logout()"]')).click();
        browser.waitForAngular();
    });*/

    beforeEach(function() {
        browser.get('/#');
    });

    it('should display the correct page url', function () {
        expect(browser.getCurrentUrl()).toContain('#/login');
    });

    it("should allow new users a link to register", function() {
        expect(element(by.linkText('Register')).getAttribute('href')).toContain('#/create-profile');
    });

    //Unfortunately cannot test for incorrect login credentials because chromedriver has an issue that will be fixed in 2.22 that
    // allows the browser to wait for alerts.
    /*
    it("should give a message to a user if email/password is not valid", function() {
        element(by.id('email')).sendKeys('invalid.email@example.com');
        element(by.id('password')).sendKeys('xyz');
        element(by.xpath("//input[@value='Log in']")).click();

        var EC = protractor.ExpectedConditions;

        browser.wait(EC.alertIsPresent(), 10000);

        var alertDialog = browser.switchTo().alert();

        expect(alertDialog.getText()).toEqual('Hello');

        alertDialog.accept();
    });*/

    it('should allow users to login with a proper email/password combination', function() {
        element(by.id('email')).sendKeys(browser.params.email);
        element(by.id('password')).sendKeys(browser.params.password);
        element(by.buttonText('Log in')).click();
/*
        return browser.wait(function() {
            return browser.getCurrentUrl().then(function(url) {
                return /messages/.test(url);
            });
        }, 10000); */

        expect(browser.getCurrentUrl()).toContain('#/messages/inbox');
    });
});