<?php
	error_reporting(E_ALL ^ E_DEPRECATED);
	$host 	= 	"mysql.hostinger.it";
	
	$uname 	= 	$_REQUEST['username'];
	$pwd 	= 	$_REQUEST['password'];
	$db 	= 	$_REQUEST['db_name'];

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");

	
	$query = "SHOW TABLES FROM $db";
	
	$result = mysql_query($query, $con);
	
	$array = array();
	
	if(mysql_num_rows($result)>0)
	{
		$array['code']=1;
		$array['num'] = mysql_num_rows($result);
		
		for ($i=0; $i<mysql_num_rows($result); ++$i) {
			$array['tab'.$i] = mysql_result($result,$i);
		}
	}
	else
	{
		$array['code']=1;
		$array['num'] = 1;
		$array['tab0'] = "Nessuna tabella trovata";
	}
	
	print(json_encode($array));
	mysql_close($con);
?>