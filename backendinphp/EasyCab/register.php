<?php
include './dbconfigur.php';
$error = "";
if (isset($_POST['btnsubmit'])) {    
    
    extract($_POST);    

   
    if (empty($txtfirstname)) {
        $error .= "Please enter your first name.<br/>";
    }
    if (empty($txtlastname)) {
        $error .= "Please enter your last name.<br/>";
    }

    if (empty($txtemail)) {
        $error .= "Please enter your email.<br/>";
    }

    if (empty($txtmobile)) {
        $error .= "Please enter your mobile no.<br/>";
    }

    if (empty($txtpassword)) {
        $error .= "Please enter your password.<br/>";
    }

    if (empty($txtconfirm)) {
        $error .= "Please enter your confirm password.<br/>";
    }
    if (empty($error)) {

        $sql_query = "INSERT INTO register(fname,lname,email,contact,password,user_type,adding_date)" . "VALUES('" . $txtfirstname . "','" . $txtlastname . "','" . $txtemail . "','" . $txtmobile . "','" . $txtpassword . "','user','" . date('Y-m-d h:i:s') . "')";
        $result = mysql_query($sql_query);
        if ($result) {
            header("location:register.php?reg=success");
        } else {
            $error = "Data has not been saved.";
        }
    }
}
?>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Register - Easy Cab-Register</title>
        <?php include './title.php'; ?>
    </head>
    <body>
        <!--  start-wrap -->

        <?php include './header.php'; ?>
        <!--  end-header -->
        <?php include './rightbar.php'; ?>
        <div style="float:left;" class="gallery">
            <h4>Register</h4>
            <form name="registration" action="register.php" method="post" enctype="multipart/form-data"> 
                <?php
                if (!empty($error)) {
                    echo '<div class="indv_fields"><label class="error">' . $error . '</label></div>';
                }
                if (isset($_GET['reg']) && $_GET['reg'] == "success") {
                    echo '<div class="indv_fields"><label class="success">You have been successfully registered.</label></div>';
                }
                ?>
                <div class="clear"> </div>
                <fieldset class="indv" style="width: 95%;">	
                    
                    <div class="indv_fields ">
                        <label for="name">First Name<span class="red">*</span></label>
                        <input type="text" name="txtfirstname" id="firstname" value="" placeholder="Please enter your first name" maxlength="100"/> 
                    </div>
                    <div class="indv_fields ">
                        <label for="name">Last Name<span class="red">*</span></label>
                        <input type="text" name="txtlastname" id="lastname" value="" placeholder="Please enter your last name" maxlength="100" /> 
                    </div>

                    <div class="indv_fields ">
                        <label for="email">Email<span class="red">*</span></label> 
                        <input type="email" name="txtemail" id="email" value=""  placeholder="Your email" maxlength="100"/> 
                    </div>
                    <div class="indv_fields ">
                        <label for="phone">Phone<span class="red">*</span></label> 
                        <input type="text" name="txtmobile" id="mobile" maxlength="10" />
                    </div>
                    <div class="indv_fields ">
                        <label for="password">Password<span class="red">*</span></label> 
                        <input type="password" name="txtpassword" id="password" value="" placeholder="Your password" maxlength="25"/> 
                    </div>
                    <div class="indv_fields ">
                        <label for="confirm">Confirm Password<span class="red">*</span></label> 
                        <input type="password" name="txtconfirm" id="confirm" value=""  placeholder="Your confirm password" maxlength="25"/> 
                    </div>
                    <div class="indv_fields ">
                        &nbsp;
                    </div>
                    <div class="indv_fields ">

                    </div>
                    <div class="indv_fields" >

                        <label for="submit"></label> 
                        <input type="submit" name="btnsubmit" id="submit" value="Submit" onclick="return checkform();"/>
                    </div>
                </fieldset><!--end user-details-->

            </form>
            <div class="clear"> </div>
        </div>
        <?php include './footer.php'; ?>
        <div class="clear"> </div>
    </div>
</body>
</html>
