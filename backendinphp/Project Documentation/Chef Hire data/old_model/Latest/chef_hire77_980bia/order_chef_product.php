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



$p_name=$_POST['p_name'];
$p_price=  $_POST['p_price'];

$chef_name=$_POST['chef_name'];
$chef_phone =  $_POST['chef_phone'];
$chef_address=$_POST['chef_address'];

$delivery_date=$_POST['delivery_date'];
$quantity=$_POST['quantity'];
$order_date=$_POST['order_date'];
$chef_id=$_POST['chef_id'];

$order_id = sprintf("%10d", mt_rand(1, 9999999999));

		$sql = "Insert INTO chef_product VALUES (NULL,'$p_name','$p_price', '$chef_name', '$chef_phone ' , '$chef_address ', '$delivery_date', '$order_date', '$chef_id','$order_id','active','$quantity' )";


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		
		    "p_name" => $_POST['p_name'],
			"p_price" =>  $_POST['p_price'],
			"chef_name" => $_POST['chef_name'],

			"chef_phone"=> $_POST['chef_phone'],
			"chef_phone" => $_POST['chef_phone'],
			"chef_address" => $_POST['chef_address'],

			"delivery_date" => $_POST['delivery_date'],
			"order_date" => $_POST['order_date'],
			"order_id" => $order_id,
		        "order_date" => $_POST['order_date'],
		  
		        "chef_id" => $_POST['chef_id'],
		        "delivery_date" => $_POST['delivery_date'],
		          "quantity" => $_POST['quantity']
		       
		   
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	


$conn->close();





?>