<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#cube{
		width: 50px;
		height:50px;
		background-color: white;
	}

	
	#show_box{
		float:left;
		position:absolute;
		margin-left:100px;
		margin-top:30px;
		width: 500px;
		height:500px;
	}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Input Page</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.custom.min.js"></script>

<script type="text/javascript">
	const x_limit = 500;
	const y_limit = 500;
	var x_pos = 0;
	var y_pos = 450;
	
	$(document).ready(function(){
		
		//$( "#cube" ).position({ left: 250, top: 250 });
		
		var myVar = setInterval(function(){
			getImage( 10, 10 );
		}, 1000);
		
	});
	
	
	
	function getImage( x_val, y_val ){
		
		nextPosition( x_val, y_val );
		
		var img = "http://localhost:8081/JSP01/drawThrowBox?cmd=drawImage&x_pos="+x_pos+"&y_pos=" + y_pos;
		$( "#img01" ).attr( "src" , img );
	}
	
	function nextPosition( x_val, y_val ){
		y_pos += y_val;
		
		if( x_limit <= x_pos ){
			x_pos = 0;
		}
		
		if( y_limit <= y_pos ){
			y_pos = 0;
			x_pos += x_val;
		}
		
	}
	
	
</script>
</head>
<body>
	
	
	<form id="frm" action="/JSP01/drawThrowBox" method="GET">
		<input type="hidden" value="drawImage" name = "cmd"/>
		<input type="hidden" value="0" name = "x_pos" id="x_pos"/>
		<input type="hidden" value="0" name = "y_pos" id="y_pos"/>
		
	</form>
	
	<div id="show_box">
	
		<img id = "img01" src="http://localhost:8081/JSP01/drawThrowBox?cmd=drawImage&x_pos=0&y_pos=0"/>
	
	</div>
	
</body>
</html>