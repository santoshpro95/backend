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
$new_key=$_POST['new_key'];

$sql= "UPDATE user SET  user_key='$new_key', lat = '$lat', log ='$log'  WHERE user_key = '$user_key'  ";

//UPDATE user SET user_key='ppp' ,lat = '567', log ='457'  WHERE user_key = '123'



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