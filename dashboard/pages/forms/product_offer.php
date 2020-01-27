<?php
  include "connection.php";
  $dblist_string="select id,type,status, itemcode, itemname, amount,qnty,freeqnty,freeitemcode, GROUP_CONCAT(`nnam` SEPARATOR ', ') nam from ( SELECT o.id, o.itemcode,o.status, o.type,i.itemname, od.amount,od.qnty,od.freeqnty,od.freeitemcode, (SELECT itemname from item itm where itm.itemcode = od.freeitemcode) nnam FROM `offer` o LEFT JOIN item i on o.itemcode = i.itemcode LEFT JOIN offer_details od on od.offerid = o.id WHERE o.`status` = 0 ) t group by id";
  $dbdata = mysqli_query($con,$dblist_string);

  $dblist_string1="SELECT * FROM `item` WHERE `haveoffer` = 1 and `status` = 0";
  $dbdata1 = mysqli_query($con,$dblist_string1);

  $dblist_string2="SELECT * FROM `item` WHERE `status` = 0 ";
  $dbdata2 = mysqli_query($con,$dblist_string2);
?>




<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Tasty Treat</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="../../bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="../../bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="../../plugins/iCheck/all.css">
  <!-- Bootstrap Color Picker -->
  <link rel="stylesheet" href="../../bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
  <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="../../plugins/timepicker/bootstrap-timepicker.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="../../bower_components/select2/dist/css/select2.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">
 <link rel="stylesheet" href="../../dist/css/mystyle.css">
   <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="http://weareoutman.github.io/clockpicker/dist/jquery-clockpicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://weareoutman.github.io/clockpicker/dist/jquery-clockpicker.min.css">
<script type="text/javascript">
  
  $(document).ready(function () {
    $("#btnadd").on('click', function(){
       $('.hover_bkgr_friccoffer').show();
    });

    $('.popupCloseButton').click(function(){
        $('.hover_bkgr_friccoffer').hide();
    });
    // $('#btnadd').click(function(){
    //     $('.hover_bkgr_fricc').hide();
    // });

    $("#type").on('change',function(){
      let option = $("#type option:selected").text();

      if(option=="Product"){
        $(".paq").css("display", "block");
        $(".qnty").css("display", "none");
      }else{
        $(".paq").css("display", "none");
        $(".qnty").css("display", "block");
      }

    });
});

</script>

<script type="text/javascript">
    function update(sh_id){

      //alert(sh_id);
       if(sh_id != "") {
          $.ajax({
            url:"update_offer.php",
            data:{g_id:sh_id},
            type:'POST',
            success:function(response) {
              location.reload();
              alert("Action performed successfully");
            }
          });
        } 
    }
    
</script>
<script type="text/javascript">
  var input = $('#datetimepicker3');
input.clockpicker({
    autoclose: true
});

</script>
<script type="text/javascript">
  var input = $('#datetimepicker4');
input.clockpicker({
    autoclose: true
});

</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="../../index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>Tas</b>ty</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Tasty</b>Treat</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          
          <!-- Notifications: style can be found in dropdown.less -->
          
          <!-- Tasks: style can be found in dropdown.less -->
         
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="../../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">Kazi Md. Salahuddin</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Kazi Md. Salahuddin - Android App Developer
                  <small>Since Nov. 2013</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Kazi Md. Salahuddin</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
     
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">  </li>
        <li class="header">  </li>
        <li class="treeview active">
          <a href="#">
            <i class="fa fa-edit"></i> <span>Administrative task</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="upload_product_image.php"><i class="fa fa-circle-o"></i> Product image </a></li>
            <li><a href="product_offer.php"><i class="fa fa-circle-o"></i> Offer </a></li>
            <li><a href="showroom_list.php"><i class="fa fa-circle-o"></i> Showroom </a></li>
            <li><a href="all_item.php"><i class="fa fa-circle-o"></i> Item </a></li>
            <li><a href="create_delivery_man.php"><i class="fa fa-circle-o"></i> Delivery Man </a></li>
            <li><a href="assign_deliveryman_showroom.php"><i class="fa fa-circle-o"></i> Assign </a></li>
            <li><a href="clientlist.php"><i class="fa fa-circle-o"></i> Client </a></li>
          </ul>
        </li>
        
       
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
         Product offer
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Forms</a></li>
        <li class="active"> Product offer</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- SELECT2 EXAMPLE -->
      <div class="box box-default">
        
        <!-- /.box-header -->
        <div class="box  box-primary">
    <!-- <a href="@Url.Action("AddBrand", "Setting")"> -->
    <button type="button" class="btn btn-default btn-sm" id="btnadd">
        Add  offer
    </button>
