<html>



<head>

</head>



<body>


<?php




$servername = "localhost";
$username = "zeencrush";
$password = "santoshpro95";
$dbname = "developers_journal";
// Create connection
$conn = new mysqli($servername, $username,  $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully";





	if(!empty($_POST['username']) && !empty($_POST['password']))
	{
		$user=$_POST['username'];
		$pass= $_POST['password'];
		$select = "select * from user_reg where username= '$user' and password='$pass'";
		$sql= mysql_query($select) or die(mysql_error());
		

		if(mysql_num_rows($sql) == 1 )
		{
			echo "Login successfully";

header("Location: new_data.html");

		}
		else
		{
			echo " can't login";
		}
	}
	else
		echo "login Failed";

?>


</body>
</html>