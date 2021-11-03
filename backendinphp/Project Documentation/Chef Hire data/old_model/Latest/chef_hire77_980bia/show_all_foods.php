      <?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/


  $sql = "select * from meals WHERE verify = '1' ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                  "name" =>  $row['food_name'],
                  "price" =>  $row['price'],
                  "image" =>  $row['food_image'],
                    "items" =>  $row['items'],
                    "meal_id" =>  $row['meal_id']
                    
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
