<?php
include './dbconfigur.php';
$error = "";
if (!empty($user_id)) {

    if (isset($_POST['btnchangepwd'])) {
        extract($_POST);

        if (empty($oldpwd)) {
            $error = "Please enter old password.";
        }
        if (empty($newpwd)) {
            $error = "Please enter new password.";
        }
        if (empty($confirm)) {
            $error = "Please enter confirm password.";
        }

        if ($newpwd != $confirm) {
            $error = "New password  does not matched with confirm password.";
        }

        if (empty($error)) {
            echo $query = "select * from register where id='" . $user_id . "' AND password = '" . $oldpwd . "' ";
            $result = mysql_query($query);
            if (mysql_num_rows($result) > 0) {

                $updatepwd = "update register  set password='" . $newpwd . "' WHERE  id='" . $user_id . "' and password='" . $oldpwd . "'";
                $result_pwd = mysql_query($updatepwd);
                if ($result_pwd) {
                    header("location:changepassword.php?status=success");
                } else {
                    $error = "Password has not been changed.";
                }
            } else {
                $error = "Current password is wrong. Please enter correct current password.";
            }
        }
    }
    ?>
    <html>
        <head>
            <title>Easy Cab-Change Password</title>
            <?php include './title.php'; ?>
        </head>
        <body>
            <!--  start-wrap -->

            <?php include './header.php'; ?>
            <!--  end-header -->

            <?php include './leftmenu.php'; ?>
            <div class="gallery">
                <div class="about">
                    <h4>Change Password</h4>
                    <form name="registration" action="" method="post">
                        <fieldset class="indv" style="width: 95%;">	
                            <?php
                            if (!empty($error)) {
                                echo '<div class="indv_fields ind"><label class="error">' . $error . '</label></div>';
                            }
                            if (isset($_GET['status']) && $_GET['status'] == "success") {
                                echo '<div class="indv_fields ind red"><label>Your password has been changed.</label></div>';
                            }
                            ?>
                            <div class="indv_fields">
                                <label for="password">Old Password:</label> 
                                <input type="password" name="oldpwd" id="oldpwd" value="" placeholder="Old Password" /> 
                            </div>
                            <div class="clear"></div>
                            <div class="indv_fields ">
                                <label for="password">New Password:</label> 
                                <input type="password" name="newpwd" id="newpwd" value="" placeholder="New Password" /> 
                            </div>
                            <div class="clear"></div>
                            <div class="indv_fields ">
                                <label for="password">Confirm Password:</label> 
                                <input type="password" name="confirm" id="confirm" value="" placeholder="Confirm Password" /> 
                            </div>
                            <div class="clear"></div>
                            <div class="indv_fields">
                                <label for="submit"></label> 
                                <input type="submit" name="btnchangepwd" id="submit" value="Submit"/>
                            </div>
                        </fieldset>
                    </form>

                </div>
                <div class="clear"> </div>
            </div>
            <?php include './footer.php'; ?>
        </div>
    </body>
    </html>
    <?php
} else {
    header("location:login.php?msg=login");
}
