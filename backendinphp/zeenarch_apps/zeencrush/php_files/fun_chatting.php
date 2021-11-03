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


$my_fb_id = $_POST['my_fb_id'];
$chat = $_POST['chat'];
$friend_fb_id = $_POST['friend_fb_id'];
$friend_name = $_POST['friend_fb_name'];

$sql = "INSERT INTO fun_box VALUES (NULL,'$my_fb_id','$chat','$friend_fb_id','$friend_name')";

if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "my_fb_id" => $_POST['my_fb_id'],
      "chat" => $_POST['chat'],
      "friend_fb_id" => $_POST['friend_fb_id'],
      "friend_fb_name" => $_POST['friend_fb_name']
 
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}
$conn->close();



?>



