(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");

  // Controller for child search facilities
  function childResidentialFacilitiesController($window, toaster, searchService, userService, dataService) {
    var model = this;
    model.selectedPageSize = 5;
    model.pageSizes = [
        {
            Id: 10,
            Name: '10'
      }, {
            Id: 50,
            Name: '50'
      }, {
            Id: 100,
            Name: '100'
      }
    ];
    model.isFacilitiesAvailable = 'default';
  //  var facData = [];
    model.gridOptions = {
      columnDefs:  [
        {headerName: "Facility Type", field:"facility_type", sortingOrder: ['asc','desc']},
        {headerName: "Facility Number", field:"facility_number", sortingOrder: ['asc','desc']},
        {headerName: "Facility Name", field:"facility_name", sortingOrder: ['asc','desc']},
        {headerName: "Facility License Type", field:"licensee", sortingOrder: ['asc','desc']},
        {headerName: "Facility Contact", field:"facility_telephone_number", sortingOrder: ['asc','desc']},
        {headerName: "Address", field:"facility_address", sortingOrder: ['asc','desc']},
      ],
      rowData: null,
      onGridReady: function() {
        model.gridOptions.api.sizeColumnsToFit();
        model.setGridData(model.selectedPageSize);
      },
      enableColResize: true,
      enableSorting: true,
      rowModelType: 'pagination'
    }
   // model.agGridStyle = "grid";
    model.agGridStyle = {
      'width': '98%',
      'height': '300px'//,
      //'min-height': '200px'
    };
    model.onPageSizeChanged = function(pageSize) {
        pageSize = parseInt(pageSize);
        model.setGridData(pageSize);
    };
    model.setGridData = function(pSize){
      //model.gridOptions.api.setRowData(model.facilities.facilities.pojoList);
      var gridConfigOptions = model.gridOptions;
        setTimeout(function () {
        if (!model.facilities.facilities.pojoList) {
            // in case user selected 'onPageSizeChanged()' before the json was loaded
            return;
        }

        var dataSource = {
            //rowCount: ???, - not setting the row count, infinite paging will be used
            pageSize: pSize, // changing to number, as scope keeps it as a string
            getRows: function (params) {
                // this code should contact the server for rows. however for the purposes of the demo,
                // the data is generated locally, a timer is used to give the experience of
                // an asynchronous call
                console.log('asking for ' + params.startRow + ' to ' + params.endRow);
                setTimeout(function () {
                    // take a chunk of the array, matching the start and finish times
                    var rowsThisPage = model.facilities.facilities.pojoList.slice(params.startRow, params.endRow);
                    // see if we have come to the last page. if we have, set lastRow to
                    // the very last row of the last page. if you are getting data from
                    // a server, lastRow could be returned separately if the lastRow
                    // is not in the current page.
                    var lastRow = -1;
                    if (model.facilities.facilities.pojoList.length <= params.endRow) {
                        lastRow = model.facilities.facilities.pojoList.length;
                    }
                    params.successCallback(rowsThisPage, lastRow);
                }, 500);
            }
        };
        gridConfigOptions.api.setDatasource(dataSource);
        }, 1000);
    }

    model.$onInit = function() {
      if(!userService.IsUserLoggedIn()){
        //  model.$router.navigate(['HomePage']);
      };

    }

    model.searchFacilities = function(zipCode){
      if(zipCode) {
        /*dataService.fetchFacilities().then(function(facilities){
          console.log("Returned data: ", facilities);
          model.facilities = facilities;
          model.isFacilitiesAvailable = 'yes';
        });*/
        searchService.getChildResidentialFacilities(zipCode).then(function(facilitiesData){
          if(facilitiesData.facilities && facilitiesData.facilities.pojoList.length > 0){
            model.facilities = facilitiesData;
            model.isFacilitiesAvailable = 'yes';
          } else {
            model.isFacilitiesAvailable = 'no';
            //toaster.pop('error', 'Child Residential Facilities', 'No facility available for this location');
          }

        }, function(error){
          model.isFacilitiesAvailable = 'default';
          console.log(error);
          console.log("Error in searching child residential facilities");
        });

      }
    }

  } //controller ends here

  //Component to display child residential facilities based on search criteria
  module.component("childResidentialFacilities", {
    templateUrl: './app/templates/childResidentialFacilities.html',
    controllerAs: "model",
    controller: ['$window', 'toaster', 'searchService', 'userService', 'dataService', childResidentialFacilitiesController],
    bindings: {
      $router: '<'
    }
  })

}(window.angular));
