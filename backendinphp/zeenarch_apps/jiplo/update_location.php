<?php
$servername = "localhost";
$username = "root";
$password = "";
$database ="jiplo";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
}

$user_key=$_POST['user_key'];
$lat = $_POST['lat'];
$log= $_POST['log'];

$sql= "UPDATE user SET lat = '$lat', log ='$log'  WHERE user_key= '$user_key'  ";

if ($conn->query($sql) === TRUE ){
 $postArray = array(
      "success" => "true"
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql. "<br>" . $conn->error;
}
$conn->close();





?>