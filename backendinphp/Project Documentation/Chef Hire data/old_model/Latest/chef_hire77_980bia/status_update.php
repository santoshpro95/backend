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

$status=$_POST['status'];
$order_id=$_POST['order_id'];
$cur_time=$_POST['cur_time'];

  $sql = "UPDATE chef_order set status = '$status', delivery_time = '$cur_time' where order_id = '$order_id' ";


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
