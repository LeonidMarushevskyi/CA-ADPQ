<?php 
session_start();//session starts here  
include 'dbconnection.php';
if(! $conn ) 
	{
		die("Connection failed: " . $conn);
	} 
$collection = $conn->tbl_profile;	
	/*$sql = "SELECT * FROM  tbl_profile WHERE Email='" . $_REQUEST["username"] . "' and Password = '". $_REQUEST["passwrd"]."' and Typeofuser = '". $_REQUEST["role"]."'";
	 $result = mysqli_query($conn,$sql);	
	 $larresult = $result->fetch_assoc();
	 $count  = mysqli_num_rows($result);*/
	 //$result = $posts::find();
    // one record
    //$id = '52d68c93cf5dc944128b4567';
	
    $results = $collection->findOne(array('Email' => $_REQUEST["username"],'Password' => $_REQUEST["passwrd"],'Typeofuser' => $_REQUEST["role"]));
	//$cursor2array = iterator_to_array($results);
	//echo "<pre>";
	//print_r($results);die();
	
	
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
	  
	    if($_REQUEST["role"]==1)
		{
		$_SESSION['email']=$_REQUEST["username"];
		$_SESSION['iduser']=$results['_id'];
		//echo $_SESSION['email'];die();
		$message = "You are successfully authenticated!";
		 echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/index.php\");
			</SCRIPT>";
			mysql_close(); 
        }
        else if($_REQUEST["role"]==2)
		{
		   $_SESSION['email']=$_REQUEST["username"];
		   $_SESSION['iduser']=$results['_id'];		  
		   $message = "You are successfully authenticated!";
		   echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/index.php\");
			</SCRIPT>";
			mysql_close();			
		}		
	  }	
?>
