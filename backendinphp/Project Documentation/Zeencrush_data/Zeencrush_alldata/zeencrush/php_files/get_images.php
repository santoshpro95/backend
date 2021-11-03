<?php
mysql_connect("localhost", "zeencrush", "santoshpro95") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");

/*get data from database*/

$my_fb_id=$_POST['fb_id'];


 /// $sql = "select * from persons_friend where (my_fb_id='$my_fb_id' or friend_fb_id ='$my_fb_id')";

 // $sql = "select * from friends where recipient_id ='$my_fb_id'  ";


  $sql = "SELECT * FROM images WHERE fb_id='$my_fb_id'";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
			    if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {


 					 $postArray[] = array(

						      "fb_id" =>  $row['fb_id'],
						      "url" =>  $row['url'],
						           "img_id" =>  $row['img_id']
            

						    );
   					 }

   		$output = json_encode(array('data' => $postArray));
   					  echo $output;
			}


?>



