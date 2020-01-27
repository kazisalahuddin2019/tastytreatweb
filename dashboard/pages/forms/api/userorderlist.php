    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
    $id="";
    $status="";
    $data = array();
    $id=$_POST["id"];

	$address_string="SELECT om.id,om.order_status,om.delivery_address ,p.total_amount FROM order_master om,payment p

	WHERE p.orderid=om.id AND customerid=$id"; 
    
    $addressresult = mysqli_query($con,$address_string);
       if(mysqli_num_rows($addressresult)<1){
       		$myObj->status="0";
    		$myObj->message="No  data exist";
    	}else{
    		$myObj->status="1";
    		while($addressrow = mysqli_fetch_object($addressresult)) {
    			array_push($data,$addressrow);
    		}
    		$myObj->data = $data;
       	}            

    
    $myJSON = json_encode($myObj);

	echo $myJSON;
	
    ?>

