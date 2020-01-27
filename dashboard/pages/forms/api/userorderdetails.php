    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
    $id="";
    $data = array();
    $id=$_POST["id"];

	$order_string="SELECT i.itemname,od.qnty,od.rate,od.sold_type FROM order_details od,item i WHERE i.itemcode=od.itemid AND orderid='$id'"; 
    
    $addressresult = mysqli_query($con,$order_string);
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

