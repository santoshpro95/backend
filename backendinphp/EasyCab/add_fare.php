<?php
include './dbconfigur.php';
//include './encryption.php';
$error = "";
if (!empty($user_id)) {
    error_reporting(E_ALL);
    if (isset($_POST['btnaddfare'])) {
        extract($_POST);
        if (empty($txtfrom)) {
            $error .= "Please enter Starting To .<br/>";
        }
        if (empty($txtto)) {
            $error .= "Please enter where are you going.<br/>";
        }
        if (empty($txtkm)) {
            $error .= "Please enter Total Distance <br/>";
        }
        if (empty($txtprize)) {
            $error .= "Total Fare cost <br/>";
        }
        if (empty($txtfkm)) {
            $error .= "Please enter First KM Fare <br/>";
        }
        if (empty($rs)) {
            $error .= "Please enter 1 Km Fare Rs. <br/>";
        }


            echo $sql_query = "INSERT INTO fare(froms,too,total_km,prize,first_km,rs,adding_date) VALUES
            ('" . $txtfrom . "','" . $txtto . "','" . $txtkm . "','" . $txtprize . "','1','" . $rs ."','" . date('Y-m-d h:i:s') . "')";

            $result = mysql_query($sql_query);
            if (mysql_insert_id() > 0) {
                header("location:add_fare.php?reg=success");
            } else {
                $error = "Data has not been saved.";
            }
        }
    
    ?>
    <html>
        <head>
            <title>Add Property - Multi Real Estate Business Corporation</title>
            <?php include './title.php'; ?>
        </head>
        <body>
            <!--  start-wrap -->
            <?php include './header.php'; ?>
            <!--  end-header -->
            <?php include './leftmenu.php'; ?>
            <div class="gallery">
                <div class="about">
                    <h4>Add Fare </h4>
                    <form name="registration" action="" method="post" enctype="multipart/form-data"> 
                        <fieldset class="indv">	
                            <?php
                            if (!empty($error)) {
                                echo '<div class="indv_fields"><label class="error">' . $error . '</label></div>';
                            }
                            if (isset($_GET['reg']) && $_GET['reg'] == "success") {
                                echo '<div class="indv_fields"><label class="success">Your has been save successfully.</label></div>';
                            }
                            ?>
                            <div class="clear"></div>

                            <div class="indv_fields">
                                <label for="name">From<span>*</span></label>
                                <input type="text" name="txtfrom" id="from" value=""  /> 
                            </div>
                            
                            <div class="indv_fields ">
                                <label for="landarea">To<span>*</span></label> 
                                <input type="text" name="txtto" id="to" value=""  placeholder=""/> 
                            </div>
                            <div class="indv_fields ">
                                <label for="buildingsize">KM<span>*</span></label> 
                                <input type="text" name="txtkm" id="km" value=""/>
                            </div>
                            <div class="indv_fields ">
                                <label for="prize">Cost<span>*</span></label> 
                                <input type="text" name="txtprize" id="prize" value="" placeholder=""/> 
                            </div>
                            <div class="indv_fields">
                                <label for="builtyear">First 1 KM<span>*</span></label> 
                                <input type="text" name="txbfkm" id="fkm" value=""  placeholder=""/> 
                            </div>
                            <div class="indv_fields ">
                                <label for="addr">Rs.<span>*</span></label> 
                                <input type="text" name="rs" id="rs" /> 
                            </div>
                            <div class="indv_fields indv">
                                <input type="submit" name="btnaddfare" id="btnaddfare" Value="Add Fare" /> 
                            </div>
                      
                        </fieldset>
                    </form>
                </div>
                <div class="clear"></div>
            </div>
            <?php include './footer.php'; ?>
        </body>
    </html>
    <?php
} else {
    header("location:login.php?msg=login");
}
?>