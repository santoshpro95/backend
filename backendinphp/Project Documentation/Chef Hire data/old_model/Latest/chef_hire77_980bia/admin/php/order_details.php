<?php
$page = $_SERVER['PHP_SELF'];
$sec = "10";
?>

<html>


<head> 

  <title> Order Details </title>

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




  <h2>Food Order Details</h2>
<p>Track order, we take 20% commision</p>    
  
  
  
  <a href = "http://zeenarch.com/chef_hire77_980bia/admin/admin.html"> Main Screen </a>
         
  <table class="table">
  


      <?php
$commision = 20/100;

mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");


  $sql = "SELECT * from chef_order ORDER BY id desc";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
          if (mysql_num_rows($result) > 0) {

echo "  <thead>
      <tr>
        <th>user_name</th>
        <th>user_phone</th>
        <th>user_address</th>

        <th>chef_name</th>
        <th>chef_phone</th>
        <th>chef_address</th>

        <th>chef_id</th>
        <th>food_name</th>
        <th>status</th>

        <th>food_price</th>
          <th>profit</th>
        <th>meals_no</th>
        <th>order_id</th>

        <th>rating</th>
        <th>Order date</th>
        <th>delivery_time</th>


        <th>lat</th>
        <th>log</th>
        <th>del_price</th>

        
        <th>del_name</th>
        <th>del_phone</th>
        
      </tr>
    </thead>


    
";


         while ($row = mysql_fetch_array($result)) {

echo "  <tbody>
      <tr>
        <td>" .$row['user_name']. "</td>
        <td>" .$row['user_phone']."</td>
        <td>".$row['user_address']. "</td>

        <td>".$row['chef_name']."</td>
        <td>".$row['chef_phone']."</td>
        <td>".$row['chef_address']."</td>

        <td>".$row['chef_id']."</td>
        <td>".$row['food_name']."</td>
        <td><h3 style = 'color: green'>".$row['status']."</h3></td>

        <td>".$row['food_price']. " / ". $final = $row['food_price'] - $row['food_price'] * ($commision)  ."</td>
           <td><h3>". $profit = $row['food_price'] - ($row['food_price'] - $row['food_price'] * ($commision ) ) ."</h3></td>
        <td>".$row['meals_no']."</td>
        <td>".$row['order_id']."</td>

        <td>".$row['rating']."</td>
        <td>".$row['time']."</td>
        <td>".$row['delivery_time']."</td>

          <td>".$row['lat']."</td>
        <td>".$row['log']."</td>
        <td>".$row['del_price']."</td>


          <td>".$row['del_name']."</td>
        <td>".$row['del_phone']."</td>
   

      </tr>
    </tbody> ";




             }

    
      }

?>
</table>

</div>

</body>

</html>
