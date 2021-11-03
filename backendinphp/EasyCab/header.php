<?php include './dbconfigur.php'; ?>
<div class="wrap">
    <!--  start-header -->
    <div class="header">
        <!--  start-logo -->
        <div class="logo">
            <h1 style="color: maroon; font-size: 25px ">Easy Cab</h1>
            <h2>&nbsp;</h2>
        </div>

        <!--  end-logo -->
        <!--  start-search-info -->
        <div class="search-form">
            <?php
            if (!empty($user_name)) {
                echo '<p style="text-align:right;padding-top:0px; font-size:14px; color:red;"><strong> Welcome |  ' . ucfirst($user_name) . '</strong></p>';
            }
            ?>
        </div>
        <div class="clear"></div>
        <div class="clear"></div>
        <div class="menu-main">
            <div class="menu">
                <ul>
                    <?php
                    $home = "";
                    $about = "";
                    $contact = "";
                    $properties = "";
                    $register = "";
                    $login = "";
                    $myaccount = "";
                    $logout = "";
                    $currantPage = $_SERVER['PHP_SELF'];
                    if ($currantPage == "/easy_cab/index.php") {
                        $home = 'class="current"';
                    } else if ($currantPage == "/easy_cab/about.php") {
                        $about = 'class="current"';
                    } else if ($currantPage == "/easy_cab/contact.php") {
                        $contact = 'class="current"';
                    } else if ($currantPage == "/easy_cab/properties.php") {
                        $properties = 'class="current"';
                    } else if ($currantPage == "/easy_cab/fare.php") {
                        $fare = 'class="current"';
                     } else if ($currantPage == "/easy_cab/register.php") {
                        $register = 'class="current"';
                    } else if ($currantPage == "/easy_cab/login.php") {
                        $login = 'class="current"';
                    } else if ($currantPage == "/easy_cab/myaccount.php") {
                        $myaccount = 'class="current"';
                    } else if ($currantPage == "/easy_cab/logout.php") {
                        $logout = 'class="current"';
                    }
                    ?>
                    <li <?php echo $home; ?>><a href="index.php">Home</a></li>
                    <li <?php echo $about; ?>><a href="about.php">About</a></li>
                    <li <?php echo $contact; ?>><a href="contact.php">Contact</a></li>
                    <li <?php echo $fare; ?>><a href="fare.php">Fare</a></li>
                    
                    <?php
                    if (!empty($user_id)) {
                        ?>
                        <li <?php echo $myaccount; ?>> <a href="myaccount.php">My&nbsp;Account </a></li>
                        <li <?php echo $logout; ?> class="last-menu"><a href="logout.php">Logout</a></li>
                        <?php
                    } else {
                        ?>
                        <li <?php echo $register; ?>><a href="register.php">&nbsp;&nbsp;&nbsp;Register&nbsp;&nbsp;</a></li>
                        <li <?php echo $login; ?> ><a href="login.php" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Login&nbsp;</a></li>
                    <?php } ?>
                    <div class="clear"> </div>
                </ul>
                <div class="clear"> </div>
            </div>

        </div>
        <!--  end-header -->
