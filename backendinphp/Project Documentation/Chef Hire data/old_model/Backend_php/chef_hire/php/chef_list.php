
      <?php
mysql_connect("localhost", "root", "") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/
$choose=$_POST['choose'];

  $sql = "SELECT * from chef where status = '1' and verify = '1' and choose = '$choose' ";

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
                     "choose" => $row['choose']
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
