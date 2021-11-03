<?php


mysql_connect("localhost", "zeencrush", "santoshpro95") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");
$fb_id=$_POST['fb_id'];
$message=$_POST['message'];

  $sql = "SELECT * FROM persons WHERE fb_id='$fb_id'";


        $result = mysql_query($sql);



    while ($row = mysql_fetch_array($result)) {


$token = $row[7];

echo $token;

    }

$authToken = 'key=AAAANVkQSag:APA91bFT7N6NoVFZPiYNOBzysAdeEGl41_gEWjRIqc9-gffqcW-oU2U-dhHbw1r6u-Q-jFMqpXLC7rNBy25j8hPWosi9-LzI6vF84lleilfiNo_XI0cIdzTswwdGindVgAmzlVNr_oNL';


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
echo $responseData['published'];




?>