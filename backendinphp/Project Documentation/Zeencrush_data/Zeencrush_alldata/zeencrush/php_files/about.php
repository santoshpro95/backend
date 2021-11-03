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
$about=$_POST['about'];
$profession = $_POST['profession'];
date_default_timezone_set("Asia/Calcutta");
$timee =date("Y/m/d/H/i/s") ;

$sql = "UPDATE persons SET about = '$about', profession ='$profession', time='$timee'  WHERE fb_id = '$fb_id';";

if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "success" => "true"
 
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();





?>