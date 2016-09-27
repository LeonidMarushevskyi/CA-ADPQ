<?php include 'dbconnection.php';
if($_SERVER['REQUEST_METHOD'] == "POST")
{
	$upddate= $date = date('Y-m-d H:i:s');    
	 // Check connection		
	if(! $conn ) 
	{
		die("Connection failed: " . $conn);
	} 
	// creating new user and storing the values to the profile collection
   $post = array(
        'Name'     => $_POST['Fname'],
        'Lname'   => $_POST['Lname'],
        'Address'  => $_POST['Location'],
		'Zipcode'=>$_POST['Zipcode'],
		'Dateofbirth'=>date("Y-m-d", strtotime($_POST['Db'])),
		'Gender'     =>$_POST['Gender'],
        'Phone'   => $_POST['Phone'],
        'Email'  => $_POST['Email'],
		'Password'=>$_POST['Password'],
		'Typeofuser'=>$_POST['typeofuser'],
		'Upddate'=>$upddate
    );
	$collection = $conn->tbl_profile;
    $collection->insert($post);		
	//alerting the confirmation message to user on successful registration to the system
	 $message = 'User has been successfully created.';	
	 echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/home.php\");
			</SCRIPT>";
			
}

?>
