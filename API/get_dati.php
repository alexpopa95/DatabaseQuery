<?php
	error_reporting(E_ALL ^ E_DEPRECATED);
	$host 	= 	"mysql.hostinger.it";
	
	$uname 	= 	$_REQUEST['username'];
	$pwd 	= 	$_REQUEST['password'];
	$db 	= 	$_REQUEST['db_name'];
	
	$nome 	= 	$_REQUEST['nome'];

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");

	$query = "SELECT col1, col2 FROM $nome";
	
	$result = mysql_query($query, $con);
	
	$array = array();
	
	if(mysql_num_rows($result)>0)
	{
		$array['code'] = 1;
		$array['num'] = mysql_num_rows($result);
		$i = 0;
		while($row = mysql_fetch_row($result)) {
		    $array['col1row'.$i] = $row[0];
		    $array['col2row'.$i] = $row[1];
        	$i++;
		}
	}
	else
	{
		$array['code']=1;
		$array['num'] = 1;
		$array['col1row0'] = "vuoto";
		$array['col2row0'] = "vuoto";
	}
	
	print(json_encode($array));
	mysql_close($con);
?>