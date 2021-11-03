      <?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/

$chef_id=$_POST['chef_id'];

  $sql = "SELECT * from chef where chef_id = '$chef_id' ";

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
                  "email" =>  $row['email'],

                  "adhar" =>  $row['adhar'],
               
                  "verify" => $row['verify'],
                  "status" => $row['status'],
                   "chef_id" => $row['chef_id'],
                   
                    "distance" => $row['distance'],
                       "lat" => $row['lat'],
                   "log" => $row['log']

                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
