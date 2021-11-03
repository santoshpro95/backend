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

$recipient_name=$_POST['my_fb_name'];
$sender_name=$_POST['friend_fb_name'];

$recipient_id=$_POST['my_fb_id'];
$sender_id=$_POST['friend_fb_id'];

$sql = "UPDATE friends SET alpha = '1' where sender_id = '$sender_id' and recipient_id = '$recipient_id' ";

$sql2 = "INSERT INTO friends VALUES (NULL,'$recipient_id',   '$recipient_name',   '$sender_id', '$sender_name', '1')";


if ($conn->query($sql) === TRUE && $conn->query($sql2)) {

 $postArray = array(
  
      "success" => "true"
 
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}



$conn->close();





?>