<?php
	error_reporting(E_ALL ^ E_DEPRECATED);
	$host 	= 	"mysql.hostinger.it";
	
	$uname 	= 	$_REQUEST['username'];
	$pwd 	= 	$_REQUEST['password'];
	$db 	= 	$_REQUEST['db_name'];

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	
	$flag['code']=0;
	
	if($con)
	{
		$flag['code']=1;
	}
	
	print(json_encode($flag));
	mysql_close($con);
?>