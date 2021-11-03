
      <?php
mysql_connect("localhost", "zeencrush", "santoshpro95") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");

/*get data from database*/

$my_fb_id = $_POST['my_fb_id'];
$friend_fb_id = $_POST['friend_fb_id'];

  $sql = "SELECT * from persons_chat where (my_fb_id ='$my_fb_id' or friend_fb_id='$my_fb_id ') and (my_fb_id ='$friend_fb_id' or friend_fb_id='$friend_fb_id')";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {

           $postArray[] = array(

                  "my_fb_id" =>  $row['my_fb_id'],
                  "chat" =>  $row['chat'],
                  "friend_fb_id" =>  $row['friend_fb_id']
                );

             }

      $output = json_encode(array('data' => $postArray));
              echo $output;
      }

?>
