<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">

	var links = ["/HelloWorld"
				
				,"/ImageGet?fileName=overcome.jpg"
				,"/ImageGetSize?width=100&height=100"
				,"/paintImage?number=5"
				,"/drawSingleLine?x1=50&y1=250&x2=450&y2=250"
				,"/drawSpiral?width=500&height=500&x1=250&y1=250&degree=10&radius=50&count=50"
				
				,"/drawQuadrangle?width=500&height=500&x1=100&y1=400&x2=400&y2=400&x3=400&y3=100&x4=100&y4=100"
				,"/drawTriangle?width=500&height=500&x1=100&y1=400&x2=400&y2=400&x3=250&y3=120"
				,"/drawXline?width=500&height=500&x1=250&y1=250&x2=50&y2=80"
				,"/drawMirrorLine?width=500&height=500&x1=250&y1=250&x2=80&y2=300&x3=200&y3=50"
				,"/drawMiddleDot?width=500&height=500&x1=50&y1=450&x2=450&y2=50&radius=50"
				
				,"/drawBoxing01?width=500&height=500&x1=100&y1=400&x2=400&y2=400&x3=250&y3=120"
				,"/drawTwoBox?width=500&height=500&x1=100&y1=100&x2=200&y2=200"
				,"/drawOctagon?width=500&height=500&length=100"
				,"/drawSymbol?cmd=inputPage"
				,"/drawSymbol2?cmd=inputPage"
				
				,"/drawMousePosition?cmd=inputPage"
				,"/drawAjaxImage?cmd=inputPage"
				,"/drawText?cmd=inputPage"
				,"/drawRandomColor?cmd=inputPage"
				,"/drawSpiral2?width=500&height=500&round=20"
				
				,"/drawDiamond?width=500&height=500&round=20"
				,"/drawRotateSymbol?cmd=inputPage"
				,"/drawDropBox?cmd=inputPage"
				,"/drawMultiDropBox?cmd=inputPage"
				,"/drawCircleBox?cmd=inputPage"
						
				,"/drawColorMatrix?cmd=inputPage"
				,"/drawThrowBox?cmd=inputPage"
				];


	$(document).ready(function(){
		
		printPageArr();
	});

	function printPageArr(){
		var contextPath = "<c:out value='${pageContext.request.contextPath}'/>";
		var dot = "";
		var html = "";
		
		if( contextPath != null && contextPath != '' && contextPath != undefined ){
			dot = ".";
		}
		
		html += "<ul>";
		for( var i = 0, ii = links.length ; i<ii ; i++ ){
			
			var url = "<c:url value='" + links[i] + "'/>";
			
			html += "<li>";
			html += "<a href='" + dot +  url + "' target='_blank'>";
			html += links[i];
			html += "</a>";
			html += "</li>";
		}
		
		html += "</ul>";
		
		$("#pageArr").html(html);
	}


</script>
</head>
<body>
	<%
		Date date = new Date(System.currentTimeMillis());
		
	%>
	<br/>
	

	<h1>Im working (<%= date.toString() %>) </h1>
	
	<div id="pageArr">
	
	</div>
	
	
</body>
</html>