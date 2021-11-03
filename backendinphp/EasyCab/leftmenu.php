<div class="content">
     
    <div class="side-bar">
        <h4>&nbsp;</h4>
        <div class="side-bar-from">
            <div class="sidemenu">
     <?php
        if (!empty($user_image)) {
            echo '<img src="' . $user_image . '" width= "199" style="border: solid 1px #ccc;"/>';
        } else {
            echo '<img src="images/default.jpg" width="199" style="border: solid 1px #ccc;"/>';
        }
        if (!empty($user_name)) {
            echo '<p style="text-align:left;padding-top:10px;"><strong>' . ucfirst($user_name) . '</strong></p>';
        }
        ?>
                <div class="left_menu">
                    <ul>
                        <li><a href="myaccount.php">My Account</a></li>
                        <?php
                        if ($user_type == "admin") {
                            ?>
                            <li><a href="user_list.php">User List</a></li>
                            <li><a href="fare_list.php">View Fare</a></li>
                           <li><a href="add_fare.php">Add Fare</a></li>
                           <li><a href="view_book_customer.php">View Book Customer</a></li>
               
                            <!--<li><a href="order_status.php">Aprove Cab Booking </a></li>-->
                            
                           
                            <?php
                        } elseif ($user_type == "user") { ?>                                                       
                          <li><a href="booking.php">Book Cab</a></li>
                          <li><a href="booking.php">View Fare</a></li>
                            <!--<li><a href="order_status.php">order status</a></li>-->
                           
                        <?php } ?>
                        <li><a href="changepassword.php">Change Password</a></li>
                        <li><a href="logout.php.">Logout</a></li>
               
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>