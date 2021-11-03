<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Broadcast</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

    <!-- Custom styles for this template -->
  

<style type="text/css">
  
.card-container.card {
    max-width: 100%;
    padding: 10px 10px;
    margin-top: 10px;
    

}


.card-container.card2 {
    max-width: 100%;
    padding: 5px 5px 3px 3px;
   display: inline-flex;

    

}

  .card {
    background-color: #ffffff;
     
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    height: 100%;
    -webkit-border-radius: 2px;
    border-radius: 5px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 0px 10px  rgba(0, 0, 0, 0.3);
}


  .card2 {
    background-color: #ffffff;
     
    /* shadows and rounded borders */
    
  
    -moz-border-radius: 2px;
    
    -webkit-border-radius: 2px;
    border-radius: 205px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 0px 10px  rgba(0, 0, 0, 0.3);
}

  .circle {
 width: 25px;
 height: 25px;
 border-radius: 50%;
 font-size: 15px;
 color: #fff;

 text-align: center;
 background: #6480F6
}


./*form-inline {
    padding:5px;
}
.form-inline > * {
    margin: 1px 10px  !important;
}*/


.fake-input { position: relative; width:240px; }
.fake-input input { border:none: background:#fff; display:block; width: 100%; box-sizing: border-box; }
.fake-input img { position: absolute; top: 2px; right: 5px }

.ScrollStyle
{
    max-height: 350px;
    overflow-y: scroll;
}
</style>


  </head>

  <body>

 

    <!-- Page Content -->
    <div class="container-fluid" >
<p style="text-align: left; font-size:  35px; font-family: Freestyle Script">ZEENARCH.com</p>



      <!-- Portfolio Item Row -->
      <div class="row" >

        <div class="col-md-8">
         
    <div  class="card card-container " style="   background-color:white; border-radius: 5px;" width=100% height=100% controls >
               <div width=100% align="right" >
		</div>
      
       <div class="ScrollStyle">
   


<?php
$servername = "localhost";
$username = "zeencrush";
$password = "santoshpro95";
$dbname = "developers_journal";
// Create connection

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
else{

}

$sql = "SELECT * FROM new_data";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
           
 echo "<table class='table table-hover' >   <tbody>";
 
    while($row = $result->fetch_assoc()) {
      
        
             echo "<tr><td>".$row["journal"]."</td><td>".$row["datetime"]."</td></tr>";
        
    }
    echo "  </tbody> </table>";
} else {
    echo "0 results";
}

$conn->close();
?> 












      </div>
	      <br>
	 
	      <div width=100%>
	      <img src="image/simpleman.jpg"  style="float: right" width="125" height="140" alt=""/>
		</div>
      <div width=100%>
      
      <style>
.button {
    background-color: #e42f15; /* Green */
    border: none;
    color: white;
    
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
width: 170px;
		  }

.button4 {border-radius: 12px;}

</style>



		</div>
      
      
      
       </div>
        </div>
        

        <div class="col-md-4">

              <div  class="card card-container " >


                  <form class="form-inline">

                     <div class="col-sm-6"> 

                    <div class="form-group">              
               <p style="font-size: 18px; font-style: bold; margin-top: 10px;">Developer's Journal</p>
                  </div>

                    </div>

                    <div class="col-sm-6">
                                  <div class="form-group">   
               

            </div>


            </div>
            
            </form>

              <br>
             <div style="max-height: 500px; height: 100%; width: 100%;">
                



<form action="php_files/new_data.php" method="post">
  <div class="form-group">


<div class="radio">
  <label><input type="radio" name="access" checked value="1"> Public</label>
</div>
<div class="radio">
  <label><input type="radio" name="access" value="0"> Private</label>
</div>

  </div>


<div class="form-group">
  <label for="comment">Journal:</label>
  <textarea class="form-control" name="journal" rows="10" placeholder="Write here" class="form-control" required autofocus></textarea>
</div>





  <button style="margin-right: 10px" type="submit" class="btn btn-success">Submit</button>

</form>

                </div>


                 <div class="form-group" style="position: relative;">


            </div>





<!-- <div class="form-group" style="position: relative;top: 100%;transform: translateY(-260%);">
  <input type="text" class="form-control demo" id="usr">

   <img src="image/send.png"  style=" position:absolute; right:5px; top:5px; height:  35px;" /> -->




</div>

              </div>

        </div>

      </div>
    
      <!-- /.row -->

    </div>
    <!-- /.container -->



    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
