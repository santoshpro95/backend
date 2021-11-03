<?php
include './dbconfigur.php';
$error = "";
if (!empty($user_id)) {

    if (isset($_POST['btnupdate'])) {

        extract($_POST);

        $file = $_FILES["file"]["name"];
        if (empty($file)) {
            $error .= "Please profile pic.<br/>";
        }

        $allowedExts = array("jpeg", "jpg", "png");
        if (!empty($_FILES["file"]["name"])) {
            $temp = explode(".", $_FILES["file"]["name"]);
            $extension = end($temp);
            if (!in_array($extension, $allowedExts)) {
                $error .= "Extension not allowed.<br/>";
            }
            if (empty($error)) {
                move_uploaded_file($_FILES["file"]["tmp_name"], "file_upload/" . $_FILES["file"]["name"]);
                $fileupload = "file_upload/" . $_FILES["file"]["name"];
            }
        }

        if (empty($error)) {

            $query = "update register set dob='" . $txtdob . "', address='" . $txtaddress . "', pin='" . $txtpin . "',state='" . $txtstate . "', country='$txtcountry',  file_upload='" . $fileupload . "' where id = '$user_id' ";
            $result = mysql_query($query);
            $num = (int) $result;
            if ($num > 0) {
                $error = "Your profile has been successfully updated.";
            } else {
                $error = "Your profile has not been updated.";
            }
        }
    }
    ?>
    <html>
        <head>
            <title>My Account - Easy Cab-My Account</title>
            <?php include './title.php'; ?>
            <script type="text/javascript" src="js/scw.js"></script>
        </head>
        <body>
            <?php include './header.php'; ?>
            <?php include './leftmenu.php'; ?>

            <div class="gallery">
                <div class="about">
                    <h4>My Account</h4>
                    <form name="registration" action="myaccount.php" method="post" enctype="multipart/form-data"> 
                        <?php
                        $sqlquery = "select * from register where id = '$user_id'";
                        $result = mysql_query($sqlquery);
                        if (mysql_num_rows($result) > 0) {
                            $row = mysql_fetch_array($result);
                            ?>    
                            <div class="clear"></div>
                            <fieldset class="indv">	
                                <?php
                                if (!empty($error)) {
                                    echo '<div class="indv_fields ind" style="color:red;font-size-11px;padding:5px;">' . $error . '</div>';
                                }
                                ?>
                                <div class="indv_fields ">
                                    <label for="name">First Name</label>
                                    <input type="text" name="txtfirstname" id="firstname" value="<?php echo $row['fname']; ?>"  /> 
                                </div>
                                <div class="indv_fields ">
                                    <label for="name">Last Name</label>
                                    <input type="text" name="txtlastname" id="lastname" value="<?php echo $row['lname']; ?>" placeholder="Please enter your last name"  /> 
                                </div>

                                <div class="indv_fields ">
                                    <label for="email">Email</label> 
                                    <input type="email" name="txtemail" id="email" value="<?php echo $row['email']; ?>"  placeholder="Your email"/> 
                                </div>
                                <div class="indv_fields ">
                                    <label for="phone">Phone</label> 
                                    <input type="text" name="txtmobile" id="mobile" value="<?php echo $row['contact']; ?>"  />
                                </div>
                                <div class="indv_fields ">
                                    <label for="dob">Date of Birth</label> 
                                    <input type="text" name="txtdob" id="dob" value="<?php echo $row['dob']; ?>" placeholder="Your Date of birth" onclick="scwShow(this, event)" readonly=""/> 
                                </div>

                                <div class="indv_fields">
                                    <label for="address">Address</label> 
                                    <input type="text" name="txtaddress" id="address" value="<?php echo $row['address']; ?>"  placeholder="Your address"/> 
                                </div>

                                <div class="indv_fields">
                                    <label for="pin">Pin</label> 
                                    <input type="text" name="txtpin" id="pin" value="<?php echo $row['pin']; ?>"  placeholder="Your City Pin"/>                            
                                </div>
                                <div class="indv_fields">
                                    <label for="state">State</label> 
                                    <input type="text" name="txtstate" id="state" value="<?php echo $row['state']; ?>"  placeholder="Your state"/> 
                                </div>
                                <div class="indv_fields">
                                    <label for="country">Country</label> 
                                    <input type="text" name="txtcountry" id="country" value="<?php echo $row['country']; ?>"  placeholder="Your Country"/>                            
                                </div>
                                <div class="indv_fields">
                                    <label for="fileupload">Profile Pic</label> 
                                    <input type="file" name="file" id="file" value=""  placeholder="Choose Your file"/> 

                                </div>
                                <div>&nbsp;</div>

                                <div class="indv_fields">

                                </div>

                                <div class="indv_fields"> 
                                    <label for="submit"></label> 
                                    <input type="submit" name="btnupdate" id="submit" value="Update"/>
                                </div>

                            </fieldset><!--end user-details-->
                        <?php } ?>
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
?>