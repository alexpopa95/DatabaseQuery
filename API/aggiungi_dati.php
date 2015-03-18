<?php
	error_reporting(E_ALL ^ E_DEPRECATED);
	$host 	= 	"mysql.hostinger.it";
	
	$uname 	= 	$_REQUEST['username'];
	$pwd 	= 	$_REQUEST['password'];
	$db 	= 	$_REQUEST['db_name'];
	
	$nome 	= 	$_REQUEST['nome'];
	$info1 	= 	$_REQUEST['info1'];
	$info2 	= 	$_REQUEST['info2'];

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");

	$query = "INSERT INTO $nome(col1, col2) VALUES('$info1', '$info2')";
	
	$result = mysql_query($query, $con);
	
	$flag['code']=0;
	
	if($result)
	{
		$flag['code']=1;
	}
	
	print(json_encode($flag));
	mysql_close($con);
?>