    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
    $id="";
    $status="";
    $myObj = NULL;
    $id=$_POST["id"];
    $status=$_POST["status"];

	$orderupdate_string="UPDATE order_master SET order_status=$status WHERE id='$id' ";

     $mobsqldata = mysqli_query($con,$orderupdate_string);   

     if($mobsqldata){
     	  $myObj->status = 1;
          $myObj->Message="Successful";
       } else{
       	
       	$myObj->status = 0;
          $myObj->Message="failed";
       }              

    
    $myJSON = json_encode($myObj);

	echo $myJSON;
	
    ?>

