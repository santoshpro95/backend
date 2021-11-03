<?php
mysql_connect("localhost", "root", "") or die("Connection Failed");
mysql_select_db("jiplo")or die("Connection Failed");

$user_key=$_POST['user_key'];

$sql = " SELECT lat , log from user WHERE user_key = '$user_key'  ";


     $result = mysql_query($sql);


  while ( $row = mysql_fetch_array($result) ) {

 				 $postArray = array(
    
						      "lat" =>  $row['lat'],
						       "log" =>  $row['log']
   							 );

   					 }
 echo json_encode( $postArray );
			
?>