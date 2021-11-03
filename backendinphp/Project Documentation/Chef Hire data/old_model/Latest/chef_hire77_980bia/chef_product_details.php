      <?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/
$order_id=$_POST['order_id'];

  $sql = "SELECT * from chef_product where order_id = '$order_id' ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

             
                      "p_name" => $row['p_name'],
      "p_price" =>  $row['p_price'],
      "no" => $row['quantity'],

      "status"=> $row['status'],
      "chef_phone" => $row['c_phone'],
      "chef_address" => $row['c_address'],

      "chef_id" => $row['chef_id'],
      
      "time" =>  $row['order_date']
         
         
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
