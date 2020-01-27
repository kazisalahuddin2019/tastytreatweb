    <?php 
	// error_reporting(E_ALL);
	// ini_set('display_errors', 1);
    include "../connection.php";    
    $myObj = null;
    $myLoc = NULL;
    $myItem = NULL;
    $data = array();
    $itemdata = array();

    $showroom_string="SELECT * FROM showroom WHERE location!='unknown' AND STATUS=0";
    $showroomsqldata = mysqli_query($con,$showroom_string); 

    $item_string="SELECT itemcode, itemname, image,haveimage,haveoffer, isactive, offertype,amount, qnty, freeqnty, GROUP_CONCAT(`freeitemcode` SEPARATOR ', ') freeItemCodes ,GROUP_CONCAT(`freeItemName` SEPARATOR ', ') freeItemName
		FROM
		(    
		    
		SELECT i.itemcode, i.itemname, i.image,i.haveimage,i.haveoffer,o.status isactive,o.type offertype,od.amount, od.qnty, od.freeqnty, od.freeitemcode,
		(SELECT ii.itemname FROM item ii WHERE ii.itemcode = od.freeitemcode) freeItemName
		FROM item i LEFT JOIN offer o ON i.itemcode = o.itemcode LEFT JOIN offer_details od ON o.id = od.offerid WHERE i.status = 0

		) tbl
		GROUP BY itemcode
		ORDER BY freeItemName DESC";
    $itemsqldata = mysqli_query($con,$item_string);     

    if(mysqli_num_rows($showroomsqldata)==0){
    	$myObj->Message="No showroom data exist";
    } else{
    	while($resourcerow = mysqli_fetch_object($showroomsqldata)) {
    		$location=$resourcerow->location;
    		$id=$resourcerow->id;
    		$code=$resourcerow->code;
    		$myLoc = null;
    		$myLoc->id=$id;
    		$myLoc->code=$code;
    		$myLoc->loc=$location;
    		array_push($data,$myLoc);
    	}
    		// print_r($data);
    	$myObj->data = $data;
    	$myObj->Message="Successful";

    	if(mysqli_num_rows($itemsqldata)==0){
    		$myObj->Message="No item data exist";
    	}else{
    		while($itemrow = mysqli_fetch_object($itemsqldata)) {
    			
    			$itemcode=$itemrow->itemcode;
    			$itemname=$itemrow->itemname;
    			$image=$itemrow->image;
    			$haveimage=$itemrow->haveimage;
    			$haveoffer=$itemrow->haveoffer;
    			$isactive=$itemrow->isactive;
    			$offertype=$itemrow->offertype;
    			$amount=$itemrow->amount;
    			$qnty=$itemrow->qnty;
    			$freeqnty=$itemrow->freeqnty;
    			$freeItemCodes=$itemrow->freeItemCodes;
    			$freeItemName=$itemrow->freeItemName;
//     			$myItem =  ['itemcode' =>  $itemcode,
//     							'itemname' =>  utf8_encode($itemname),
//     						'image' =>  $image,
//     					'haveimage' =>  $haveimage,
//     				'haveoffer' => $haveoffer,
//     			'status' =>  $status,
//     		'amount' =>  $amount,
//     	'qnty' =>  $qnty,
//     'freeqnty' =>  $freeqnty,
// 'freeitemcode' =>  $freeitemcode];
				$myItem = null;
    			$myItem->itemcode=$itemcode;
    			$myItem->itemname=utf8_encode($itemname);
    			$myItem->image=$image;
    			$myItem->haveimage=$haveimage;
    			$myItem->haveoffer=$haveoffer;
    			$myItem->isactive=$isactive;
    			$myItem->offertype=$offertype;
    			$myItem->amount=$amount;
    			$myItem->qnty=$qnty;
    			$myItem->freeqnty=$freeqnty;
    			$myItem->freeItemCodes=$freeItemCodes;
    			$myItem->freeItemName=$freeItemName;
    			array_push($itemdata,$myItem);
    		}
    		// print_r($itemdata);
    		$myObj->itemdata = $itemdata;
    	// echo $myObj;
    	}

    	$myJSON = json_encode($myObj);

    	echo $myJSON;

    }           
    
    
    ?>

