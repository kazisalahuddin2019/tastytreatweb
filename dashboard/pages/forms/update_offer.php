    <?php 
    include("connection.php"); 
   
    if(isset($_POST['g_id'])) {
       $id=$_POST['g_id'];
      

      $sql = "SELECT * FROM `item` WHERE `id` = $id; ";
      $res = mysqli_query($con, $sql);
      $row = $res->fetch_assoc();
      
      $sqlx = "UPDATE `item` SET `status` = '1' WHERE `item`.`id` = $id; ";
      $resx = mysqli_query($con, $sqlx);
       $sqly = "UPDATE `offer` SET `status` = '1' WHERE `offer`.`id` = $id; ";
      $resy = mysqli_query($con, $sqly);
      //$val=mysqli_num_rows($res);
      }
      
    ?>