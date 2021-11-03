      <?php
mysql_connect("localhost", "zeencrush", "santoshpro95") or die("Connection Failed");
mysql_select_db("zeencrush")or die("Connection Failed");

/*get data from database*/

$fb_id = $_POST['fb_id'];
$img_id = $_POST['img_id'];

  $sql = "Delete from images where fb_id ='$fb_id' and img_id='$img_id'";

        $result = mysql_query($sql);
/*check data should be more than one*/
           $postArray = array(
  
      "success" => "true"
    );

 echo json_encode( $postArray );

?>
