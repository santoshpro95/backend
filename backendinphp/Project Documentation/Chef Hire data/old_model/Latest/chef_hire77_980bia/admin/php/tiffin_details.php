<?php
$page = $_SERVER['PHP_SELF'];
$sec = "10";
?>

<html>


<head> 

  <title> Tiffin Details </title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.11.1.js"></script>
<script src="https://cdn.socket.io/socket.io-1.2.0.js"></script>
    <script src="https://code.jquery.com/jquery-1.11.1.js"></script>
   <meta http-equiv="refresh" content="<?php echo $sec?>;URL='<?php echo $page?>'">

</head>



<body>


<div class="container">




  <h2>Tiffin Order Details</h2>
  <p>Track order, we take 20% commision</p>     
  
  
  
  <a href = "http://zeenarch.com/chef_hire77_980bia/admin/admin.html"> Main Screen </a>
  
         
  <table class="table">
  


      <?php

$commision = 20/100;


mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");


  $sql = "SELECT * from user_service ORDER BY id desc ";




        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {

echo "  <thead>
      <tr>
        <th>s_id</th>
        <th>User phone</th>
        <th>Start date</th>
        <th>veg_only</th>
        
        
        <th>breakfast</th>
        <th>lunch</th>
        <th>dinner</th>
 
 
        <th>days</th>
        <th>Tiffin Outlet</th>
        <th>price</th>
        <th>profit</th>

        <th>status</th>
        <th>User address</th>
        <th>end_date</th>

        <th>order_id</th>
        <th>del_name</th>
        <th>del_phone</th>
        <th>pickup_address</th>
 <th>user_name</th>
        <th>tiffin Phone</th>
    
      </tr>
    </thead>


    
";


         while ($row = mysql_fetch_array($result)) {
         
	if($row['breakfast'] == "0"){
	$breakf ="No";
	}else{
	$breakf = "Yes";
	}
	
	if($row['lunch'] == "0"){
	$lunch="No";
	}else{
	$lunch= "Yes";
	}
	
	if($row['dinner'] == "0"){
	$dinner="No";
	}else{
	$dinner= "Yes";
	}
	
	if($row['veg_only'] == "0"){
	$veg_only="No";
	}else{
	$veg_only= "Yes";
	}
	
echo "  <tbody>
      <tr>
        <td>" .$row['s_id']. "</td>
        <td>" .$row['phone']."</td>
        <td>".$row['duration']. "</td>
        <td>".$veg_only."</td>
        
        <td>". $breakf ."</td>
        <td>".$lunch. "</td>
        <td>".$dinner."</td>
        
        
        <td>".$row['days']."</td>
           <td>".$row['name']."</td>
            <td>".$row['price']. " / ". $final = $row['price'] - $row['price'] * ($commision )  ."</td>
           
              <td><h3>". $profit = $row['price'] - ($row['price'] - $row['price'] * ($commision ) ) ."</h3></td>
           
        <td><h3 style = 'color: green'>".$row['status']."</h3></td>

        <td>".$row['address']."</td>

        <td>".$row['end_date']."</td>
        <td>".$row['order_id']."</td>
        <td>".$row['del_name']."</td>

          <td>".$row['del_phone']."</td>
        <td>".$row['pickup_address']."</td>
       <td>".$row['user_name']."</td>
        <td>".$row['service_phone']."</td>

      </tr>
    </tbody> ";




             }

    
      }

?>
</table>

</div>

</body>

</html>
