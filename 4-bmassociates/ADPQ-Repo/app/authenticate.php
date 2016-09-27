<?php 
session_start();//session starts here  
include 'dbconnection.php';
if(! $conn ) 
	{
		die("Connection failed: " . $conn);
	} 
	//connection to the table and authenticating the user in mongodb
    $collection = $conn->tbl_profile;
    $results = $collection->findOne(array('Email' => $_REQUEST["username"],'Password' => $_REQUEST["passwrd"],'Typeofuser' => $_REQUEST["role"]));
	
	 if(count($results)==0) 
	 {
		$message = "Invalid Username or Password!";
		 echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/home.php\");
			</SCRIPT>";
			mysql_close();  
	  } else 
	  {	  
	    if($_REQUEST["role"]==1 || $_REQUEST["role"]==2)
		{
		$_SESSION['email']=$_REQUEST["username"];
		$_SESSION['iduser']=$results['_id'];		
		$message = "You are successfully authenticated!";
		 echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/index.php\");
			</SCRIPT>";			
        }
       	
	  }	
?>
