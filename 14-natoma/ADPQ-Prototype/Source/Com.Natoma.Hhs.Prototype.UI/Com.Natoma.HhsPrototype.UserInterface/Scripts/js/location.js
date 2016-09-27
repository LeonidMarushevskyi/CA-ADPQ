$().ready(function () {
    $('#txtZipToSearch').keypress(function (e) {
        if (e.keyCode == 13)
            $('#btnSearch').click();
    });
});

var LocationsModel = function (zip) {
    var self = this;
    self.locations = ko.observableArray([]);

    if (!zip) {
        zip = $('#txtZipToSearch').val();
    }

    GetHHSData(self, zip);
}

var locationsViewModel = new LocationsModel();
ko.applyBindings(locationsViewModel, $('#locations-table')[0]);

var LocationModel = function (location) {
    if (location == null) {
        location = GetEmptyLocationObject();
    }

    var self = this;
    self.location = ko.observable(location);
}

function GetHHSData(model, zip) {
    //console.log('ZIP Code to Search: ' + zip);

    $.getJSON('https://chhs.data.ca.gov/resource/mffa-c6z5.json?location_zip=' + zip, function (data) {
        //console.log('Data returned from CHHS web service');
        //console.log(data);

        var mappedLocations = $.map(data, function (item) { return new Location(item); });
        model.locations(mappedLocations);

        model.locations.sort(function (left, right) {
            return left.facility_name() == right.facility_name() ? 0 : (left.facility_name() < right.facility_name() ? -1 : 1)
        });

        // Hide/Show closed locations
        if (!$('#chkShowClosedLocations').is(':checked')) {
            model.locations.remove(function (item) {
                return item.closed_date() != null;
            });
        }
    });
}

function SearchZip(zip) {
    GetHHSData(locationsViewModel, zip);
}

function GetEmptyLocationObject() {
    var location = {};
    location.closed_date = '';
    location.county_name = '';
    location.facility_address = '';
    location.facility_administrator = '';
    location.facility_capacity = '';
    location.facility_city = '';
    location.facility_name = '';
    location.facility_number = '';
    location.facility_status = '';
    location.facility_telephone_number = '';
    location.facility_type = ko.observable('');
    location.facility_zip = '';
    location.license_first_date = '';
    location.licensee = '';
    location.location = '';
    location.location_address = '';
    location.location_city = '';
    location.location_state = '';
    location.location_zip = '';
    location.regional_office = '';

    location.facility_city_state_zip = ko.observable('');
    location.facility_administrator_display = ko.observable('');
    location.license_first_date_display = ko.observable('');
    location.closed_facility_display = ko.observable('');
    location.map_url = ko.observable('');

    return location;
}

function Location(data) {
    this.closed_date = ko.observable(data.closed_date);
    this.county_name = ko.observable(data.county_name);
    this.facility_address = ko.observable(data.facility_address);
    this.facility_administrator = ko.observable(data.facility_administrator);
    this.facility_capacity = ko.observable(data.facility_capacity);
    this.facility_city = ko.observable(data.facility_city);
    this.facility_name = ko.observable(data.facility_name);
    this.facility_number = ko.observable(data.facility_number);
    this.facility_status = ko.observable(data.facility_status);
    this.facility_telephone_number = ko.observable(data.facility_telephone_number);
    this.facility_type = ko.observable(data.facility_type);
    this.facility_zip = ko.observable(data.facility_zip);
    this.license_first_date = ko.observable(data.license_first_date);
    this.licensee = ko.observable(data.licensee);
    this.location = ko.observable(data.location);
    this.location_address = ko.observable(data.location_address);
    this.location_city = ko.observable(data.location_city);
    this.location_state = ko.observable(data.location_state);
    this.location_zip = ko.observable(data.location_zip);
    this.regional_office = ko.observable(data.regional_office);

    this.facility_city_state_zip = ko.computed(function () { return data.facility_city + ', ' + data.facility_state + ' ' + data.facility_zip; });
    this.facility_administrator_display = ko.computed(function () { return 'Adminstrator: ' + data.facility_administrator; });
    this.closed_facility_display = ko.computed(function () {
        if (!data.closed_date) {
            return null;
        }

        return 'Facility Closed as of: ' + toShortDateString(data.closed_date);
    });
    this.license_first_date_display = ko.computed(function () {
        return toShortDateString(data.license_first_date);
    });

    this.map_url = ko.computed(function () {
        var url = 'https://www.google.com/maps/place/';
        url = url + data.facility_address + ',+' + data.facility_city + ',+' + data.facility_state + ',+' + data.facility_zip;
        url = url.replace(/\s+/g, '+');

        return url;
    });
}

function ToggleAdditionalDetails(btn) {
    var button = $(btn);
    var div = $(button).parent().parent().parent().children('#divAdditionalInfoRow');

    // Change button text before 'toggle' effect
    if (!$(div).is(':visible')) {
        button.html('Hide Details');
    } else {
        button.html('Additional Details');
    }

    $(div).toggle({
        effect: 'blind',
        duration: 400,
        easing: 'linear'
    });
}