<?php include 'dbconnection.php';
if($_SERVER['REQUEST_METHOD'] == "POST")
{
	$upddate= $date = date('Y-m-d H:i:s');    
	 // Check connection		
	
	if(! $conn ) 
	{
		die("Connection failed: " . $conn);
	}
	$rate='';
    if((isset($_POST['mark'])) && $_POST['rating']=="") 	
	{
	  $rate = $_POST['mark'];
	}
	else 
	{
	  $rate = $_POST['rating'];
	}
	
	$post = array(
        'Message'     => $_POST['txtmessage'],
        'Type'   => $_POST['feedbacktype'],
        'Rating'  => $rate,
		'Email'=>$_POST['email'],
		'Upddate'=>$upddate
    );
	$collection = $conn->tbl_feedback;
    $collection->insert($post);
	//$sql = "INSERT INTO  tbl_feedback (Message,Type,Rating,Email,Upddate)
	//VALUES ('".$_POST['txtmessage']."','".$_POST['feedbacktype']."','".$rate."','".$_POST['email']."','".$upddate."')";	
	// mysqli_query($conn,$sql);
	 //mysqli_close($conn);
	 $message = 'Feedback successfully saved.';	
	 echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/home.php\");
			</SCRIPT>";
			mysql_close();   
	
}
?>
