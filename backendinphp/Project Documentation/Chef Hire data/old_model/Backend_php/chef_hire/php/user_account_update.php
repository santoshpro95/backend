
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

/*get data from database*/
$name=$_POST['name'];
$address=$_POST['address'];
$phone=$_POST['phone'];

  $sql = "UPDATE user set name = '$name', address = '$address' where phone = '$phone' ";

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
