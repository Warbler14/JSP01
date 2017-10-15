<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
		
	#show_box{
		margin-left:30px;
		margin-top:30px;
		width: 500px;
		height:200px;
	}
	
	#textArea{
		border: 4px dotted green; 
		border-bottom: 4px dashed blue;
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
	var console = window.console || { log: function() {} };

	$(document).ready(function(){
		
		$("#btn1").click(function(){
			insertText();
		});
		
		$("#btn2").click(function(){
			getImage();
			
		});
		/* 
		$("#message").keyup(function(){
			
			if( $("#live").is(":checked") ){
				getImage();
			}
			
		});
		 */
	});

	function insertText(){
		
		var htmls = $("#textArea").html();
		var textBox = $("#textBox").val();
		
		//alert( htmls);
		//alert( htmls.length );
		
		if( htmls.length > 3 ){
			htmls = htmls + "<br/>" +  textBox;
		}else{
			console.log(htmls);
			htmls = htmls + textBox;	
		}
		
		$("#textArea").html( htmls );
		$("#textBox").val("");
		
	}
	
	
	function getImage(){
		
		var message = $("#textArea").html();
		alert(message);
		message = message.replace(/<br>/gi, "%0A");	//URL encoding  ('\n')
		
		var letterWidth = $("#letterWidth").val();
		var letterHight = $("#letterHight").val();
		
		var img = "http://localhost:8081/JSP01/drawTextList?cmd=drawImage"
				+ "&message="+ encodeURI(encodeURIComponent(message)) 
				+ "&letterWidth=" + letterWidth + "&letterHight=" + letterHight;
		$( "#img01" ).attr( "src" , img );
	}
	
	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : evt.keyCode
		return !(charCode > 31 && (charCode < 48 || charCode > 57));
	}
	
</script>
</head>
<body>
	
	<input type="checkbox" name="live" id="live" value=""> live call
	
	<form id="frm" action="/JSP01/drawAjaxImage" method="GET">
		<input type="hidden" value="drawImage" name = "cmd"/>
		
		<span>letterWidth : </span>
		<input type="text" name="letterWidth" id="letterWidth" value="20" maxlength="3" onkeypress="return isNumberKey(event);"/>
		<br/>
		<span>letterHight : </span>
		<input type="text" name="letterHight" id="letterHight" value="10" maxlength="3" onkeypress="return isNumberKey(event);"/>
		<br/>
	
		<div id="textArea">
		</div>
		
		<input type="text" name="textBox" id="textBox" value="" />
		<br/>
		
		<input type="button" id="btn1" value="insertText" />
		
		<input type="button" id="btn2" value="show" />
	</form>
	
	<div id="show_box">
	
		<img id = "img01" src="http://localhost:8081/JSP01/drawTextList?cmd=drawImage&message=&letterWidth=20&letterHight=20"/>
	
	</div>
	
</body>
</html>