    <?php 
    include("connection.php"); 
   
    if(isset($_POST['g_id'])) {
       $id=$_POST['g_id'];
      

      $sql = "SELECT * FROM `showroom` WHERE `id` = $id; ";
      $res = mysqli_query($con, $sql);
      $row = $res->fetch_assoc();
      $status=$row["status"];
      if ($status==0) {
        $statusData=1;
      }else{
        $statusData=0;
      }

      $sqlx = "UPDATE `showroom` SET `status` = '$statusData' WHERE `showroom`.`id` = $id; ";
      $sqlx = "UPDATE `showroom` SET `status` = '$statusData' WHERE `showroom`.`id` = $id; ";
      $resx = mysqli_query($con, $sqlx);
      //$val=mysqli_num_rows($res);
      }
      
    ?>