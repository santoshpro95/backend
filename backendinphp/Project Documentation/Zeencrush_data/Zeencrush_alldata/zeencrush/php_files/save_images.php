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
$fb_id=$_POST['fb_id'];
$url =  $_POST['url'];
$img_id=  $_POST['img_id'];

$sql = "INSERT INTO images VALUES (NULL,'$fb_id', '$url','$img_id')";

     if ($conn->query($sql) === TRUE) {

 $postArray = array(
      "fb_id" => $_POST['fb_id'],
      "url" => $_POST['url'],
      "img_id"=> $_POST['img_id']
    );
    
 echo json_encode( $postArray);
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>