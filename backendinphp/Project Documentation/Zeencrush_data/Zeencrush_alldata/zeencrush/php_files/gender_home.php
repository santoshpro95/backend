<?php
mysql_connect("localhost", "zeencrush", "santoshpro95") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");


$my_fb_id=$_POST['my_fb_id'];
$gender=$_POST['gender'];
//$my_fb_id=1305207302935169;
  // $sql = "Select name,fb_id,lat,log,gender,birthday from persons where fb_id not in (select recipient_id from friends where sender_id = '120') and fb_id != '120' UNION ALL Select sender_id from friends where recipient_id not in (select fb_id from persons)";


// $sql = "Select * from persons WHERE fb_id= (Select fb_id from persons where fb_id not in (select recipient_id from friends where sender_id = '$my_fb_id') and fb_id != '$my_fb_id' UNION Select sender_id from friends where recipient_id not in (select fb_id from persons)  )  ";


$sql = "Select * from persons where fb_id not in (select recipient_id from friends where sender_id = '$my_fb_id') and fb_id != '$my_fb_id' and gender = '$gender' ";

 // $sql = "Select name,fb_id,lat,log,gender,birthday from persons ";
        $result = mysql_query($sql);
/*check data should be more than one*/
      // echo $result;

			    if (mysql_num_rows($result) > 0) {

         while ( $row = mysql_fetch_array($result) ) {

 					 $postArray[] = array(
							"id" =>  $row['ID'],
						      "name" =>  $row['name'],
						      "fb_id" =>  $row['fb_id'],
                  "lat" =>  $row['lat'],
                  "log" =>  $row['log'],
                  "gender" =>  $row['gender'],
                  "birthday" =>  $row['birthday']

						    );

   					 }

   		$output = json_encode(array('data' => $postArray));
   					  echo $output;
			}
      else{
     
     
      }



?>



