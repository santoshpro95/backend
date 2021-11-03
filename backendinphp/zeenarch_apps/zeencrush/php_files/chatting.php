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


$my_fb_id = $_POST['my_fb_id'];
$chat = $_POST['chat'];
$friend_fb_id = $_POST['friend_fb_id'];


$sql = "INSERT INTO persons_chat VALUES (NULL,'$my_fb_id','$chat','$friend_fb_id')";

if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "my_fb_id" => $_POST['my_fb_id'],
      "chat" => $_POST['chat'],
      "friend_fb_id" => $_POST['friend_fb_id']
 
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}
$conn->close();



?>



