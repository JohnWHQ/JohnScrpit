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


    // $selectAll = "select * from user_info;";
    // $saRes = mysql_query($selectAll);

    // // 获取数据集结果的行数
    // $num = mysql_num_rows($saRes);
    // var_dump($num);


    $file = fopen("user_info.txt", "r");
    // $file = fopen("user_info.txt", "r"); 
    $user=array();
    $j=0;
    //输出文本中所有的行，直到文件结束为止。
    while(! feof($file)){

      $line = explode("\t", fgets($file));
      $line = str_replace("\n","",$line);

      $name = $line[0];
      $password = $line[1];
      $email = $line[2];
      $info = $line[3];
      $nickname = $line[4];

      $insertSql = "insert into user_info (name,password,email,info,nickname) values 
                                          ('".$name."','"
                                             .$password."','"
                                             .$email."','"
                                             .$info."','"
                                             .$nickname ."');";
      var_dump(mysql_query($insertSql));  
      
    }
    fclose($file);


    // 关闭MySQL连接
    mysql_close(); 

?>