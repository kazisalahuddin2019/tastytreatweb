    <?php 
    include("connection.php"); ?>
    <?php
    if(isset($_POST['g_id'])) {
       $group_name=$_POST['g_id'];
      

     $sql = "SELECT * FROM `company` WHERE `mcompany` = "."'".$group_name."'";
      $res = mysqli_query($con, $sql);
      $val=mysqli_num_rows($res);
      
      if($val > 0) {
        
        echo "<option value=''> Select company</option>";
        while($row = mysqli_fetch_object($res)) {
          echo "<option value='".$row->id."'>".$row->companyname."</option>";
        }
      }
    } else {
      header('location: ./');
    }
    ?>