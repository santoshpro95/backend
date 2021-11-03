<?php
include './dbconfigur.php';
$error = "";
if (isset($_POST['btnlogin'])) {

    extract($_POST);

    if (empty($txtemail)) {
        $error .= "Please enter your email.<br/>";
    }
    if (empty($txtpassword)) {
        $error .= "Please enter your password.<br/>";
    }
    if (empty($error)) {
        $query = "Select id,fname,lname,email,file_upload,user_type from register where email = '$txtemail' AND password = '$txtpassword'";
        $result = mysql_query($query);
        if (mysql_num_rows($result) > 0) {

            $row = mysql_fetch_array($result);
            $_SESSION['user_id'] = $row['id'];
            $_SESSION['user_name'] = $row['fname'];
             $_SESSION['user_image'] = $row['file_upload'];
            $_SESSION['user_type'] = $row['user_type'];

            if (!empty($hidpid)) {
                header('location:property_detail.php?pid=' . $hidpid);
            } else {
                header('location:myaccount.php');
            }
        } else {
            $error = "Email and password are wrong. Please Try Again.";
        }
    }
}
?> 
<!DOCTYPE HTML>
<html>
    <head>
        <title>Login - Easy Cab-Login</title>
        <?php include './title.php'; ?>
    
    </head>
    <body>
        <!--  start-wrap -->

        <?php include './header.php'; ?>
        <!--  end-header -->
        <?php include './rightbar.php'; ?>
        <div style="float:left;" class="gallery">
            <h4>Login</h4>
            <form method="post" action="login.php" >                
                <?php
                //properties
                if (!empty($error)) {
                    echo '<div class="indv_fields ind"><label class="error" style="color:red">' . $error . '</label></div>';
                }
                if (isset($_GET['msg']) && $_GET['msg'] == "login") {
                    echo '<div class="indv_fields red" ><label>You must first login.</label></div>';
                } elseif (isset($_GET['msg']) && $_GET['msg'] == "login") {
                    echo '<div class="indv_fields red" ><label>you are successfully logout.</label></div>';
                } elseif (isset($_GET['msg']) && $_GET['msg'] == "properties") {
                    $properties_id = mysql_real_escape_string($_GET['pid']);
                    ?>
                    <input type="hidden" name="hidpid" id="hidpid" value="<?php echo $properties_id; ?>" /> 
                    <?php
                }
                ?>
                <div class="clear"></div>

                <fieldset class="indv" style="width: 95%" >	
                    <div class="indv_fields ind">
                        <label for="email">Email<span class="red">*</span></label> 
                        <input type="email" name="txtemail" id="email" value=""  placeholder="Your email"/> 
                    </div>
                    <div class="indv_fields ind">
                        <label for="password">Password<span class="red">*</span></label> 
                        <input type="password" name="txtpassword" id="password" value="" placeholder="Your password" /> 
                    </div>
                    <label for="submit"></label> 
                    <input type="submit" name="btnlogin" class="submit" id="btnlogin" value="Submit"/>

                </fieldset><!--end user-details-->

            </form>
            <div class="clear"> </div>
        </div>
        <?php include './footer.php'; ?>
        <div class="clear"> </div>
    </div>
</body>
</html>
