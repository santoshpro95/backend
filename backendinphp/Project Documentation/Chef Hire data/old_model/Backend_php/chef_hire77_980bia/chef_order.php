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



$user_name=$_POST['user_name'];
$user_phone =  $_POST['user_phone'];
$user_address =$_POST['user_address'];

$chef_name=$_POST['chef_name'];
$chef_phone =  $_POST['chef_phone'];
$chef_address =$_POST['chef_address'];

$chef_id =$_POST['chef_id'];
$status = "waiting";
$food_name =$_POST['food_name'];

$food_price =  $_POST['food_price'];
$no_meals =$_POST['no_meals'];
$choose =$_POST['choose'];
$timee=$_POST['time'];

$lat=$_POST['lat'];
$log=$_POST['log'];

$order_id = sprintf("%10d", mt_rand(1, 9999999999));


		$sql = "Insert INTO chef_order VALUES (NULL,'$order_id','$user_name', '$user_phone', '$user_address' , '$chef_name', '$chef_phone', '$chef_address', '$chef_id', '$status', '$food_name', '$food_price', '$no_meals', '$choose', '$timee','0','$lat', '$log','0' )";


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		
		    "user_name" => $_POST['user_name'],
			"user_phone" =>  $_POST['user_phone'],
			"user_address" => $_POST['user_address'],

			"chef_name"=> $_POST['chef_name'],
			"chef_phone" => $_POST['chef_phone'],
			"chef_address" => $_POST['chef_address'],

			"chef_id" => $_POST['chef_id'],
			"food_name" => $_POST['food_name'],

			"food_price" =>  $_POST['food_price'],
			"no_meals" => $_POST['no_meals'],
			"order_id" => $order_id,
		        "time" => $_POST['time'],
		  
		        "lat" => $_POST['lat'],
		        "log" => $_POST['log']
		   
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	


$conn->close();





?>