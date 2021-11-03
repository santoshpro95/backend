<?php
include './dbconfigur.php';
if (!empty($user_id)) {
    $error = "";
    if (isset($_GET['id']) && !empty($_GET['id'])) {
      echo  $bookingid = mysql_real_escape_string($_GET['id']);
     echo   $sql = "DELETE FROM book WHERE id='" . $bookingid . "'";
        $result = mysql_query($sql);
        $valueInsert = (int) $result;
        if ($valueInsert > 0) {
            header("location:view_book_customer.php?status=success");
        } else {
            $error = "User has not been deleted.";
        }
    }
 
?>
<html>
    <head>
        <title>View Book Customer</title>
        <?php include './title.php'; ?>
        <script type="text/javascript">
            function deleteProperties() {
                var con = confirm("Are you sure want to delete.");
                if (con == true) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <!--  start-wrap -->
        <?php include './header.php'; ?>
        <!--  end-header -->

        <?php include './leftmenu.php'; ?>
        <div class="gallery">
            <div class="about">
                <h4>View Booked Customers</h4>
                <form class="form-light mt-20" role="form" method="post" action="">
                    <table class="table_list" cellspacing="2" cellpadding="2" width="100%">
                        <?php
                        if (isset($_GET['status']) && $_GET['status'] == "success") {
                            echo '<div class="indv_fields " style="color:red"> Fare  has been successfully deleted </div>';
                        } elseif (isset($_GET['updatestatus']) && $_GET['updatestatus'] == "success") {
                            echo '<div class="indv_fields " style="color:red">Property has been approved.</div>';
                        }
                        if (!empty($error)) {
                            echo '<tr><td>' . $error . '</td></tr>';
                        }
                        ?>
                        <div class="clear"></div>
                        <tr>
                            <td class="grid_heading">S.No</td>
                            <td class="grid_heading">Name</td>
                            <td class="grid_heading">From</td>
                            <td class="grid_heading">To</td>
                            <td class="grid_heading">Total KM</td>
                            <td class="grid_heading">Total Cost</td>
                            
                           <td class="grid_heading">Delete</td>                           

                        </tr>
                        <?php
                        $i = 0;
                        $sql = "";
                        if ($user_type == "admin") {
                            $sql = "SELECT b.id,r.fname,b.froms,b.too,b.total_km,b.total_fare  FROM register r JOIN book b WHERE r.id = b.userid   ";
                        } 

                        $result = mysql_query($sql);
                        if (mysql_num_rows($result) > 0) {
                            while ($row = mysql_fetch_array($result)) {
                                $i++;
                                ?>

                                <tr>
                                    
                                    <td class="grid_label" align="center"><?php echo $i; ?></td>
                                    <td class="grid_label"><?php echo $row['fname'] ?></td>
  
                                    <td class="grid_label"><?php echo $row['froms'] ?></td>
                                    <td class="grid_label"><?php echo $row['too'] ?></td>
                                    <td class="grid_label"><?php echo $row['total_km'] ?></td>
                                    <td class="grid_label"> <?php echo ($row['total_fare']) ?></td>      

                                    <td class="grid_label" align="center"><a href="view_book_customer.php?id=<?php echo $row ['id']; ?>" onclick="return deleteProperties()">Delete</a></td>                            
                                    </td>                            

                                </tr>
                                <?php
                            }
                        }
                        ?>
                    </table>
                </form>

            </div>
            <div class="clear"> </div>
        </div>
        <?php include 'footer.php'; ?>
    </div>
</body>
</html>
  <?php
} else {
    header("location:index.php?msg=login");
    ob_flush();
}
?>