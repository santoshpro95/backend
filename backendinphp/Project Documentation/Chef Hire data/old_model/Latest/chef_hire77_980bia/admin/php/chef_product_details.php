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




  <h2>CHEF Product Order Details</h2> 
  
  
  
  <a href = "http://zeenarch.com/chef_hire77_980bia/admin/admin.html"> Main Screen </a>
         
  <table class="table">
  


      <?php
$commision = 20/100;

mysql_connect("localhost", "chef_hire", "santoshpro95") or die("Connection Failed");
mysql_select_db("chef_hire")or die("Connection Failed");


  $sql = "SELECT * from chef_product ORDER BY id desc";

        $result = mysql_query($sql);
/*check data should be more than one*/
    
    
    
          if (mysql_num_rows($result) > 0) {

echo "  <thead>
      <tr>
        <th>product name</th>
        <th>product price</th>
        <th>quantity</th>

        <th>status</th>
        <th>chef phone</th>
        <th>chef address</th>

        <th>chef id</th>
        <th>order date</th>
        <th>delivery date</th>
        
      </tr>
    </thead>


    
";


         while ($row = mysql_fetch_array($result)) {

echo "  <tbody>
      <tr>
        <td>" .$row['p_name']. "</td>
        <td>" .$row['p_price']."</td>
        <td>".$row['quantity']. "</td>
        
        
 <td><h3 style = 'color: green'>".$row['status']."</h3></td>
        <td>".$row['c_phone']."</td>
        <td>".$row['c_address']."</td>
        <td>".$row['chef_id']."</td>

        <td>".$row['order_date']."</td>
        <td>".$row['delivery_date']."</td>
   

      </tr>
    </tbody> ";




             }

    
      }

?>
</table>

</div>

</body>

</html>
