<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

        <!-- Theme CSS -->
    <link href="css/broadcast.css" rel="stylesheet">


<body>

          <div class="container">

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
$result = mysqli_query($conn,"SELECT * FROM chef");
 $rowco  = mysqli_num_rows($result);

echo "<img src='chefooz_logo.png' style='height: 100px;'> ";
 echo " <h3 style='color: #E50000; font-weight: 700; text-align: center;float: right;'>".$rowco."Chefs Registered</h3>";
?>


      

        <div class="card card-container">

<br>

        
         
            
				<form method="post" action="http://www.zeenarch.com/chefooz/chef_hire77_980bia/chef/chef_reg.php">


			<div class="row">
						<!-- 1st Column -->
						<div class="col-sm-6">
						  <div class="form-group">
				    <label for="email">Name:</label>
				    <input type="name" class="form-control" id="name" required>
				  </div>
				
				  <div class="form-group">
				    <label for="email">Email Id:</label>
				    <input type="email" class="form-control" id="email">
				  </div>

				  <div class="form-group">
				    <label for="pwd">Phone No:</label>
				    <input type="number" class="form-control" id="phone">
				  </div>
			
					</div>

					<!-- 2nd Column -->

					<div class="col-sm-6">
					
  <div class="form-group">
				    <label for="pwd">Address:</label>
				    <input type="address" class="form-control" id="address">
				  </div>


				  	  <div class="form-group">
				    <label for="email">Password:</label>
				   <p>Default Password is : 1234</p>
				  </div>
		
			
					</div>




			</div>




				  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
              
            
            
        </div><!-- /card-container -->
    </div><!-- /container -->






</body>
</html>
