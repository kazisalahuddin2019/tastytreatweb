    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
    $id="";
    $data = array();
    $id=$_GET["id"];

	$address_string="SELECT * FROM clientaddress WHERE  STATUS=0 AND userid = '$id'"; 
    
    $addressresult = mysqli_query($con,$address_string);
       if(mysqli_num_rows($addressresult)<1){
       		$myObj->status="0".$address_string;
    		$myObj->message="No  data exist";
    	}else{
    		$myObj->status="1";
    		while($addressrow = mysqli_fetch_object($addressresult)) {
    			array_push($data,$addressrow);
    		}
    		$myObj->data = $data;
       	}            

    $myObj->charge="60";
    $myJSON = json_encode($myObj);

	echo $myJSON;
	
    ?>

