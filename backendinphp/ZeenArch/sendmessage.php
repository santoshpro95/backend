<?php

$servername = "localhost";
$username = "root";

$password = "";
$database ="zeenarch";
// Create connection
$conn = new mysqli($servername, $username,  $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

// sql to create table
$sql = "CREATE TABLE if not exists user_message(id INT(6) UNSIGNED 
AUTO_INCREMENT PRIMARY KEY, name VARCHAR(40),email VARCHAR(50),
subject VARCHAR (40),message VARCHAR(200))";

if ($conn->query($sql) === TRUE) {
  
} else {
    echo "Error creating table: " . $conn->error;
}

$name=$_POST['name'];
$email=$_POST['email'];
$subject=$_POST['subject'];
$message=$_POST['message'];

$sql2 = "INSERT INTO user_message VALUES (NULL,'$name','$email',
'$subject','$message')";

if ($conn->query($sql2) === TRUE) {
echo "<h2>Your message has been sent. Thank you!</h2>";

    header('Refresh:2 ; url= index.html');
	
} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}
$conn->close();
?>