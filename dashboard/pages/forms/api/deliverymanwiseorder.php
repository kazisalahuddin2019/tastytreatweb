    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
    $id="";
    $status="";
    $data = array();
    $id=$_POST["id"];
    $status=$_POST["status"];

	$address_string="SELECT s.name showroomname,s.address showroomaddress,s.mobno showroommobno,s.lat showroomlat,s.lng showroomlng,m.name customername,m.mobno customermobno,om.delivery_address delivery_address,om.id,p.product_price productprice

					FROM showroom s,members m,order_master om,payment p

					WHERE p.orderid=om.id AND m.id=om.customerid AND s.code=om.showroomid AND  om.order_status=$status AND delivermanid=$id"; 
    
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

