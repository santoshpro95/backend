      <?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/


  $sql = "select * from services";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                 
                   "name" =>  $row['name'],
                  "breakfast" =>  $row['breakfast'],
                   "lunch" =>  $row['lunch'],
                   "dinner" =>  $row['dinner'],
                   "phone" =>  $row['phone'],
                  "image" =>  $row['image'],
                  "info" =>  $row['info'],
                  "s_id" =>  $row['s_id'],
                 "address" =>  $row['address'],
                     "lat" =>  $row['lat'],
                  "log" =>  $row['log'],
                   "state" =>  $row['state']
                 
                  
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
