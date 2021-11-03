<?php
$servername = "localhost";
$username = "chef_hire";
$password = "santoshpro95";
$database ="chef_hire";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
} 


$phone =  $_POST['phone'];
$password =  $_POST['password'];
$lat =  $_POST['lat'];
$log =  $_POST['log'];
$fcm=  $_POST['fcm'];



$result = mysqli_query($conn,"SELECT * FROM chef where phone = '$phone' and password = '$password'");

$result2 = mysqli_query($conn,"UPDATE chef set lat = '$lat', log = '$log', fcm='$fcm' where phone= '$phone'");
 $rowco  = mysqli_num_rows($result);
//echo $rowco ;


if($rowco > 0){

			while ($row = $result->fetch_assoc()  ) {

 			

 					 $postArray = array(

						    
		                 			    
		                  "name" =>  $row['name'],
		                  "phone" =>  $row['phone'],
		                  "address" =>  $row['address'],

		                   "rating" =>  $row['rating'],
		                  "image" =>  $row['image'],
		                  "email" =>  $row['status'],

		                  "adhar" =>  $row['adhar'],
		              
		                   "status" =>  $row['status'],
		                    "chef_id" =>  $row['chef_id'],
		                     "verify" =>  $row['verify'],
		                   "user_type" =>  "old",
               			   
               			    "lat" =>  $row['lat'],
		                    "log" =>  $row['log'],
		                    "fcm" =>  $row['fcm']	

						    );
			 
 				}// while close
 				 echo json_encode( $postArray );


		

}
else{
	 $postArray = array(

		                  "user_type" =>  "new"		                 
            

						    );
	  echo json_encode( $postArray );

}


$conn->close();





?>