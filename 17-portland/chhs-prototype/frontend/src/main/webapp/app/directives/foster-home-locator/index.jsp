<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authorize access="isAuthenticated()">
  <h2>Foster Facility Finder</h2>

  <div class="foster-home-locator__search-input">
    <form class="form-inline" name="fosterHomeLocatorForm" ng-submit="searchFosterHomes(search.zip, search.proximity)">
      <label class="sr-only" for="foster-home-locator__zip-input">Zip Code:</label>
      <input type="text" name="zip" required id="foster-home-locator__zip-input" class="foster-home-locator__search-input-control form-control" placeholder="Zip Code" ng-model="search.zip">
      <label class="sr-only" for="foster-home-locator__proximity-input">Proximity (in miles):</label>
      <input type="number" name="proximity" required id="foster-home-locator__proximity-input" class="foster-home-locator__search-input-control form-control" placeholder="Proximity (in miles)" ng-model="search.proximity">
      <button type="submit" class="foster-home-locator__search-input-control foster-home-locator__search-input-locate btn btn-primary" ng-disabled="fosterHomeLocatorForm.$invalid">Locate</button>
      <button type="button" class="foster-home-locator__search-input-control foster-home-locator__search-input-reset btn btn-default" ng-click="resetFosterHomes()">Reset</button>
    </form>
  </div>

  <div class="foster-home-locator__search-map">
    <ui-gmap-google-map center='map.center' zoom='map.zoom'>
      <ui-gmap-marker ng-repeat="location in results" events="map.events" idKey="location.facility_number" coords="{ latitude: location.location.latitude, longitude: location.location.longitude }"></ui-gmap-marker>
    </ui-gmap-google-map>
  </div>

  <h3>Foster Facility Results</h3>
  <div class="foster-home-locator__search-results table-responsive">
    <table class="table">
      <thead>
        <tr>
          <th>Address</th>
          <th>Phone</th>
          <th>Number</th>
        </tr>
      </thead>
      <tbody>
        <tr ng-repeat="location in results" id="{{ location.facility_number }}" ng-click="selectFacility(location)" ng-class="{'foster-home-locator__result-selected': selectedFacility === location.facility_number}">
          <td>
            <p class="foster-home-locator__result-name">{{ location.facility_name }}</p>
            <p class="foster-home-locator__result-address">
              <span class="foster-home-locator__result-street">{{ location.facility_address }}</span>,
              <span class="foster-home-locator__result-city">{{ location.facility_city }}</span>,
              <span class="foster-home-locator__result-state">{{ location.facility_state }}</span>
              <span class="foster-home-locator__result-zip">{{ location.facility_zip }}</span>
            </p>
          </td>
          <td>
            <p class="foster-home-locator__result-phone">{{ location.facility_telephone_number }}</p>
          </td>
          <td>
            <p class="foster-home-locator__result-number">{{ location.facility_number }}</p>
          </td>
        </tr>
        <tr ng-if="results.length == 0">
          <td colspan="3" align="center">No foster homes found.</td>
        </tr>
      </tbody>

    </table>

  </div>
</sec:authorize>
