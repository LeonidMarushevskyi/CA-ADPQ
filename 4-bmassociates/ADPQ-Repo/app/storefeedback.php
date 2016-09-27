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
	//connecting the CHHS instance to feedback collection
	//and inserting the data to the feedback collection
	$collection = $conn->tbl_feedback;
    $collection->insert($post);	
	 $message = 'Feedback successfully saved.';	
	 echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/home.php\");
			</SCRIPT>";			
	
}
?>
