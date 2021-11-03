<?php
include './dbconfigur.php';
if (!empty($user_id)) {
    $propertyid = mysql_real_escape_string($_GET['pid']);
    ?>
    <html>
        <head>
            <title>Property Detail - Multi Real Estate Business Corporation</title>
            <?php include './title.php'; ?>
        </head>
        <body>            
            <?php include './header.php'; ?>
            <div class="gallery ind" >
                <?php
                $i = 0;
                $propertydetail = "select * from property where property_id='" . $propertyid . "' ";
                $result = mysql_query($propertydetail);
                if (mysql_num_rows($result) > 0) {
                    $row = mysql_fetch_array($result);
                    $i++;
                    ?>
                    <div  class="grid deto ind">
                        <div style="width: 900px;">
                            <div style="width: 400px;float: left;">
                                <ul style="width: 400px;clear: both;">
                                    <li>
                                        <span class="properties_data">Property Name:</span>
                                        <span class="properties_data1 red"> <?Php echo $row['name'] ?></span>
                                    </li>                                           
                                    <li>
                                        <span class="properties_data">Property Type:</span>
                                        <span class="properties_data1 red"><?Php echo $row['property_type'] ?></span>
                                    </li>                                            
                                    <li>
                                        <span class="properties_data">Land Area:</span>
                                        <span class="properties_data1 red"><?Php echo $row['land_area'] ?></span>
                                    </li>                                           
                                    <li>
                                        <span class="properties_data">Building Size:</span>
                                        <span class="properties_data1 red"><?Php echo $row['building_size'] ?></span>
                                    </li>
                                    <li>
                                        <span class="properties_data">Cost:</span>
                                        <span class="properties_data1 red"><?Php echo $row['prize'] ?></span>
                                    </li>                                            
                                    <li>
                                        <span class="properties_data">Built Year:</span>
                                        <span class="properties_data1 red"><?Php echo $row['built_year'] ?></span>
                                    </li>                                            
                                    <li>
                                        <span class="properties_data">Address:</span>
                                        <span class="properties_data1 red"><?Php echo $row['addr']; ?></span>
                                    </li>                                                                                
                                    <li>
                                        <span class="properties_data">Description</span>
                                        <span class="properties_data1 red"><?Php echo $row['discription'] ?></span>
                                    </li>
                                </ul> 
                                <div class="price" style="float: left;clear: both;">
                                    <a href="purchase.php?pid=<?php echo $row['property_id'] ?>">Purchase</a>
                                </div>
                            </div>
                            <div style="width: 450px;float: left;">
                                <img src="<?Php echo $row['imgpath2'] ?>" width="500"/>            
                            </div>
                        </div>                            
                    </div>
                    <?php  
                    }
                ?>
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