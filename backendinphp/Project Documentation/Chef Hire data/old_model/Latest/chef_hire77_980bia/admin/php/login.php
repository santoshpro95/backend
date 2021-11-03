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


$email =  $_POST['email'];
$password =  $_POST['password'];



$result = mysqli_query($conn,"SELECT * FROM admin where email = '$email' and password = '$password'");

 $rowco  = mysqli_num_rows($result);
//echo $rowco ;


if($rowco > 0){

		
  header("Location:../index.html");
  exit();
		

}
else{
	 
	  echo "Incorrect Email or Password";

}


$conn->close();





?>