<!-- </a> -->

    <div class="box-body">
        <table id="example1" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>SL</th> <th>Code</th>  <th> Name </th> <th> Offer </th><th> Action </th>
                </tr>
            </thead>
            <tbody>
                    <?php
                     $dblist=mysqli_num_rows($dbdata);
                    if ($dblist>0) {
                      $i=0; 
                      while($row = $dbdata->fetch_assoc()) {
                        $i++;
                        $offerval="";
                        $amount=$row["amount"];
                        $qnty=$row["qnty"];
                        $freeqnty=$row["freeqnty"];
                        $nam=$row["nam"];
                        $type=$row["type"];
                        $status=$row["status"];
                        if ($status==0) {
                          $val='Off';
                        }else{
                          $val='On';
                        }

                        if ($type==3) {
                          $offerval="Purchage ".$qnty.",get free ".$freeqnty." item (";
                          $offer=$offerval.$nam.")";
                        }else if ($type==1) {
                          $offer=$amount." Tk Discount ";
                        }else if ($type==2) {
                          $offer=$amount."% Discount ";
                        }
                          ?>
                              <tr>
                                  <td><?php echo $i;?></td><td><?php echo $row["itemcode"];?></td><td><?php echo $row["itemname"];?></td><td><?php echo $offer;?></td>
                                  <td>
                                    <!-- <button  data-id ="<?php echo $row["id"] ?>" type="button" class="btn btn-default btn-xs" id="btnupdate"onclick="update(this.getAttribute('data-id'))">Edit</button> -->
                                    <button  data-id ="<?php echo $row["id"] ?>" type="button" class="btn btn-default btn-xs" id="btnupdate"onclick="update(this.getAttribute('data-id'))"><?php echo $val;?></button>

                                  </td>
                                                      
                              </tr>
                          <?php
                      }
                      
                    }
                    ?>
      

            </tbody>

        </table>
    </div>

    

<div class="hover_bkgr_friccoffer">
    <span class="helper"></span>
    <div>
        <div class="popupCloseButton">+</div>
                
            <div class="box-body">
              <form  action="" method="POST" enctype="multipart/form-data">  
                      <div class="row">
                        <div class="col-md-12">
                        <div class="form-group">
                            <label>Item name</label>
                             <select  class="form-control" name="shid" style="width: 40%";>
                            <option value="0">Select</option>
                            <?php
                               $dblistt=mysqli_num_rows($dbdata1);
                              if ($dblistt>0) {
                                 while($row = $dbdata1->fetch_assoc()) {
                                  $id=$row['id'];
                                  $itemcode=$row['itemcode'];
                                  $itemname=$row['itemname'];
                                    ?>
                                      <option value="<?php echo $itemcode;?>"><?php echo $itemname.'('.$itemcode.')';?></option>
                                    <?php
                                 }
                               }
                            ?>
                            
                          </select>
                          </div>
                          </div>
                        <!-- /.col -->
                       <div class="col-md-12">
                          <div class="form-group">
                            <label>Type</label>
                              <select  class="form-control" name="type" id="type" style="width: 40%;">
                            <option value="0">Select</option>
                            <option value="1">Discount</option>
                            <option value="2">Percentage</option>
                            <option value="3">Product</option>
                            
                          </select>
                          </div>
                         
                        </div>

                        <div class="col-md-2 paq" style="display: none";>
                          <div class="form-group">
                            <label>Quantity</label>
                             <input type="text" name="qnty" id="qnty" class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                         

                        <div class="col-md-2 paq" style="display: none";>
                          <div class="form-group">
                            <label>Free</label>
                             <input type="text" name="free" id="free" class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                        <div class="col-md-8 paq" style="display: none">
                        <div class="form-group">
                            <label>Item</label>
                             <select  class="form-control" name="freeshid[]" multiple style="width: 40%";>
                            <option value="0">Self</option>
                            <?php
                               $dblistt=mysqli_num_rows($dbdata2);
                              if ($dblistt>0) {
                                 while($row = $dbdata2->fetch_assoc()) {
                                  $id=$row['id'];
                                  $itemcode=$row['itemcode'];
                                  $itemname=$row['itemname'];
                                    ?>
                                      <option value="<?php echo $itemcode;?>"><?php echo $itemname.'('.$itemcode.')';?></option>
                                    <?php
                                 }
                               }
                            ?>
                            
                          </select>
                          </div>
                          </div>

                        <div class="col-md-12 qnty" style="display: none">
                          <div class="form-group">
                            <label>Quantity</label>
                             <input type="text" name="amount" class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                        <div class="col-md-12 img">
                          <div class="form-group">
                            <label for="fileToUpload">Image upload</label>
                          <input type="file" name="fileToUpload" id="fileToUpload">  
                          </div>
                         
                        </div>

                    <div class='col-md-6'>
                        <div class="form-group">
                          <label>Start</label>
                            <div class='input-group' id='datetimepicker3'>
                                <input type='date' class="form-control" id="txtStartTime" data-default="20:48" name="schstart"/>
                                
                            </div>
                        </div>
                    </div>

                        

                        <div class='col-md-6'>
                            <div class="form-group">
                              <label>End</label>
                                <div class='input-group ' id='datetimepicker4'>
                                    <input type='date' class="form-control" id="txtStartTime" data-default="20:48" name="schend" />
                                    
                                </div>
                            </div>
                        </div>



                        <div class="col-md-12">
                          <div class="form-group">
                           
                            <br/>
                             <button type="submit" name="submit" id="btn-submit-ex" style="width: 40%;">Submit</button>
                          </div>
                         
                        </div>
                        <!-- /.col -->
                      </div>
                      </form>
                      <!-- /.row -->
                    </div>
        
    </div>
