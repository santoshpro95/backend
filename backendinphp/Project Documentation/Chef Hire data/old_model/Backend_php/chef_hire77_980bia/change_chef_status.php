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

$status=$_POST['available'];
$chef_id=$_POST['chef_id'];

  $sql = "UPDATE chef set status = '$status' where chef_id = '$chef_id' ";

      if ($conn->query($sql) === TRUE) {
/*check data should be more than one*/
    
         



           $postArray = array(

                "success" => "true"
    

                );

             

      $output = json_encode($postArray);
              echo $output;
      }

      else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
  


$conn->close();


?>
