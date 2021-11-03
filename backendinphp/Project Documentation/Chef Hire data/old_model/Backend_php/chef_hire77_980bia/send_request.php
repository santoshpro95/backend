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
$address=$_POST['address'];
$lat=$_POST['lat'];
$log=$_POST['log'];





$result = mysqli_query($conn,"SELECT * FROM user_request where phone = '$phone'");
 $rowco  = mysqli_num_rows($result);
//echo $rowco ;


if($rowco > 0){	


		$postArray = array(
		  
		      "user" =>"old"
		   
		    );

		 echo json_encode( $postArray );

}
else{

	$sql = "INSERT INTO user_request VALUES (NULL,'$phone','$lat','$log','$address','$name')";


		if ($conn->query($sql) === TRUE) {

		 $postArray = array(
		  
		      "name" => $_POST['name'],
		      "phone" => $_POST['phone'],
		       "address" => $_POST['address'],
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