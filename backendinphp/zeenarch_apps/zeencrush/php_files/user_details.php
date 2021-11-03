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


$fb_id = $_POST['fb_id'];

$sql = "SELECT * from persons where fb_id = '$fb_id' where active = 'true' ";

if ($conn->query($sql) === TRUE) {

 $postArray = array(
  
						"id" =>  $row['ID'],
						      "name" =>  $row['name'],
						      "fb_id" =>  $row['fb_id'],
                  "lat" =>  $row['lat'],
                  "log" =>  $row['log'],
                  "gender" =>  $row['gender'],
                  "birthday" =>  $row['birthday'],
                       "time" =>  $row['time'],
                        "about" =>  $row['about'],
                         "profession" =>  $row['profession'],
                         "location"=> $row['location']
 
    );

 echo json_encode( $postArray );

} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();



?>



