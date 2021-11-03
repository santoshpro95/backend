<?php 



$servername = "localhost";
$username = "root";
$password = "";
$database ="chef_hire";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
}

$phone=$_POST['phone'];
$adhar =  $_POST['adhar'];

$name= $_FILES['file']['name'];

$tmp_name= $_FILES['file']['tmp_name'];

$position= strpos($name, ".");

$fileextension= substr($name, $position + 1);

$fileextension= strtolower($fileextension);





  $sql = "UPDATE chef set verify = '1', adhar= '$adhar', image = 'http://zeenarch.com/chef_hire/images/$name' where phone = '$phone' ";



     if ($conn->query($sql) === TRUE) {


           $postArray = array(

                "success" => "true"
    

                );

             

      $output = json_encode($postArray);
              echo $output;

     }
     else{

     }



if (isset($name)) {

$path= 'Uploads/';
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
