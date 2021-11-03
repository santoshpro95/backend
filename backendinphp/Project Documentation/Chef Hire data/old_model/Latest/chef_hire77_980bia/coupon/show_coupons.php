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

$phone=$_POST['phone'];

  $sql = "SELECT * from user_coupons where phone = '$phone' ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {
         
         
          	  "name" =>  $row['name'],
                  "coupon_id" =>  $row['coupon_id'],
                  "exp_date" =>  $row['exp_date'],
                  "status" =>  $row['status'],
                  "phone" =>  $row['phone'],
                  "discount" =>  $row['discount']
         
         }
         
      }



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
        
          echo "Error: " . $sql2 . "<br>" . $conn->error;
    }
  


$conn->close();


?>
