<?php


mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");

$message="New Order Received";

  $sql = "SELECT fcm FROM delivery";


        $result = mysql_query($sql);
  



    while ($row = mysql_fetch_array($result)) {

	
	$token = $row[0];
	
	
	      
$authToken = 'key=AAAAw_duxaw:APA91bHhbgskAe0D6uM8Nc90By-4qfEG0WOV6nyjEUgzQYN-jI-hLZ5xC5GEm4DcOgISkpHe03DDf06DeON5LI8qM74F9MrSBR3nlORKDz3m8RrlPej40vdpG9mi-iWkHiHQ2w1INxIs';

	
	
// The data to send to the API
$postData = array(
    
    'notification' => array('body' => $message),
    'to' => $token
);

// Setup cURL
$ch = curl_init('https://fcm.googleapis.com/fcm/send');
curl_setopt_array($ch, array(
    CURLOPT_POST => TRUE,
    CURLOPT_RETURNTRANSFER => TRUE,
    CURLOPT_HTTPHEADER => array(
        'Authorization: '.$authToken,
        'Content-Type: application/json'
    ),
    CURLOPT_POSTFIELDS => json_encode($postData)
));

// Send the request
$response = curl_exec($ch);

// Check for errors
if($response === FALSE){
    die(curl_error($ch));
}

// Decode the response
$responseData = json_decode($response, TRUE);

	
        
	//echo $token;

    }
   $postArray = array(

                "success" => "true"
    

                );

             

      $output = json_encode($postArray);
              echo $output;
	
	
	

// Print the date from the response
//echo $responseData['published'];




?>