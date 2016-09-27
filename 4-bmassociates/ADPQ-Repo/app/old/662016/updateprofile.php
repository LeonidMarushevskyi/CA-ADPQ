<?php 
session_start();//session starts here  
include 'dbconnection.php';
$postdata = $_POST;
$name = $_POST['Name'];
$address = $_POST['Location'];
$phone = $_POST['Phone'];
$email = $_POST['Email'];
$Zipcode = $_POST['Zipcode'];
$Db = $_POST['Db'];
$Gender = $_POST['Gender'];
$Lname = $_POST['Lname'];
$collection = $conn->tbl_profile;	
	$collection->update(
                        array('_id'     => $_SESSION['iduser']),
                        array('$set'    => array('Name'=> $_POST['Name'],
						                         'Email'=> $_POST['Email'],
												 'Address'=> $_POST['Location'],
												 'Phone'=> $_POST['Phone'],
												 'Zipcode'=>$_POST['Zipcode'],
												 'Dateofbirth'=>$_POST['Db'],
												 'Gender'=>$_POST['Gender'],
												 'Lname'=> $_POST['Lname'])));												 
 //$sql = "update tbl_profile set Name='".$name."' , Email='".$email."' , Address='".$address."' , Phone='".$phone."',Zipcode='".$Zipcode."',Dateofbirth='".$Db."',Gender='".$Gender."',Lname='".$Lname."'
 //where Idprofile='".$_SESSION['iduser']."'";
//if (mysqli_query($conn,$sql))
	//{
	   // mysqli_close($conn);
	    header("Location: /profile.php");
	//}
	
?>
