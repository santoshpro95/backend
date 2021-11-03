
      <?php
mysql_connect("localhost", "root", "") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/


  $sql = "SELECT * from meals ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                  "name" =>  $row['name'],
                  "price" =>  $row['price'],
                  "image" =>  $row['image'],
                    "items" =>  $row['items']
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
