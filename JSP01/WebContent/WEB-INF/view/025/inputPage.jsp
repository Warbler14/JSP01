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
	const x_limit = 800;
	const y_limit = 600;
	var x_pos1 = 100;
	var y_pos1 = 300;
	var x_pos2 = 500;
	var y_pos2 = 300;
	
	var r1 = 20;
	var r2 = 50;
	var angle1 = 0;
	var angle2 = 0;
	
	var add1 = 10;
	var add2 = 20;
	
	$(document).ready(function(){
		
		loader01();
		//roundPosition( 10, 20 );
	});
	
	
	function loader01(){
		var myVar = setInterval(function(){
			getImage();
		}, 1000);
	}
	
	
	
	function getImage(){
		
		angle1 += add1;
		if( angle1 > 360 ){
			angle1 = 0;
		}
		
		angle2 += add2;
		if( angle2 > 360 ){
			angle2 = 0;
		}
		
		var pos1 = roundPosition( r1, angle1 );
		var pos2 = roundPosition( r2, angle2 );
		
		
		var img = "http://localhost:8081/JSP01/drawCircleBox?cmd=drawImage"
				+ "&c_width="+ x_limit +"&c_height=" + y_limit
				+ "&x_pos1="+ (x_pos1 + pos1[0])+"&y_pos1=" + ( y_pos1 + pos1[1] )
				+ "&x_pos2="+ (x_pos2 + pos2[0])+"&y_pos2=" + ( y_pos2 + pos2[1] );
		$( "#img01" ).attr( "src" , img );
	}
	
	
	
	function roundPosition( r, angle ){
		var pos = [0,0];
		
		pos[0] = Math.ceil( r * Math.cos(angle * Math.PI/180) );

		pos[1] = Math.ceil( r * Math.sin(angle * Math.PI/180) );
		
		//alert( pos[0] + "," + pos[1] );
		return pos;
	}
	
	
</script>
</head>
<body>
	
	
	<form id="frm" action="/JSP01/drawCircleBox" method="GET">
		<input type="hidden" value="drawImage" name = "cmd"/>
		
	</form>
	
	<div id="show_box">
	
		<img id = "img01" src="http://localhost:8081/JSP01/drawCircleBox?cmd=drawImage&c_width=800&c_height=600&x_pos1=100&y_pos1=300&x_pos2=500&y_pos2=300"/>
	
	</div>
	
</body>
</html>