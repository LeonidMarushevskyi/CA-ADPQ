<?php include 'dbconnection.php';
  $message = $_POST['message'];
  $from = $_POST['from'];
  $to = $_POST['to'];
  //connecting instance to the mongodb collection tbl_chat
 //and inserting chat messages to collection
$collection = $conn->tbl_chat;
$upddate= $date = date('Y-m-d H:i:s'); 	
$post = array(
        'from'     => $from,
        'to'   => $to,
        'message'  => $message,
		'status'=>$status,
		'upddate'=>$upddate,
		'Timestamp'=>date("Y-m-d h:i:s")
         );	
    $collection->insert($post);	

?>
