<?php
$servername = "localhost";
$username = "root";
$password = "";
$database ="dj";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
}

$journal=$_POST['journal'];
$time = $_POST['time'];
$access= $_POST['access'];

$sql = "INSERT Into new_data values(null , '$journal',  '$time','$access')";

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