<?php
$servername = "localhost";
$username = "zeencrush";
$password = "santoshpro95";
$database ="zeencrush";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
}

$fb_id=$_POST['fb_id'];
$private = $_POST['location'];

$sql = "UPDATE persons SET location = '$private' WHERE fb_id = '$fb_id';";

if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "success" => "true"
 
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}
$conn->close();





?>