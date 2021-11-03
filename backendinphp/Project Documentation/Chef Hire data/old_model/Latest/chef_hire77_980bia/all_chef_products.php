      <?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/


  $sql = "select * from product";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                  "name" =>  $row['p_name'],
                  "price" =>  $row['p_price'],
                  "image" =>  $row['p_image']
                    
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
