<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

	#frm input[type=text] {
		width: 50px;
	}

	#matrix tr td{
		border: 1px solid black;
	}
	

	#cube{
		width: 50px;
		height:50px;
		background-color: white;
	}

	
	#show_box{
		float:left;
		position:absolute;
		margin-left:50px;
		margin-top:100px;
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
		
		buildTable();
	});
	
	function buildTable(){
		var boxWidth = parseInt( $("#boxWidth").val() );
		var boxHeight = parseInt($("#boxHeight").val() );
		var countX = parseInt($("#countX").val() );
		var countY =parseInt( $("#countY").val() );
		
		var html = "";
		
		html += "<table id='matrix'>";
		for( var i = 0 ; i < countY ; i++ ){
			html += "<tr>";
			for( var j = 0 ; j < countX ; j++ ){
				html += "<td style='width:"+ boxWidth +";height:"+ boxHeight +";' >";
				
				html += "<span>" + i +":" + j + "</span>"
				
				html += "</td>";
			}
			html += "</tr>";
		}
		html += "</table>";
		
		$("#tableArea").html(html);
	}
	
	
	function getImage(){
		
		
		
		var img = "http://localhost:8081/JSP01/drawColorMatrix?cmd=drawImage"
				+ "&c_width="+ x_limit +"&c_height=" + y_limit
				+ "&x_pos1="+ x_pos1+"&y_pos1=" +  y_pos1 
				+ "&x_pos2="+ x_pos2 +"&y_pos2=" +  y_pos2;
		
		$( "#img01" ).html( "src" , img );
	}
	
	
	
	
	
</script>
</head>
<body>
	
	
	<form id="frm" action="/JSP01/drawColorMatrix" method="GET">
		<input type="hidden" value="drawImage" name = "cmd"/>
		
		<span>boxWidth : </span> <input type="text" id="boxWidth"  name = "boxWidth"  value="10" maxlength="3"/><br/>
		<span>boxHeight : </span><input type="text" id="boxHeight" name = "boxHeight" value="10" maxlength="3"/><br/>
		<span>countX : </span>   <input type="text" id="countX"    name = "countX"    value="10" maxlength="3"/><br/>
		<span>countY : </span>   <input type="text" id="countY"    name = "countY"    value="10" maxlength="3"/><br/>
		
		<div id="tableArea"></div>
		
		<input type="button" onclick="javascript:buildTable();" value="buildTable"/>
	</form>
	
	<div id="show_box">
	
		<img id = "img01" src="http://localhost:8081/JSP01/drawColorMatrix?cmd=drawImage&c_width=800&c_height=600&x_pos1=100&y_pos1=300&x_pos2=500&y_pos2=300"/>
	
	</div>
	
</body>
</html>