<?php
include './dbconfigur.php';
$error = "";
if (!empty($user_id)) {

    $order_id = mysql_real_escape_string($_GET['order_id']);
    $pid = mysql_real_escape_string($_GET['pid']);
    if (isset($_POST['btnsubmit'])) {

        extract($_POST);
        if (empty($rdoregister)) {
            $error .= "Please select payment option.<br/>";
        }
        if (empty($txtcadno)) {
            $error .= "Please enter card no.<br/>";
        }
        if (empty($txtexpdate)) {
            $error .= "Please enter expiry date<br/>";
        }
        if (empty($txtcvv)) {
            $error .= "Please enter your cvv.<br/>";
        }

        if (empty($bank_name)) {
            $error .= "Please select card type.<br/>";
        }
        if (empty($error)) {

            echo $sql_query = "INSERT INTO payment(userid,propertyid,card_type,cardno,expiry_date,cvv,card_name,payment_date)"
            . "VALUES('" . $user_id . "','" . $order_id . "','" . $rdoregister . "','" . $txtcadno . "','" . $txtexpdate . "','" . $txtcvv . "','" . $bank_name . "','" . date('Y-m-d h:i:s') . "')";
            $result = mysql_query($sql_query);
            if ($result) {

                mysql_query("UPDATE purchase SET payment_status = 'Done' WHERE id = '$order_id'");
                mysql_query("UPDATE property SET `status` = '2' WHERE property_id = '$pid'");

                header("location:thankyou.php");
            } else {
                $error = "Data has not been saved.";
            }
        }
    }
    ?>
    <html>
        <head>
            <title>Payment - Easy Cab</title>
            <?php include './title.php'; ?>
            <script src="js/scw.js" type="text/javascript"></script>
        </head>
        <body>            
            <?php include './header.php'; ?>
            <div class="gallery ind">
                <h4>Payment</h4>
                <form name="registration" action="" method="post">
                    <div  class="grid deto ind">

                         
                        <?php
                        if (!empty($error)) {
                            echo '<div class="indv_fields ind" style="color:red;font-size-10px;padding:5px;">' . $error . '</div>';
                        }
                        ?>
                        <div class="clear"></div>
                        <div class="indv_radio" style="padding-top: 15px;padding-left: 8px;padding-bottom: 0px;margin-bottom: 0px;font-size: 14px;">
                            <label for="">Payment Option<span class="red">*</span></label>
                            <input type="radio" name="rdoregister" id="rdoregister" value="debit" style="width: 18px;"/>Debit Card                            
                            <input type="radio" name="rdoregister" id="rdoregister" value="credit" style="width: 18px;"/>Credit Card
                            <input type="hidden" name="hidorderid" id="hidorderid" value="<?php echo $order_id; ?>" />
                            <input type="hidden" name="pid" id="pid" value="<?php echo $pid; ?>"/> 
                        </div>
                        <div class="clear"></div>
                        <div class="indv_fields ">
                            <label for="">Card Number<span class="red">*</span></label> 
                            <input type="text" name="txtcadno" id="cadno" value="" placeholder="Please enter your card no" maxlength="16"/>
                        </div>
                        <div class="indv_fields ">
                            <label for="accno">Expiry Date<span class="red">*</span> </label> 
                            <input type="text" name="txtexpdate" id="expdate" value="" placeholder="Please enter your expiry date" onclick="scwShow(this, event)" readonly=""/> 
                        </div>
                        <div class="indv_fields ">
                            <label for="payment">Cvv<span class="red">*</span> </label>
                            <input type="password" name="txtcvv" id="cvv" value="" placeholder="Please enter your cvv" maxlength="3"/> 
                        </div>
                        <div class="indv_fields ">
                            <label for="">Card Type</label> 
                            <select name="bank_name" id="bank_name" style="width: 100%">
                                <option selected=""> - - - - - Select - - - - - </option>
                                <option>Master card</option>
                                <option>Visa card</option>
                                <option>Mestro Card</option>
                                <option>Rupay Card</option>
                            </select> 
                        </div>
                        <div class="indv_fields" >                            
                            <input type="submit" name="btnsubmit" id="submit" value="Payment"/>
                        </div>
                    </div>
                </form>
            </div>
            <!--</div>-->
            <div class="clear"> </div>
            <div class="clear"></div>
            <?php include './footer.php'; ?>
        </body>
    </html>
    <?php
} else {
    header("location:login.php?msg=login");
}
?>