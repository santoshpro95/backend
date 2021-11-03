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

$fname=$_POST['name'];
$price=  $_POST['price'];
$items=  $_POST['items'];
$meal_id=  $_POST['meal_id'];


$name= $_FILES['file']['name'];

$tmp_name= $_FILES['file']['tmp_name'];

$position= strpos($name, ".");

$fileextension= substr($name, $position + 1);

$fileextension= strtolower($fileextension);





  $sql = "Insert into meals values(NULL, '$fname', '$price', 'http://zeenarch.com/chef_hire77_980bia/images/$name', '$items', '$meal_id', '1') ";



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
