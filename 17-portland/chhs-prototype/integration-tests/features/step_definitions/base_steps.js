// TODO - Extract logic into cleaner appropriate locations
var Q = require('q');

/* LODASH */
var _ = require('lodash');

/* CHAI */
var chai = require('chai');
var chaiAsPromised = require('chai-as-promised');
chai.use(chaiAsPromised);
var expect = chai.expect;

var config = require('../../config/protractor');
var loggedIn = false;

/* SHARED / COMMON STEPS */
module.exports = function() {
  var baseUrl = config.config.baseUrl;

  var pageMap = [
    { name: 'home', url: '' },
    { name: 'dashboard', url: 'dashboard'},
    { name: 'account', url: 'account'},
    { name: 'register', url: 'register'},
    { name: 'login', url: 'login'},
  ];

  var langs = [

  ];

  // Alias getLocationAbsUrl
  browser.getUrl = browser.getLocationAbsUrl;

  /* PRECONDITIONS */
  this.Given('I am on the "$page" page', function(pageUrl, next) {
    var page = _.find(pageMap,_.matchesProperty('name', pageUrl));
    browser.get(baseUrl+page.url).then(next);
  });


  this.Given('I am logged in with username "$username" and password "$password"', function(username, pw, next){
      if (loggedIn) {
        element(by.css('.dropdown .dropdown-toggle')).click().then(function(){
          loggedIn = false;
          element(by.css('.dropdown .site_header__link-logout')).click().then(function(){
            browser.get(baseUrl+'login').then(function(){
              element(by.model('login.auth.email')).sendKeys(username).then(function(){
                element(by.model('login.auth.password')).sendKeys(pw).then(function(){
                  loggedIn = true;
                  element(by.css('.btn-primary')).click().then(function(){
                    browser.sleep(100).then(next);
                  });
                });
              });
            });
          });
        });
    } else {
      browser.get(baseUrl+'login').then(function(){
        element(by.model('login.auth.email')).sendKeys(username).then(function(){
          element(by.model('login.auth.password')).sendKeys(pw).then(function(){
            loggedIn = true;
            element(by.css('.btn-primary')).click().then(function(){
              browser.sleep(100).then(next);
            });
          });
        });
      });
    }
  });

  this.Given('I am logged out', function(next){
    if (loggedIn) {
      element(by.css('.dropdown .dropdown-toggle')).click().then(function(){
        loggedIn = false;
        element(by.css('.dropdown .site_header__link-logout')).click().then(next);
      });
    } else {
      next();
    }
  });

  /* WHEN */

  this.When('I log out', function(next){
    if (loggedIn) {
      // var EC = protractor.ExpectedConditions;
      // var logoutIsClickable = EC.elementToBeClickable(element(by.css('.user-account .dropdown-toggle')));
      // browser.wait(logoutIsClickable, 10000);
      element(by.css('.dropdown .dropdown-toggle')).click().then(function(){
        loggedIn = false;
        element(by.css('.dropdown .site_header__link-logout')).click().then(next);
      });
    }
    else {
      next();
    }
  });

  this.When('I log in with username "$user" and password "$pass"', function(username, pw, next){
      if (loggedIn) {
        element(by.css('.dropdown .dropdown-toggle')).click().then(function(){
          loggedIn = false;
          element(by.css('.dropdown .site_header__link-logout')).click().then(function(){
            browser.get(baseUrl+'login').then(function(){
              element(by.model('login.auth.email')).sendKeys(username).then(function(){
                element(by.model('login.auth.password')).sendKeys(pw).then(function(){
                  loggedIn = true;
                  element(by.css('.btn-primary')).click().then(function(){
                    browser.sleep(100).then(next);
                  });
                });
              });
            });
          });
        });
    } else {
      browser.get(baseUrl+'login').then(function(){
        element(by.model('login.auth.email')).sendKeys(username).then(function(){
          element(by.model('login.auth.password')).sendKeys(pw).then(function(){
            loggedIn = true;
            element(by.css('.btn-primary')).click().then(function(){
              browser.sleep(100).then(next);
            });
          });
        });
      });
    }
  });

  this.When('I select the element of dropdown "$name" in position "$number"', function(name, number, next){
    element(by.css('select[name="'+name+'"]')).element(by.css('option:nth-child('+number+')')).click().then(next);

  });

  this.When('I click the model "$model"', function(model, next){
    element(by.model(model)).click().then(next);
  })

  this.When('I click "$target" class in the "$parent" class', function(target, parent, next) {
    lnButton = element(by.css('.'+parent)).element(by.css('.'+target));
    lnButton.click().then(next);
  });

  this.When(/^I click the "([^"]*)" link(?: in parent "([^"]*)")?$/, function (link, parent, next) {
    if (parent == undefined) {
      element(by.linkText(link)).click().then(next);
    } else {
      element(by.css('.'+parent)).element(by.linkText(link)).click().then(next);
    };
  });

  this.When(/^I click the "([^"]*)" link in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")?$/, function (link, row, itemInRepeater, parent, next) {
    var repeaterSelector = by.repeater(itemInRepeater);
    if (parent == undefined) {
      element(repeaterSelector.row(row)).element(by.linkText(link)).click().then(next);
    } else {
      element(by.css('.'+parent)).element(repeaterSelector.row(row)).element(by.linkText(link)).click().then(next);
    };
  });

  this.When('I click the "$link" link in the "$parentGroup"', function (link, parentGroup, next) {
    browser.findElement(by.className(parentGroup)).findElement(by.className(link)).click().then(next);
  });

  this.When('I click the "$button" button in the "$parentGroup"', function (button, parentGroup, next) {
    browser.findElement(by.className(parentGroup)).findElement(by.css('button.'+button)).click().then(next);
  });

  this.When('I enter "$textInput" into the "$textBox" textbox', function (textInput, textbox, next) {
    browser.findElement(by.css('input[type="text"].' + textbox)).sendKeys(textInput).then(next);
  });

  this.When(/^I click the "([^"]*)" checkbox in "([^"]*)"$/, function (checkbox, parent, next) {
    browser.findElement(by.className(parent)).findElement(by.css('label:contains("' + checkbox + '"):visible')).findElement(by.css('input[type="checkbox"]')).click().then(next);
  });

  this.When(/^I enter "([^"]*)" in the textarea "([^"]*)"(?: in parent "([^"]*)")?$/, function (text, model, parent, next) {
    if (parent == undefined) {
      element(by.model(model)).sendKeys(text).then(next);
    } else {
      element(by.css('.'+parent)).element(by.model(model)).sendKeys(text).then(next);
    };
  });

  this.When(/^I enter "([^"]*)" in the class textarea "([^"]*)"(?: in parent "([^"]*)")?$/, function (text, model, parent, next) {
    if (parent == undefined) {
      element(by.css('.'+model)).sendKeys(text).then(next);
    } else {
      element(by.css('.'+parent)).element(by.css('.'+model)).sendKeys(text).then(next);
    };
  });

  this.When(/^I type the long string:$/, function (text, next) {
    browser.actions().sendKeys(text).perform().then(next);
  });

  this.When('I delay the test by "$seconds" milliseconds', function(seconds,next){
    browser.sleep(seconds).then(next);
  });

  this.When(/^I click on "([^"]*)" in the dropdown "([^"]*)"(?: in parent "([^"]*)")?$/, function(option, dropdown, parent, next){
    if (parent == undefined) {
      element(by.model(dropdown)).element(by.cssContainingText('option', option)).click().then(next);
    } else {
      element(by.css('.'+parent)).element(by.model(dropdown)).element(by.cssContainingText('option', option)).click().then(next);;
    };
  });


  // this.When('I set the window size to width "$width" and height "$height"', function(width, height, next){
  //       browser.manage().window().setSize(width,height).then(next);
  // });

  this.When(/^I set the window size to(?: width "(\d*)" and height "(\d*)"| max)$/, function(varWidth, varHeight, next){
    if (! varHeight) {
        browser.driver.manage().window().maximize().then(next);
    } else {
        browser.driver.manage().window().setSize(parseInt(varWidth,10),parseInt(varHeight,10)).then(next);
    }
  });

  this.When('I clear the field "$model"', function(model, next){
    element(by.model(model)).clear().then(next);
  });

  this.When(/^I click the class "([^"]*)" in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")?$/, function(cName, row, itemInRepeater, parent, next){
    var repeaterSelector = by.repeater(itemInRepeater);
    if (parent == undefined){
      element(repeaterSelector.row(row)).element(by.css('.'+cName)).click().then(next);
    }
    else {
      element(by.css('.'+parent)).element(repeaterSelector.row(row)).element(by.css('.'+cName)).click().then(next);
    }
  });

  this.When(/^I click the class in row "([^"]*)" of class "([^"]*)"$/, function(row, cName, next){
    element.all(by.css('.'+cName+' li')).get(row).click().then(next);
  });

  /* THENS */
  this.Then('the model "$model" should exist', function(model, next){
      expect(element(by.model(model)).isDisplayed()).to.become(true).and.notify(next);
  });

  this.Then('I should see the class "$className"', function (className, next) {
     expect(element(by.css('.'+className)).isDisplayed()).to.become(true).and.notify(next);
  });

  this.Then('the class "$className" should not exist', function(className, next) {
    expect(element(by.css('.'+className)).isPresent()).to.become(false).and.notify(next);
  });

  this.Then('I should not see the class "$className"', function(className, next){
    expect(element(by.css('.'+className)).isDisplayed()).to.become(false).and.notify(next);

      // var testedClass = element(by.css('.'+className)).not.isDisplayed().then(function(tc){
      //       console.log('Class ' + className+ ' is not displayed.');
      //       next();
      //   });
  });

  this.Then('I should see the link "$linkname"', function (linkname, next) {
    expect(element(by.linkText(linkname)).isDisplayed()).to.become(true).and.notify(next);
  });

  this.Then('the link "$linkname" should not exist', function (linkname, next) {
    expect(element(by.linkText(linkname)).isPresent()).to.become(false).and.notify(next);
  });

  this.Then(/^I should see the link "([^"]*)" in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")?$/, function (linkname, row, itemInRepeater, parent, next) {
    var repeaterSelector = by.repeater(itemInRepeater);
      if (parent == undefined) {
        expect(element(repeaterSelector.row(row)).element(by.linkText(linkname)).isDisplayed()).to.become(true).and.notify(next);
        }
    else {
      expect(element(by.css('.'+parent)).element(by.linkText(linkname)).isDisplayed()).to.become(true).and.notify(next);
    }
  });

  this.Then('I should be on the "$pagename" page', function (pageUrl, next) {

    var pageExtension = _.find(pageMap,_.matchesProperty('name',pageUrl));
    browser.getCurrentUrl().then(function(url){
      expect(url).to.equal(baseUrl+pageExtension.url);
      next();
    });
  });


  this.Then('the value of model "$model" should be "$value"', function(model, value, next){
    element(by.model(model)).getAttribute('value').then(function(val){
      expect(val).equals(value);
      next();
    })
  });

  this.Then('I should be logged in as "$userEmail"', function(userEmail, next){
    var loginName = element(by.css('.dropdown .dropdown-toggle')).getText().then(function(name){
      expect(name).equals(userEmail);
      next();
    });
  });

  this.Then('the text of class "$cName" should be "$text"', function(cName, text, next){
    element(by.css('.'+cName)).getText().then(function(result){
      expect(result).equals(text);
      next();
    });
  });

  this.Then(/^the class "([^"]*)"(?: in parent "([^"]*)")? should be disabled$/, function(cName, parent, next){
    if (parent == undefined) {
      expect(element(by.css('.'+cName)).getAttribute('disabled')).to.become('true').and.notify(next);
    } else {
      expect(element(by.css("."+parent)).element(by.css('.'+cName)).getAttribute('disabled')).to.become('true').and.notify(next);
    }
  });

  this.Then(/^the class "([^"]*)"(?: in parent "([^"]*)")? should be enabled$/, function(cName, parent, next){
    if (parent == undefined) {
      expect(element(by.css('.'+cName)).getAttribute('disabled')).to.become(null).and.notify(next);
    } else {
      expect(element(by.css("."+parent)).element(by.css('.'+cName)).getAttribute('disabled')).to.become(null).and.notify(next);
    }
  });

  this.Then(/^the model "([^"]*)"(?: in parent "([^"]*)")? should be disabled$/, function(model, parent, next){
    if (parent == undefined) {
      expect(element(by.model(model)).getAttribute('disabled')).to.become('true').and.notify(next);
    } else {
      expect(element(by.css("."+parent)).element(by.model(model)).getAttribute('disabled')).to.become('true').and.notify(next);
    }
  });

  this.Then(/^the model "([^"]*)"(?: in parent "([^"]*)")? should be enabled$/, function(model, parent, next){
    if (parent == undefined) {
      expect(element(by.model(model)).getAttribute('disabled')).to.become(null).and.notify(next);
    } else {
      expect(element(by.css("."+parent)).element(by.model(model)).getAttribute('disabled')).to.become(null).and.notify(next);
    }
  });

  this.Then(/^the text of class "([^"]*)" in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")? should be "([^"]*)"$/, function(cName, row, itemInRepeater, parent, text, next){
    var repeaterSelector = by.repeater(itemInRepeater);
      if (parent == undefined) {
        element(repeaterSelector.row(row)).element(by.css('.' + cName)).getText().then(function(result){
          expect(result).equals(text);
          next();
        });
    }
    else {
      element(by.css('.'+parent)).element(repeaterSelector.row(row)).element(by.css('.' + cName)).getText().then(function(result){
        expect(result).equals(text);
        next();
      });
    }
  });

  this.Then(/^the text of class "([^"]*)" in the last row of repeater "([^"]*)"(?: in parent "([^"]*)")? should be "([^"]*)"$/, function(cName, itemInRepeater, parent, text, next){
    var repeaterSelector = element.all(by.repeater(itemInRepeater));
      if (parent == undefined) {
        repeaterSelector.last().element(by.css('.' + cName)).getText().then(function(result){
          expect(result).equals(text);
          next();
        });
    }
    else {
      element(by.css('.'+parent)).repeaterSelector.last().element(by.css('.' + cName)).getText().then(function(result){
        expect(result).equals(text);
        next();
      });
    }
  });

  this.Then(/^the class "([^"]*)" in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")? should be the current date$/, function(cName, row, itemInRepeater, parent, next){
    var day = new Date();
    var month = new Date();
    var year = new Date();
    var repeaterSelector = by.repeater(itemInRepeater);
      if (parent == undefined) {
        element(repeaterSelector.row(row)).element(by.css('.'+ cName)).getText().then(function(result){
          expect(result).equals((month.getMonth()+1)+'/'+day.getDate()+'/'+year.getFullYear());
          next();
        });
    }
    else {
      element(by.css('.'+parent)).element(repeaterSelector.row(row)).element(by.css('.'+ cName)).getText().then(function(result){
        expect(result).equals((month.getMonth()+1)+'/'+day.getDate()+'/'+year.getFullYear());
        next();
      });
    }
  });

  this.Then(/^the class "([^"]*)" in the last row of repeater "([^"]*)"(?: in parent "([^"]*)")? should be the current date$/, function(cName, row, itemInRepeater, parent, next){
    var day = new Date();
    var month = new Date();
    var year = new Date();
    var repeaterSelector = element.all(by.repeater(itemInRepeater));
      if (parent == undefined) {
        repeaterSelector.last().element(by.css('.'+ cName)).getText().then(function(result){
          expect(result).equals((month.getMonth()+1)+'/'+day.getDate()+'/'+year.getFullYear());
          next();
        });
    }
    else {
      element(by.css('.'+parent)).repeaterSelector.last().element(by.css('.'+ cName)).getText().then(function(result){
        expect(result).equals((month.getMonth()+1)+'/'+day.getDate()+'/'+year.getFullYear());
        next();
      });
    }
  });

  this.Then(/^the value of model "([^"]*)" in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")? should be "([^"]*)"$/, function(model, row, itemInRepeater, parent, value, next){
    var repeaterSelector = by.repeater(itemInRepeater);
    if (parent == undefined){
      element(repeaterSelector.row(row)).element(by.model(model)).getAttribute('value').then(function(result){
        expect(result).equals(value);
        next();
      });
    } else {
      element(by.css('.'+parent)).element(repeaterSelector.row(row)).element(by.model(model)).getAttribute('value').then(function(result){
        expect(result).equals(value);
        next();
      });
    }
  });

  this.Then(/^I should see the class "([^"]*)" in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")?$/, function(cName, row, itemInRepeater, parent, next){
    var repeaterSelector = by.repeater(itemInRepeater);
    if (parent == undefined){
      element(repeaterSelector.row(row)).getAttribute('class').then(function(result){
        expect(result).contain(cName);
        next();
      });
    } else {
      element(by.css('.'+parent)).element(repeaterSelector.row(row)).getAttribute('class').then(function(result){
        expect(result).contain(cName);
        next();
      });
    }
  });

  this.Then(/^I should not see the class "([^"]*)" in row "([^"]*)" of repeater "([^"]*)"(?: in parent "([^"]*)")?$/, function(cName, row, itemInRepeater, parent, next){
    var repeaterSelector = by.repeater(itemInRepeater);
    if (parent == undefined){
      element(repeaterSelector.row(row)).getAttribute('class').then(function(result){
        expect(result).not.contain(cName);
        next();
      });
    } else {
      element(by.css('.'+parent)).element(repeaterSelector.row(row)).getAttribute('class').then(function(result){
        expect(result).not.contain(cName);
        next();
      });
    }
  });


};
