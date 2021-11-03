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


/*get data from database*/
$fb_id=$_POST['fb_id'];
$url =  $_POST['url'];


$sql = "INSERT INTO images VALUES (NULL,'$fb_id', '$url')";

     if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
      "fb_id" => $_POST['fb_id'],
      "url" => $_POST['url']
    );

 echo json_encode( $postArray);
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>