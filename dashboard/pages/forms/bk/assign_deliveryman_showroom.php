<?php
  include "connection.php";
  $dblist_string="SELECT COUNT(resource.`id`) cnt, `brandid`, `link`, `db_id`, `uploadby`, `priority`, `type` ,brand.name FROM `resource`,brand where `brandid`!=0 and brand.id=resource.brandid group by `brandid` ";
  $dbdata = mysqli_query($con,$dblist_string);

  $dblist_string1="SELECT * FROM displayboard";
  $dbdata1 = mysqli_query($con,$dblist_string1);

  $dblist_string2="SELECT * FROM displayboard";
  $dbdata2 = mysqli_query($con,$dblist_string2);

?>




<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Online Display</title>
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
    // $('#btnadd').click(function(){
    //     $('.hover_bkgr_fricc').hide();
    // });
});

</script>

<script type="text/javascript">
  var typetxt=$( "#myselect option:selected" ).text();
  if (typetxt="Special") {
      $("#typediv").show();
  }else{
      $("#typediv").hide();
  }
</script>

<script type="text/javascript">
    $(document).ready(function() {
      $("#groupid").change(function() {
        var group_id = $(this).val();
        //alert(group_id);
        if(group_id != "") {
          $.ajax({
            url:"get_company.php",
            data:{g_id:group_id},
            type:'POST',
            success:function(response) {
              var resp = $.trim(response);
              $("#company").html(resp);
            }
          });
        } else {
          $("#state").html("<option value=''>------- Select --------</option>");
        }
      });
    });
</script>

