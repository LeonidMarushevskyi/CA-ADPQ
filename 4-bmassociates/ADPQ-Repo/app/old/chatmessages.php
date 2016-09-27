<?php include 'dbconnection.php';
  $message = $_POST['message'];
  $from = $_POST['from'];
  $to = $_POST['to']; 
  
  
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
	//$collection = $conn->tbl_message;
    $collection->insert($post);	
	
  /*$arr_Search = array(
        '$or' => array(
            array('from' => $from, 'to' => $to ),
            array('from' => $to, 'to' => $from )
            )
        );
		$chatcolln = $conn->tbl_chat;
		$larresult = $chatcolln->find($arr_Search);
		foreach($larresult as $item){
		//echo "<pre>";
		print_r($item);
		}
		die();*/
  /*$me = $_POST['me'];
  $Idusersto = implode(",",$_POST['Idusersto']);
  $timestamp= $_POST['timestamp'];
		
		//echo $me."---".$Idusersto."---".$timestamp;die();
		$namespace = new Zend_Session_Namespace();
		$namespace->lasttimestamp;
		
		$timestemp1 = $this->lobjChat->fngettimestamp($me,$Idusersto);
		
		
		if($namespace->lasttimestamp == $timestemp1['Timestamp']){
			$aMessages = "";
		}else{
			$timestemp  = $timestemp1['Timestamp'];
			$namespace->lasttimestamp=$timestemp;
			$aMessages = $this->lobjChat->fngetmessages($me,$Idusersto,$timestamp,$timestemp1['Timestamp']);
		}
		
		if($aMessages != null){
			$json_response = json_encode($aMessages);
			print_r($json_response);exit;
		}else{
			echo "0";exit;
		}*/
  
//$sql = "update tbl_message set status=1 where idmessage=$idmessage";


?>
