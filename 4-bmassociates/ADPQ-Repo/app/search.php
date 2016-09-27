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
//calling an api to get the details of zip code
function fngetdetails(){
  var zipcode=document.getElementById("zipcode").value;  
  jQuery.get( "https://chhs.data.ca.gov/resource/mffa-c6z5.json?facility_zip="+zipcode, function( response ) { 
    var jsonstring=JSON.stringify(response);
    var json_obj =JSON.parse(jsonstring);
    $("#datatable-fixed-header").find("tr:gt(0)").remove();    
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
//showing the data fetch from api to the user
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
	  <!-- footer content -->
<!-- /footer content -->
      <footer>
	  <?php 
include 'inc/footer.php';
?>
