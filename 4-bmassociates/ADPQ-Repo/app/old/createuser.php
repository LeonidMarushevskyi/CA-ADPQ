<?php include 'dbconnection.php';
if($_SERVER['REQUEST_METHOD'] == "POST")
{
	$upddate= $date = date('Y-m-d H:i:s');    
	 // Check connection		
	if(! $conn ) 
	{
		die("Connection failed: " . $conn);
	} 
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
	/*$sql = "INSERT INTO  tbl_profile (Name,Lname,Address,Zipcode,Dateofbirth,Gender,Phone,Email,Password,Typeofuser,Upddate)
	VALUES ('".$_POST['Fname']."','".$_POST['Lname']."','".$_POST['Location']."','".$_POST['Zipcode']."','".$_POST['Db']."','".$_POST['Gender']."','".$_POST['Phone']."','".$_POST['Email']."','".$_POST['Password']."','".$_POST['typeofuser']."','".$upddate."')";
	 mysqli_query($conn,$sql);
	 mysqli_close($conn);*/
	 $message = 'User has been successfully created.';	
	 echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/home.php\");
			</SCRIPT>";
			mysql_close();
}

?>
