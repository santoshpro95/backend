      <?php
mysql_connect("localhost", "root", "") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");

/*get data from database*/

$my_fb_id = $_POST['my_fb_id'];
$friend_fb_id = $_POST['friend_fb_id'];

  $sql = "Delete from persons_chat where (my_fb_id ='$friend_fb_id' or friend_fb_id='$friend_fb_id ') and (my_fb_id ='$my_fb_id' or friend_fb_id='$my_fb_id ') ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
           $postArray = array(
  
      "success" => "true"
    );

 echo json_encode( $postArray );
    

?>
