

<?php
$servername = "localhost";
$username = "root";
$password = "";
$database ="chef_hire";
// Create connection
$conn = mysqli_connect($servername, $username,  $password, $database);
// Check connection
if ($conn->connect_error) {

    die("Connection failed: " . $conn->connect_error);
} 


$phone =  $_POST['phone'];
$chef_id = sprintf("%06d", mt_rand(1, 999999));



$result = mysqli_query($conn,"SELECT * FROM chef where phone = '$phone'");
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
		                  "choose" =>  $row['choose'],
		                   "status" =>  $row['status'],
		                    "chef_id" =>  $row['chef_id'],
		                     "verify" =>  $row['verify'],
		                   "user_type" =>  "old"	
            

						    );
			 
 				}// while close
 				 echo json_encode( $postArray );


		

}
else{
	 $postArray = array(

						     
		     			 "chef_id" => $chef_id,
		     			  "phone" => $_POST['phone'],
		                  "user_type" =>  "new"		                 
            

						    );
	  echo json_encode( $postArray );

}


$conn->close();





?>