<?php
include './dbconfigur.php';
$error = "";
if (!empty($user_id)) {

    $fareid = mysql_real_escape_string($_GET['id']);
    if (isset($_POST['btnsubmit'])) {

        extract($_POST);
//        if (empty($job_title)) {
//            $error .= "Please enter your job title.<br/>";
//        }
//        if (empty($txtcomname)) {
//            $error .= "Please enter your profession.<br/>";
//        }
//        if (empty($txtannulaincome)) {
//            $error .= "Please enter your annual income.<br/>";
//        }
//        if (empty($txtpanno)) {
//            $error .= "Please enter pan no.<br/>";
//        }
//        if (empty($addr)) {
//            $error .= "Please enter addresss.<br/>";
//        }


        if (empty($error)) {
           echo $sql_query = "INSERT INTO book(userid,fare_id,froms,too,total_km,total_fare,booking_date)"
                    . "VALUES('" . $user_id . "','" . $fareid . "','" . $txtfrom . "','" . $txtto . "','" . $txttotalkm . "','" . $txttotalfare . "','" . date('Y-m-d h:i:s') . "')";
            $result = mysql_query($sql_query);
            $order_id = mysql_insert_id();
            if ($result) {
                header("location:payment.php?order_id=" . $order_id."&fid=".$fareid);
            } else {
                $error = "Data has not been saved.";
            }
        }
    }
    ?>
    <html>
        <head>
            <title>Properties Order - Multi Real Estate Business Corporation</title>
            <?php include './title.php'; ?>
        </head>
        <body>            
            <?php include './header.php'; ?>            
            <div class="gallery ind">
                <div class="about">
                    <h4>Properties Order</h4>
                    <form name="registration" action="" method="post" enctype="multipart/form-data"> 
                        <?php
                        if (!empty($error)) {
                            echo '<div class="indv_fields"><label class="error">' . $error . '</label></div>';
                        }
                        if (isset($_GET['reg']) && $_GET['reg'] == "success") {
                            echo '<div class="indv_fields"><label class="success">You are add a property.</label></div>';
                        }
                        ?>
                        
                         <?php
                        $sqlquery = "select * from register where id = '$user_id'";
                        $result = mysql_query($sqlquery);
                        if (mysql_num_rows($result) > 0) {
                            $row = mysql_fetch_array($result);
                            ?>    
          
                        <div class="clear"></div>
                        <fieldset class="indv">	                           
                            <div class="indv_fields ">
                                <!--<label for="propertytype">Profession<span class="red">*</span></label>-->
                                <input type="hidden" name="txtuserid" id="txtuserid" value="<?php echo $row['id']; ?>" placeholder=""  />                                 
                            </div>
                             <div class="indv_fields ">
                                <label for="name"> Job Title<span class="red">*</span></label>
                                <input type="text" name="txtname" id="name" value="<?php echo $row['name']; ?>"  /> 
                                
                            </div>
                        <?php } ?>
                             <?php
                        $sqlquery = "select * from fare ";
                        $result = mysql_query($sqlquery);
                        if (mysql_num_rows($result) > 0) {
                            $row = mysql_fetch_array($result);
                            ?>
                            <div class="indv_fields ">
                                <label for="income">From<span class="red">*</span></label> 
                                <input type="text" name="txtfrom" id="annulainocme" value="<?php echo $row['from']; ?>"  placeholder=""/> 
                            </div>
                            <div class="indv_fields "> 
                                <label for="panno">TO<span class="red">*</span></label> 
                                <input type="text" name="txtpanno" id="panno" value="<?php echo $row['too']; ?>"/>
                            </div>
                            <div class="indv_fields">
                                <label for="addr">Total Fare<span class="red">*</span></label> 
                                <input type="txttotalfare" name="txtfare" id="fare" /> 
                            </div>
                             <!--<div class="clear"> </div>-->
                            <div class="indv_fields ind"> 
                                <input type="submit" name="btnsubmit" id="btnsubmit" value="Submit"/>
                            </div>
                              <?php } ?>
                        </fieldset>
                    </form>
                </div>
                <div class="clear"></div>
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