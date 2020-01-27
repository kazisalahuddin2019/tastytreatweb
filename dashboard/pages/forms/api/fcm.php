    <?php 
   function sendGCM($message, $id) {
 //$message="test msg";
 //$id="c-BpqQZpxMs:APA91bHZBJZRyqgBbbPRvvq9du5_G93kab3-zK5y56hvjS2Wb58EfHIFbUSZK_cGLL7dTuF62cn6FTQKmSFHsyFLjaQu9BesQXtfuQ6LiXLc0C0d88o-T2fUJHXfNGdQklFQ5K0VKyIH";

    $url = 'https://fcm.googleapis.com/fcm/send';

    $fields = array (
            'registration_ids' => array (
                    $id
            ),
            'data' => array (
                // "flag" => $flag,
                    "message" => $message
            )
    );
    $fields = json_encode ( $fields );

    $headers = array (
            'Authorization: key=' . "AAAAtREbPA4:APA91bEPXDFpZS0frKYgBybrbYYisjyD8xRhzhgBwMZwIYgNsTQ_UwodAj373j2hNYy777HQrrf718bWCYsf0hqQxjfDcYah69SCeHMIsZKsFXUF1CogIVtezsQp9IDlLh0xg64xvWNB",
            'Content-Type: application/json'
    );

    $ch = curl_init ();
    curl_setopt ( $ch, CURLOPT_URL, $url );
    curl_setopt ( $ch, CURLOPT_POST, true );
    curl_setopt ( $ch, CURLOPT_HTTPHEADER, $headers );
    curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, true );
    curl_setopt ( $ch, CURLOPT_POSTFIELDS, $fields );

    $result = curl_exec ( $ch );
    //echo $result;
    curl_close ( $ch );
}
    
    ?>