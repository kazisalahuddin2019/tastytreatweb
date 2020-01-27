    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
    $token="";
    $mob="";
    $myObj = NULL;
    $token=$_POST["token"];
    $mob=$_POST["mobile"];

	$mob_string="SELECT * FROM `members` WHERE `mobno` = '$mob' ";
    $insert_string="INSERT INTO members (mobno,type,fcmid) VALUES('$mob', 1,'$token')";

     $mobsqldata = mysqli_query($con,$mob_string);   

     if(mysqli_num_rows($mobsqldata)>0){
          $myObj->Message="Mobile no already exist";
       } else{
       	$insertresult = mysqli_query($con,$insert_string);
       	if ($insertresult==1){

       		$id=mysqli_insert_id($con);
       		$alphabet = '1234567890';	    
			$pass = array();
			$alphaLength = strlen($alphabet) - 1;
			for ($i = 0; $i < 4; $i++) {
		        $n = rand(0, $alphaLength);
		        $pass[] = $alphabet[$n];
		    }	
		    $code=implode($pass);
		    $myObj->otp = $code;
		    $myObj->userid = $id;
		    $myObj->Message="Successful";

		    $fields = http_build_query([
					 'userid' => tt_api,
					 'password' => MD5(QxBqerT),
					 'msisdn' => $mob,
					 'masking' => 28585,
					 'message' => "Tasty Treat registration OTP: ".$code,
					 'unicode'=>  false
			 ]);
			$url = 'http://sms.prangroup.com/postman/api/sendsms?'.$fields;
			$ch = curl_init();
			curl_setopt($ch,CURLOPT_URL,$url);
			curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			curl_setopt($ch, CURLOPT_FAILONERROR, true);
			curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
			$response = curl_exec($ch);
			$res_arr=json_decode($response,true);
			curl_close($ch);
			$code=$res_arr['status'][0]['statuscode'];
	}else{
		$myObj->Message="Failure";
	}
       	
       }              

    
    $myJSON = json_encode($myObj);

	echo $myJSON;
	
    ?>

