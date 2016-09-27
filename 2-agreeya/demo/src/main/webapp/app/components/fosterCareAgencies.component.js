(function(angular) {
  'use strict';
  var module = angular.module("chhsDemo");


  //controller
  function fosterCareAgenciesController(toaster, searchService, userService, dataService, $geolocation) {
    var model = this;
    model.lat = 0;
    model.lang = 0;
    model.radius = 10000;
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
    model.isAgenciesAvailable = 'default';
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
      //model.gridOptions.api.setRowData(model.agencies.facilities.pojoList);
      var gridConfigOptions = model.gridOptions;
        setTimeout(function () {
        if (!model.agencies.facilities.pojoList) {
            // in case user selected 'onPageSizeChanged()' before the json was loaded
            return;
        }

        var dataSource = {
            //rowCount: ???, - not setting the row count, infinite paging will be used
            pageSize: pSize, // changing to number, as scope keeps it as a string
            getRows: function (params) {
              
                console.log('asking for ' + params.startRow + ' to ' + params.endRow);
                setTimeout(function () {
                    // take a chunk of the array, matching the start and finish times
                    var rowsThisPage = model.agencies.facilities.pojoList.slice(params.startRow, params.endRow);
                   
                    var lastRow = -1;
                    if (model.agencies.facilities.pojoList.length <= params.endRow) {
                        lastRow = model.agencies.facilities.pojoList.length;
                    }
                    params.successCallback(rowsThisPage, lastRow);
                }, 500);
            }
        };
        gridConfigOptions.api.setDatasource(dataSource);
        }, 1000);
    }

    model.$onInit = function() {

      var geolocation;
      if ( navigator.geolocation )
       {
           geolocation = navigator.geolocation.getCurrentPosition( function(position){
           
           //  console.log(position);
             model.lat = position.coords.latitude;
             model.lang = position.coords.longitude;
             if(model.lat > 0 && model.lang > 0) {
               var locationData = {
                 latitude: model.lat,
                 longitude: model.lang,
                 radius: model.radius,
               }
               console.log(JSON.stringify(locationData));
               searchService.getFosterCareAgencies(locationData).then(function(agenciesData){
                 if(agenciesData.facilities && agenciesData.facilities.pojoList.length > 0){
                   model.agencies = facilitiesData;
                   model.isAgenciesAvailable = 'yes';
                 } else {
                   model.isAgenciesAvailable = 'no';
                   //toaster.pop('error', 'Foster Care Agencies', 'No facility available for this location');
                 }

               }, function(error){
                 model.isAgenciesAvailable = 'no';
                 console.log(error);
                 console.log("Error in searching Foster Care Agencies");
               });
             } else {
               toaster.pop('error', 'Foster Care Agencies', 'Error in getting foster care agencies.');
             }

           } );
       };

      if(!userService.IsUserLoggedIn()){
        //  model.$router.navigate(['HomePage']);
      };
    }
    model.showPosition = function (position) {
      model.lat = position.coords.latitude;
      model.lang = position.coords.longitude;
      console.log(model.lat);
      if(model.lat > 0 && model.lang > 0) {
        var locationData = {
          latitude: model.lat,
          longitude: model.lang,
          radius: model.radius
        }
        console.log(JSON.stringify(locationData));
        searchService.getFosterCareAgencies(locationData).then(function(agenciesData){
          if(agenciesData.facilities && agenciesData.facilities.pojoList.length > 0){
            model.agencies = facilitiesData;
            model.isAgenciesAvailable = 'yes';
          } else {
            model.isAgenciesAvailable = 'no';
            //toaster.pop('error', 'Foster Care Agencies', 'No facility available for this location');
          }

        }, function(error){
          model.isAgenciesAvailable = 'no';
          console.log(error);
          console.log("Error in searching Foster Care Agencies");
        });
      } else {
        toaster.pop('error', 'Foster Care Agencies', 'Error in getting foster care agencies.');
      }
    }
    model.searchAgencies = function(zipCode){
      if(zipCode) {


        /*searchService.getUserLocation(zipCode).then(function(geolocation){
          console.log(geolocation);
          var locationData = {
            latitude: geolocation.geometry.location.lat,
            longitude: geolocation.geometry.location.lng,
            radius: model.radius
          }
          console.log(JSON.stringify(locationData));
          searchService.getFosterCareAgencies(locationData).then(function(agenciesData){
            if(agenciesData.facilities && agenciesData.facilities.pojoList.length > 0){
              model.agencies = facilitiesData;
              model.isAgenciesAvailable = true;
            } else {
              model.isAgenciesAvailable = 'yes';
              toaster.pop('error', 'Foster Care Agencies', 'No facility available for this location');
            }

          }, function(error){
            model.isAgenciesAvailable = 'no';
            console.log(error);
            console.log("Error in searching Foster Care Agencies");
          });
        })*/




      }
    }


  }

  //controller code ends here

  //component
  module.component("fosterCareAgencies", {
    templateUrl: './app/templates/fosterCareAgencies.html',
    controllerAs: 'model',
    controller: ['toaster', 'searchService', 'userService', 'dataService', '$geolocation', fosterCareAgenciesController],
    bindings: {
      $router: '<'
    }
  });

}(window.angular));
