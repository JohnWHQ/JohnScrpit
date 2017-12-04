<?php
require("db_config.php");
header("Content-type: text/html; charset=utf-8"); 
//允许ajax跨域请求 * 所有来源
header('Access-Control-Allow-Origin:*'); 


    // 连接数据库
    $conn = mysql_connect($mysql_server_name,$mysql_username,$mysql_password); 
    // 数据库输出编码
    mysql_query("set names 'utf8'"); 
    // 打开数据库 
    mysql_select_db($mysql_database);


    $file = fopen("PLC_config_utf8.txt", "r");
    // $file = fopen("user_info.txt", "r"); 
    $user=array();
    $j=0;
    //输出文本中所有的行，直到文件结束为止。
    while(! feof($file)){
      
      $line = explode(",", fgets($file));
      $line = str_replace("\n","",$line);

      $plcio = $line[0];
      $device = $line[1];
      $status = "null";
      $deviceid = "";

      if (sizeof($line) == 3) {
        $deviceid = $line[2];
      }else{

        $status = $line[2];
        $deviceid = $line[3];
      }

      $insertSql = "insert into plc_device (plc_io,device,status,deviceid) values 
                                          ('".$plcio."','"
                                             .$device."','"
                                             .$status."','"
                                             .$deviceid ."');";

      var_dump(mysql_query($insertSql)) ;  
      
    }
    fclose($file);

    mysql_close(); 

?>