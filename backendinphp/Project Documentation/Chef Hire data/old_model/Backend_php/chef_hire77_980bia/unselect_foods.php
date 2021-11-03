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


$chef_id=$_POST['chef_id'];
$meal_id =  $_POST['meal_id'];



		$sql = "DELETE FROM chef_meals WHERE chef_id = '$chef_id' and meal_id = '$meal_id' ";


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