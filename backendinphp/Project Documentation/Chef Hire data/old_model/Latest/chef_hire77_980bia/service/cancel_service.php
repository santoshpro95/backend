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


$order_id =  $_POST['order_id'];

  $sql = "UPDATE user_service set status= 'cancel' where  order_id = '$order_id' ";


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		  
		    "success"=> "true"	      
		   
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	





$conn->close();

?>