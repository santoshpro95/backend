<?php
mysql_connect("localhost", "root", "") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");

/*get data from database*/

$my_fb_id=$_POST['my_fb_id'];


 /// $sql = "select * from persons_friend where (my_fb_id='$my_fb_id' or friend_fb_id ='$my_fb_id')";

 // $sql = "select * from friends where recipient_id ='$my_fb_id'  ";


  $sql = "SELECT sender_name,sender_id,gender,birthday,alpha,lat,log FROM persons p, friends f WHERE   p.fb_id = f.sender_id and f.recipient_id = '$my_fb_id' ";



        $result = mysql_query($sql);
/*check data should be more than one*/
    
			    if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {


 					 $postArray[] = array(

						      "name" =>  $row['sender_name'],
						      "fb_id" =>  $row['sender_id'],
                  "alpha" =>  $row['alpha'],
                   "gender" =>  $row['gender'],
                    "birthday" =>  $row['birthday'],
                     "lat" =>  $row['lat'],
                    "log" =>  $row['log']

						    );
   					 }

   		$output = json_encode(array('data' => $postArray));
   					  echo $output;
			}


?>



