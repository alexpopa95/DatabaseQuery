<?php
	error_reporting(E_ALL ^ E_DEPRECATED);
	$host 	= 	"mysql.hostinger.it";
	
	$nome 	= 	$_REQUEST['nome'];
	$uname 	= 	$_REQUEST['username'];
	$pwd 	= 	$_REQUEST['password'];
	$db 	= 	$_REQUEST['db_name'];

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");

	$flag['code']=0;
	$query = "CREATE TABLE $nome(col1 varchar(10), col2 varchar(10))";
	$r = mysql_query($query, $con);
	
	if($r)
	{
		$flag['code']=1;
	}
	
	print(json_encode($flag));
	mysql_close($con);
?>