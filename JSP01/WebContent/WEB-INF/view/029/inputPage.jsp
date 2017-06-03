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
		<span>posX : </span><input type="number" value="30" id="posX"/>
		
		<span>posY : </span><input type="number" value="50" id="posY"/>
		
		<span>radius : </span><input type="number" value="30" id="radius"/>
		
		<span>vy0 : </span><input type="number" value="0" id="vy0"/>
		
		<span>lineLength : </span><input type="number" value="470" id="lineLength"/>
		
		<!-- 
		<span>g0 : </span><input type="number" value="0.2" id="g0"/>
		 -->
		<input type="button" value="run" onclick="javascript:throwObject();"/>
		
		<br/>
		
		<span>status : </span><span id="status"></span>
		
		<br/>
		
		<canvas id="canvas1" width="1000" height="500"></canvas>
		
		<br/>
		
		
		<div id="show_box">
		<!-- 
			<img id = "img01" src="http://localhost:8081/JSP01/drawPendulum?cmd=drawImage&width=1000&height=500&posX=50&posY=300"/>
		 -->	
		</div>
		
		<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.custom.min.js"></script>
		<script type="text/javascript">

			var width = 1000;
			var height = 500;
			var vx0 = 1;
			var vy0 = 12;
			var g0 = 0.2;	// 중력 가속도
			
			var canvas = document.getElementById("canvas1");   
			var context = canvas.getContext("2d");
			
			// Fill with gradient
			/* context.strokeStyle = gradient;
			context.lineWidth = 3;
			context.strokeRect(20, 20, 150, 100); */

			var gradient = context.createLinearGradient(0, 0, width, 0);
			gradient.addColorStop("0",    "magenta");
			gradient.addColorStop("0.25", "blue");
			gradient.addColorStop("0.5",  "green");
			gradient.addColorStop("0.75", "orange");
			gradient.addColorStop("1.0",  "red");

			
			var g = g0;                 	// 중력 가속도
			var posX = 30;					// 위치  
			var posY =  50;  
			var radius = 30;
			var lineLength = height - radius;
			
			var vx = vx0;					// 초기 속도값 지정
			var vy = vy0;
			
			var vy1 = 0;
			
			var arcRad = (2*Math.PI)*2 + radius;
			
			function drawCanvas(){
				context.strokeStyle = gradient;
				context.lineWidth = 0.5;   
				context.strokeRect(0, 0, canvas.width, canvas.height);				
			}
			
			const MOVE_RIGHT = 1;
			const MOVE_LEFT = 2;
			
			function throwObject(){
				var isFall = true;
				var direction = MOVE_RIGHT;
				
				posX =   parseInt( $("#posX").val() );  
				posY =   parseInt( $("#posY").val() );  
				radius = parseInt( $("#radius").val() );
				vy =     parseInt( $("#vy0").val() );
				lineLength = parseInt( $("#lineLength").val() );
				
				
				if( posX <= 0 ){
					alert( "posX <= 0" );
					return;
				}
				
				if( posY <= 0 ){
					alert( "posY <= 0" );
					return;
				}
				
				if( radius <= 0 ){
					alert( "radius <= 0" );
					return;
				}
				/* 
				if( vy <= 0 ){
					alert( "vy <= 0" );
					return;
				}
				 */

				if( lineLength <= 0 ){
					alert( "lineLength <= 0" );
					return;
				}
				
				var runEvent = setInterval(function(){
					/* 
					context.strokeStyle = gradient;
					context.lineWidth = 0.5;
					//context.clearRect(0, 0, canvas.width, canvas.height);      
					context.strokeRect(0, 0, canvas.width, canvas.height);     
					 */
					drawCanvas();
					
					if(!isFall){
						vy = vy - g;
						posY = posY - vy;
						
						if(MOVE_RIGHT == direction){
							vx = vx - g;
							posX = posX + vx;
						}else if(MOVE_LEFT == direction ){
							vx = vx - g;
							posX = posX - vx;
						}
						
					}else{
						vy = vy + g;
						posY = posY + vy;
						
						if(MOVE_RIGHT == direction){
							vx = vx + g;
							posX = posX + vx;
						}else if(MOVE_LEFT == direction ){
							vx = vx + g;
							posX = posX - vx;
						}
						
					}
					
					
					
					//착지 판정
					if( isFall == true && posY >= (lineLength - arcRad) ){
						isFall = false;
						
						
						//posY = height - radius;
						
						//vy = parseInt( $("#vy0").val() ) - g;
						/* 
						if(vy1 > 0){
							vy = vy1;
						}else{
							var q = (vy + g) % ( ((posY - height) / (vy + g + height)) );
							
							vy1 = vy + g + q;
							vy = vy1;
							
						}
						 */
					}


					
					//상승 최고점 판정
					if( isFall == false && vy <= 0.0 ){
						isFall = true;

						if(MOVE_RIGHT == direction){
							direction = MOVE_LEFT;
						}else if(MOVE_LEFT == direction ){
							direction = MOVE_RIGHT;
						}
					}
					
					//좌우 벽면 바운스 판정
					/* 
					if( posX > (width - radius) || posX < radius){
						vx = vx * (-1);
						
						if( posX > width ){
							posX = width - radius;
						}else if(posX < radius){
							posX = radius;
						}
					}
					 */
					
					context.beginPath();       
					context.arc(posX, posY, radius, 0, 2*Math.PI, true);       
					context.stroke();      
					context.closePath();
					
					//setImage( "img01", width, height, posX, posY );
					
					$("#status").text( "isFall : " + isFall + ", vy : " + vy + ", posY : " + posY);
					
				}, 1000/60);
				
			}
			//https://www.w3schools.com/tags/canvas_arc.asp
			//https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_canvas_arc
			
			function setImage ( id, width, height, posX, posY ){
				
				var img = "http://localhost:8081/JSP01/drawPendulum?cmd=drawImage"
					+ "&width="+ width +"&height=" + height
					+ "&posX="+ posX +"&posY=" + posY;
				$( "#" + id ).attr( "src" , img );
			
			}
			
			
			$(document).ready(function(){

				drawCanvas();
				
			});
			
		</script>
		
		
		
	
		
		
	</body>
</html>