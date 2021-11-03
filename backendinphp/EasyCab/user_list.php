<?php
include './dbconfigur.php';
if (!empty($user_id)) {
    $error = "";

    if (isset($_GET['id']) && !empty($_GET['id'])) {
        $user_id = mysql_real_escape_string($_GET['id']);
        $sql = "DELETE FROM register WHERE id='" . $user_id . "'";
        $result = mysql_query($sql);
        $valueInsert = (int) $result;
        if ($valueInsert > 0) {
            header("location:user_list.php?status=success");
        } else {
            $error = "User has not been deleted.";
        }       
        
    }
}
?>
<html>
    <head>
        <title>User List - Easy Cab-My Account</title>
        <?php include './title.php'; ?>
        <script type="text/javascript">
            function deleteUser(){
                var con = confirm("Are you sure want to delete.");
                if(con == true){
                    return true;
                }else{
                    return false;
                }
            }
        </script>
    </head>
    <body>        
        <?php include './header.php'; ?>        

        <?php include './leftmenu.php'; ?>
        <div class="gallery">
            <div class="about">
                <h4>User List</h4>
                <form class="form-light mt-20" role="form" method="post" action="user_list.php">
                    <table class="table_list" cellspacing="2" cellpadding="2" width="100%">
                        <?php
                        if (isset($_GET['status']) && $_GET['status'] == "success") {
                            echo '<tr><td colspan="4" syle="color:red">User has been successfully deleted.</td></tr>';
                        }
                        if (!empty($error)) {
                            echo '<tr><td>' . $error . '</td></tr>';
                        }
                        ?>
                        <tr>
                            <td class="grid_heading">S.No</td>
                            <td class="grid_heading">Name</td>
                            <td class="grid_heading">Email</td>
                            <td class="grid_heading">Phone</td>
                            <td class="grid_heading">User Type</td>  
                            <td class="grid_heading">Details</td>
                            <td class="grid_heading">Delete</td>
                        </tr>
                        <?php
                        $i = 0;
                        $sql = "SELECT * FROM register WHERE id not in ('1') ORDER BY fname ASC";
                        $result = mysql_query($sql);
                        if (mysql_num_rows($result) > 0) {
                            while ($row = mysql_fetch_array($result)) {
                                $i++;
                                ?>
                                <tr>
                                    <td class="grid_label" align="center"><?php echo $i; ?></td>
                                    <td class="grid_label"><?php echo ucfirst($row['fname']) . " " . ucfirst($row['lname']); ?></td>
                                    <td class="grid_label"><?php echo $row['email'] ?></td>
                                    <td class="grid_label"><?php echo $row['contact'] ?></td>
                                    <td class="grid_label"><?php echo ucfirst($row['user_type']) ?></td>      
                                    <td class="grid_label" align="center"><a href="user_details.php?id=<?php echo $row ['id']; ?>">View</a></td>                            
                                    <td class="grid_label" align="center"><a href="user_list.php?id=<?php echo $row ['id']; ?>" onclick="return deleteUser();">Delete</a></td>                            
                                </tr>
                                <?php
                            }
                        }
                        ?>
                    </table>
                </form>

            </div>
            <div class="clear"> </div>
        </div>
        <?php include 'footer.php'; ?>
    </div>
</body>
</html>
