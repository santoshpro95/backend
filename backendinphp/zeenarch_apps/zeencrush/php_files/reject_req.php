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


$my_fb_id=$_POST['my_fb_id'];
$friend_fb_id=$_POST['friend_fb_id'];

$sql = "DELETE FROM friends WHERE sender_id = '$friend_fb_id' and recipient_id = '$my_fb_id' ;";


$sql2 = "DELETE FROM friends WHERE sender_id = '$my_fb_id' and recipient_id = '$friend_fb_id' ;";
if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "success" => "true"
    );

} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}


if ($conn->query($sql2) === TRUE) {

 $postArray = array(
  
      "success" => "true"
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}
$conn->close();



?>