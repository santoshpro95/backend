<?php

ob_start();
session_start();
unset($_SESSION['user_id']);
unset($_SESSION['user_name']);
unset($_SESSION['user_image']);
unset($_SESSION['user_type']);
session_destroy();
header("location:login.php?logout=login");
ob_flush();
?>


   <?php
//                        } elseif ($user_id == ("buyer" || "seller")) {
//                            ?>

                           <!--<li><a href="applyloan.php">Apply Loan</a></li>-->
                            <!--<li><a href="loanstatus.php"> Loan Status</a></li>-->
                            //<?php
//                        } else{
//                        <li><a href = "changepassword.php">Change Password</a></li>
//                        }
                        ?>

