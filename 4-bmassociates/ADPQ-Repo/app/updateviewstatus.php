<?php include 'dbconnection.php';
  $idmessage = $_POST['idmessage'];
$collection = $conn->tbl_message;
$newdata = array('$set' => array("status" => "1"));
$collection->update(array("_id" =>$idmessage),$newdata);
	   return 1;	
?>
