<html>

<?php

// Database Information

	mysql_connect("localhost", "root", "") or die("Connection Failed");
	mysql_select_db("zeencrush")or die("Connection Failed");



?>

<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
</head>

	<body>
		

		<div class="container">
			
					<?php  

					$curr_date = date("Y/m/d");

						echo "<h4 align='center'>Today is " . $curr_date . "</h4><br>";

						$sql = "SELECT * FROM persons";
								$result = mysql_query($sql);

											    if (mysql_num_rows($result) > 0) {
											         while ($row = mysql_fetch_array($result)) {

											         $time1 = strtotime($curr_date);
													 $date1  =  date('m/d',$time1); 
											         $time = strtotime($row['birthday']);
													 $date2  =  date('m/d',$time);  

													
											         if(!strcmp($date1,$date2)){

											         			echo " 	<div class='row'><div class='col'><h2>Today <span style='color:red'>".$row['name']."'s </span> birthday</h2></div>";

											         				echo "<div class='col'>	<h3 align='center'>".yearsDifference($curr_date,$row['birthday'])." Years old </h3></div>	</div>";  
																

											         			
											         }

											 	
											 			 		 
											   				}
														}
														else{
											 					echo "No employees list";
															}

															function yearsDifference($endDate, $beginDate)
																	{
																	   $date_parts1=explode("/", $beginDate);
																	   $date_parts2=explode("/", $endDate);
																	   return $date_parts2[0] - $date_parts1[0];
																	}



					?>


		
		<h6 align="center">Employees Details</h6>
			<table class="table table-striped">
				 <thead>
				      <tr>
				        <th>Name</th>
				        <th>Gender</th>
				        <th>Birthday</th>
				          <th>Email</th>
				      </tr>
			    </thead>

			    <tbody>
			    	
			    	<?php

								$sql = "SELECT * FROM persons";
								$result = mysql_query($sql);

											    if (mysql_num_rows($result) > 0) {
											         while ($row = mysql_fetch_array($result)) {

											         	echo "<tr><td>".$row['name']."</td>";
														echo "<td>".$row['gender']."</td>";
															echo "<td>".$row['birthday']."</td>";
														echo "<td>".$row['email']."</td></tr>";
			
											 			 		
											   				}
														}
														else{
											 					echo "No employees list";
															}

			    	?>


			    </tbody>


			</table>



		</div>



	</body>

</html>