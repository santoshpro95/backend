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

$rating=$_POST['rating'];
$chef_id=$_POST['chef_id'];
$order_id=$_POST['order_id'];


  $sql = "UPDATE chef set rating = '$rating' where chef_id = '$chef_id' ";
    $sql2 = "UPDATE chef_order set rating = '$rating' where order_id= '$order_id' ";

      if ($conn->query($sql) === TRUE && $conn->query($sql2) === TRUE) {
/*check data should be more than one*/
    

           $postArray = array(

                "success" => "true"
    

                );

             

      $output = json_encode($postArray);
              echo $output;
      }

      else {
        echo "Error: " . $sql . "<br>" . $conn->error;
        
          echo "Error: " . $sql2 . "<br>" . $conn->error;
    }
  


$conn->close();


?>