<script type="text/javascript">
    $(document).ready(function() {
      $("#company").change(function() {
        var company_id = $(this).val();
        //alert(group_id);
        if(company_id != "") {
          $.ajax({
            url:"get_brand.php",
            data:{company_id:company_id},
            type:'POST',
            success:function(response) {
              var resp = $.trim(response);
              $("#brand").html(resp);
            }
          });
        } else {
          $("#state").html("<option value=''>------- Select --------</option>");
        }
      });
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
                  <small>Since Nov. 2019</small>
                </p>
              </li>
              <!-- Menu Body -->

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
          <p>kazi Md. Salahudin</p>
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
            <li><a href="create_delivery_man.php"><i class="fa fa-circle-o"></i> Delivery Man </a></li>
            <li><a href="assign_deliveryman_showroom.php"><i class="fa fa-circle-o"></i> Assign </a></li>
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
         File summary 
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Forms</a></li>
        <li class="active">Upload</li>
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
        Upload file
    </button>
<!-- </a> -->

    <div class="box-body">
        <table id="example1" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>SL</th> <th>Brand name</th>  <th>Count</th>
                </tr>
            </thead>
            <tbody>
                    <?php
                     $dblist=mysqli_num_rows($dbdata);
                    if ($dblist>0) {
                      $i=0; 
                      while($row = $dbdata->fetch_assoc()) {
                        $i++;
                          ?>
                              <tr>
                                  <td><?php echo $i;?></td><td><?php echo $row["name"];?></td><td><?php echo $row["cnt"];?></td>
                                  <td>
                                    <!-- <a href=""><button type="button" class="btn btn-default btn-xs">Details</button></a> -->
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
              <form  action="" method="POST" enctype="multipart/form-data">  
                      <div class="row">

                       <div class="col-md-12">
                          <label>Select Group</label>
                          <select  class="form-control" name="groupid" id="groupid">
                            <option value="pran">PRAN</option>
                            <option value="rfl">RFL</option>
                            
                          </select>
                        </div>
                        <!-- /.col -->
                       <div class="col-md-12">
                          <label>Select company</label>
                          <select class="form-control" name="company" id="company">
                          </select>
                        </div>

                        <div class="col-md-12">
                          <label>Select brand</label>
                          <select class="form-control" name="brand" id="brand">
                          </select>
                        </div>
                        
                        <div class="col-md-12">
                          <label>Select display board</label>
                          <select  class="form-control" name="dbid">
                            <option value="0">All</option>
                            <?php
                               $dblistt=mysqli_num_rows($dbdata1);
                              if ($dblistt>0) {
                                 while($row = $dbdata1->fetch_assoc()) {
                                    ?>
                                      <option value="<?php echo $row['id'];?>"><?php echo $row['name'];?></option>
                                    <?php
                                 }
                               }
                            ?>
                            
                          </select>
                        </div>

                         <div class="col-md-12">
                          <label>Select priority</label>
                          <select  class="form-control" name="priorityid" id="priorityid">
                            <option value="2">Normal</option>
                            <option value="1">High</option>
                            
                          </select>
                        </div>
                        
                        <div class="col-md-12" id="typetxt">
                          <label for="fileToUpload">File upload</label>
                          <input type="file" name="fileToUpload" id="fileToUpload">                  
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
<script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()

    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, locale: { format: 'MM/DD/YYYY hh:mm A' }})
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>
</body>
</html>


<?php
if(isset($_POST['submit'])){
  
    $brand="";
    $dbid="";
    $target_file="";
    $priorityid="";
    
     $brand=$_POST["brand"];
     $dbid=$_POST["dbid"];
     $priorityid=$_POST["priorityid"];
     $target_file =basename($_FILES["fileToUpload"]["name"]);

     $target_dir = "filesuploads/";
     $file_path = $target_dir . basename($_FILES["fileToUpload"]["name"]);

    if ($brand=="") {
     echo '<script>window.alert("Brand name empty")</script>';
    }elseif ($dbid=="") {
     echo '<script>window.alert("Display board name empty")</script>';
    }elseif ($priorityid=="") {
     echo '<script>window.alert("Priority empty")</script>';
    }elseif ($target_file=="") {
     echo '<script>window.alert("File empty")</script>';
    }else{
      $str_arr = explode (".", $target_file);
      $extn= $str_arr[1];

      if ($extn=="png" || $extn=="mp4") {
          if (file_exists($file_path)) {
            echo '<script>window.alert("File already exists")</script>';
            
          }else{
            if($extn=="png"){
                $typeval=2;
            }else{
                $typeval=1;
              }

            if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $file_path)) {
              //echo "The file ". basename( $_FILES["fileToUpload"]["name"]). " has been uploaded.";
              if($dbid==0){
                while($row2 = $dbdata2->fetch_assoc()) {
                $dbid=$row2['id'];
                $resource_string="INSERT INTO `resource` ( `brandid`, `link`, `db_id`, `uploadby`, `priority`, `type`) VALUES ( '$brand', '$target_file', '$dbid', '1', '$priorityid', '$typeval');";
                $sqlupdata = mysqli_query($con,$resource_string); 
                if ($sqlupdata>0) {
                    
                    echo '<script>window.alert("Succefully uploaded")</script>';
                    echo '<script>window.location.href = "schedule_video_img.php";</script>';

                 }else{
                    echo '<script>window.alert("Upload failure")</script>';
                    echo '<script>window.location.href = "upload_video_img.php";</script>';
                 }
              }
              }else{
                $resource_string="INSERT INTO `resource` ( `brandid`, `link`, `db_id`, `uploadby`, `priority`, `type`) VALUES ( '$brand', '$target_file', '$dbid', '1', '$priorityid', '$typeval');";
                $sqlupdata = mysqli_query($con,$resource_string); 
                if ($sqlupdata>0) {
                    
                    echo '<script>window.alert("Succefully uploaded")</script>';
                    echo '<script>window.location.href = "upload_video_img.php";</script>';

                 }else{
                    echo '<script>window.alert("Upload failure")</script>';
                    
                 }
              }
            }else {
                echo "Sorry, there was an error uploading your file.";
              }
          }
      }else{
        echo '<script>window.alert("Please upload mp4 or png format file")</script>';
      }//format chk

    }//field validation
 

}//sumit
?>