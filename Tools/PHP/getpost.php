<?php
   $array = array('title' => 'got it' ,
   	              'content'=>'success acept and response乱码');
   foreach ($array as $key => $value) {
   	      $array[$key] = urlencode($value);
   }
   echo urldecode(json_encode($array));
?>