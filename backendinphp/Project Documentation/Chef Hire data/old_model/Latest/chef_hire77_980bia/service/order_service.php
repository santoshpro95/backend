<?php
$servername = "localhost";
$username = "chef_hire";
$password = "santoshpro95";
$database ="chef_hire";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
} 


	$phone =  $_POST['phone'];
	$s_id=$_POST['s_id'];
	$timee=  $_POST['time'];
	$lat=  $_POST['lat'];
	$log=  $_POST['log'];
	$veg_only=  $_POST['veg_only'];
	
	$breakfast=  $_POST['breakfast'];
	$lunch=  $_POST['lunch'];
	$dinner =  $_POST['dinner'];
	
	$days=  $_POST['days'];
	$name=  $_POST['name'];
	$price=  $_POST['price'];
	
	$status =  $_POST['status'];
	$address =  $_POST['address'];
	$end_date =  $_POST['end_date'];
	$pickup_addr =  $_POST['pickup_addr'];
$order_id = sprintf("%10d", mt_rand(1, 9999999999));
	$user_name=  $_POST['user_name'];
	$s_phone=  $_POST['s_phone'];



		$sql = "Insert INTO user_service VALUES (NULL,'$phone','$s_id','$timee', '$lat', '$log', '$veg_only','$breakfast', '$lunch', '$dinner',
		'$days','$name', '$price', '$status', '$address', '$end_date','$order_id', '', '', '$pickup_addr', '$user_name', '$s_phone')";
		


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		  
		      "s_id" => $_POST['s_id'],
		      "phone" => $_POST['phone'],
		       "time" => $_POST['time'],
		       "veg" => $_POST['veg_only'],
		       
		       "breakfast"=>  $_POST['breakfast'],
			"lunch"=>  $_POST['lunch'],
			"dinner" =>  $_POST['dinner'],
			
			"days" =>  $_POST['days'],
			"name"=>  $_POST['name'],
			"price"=>  $_POST['price'],
			
			"status" =>  $_POST['status'],
			"address" =>  $_POST['address'],
			"end_date" =>  $_POST['end_date'],
			"order_id" => $order_id,
			"pickup_addr" => $_POST['pickup_addr'],
			"user_name" => $_POST['user_name'],
			"s_phone" => $_POST['s_phone']
		     
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	





$conn->close();





?>