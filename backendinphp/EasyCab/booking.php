<?php
include './dbconfigur.php';
if (!empty($user_id)) {
    $error = "";
    if (isset($_GET['id']) && !empty($_GET['id'])) {
        $fareid = mysql_real_escape_string($_GET['id']);
        $sql = "DELETE FROM fare WHERE id='" . $fareid . "'";
        $result = mysql_query($sql);
        $valueInsert = (int) $result;
        if ($valueInsert > 0) {
            header("location:fare_list.php?status=success");
        } else {
            $error = "User has not been deleted.";
        }
    }

   
?>
<html>
    <head>
        <title>Easy Cab Booking</title>
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
                <h4> Fare Prise List</h4>
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
                            <td class="grid_heading">From</td>
                            <td class="grid_heading">To</td>
                           
                            <td class="grid_heading">Total KM</td>
                            <td class="grid_heading">Total Cost</td>
                           
                            <td class="grid_heading">First KM</td>                            
                           <td class="grid_heading">Cost</td>                            
                           
                            <td class="grid_heading">Book</td>
                            
                        </tr>
                        <?php
                        $i = 0;
                        $sql = "";
                        if ($user_type == "user") {
                            $sql = "SELECT * FROM fare ORDER BY froms ASC";
                        } 

                        $result = mysql_query($sql);
                        if (mysql_num_rows($result) > 0) {
                            while ($row = mysql_fetch_array($result)) {
                                $i++;
                                ?>

                                <tr>
                                    <td class="grid_label" align="center"><?php echo $i; ?></td>
                                    <td class="grid_label"><?php echo $row['froms'] ?></td>
                                    <td class="grid_label"><?php echo $row['too'] ?></td>
                                    <td class="grid_label"><?php echo $row['total_km'] ?></td>
                                    <td class="grid_label"> <?php echo ucfirst($row['prize']) ?></td>      
                                   <td class="grid_label"> <?php echo ucfirst($row['first_km']) ?></td>      
                                   <td class="grid_label"> <?php echo ucfirst($row['rs']) ?></td>      
                                   
                                   <td class="grid_label" align="center"><a href="book_cab.php.?id=<?php echo $row ['id']; ?>">Book Cab</a></td>                            
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
?><?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