</div>

    <!-- /.box-body -->
</div>
        <!-- /.box-body -->
       
      </div>
      <!-- /.box -->

      <div class="row">
        
        <!-- /.col (left) -->
       
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.1.1
    </div>
    <strong>Copyright &copy; 2019-2020 <a href="https://adminlte.io">MIS</a>.</strong> All rights
    reserved.
  </footer>

  <!-- Control Sidebar -->
 
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Select2 -->
<script src="../../bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="../../plugins/input-mask/jquery.inputmask.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="../../bower_components/moment/min/moment.min.js"></script>
<script src="../../bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="../../bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="../../bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="../../plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="../../plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js"></script>
<!-- Page script -->

</body>
</html>


<?php
if(isset($_POST['submit'])){
    $action=1;
    $shid="";
    $type="";
    $amount=0;
    $qnty=0;
    $free=0;
    $freeshid=0;
    $schstart="";
    $schend="";
    
    $shid=$_POST["shid"];
    $type=$_POST["type"];

    $file_name = $_FILES['fileToUpload']['name'];
    $target_dir = "filesuploads/";
     $file_path = $target_dir . $file_name;

    if ($type==3) {
      $qnty=$_POST["qnty"];
      $free=$_POST["free"];
      $freeshid=$_POST["freeshid"];
      if ($freeshid==0) {
         $freeshid=$shid;
      }
    }else{
      $amount=$_POST["amount"];
    }
    
    $schstart=$_POST["schstart"];
    $schend=$_POST["schend"];

    
    if ($shid=="0") {
      $action=0;
     echo '<script>window.alert("Item name empty")</script>';
    }elseif ($type=="0") {
      $action=0;
     echo '<script>window.alert("Offer type  empty")</script>';
    }elseif ($type!="3") {
        if ($amount<1) {
          $action=0;
            echo '<script>window.alert("Amount empty")</script>';
            }
    }elseif ($type=="3") {
        if ($qnty<1) {
          $action=0;
            echo '<script>window.alert("Quantity empty")</script>';
            }elseif ($free<1) {
              $action=0;
              echo '<script>window.alert("Free Quantity empty")</script>';
            }
    }elseif ($schstart=="mm/dd/yyyy") {
      $action=0;
     echo '<script>window.alert("Start time empty")</script>';
    }elseif ($file_name=="") {
      $action=0;
     echo '<script>window.alert("Image empty")</script>';
    }
 
    if ($action==1) {  
    if (file_exists($file_path)) {
            echo '<script>window.alert("File already exists")</script>';
            echo $file_path;
            
          }else{
            $moved = move_uploaded_file($_FILES["fileToUpload"]["tmp_name"],$file_path);
            if( $moved ) {   
      		$size=sizeof($freeshid);
            $dbchk_string="UPDATE `item` SET `haveoffer` = '0' WHERE `item`.`itemcode` = $shid";
            $sqldata = mysqli_query($con,$dbchk_string); 

            $insert_string="INSERT INTO offer ( itemcode, type, starttime, endtime) VALUES ( '$shid', '$type', '$schstart', '$schend')";
            $insertresult = mysqli_query($con,$insert_string); 
            $offerid=mysqli_insert_id($con);
            for ($i=0; $i <$size ; $i++) { 
            	 $freeshidData=$freeshid[$i];
                if ($freeshidData=="") {
                    $freeshidData=0;
                    }
             $insert_string1="INSERT INTO `offer_details` (`offerid`, `amount`, `qnty`, `freeqnty`, `freeitemcode`) VALUES ('$offerid', '$amount', '$qnty', '$free', '$freeshidData')";
            $insertresult = mysqli_query($con,$insert_string1);
            }
                         
       
          if ($insertresult==1){
             echo '<script>window.alert("Succefully added")</script>';
             echo '<script>window.location.href = "product_offer.php";</script>';
          }else{
             echo '<script>window.alert("Failure")</script>';
          }
      }   
          }  
    
    }
}
?>