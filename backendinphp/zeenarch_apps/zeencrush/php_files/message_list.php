<?php
mysql_connect("localhost", "root", "") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");

/*get data from database*/

$my_fb_id=$_POST['my_fb_id'];


  $sql = "select * from friends where (sender_id='$my_fb_id' and alpha='1')  ";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
			 
          if (mysql_num_rows($result) > 0) {
         while ($row = mysql_fetch_array($result)) {



 					 $postArray[] = array(

						      "name" =>  $row['recipient_name'],
						      "fb_id" =>  $row['recipient_id']
        
						    );

   					 }

   		$output = json_encode(array('data' => $postArray));
   					  echo $output;
			}
      else{
           $postArray[] = array(

                  "data" =>  "null"
        
                );
    $output = json_encode(array('data' => $postArray));
              echo $output;
      }


?>



