      <?php
mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

/*get data from database*/


$s_id=$_POST['s_id'];

  $sql = "select * from menu WHERE s_id = '$s_id' ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                  "monday" =>  $row['mon'],
                  "tuesday" =>  $row['tue'],
                  "wednesday" =>  $row['wed'],
                  "thursday" =>  $row['thu'],
                  
                  "friday" =>  $row['fri'],
                  "saturday" =>  $row['sat'],
                  "sunday" =>  $row['sun']
                 
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
