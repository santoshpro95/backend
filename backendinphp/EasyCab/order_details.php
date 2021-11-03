<?php
include './dbconfigur.php';
$lid = "";
if (!empty($user_id)) {//loan_id
    if (isset($_GET['oid'])) {

        $oid = mysql_real_escape_string($_GET['oid']);
    }

    if (isset($_GET['action']) && isset($_GET['oid'])) {
        
        $order_id = mysql_real_escape_string($_GET['oid']);
        $action = mysql_real_escape_string($_GET['action']);
        if ($action == "approve") {        
            mysql_query("UPDATE purchase SET payment_status = 'Done', order_status = 'Approve' WHERE id = '$order_id'");
            header("location:order_status.php");
        }

        if ($action == "reject") {            
            mysql_query("UPDATE purchase SET payment_status = 'Reject', order_status = 'Reject' WHERE id = '$order_id'");
            header("location:order_status.php");
        }
    }
    ?>
    <html>
        <head>
            <title>Order Details -  Multi Real Estate Business Corporation-My Account</title>
            <?php include './title.php'; ?>
        </head>
        <body>
            <!--  start-wrap -->
            <?php include './header.php'; ?>
            <!--  end-header -->
            <?php include './leftmenu.php'; ?>
            <div class="gallery">
                <div class="about">
                    <h4>Order Details</h4>
                    <form>
                        <?php
                        $sqlquery = "SELECT r.*,p.*,o.id as order_id, o.* FROM property p, purchase o,register r WHERE p.property_id = o.property_id AND r.id = o.userid  AND o.id = '" . $oid . "' GROUP BY p.property_id ORDER BY p.property_id DESC";
                        $result = mysql_query($sqlquery);
                        if (mysql_num_rows($result) > 0) {
                            $row = mysql_fetch_array($result);
                            ?>   
                            <fieldset class="indv">                                
                                <div class="indv_fields">
                                    <label for="name" class="properties_data1">First Name</label>
                                    <label for="name" class="properties_data1"><?php echo ucfirst($row['fname']) ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label for="name" class="properties_data1">Last Name</label>
                                    <label for="name" class="properties_data1"><?php echo ucfirst($row['lname']) ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label for="email" class="properties_data1">Email</label> 
                                    <label for="name" class="properties_data1"><?php echo $row['email']; ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data1">Phone</label> 
                                    <label class="properties_data1"><?php echo $row['contact']; ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data1">Properties Name</label> 
                                    <label class="properties_data1"><?php echo $row['name']; ?></label>                             
                                </div>

                                <div class="indv_fields">
                                    <label class="properties_data1" style="width: 110px;">Properties&nbsp;Type</label> 
                                    <label class="properties_data1"><?php echo $row['property_type']; ?></label> 
                                </div>

                                <div class="indv_fields">
                                    <label class="properties_data1">Land&nbsp;Area</label> 
                                    <label class="properties_data1"><?php echo $row['land_area']; ?></label>                             
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data1">Price</label> 
                                    <label class="properties_data"><?php echo $row['prize']; ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data1">Location</label> 
                                    <label class="properties_data1 ind"><?php echo $row['addr']; ?></label>                             
                                </div>
                                <div class="indv_fields ind">
                                    <label class="properties_data1">Description</label> 
                                    <label class="properties_data1 ind"><?php echo $row['discription']; ?></label>                             
                                </div>
                                
                                <div class="indv_fields">
                                    <label class="properties_data1">Payment Status</label> 
                                    <label class="properties_data"><?php echo $row['payment_status']; ?></label>                             
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data1">Order Date</label> 
                                    <label class="properties_data1"><?php echo $row['purchase_date']; ?></label>                             
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data1">ID Proof</label> 
                                    <label class="properties_data1"><a href="<?php echo $row['id_prof']; ?>" target="_blank">Download</a></label>                             
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data1">Address Proof</label> 
                                    <label class="properties_data1"><a href="<?php echo $row['add_prof']; ?>" target="_blank">Download</a></label>                             
                                </div>
                                <div class="indv_fields ind">
                                    <div class="price" style="float: left;width: 80px;padding: 10px 0px 10px 0px">
                                        <a href="order_details.php?action=reject&oid=<?php echo $row['order_id']; ?>">Reject</a>
                                    </div>
                                    <div class="price" style="float: left;width: 80px;padding: 10px 0px 10px 0px">
                                        <a href="order_details.php?action=approve&oid=<?php echo $row['order_id']; ?>">Approve</a>
                                    </div>
                                </div>
                            </fieldset>
                        <?php } ?>
                    </form>
                </div>
                <div class="clear"> </div>
            </div>
            <?php include 'footer.php'; ?>
        </body>
    </html>
    <?php
} else {
    header("location:login.php?msg=login");
}
?>
