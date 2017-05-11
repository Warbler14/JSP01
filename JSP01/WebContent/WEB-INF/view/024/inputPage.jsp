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
	const x_limit = 600;
	const y_limit = 600;
	var x_pos1 = 125;
	var y_pos1 = 0;
	var x_pos2 = 275;
	var y_pos2 = 0;
	var x_pos3 = 425;
	var y_pos3 = 0;
	
	$(document).ready(function(){
		
		var myVar = setInterval(function(){
			getImage();
		}, 1000);
		
	});
	
	
	
	function getImage(){
		
		y_pos1 = nextPosition( y_limit, y_pos1 + 10 );
		y_pos2 = nextPosition( y_limit, y_pos2 + 20 );
		y_pos3 = nextPosition( y_limit, y_pos3 + 30 );
		
		var img = "http://localhost:8081/JSP01/drawMultiDropBox?cmd=drawImage"
				+ "&x_pos1="+x_pos1+"&y_pos1=" + y_pos1
				+ "&x_pos2="+x_pos2+"&y_pos2=" + y_pos2
				+ "&x_pos3="+x_pos3+"&y_pos3=" + y_pos3;
		$( "#img01" ).attr( "src" , img );
	}
	
	
	
	function nextPosition( limit, pos ){
		
		if( limit <= pos ){
			pos = 0;
		}
		
		return pos;
	}
	
	
</script>
</head>
<body>
	
	
	<form id="frm" action="/JSP01/drawMultiDropBox" method="GET">
		<input type="hidden" value="drawImage" name = "cmd"/>
		<input type="hidden" value="0" name = "x_pos1" id="x_pos1"/>
		<input type="hidden" value="0" name = "y_pos1" id="y_pos1"/>
		<input type="hidden" value="0" name = "x_pos2" id="x_pos2"/>
		<input type="hidden" value="0" name = "y_pos2" id="y_pos2"/>
		<input type="hidden" value="0" name = "x_pos3" id="x_pos3"/>
		<input type="hidden" value="0" name = "y_pos3" id="y_pos3"/>
		
	</form>
	
	<div id="show_box">
	
		<img id = "img01" src="http://localhost:8081/JSP01/drawMultiDropBox?cmd=drawImage&x_pos1=125&y_pos1=0&x_pos2=275&y_pos2=0&x_pos3=425&y_pos3=0"/>
	
	</div>
	
</body>
</html>