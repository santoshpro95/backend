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
$email=$_POST['email'];
$address=$_POST['address'];
$chef_id = sprintf("%06d", mt_rand(1, 999999));


		$sql = "INSERT INTO chef VALUES (NULL,'$name','$phone','$address', '5.0', '', '$email','','','$chef_id','0','')";


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		  
		      "name" => $_POST['name'],
		      "phone" => $_POST['phone'],
		      "email" => $_POST['email'],
		       "address" => $_POST['address'],
		      "verify" => "0",
		      "chef_id" => $chef_id
		      
		   
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	





$conn->close();

?>