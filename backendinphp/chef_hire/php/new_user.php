

<?php
$servername = "localhost";
$username = "root";
$password = "";
$database ="chef_hire";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
} 


$name=$_POST['name'];
$phone =  $_POST['phone'];
$address=$_POST['address'];



		$sql = "Insert INTO user VALUES (NULL,'$name','$phone','$address')";


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		  
		      "name" => $_POST['name'],
		      "phone" => $_POST['phone'],
		      "address" => $_POST['address']
		      
		   
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	





$conn->close();





?>