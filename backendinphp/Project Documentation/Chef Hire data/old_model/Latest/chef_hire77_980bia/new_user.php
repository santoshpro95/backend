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


$name=$_POST['name'];
$phone =  $_POST['phone'];
$password = $_POST['password'];
$address=$_POST['address'];
$lat=  $_POST['lat'];
$log=$_POST['log'];
$email=$_POST['email'];
$fcm=$_POST['fcm'];


$datee = date('m-d-Y-h-i-s-a', time());
$token = "$phone.$datee";



$result = mysqli_query($conn,"SELECT * FROM user where phone = '$phone'");
 $rowco  = mysqli_num_rows($result);
//echo $rowco ;


if($rowco > 0){




 		$postArray = array(
		  
		      "user" =>"old"
	
		   
		    );

		 echo json_encode( $postArray );

	


}
else{





		$sql = "Insert INTO user VALUES (NULL,'$name','$phone','$address','$lat', '$log','$password','$email', '$fcm')";
	$sql2 = "INSERT INTO token_auth VALUES (NULL,'$token')";

		if ($conn->query($sql) === TRUE && $conn->query($sql2) === TRUE) {

		 $postArray = array(
		  
		      "name" => $_POST['name'],
		      "phone" => $_POST['phone'],
		      "address" => $_POST['address'],
		       "lat" => $_POST['lat'],
		      "log" => $_POST['log'],
		       "email" => $_POST['email'],
		         "fcm" => $_POST['fcm'],
		        "user" =>"new"
		       
		      
		   
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}



}


$conn->close();





?>