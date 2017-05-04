<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
		
	#show_box{
		float:left;
		position:absolute;
		margin-left:30px;
		margin-top:30px;
		width: 500px;
		height:200px;
	}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Input Page</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.custom.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("#btn1").click(function(){
			getImage();
			
		});
		
		$("#message").keyup(function(){
			
			if( $("#live").is(":checked") ){
				getImage();
			}
			
		});
		
	});
	 
	function getImage(){
		
		var message = $("#message").val();
		
		var img = "http://localhost:8081/JSP01/drawText?cmd=drawImage&message="+message;
		$( "#img01" ).attr( "src" , img );
	}
	
	
</script>
</head>
<body>
	
	<input type="checkbox" name="live" id="live" value=""> live call
	
	<form id="frm" action="/JSP01/drawAjaxImage" method="GET">
		<input type="hidden" value="drawImage" name = "cmd"/>
		
		<input type="text" value="" name="message" id="message"/>
		
		<input type="button" id="btn1" value="show" />
	</form>
	
	<div id="show_box">
	
		<img id = "img01" src="http://localhost:8081/JSP01/drawText?cmd=drawImage&message='message'"/>
	
	</div>
	
</body>
</html>