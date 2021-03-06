<?php

$api_key = ""; // Set API Key
$model_endpoint = "dataset/v"; // Set model endpoint (Found in Dataset URL)
$img_url = "https://i.imgur.com/PEEvqPN.png";

// URL for Http Request
$url =  "https://detect.roboflow.com/" . $model_endpoint
. "?api_key=" . $api_key
. "&image=" . urlencode($img_url);

// Setup + Send Http request
$options = array(
  'http' => array (
    'header' => "Content-type: application/x-www-form-urlencoded\r\n",
    'method'  => 'POST'
  ));

$context  = stream_context_create($options);
$result = file_get_contents($url, false, $context);
echo $result;
?>