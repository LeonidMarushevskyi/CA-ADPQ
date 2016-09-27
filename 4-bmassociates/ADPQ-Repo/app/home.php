<!DOCTYPE html>
<?php include 'dbconnection.php';?>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type='text/javascript'>
 $(function () {
	 //restricting user to enter only alphabets for the first name
$('#Fname').keydown(function (e) {
		if (e.shiftKey || e.ctrlKey || e.altKey) {
		e.preventDefault();
		} else {
		var key = e.keyCode;
		if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
		e.preventDefault();
		}
		}
		});
     //restricting user to enter only alphabets for the last name
$('#Lname').keydown(function (e) {
		if (e.shiftKey || e.ctrlKey || e.altKey) {
		e.preventDefault();
		} else {
		var key = e.keyCode;
		if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
		e.preventDefault();
		}
		}
		});
		}
);
function onlyAlphabets(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
                    return true;
                else
                    return false;
               }
            catch (err) {
                alert(err.Description);
                        }
        } 
$(document).ready(function($) {	
//evaluation of the password strength 
$('#Password').keyup(function() {
                $('#password_error_msg').html('').parent().hide();
                var password = $('#Password').val();

                if(password.length < 6){
                    $('#password_error_msg').html("Weak Password");
                    $('#password_error_msg').parent().show();
                }else{
                    var regex_simple = /^[a-z]$/;
                    var regex_capital = /^[A-Z]$/;
                    var regex_numbers = /^[0-9]$/;
                    var simple_status  = '0';
                    var capital_status = '0';
                    var number_status  = '0';
                    var status_count   = '0';
                    for(i=0;i<password.length;i++){
                        var check_character = password.charAt(i);

                        if(regex_simple.test(check_character) && simple_status == '0'){
                            simple_status = '1';
                            status_count++;
                        }
                        if(regex_capital.test(check_character) && capital_status == '0'){
                            capital_status = '1';
                            status_count++;
                        }
                        if(regex_numbers.test(check_character) && number_status == '0'){
                            number_status = '1';
                            status_count++;
                        }
                    }
                    switch(status_count){
                        case 0:
                            $('#password_error_msg').html("Weak Password");
                            $('#password_error_msg').parent().show();
                            break;
                        case 1:

                            $('#password_error_msg').html("Good Password");
                            $('#password_error_msg').parent().show();
                            break;

                        case 2:
                            $('#password_error_msg').html("Strong Password");
                            $('#password_error_msg').parent().show();
                            break;

                        case 3:
                            $('#password_error_msg').html("Excellent Password");
                            $('#password_error_msg').parent().show();
                            break;
                    }
                }
            });
//Validation for the emailid format
$('.keyup-email').keyup(function() {
    $('span.error-keyup-7').remove();
    var inputVal = $(this).val();
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if(!emailReg.test(inputVal)) {
        $(this).after('<span class="error error-keyup-7"><b>Invalid Email Format.</span>');
    }
});
});
//function call once the feedback options changed
//to provide different options to select
function changeFunc() {
    var selectBox = document.getElementById("feedbacktype").value;
	if(selectBox == 1)
	{
	     document.getElementById("markbox").style.display ="";
		 document.getElementById("ratingbox").style.display ="none";
	}
	else if(selectBox == 2 || selectBox == 3 || selectBox == 4)
	{
	    document.getElementById("ratingbox").style.display ="";
		document.getElementById("markbox").style.display ="none";
		document.getElementById("mark").value='';
	}
	else if(selectBox == 5)
	{
	    document.getElementById("ratingbox").style.display ="none";
		document.getElementById("markbox").style.display ="none";
		document.getElementById("mark").value='';
	}
   }
