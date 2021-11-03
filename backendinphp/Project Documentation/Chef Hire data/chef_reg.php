<html>



<head>
	<title>
		Chefooz
	</title>
	  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>

<body>
	
<div class="container">
 <img src="chefooz_logo.png">
<br>
<br>

	<form action="php_files/new_data.php" method="post">


	  <div class="form-group">
	    <label for="pwd">Access:</label>
	    <input type="text" enable="false" value="public" class="form-control" id="pwd" name="access">
	  </div>



	  <button style="margin-right: 10px" type="submit" class="btn btn-success">Submit</button>

	</form>

</div>


</body>

</html>

