describe('listing foster agencies', function() {

    it('should load the correct page title', function(){
        browser.get('/#/foster-agencies');
        expect(element(by.id('foster-agency-title')).getText()).toBe('Foster agencies in your zip code');
    });

    it('should display a message if no foster agencies found for zip', function (){

        browser.get('/#/foster-agencies');

        agencyList = element.all(by.repeater('fosterAgency in fosterAgencies'));

        //expect(agencyList.count()).toBeGreaterThan(0);
        agencyList.count().then(function(count) {
           if(count == 0){
               expect(element(by.id('no-foster-agencies-message')).isDisplayed()).toBeTruthy();
           } else {
               expect(element(by.id('no-foster-agencies-message')).isDisplayed()).not.toBeTruthy();
           }
        });
    });

    describe('foster agencies are displayed on screen', function(){
        beforeEach(function () {
            browser.get('/#/foster-agencies');

            agencyList = element.all(by.repeater('fosterAgency in fosterAgencies'));
        })

        it('should populate facility_name in the agency listing', function(){
            agencyList.count().then(function(count) {
                if(count != 0) {
                    expect(element.all(by.id('foster-agency-name')).get(0).getText()).not.toBe('');
                }
            });
        });

        it('should populate foster agency facility type in the agency listing', function(){
            agencyList.count().then(function(count) {
                if(count != 0) {
                    expect(element.all(by.id('foster-agency-facility-type')).get(0).getText()).not.toBe('');
                }
            });
        });

        it('should populate foster agency phone in the agency listing', function(){
            agencyList.count().then(function(count) {
                if(count != 0) {
                    expect(element.all(by.id('foster-agency-phone')).get(0).getText()).not.toBe('');
                }
            });
        });

        it('should populate foster agency location in the agency listing', function(){
            agencyList.count().then(function(count) {
                if(count != 0) {
                    expect(element.all(by.id('foster-agency-location')).get(0).getText()).not.toBe('');
                }
            });
        });

        it('should populate foster agency location in the agency listing', function(){
            agencyList.count().then(function(count) {
                if(count != 0) {
                    expect(element.all(by.id('foster-agency-location')).get(0).getText()).not.toBe('');
                }
            });
        });

        it('should return only agencies in the same zipcode', function(){
            zips = element.all(by.id('foster-agency-zip'));
            allSame = true;

            agencyList.count().then(function(count) {
                if(count != 0) {
                    zips.getText().then(function (value_array) {
                        for (var i = 1; i < value_array.length; i++) {
                            expect(value_array[i] == value_array[0]).toBeTruthy();
                        }
                    });
                }
            });
        });
    })
});