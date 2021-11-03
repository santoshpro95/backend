<?php
include './dbconfigur.php';
$fareid = $_GET['id'];
if (!empty($user_id)) {
    $error = "";
    if (isset($_POST['btnupdate'])) {
         extract($_POST);
        //if (isset($_GET['id']) && !empty($_GET['id'])) {
//        $fareid = mysql_real_escape_string($_GET['id']);
     echo   $query = "update fare set froms='" . $txtfrom . "', too='$txtto',  total_km='$txttkm', prize='$txtprize', first_km='1', rs='$txtrs' where id = '$txtid' ";
        //}
        $r = mysql_query($query);
        $num = (int) $r;
        if ($num > 0) {
            header("location:fare_list.php");
            $_SESSION['MSG'] = "Fare has been successfully updated.!!";
        } else {
            $_SESSION['MSG'] = "Fare has not been updated.!!";
        }

    }
    ?>

    <html>
        <head>
            <title>Edit Easy Cab</title>
            <?php include './title.php'; ?>
        </head>
        <body>
            <!--  start-wrap -->
            <?php include './header.php'; ?>
            <!--  end-header -->

            <?php include './leftmenu.php'; ?>
            <div class="gallery">
                <div class="about">
                    <?php
                    if ($_SESSION['MSG'] != '') {
                        echo '<tr><td colspan="4" valign="middle" align="center"><font color="red" style="font-size:13px;">' . $_SESSION['MSG'] . ' </font></td></tr>';
                        $_SESSION['MSG'] = "";
                    }
                    ?>

                    <h4>Edit-Fare</h4>
                    <form  action=" " method="post">                     
                        <?php
                        if (!empty($error)) {
                            echo '<div class="indv_fields " style="color:red">' . $error . '</div>';
                        }
                        if (isset($_GET['id']) && !empty($_GET['id'])) {
                            $fareid = mysql_real_escape_string($_GET['id']);
                            $sqlquery = "select * from fare where id = '$fareid'";
                            $result = mysql_query($sqlquery);
                            while ($row = mysql_fetch_array($result)) {
                                ?>    
                                <div class="clear"></div>
                                <fieldset class="indv">	
                                    <div class="indv_fields">
                                        <label for="name">From</label>
                                        <input type="hidden" name="txtid" id="id" value="<?php echo $row['id']; ?>" /> 
                                        <input type="text" name="txtfrom" id="from" value="<?php echo $row['froms']; ?>"  /> 
                                    </div>

                                    <div class="indv_fields ">
                                        <label for="landarea">To</label> 
                                        <input type="text" name="txtto" id="to" value="<?php echo $row['too']; ?>"  placeholder=""/> 
                                    </div>
                                    <div class="indv_fields ">
                                        <label for="buildingsize">Total KM</label> 
                                        <input type="text" name="txttkm" id="txttkm" value="<?php echo $row['total_km']; ?>"/>
                                    </div>
                                    <div class="indv_fields ">
                                        <label for="prize">Total Fare </label> 
                                        <input type="text" name="txtprize" id="prize" value="<?php echo $row['prize']; ?>" placeholder=""/> 
                                    </div>
                                    <div class="indv_fields">
                                        <label for="builtyear">First KM</label> 
                                        <input type="text" name="km" id="tkm" readonly="" value="<?php echo $row['first_km']; ?>"  placeholder=""/> 
                                    </div>
                                    <div class="indv_fields">
                                        <label for="builtyear">Rs.</label> 
                                        <input type="text" name="txtrs" id="tkm"  value="<?php echo $row['rs']; ?>"  placeholder=""/> 
                                    </div>


                                    <!--<div class="clear"> </div>-->
                                    <div class="indv_fields"> 
                                        <label for="submit"></label> 
                                        <input type="submit" name="btnupdate" id="submit" value="Update" onclick="return checkform();"/>
                                    </div>
                                </fieldset><!--end user-details-->

                                <?php
                            }
                        }
                        ?>


                    </form>

                </div>
                <div class="clear"> </div>
            </div>
            <?php include 'footer.php'; ?>
        </div>
    </body>
    </html>
    <?php
} else {
    header("location:login.php?msg=login");
}
?>