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
$lat = $_POST['lat'];
$log= $_POST['log'];

$sql = "UPDATE persons SET lat = '$lat', log ='$log'  WHERE fb_id = '$fb_id';";

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