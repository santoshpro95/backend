      <?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/

$sql = "SELECT * FROM chef RIGHT JOIN meals ON chef.chef_id in (select chef_id from chef_meals) where chef.verify = 1 and chef.status = 1";
        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                  "name" =>  $row['name'],
                  "phone" =>  $row['phone'],
                   "address" =>  $row['address'],
                    "rating" =>  $row['rating'],
                  "image" =>  $row['image'],
                  "email" => $row['email'],
                   "status" => $row['status'],
                    "chef_id" => $row['chef_id'],
                    "meal_id" =>  $row['meal_id'],
                     "lat" => $row['lat'],
                       "log" => $row['log'],
                        "food_name" =>  $row['food_name'],
                       "food_image" =>  $row['food_image'],
                          "price" =>  $row['price'],
                            "items" =>  $row['items']
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
