<?php
include './dbconfigur.php';
if (!empty($user_id)) {
    ?>
    <html>
        <head>
            <title>Properties Order Status - Multi Real Estate Business Corporation</title>
            <?php include './title.php'; ?>
        </head>
        <body>            
            <?php include './header.php'; ?>
            <?php include './leftmenu.php'; ?>
            <div class="gallery">
                <div class="about" style="min-height: 450px;">
                    <h4>Booking Order Status</h4>
                    <form class="form-light mt-20">
                        <table class="table_list" cellspacing="2" cellpadding="2" width="100%">  
                           
                            <?php if ($user_type == "admin") {
                                ?>
                                <tr>
                                    <td class="grid_heading">S.No</td>
                                    <td class="grid_heading">User Name</td>
                                    <td class="grid_heading">Property Name</td>
                                    <td class="grid_heading">Order Status</td>                                
                                    <td class="grid_heading">Order Date</td>
                                    <td class="grid_heading">Details</td>
                                </tr>
                                <?php
                                $i = 0;
                                $sql = "SELECT r.name,p.name,o.id, o.order_status,o.purchase_date FROM property p, purchase o,register r WHERE p.property_id = o.property_id AND r.id = o.userid GROUP BY p.property_id ORDER BY p.property_id DESC";
                                $result = mysql_query($sql);
                                if (mysql_num_rows($result) > 0) {
                                    while ($row = mysql_fetch_array($result)) {
                                        $i++;
                                        ?>
                                        <tr>
                                            <td class="grid_label" align="center"><?php echo $i; ?></td>
                                            <td class="grid_label"><?php echo ucfirst($row['fname']) . ' ' . ucfirst($row['lname']); ?></td>
                                            <td class="grid_label"><?php echo ucfirst($row['name']); ?></td>
                                            <td class="grid_label"><?php echo $row['order_status'] ?></td>
                                            <td class="grid_label"><?php echo $row['purchase_date'] ?></td>
                                            <td class="grid_label"><a href="order_details.php?oid=<?php echo $row['id'] ?>">View</a></td>      

                                        </tr>
                                        <?php
                                    }
                                }
                                ?>    

                            <?php } else {
                                ?>
                                <tr>
                                    <td class="grid_heading">S.No</td>
                                    <td class="grid_heading">User Name</td>
                                    <td class="grid_heading">Property Name</td>
                                    <td class="grid_heading">Order Status</td>                                
                                    <td class="grid_heading">Order Date</td>
                                    <td class="grid_heading">Details</td>
                                </tr>
                                <?php
                                $i = 0;
                                $sql = "SELECT r.fname,r.lname,p.name, o.id,o.order_status,o.purchase_date FROM property p, purchase o,register r WHERE p.property_id = o.property_id AND r.id = o.userid AND p.userid = '$user_id' GROUP BY p.property_id ORDER BY p.property_id DESC";
                                $result = mysql_query($sql);
                                if (mysql_num_rows($result) > 0) {
                                    while ($row = mysql_fetch_array($result)) {
                                        $i++;
                                        ?>
                                        <tr>
                                            <td class="grid_label" align="center"><?php echo $i; ?></td>
                                            <td class="grid_label"><?php echo ucfirst($row['fname']) . ' ' . ucfirst($row['lname']); ?></td>
                                            <td class="grid_label"><?php echo ucfirst($row['name']); ?></td>
                                            <td class="grid_label"><?php echo $row['order_status'] ?></td>
                                            <td class="grid_label"><?php echo $row['purchase_date'] ?></td>
                                            <td class="grid_label"><a href="order_details.php?oid=<?php echo $row['id'] ?>">View</a></td>      

                                        </tr>
                                        <?php
                                    }
                                }
                                ?> 
                                <?php
                            }
                            ?>
                        </table>
                    </form>

                </div>
                <div class="clear"> </div>
            </div>                        
            <div class="clear"></div>
            <?php include './footer.php'; ?>
        </body>
    </html>
    <?php
} else {
    header("location:login.php?msg=login");
}
?>