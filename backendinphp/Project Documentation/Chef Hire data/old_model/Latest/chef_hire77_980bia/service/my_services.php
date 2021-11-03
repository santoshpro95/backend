<?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/

$phone = $_POST['phone'];

$sql = "SELECT * from user_service where phone = '$phone' ORDER BY id desc  ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

               
                      "s_id" => $row['s_id'],
		      "phone" => $row['phone'],
		       "time" => $row['duration'],
		       "veg" => $row['veg_only'],
		       
		       "breakfast"=>  $row['breakfast'],
			"lunch"=>  $row['lunch'],
			"dinner" =>  $row['dinner'],
			
			"days" =>  $row['days'],
			"name"=>  $row['name'],
			"price"=>  $row['price'],
			
			"status" =>  $row['status'],
			"address" =>  $row['address'],
			"end_date" =>  $row['end_date'],
			"order_id" =>  $row['order_id'],
			  "del_name" => $row['del_name'],
 			"del_phone" => $row['del_phone'],                   
 			"del_address" => $row['del_address']   
                   
                   
                   
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
