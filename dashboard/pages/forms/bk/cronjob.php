    <?php 
	error_reporting(E_ALL);
	ini_set('display_errors', 1);
    include("connection.php"); 
    include("fcm.php"); 
    
	date_default_timezone_set("Asia/Dhaka");
	$dateTime= date('H:i');
	$type=0;
	$cart = array();
	$data="";
	$data=$data.$dateTime;
	echo $resQuery="SELECT * FROM schedulee WHERE starttime<='".$dateTime."' AND endtime>='".$dateTime."'";

	$result = mysqli_query($con, $resQuery);

	while($row = mysqli_fetch_object($result)) {
		$schid=$row->id;
		echo $asQuery="SELECT * FROM assignschedule WHERE sch_id=$schid";
		$asresult = mysqli_query($con, $asQuery);

		while($asrow = mysqli_fetch_object($asresult)) {
				$db_id=$asrow->db_id;
				$action=$asrow->action;
				if($action=="Image"){
				 	$type=2;
				 }else if($action=="Video"){
				 	$type=1;
				 }
				$data=$db_id.$action.$schid;

				echo $chkspQuery="SELECT * FROM displayboard WHERE id=$db_id and action_status=1 and running_sch_id!=$schid";
				 $chkspresult = mysqli_query($con, $chkspQuery);
				 
				 echo 'count'.$count=mysqli_num_rows($chkspresult);
				
				if ($count==1) {
					$data=$data.$count;
					
					$updatesql = "UPDATE displayboard SET running_sch_id=$schid WHERE id=$db_id";
     				$updateres = mysqli_query($con, $updatesql);

     				$resourceQuery="SELECT * FROM resource WHERE db_id=$db_id AND type=$type and priority!=4" ;
					$resourceresult = mysqli_query($con, $resourceQuery);
					$resourcecount=mysqli_num_rows($resourceresult);
					$data=$data."-".$resourcecount;

					if ($resourcecount>0) {

						while($resourcerow = mysqli_fetch_object($resourceresult)) {
							$link=$resourcerow->link;
							$priority=$resourcerow->priority;
							$data=$data."-".$link."-".$priority."-".$type;
							array_push($cart, $link."**".$priority);
							
							$sql = "INSERT INTO cronjob(comments) VALUES ('$data')";
     						$res = mysqli_query($con, $sql);
     						$data="";
						}
					    $fcmQuery="SELECT * FROM displayboard WHERE id=$db_id";
						$fcmresult = mysqli_query($con, $fcmQuery);
						 while($fcmrow = mysqli_fetch_object($fcmresult)) {
						    $fcmid=$fcmrow->fcmid;
						 	sendGCM($cart, $fcmid,$type);
						 	$fcmid="";
						 }
							
						
					}

     				
				}//chk_db_running_sch

				
		}//assignschedule

		}//schedulechk
	
       
    ?>

