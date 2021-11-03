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


$name=$_POST['name'];
$phone =  $_POST['phone'];
$password =  $_POST['password'];
$email=$_POST['email'];
$address=$_POST['address'];
$lat=$_POST['lat'];
$log=$_POST['log'];
$chef_id = sprintf("%10d", mt_rand(1, 9999999999));





$result = mysqli_query($conn,"SELECT * FROM chef where phone = '$phone'");
 $rowco  = mysqli_num_rows($result);
//echo $rowco ;


if($rowco > 0){	


		$postArray = array(
		  
		      "user" =>"old"
		   
		    );

		 echo json_encode( $postArray );

}
else{

	$sql = "INSERT INTO chef VALUES (NULL,'$name','$phone','$address', '5.0', '', '$email','','0','$chef_id','0','0','5','3','$lat', '$log','$password')";


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		  
		      "name" => $_POST['name'],
		      "phone" => $_POST['phone'],
		      "email" => $_POST['email'],
		       "address" => $_POST['address'],
		      "verify" => "0",
		      "chef_id" => $chef_id,
		       "chef_distance" => "3",
		        "lat" => $_POST['lat'],
		       "log" => $_POST['log'],
		        "user" =>"new"
		      
		   
		    );

		 echo json_encode( $postArray );
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}


}






	
	





$conn->close();

?>