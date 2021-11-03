<?php

$token=$_POST['token'];
$message=$_POST['message'];


$authToken = "key=AAAAw_duxaw:APA91bHhbgskAe0D6uM8Nc90By-4qfEG0WOV6nyjEUgzQYN-jI-hLZ5xC5GEm4DcOgISkpHe03DDf06DeON5LI8qM74F9MrSBR3nlORKDz3m8RrlPej40vdpG9mi-iWkHiHQ2w1INxIs";


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

// Print the date from the response
//echo $responseData['published'];


           $postArray = array(

                "success" => "true"
    

                );

             

      $output = json_encode($postArray);
              echo $output;


?>