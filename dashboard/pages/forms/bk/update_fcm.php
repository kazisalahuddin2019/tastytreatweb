    <?php 
	error_reporting(E_ALL);
	ini_set('display_errors', 1);
    include("connection.php"); 
    $deviceid="";
    $fcmid="";
    $deviceid=$_POST["deviceid"];
    $fcmid=$_POST["fcmid"];
    
	
	$updatesql = "UPDATE displayboard SET fcmid='$fcmid',running_sch_id='0' WHERE deviceid='$deviceid'";
    	$updateres = mysqli_query($con, $updatesql);

    echo json_encode($updatesql);
	
    ?>

