<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Input Page</title>
</head>
<body>

	<form action="/JSP01/drawSymbol" method="GET">
		<input type="hidden" value="drawImage" name = "cmd"/>
		<span>input number (0~9) : </span>
		<input type="text" value="" name="number"/>
		<input type="submit" value="Submit form "/>
	</form>
	
</body>
</html>