var specialKeys = new Array();
        specialKeys.push(8); //Backspace
		
        function IsNumeric(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
            document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
		function IsNumericzip(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
            document.getElementById("error1").style.display = ret ? "none" : "inline";
            return ret;
        }
		function  checklogin()
		{
		}
//function call once feedback of the user got submitted
function checkfeedbackform()
{
      var message = document.forms["feedbackform"]["txtmessage"].value;
	  var feedbacktype = document.forms["feedbackform"]["feedbacktype"].value;
	  var email = document.forms["feedbackform"]["email"].value;
	   if (message == null || message == "") 
	   {
	    alert("Message can not be empty");
        return false;
	   }
	   if (feedbacktype == null || feedbacktype == "") 
	   {
	    alert("Please select the feedback type");
        return false;
	   }
	   if (email == null || email == "") 
	   {
	    alert("Email Adress is required");
        return false;
	   }
	 var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
	
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
        alert("Not a valid e-mail address");
        return false;
    }	
}
//function call once the registration form submitted and validate  the fields entered
function checkform()
{
      var fname = document.forms["myform"]["Fname"].value;
	  var address = document.forms["myform"]["Location"].value;
	  var zipcode = document.forms["myform"]["Zipcode"].value;
	  var gender = document.forms["myform"]["Gender"].value;
	  var email = document.forms["myform"]["Email"].value;
	  var dateofbirth = document.forms["myform"]["Db"].value;	  
	  var password = document.forms["myform"]["Password"].value;
	  var confirmPassword = document.forms["myform"]["Confirmpass"].value;
	  var typeofuser = document.forms["myform"]["typeofuser"].value;
	if (gender == null || gender == "") {
        alert("Gender is required");
        return false;
    }
	if (dateofbirth == null || dateofbirth == "") {
        alert("Date of birth is required");
        return false;
    }
	else
	{
					// Checks for the following valid date formats:
					// MM/DD/YY   MM/DD/YYYY   MM-DD-YY   MM-DD-YYYY
					// Also separates date into month, day, and year variables
					var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
					// To require a 4 digit year entry, use this line instead:
					// var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
					var matchArray = dateofbirth.match(datePat); // is the format ok?
					if (matchArray == null) {
					alert("Date is not in a valid format.")
					return false;
					}
					month = matchArray[1]; // parse date into variables
					day = matchArray[3];
					year = matchArray[4];
					
					if (month < 1 || month > 12) { // check month range
					alert("Month must be between 1 and 12.");
					return false;
					}
					if (day < 1 || day > 31) {
					alert("Day must be between 1 and 31.");
					return false;
					}
					if ((month==4 || month==6 || month==9 || month==11) && day==31) {
					alert("Month "+month+" doesn't have 31 days!")
					return false
					}
					if (month == 2) { // check for february 29th
					var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					if (day>29 || (day==29 && !isleap)) {
					alert("February " + year + " doesn't have " + day + " days!");
					return false;
					   }
					}					
	}
	   if (password == null || confirmPassword == "") 
	   {
	           return false;
	   }
	   else
	   {
				if (password != confirmPassword) 
				{
					alert("Passwords do not match.");
					return false;
				}				
		}
		var atpos = email.indexOf("@");
       var dotpos = email.lastIndexOf(".");
	
		if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
			alert("Not a valid e-mail address");
			return false;
		}	
}
</script>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CHHS </title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/starrr.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="css/style.css" rel="stylesheet">
  </head>
  <body class="Landing">
    <div class="container body">
      <div class="main_container">  
        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav class="" role="navigation">
              <div class="nav toggle">
                <h3><a>LOGO</a></h3>
              </div>
			  </nav>
          </div>
        </div>        
        <div class="right_col outer" role="main">
            <div class="row middle">			 
              <div class="col-md-12 landing-page inner">			  
                  <div class="col-md-6 col-md-offset-3 col-xs-12 profile_details">
                    <div class="col-md-8  col-md-offset-2">
                    <div class="well profile_view">
					 <!-- User Login Template/form -->		
                      <form onsubmit="return checklogin()" action="/authenticate.php" method='post' id="loginform" name='loginform' data-parsley-validate="" class="form-horizontal form-label-left">
                      <div class="col-sm-12">  
                        <div class="col-md-12 text-center">
                        <div class="form-group">
                        <label class="control-label col-md-3 col-xs-12" for="first-name">User Id:</label>
                        <div class="col-md-8 col-xs-12">
                          <input type="text" required="required" id="username"  name="username" class="form-control col-md-7 col-xs-12" >              
                        </div>
                      </div>
                       <div class="form-group">
                        <label class="control-label col-md-3 col-xs-12" for="first-name">Password:</label>
                        <div class="col-md-8 col-xs-12">
                          <input type="password" required="required" id="passwrd"  name="passwrd" class="form-control col-md-7 col-xs-12">              
                        </div>
                        </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Role:</label>
                        <div class="col-md-8 col-xs-12">
                          <select name='role' class="form-control" required="required">
                            <option value=''>Choose Role</option>
                            <option value='1'>Parent</option>
                            <option value='2' >Case Worker</option>                            
                          </select>
                        </div>
                      </div>
                      </div>
                      <div class="col-xs-12 bottom text-center">
                        <div class="col-md-6 col-sm-0">                          
                        </div>
                        <div class="col-md-12 col-sm-12 ">
                          <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-feed"> <i class="fa fa-user"></i>Feedback</a></button>
                          <button type="button" class="btn btn-success" data-toggle="modal" data-target=".bs-example-modal-regi">Register</button>
                          <button type="submit" class="btn btn-primary"> <i class="fa fa-user"></i>Login</a></button>
                        </div>
                      </div>
                    </div>
                     </form>
					  <!-- Login tempalte ends here -->		
                  </div>
                  </div>
               </div>
            </div>
        </div>
        <div class="modal fade bs-example-modal-feed bs-example-modal-feed-sm" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
          <div class="modal-dialog modal-sm">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">x</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Feedback</h4>
              </div>
              <div class="modal-body">
                <!-- Feedback Template/form -->			  
                <form  onsubmit="return checkfeedbackform()" action="/storefeedback.php" method='post' name='feedbackform' data-parsley-validate="" class="form-horizontal form-label-left">
                   <div class="form-group">                        
                        <div class="col-md-12 col-sm-12 col-xs-12">  
                        <label class="control-label" for="first-name">Feedback Type:
                        </label>                       
                          <select id='feedbacktype' name='feedbacktype' class="form-control" onchange="changeFunc();" required="required">
                            <option value=''>Choose Feedback Type</option>
                            <option value='1' >Feedback about your registration</option>
            							  <option value='2' >About your login with the site</option>
            							  <option value='3' >About search for care home</option>
            							   <option value='4' >About your communication with care taker</option>
            							   <option value='5' >Feedback on bug/issues in the system</option>
                          </select> 
                        </div>
                      </div>
                      <div class="form-group" name='markbox' id='markbox' style="display: none;">                                              
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="first-name">Select Value
                          </label> 
                          <div class="opt-ratio col-md-12">
                            <div class="radio col-md-3">
                              <label>
                                <input type="radio"  value="good"  name="mark"> Good
                              </label>
                            </div>
                            <div class="radio col-md-3">
                              <label>
                                <input type="radio"  value="bad"  name="mark"> Bad
                              </label>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="form-group" name='ratingbox' id='ratingbox' style="display: none;">
					  <input type='hidden' name='rating' id='rating' value='' />
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="first-name">Select Value
                          </label>
                          <div>
                            <div class="starrr stars"></div>
                            You gave a rating of <span class="stars-count">0</span> star(s)
                          </div>
                          </div>
                      </div>
					  <div class="form-group">                       
                        <div class="col-md-12 col-sm-12 col-xs-12">
                           <label class="control-label" for="first-name">Message:
                        </label>
                          <textarea  name='txtmessage' name='txtmessage' class="form-control col-md-12 col-xs-12"> </textarea>              
                        </div>
                      </div>
                      <div class="form-group">                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="first-name">Email Address:
                        </label>
                          <input type="text" id="email"  required="required" class="form-control col-md-12 col-xs-12" name="email">
                        </div>
                      </div>
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-12 col-sm-12 col-xs-12 align-right">
                          <button type="button" class="btn btn-primary" onclick="location.href='home.php'">Cancel</button>
                          <button type="submit" class="btn btn-success" >Submit</button>
                        </div>
                      </div>
                    </form> 
                    <!-- /end of feedback template -->					
                  </div>
                </div>
              </div>
            </div>
          <!-- Registration Template -->
                    <div class="modal fade bs-example-modal-sm bs-example-modal-regi" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                      <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">x</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Registration</h4>
                          </div>
                          <div class="modal-body">						
                        <form onsubmit="return checkform()" id="demo-form2" name='myform' data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" action="/createuser.php" method='post'>
                        <div class="form-group">
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label class="control-label" for="first-name">First Name<span class="required">*</span>
							</label>
                            <input type="text" id="Fname"   required="required" class="form-control col-md-12 col-xs-12" name="Fname" placeholder='First Name'>
                          </div>
                        </div>
  					    <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label class="control-label" for="first-name">Last Name
                          </label>
                            <input type="text" id="Lname"   class="form-control col-md-12 col-xs-12" name="Lname" placeholder='Last Name'>
                          </div>
                        </div>					  
                        <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label class="control-label" for="Location">Location<span class="required">*</span>
                          </label>
                            <input type="text" id="Location"  name="Location" required="required" class="form-control col-md-12 col-xs-12" placeholder='Your Address'>
                          </div>
                        </div>
                        <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label class="control-label" for="Location">Zip Code<span class="required">*</span>
                          </label>
                            <input onkeypress="return IsNumericzip(event);" ondrop="return false;" onpaste="return false;" placeholder='Enter Zip Code' type="text" id="Zipcode"  name="Zipcode" required="required" class="form-control col-md-12 col-xs-12">
                            <span id="error1" style="color: Red; display: none">* Input digits (0 - 9)</span>
  						   </div>
                        </div>					  
  					    <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label class="control-label" for="Location">Date Of Birth<span class="required">*</span>
                          </label>
                            <input type="text" id="Db"  name="Db"  required="required" placeholder='Enter Date Of Birth' class="form-control col-md-12 col-xs-12">(MM/DD/YYYY)
                          </div>
                        </div>
                       <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label class="control-label" required="required" for="Location">Select Gender<span class="required">*</span>
                            </label></br>
  						              <label class="radio-inline"><input type="radio" name="Gender" value='1' >Male</label>
                            <label class="radio-inline"><input type="radio" name="Gender" value='2'>Female</label>
                          </div>
                        </div> 					  
                        <div class="form-group">                         
                          <div class="col-md-12 col-sm-12 col-xs-12">
                             <label for="Phone" class="control-label">Phone</label>							 
							 <input type="text"  name="Phone" id="Phone" placeholder='Phone Number' class="form-control col-md-12 col-xs-12" onkeydown="javascript:backspacerDOWN(this,event);" onkeyup="javascript:backspacerUP(this,event);" />
							<!--<input onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" id="Phone" class="form-control col-md-12 col-xs-12" type="text"  name="Phone">
                            <span id="error" style="color: Red; display: none">* Input digits (0 - 9)</span>-->
  						</div>
                        </div>
  					    <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label for="Email"  class="control-label">Email<span class="required">*</span>
							</label>
                            <input id="Email" required="required" class="form-control col-md-12 col-xs-12 keyup-email text-input" type="text" name="Email" placeholder='Email Address'>
                          </div>
                        </div>
                       <div class="form-group">                         
                          <div class="col-md-12 col-sm-12 col-xs-12">
                             <label for="Password" class="control-label">Password<span class="required">*</span>
							 </label>
                            <input id="Password" required="required" class="form-control col-md-12 col-xs-12" type="password" name="Password" placeholder='Enter Password'>
                             <li class='error_msg' >
								<div class='label_col'> </div><div class='error_field'
							id='password_error_msg'> </div>
								</li>
						  </div>						  
                        </div>
                        <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <label for="Confirm Password" class="control-label">Confirm Password<span class="required">*</span></label>
                            <input id="Confirmpass" required="required" class="form-control col-md-12 col-xs-12" type="password" name="Confirmpass" placeholder='Re-enter Password'>
                          </div>
                        </div> 
                        <div class="form-group">                          
                          <div class="col-md-12 col-sm-12 col-xs-12">
                           <label class="control-label" for="first-name">Register As<span class="required">*</span>
                          </label>
                            <select required="required" id='typeofuser' name='typeofuser' class="form-control">
                              <option value="" >Choose Option</option>
                              <option value="1">Parent</option>
                              <option value="2">Care Taker</option>                            
                            </select> 
                          </div>
                        </div>					  
                        <div class="ln_solid"></div>
                        <div class="form-group">
                          <div class="col-md-12 col-sm-12 col-xs-12 align-right">
                            <button type="button" class="btn btn-primary" onclick="location.href='home.php'">Cancel</button>
                            <button type="submit" class="btn btn-success" >Submit</button>
                          </div>
                        </div>
                    </form>	
                  <!-- /end of registration template -->					
                </div>
              </div>
            </div>
          </div>
        <!-- /page content -->
        </div>
      </div>
    </div>
    <!-- /Starrr -->
    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>    
    <!-- Bootstrap -->
    <script src="js/bootstrap.min.js"></script>
    <!-- NProgress -->
    <script src="js/nprogress.js"></script> 
     <script src="js/daterangepicker.js"></script>
    <!-- bootstrap-wysiwyg -->
    <script src="js/bootstrap-wysiwyg.min.js"></script>   
    <script src="js/prettify.js"></script>
    <!-- jQuery Tags Input -->
    <script src="js/jquery.tagsinput.js"></script>
    <!-- Switchery -->    
    <script src="js/select2.full.min.js"></script>
    <!-- Parsley -->
    <script src="js/parsley.min.js"></script>
    <!-- Autosize -->
    <script src="js/autosize.min.js"></script>
    <!-- jQuery autocomplete -->
    <script src="js/jquery.autocomplete.min.js"></script>
    <!-- starrr -->
    <script src="js/starrr.js"></script>
    <!-- Custom Theme Scripts -->
    <!-- Custom Theme Scripts -->
    <script src="js/custom.js"></script>
        <script>
      $(document).ready(function() {
        $(".stars").starrr();

        $('.stars-existing').starrr({
          rating: 4
        });

        $('.stars').on('starrr:change', function (e, value) {
          $('.stars-count').html(value);
		   $("#rating").val(value);		  
        });

        $('.stars-existing').on('starrr:change', function (e, value) {
          $('.stars-count-existing').html(value);
        });
      });
    </script>
  </body>
</html>