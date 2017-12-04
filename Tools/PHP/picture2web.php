<?php

 $agent = strtolower($_SERVER['HTTP_USER_AGENT']);
 $type = 'other';
	 if(strpos($agent, 'iphone') || strpos($agent, 'ipad') || strpos($agent, 'ipod') || strpos($agent, 'mac')){
	 		$type = 'ios';
	 }else if(strpos($agent, 'android')){
	 	$type = 'android';
	 }else{
	 	$type = 'others';
	 }
$url="Location: http://map.baidu.com/zt/client/index/?fr=anquanA&qd=1019152b&pt=328057&ct=baijiahao&mt=8";
	if ($type == 'ios') {
	 	$url="Location: https://itunes.apple.com/app/apple-store/id452186370?pt=328057&ct=baijiahao&mt=8";
	}else if ($type == 'android') {
		$url="Location: http://map.baidu.com/maps/download/index.php?app=map&qudao=1019152b";
	}else{

	}
	header($url);
 ?>