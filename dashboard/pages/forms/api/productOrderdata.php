    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
	include("fcm.php");
    $userid="";
    $showRoom="";
    $dm="";
    $da="";
    $totalProductPrice="";
    $charge="";
    $tc="";
    $pm="";
    $aOrderArray="";

    $myObj = NULL;
    $deliveryObj = NULL;
    $userid=$_POST["userid"];
    $showRoom=$_POST["showRoom"];
    $dm=$_POST["dm"];
    $da=$_POST["da"];
    $totalProductPrice=$_POST["totalProductPrice"];
    $charge=$_POST["charge"];
    $tc=$_POST["tc"];
    $pm=$_POST["pm"];
    $aOrderArray=$_POST["aOrderArray"];

	if ($dm==1) {
		$del_string="SELECT * FROM showroom_deliveryman WHERE showroomid='$showRoom' ";
		//$myObj->notice1=$del_string;
		$delsqldata = mysqli_query($con,$del_string);  
		if(mysqli_num_rows($delsqldata)>0){
			while($delrow = mysqli_fetch_object($delsqldata)) {
    			//$dm=$delrow['memberid'];
    			$dm=$delrow->memberid;
    		}
		}
					$myObj->status=1;
					$myObj->msg="Order submited sucessfully";
					$deliveryObj->ordermsg="You have a product order";

					

					$fcmQuery="SELECT * FROM members WHERE id=$dm";
					$fcmresult = mysqli_query($con, $fcmQuery);
					while($fcmrow = mysqli_fetch_object($fcmresult)) {
					  $fcmid=$fcmrow->fcmid;
					  sendGCM($deliveryObj, $fcmid);
					  $fcmid="";
					}
	}
    // $myObj->Message=$del_string;
    // $myObj->data=$dm;
	$alphabet = 'abcdefghijklmnopqrstuvwxyz1234567890';	    
			$pass = array();
			$alphaLength = strlen($alphabet) - 1;
			for ($i = 0; $i < 5; $i++) {
		        $n = rand(0, $alphaLength);
		        $pass[] = $alphabet[$n];
		    }	
		    $code=implode($pass);

    $om_string="INSERT INTO order_master (id,customerid,showroomid,delivermanid,delivery_address) VALUES('$code',$userid,$showRoom,$dm,'$da')";
    //$myObj->notice2=$om_string;
	$omsqldata = mysqli_query($con,$om_string);
	 
	if($omsqldata){
		$paymt_string="INSERT INTO payment (orderid,product_price,charge,total_amount,method) VALUES('$code',$totalProductPrice,$charge,$tc,'$pm')";
		//$myObj->notice3=$paymt_string;
		$pmntsqldata = mysqli_query($con,$paymt_string);
		
		if($pmntsqldata){
			$jsonArray = json_decode($aOrderArray, True); 
			$elementCount  = count($jsonArray);

			// $myObj->Message=$jsonArray;
	 	// 	$myObj->data=$elementCount;

			for ($i=0; $i <$elementCount ; $i++) { 
				$ordobj=$jsonArray[$i];
				$itemid=$ordobj['itemID'];
				$qnty=$ordobj['qnty'];
				$rate=$ordobj['rate'];
				$type=$ordobj['type'];

				$od_string="INSERT INTO order_details (orderid,itemid,qnty,rate,sold_type) VALUES('$code',$itemid,$qnty,$rate,$type)";
				//$myObj->notice4=$od_string;
				$odsqldata = mysqli_query($con,$od_string);

			}
				if ($dm==0) {
					$showroom_string="SELECT * FROM showroom WHERE CODE='$showRoom'";
					$showroomsqldata = mysqli_query($con,$showroom_string);  
					if(mysqli_num_rows($showroomsqldata)>0){
						while($shrow = mysqli_fetch_object($showroomsqldata)) {
	    					$name=$shrow->name;
	    					$location=$shrow->location;
	    					$address=$shrow->address;
	    					$mobno=$shrow->mobno;
	    					$lat=$shrow->lat;
	    					$lng=$shrow->lng;

	    					$data->name=$name;
	    					$data->location=$location;
	    					$data->address=$address;
	    					$data->mobno=$mobno;
	    					$data->lat=$lat;
	    					$data->lng=$lng;
    					}
					}
					$myObj->data=$data;
					$myObj->orderid=$code;
					$myObj->status=1;
					$myObj->msg="Order submited sucessfully";
				}
				
		
			
			}
		} 
   $myObj->orderid=$code;
    
    $myJSON = json_encode($myObj);

	echo $myJSON;
	
    ?>


