    <?php 
	//error_reporting(E_ALL);
	//ini_set('display_errors', 1);
	include "../connection.php";
    $token="";
    $id="";
    $pw="";
    $myObj = NULL;
    $data = NULL;
    $token=$_POST["token"];
    $id=$_POST["id"];
    $pw=$_POST["pw"];

	$del_string="SELECT * FROM members WHERE userid='$id' AND PASSWORD='$pw' AND TYPE='2' AND STATUS='0'AND isOffline='1' ";

     $delsqldata = mysqli_query($con,$del_string);   

     if(mysqli_num_rows($delsqldata)>0){
     	$myObj->status = 1;
     	while($delrow = mysqli_fetch_object($delsqldata)) {
    					$id=$delrow->id;
    					$name=$delrow->name;
    					$mobno=$delrow->mobno;
    					$image=$delrow->image;

    					$myObj->userid=$id;
    					$myObj->name=$name;
    					$myObj->mobno=$mobno;
    					$myObj->image=$image;
    				}
    				

    				$del_string="UPDATE members SET fcmid='$token' WHERE id=$id ";

     				$delsqldata = mysqli_query($con,$del_string);   
          
       } else{
       	$myObj->status = 0;
       	$myObj->Message="Invalid userid and password";
       	
       }              

    
    $myJSON = json_encode($myObj);

	echo $myJSON;
	
    ?>

