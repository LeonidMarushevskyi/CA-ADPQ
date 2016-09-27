<?php include 'dbconnection.php';
$me = $_POST['me'];
  $Idusersto = implode(",",$_POST['Idusersto']);
  $timestamp= $_POST['timestamp'];
		
		//echo $me."---".$Idusersto."---".$timestamp;die();
		//$namespace = new Zend_Session_Namespace();
		//$namespace->lasttimestamp;
		 $_SESSION['mytimestamp']='';
		 $arr_Search = array(
        '$or' => array(
            array('from' => $me, 'to' => $Idusersto ),
            array('from' => $Idusersto, 'to' => $me )
            )
        );
		$chatcolln = $conn->tbl_chat;
		$larresult = $chatcolln->find($arr_Search);
		echo "<pre>";
		print_r($larresult);die();
		 $timestemp1 = $this->lobjChat->fngettimestamp($me,$Idusersto);
		
		
		if($_SESSION['mytimestamp'] == $timestemp1['Timestamp']){
			$aMessages = "";
		}else{
			$timestemp  = $timestemp1['Timestamp'];
			$_SESSION['mytimestamp'] = $timestemp;
			$aMessages = $this->lobjChat->fngetmessages($me,$Idusersto,$timestamp,$timestemp1['Timestamp']);
		}
		
		if($aMessages != null){
			$json_response = json_encode($aMessages);
			print_r($json_response);exit;
		}else{
			echo "0";exit;
		}

?>
