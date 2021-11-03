<?php
error_reporting(0);
ob_start();
session_start();

$hostName = "localhost";
$dbUsername = "root";
$dbPassword = "";
$dbName = "cab";

$dbhandle = mysql_connect($hostName, $dbUsername, $dbPassword) or
        die("Unable to conect to MySQL");
mysql_select_db($dbName, $dbhandle)or
        die("Could not select example");

if (isset($_SESSION['user_id'])) {
    $user_id = $_SESSION['user_id'];
} else {
    $user_id = "";
}
if (isset($_SESSION['user_name'])) {
    $user_name = $_SESSION['user_name'];
} else {
    $user_id = "";
}
if (isset($_SESSION['user_type'])) {
    $user_type = $_SESSION['user_type'];
} else {
    $user_type = "";
}

if (isset($_SESSION['user_image'])) {
    $user_image = $_SESSION['user_image'];
} else {
    $user_image = "";
}

?>