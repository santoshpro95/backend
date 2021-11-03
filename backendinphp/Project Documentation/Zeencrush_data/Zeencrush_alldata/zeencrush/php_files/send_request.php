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


/*get data from database*/


$sender_id=$_POST['my_fb_id'];
$sender_name =  $_POST['my_name'];
$recipient_id=$_POST['friend_fb_id'];
$recipient_name =  $_POST['friend_name'];


$sql = "INSERT IGNORE INTO friends VALUES (NULL,'$sender_id',   '$sender_name',   '$recipient_id',    '$recipient_name','3')";

     if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "my_fb_id" => $_POST['my_fb_id'],
      "my_name" => $_POST['my_name'],
      "friend_fb_id" => $_POST['friend_fb_id'],
      "friend_name" => $_POST['friend_name']
    );

 echo json_encode( $postArray);
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>