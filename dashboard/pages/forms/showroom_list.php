<?php
  include "connection.php";
  $dblist_string="SELECT * FROM `showroom`";
  $dbdata = mysqli_query($con,$dblist_string);

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

<script type="text/javascript">
  
  $(document).ready(function () {
    $("#btnadd").on('click', function(){
       $('.hover_bkgr_fricc').show();
    });

    $('.popupCloseButton').click(function(){
        $('.hover_bkgr_fricc').hide();
    });


});



</script>
<script type="text/javascript">
    function update(sh_id){

      //alert(sh_id);
       if(sh_id != "") {
          $.ajax({
            url:"update_showroom.php",
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
         Showroom
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Forms</a></li>
        <li class="active">Showroom</li>
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
        Add  showroom
    </button>
<!-- </a> -->

    <div class="box-body">
        <table id="example1" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>SL</th> <th>Name</th>  <th> Address </th> <th> Mobile no. </th><th> Action </th>
                </tr>
            </thead>
            <tbody>
                    <?php
                     $dblist=mysqli_num_rows($dbdata);
                    if ($dblist>0) {
                      $i=0; 
                      while($row = $dbdata->fetch_assoc()) {
                        $i++;
                        $status=$row["status"];
                        if ($status==0) {
                          $val='Offline';
                        }else{
                          $val='Online';
                        }
                          ?>
                              <tr>
                                  <td><?php echo $i;?></td><td><?php echo $row["name"];?></td><td><?php echo $row["address"];?></td><td><?php echo $row["mobno"];?></td>
                                  <td>
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

    

<div class="hover_bkgr_fricc">
    <span class="helper"></span>
    <div>
        <div class="popupCloseButton">+</div>
                
            <div class="box-body">
              <form  action="" method="POST" >  
                      <div class="row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Showroom Code</label>
                             <input type="text" name="code"  class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Name</label>
                             <input type="text" name="shname"  class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>
                        <!-- /.col -->
                       <div class="col-md-12">
                          <div class="form-group">
                            <label>Location</label>
                             <input type="text" name="location" class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Address</label>
                             <input type="text" name="address" class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Mobile no.</label>
                             <input type="text" name="mobno" class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Latitude</label>
                             <input type="text" name="lat" class="form-control " style="width: 40%;">
                          </div>
                         
                        </div>

                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Longitude</label>
                             <input type="text" name="lng" class="form-control " style="width: 40%;">
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

    $code="";
    $shname="";
    $address="";
    $location="";
    $mobno="";
    $lat="";
    $lng="";
    
    $code=$_POST["code"];
    $shname=$_POST["shname"];
    $location=$_POST["location"];
    $address=$_POST["address"];
    $mobno=$_POST["mobno"];
    $lat=$_POST["lat"];
    $lng=$_POST["lng"];

    if ($code=="") {
     echo '<script>window.alert("Showroom code empty")</script>';
    }elseif ($shname=="") {
     echo '<script>window.alert("Showroom name empty")</script>';
    }elseif ($location=="") {
     echo '<script>window.alert("Location  empty")</script>';
    }elseif ($address=="") {
     echo '<script>window.alert("Address  empty")</script>';
    }elseif ($mobno=="") {
     echo '<script>window.alert("Mobile no empty")</script>';
    }elseif ($lat=="") {
     echo '<script>window.alert("Latitude empty")</script>';
    }elseif ($lng=="") {
     echo '<script>window.alert("Longitude  empty")</script>';
    }else{
       //$dbchk_string="SELECT * FROM `displayboard` WHERE `name` = '$dbname' ";
       $insert_string="INSERT INTO showroom (code,name, location,address, mobno,lat,lng) VALUES ('$code','$shname', '$location','$address', '$mobno', '$lat', '$lng')";

       /*$sqldata = mysqli_query($con,$dbchk_string);
       if (mysqli_num_rows($sqldata)==1){
          echo '<script>window.alert("Display board name already exist")</script>';
       }else{*/
          $insertresult = mysqli_query($con,$insert_string);
          if ($insertresult==1){
             echo '<script>window.alert("Succefully added")</script>';
             echo '<script>window.location.href = "showroom_list.php";</script>';
          }else{
             echo '<script>window.alert("Failure")</script>';
          }
       //}
    }
 

}
?>