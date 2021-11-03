<?php
mysql_connect("localhost", "zeencrush", "santoshpro95") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");

/*get data from database*/

$fb_id=$_POST['fb_id'];


 /// $sql = "select * from persons_friend where (my_fb_id='$my_fb_id' or friend_fb_id ='$my_fb_id')";

 // $sql = "select * from friends where recipient_id ='$my_fb_id'  ";


  $sql = "SELECT * FROM persons p, friends f WHERE   p.fb_id = f.sender_id and f.recipient_id = '$fb_id' ";


        $result = mysql_query($sql);
/*check data should be more than one*/
    
			    if (mysql_num_rows($result) > 0) {

         $postArray[] = array(

                  "number" =>  mysql_num_rows($result)

                );

   		$output = json_encode(array('data' => $postArray));
   					  echo $output;
			}
      else{
              $postArray[] = array(

                  "number" =>  mysql_num_rows($result)

                );
                $output = json_encode(array('data' => $postArray));
              echo $output;
      }


?>



