<?php
$servername = "localhost";
$username = "zeencrush";
$password = "santoshpro95";
$database ="developers_journal";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
}

$journal=$_POST['journal'];

date_default_timezone_set("Asia/Calcutta");
$timee = date("Y/m/d/H/i/s") ;

$access= $_POST['access'];

$sql = "INSERT Into new_data values(null , '$journal',  '$timee','$access')";

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