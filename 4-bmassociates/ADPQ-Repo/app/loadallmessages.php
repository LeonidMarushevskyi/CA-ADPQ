<?php include 'dbconnection.php';
 $me = $_POST['me'];    
 $Iduserfrom = $_POST['me'];
 $Iduserto = $_POST['Idusersto'];	
 $name = $_POST['name'];
 $track_click = $_POST['track_click'];	
//connection to chat collection and load the all chat messages 
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
			print_r($array);
			exit;
		}else{
			echo "0";exit;
		}		
?>
