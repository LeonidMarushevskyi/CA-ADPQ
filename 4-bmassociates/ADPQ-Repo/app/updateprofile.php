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
//functionality to update the profile information
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
												 header("Location: /profile.php");
	
?>
