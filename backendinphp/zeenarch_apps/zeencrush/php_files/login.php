<?php
$servername = "localhost";
$username = "root";
$password = "";
$database ="zeencrush";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
} 


$fb_id=$_POST['fb_id'];
$username =  $_POST['name'];
$latitude=$_POST['lat'];
$longitude =  $_POST['log'];
$fcm_token =  $_POST['fcm_token'];
$gender =  $_POST['gender'];
$birthday=$_POST['birthday'];
// $email =  $_POST['email'];

$sql = "REPLACE INTO persons VALUES (NULL,'$username','$fb_id','$latitude', '$longitude','$gender','$birthday','$fcm_token')";

// $sql = "REPLACE INTO persons VALUES (NULL,'$username','$fb_id','$latitude', '$longitude')";


if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "name" => $_POST['name'],
      "fb_id" => $_POST['fb_id'],
      "lat" => $_POST['lat'],
      "log" => $_POST['log'],
      "gender" => $_POST['gender'],
      "birthday" => $_POST['birthday']
   
    );

 echo json_encode( $postArray );
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();

?>