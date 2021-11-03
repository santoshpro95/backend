<?php
include './dbconfigur.php';
if (!empty($user_id)) {
    $id = mysql_real_escape_string($_GET['id']);
    ?>
    <html>
        <head>
            <title>User Details -  Easy Cab-My Account</title>
            <?php include './title.php'; ?>
        </head>
        <body>
            <!--  start-wrap -->
            <?php include './header.php'; ?>
            <!--  end-header -->
            <?php include './leftmenu.php'; ?>
            <div class="gallery">
                <div class="about">
                    <form>
                        <?php
                        $sqlquery = "select * from register where id = '$id'";
                        $result = mysql_query($sqlquery);
                        if (mysql_num_rows($result) > 0) {
                            $row = mysql_fetch_array($result);
                            ?>                        

                            <fieldset class="indv">	
                                <h4>User Details</h4>

                                <?php
                                if ($row['user_type'] == "employee") {
                                    ?>
                                    <div class="indv_fields">
                                        <label for="name" class="properties_data">Employee ID</label>
                                        <label for="name" class="properties_data1"><?php echo $row['emp_id'] ?></label>                            
                                    </div>
                                    <div class="indv_fields">
                                        <label for="name" class="properties_data">Bank Name</label>
                                        <label for="name" class="properties_data1"><?php echo ucfirst($row['bank_name']) ?></label>                            
                                    </div>
                                <?php }
                                ?>

                                <div class="indv_fields">
                                    <label for="name" class="properties_data">User Type</label>
                                    <label for="name" class="properties_data1"><?php echo ucfirst($row['user_type']) ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label for="name" class="properties_data">First Name</label>
                                    <label for="name" class="properties_data1"><?php echo ucfirst($row['fname']) ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label for="name" class="properties_data">Last Name</label>
                                    <label for="name" class="properties_data1"><?php echo ucfirst($row['lname']) ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label for="email" class="properties_data">Email</label> 
                                    <label for="name" class="properties_data1"><?php echo $row['email']; ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data">Phone</label> 
                                    <label class="properties_data1"><?php echo $row['contact']; ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data">Date of Birth</label> 
                                    <label class="properties_data1"><?php echo $row['dob']; ?></label>                             
                                </div>

                                <div class="indv_fields">
                                    <label class="properties_data">Address</label> 
                                    <label class="properties_data1"><?php echo $row['txtaddress']; ?></label> 
                                </div>

                                <div class="indv_fields">
                                    <label class="properties_data">Pin</label> 
                                    <label class="properties_data1"><?php echo $row['pin']; ?></label>                             
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data">State</label> 
                                    <label class="properties_data"><?php echo $row['state']; ?></label>                            
                                </div>
                                <div class="indv_fields">
                                    <label class="properties_data">Country</label> 
                                    <label class="properties_data1"><?php echo $row['country']; ?></label>                             
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
