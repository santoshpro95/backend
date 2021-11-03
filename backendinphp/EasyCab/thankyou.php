<?php
include './dbconfigur.php';
if (!empty($user_id)) {
    ?>
    <html>
        <head>
            <title>Thank you - Easy Cab</title>
            <?php include './title.php'; ?>
        </head>
        <body>            
            <?php include './header.php'; ?>
            <div class="gallery ind" style="min-height: 400px;">
                <h4>Thank you</h4>
                <div  class="grid deto ind"> 
                    <div class="indv_fields ind">
                        <label>Payment Successful.</label>
                        <br><label>Book your cab.</label>
                    </div> 
                </div>
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