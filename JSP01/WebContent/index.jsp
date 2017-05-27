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
				/* 
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
				,"/drawThrowBox?cmd=inputPage" */
				];

	var linkArr = new Array();
	
	$(document).ready(function(){
		
		loadPageList();
		
	});
	
	function printPageArr(){
		
		var dot = ".";
		var html = "";
		
		var list;
		
		if( linkArr.length > 0 ){
			list = linkArr;
		}else{
			list = links;
		}
		
		html += "<ul>";
		for( var i = 0, ii = list.length ; i<ii ; i++ ){
			html += "<li>";
			html += "<a href='" + dot +  list[i] + "' target='_blank'>";
			html += list[i];
			html += "</a>";
			html += "</li>";
		}
		
		html += "</ul>";
		
		$("#pageArr").html(html);
	}

	function loadPageList(){
		var datas = $("frm").serialize();
		
		$.ajax({
			url:"./getPage",
			data: datas,  //파라미터 보내기
			type:"GET", //post방식으로 요청하기
			dataType:"xml" ,//응답문서타입 설정하기
			
			success:function(data){
				var pageList = $(data).find("pageList");
				$("#pageSize").text( pageList.length );
				
				for(var i = 0, ii = pageList.length ; i<ii ; i++ ){
					var strTmp = $(pageList[i]).text();
					
					console.log( strTmp );
					linkArr.push( strTmp );
				}
				
				printPageArr();
				
			}//end success
		});
		
	}

</script>
</head>
<body>

	<form id="frm" action="">
		<input type="hidden" id="pageNum" name="pageNum" value="0"/>
	</form>
	
	<span id="pageSize">0</span>
	
	<%
		Date date = new Date(System.currentTimeMillis());
		
	%>
	<br/>
	

	<h1>Im working (<%= date.toString() %>) </h1>
	
	
	<div id="pageArr">
	
	</div>
	
	
</body>
</html>
