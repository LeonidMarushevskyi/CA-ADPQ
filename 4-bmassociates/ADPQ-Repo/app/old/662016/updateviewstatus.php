<?php include 'dbconnection.php';
  $idmessage = $_POST['idmessage'];
//$sql = "update tbl_message set status=1 where idmessage=$idmessage";
$collection = $conn->tbl_message;	
//$updateObj = array('status' => $nestatus);
$newdata = array('$set' => array("status" => "1"));
$collection->update(array("_id" =>$idmessage),$newdata);
/*
$nestatus =1;
	 $collection->update(
                        array('_id'     => $idmessage),
                        array('$set'    => $updateObj));
						//print_r($set);*/
	  
//mysqli_query($conn,$sql);
//if (mysqli_query($conn,$sql))
	//{
	   return 1;
	//}
?>
