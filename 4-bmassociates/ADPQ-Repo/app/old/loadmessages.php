<?php include 'dbconnection.php';
//print_r($_POST);
 $me = $_POST['me'];    
   $timestamp= $_POST['timestamp'];	
         //$me ="'".$me."'";
         //echo $Idusersto = implode(',',$_POST['Idusersto']);  
         // echo "<br/>";
         //echo $userstrings ="'".$Idusersto."'";  
        //$myArray = explode(',', $userstrings);	
		//echo $me."---".$Idusersto."---".$timestamp;die();
		//$namespace = new Zend_Session_Namespace();
		//$namespace->lasttimestamp;
		 $_SESSION['mytimestamp']='';
		 $arr_Search = array(
        '$or' => array(
            array('from' => $me, 'to' =>array('$in'=>$_POST['Idusersto']) ),
            array('from' =>array('$in'=>$_POST['Idusersto']), 'to' => $me )
            )
        );		
		//$item = array('$and' => array(array('tags' => 'cats'), array('tags' => 'videos')))
		$chatcolln = $conn->tbl_chat;
		/*$chatcolln->aggregate(array(
				array(
					'$group' => array (
						'_id' => '$_id',
						'max' => array( '$max' => '$Timestamp' )
						
					)
				)
			));*/
			 $arrayone = $chatcolln->find($arr_Search,array('_id' => true, 'message' => true, 'Timestamp' => true));		
		                     // print_r($arr_Search);
							 // print_r($arrayone);
							  foreach ($arrayone as $person) {							  
							  $newarray[] = $person['Timestamp'];
							  // print_r($person);
							  }
							  //print_r($newarray);
							   $currenttimestamp  = date("Y-m-d h:i:s");
							  $max = max(array_map('strtotime', $newarray));
							  $newmax = date("Y-m-d h:i:s",$max);				  
							  							  
							 // if($_SESSION['mytimestamp'] == $max)
							 // {
									//$aMessages = "";
							 // }
							  //else
							  //{
									$timestemp  = $max;
									$_SESSION['mytimestamp'] = $timestemp;
									$arr_Searchmessages = array( 
																  
																  '$or' => array(
																					array('from' => $me, 'to' =>$_POST['Idusersto']),
																					array('from' =>$_POST['Idusersto'], 'to' => $me )
																				 )
																				 
									                       
														     
															   );
															// print_r($arr_Searchmessages);
							        $aMessages = $chatcolln->find($arr_Searchmessages);
									Print_r($aMessages);die();
									if($aMessages != null){
									$json_response = json_encode($aMessages);
									print_r($json_response);exit;
								}else{
									echo "0";exit;
								}
									//foreach ($aMessages as $personchat) 
									//  {
										// print_r($personchat);
									//  }
									//$aMessages = $this->lobjChat->fngetmessages($me,$Idusersto,$timestamp,$max);
									 /*if($timestamp > 0){
										$sql .= " AND c.Timestamp BETWEEN '$lasttimestamp' AND '$currenttimestamp'";
									 }*/		 
									 //$sql .= " ORDER BY c.Timestamp ASC";
								//}
								
								
								
								
								
							  /*if($namespace->lasttimestamp == $timestemp1['Timestamp']){
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
							  
                             // echo date('Y-m-j H:i:s', $max); // 2012-06-11 08:30:49
							 // die();
							 
		//$arr_Search = array('to' =>array('$in'=>$myArray)); 
		//$arr_Search = array('to' =>['574d4e5dd74615b01e000029','0']);
		//$arr_Search = array('to' =>array('$in'=>$myArray)); 
		//$arr_Search = array("from"=>array('$in'=>['574d4f16d74615b01e00002a','mJnB2DhK']));
		//$arr_Search = array("to"=>array('$in'=>$_POST['Idusersto']));
       //$arr_Search = array('$or'=>array(array('from'=>'574d4f16d74615b01e00002a'),array('to' =>array('$in'=>$_POST['Idusersto'])))); 
	  /* $larresult = $chatcolln->find(array('from' =>'574d4f16d74615b01e00002a'));		
           foreach ($larresult as $result)
		{	
							  //echo "<pre>";
		                      print_r($result);
		}
		*/
			 
		//$db->products->find(array("url" => array('$in'=>$array1), "shop"=>$shop));
		
		// $larresult = $chatcolln->find(array('from' =>'574d4f16d74615b01e00002a'));
		 //$cursor = $songs->find($query)->sort(array('decade' => 1));
		 //$query = array( "i" => array( '$gt' => 50 ) ); //note the single quotes around '$gt'
         //$cursor = $collection->find( $query );
		 /*
		 $ages = $people->aggregateCursor( [
        [ '$group' => [ '_id' => '$name', 'points' => [ '$sum' => '$points' ] ] ],
        [ '$sort' => [ 'points' => -1 ] ],
		] );

		foreach ($ages as $person) {
			echo "{$person['_id']}: {$person['points']}\n";
		}
		*/
		/*$arrayone = $chatcolln->find(array('from' => $me,'to' => array('$in' =>$myArray)));
		print_r($arrayone);
		 while ( $arrayone->hasNext() )
		{
			var_dump( $arrayone->getNext() );
		}
		die();
		//$arraytwo = $chatcolln->find(array('from' => array('$in' =>$myArray),'to' =>$me));
		
		
		//$larresult = $chatcolln->find($arr_Search);
		print_r($larresult);
		foreach ($larresult as $result)
		{	
							  //echo "<pre>";
		                      print_r($result);
		}
		die();
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
		}*/

?>
