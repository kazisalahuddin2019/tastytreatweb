    <?php 
    include("connection.php"); ?>
    <?php
    if(isset($_POST['company_id'])) {
       $company_id=$_POST['company_id'];
      

     $sql = "SELECT * FROM `brand` WHERE `comid` = ".$company_id;
      $res = mysqli_query($con, $sql);
      $val=mysqli_num_rows($res);
      
      if($val > 0) {
        
        echo "<option value=''> Select brand</option>";
        while($row = mysqli_fetch_object($res)) {
          echo "<option value='".$row->id."'>".$row->name."</option>";
        }
      }
    } else {
      header('location: ./');
    }
    ?>