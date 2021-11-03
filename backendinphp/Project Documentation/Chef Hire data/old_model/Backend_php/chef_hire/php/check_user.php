

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




$result = mysqli_query($conn,"SELECT * FROM user where phone = '$phone'");
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