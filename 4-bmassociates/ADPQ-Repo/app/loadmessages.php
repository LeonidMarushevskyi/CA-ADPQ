<?php include 'dbconnection.php';
 $me = $_POST['me'];    
   $timestamp= $_POST['timestamp'];	
         
		 $_SESSION['mytimestamp']='';
		 $arr_Search = array(
        '$or' => array(
            array('from' => $me, 'to' =>array('$in'=>$_POST['Idusersto']) ),
            array('from' =>array('$in'=>$_POST['Idusersto']), 'to' => $me )
            )
        );
		$chatcolln = $conn->tbl_chat;		
	    $arrayone = $chatcolln->find($arr_Search,array('_id' => true, 'message' => true, 'Timestamp' => true));
		foreach ($arrayone as $person) {							  
							  $newarray[] = $person['Timestamp'];							 
							  }							 
							  $currenttimestamp  = date("Y-m-d h:i:s");
							  $max = max(array_map('strtotime', $newarray));
							  $newmax = date("Y-m-d h:i:s",$max);
									$timestemp  = $max;
									$_SESSION['mytimestamp'] = $timestemp;
									$arr_Searchmessages = array('$or' => array(
																					array('from' => $me, 'to' =>$_POST['Idusersto']),
																					array('from' =>$_POST['Idusersto'], 'to' => $me )
																				 ));															
							        $aMessages = $chatcolln->find($arr_Searchmessages);
									Print_r($aMessages);die();
									if($aMessages != null){
									$json_response = json_encode($aMessages);
									print_r($json_response);exit;
								}else{
									echo "0";exit;
								}
									

?>
