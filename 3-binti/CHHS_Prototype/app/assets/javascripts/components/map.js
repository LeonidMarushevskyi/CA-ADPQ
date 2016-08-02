$(function () {
  var $map = $('#map');
  var _ = window._;

  var initMap = function () {
    var map = new window.google.maps.Map($map[0], {
      center: { lat: 36.7783, lng: -119.4179 },
      mapTypeId: window.google.maps.MapTypeId.ROADMAP,
      zoom: 6,
    });

    $('.map-finder').on('submit', function (e) {
      e.preventDefault();
      $.get('/api/facilities.json?' + $(e.target).serialize())
        .done(function (results) {
          $(".usa-alert-error").remove();
          if (results.error) {
            var alertTemplate = _.template($('.template-alert').html());
            var errorData = {alert_type: "error", message: results.error};
            $(".map-finder").before(alertTemplate(errorData));
            return;
          }

          var bounds = new window.google.maps.LatLngBounds();
          var infoWindow = new window.google.maps.InfoWindow();
          var infoTemplate = _.template($('.template-marker-info').html());
          var facilityTemplate = _.template($('.template-facility-row').html());
          var $facilitiesContainer = $('.facilities-container');
          $facilitiesContainer.html('');

          _.chain(results).sortBy(function (facility) {
            return facility.distance_in_miles;
          }).each(function (facility) {
            var marker = new window.google.maps.Marker({
              position: new window.google.maps.LatLng(
                facility.lat,
                facility.lon
              ),
              map: map,
              title: facility.name,
            });

            bounds.extend(marker.getPosition());

            $facilitiesContainer.append($(facilityTemplate(facility)));

            marker.addListener('click', function () {
              infoWindow.close();
              infoWindow.setContent(infoTemplate(facility));
              infoWindow.open(map, marker);
            });
          });

          map.fitBounds(bounds);
        });
    });
  };

  if ($map[0]) {
    window._initMap = initMap;
    $('body').append('<script src="https://maps.googleapis.com/maps/api/js?key=' +
        'AIzaSyB1hayeRyd0HQlhbYDls122orgOu_5WqNg' +
        '&callback=_initMap" async defer></script');
  }
});
