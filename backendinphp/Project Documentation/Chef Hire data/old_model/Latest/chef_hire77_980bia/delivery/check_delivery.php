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


$id_no=  $_POST['id_no'];

$fcm=  $_POST['fcm'];

$result = mysqli_query($conn,"SELECT * FROM delivery where company_id= '$id_no'");
$result2 = mysqli_query($conn,"UPDATE delivery set fcm='$fcm' where company_id= '$id_no'");

 $rowco  = mysqli_num_rows($result);
//echo $rowco ;


if($rowco > 0){


			while ($row = $result->fetch_assoc()  ) {

 			

 					 $postArray = array(

						    
		                  "name" =>  $row['name'],
		                  "phone" =>  $row['phone'],
		                  "address" =>  $row['address'],
		                   "user_type" =>  "old"
		                 
            

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