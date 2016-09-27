<?php 
session_start();//session starts here  
include 'dbconnection.php';
if($_SERVER['REQUEST_METHOD'] == "POST")
{  
	if(! $conn ) 
	{
		die("Connection failed: " . $conn);
	} 
	
	if(isset($_POST['messageto']))
	{
		$collection = $conn->tbl_profile;	
		$larresult = $collection->findOne(array('Email' => $_POST['messageto']));
     if(count($larresult) > 0) 
	 {
	   $to=$larresult['_id'];
	   $from=$_POST['messagefrom'];
	   $message = $_POST['message'];
	   $status=0;
	   $upddate= $date = date('Y-m-d H:i:s');        
		$post = array(
        'messagefrom'     => $from,
        'messageto'   => $to,
        'message'  => $message,
		'status'=>$status,
		'upddate'=>$upddate
    );
	//connecting the instance of an CHHS to the message collection
	//and inserting the messages to the collection
	$collection = $conn->tbl_message;
    $collection->insert($post);	
		  $message = 'message successfully sent.';	
          echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/message.php\");
			</SCRIPT>";		    
	  }
	  else
	  {
	     $unsuccessful = 'Wrong Email Adress.';	
          echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$unsuccessful');
				window.location.replace(\"/message.php\");
			</SCRIPT>";		  
	  }
	}
	//for storing of reply message to collection
	else if(isset($_POST['replyto']))
	{	 
	    $to=$_POST['replyto'];
	    $from=$_POST['messagefrom'];
	    $message = $_POST['message'];
	    $status=0;
	    $upddate= $date = date('Y-m-d H:i:s');         
        $post = array(
        'messagefrom'     => $from,
        'messageto'   => new MongoId($to),
        'message'  => $message,
		'status'=>$status,
		'upddate'=>$upddate
    );
	$collection = $conn->tbl_message;
    $collection->insert($post);		
		$message = 'message successfully sent.';	
        echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$message');
				window.location.replace(\"/message.php\");
			</SCRIPT>";	  
	} 
	
}

?>
