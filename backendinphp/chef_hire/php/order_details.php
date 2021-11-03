
      <?php
mysql_connect("localhost", "root", "") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/
$order_id=$_POST['order_id'];

  $sql = "SELECT * from chef_order where order_id = '$order_id' ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                 "user_name" => $row['user_name'],
      "user_phone" =>  $row['user_phone'],
      "user_address" => $row['user_address'],

      "chef_name"=> $row['chef_name'],
      "chef_phone" => $row['chef_phone'],
      "chef_address" => $row['chef_address'],

      "chef_id" => $row['chef_id'],
      "food_name" => $row['food_name'],
      "status" => $row['status'],

      "food_price" =>  $row['food_price'],
      "meals_no" => $row['meals_no'],
      "order_id" => $row['order_id'],
      "choose" => $row['choose']      
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
