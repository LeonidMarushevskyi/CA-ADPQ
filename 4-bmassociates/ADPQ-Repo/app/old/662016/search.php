<?php 
session_start();  
if(!$_SESSION['email'])  
{
    header("Location: logout.php");//redirect to login page to secure the welcome page without login access.  
}  
include 'inc/header.php';
include 'inc/Left-sidebar.php';
include 'inc/Top-Bar.php';  
?> 
        <!-- page content -->
<script>
function fngetdetails(){
  var zipcode=document.getElementById("zipcode").value;
  //alert(zipcode);
  jQuery.get( "https://chhs.data.ca.gov/resource/mffa-c6z5.json?facility_zip="+zipcode, function( response ) { 
    /*response = '[{"county_name":"LOS ANGELES","facility_address":"3701 STOCKER STREET #200","facility_administrator":"VEGA, DORIS","facility_capacity":"69","facility_city":"LOS ANGELES","facility_name":"EGGLESTON FAMILY SERVICES, FFA","facility_number":"197805862","facility_state":"CA","facility_status":"LICENSED","facility_telephone_number":"(323) 954-1464","facility_type":"FOSTER FAMILY AGENCY","facility_zip":"90008","license_first_date":"2010-04-01T00:00:00.000","licensee":"EGGLESTON YOUTH CENTER, INC.","location":{"type":"Point","coordinates":[-118.337172,34.007369]},"location_address":"3701 STOCKER STREET #200","location_city":"LOS ANGELES","location_state":"CA","location_zip":"90008","regional_office":"32"},{"closed_date":"2013-07-04T00:00:00.000","county_name":"LOS ANGELES","facility_address":"4252 CRENSHAW BLVD.","facility_administrator":"CYNTHIA M. WILLARD","facility_capacity":"0","facility_city":"LOS ANGELES","facility_name":"INSTITUTE FOR BLACK PARENTING","facility_number":"197806229","facility_state":"CA","facility_status":"CLOSED","facility_telephone_number":"(310) 807-3350","facility_type":"ADOPTION AGENCY","facility_zip":"90008","licensee":"INSTITUTE FOR BLACK PARENTING","location":{"type":"Point","coordinates":[-118.33458,34.006524]},"location_address":"4252 CRENSHAW BLVD.","location_city":"LOS ANGELES","location_state":"CA","location_zip":"90008","regional_office":"32"},{"closed_date":"2013-07-04T00:00:00.000","county_name":"LOS ANGELES","facility_address":"4252 CRENSHAW BLVD.","facility_administrator":"CYNTHIA M. WILLARD","facility_capacity":"0","facility_city":"LOS ANGELES","facility_name":"INSTITUTE FOR BLACK PARENTING","facility_number":"197806230","facility_state":"CA","facility_status":"CLOSED","facility_telephone_number":"(310) 807-3350","facility_type":"FOSTER FAMILY AGENCY SUB","facility_zip":"90008","licensee":"INSTITUTE FOR BLACK PARENTING","location":{"type":"Point","coordinates":[-118.33458,34.006524]},"location_address":"4252 CRENSHAW BLVD.","location_city":"LOS ANGELES","location_state":"CA","location_zip":"90008","regional_office":"32"},{"county_name":"LOS ANGELES","facility_address":"3701 STOCKER AVE., STE. 200","facility_administrator":"DIXON, KAREN","facility_capacity":"0","facility_city":"LOS ANGELES","facility_name":"EGGLESTON FAMILY SERVICES","facility_number":"197805863","facility_state":"CA","facility_status":"LICENSED","facility_telephone_number":"(323) 954-1464","facility_type":"ADOPTION AGENCY","facility_zip":"90008","license_first_date":"2010-06-30T00:00:00.000","licensee":"EGGLESTON YOUTH CENTER. INC","location":{"type":"Point","coordinates":[-118.337246,34.007269]},"location_address":"3701 STOCKER AVE., STE. 200","location_city":"LOS ANGELES","location_state":"CA","location_zip":"90008","regional_office":"32"},{"county_name":"LOS ANGELES","facility_address":"3756 SANTA ROSALIA DRIVE #312","facility_administrator":"NEKISHA KEE","facility_capacity":"0","facility_city":"LOS ANGELES","facility_name":"GUARDIANS OF LOVE","facility_number":"197805491","facility_state":"CA","facility_status":"LICENSED","facility_telephone_number":"(323) 295-6030","facility_type":"ADOPTION AGENCY","facility_zip":"90008","license_first_date":"2008-09-22T00:00:00.000","licensee":"GUARDIANS OF LOVE","location":{"type":"Point","coordinates":[-118.338895,34.008935]},"location_address":"3756 SANTA ROSALIA DRIVE #312","location_city":"LOS ANGELES","location_state":"CA","location_zip":"90008","regional_office":"32"},{"county_name":"LOS ANGELES","facility_address":"3756 SANTA ROSALIA STE305","facility_administrator":"FULLERWOOD, KINIKKI","facility_capacity":"77","facility_city":"LOS ANGELES","facility_name":"GUARDIANS OF LOVE FFA","facility_number":"197805376","facility_state":"CA","facility_status":"LICENSED","facility_telephone_number":"(323) 295-6030","facility_type":"FOSTER FAMILY AGENCY","facility_zip":"90008","license_first_date":"2007-12-24T00:00:00.000","licensee":"GUARDIANS OF LOVE","location":{"type":"Point","coordinates":[-118.338838,34.008788]},"location_address":"3756 SANTA ROSALIA STE305","location_city":"LOS ANGELES","location_state":"CA","location_zip":"90008","regional_office":"32"}]';*/
    var jsonstring=JSON.stringify(response);
    var json_obj =JSON.parse(jsonstring);
    $("#datatable-fixed-header").find("tr:gt(0)").remove();
    //$("#datatable-fixed-header").empty();
    if(json_obj.length<=0){
      var row = $("<tr />")
      $("#datatable-fixed-header").append(row);
      row.append($("<td colspan='5' style='text-align:center;'> ***** DATA NOT AVAILABLE ***** </td>"));
    }else{
      for (var i = 0; i < json_obj.length; i++) {
        drawRow(json_obj[i]);
      }
    }
    
  } );
}
function drawRow(rowData) {

    var row = $("<tr />")
    $("#datatable-fixed-header").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.facility_type + "</td>"));
  row.append($("<td>" + rowData.facility_name + "</td>"));
    row.append($("<td>" + rowData.facility_telephone_number + "</td>"));
  row.append($("<td>" + rowData.facility_capacity + "</td>"));
  row.append($("<td>" + rowData.facility_address +" ,"+ rowData.facility_city +" ,"+ rowData.facility_state +" ,"+ rowData.county_name +" ,"+ rowData.location_zip + "</td>"));
}
</script>
        <div class="right_col" role="main">
          <div class="col-md-8 col-md-offset-2 form-group top_search">
          <div class="input-group">
              <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="Search for Zip code...">
              <span class="input-group-btn">
                  <button class="btn btn-default" type="button" onclick="fngetdetails();">Go!</button>
              </span>
            </div>
          </div>
          <div class="row">
          <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
              <div class="x_title">
                <h2>Search Results <small>Case Workers</small></h2>
                <ul class="nav navbar-right panel_toolbox">
                  <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                  </li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#">Settings 1</a>
                      </li>
                      <li><a href="#">Settings 2</a>
                      </li>
                    </ul>
                  </li>
<!--                   <li><a class="close-link"><i class="fa fa-close"></i></a>
                  </li> -->
                </ul>
                <div class="clearfix"></div>
              </div>
              <div class="x_content">
                <table id="datatable-fixed-header" class="table table-striped table-bordered">
                  <thead>
                    <tr>
                      <th>Facility Type</th>
                      <th>Facility Name</th>
                      <th>Facility Telephone Number</th>
					  <th>Facility Capacity</th>
                      <th>Facility Address</th>
                    </tr>
                  </thead>
                  </tbody>
                </table>
              </div>
            </div>
          </div>        
        </div>
         </div>      
        <!-- /page content -->  
      <!-- footer content -->
      <footer>
        <div class="pull-right">         
        </div>
        <div class="clearfix"></div>
      </footer>
      <!-- /footer content -->     
    </div>
    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="js/bootstrap.min.js"></script>
    <!-- NProgress -->
    <script src="JS/nprogress.js"></script> 
    <!-- Custom Theme Scripts -->
    <script src="js/custom.js"></script>
  </body>
</html>