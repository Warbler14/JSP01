<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#box{
		float:left;
		position:absolute;
		margin-left:50px;
		margin-top:50px;
		width: 500px;
		height:500px;
		background-color: black;
	}
	
	#cube{
		width: 50px;
		height:50px;
		background-color: white;
	}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Input Page</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.custom.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		
		$( "#cube" ).position({ left: 250, top: 250 });
		
		$( "#cube" ).draggable({
			drag: function(){
				
				var pos = $(this).position();
				var xPos = pos.left;
				var yPos = pos.top;
				var pageCoords = "( " + xPos + ", " + yPos + " )";
				$( "span:first" ).text( "( X, Y ) : " + pageCoords );
				$("#x_pos").val( xPos );
				$("#y_pos").val( yPos );
				
				//getImage( "/JSP01/drawMousePosition", "frm" );
			}
			
			
			
			
		});
		
		
	});
	/* 
	function getImage( url, frm ){
		
		$.ajax({
			url : url
			,data:$( "#" + frm ).serialize()
			,method:'post'
			,dataType : 'jpg'
			,success:function(jpg){
				$("#show_box").append( jpg );
			}
			,error:function(){
				console.log("error")
			}
			, complete:function(){
				//FnPageLoadding(false);
			}
		});
	}
	 */
	
</script>
</head>
<body>
	<p>
		<span>Move the mouse over the div.</span>
		<span>&nbsp;</span>
	</p>
	
	<form id="frm" action="/JSP01/drawMousePosition" method="POST">
		<input type="hidden" value="drawImage" name = "cmd"/>
		<input type="hidden" value="0" name = "x_pos" id="x_pos"/>
		<input type="hidden" value="0" name = "y_pos" id="y_pos"/>
			
		<div id="box">
			<div id="cube">
			</div>
		</div>
		
		<input type="submit" value="Submit form "/>
	</form>
	
	<div id="show_box">
	
	
	
	</div>
	
</body>
</html>