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
	 //$sql1 = "SELECT * FROM tbl_profile where Email='".$_POST['messageto']."'";
						  // $result = mysqli_query($conn,$sql1);
						  // $larresult = $result->fetch_assoc();					   
						  // $count  = mysqli_num_rows($result);
						  $collection = $conn->tbl_profile;	
				 $larresult = $collection->findOne(array('Email' => $_POST['messageto']));
     if(count($larresult) > 0) 
	 {
	   $to=$larresult['_id'];
	   $from=$_POST['messagefrom'];
	   $message = $_POST['message'];
	   $status=0;
	   $upddate= $date = date('Y-m-d H:i:s'); 
       //$sql = "INSERT INTO  tbl_message (messagefrom,messageto,message,status,upddate)
		// VALUES ($from,'".$to."','".$message."',$status,'".$upddate."')";
		// mysqli_query($conn,$sql);
		// mysqli_close($conn);
		$post = array(
        'messagefrom'     => $from,
        'messageto'   => $to,
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
		  //mysql_close();		  
	  }
	  else
	  {
	     $unsuccessful = 'Wrong Email Adress.';	
          echo "<SCRIPT type='text/javascript'> //not showing me this
				alert('$unsuccessful');
				window.location.replace(\"/message.php\");
			</SCRIPT>";
		  //mysql_close();
	  }
	}
	else if(isset($_POST['replyto']))
	{
	  // echo "<pre>";
	//print_r($_POST);die();
	    $to=$_POST['replyto'];
	    $from=$_POST['messagefrom'];
	    $message = $_POST['message'];
	    $status=0;
	    $upddate= $date = date('Y-m-d H:i:s'); 
        //$sql = "INSERT INTO  tbl_message (messagefrom,messageto,message,status,upddate)
		//VALUES ($from,'".$to."','".$message."',$status,'".$upddate."')";
		//mysqli_query($conn,$sql);
		//mysqli_close($conn);	
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
	   // mysql_close();	
	} 
	
}

?>
