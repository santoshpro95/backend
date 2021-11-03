<?php

$servername = "localhost";
$username = "chef_hire";
$password = "santoshpro95";
$database ="chef_hire";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection


if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
} 


$s_name=$_POST['name'];
$s_id = $_POST['s_id'];
$info=$_POST['info'];
$address=$_POST['address'];
$phone= $_POST['phone'];
$state=$_POST['state'];

$breakfast=  $_POST['breakfast'];
$lunch=  $_POST['lunch'];
$dinner=$_POST['dinner'];

$mon=$_POST['mon'];
$tue=$_POST['tue'];
$wed=$_POST['wed'];
$thu= $_POST['thu'];
$fri=$_POST['fri'];
$sat=$_POST['sat'];
$sun= $_POST['sun'];




$name= $_FILES['file']['name'];

$tmp_name= $_FILES['file']['tmp_name'];

$position= strpos($name, ".");

$fileextension= substr($name, $position + 1);

$fileextension= strtolower($fileextension);


  $sql = "Insert into services values(NULL, '$s_name', 'http://zeenarch.com/chef_hire77_980bia/images/$name', '$breakfast', '$lunch', '$dinner', '$info', '$address', '','','$phone', '$state' ) ";

  $sql2 = "Insert into menu values(NULL,'$s_id', '$mon', '$tue', '$wed', '$thu', '$fri', '$sat', '$sun') ";

     if ($conn->query($sql) === TRUE && $conn->query($sql2) === TRUE) {


           $postArray = array(

                "success" => "true"
    

                );

             

      $output = json_encode($postArray);
              echo $output;

     }
     else{

     }



if (isset($name)) {

$path= 'images/';
if (empty($name))
{
echo "Please choose a file";
}
else if (!empty($name)){
if ( ($fileextension !== "png") && ($fileextension !== "jpg")  )
{
echo "The file extension must be .png, .jppg, or .webm in order to be uploaded";
}


else if (($fileextension == "png") || ($fileextension == "jpg"))
{
if (move_uploaded_file($tmp_name, $path.$name)) {
echo 'Uploaded!';
}
}
}
}





$conn->close();





?>