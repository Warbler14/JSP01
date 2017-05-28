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
		margin-left:10px;
		margin-top:30px;
		width: 500px;
		height:500px;
	}
</style>



	<body>
		<span>status : </span><span id="status"></span>
		1
		<canvas id="canvas1" width="1000" height="500"></canvas>
		2
		
		<div id="show_box">
			<img id = "img01" src="http://localhost:8081/JSP01/drawBounceBox?cmd=drawImage&width=1000&height=500&posX=50&posY=300"/>
		</div>
		
		<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.custom.min.js"></script>
		<script type="text/javascript">

			var width = 1000;
			var height = 500;
			
			var canvas = document.getElementById("canvas1");   
			var context = canvas.getContext("2d"); 
			
			var g = 0.2;                // 중력 가속도  
			var vx = 0, vy = 0;         // 속도  
			var posX = 50, posY = 400;  // 위치  
			     
			vx = 1;
			vy = 12;                   // 초기 속도값 지정   
			
			function throwObject(){
				var isFall = true;
				
				var runEvent = setInterval(function(){  
					//context.clearRect(0, 0, canvas.width, canvas.height);      
					context.strokeRect(0, 0, canvas.width, canvas.height);     
					
					if(isFall){
						
						vy = vy - g;        // 중력 가속도 계산       
						posX = posX + vx;      
						posY = posY - vy;
					
					}else{
						vy = vy + g;        // 중력 가속도 계산       
						posX = posX + vx;      
						posY = posY + vy;
						
					}
					
					if( isFall == true && posY > 400 ){
						isFall = false;
						//clearInterval(runEvent);
					}
					
					
					
					if( isFall == false && vy <= 0 ){
						isFall = true;
					}
					
					
					context.beginPath();       
					context.arc(posX, posY, 30, 0, 2*Math.PI, true);       
					context.stroke();      
					context.closePath();
					
					setImage( "img01", width, height, posX, posY );
					
					$("#status").text( "isFall : " + isFall + ", vy : " + vy)
				}, 1000/60);
				
			}
			//https://www.w3schools.com/tags/canvas_arc.asp
			//https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_canvas_arc
			
			function setImage ( id, width, height, posX, posY ){
				
				var img = "http://localhost:8081/JSP01/drawBounceBox?cmd=drawImage"
					+ "&width="+ width +"&height=" + height
					+ "&posX="+ posX +"&posY=" + posY;
				$( "#" + id ).attr( "src" , img );
			
			}
			
			
			$(document).ready(function(){

				throwObject();
				
			});
			
		</script>
		
		
		
	
		
		
	</body>
</html>