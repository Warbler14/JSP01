<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<title>Input Page</title>
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



	<body>
		1
		<canvas id="canvas1" width="800" height="480"></canvas>
		2
	
		<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.custom.min.js"></script>
		<script type="text/javascript">
			const x_limit = 500;
			const y_limit = 500;
			
			var canvas = document.getElementById("canvas1");   
			var context = canvas.getContext("2d"); 
			var g = 0.3;                // 중력 가속도  
			var vx = 0, vy = 0;         // 속도  
			var posX = 50, posY = 200;  // 위치  
			     
			vx = 8;
			vy = -10;                   // 초기 속도값 지정   
			
			$(document).ready(function(){
				
				throwObject();
				
				
			});
			
			function throwObject(){
				
				var runEvent = setInterval(function(){  
					context.clearRect(0, 0, canvas.width, canvas.height);      
					context.strokeRect(0, 0, canvas.width, canvas.height);     
					vy = vy + g;        // 중력 가속도 계산       
					posX = posX + vx;      
					posY = posY + vy;      
					context.beginPath();       
					context.arc(posX, posY, 30, 0, 2*Math.PI, true);       
					context.stroke();      
					context.closePath();   
				}, 1000/60);
				
			}
			
		</script>
		
		
		
	<!-- 
		<form id="frm" action="/JSP01/drawThrowBox" method="GET">
			<input type="hidden" value="drawImage" name = "cmd"/>
			<input type="hidden" value="0" name = "x_pos" id="x_pos"/>
			<input type="hidden" value="0" name = "y_pos" id="y_pos"/>
			
		</form>
 	-->		
		<div id="show_box">
		<!-- 
			<img id = "img01" src="http://localhost:8081/JSP01/drawThrowBox?cmd=drawImage&x_pos=0&y_pos=450"/>
		 -->
		</div>
		
	</body>
</html>