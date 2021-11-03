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

/*get data from database*/
$password=$_POST['password'];
$phone=$_POST['phone'];
  
  
$result = mysqli_query($conn,"SELECT * FROM chef where phone = '$phone'");
$result2 = mysqli_query($conn,"UPDATE chef set password= '$password' where phone = '$phone'");



 $rowco  = mysqli_num_rows($result);
//echo $rowco ;


if($rowco > 0){

           $postArray = array(

                "success" => "true"
    

                );

             

      $output = json_encode($postArray);
              echo $output;
    


  }else{
  
   $postArray = array(

                "success" => "false"
    

                );

             

      $output = json_encode($postArray);
              echo $output ;
  
  }

  


$conn->close();


?>
