<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#checkBox tr td{
		border: 1px solid orange;
		background-color: black;
	}
	
	.box{
		width: 50px;
		height:50px;

	}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Input Page</title>

<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		$(".box").on()
		
		$(".box").on( "click", function() {
			var pos = $(this).children(':first').val();
			
			var data = $("#box_" + pos).val();
			
			
			
			if( data > 0 ){
				$("#box_" + pos).val(0);
				$("#div_" + pos).css("background-color", "black");
			}else{
				$("#box_" + pos).val(1);
				$("#div_" + pos).css("background-color", "white");
			}
			
			//alert( pos +", " + data);
		});
	});
	


</script>
</head>
<body>

	<form action="/JSP01/drawSymbol2" method="POST">
		<input type="hidden" value="drawImage" name = "cmd"/>
		
		
		<div>
			<table id="checkBox">
			<c:forEach var="i" begin="0" end="9" step="1">
				<tr>
				<c:forEach var="j" begin="0" end="9" step="1">
					<td id="div_${i}_${j}">
						<div class="box">
							<input type="hidden" value="${i}_${j}" />
							<input type="hidden" id = "box_${i}_${j}" name="box_${i}_${j}" value="0" />
						</div>
					</td>
				</c:forEach>
				</tr>
			</c:forEach>
			</table>
		</div>
		
		
		
		<input type="submit" value="Submit form "/>
	</form>
	
</body>
</html>