<?php include 'dbconnection.php';
//print_r($_POST);
 $me = $_POST['me'];    
   $Iduserfrom = $_POST['me'];//$this->getRequest()->get("Iduserfrom");		
		 $Iduserto = $_POST['Idusersto'];//$this->getRequest()->get("Iduserto");		
		$name = $_POST['name'];//$this->getRequest()->get("name");	
		$track_click = $_POST['track_click'];//$this->getRequest()->get("track_click");			
		$chatcolln = $conn->tbl_chat;		
$arr_Search = array(
								'$or' => array(
													array('from' =>$Iduserfrom,'to'=>$Iduserto),
													array('to' =>$Iduserfrom,'from'=>$Iduserto)
									          )
                    );
		
		
		$messageindividual = $chatcolln->find($arr_Search,array('_id' => true,'from' => true,'to' => true, 'message' => true, 'Timestamp' => true));	
		$data_to_encode = array();
			foreach($messageindividual as $mongo){
				$data['from'] = $mongo['from'];
				$data['message'] = $mongo['message'];
				$data['to'] = $mongo['to'];
				$data['Timestamp'] = $mongo['Timestamp'];
				$data_to_encode[] = $data;
			}			
		if($data_to_encode != null){
			$array = json_encode($data_to_encode);
            //$array = iterator_to_array($messageindividual);
			print_r($array);
			exit;
		}else{
			echo "0";exit;
		}
		/*foreach($messageindividual1 as $mongo){
		print_r($mongo);
		}
		foreach($messageindividual2 as $mongo2){
		print_r($mongo2);
		}*/
		
		// $mongoDb->find(array('organization_id' => new MongoId($_SESSION['user_id'])))->sort(array("time_created" => -1));//foreach($arrayone as $result)
		//{
		  //print_r($result);
		//}
		//die();
/*$sql ="select tbl_chatmaster.Iduserfrom,tbl_chatmaster.Iduserto,tbl_chatmaster.Messagecontent,tbl_chatmaster.Messagecontent,
	  DATE_FORMAT(tbl_chatmaster.Created_at,'%b %d %Y %h:%i %p') as Created_at,tbl_registration.FirstName from tbl_chatmaster as tbl_chatmaster 
	  join tbl_registration as  tbl_registration on tbl_registration.idRegistration = tbl_chatmaster.Iduserfrom
	  where tbl_chatmaster.Iduserfrom IN ($Iduserfrom,$Iduserto) AND `Iduserto` IN ($Iduserfrom,$Iduserto) AND DATE_FORMAT( tbl_chatmaster.Created_at, '%Y-%m-%d' ) = CURDATE( ) 
	 order by tbl_chatmaster.Created_at";
		*/
		
		//$messageindividual = response.json($messageindividual)
		
		/* $arr_Search = array(
								'$or' => array(
													array('from' =>array('$in'=>[$Iduserfrom,$Iduserto]) ),
													array('to' =>array('$in'=>[$Iduserfrom,$Iduserto]))
									          )
        );*/
		//echo $Iduserfrom."---".$Iduserto."---".$name."---".$track_click;die();
		//$messageindividual = $this->lobjChat->fngetmessagesofindividualmembers($Iduserfrom,$Iduserto,$track_click);
		/*$arr_Search1 = array('from' =>$Iduserfrom,'to'=>$Iduserto);
		$arr_Search2 = array('to' =>$Iduserfrom,'from' =>$Iduserto);
		//print_r($arr_Search1);
		
		 $messageindividual1 = $chatcolln->find($arr_Search1,array('_id' => true,'from' => true,'to' => true, 'message' => true, 'Timestamp' => true));	
		$messageindividual2 = $chatcolln->find($arr_Search2,array('_id' => true,'from' => true,'to' => true, 'message' => true, 'Timestamp' => true));	
		*/
?>
